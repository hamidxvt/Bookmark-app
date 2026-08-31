import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../../core/services/gps_service.dart';

// ── Data models ───────────────────────────────────────────────────────────────
class RouteStop {
  final int visitId;
  final int sequence;
  final String customerName;
  final double lat;
  final double lng;
  final double distanceKm;
  final String status;

  const RouteStop({
    required this.visitId,
    required this.sequence,
    required this.customerName,
    required this.lat,
    required this.lng,
    required this.distanceKm,
    required this.status,
  });

  factory RouteStop.fromJson(Map<String, dynamic> j) {
    final c = j['customer'] as Map<String, dynamic>? ?? {};
    return RouteStop(
      visitId: j['visitId'] ?? 0,
      sequence: j['sequence'] ?? 0,
      customerName: c['name'] ?? j['customerName'] ?? 'Unknown',
      lat: (c['latitude'] ?? j['lat'] ?? 0).toDouble(),
      lng: (c['longitude'] ?? j['lng'] ?? 0).toDouble(),
      distanceKm: (j['distanceKm'] ?? 0).toDouble(),
      status: j['status'] ?? 'PENDING',
    );
  }
}

class DirectionsResult {
  final List<LatLng> polylinePoints;
  final String distanceText;
  final String durationText;
  final int durationSec;
  final String? walkDurationText;

  const DirectionsResult({
    required this.polylinePoints,
    required this.distanceText,
    required this.durationText,
    required this.durationSec,
    this.walkDurationText,
  });
}

// ── Google encoded-polyline decoder ──────────────────────────────────────────
List<LatLng> decodePolyline(String encoded) {
  final result = <LatLng>[];
  int index = 0, len = encoded.length;
  int lat = 0, lng = 0;

  while (index < len) {
    int b, shift = 0, result2 = 0;
    do {
      b = encoded.codeUnitAt(index++) - 63;
      result2 |= (b & 0x1f) << shift;
      shift += 5;
    } while (b >= 0x20);
    final dlat = (result2 & 1) != 0 ? ~(result2 >> 1) : (result2 >> 1);
    lat += dlat;

    shift = 0;
    result2 = 0;
    do {
      b = encoded.codeUnitAt(index++) - 63;
      result2 |= (b & 0x1f) << shift;
      shift += 5;
    } while (b >= 0x20);
    final dlng = (result2 & 1) != 0 ? ~(result2 >> 1) : (result2 >> 1);
    lng += dlng;

    result.add(LatLng(lat / 1e5, lng / 1e5));
  }
  return result;
}

// ── Providers ─────────────────────────────────────────────────────────────────
final routeProvider = FutureProvider.autoDispose<List<RouteStop>>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final gps = ref.watch(gpsServiceProvider);
  final pos = await gps.getCurrentPosition();

  final q = [
    if (pos != null) 'lat=${pos.latitude}',
    if (pos != null) 'lng=${pos.longitude}',
  ].join('&');

  final res = await dio.get('/route${q.isNotEmpty ? '?$q' : ''}');
  final data = (res.data['data'] as Map<String, dynamic>? ?? res.data ?? {});
  return ((data['stops'] as List?) ?? [])
      .map((e) => RouteStop.fromJson(e as Map<String, dynamic>))
      .where((s) => s.lat != 0 && s.lng != 0)
      .toList();
});

// ── Screen ────────────────────────────────────────────────────────────────────
class RouteMapScreen extends ConsumerStatefulWidget {
  const RouteMapScreen({super.key});

  @override
  ConsumerState<RouteMapScreen> createState() => _RouteMapScreenState();
}

class _RouteMapScreenState extends ConsumerState<RouteMapScreen> {
  GoogleMapController? _mapController;
  int _selectedStop = 0;
  Set<Polyline> _polylines = {};
  bool _navigating = false;

