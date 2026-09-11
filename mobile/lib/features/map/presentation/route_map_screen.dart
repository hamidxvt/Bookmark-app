import 'dart:async';
import 'dart:math' as math;
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:geolocator/geolocator.dart';
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
    double toDouble(dynamic v) => v is num ? v.toDouble() : double.tryParse('$v') ?? 0;
    return RouteStop(
      visitId: j['visitId'] ?? 0,
      sequence: j['sequence'] ?? 0,
      customerName: c['name'] ?? j['customerName'] ?? 'Unknown',
      lat: toDouble(c['latitude'] ?? j['lat']),
      lng: toDouble(c['longitude'] ?? j['lng']),
      distanceKm: toDouble(j['distanceKm']),
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
  Set<Polyline> _navPolylines = {};
  Set<Polyline> _overviewPolylines = {};
  bool _navigating = false;
  bool _mapFitted = false;

  // ── Follow-me navigation state ────────────────────────────────────────────
  RouteStop? _navDest;              // active destination (null = not navigating)
  StreamSubscription<Position>? _navPosSub;
  LatLng? _navMyPos;
  double? _navBearing;              // degrees; camera + marker rotation
  double _navSpeedMps = 0;
  double _navDistanceM = 0;         // straight-line remaining, metres
  DateTime? _navStartedAt;
  int _navInitialDurationSec = 0;   // from Directions API, used for ETA decrement
  int _navRemainingSec = 0;
  bool _hasArrived = false;

  bool get _isFollowing => _navDest != null;

  Set<Polyline> get _allPolylines =>
      _navPolylines.isNotEmpty ? _navPolylines : _overviewPolylines;

  @override
  void dispose() {
    _navPosSub?.cancel();
    super.dispose();
  }

  /// Great-circle distance in metres between two points.
  double _haversineM(LatLng a, LatLng b) {
    const r = 6371000.0;
    final dLat = (b.latitude - a.latitude) * math.pi / 180;
    final dLng = (b.longitude - a.longitude) * math.pi / 180;
    final la1 = a.latitude * math.pi / 180;
    final la2 = b.latitude * math.pi / 180;
    final h = math.sin(dLat / 2) * math.sin(dLat / 2) +
        math.cos(la1) * math.cos(la2) * math.sin(dLng / 2) * math.sin(dLng / 2);
    return 2 * r * math.asin(math.sqrt(h));
  }

  /// Initial bearing (degrees, 0-360) from a -> b.
  double _bearingDeg(LatLng a, LatLng b) {
    final la1 = a.latitude * math.pi / 180;
    final la2 = b.latitude * math.pi / 180;
    final dLng = (b.longitude - a.longitude) * math.pi / 180;
    final y = math.sin(dLng) * math.cos(la2);
    final x =
        math.cos(la1) * math.sin(la2) - math.sin(la1) * math.cos(la2) * math.cos(dLng);
    return (math.atan2(y, x) * 180 / math.pi + 360) % 360;
  }

  Future<void> _startFollowMe(RouteStop dest, DirectionsResult dir) async {
    await _navPosSub?.cancel();
    _hasArrived = false;
    _navDest = dest;
    _navInitialDurationSec = dir.durationSec;
    _navRemainingSec = dir.durationSec;
    _navStartedAt = DateTime.now();

    // Kick the camera into "driving view" immediately, before the first
    // stream event arrives, so it feels responsive.
    final gps = ref.read(gpsServiceProvider);
    final first = await gps.getCurrentPosition();
    if (first != null && _mapController != null) {
      final start = LatLng(first.latitude, first.longitude);
      final destLL = LatLng(dest.lat, dest.lng);
      final bearing =
          first.heading >= 0 ? first.heading : _bearingDeg(start, destLL);
      await _mapController!.animateCamera(
        CameraUpdate.newCameraPosition(
          CameraPosition(target: start, zoom: 17.5, tilt: 55, bearing: bearing),
        ),
      );
    }

    _navPosSub = Geolocator.getPositionStream(
      locationSettings: const LocationSettings(
        accuracy: LocationAccuracy.bestForNavigation,
        distanceFilter: 5,   // metres — smooth but not jittery
      ),
    ).listen(
      _onNavPosition,
      onError: (Object e) => debugPrint('[nav] position stream error: $e'),
      cancelOnError: false,
    );

    if (mounted) setState(() {});
  }

  void _onNavPosition(Position pos) {
    final dest = _navDest;
    if (dest == null || !mounted) return;

    final now = LatLng(pos.latitude, pos.longitude);
    final destLL = LatLng(dest.lat, dest.lng);
    final distM = _haversineM(now, destLL);
    // Prefer device heading (accurate while moving); fall back to bearing
    // toward the destination when the device is stationary/heading unknown.
    final bearing = (pos.heading >= 0 && pos.speed > 0.5)
        ? pos.heading
        : _bearingDeg(now, destLL);

    // ETA decrement: keep original ETA linear vs remaining distance. Prefer
    // measured speed when it's meaningful (> ~5 km/h).
    int remaining = _navRemainingSec;
    if (pos.speed > 1.4) {
      remaining = (distM / pos.speed).round();
    } else if (_navStartedAt != null && _navInitialDurationSec > 0) {
      final elapsed = DateTime.now().difference(_navStartedAt!).inSeconds;
      remaining = math.max(0, _navInitialDurationSec - elapsed);
    }

    setState(() {
      _navMyPos = now;
      _navBearing = bearing;
      _navSpeedMps = pos.speed >= 0 ? pos.speed : 0;
      _navDistanceM = distM;
      _navRemainingSec = remaining;
    });

    _mapController?.animateCamera(
      CameraUpdate.newCameraPosition(
        CameraPosition(target: now, zoom: 17.5, tilt: 55, bearing: bearing),
      ),
    );

    // Arrival: 50 m is enough for a check-in from the customer entrance.
    if (!_hasArrived && distM < 50) {
      _hasArrived = true;
      _showSnack('Arrived at ${dest.customerName}');
      _stopFollowMe(fitBack: false);
    }
  }

  Future<void> _stopFollowMe({bool fitBack = true}) async {
    await _navPosSub?.cancel();
    _navPosSub = null;
    _navDest = null;
    _navMyPos = null;
    _navBearing = null;
    _navSpeedMps = 0;
    _navDistanceM = 0;
    _navRemainingSec = 0;

    if (fitBack && _mapController != null) {
      // Level the camera and go back to route overview.
      final routeAsync = ref.read(routeProvider);
      routeAsync.whenData((stops) {
        final valid = stops.where((s) => s.lat != 0 && s.lng != 0).toList();
        _fitMapToStops(valid, null);
      });
    }
    if (mounted) setState(() {});
  }

  void _buildOverviewPolyline(List<RouteStop> stops, LatLng? myPos) {
    final valid = stops.where((s) => s.lat != 0 && s.lng != 0).toList();
    if (valid.length < 2 && myPos == null) {
      _overviewPolylines = {};
      return;
    }
    final points = <LatLng>[
      if (myPos != null) myPos,
      ...valid.map((s) => LatLng(s.lat, s.lng)),
    ];
    if (points.length < 2) {
      _overviewPolylines = {};
      return;
    }
    _overviewPolylines = {
      Polyline(
        polylineId: const PolylineId('route_overview'),
        points: points,
        color: AppColors.primary.withOpacity(0.85),
        width: 4,
        geodesic: true,
        startCap: Cap.roundCap,
        endCap: Cap.roundCap,
        jointType: JointType.round,
      ),
    };
  }

  Future<void> _fitMapToStops(List<RouteStop> stops, LatLng? myPos) async {
    if (_mapController == null) return;
    final valid = stops.where((s) => s.lat != 0 && s.lng != 0).toList();
    if (valid.isEmpty && myPos == null) return;

    if (valid.length == 1 && myPos == null) {
      await _mapController!.animateCamera(
        CameraUpdate.newLatLngZoom(LatLng(valid[0].lat, valid[0].lng), 14),
      );
      return;
    }

    double minLat = myPos?.latitude ?? valid.first.lat;
    double maxLat = minLat;
    double minLng = myPos?.longitude ?? valid.first.lng;
    double maxLng = minLng;

    for (final p in [
      if (myPos != null) myPos,
      ...valid.map((s) => LatLng(s.lat, s.lng)),
    ]) {
      if (p.latitude < minLat) minLat = p.latitude;
      if (p.latitude > maxLat) maxLat = p.latitude;
      if (p.longitude < minLng) minLng = p.longitude;
      if (p.longitude > maxLng) maxLng = p.longitude;
    }

    await _mapController!.animateCamera(
      CameraUpdate.newLatLngBounds(
        LatLngBounds(
          southwest: LatLng(minLat, minLng),
          northeast: LatLng(maxLat, maxLng),
        ),
        100,
      ),
    );
  }

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
          _navPolylines = {
            Polyline(
              polylineId: const PolylineId('route'),
              points: directions.polylinePoints,
              color: AppColors.primary,
              width: 5,
              geodesic: true,
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
    // Capture the outer state's context so the pushed sheet route can
    // still reach _startFollowMe after it's closed.
    final rootContext = context;
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
              SizedBox(width: 10),
              Expanded(
                  child: Text('Route calculated · ETA updated',
                      style: TextStyle(fontSize: 12, color: Color(0xFF166534),
                          fontWeight: FontWeight.w600))),
            ]),
          ),
          const SizedBox(height: 16),

          // CTA — closes the sheet AND kicks off in-app follow-me navigation.
          SizedBox(
            width: double.infinity, height: 52,
            child: ElevatedButton.icon(
              onPressed: () {
                Navigator.pop(context);
                // Fire after the sheet's pop animation so the camera
                // animation doesn't get eaten by Navigator.
                Future.delayed(const Duration(milliseconds: 160), () {
                  if (!mounted) return;
                  _startFollowMe(stop, dir);
                  ScaffoldMessenger.of(rootContext).showSnackBar(
                    const SnackBar(
                      content: Text('Navigation started'),
                      behavior: SnackBarBehavior.floating,
                      duration: Duration(seconds: 1),
                    ),
                  );
                });
              },
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

  Set<Marker> _buildMarkers(List<RouteStop> stops, int selected, LatLng? myPos, double? heading) {
    final ms = <Marker>{};
    // While navigating, prefer the live stream position/bearing so the
    // "me" arrow tracks in real time instead of using the stale one-shot
    // fix _MapBody grabbed on load.
    final effMyPos = _navMyPos ?? myPos;
    final effHeading = _navBearing ?? heading;
    if (effMyPos != null) {
      ms.add(Marker(
        markerId: const MarkerId('me'),
        position: effMyPos,
        icon: BitmapDescriptor.defaultMarkerWithHue(
          _isFollowing ? BitmapDescriptor.hueAzure : BitmapDescriptor.hueBlue,
        ),
        infoWindow: const InfoWindow(title: 'Your Location'),
        rotation: effHeading ?? 0,
        zIndex: 10,
        anchor: const Offset(0.5, 0.5),
        flat: _isFollowing, // "car on the road" look while navigating
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
        data: (stops) {
          final valid = stops.where((s) => s.lat != 0 && s.lng != 0).toList();
          return Stack(children: [
            _MapBody(
              stops: valid,
              selectedStop: _selectedStop,
              polylines: _allPolylines,
              navigating: _navigating,
              hideBottomPanel: _isFollowing,   // give the nav HUD full attention
              onSelectStop: (i) {
                setState(() => _selectedStop = i);
                if (i < valid.length) {
                  _mapController?.animateCamera(
                    CameraUpdate.newLatLngZoom(LatLng(valid[i].lat, valid[i].lng), 15),
                  );
                }
              },
              onMapCreated: (ctrl) {
                _mapController = ctrl;
                _mapFitted = false;
              },
              onReady: (myPos) {
                if (_mapFitted) return;
                _buildOverviewPolyline(valid, myPos);
                _mapFitted = true;
                _fitMapToStops(valid, myPos);
                if (mounted) setState(() {});
              },
              buildMarkers: (stops, myPos, heading) => _buildMarkers(stops, _selectedStop, myPos, heading),
              onNavigate: _navigate,
            ),
            if (_isFollowing)
              _NavigationHud(
                destinationName: _navDest!.customerName,
                distanceM: _navDistanceM,
                etaSec: _navRemainingSec,
                speedKmh: _navSpeedMps * 3.6,
                onRecenter: () {
                  if (_navMyPos == null) return;
                  _mapController?.animateCamera(
                    CameraUpdate.newCameraPosition(
                      CameraPosition(
                        target: _navMyPos!,
                        zoom: 17.5,
                        tilt: 55,
                        bearing: _navBearing ?? 0,
                      ),
                    ),
                  );
                },
                onExit: () => _stopFollowMe(),
              ),
          ]);
        },
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
  final bool hideBottomPanel;
  final ValueChanged<int> onSelectStop;
  final void Function(GoogleMapController) onMapCreated;
  final void Function(LatLng? myPos) onReady;
  final Set<Marker> Function(List<RouteStop>, LatLng?, double?) buildMarkers;
  final Future<void> Function(RouteStop) onNavigate;

  const _MapBody({
    required this.stops,
    required this.selectedStop,
    required this.polylines,
    required this.navigating,
    this.hideBottomPanel = false,
    required this.onSelectStop,
    required this.onMapCreated,
    required this.onReady,
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
    if (!mounted) return;
    setState(() {
      _myPos = pos != null ? LatLng(pos.latitude, pos.longitude) : null;
      _myHeading = pos != null && pos.heading >= 0 ? pos.heading : null;
    });
    widget.onReady(_myPos);
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
      Positioned.fill(
        child: GoogleMap(
          onMapCreated: (ctrl) {
            widget.onMapCreated(ctrl);
            widget.onReady(_myPos);
          },
          initialCameraPosition: initialCamera,
          markers: widget.buildMarkers(valid, _myPos, _myHeading),
          polylines: widget.polylines,
          myLocationEnabled: true,
          myLocationButtonEnabled: false,
          zoomControlsEnabled: false,
          mapToolbarEnabled: false,
          compassEnabled: true,
          trafficEnabled: false,
        ),
      ),

      // Top bar (hidden during follow-me navigation to give the HUD room)
      if (!widget.hideBottomPanel) Positioned(
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

      // Bottom panel (also hidden during follow-me navigation)
      if (!widget.hideBottomPanel) Positioned(
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

// ── Follow-me navigation HUD ─────────────────────────────────────────────────
// Top: destination + big ETA + distance. Bottom: speed + Recenter + Exit.
class _NavigationHud extends StatelessWidget {
  final String destinationName;
  final double distanceM;
  final int etaSec;
  final double speedKmh;
  final VoidCallback onRecenter;
  final VoidCallback onExit;

  const _NavigationHud({
    required this.destinationName,
    required this.distanceM,
    required this.etaSec,
    required this.speedKmh,
    required this.onRecenter,
    required this.onExit,
  });

  String get _distanceText {
    if (distanceM >= 1000) return '${(distanceM / 1000).toStringAsFixed(1)} km';
    return '${distanceM.round()} m';
  }

  String get _etaText {
    final m = (etaSec / 60).round();
    if (m < 1) return '<1 min';
    if (m >= 60) {
      final h = m ~/ 60;
      final rem = m % 60;
      return rem == 0 ? '$h hr' : '$h hr $rem min';
    }
    return '$m min';
  }

  String get _arrivalTime {
    final arr = DateTime.now().add(Duration(seconds: etaSec));
    final h = arr.hour % 12 == 0 ? 12 : arr.hour % 12;
    final m = arr.minute.toString().padLeft(2, '0');
    final ampm = arr.hour < 12 ? 'AM' : 'PM';
    return '$h:$m $ampm';
  }

  @override
  Widget build(BuildContext context) {
    return Stack(children: [
      // Top HUD — destination + ETA
      Positioned(
        top: 0, left: 0, right: 0,
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.fromLTRB(12, 12, 12, 0),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(20),
              child: BackdropFilter(
                filter: ImageFilter.blur(sigmaX: 18, sigmaY: 18),
                child: Container(
                  padding: const EdgeInsets.fromLTRB(16, 14, 16, 14),
                  decoration: BoxDecoration(
                    color: Colors.black.withOpacity(0.72),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  child: Row(children: [
                    Container(
                      width: 44, height: 44,
                      decoration: BoxDecoration(
                        color: AppColors.primary,
                        borderRadius: BorderRadius.circular(14),
                      ),
                      child: const Icon(Icons.navigation_rounded,
                          color: Colors.white, size: 22),
                    ),
                    const SizedBox(width: 12),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          const Text('NAVIGATING TO',
                              style: TextStyle(
                                  color: Colors.white54,
                                  fontSize: 10,
                                  fontWeight: FontWeight.w700,
                                  letterSpacing: 1.2)),
                          const SizedBox(height: 2),
                          Text(destinationName,
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis,
                              style: const TextStyle(
                                  color: Colors.white,
                                  fontSize: 15,
                                  fontWeight: FontWeight.w800)),
                        ],
                      ),
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: [
                        Text(_etaText,
                            style: const TextStyle(
                                color: Colors.white,
                                fontSize: 18,
                                fontWeight: FontWeight.w900)),
                        Text(_distanceText,
                            style: const TextStyle(
                                color: Colors.white70,
                                fontSize: 11,
                                fontWeight: FontWeight.w600)),
                      ],
                    ),
                  ]),
                ),
              ),
            ),
          ),
        ),
      ),

      // Bottom HUD — speed + arrival + controls
      Positioned(
        bottom: 0, left: 0, right: 0,
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.fromLTRB(12, 0, 12, 16),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(24),
              child: BackdropFilter(
                filter: ImageFilter.blur(sigmaX: 18, sigmaY: 18),
                child: Container(
                  padding: const EdgeInsets.fromLTRB(14, 14, 14, 14),
                  decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.96),
                    borderRadius: BorderRadius.circular(24),
                  ),
                  child: Column(mainAxisSize: MainAxisSize.min, children: [
                    Row(children: [
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text('${speedKmh.round()}',
                                style: const TextStyle(
                                    fontSize: 26,
                                    fontWeight: FontWeight.w900,
                                    color: Color(0xFF0F172A),
                                    height: 1)),
                            const Text('km/h',
                                style: TextStyle(
                                    fontSize: 10,
                                    fontWeight: FontWeight.w700,
                                    color: Color(0xFF64748B))),
                          ],
                        ),
                      ),
                      Container(width: 1, height: 32, color: const Color(0xFFE2E8F0)),
                      const SizedBox(width: 12),
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(_arrivalTime,
                                style: const TextStyle(
                                    fontSize: 18,
                                    fontWeight: FontWeight.w900,
                                    color: Color(0xFF0F172A),
                                    height: 1)),
                            const Text('arrival',
                                style: TextStyle(
                                    fontSize: 10,
                                    fontWeight: FontWeight.w700,
                                    color: Color(0xFF64748B))),
                          ],
                        ),
                      ),
                      IconButton.filled(
                        onPressed: onRecenter,
                        icon: const Icon(Icons.my_location_rounded, size: 20),
                        style: IconButton.styleFrom(
                          backgroundColor: const Color(0xFFF1F5F9),
                          foregroundColor: const Color(0xFF0F172A),
                        ),
                      ),
                    ]),
                    const SizedBox(height: 10),
                    SizedBox(
                      width: double.infinity, height: 46,
                      child: ElevatedButton.icon(
                        onPressed: onExit,
                        icon: const Icon(Icons.close_rounded, size: 18),
                        label: const Text('Exit navigation',
                            style: TextStyle(
                                fontWeight: FontWeight.w800, fontSize: 14)),
                        style: ElevatedButton.styleFrom(
                          backgroundColor: const Color(0xFF0F172A),
                          foregroundColor: Colors.white,
                          elevation: 0,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(14)),
                        ),
                      ),
                    ),
                  ]),
                ),
              ),
            ),
          ),
        ),
      ),
    ]);
  }
}
