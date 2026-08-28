import 'dart:ui';
import 'dart:math' as math;
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:url_launcher/url_launcher.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../../core/services/gps_service.dart';

// ── Data model ────────────────────────────────────────────────────────────────
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

// ── Provider ──────────────────────────────────────────────────────────────────
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

  // Haversine ETA (car: 40 km/h city, walk: 5 km/h)
  ({int car, int walk, double km}) _eta(
      double fLat, double fLng, double tLat, double tLng) {
    const r = 6371.0;
    final dLat = _rad(tLat - fLat);
    final dLng = _rad(tLng - fLng);
    final a = math.sin(dLat / 2) * math.sin(dLat / 2) +
        math.cos(_rad(fLat)) *
            math.cos(_rad(tLat)) *
            math.sin(dLng / 2) *
            math.sin(dLng / 2);
    final km = r * 2 * math.asin(math.sqrt(a));
    return (
      car: (km / 40.0 * 60).ceil(),
      walk: (km / 5.0 * 60).ceil(),
      km: km,
    );
  }

  double _rad(double d) => d * math.pi / 180;

  Future<void> _navigate(RouteStop stop) async {
    try {
      final gps = ref.read(gpsServiceProvider);
      final pos = await gps.getCurrentPosition();
      if (pos != null) {
        final info = _eta(pos.latitude, pos.longitude, stop.lat, stop.lng);
        // Send ETA to admin
        final dio = ref.read(dioClientProvider);
        dio.post('/visits/${stop.visitId}/eta', data: {
          'visitId': stop.visitId,
          'customerName': stop.customerName,
          'eta_minutes': info.car,
          'eta_walk_minutes': info.walk,
          'eta_timestamp': DateTime.now()
              .add(Duration(minutes: info.car))
              .toIso8601String(),
          'lat': pos.latitude,
          'lng': pos.longitude,
          'destination_lat': stop.lat,
          'destination_lng': stop.lng,
          'distance_km': info.km,
        }).catchError((_) => null);

        dio.post('/mobile/notify-late', data: {
          'visitId': stop.visitId,
          'etaMinutes': info.car,
          'customerName': stop.customerName,
        }).catchError((_) => null);

        if (mounted) await _showETASheet(stop, info.car, info.walk, info.km);
        await _drawRouteOnMap(stop);
      }
    } catch (_) {}

  }

  Future<void> _showETASheet(
      RouteStop stop, int car, int walk, double km) async {
    await showModalBottomSheet(
      context: context,
      shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(24))),
      backgroundColor: Colors.white,
      builder: (_) => Padding(
        padding: const EdgeInsets.all(24),
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          Container(
            width: 40,
            height: 4,
            decoration: BoxDecoration(
                color: Colors.grey.shade300,
                borderRadius: BorderRadius.circular(4)),
          ),
          const SizedBox(height: 20),
          Text(stop.customerName,
              style:
                  const TextStyle(fontSize: 18, fontWeight: FontWeight.w800)),
          const SizedBox(height: 4),
          Text('${km.toStringAsFixed(2)} km away',
              style:
                  TextStyle(color: Colors.grey.shade500, fontSize: 13)),
          const SizedBox(height: 24),
          Row(children: [
            Expanded(
                child: _ETATile(
                    icon: Icons.directions_car_rounded,
                    label: 'By Car',
                    minutes: car,
                    color: AppColors.primary)),
            const SizedBox(width: 12),
            Expanded(
                child: _ETATile(
                    icon: Icons.directions_walk_rounded,
                    label: 'Walking',
                    minutes: walk,
                    color: Colors.blue.shade700)),
          ]),
          const SizedBox(height: 16),
          Container(
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
                color: Colors.amber.shade50,
                borderRadius: BorderRadius.circular(12),
                border: Border.all(color: Colors.amber.shade200)),
            child: Row(children: [
              Icon(Icons.info_outline_rounded,
                  color: Colors.amber.shade700, size: 16),
              const SizedBox(width: 8),
              Expanded(
                  child: Text('ETA sent to admin. Opening Google Maps…',
                      style: TextStyle(
                          fontSize: 12, color: Colors.amber.shade800))),
            ]),
          ),
          const SizedBox(height: 20),
          SizedBox(
            width: double.infinity,
            height: 50,
            child: ElevatedButton.icon(
              onPressed: () => Navigator.pop(context),
              icon: const Icon(Icons.navigation_rounded),
              label: const Text('Start Navigation',
                  style: TextStyle(fontWeight: FontWeight.w700)),
              style: ElevatedButton.styleFrom(
                backgroundColor: AppColors.primary,
                foregroundColor: Colors.white,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(14)),
              ),
            ),
          ),
        ]),
      ),
    );
  }

  // Draw route on in-app map instead of opening external Maps
  Future<void> _drawRouteOnMap(RouteStop stop) async {
    if (_mapController == null) return;
    try {
      final gps = ref.read(gpsServiceProvider);
      final pos = await gps.getCurrentPosition();
      if (pos == null) return;

      // Move camera to show both origin and destination
      final bounds = LatLngBounds(
        southwest: LatLng(
          pos.latitude < stop.lat ? pos.latitude : stop.lat,
          pos.longitude < stop.lng ? pos.longitude : stop.lng,
        ),
        northeast: LatLng(
          pos.latitude > stop.lat ? pos.latitude : stop.lat,
          pos.longitude > stop.lng ? pos.longitude : stop.lng,
        ),
      );
      await _mapController!.animateCamera(
        CameraUpdate.newLatLngBounds(bounds, 80),
      );
    } catch (_) {}
  }

  Set<Marker> _buildMarkers(
      List<RouteStop> stops, int selected, LatLng? myPos) {
    final markers = <Marker>{};
    // Officer position
    if (myPos != null) {
      markers.add(Marker(
        markerId: const MarkerId('me'),
        position: myPos,
        icon: BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueAzure),
        infoWindow: const InfoWindow(title: 'You'),
      ));
    }
    // Visit stops
    for (var i = 0; i < stops.length; i++) {
      final s = stops[i];
      markers.add(Marker(
        markerId: MarkerId('stop_${s.visitId}'),
        position: LatLng(s.lat, s.lng),
        icon: i == selected
            ? BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueRed)
            : BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueOrange),
        infoWindow: InfoWindow(
          title: '${s.sequence}. ${s.customerName}',
          snippet: '${s.distanceKm.toStringAsFixed(1)} km',
        ),
        onTap: () => setState(() => _selectedStop = i),
      ));
    }
    return markers;
  }

  Polyline _buildPolyline(List<RouteStop> stops, LatLng? myPos) {
    final pts = <LatLng>[];
    if (myPos != null) pts.add(myPos);
    for (final s in stops) {
      pts.add(LatLng(s.lat, s.lng));
    }
    return Polyline(
      polylineId: const PolylineId('route'),
      points: pts,
      color: AppColors.primary,
      width: 4,
      patterns: [PatternItem.dot, PatternItem.gap(10)],
    );
  }

  @override
  Widget build(BuildContext context) {
    final routeAsync = ref.watch(routeProvider);

    return Scaffold(
      backgroundColor: const Color(0xFF1A0A0A),
      body: routeAsync.when(
        loading: () => const Center(
          child: Column(mainAxisSize: MainAxisSize.min, children: [
            CircularProgressIndicator(color: AppColors.primary),
            SizedBox(height: 16),
            Text('Calculating optimal route…',
                style: TextStyle(color: Colors.white70)),
          ]),
        ),
        error: (err, _) =>
            _ErrorView(onRetry: () => ref.invalidate(routeProvider)),
        data: (stops) => _MapBody(
          stops: stops,
          selectedStop: _selectedStop,
          onSelectStop: (i) {
            setState(() => _selectedStop = i);
            if (i < stops.length) {
              _mapController?.animateCamera(
                CameraUpdate.newLatLngZoom(
                    LatLng(stops[i].lat, stops[i].lng), 15),
              );
            }
          },
          onMapCreated: (ctrl) => _mapController = ctrl,
          buildMarkers: (stops, myPos) =>
              _buildMarkers(stops, _selectedStop, myPos),
          buildPolyline: _buildPolyline,
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
  final ValueChanged<int> onSelectStop;
  final void Function(GoogleMapController) onMapCreated;
  final Set<Marker> Function(List<RouteStop>, LatLng?) buildMarkers;
  final Polyline Function(List<RouteStop>, LatLng?) buildPolyline;
  final Future<void> Function(RouteStop) onNavigate;

  const _MapBody({
    required this.stops,
    required this.selectedStop,
    required this.onSelectStop,
    required this.onMapCreated,
    required this.buildMarkers,
    required this.buildPolyline,
    required this.onNavigate,
  });

  @override
  ConsumerState<_MapBody> createState() => _MapBodyState();
}

class _MapBodyState extends ConsumerState<_MapBody> {
  LatLng? _myPos;

  @override
  void initState() {
    super.initState();
    _fetchPosition();
  }

  Future<void> _fetchPosition() async {
    final gps = ref.read(gpsServiceProvider);
    final pos = await gps.getCurrentPosition();
    if (pos != null && mounted) {
      setState(() => _myPos = LatLng(pos.latitude, pos.longitude));
    }
  }

  @override
  Widget build(BuildContext context) {
    final stops = widget.stops;
    final valid = stops.where((s) => s.lat != 0 && s.lng != 0).toList();

    final initialCamera = _myPos != null
        ? CameraPosition(target: _myPos!, zoom: 13)
        : valid.isNotEmpty
            ? CameraPosition(
                target: LatLng(valid[0].lat, valid[0].lng), zoom: 13)
            : const CameraPosition(
                target: LatLng(34.56, 71.55), zoom: 10); // Malakand default

    if (valid.isEmpty) {
      return const _EmptyRoute();
    }

    return Stack(children: [
      // Google Map — fills the entire screen
      GoogleMap(
        onMapCreated: widget.onMapCreated,
        initialCameraPosition: initialCamera,
        markers: widget.buildMarkers(valid, _myPos),
        polylines: {widget.buildPolyline(valid, _myPos)},
        myLocationEnabled: true,
        myLocationButtonEnabled: false,
        zoomControlsEnabled: false,
        mapToolbarEnabled: false,
        compassEnabled: true,
        trafficEnabled: true,
      ),

      // Top App Bar
      Positioned(
        top: 0,
        left: 0,
        right: 0,
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.fromLTRB(12, 12, 12, 0),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(16),
              child: BackdropFilter(
                filter: ImageFilter.blur(sigmaX: 16, sigmaY: 16),
                child: Container(
                  padding: const EdgeInsets.symmetric(
                      horizontal: 16, vertical: 12),
                  decoration: BoxDecoration(
                    color: Colors.black.withOpacity(0.55),
                    borderRadius: BorderRadius.circular(16),
                  ),
                  child: Row(children: [
                    const Icon(Icons.route_rounded,
                        color: AppColors.primary, size: 22),
                    const SizedBox(width: 10),
                    Text('${valid.length} stops today',
                        style: const TextStyle(
                            color: Colors.white,
                            fontWeight: FontWeight.w700,
                            fontSize: 15)),
                    const Spacer(),
                    Container(
                      padding: const EdgeInsets.symmetric(
                          horizontal: 10, vertical: 5),
                      decoration: BoxDecoration(
                          color: AppColors.primary.withOpacity(0.2),
                          borderRadius: BorderRadius.circular(8)),
                      child: Text(
                        '${valid.fold(0.0, (s, r) => s + r.distanceKm).toStringAsFixed(1)} km',
                        style: const TextStyle(
                            color: AppColors.primary,
                            fontWeight: FontWeight.w700,
                            fontSize: 13),
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
        bottom: 0,
        left: 0,
        right: 0,
        child: SafeArea(
          child: Column(mainAxisSize: MainAxisSize.min, children: [
            // Selected stop card
            if (widget.selectedStop < valid.length)
              Padding(
                padding: const EdgeInsets.fromLTRB(12, 0, 12, 8),
                child: _StopDetailCard(
                  stop: valid[widget.selectedStop],
                  onDirections: () =>
                      widget.onNavigate(valid[widget.selectedStop]),
                ),
              ),

            // Horizontal scroll stop chips
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
                        color: sel
                            ? AppColors.primary
                            : Colors.white.withOpacity(0.92),
                        borderRadius: BorderRadius.circular(12),
                        border: Border.all(
                            color: sel
                                ? Colors.transparent
                                : Colors.white.withOpacity(0.5)),
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Row(children: [
                            Container(
                              width: 20,
                              height: 20,
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
                                        color: sel
                                            ? Colors.white
                                            : AppColors.primary)),
                              ),
                            ),
                            const SizedBox(width: 5),
                            Expanded(
                              child: Text(s.customerName,
                                  style: TextStyle(
                                      fontSize: 11,
                                      fontWeight: FontWeight.w600,
                                      color: sel
                                          ? Colors.white
                                          : const Color(0xFF1E293B)),
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis),
                            ),
                          ]),
                          const SizedBox(height: 4),
                          Text('${s.distanceKm.toStringAsFixed(1)} km',
                              style: TextStyle(
                                  fontSize: 10,
                                  color: sel
                                      ? Colors.white70
                                      : const Color(0xFF94A3B8))),
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
  final VoidCallback onDirections;

  const _StopDetailCard(
      {required this.stop, required this.onDirections});

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
              width: 44,
              height: 44,
              decoration: BoxDecoration(
                color: AppColors.primary.withOpacity(0.12),
                shape: BoxShape.circle,
              ),
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
              child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(stop.customerName,
                        style: const TextStyle(
                            fontWeight: FontWeight.w700,
                            fontSize: 14,
                            color: Color(0xFF1E293B)),
                        maxLines: 1,
                        overflow: TextOverflow.ellipsis),
                    const SizedBox(height: 2),
                    Text(
                        '${stop.distanceKm.toStringAsFixed(2)} km from previous',
                        style: const TextStyle(
                            fontSize: 11.5,
                            color: Color(0xFF64748B))),
                  ]),
            ),
            const SizedBox(width: 8),
            GestureDetector(
              onTap: onDirections,
              child: Container(
                padding: const EdgeInsets.symmetric(
                    horizontal: 14, vertical: 10),
                decoration: BoxDecoration(
                  gradient: const LinearGradient(
                    colors: [Color(0xFFC8102E), Color(0xFF9B0B22)],
                    begin: Alignment.topLeft,
                    end: Alignment.bottomRight,
                  ),
                  borderRadius: BorderRadius.circular(10),
                ),
                child: const Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Icon(Icons.navigation_rounded,
                        color: Colors.white, size: 15),
                    SizedBox(width: 5),
                    Text('Go',
                        style: TextStyle(
                            color: Colors.white,
                            fontWeight: FontWeight.w700,
                            fontSize: 13)),
                  ],
                ),
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
  final int minutes;
  final Color color;

  const _ETATile(
      {required this.icon,
      required this.label,
      required this.minutes,
      required this.color});

  String _fmt(int m) {
    if (m < 60) return '$m min';
    final h = m ~/ 60;
    final r = m % 60;
    return r == 0 ? '${h}h' : '${h}h ${r}m';
  }

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
          Text(_fmt(minutes),
              style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.w800,
                  color: color)),
          const SizedBox(height: 2),
          Text(label,
              style: TextStyle(
                  fontSize: 11,
                  color: color.withOpacity(0.7),
                  fontWeight: FontWeight.w500)),
        ]),
      );
}

// ── Error view ────────────────────────────────────────────────────────────────
class _ErrorView extends StatelessWidget {
  final VoidCallback onRetry;

  const _ErrorView({required this.onRetry});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(mainAxisSize: MainAxisSize.min, children: [
        const Icon(Icons.map_outlined, color: Colors.white30, size: 64),
        const SizedBox(height: 16),
        const Text('Could not load route',
            style: TextStyle(color: Colors.white70, fontSize: 16)),
        const SizedBox(height: 8),
        const Text('Make sure visits are scheduled for today',
            style: TextStyle(color: Colors.white38, fontSize: 13)),
        const SizedBox(height: 24),
        ElevatedButton.icon(
          onPressed: onRetry,
          icon: const Icon(Icons.refresh_rounded),
          label: const Text('Retry'),
          style:
              ElevatedButton.styleFrom(backgroundColor: AppColors.primary),
        ),
      ]),
    );
  }
}