  Future<DirectionsResult?> _fetchDirections(LatLng origin, RouteStop dest) async {
    try {
      final dio = ref.read(dioClientProvider);
      final res = await dio.post(ApiConstants.directions, data: {
        'originLat': origin.latitude,
        'originLng': origin.longitude,
        'destLat': dest.lat,
        'destLng': dest.lng,
      });
      if (res.data['success'] != true) {
        final errMsg = res.data['error'] ?? 'Unknown error';
        _showSnack('Route error: $errMsg');
        return null;
      }
      final d = res.data['data'] as Map<String, dynamic>;
      final pts = decodePolyline(d['polyline'] as String? ?? '');
      return DirectionsResult(
        polylinePoints: pts,
        distanceText: d['distanceText'] ?? '',
        durationText: d['durationText'] ?? '',
        durationSec: (d['durationSec'] as num?)?.toInt() ?? 0,
        walkDurationText: d['walkDurationText'] as String?,
      );
    } catch (e) {
      _showSnack('Connection error: ${e.toString().split('\n').first}');
      return null;
    }
  }

  Future<void> _navigate(RouteStop stop) async {
    if (_navigating) return;
    setState(() => _navigating = true);
    try {
      final gps = ref.read(gpsServiceProvider);
      final pos = await gps.getCurrentPosition();
      if (pos == null) {
        _showSnack('GPS unavailable — enable location and try again');
        return;
      }

      final origin = LatLng(pos.latitude, pos.longitude);
      final directions = await _fetchDirections(origin, stop);

      if (directions != null && directions.polylinePoints.isNotEmpty) {
        setState(() {
          _polylines = {
            Polyline(
              polylineId: const PolylineId('route'),
              points: directions.polylinePoints,
              color: AppColors.primary,
              width: 5,
              startCap: Cap.roundCap,
              endCap: Cap.roundCap,
              jointType: JointType.round,
            ),
          };
        });

        // Fit map to show the full route
        final bounds = _boundsFromPoints(directions.polylinePoints);
        await _mapController?.animateCamera(CameraUpdate.newLatLngBounds(bounds, 80));

        // Send ETA to admin
        final dio = ref.read(dioClientProvider);
        final etaMin = (directions.durationSec / 60).ceil();
        // Best-effort ETA push — ignore errors silently
        try {
          await dio.post('/visits/${stop.visitId}/eta', data: {
            'visitId': stop.visitId,
            'customerName': stop.customerName,
            'eta_minutes': etaMin,
            'eta_walk_minutes': directions.walkDurationText != null
                ? _parseWalkMin(directions.walkDurationText!)
                : null,
            'eta_timestamp': DateTime.now()
                .add(Duration(seconds: directions.durationSec))
                .toIso8601String(),
            'lat': pos.latitude,
            'lng': pos.longitude,
            'destination_lat': stop.lat,
            'destination_lng': stop.lng,
            'distance_km': directions.distanceText,
          });
        } catch (_) {}

        if (mounted) {
          await _showETASheet(stop, directions);
        }
      } else {
        _showSnack('No route found — check internet connection');
      }
    } finally {
      if (mounted) setState(() => _navigating = false);
    }
  }

  LatLngBounds _boundsFromPoints(List<LatLng> pts) {
    double minLat = pts[0].latitude, maxLat = pts[0].latitude;
    double minLng = pts[0].longitude, maxLng = pts[0].longitude;
    for (final p in pts) {
      if (p.latitude < minLat) minLat = p.latitude;
      if (p.latitude > maxLat) maxLat = p.latitude;
      if (p.longitude < minLng) minLng = p.longitude;
      if (p.longitude > maxLng) maxLng = p.longitude;
    }
    return LatLngBounds(
      southwest: LatLng(minLat, minLng),
      northeast: LatLng(maxLat, maxLng),
    );
  }

  int _parseWalkMin(String text) {
    final hourMatch = RegExp(r'(\d+)\s*hour').firstMatch(text);
    final minMatch = RegExp(r'(\d+)\s*min').firstMatch(text);
    final h = int.tryParse(hourMatch?.group(1) ?? '0') ?? 0;
    final m = int.tryParse(minMatch?.group(1) ?? '0') ?? 0;
    return h * 60 + m;
  }

  void _showSnack(String msg) {
    if (!mounted) return;
    ScaffoldMessenger.of(context)
        .showSnackBar(SnackBar(content: Text(msg), behavior: SnackBarBehavior.floating));
  }

  Future<void> _showETASheet(RouteStop stop, DirectionsResult dir) async {
    await showModalBottomSheet(
      context: context,
      shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(28))),
      backgroundColor: Colors.white,
      builder: (_) => Padding(
        padding: const EdgeInsets.fromLTRB(20, 12, 20, 34),
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          // Handle
          Center(
            child: Container(
              width: 36, height: 4,
              decoration: BoxDecoration(
                  color: Colors.grey.shade200, borderRadius: BorderRadius.circular(4)),
            ),
          ),
          const SizedBox(height: 20),

          // Destination header
          Row(children: [
            Container(
              width: 44, height: 44,
              decoration: BoxDecoration(
                color: AppColors.primary.withOpacity(0.1),
                shape: BoxShape.circle,
              ),
              child: const Icon(Icons.location_on_rounded, color: AppColors.primary, size: 22),
            ),
            const SizedBox(width: 12),
            Expanded(child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
              Text(stop.customerName,
                  style: const TextStyle(fontSize: 16, fontWeight: FontWeight.w800,
                      color: Color(0xFF1E293B)),
                  maxLines: 1, overflow: TextOverflow.ellipsis),
              Text(dir.distanceText,
                  style: const TextStyle(color: Color(0xFF94A3B8), fontSize: 12, fontWeight: FontWeight.w500)),
            ])),
          ]),
          const SizedBox(height: 20),

          // ETA tiles
          Row(children: [
            Expanded(
              child: _ETATile(
                icon: Icons.directions_car_rounded,
                label: 'By Car',
                value: dir.durationText,
                color: AppColors.primary,
              ),
            ),
            if (dir.walkDurationText != null) ...[
              const SizedBox(width: 10),
              Expanded(
                child: _ETATile(
                  icon: Icons.directions_walk_rounded,
                  label: 'Walking',
                  value: dir.walkDurationText!,
                  color: const Color(0xFF3B82F6),
                ),
              ),
            ],
          ]),
          const SizedBox(height: 14),

          // Info banner
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
            decoration: BoxDecoration(
                color: const Color(0xFFF0FDF4),
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: const Color(0xFFBBF7D0))),
            child: const Row(children: [
              Icon(Icons.check_circle_rounded, color: Color(0xFF16A34A), size: 16),
              SizedBox(width: 8),
              Expanded(
                  child: Text('Route drawn on map · ETA sent to admin',
                      style: TextStyle(fontSize: 12, color: Color(0xFF166534),
                          fontWeight: FontWeight.w500))),
            ]),
          ),
          const SizedBox(height: 16),

          // CTA
          SizedBox(
            width: double.infinity, height: 52,
            child: ElevatedButton.icon(
              onPressed: () => Navigator.pop(context),
              icon: const Icon(Icons.navigation_rounded, size: 18),
              label: const Text('Start Navigation',
                  style: TextStyle(fontWeight: FontWeight.w700, fontSize: 14)),
              style: ElevatedButton.styleFrom(
                backgroundColor: AppColors.primary,
                foregroundColor: Colors.white,
                elevation: 0,
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
              ),
            ),
          ),
        ]),
      ),
    );
  }

  Set<Marker> _buildMarkers(List<RouteStop> stops, int selected, LatLng? myPos) {
    final ms = <Marker>{};
    if (myPos != null) {
      // Officer position with heading rotation
      ms.add(Marker(
        markerId: const MarkerId('me'),
        position: myPos,
        icon: BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueAzure),
        infoWindow: const InfoWindow(title: 'Your Location'),
        rotation: _myHeading ?? 0, // Rotate marker based on heading
        zIndex: 10,
      ));
    }
    for (var i = 0; i < stops.length; i++) {
      final s = stops[i];
      ms.add(Marker(
        markerId: MarkerId('stop_${s.visitId}'),
        position: LatLng(s.lat, s.lng),
        icon: i == selected
            ? BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueRed)
            : BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueOrange),
        infoWindow: InfoWindow(
          title: '${s.sequence}. ${s.customerName}',
          snippet: s.status,
        ),
        onTap: () => setState(() => _selectedStop = i),
        zIndex: i == selected ? 5 : 1,
      ));
    }
    return ms;
  }

  @override
  Widget build(BuildContext context) {
    final routeAsync = ref.watch(routeProvider);

    return Scaffold(
      backgroundColor: const Color(0xFF0F0A0A),
      body: routeAsync.when(
        loading: () => const Center(
          child: Column(mainAxisSize: MainAxisSize.min, children: [
            CircularProgressIndicator(color: AppColors.primary),
            SizedBox(height: 16),
            Text('Loading your route…', style: TextStyle(color: Colors.white70)),
          ]),
        ),
        error: (err, _) => _ErrorView(
          message: err.toString(),
          onRetry: () => ref.invalidate(routeProvider),
        ),
        data: (stops) => _MapBody(
          stops: stops,
          selectedStop: _selectedStop,
          polylines: _polylines,
          navigating: _navigating,
          onSelectStop: (i) {
            setState(() => _selectedStop = i);
            if (i < stops.length) {
              _mapController?.animateCamera(
                CameraUpdate.newLatLngZoom(LatLng(stops[i].lat, stops[i].lng), 15),
              );
            }
          },
          onMapCreated: (ctrl) => _mapController = ctrl,
          buildMarkers: (stops, myPos) => _buildMarkers(stops, _selectedStop, myPos),
          onNavigate: _navigate,
        ),
      ),
    );
  }
}

// ── Map body ──────────────────────────────────────────────────────────────────
class _MapBody extends ConsumerStatefulWidget {
  final List<RouteStop> stops;
  final int selectedStop;
  final Set<Polyline> polylines;
  final bool navigating;
  final ValueChanged<int> onSelectStop;
  final void Function(GoogleMapController) onMapCreated;
  final Set<Marker> Function(List<RouteStop>, LatLng?) buildMarkers;
  final Future<void> Function(RouteStop) onNavigate;

  const _MapBody({
    required this.stops,
    required this.selectedStop,
    required this.polylines,
    required this.navigating,
    required this.onSelectStop,
    required this.onMapCreated,
    required this.buildMarkers,
    required this.onNavigate,
  });

  @override
  ConsumerState<_MapBody> createState() => _MapBodyState();
}

class _MapBodyState extends ConsumerState<_MapBody> {
  LatLng? _myPos;
  double? _myHeading;

  @override
  void initState() {
    super.initState();
    _fetchPosition();
  }

  Future<void> _fetchPosition() async {
    final gps = ref.read(gpsServiceProvider);
    final pos = await gps.getCurrentPosition();
    if (pos != null && mounted) {
      setState(() {
        _myPos = LatLng(pos.latitude, pos.longitude);
        _myHeading = pos.heading >= 0 ? pos.heading : null;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final stops = widget.stops;
    final valid = stops.where((s) => s.lat != 0 && s.lng != 0).toList();

    final initialCamera = _myPos != null
        ? CameraPosition(target: _myPos!, zoom: 13)
        : valid.isNotEmpty
            ? CameraPosition(target: LatLng(valid[0].lat, valid[0].lng), zoom: 13)
            : const CameraPosition(target: LatLng(34.56, 71.55), zoom: 10);

    if (valid.isEmpty) return const _EmptyRoute();

    return Stack(children: [
      GoogleMap(
        onMapCreated: widget.onMapCreated,
        initialCameraPosition: initialCamera,
        markers: widget.buildMarkers(valid, _myPos),
        polylines: widget.polylines,
        myLocationEnabled: true,
        myLocationButtonEnabled: false,
        zoomControlsEnabled: false,
        mapToolbarEnabled: false,
        compassEnabled: true,
        trafficEnabled: true,
      ),

      // Top bar
      Positioned(
        top: 0, left: 0, right: 0,
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.fromLTRB(12, 12, 12, 0),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(16),
              child: BackdropFilter(
                filter: ImageFilter.blur(sigmaX: 16, sigmaY: 16),
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
                  decoration: BoxDecoration(
                    color: Colors.black.withOpacity(0.60),
                    borderRadius: BorderRadius.circular(16),
                  ),
                  child: Row(children: [
                    GestureDetector(
                      onTap: () => Navigator.of(context).maybePop(),
                      child: Container(
                        width: 34, height: 34,
                        decoration: BoxDecoration(
                          color: Colors.white.withOpacity(0.12),
                          borderRadius: BorderRadius.circular(10),
                        ),
                        child: const Icon(Icons.arrow_back_ios_new_rounded,
                            color: Colors.white, size: 15),
                      ),
                    ),
                    const SizedBox(width: 10),
                    const Icon(Icons.route_rounded, color: AppColors.primary, size: 20),
                    const SizedBox(width: 8),
                    Expanded(
                      child: Text('${valid.length} stops today',
                          style: const TextStyle(
                              color: Colors.white, fontWeight: FontWeight.w700, fontSize: 14),
                          overflow: TextOverflow.ellipsis),
                    ),
                    Container(
                      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 5),
                      decoration: BoxDecoration(
                          color: AppColors.primary.withOpacity(0.25),
                          borderRadius: BorderRadius.circular(8),
                          border: Border.all(color: AppColors.primary.withOpacity(0.4))),
                      child: Text(
                        '${valid.fold(0.0, (s, r) => s + r.distanceKm).toStringAsFixed(1)} km',
                        style: const TextStyle(
                            color: AppColors.primary, fontWeight: FontWeight.w800, fontSize: 13),
                      ),
                    ),
                  ]),
                ),
              ),
            ),
          ),
        ),
      ),

      // Bottom panel
      Positioned(
        bottom: 0, left: 0, right: 0,
        child: SafeArea(
          child: Column(mainAxisSize: MainAxisSize.min, children: [
            if (widget.selectedStop < valid.length)
              Padding(
                padding: const EdgeInsets.fromLTRB(12, 0, 12, 8),
                child: _StopDetailCard(
                  stop: valid[widget.selectedStop],
                  navigating: widget.navigating,
                  onDirections: () => widget.onNavigate(valid[widget.selectedStop]),
                ),
              ),
            SizedBox(
              height: 80,
              child: ListView.separated(
                scrollDirection: Axis.horizontal,
                padding: const EdgeInsets.fromLTRB(12, 0, 12, 12),
                itemCount: valid.length,
                separatorBuilder: (_, __) => const SizedBox(width: 8),
                itemBuilder: (_, i) {
                  final s = valid[i];
                  final sel = i == widget.selectedStop;
                  return GestureDetector(
                    onTap: () => widget.onSelectStop(i),
                    child: AnimatedContainer(
                      duration: const Duration(milliseconds: 200),
                      width: 150,
                      padding: const EdgeInsets.all(10),
                      decoration: BoxDecoration(
                        color: sel ? AppColors.primary : Colors.white.withOpacity(0.92),
                        borderRadius: BorderRadius.circular(12),
                        border: Border.all(
                            color: sel ? Colors.transparent : Colors.white.withOpacity(0.5)),
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Row(children: [
                            Container(
                              width: 20, height: 20,
                              decoration: BoxDecoration(
                                shape: BoxShape.circle,
                                color: sel
                                    ? Colors.white.withOpacity(0.3)
                                    : AppColors.primary.withOpacity(0.1),
                              ),
                              child: Center(
                                child: Text('${s.sequence}',
                                    style: TextStyle(
                                        fontSize: 10,
                                        fontWeight: FontWeight.w800,
                                        color: sel ? Colors.white : AppColors.primary)),
                              ),
                            ),
                            const SizedBox(width: 5),
                            Expanded(
                              child: Text(s.customerName,
                                  style: TextStyle(
                                      fontSize: 11,
                                      fontWeight: FontWeight.w600,
                                      color: sel ? Colors.white : const Color(0xFF1E293B)),
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis),
                            ),
                          ]),
                          const SizedBox(height: 4),
                          Text('${s.distanceKm.toStringAsFixed(1)} km',
                              style: TextStyle(
                                  fontSize: 10,
                                  color: sel ? Colors.white70 : const Color(0xFF94A3B8))),
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
          ]),
        ),
      ),
    ]);
  }
}

class _EmptyRoute extends StatelessWidget {
  const _EmptyRoute();

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Column(mainAxisSize: MainAxisSize.min, children: [
        Icon(Icons.map_outlined, color: Colors.white30, size: 64),
        SizedBox(height: 16),
        Text('No visits scheduled today',
            style: TextStyle(color: Colors.white70, fontSize: 16)),
        SizedBox(height: 8),
        Text('Check back after the daily schedule runs',
            style: TextStyle(color: Colors.white38, fontSize: 13)),
      ]),
    );
  }
}

// ── Stop detail card ──────────────────────────────────────────────────────────
class _StopDetailCard extends StatelessWidget {
  final RouteStop stop;
  final bool navigating;
  final VoidCallback onDirections;

  const _StopDetailCard(
      {required this.stop, required this.navigating, required this.onDirections});

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(16),
      child: BackdropFilter(
        filter: ImageFilter.blur(sigmaX: 20, sigmaY: 20),
        child: Container(
          padding: const EdgeInsets.all(14),
          decoration: BoxDecoration(
            color: Colors.white.withOpacity(0.92),
            borderRadius: BorderRadius.circular(16),
            border: Border.all(color: Colors.white.withOpacity(0.5)),
            boxShadow: [
              BoxShadow(
                  color: Colors.black.withOpacity(0.1),
                  blurRadius: 20,
                  offset: const Offset(0, -4)),
            ],
          ),
          child: Row(children: [
            Container(
              width: 44, height: 44,
              decoration: BoxDecoration(
                color: AppColors.primary.withOpacity(0.12), shape: BoxShape.circle),
              child: Center(
                child: Text('${stop.sequence}',
                    style: const TextStyle(
                        fontWeight: FontWeight.w800,
                        color: AppColors.primary,
                        fontSize: 18)),
              ),
            ),
            const SizedBox(width: 12),
            Expanded(
              child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                Text(stop.customerName,
                    style: const TextStyle(
                        fontWeight: FontWeight.w700, fontSize: 14, color: Color(0xFF1E293B)),
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis),
                const SizedBox(height: 2),
                Text('${stop.distanceKm.toStringAsFixed(2)} km · ${stop.status}',
                    style: const TextStyle(fontSize: 11.5, color: Color(0xFF64748B))),
              ]),
            ),
            const SizedBox(width: 8),
            GestureDetector(
              onTap: navigating ? null : onDirections,
              child: Container(
                padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
                decoration: BoxDecoration(
                  gradient: navigating
                      ? const LinearGradient(colors: [Color(0xFF94A3B8), Color(0xFF64748B)])
                      : const LinearGradient(
                          colors: [Color(0xFFC8102E), Color(0xFF9B0B22)],
                          begin: Alignment.topLeft,
                          end: Alignment.bottomRight,
                        ),
                  borderRadius: BorderRadius.circular(10),
                ),
                child: navigating
                    ? const SizedBox(
                        width: 40,
                        child: Row(mainAxisSize: MainAxisSize.min, children: [
                          SizedBox(
                              width: 14, height: 14,
                              child: CircularProgressIndicator(
                                  strokeWidth: 2, color: Colors.white)),
                          SizedBox(width: 6),
                          Text('…', style: TextStyle(color: Colors.white, fontSize: 12)),
                        ]),
                      )
                    : const Row(mainAxisSize: MainAxisSize.min, children: [
                        Icon(Icons.navigation_rounded, color: Colors.white, size: 15),
                        SizedBox(width: 5),
                        Text('Go',
                            style: TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.w700,
                                fontSize: 13)),
                      ]),
              ),
            ),
          ]),
        ),
      ),
    );
  }
}

// ── ETA tile ──────────────────────────────────────────────────────────────────
class _ETATile extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final Color color;

  const _ETATile(
      {required this.icon, required this.label, required this.value, required this.color});

  @override
  Widget build(BuildContext context) => Container(
        padding: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: color.withOpacity(0.08),
          borderRadius: BorderRadius.circular(16),
          border: Border.all(color: color.withOpacity(0.2)),
        ),
        child: Column(children: [
          Icon(icon, color: color, size: 28),
          const SizedBox(height: 8),
          Text(value,
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w800, color: color),
              textAlign: TextAlign.center),
          const SizedBox(height: 2),
          Text(label,
              style: TextStyle(
                  fontSize: 11, color: color.withOpacity(0.7), fontWeight: FontWeight.w500)),
        ]),
      );
}

// ── Error view ────────────────────────────────────────────────────────────────
class _ErrorView extends StatelessWidget {
  final String message;
  final VoidCallback onRetry;

  const _ErrorView({required this.message, required this.onRetry});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(32),
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          const Icon(Icons.map_outlined, color: Colors.white30, size: 64),
          const SizedBox(height: 16),
          const Text('Could not load route',
              style: TextStyle(color: Colors.white70, fontSize: 16)),
          const SizedBox(height: 8),
          Text(message,
              style: const TextStyle(color: Colors.white38, fontSize: 12),
              textAlign: TextAlign.center),
          const SizedBox(height: 24),
          ElevatedButton.icon(
            onPressed: onRetry,
            icon: const Icon(Icons.refresh_rounded),
            label: const Text('Retry'),
            style: ElevatedButton.styleFrom(backgroundColor: AppColors.primary),
          ),
        ]),
      ),
    );
  }
}
