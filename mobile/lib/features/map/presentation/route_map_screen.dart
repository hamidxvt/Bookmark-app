import 'dart:ui';
import 'dart:math' as math;
import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:latlong2/latlong.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:dio/dio.dart';
import 'package:go_router/go_router.dart';

import '../../../core/theme/app_theme.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';
import '../../../core/services/gps_service.dart';

// ── Data model for an optimized visit stop ───────────────────────────────────
class RouteStop {
  final int visitId;
  final int sequence;
  final String customerName;
  final double lat;
  final double lng;
  final double distanceKm;
  final String googleMapsUrl;
  final String status;

  const RouteStop({
    required this.visitId,
    required this.sequence,
    required this.customerName,
    required this.lat,
    required this.lng,
    required this.distanceKm,
    required this.googleMapsUrl,
    required this.status,
  });

  factory RouteStop.fromJson(Map<String, dynamic> j) {
    final customer = j['customer'] as Map<String, dynamic>? ?? {};
    return RouteStop(
      visitId: j['visitId'] ?? 0,
      sequence: j['sequence'] ?? 0,
      customerName: customer['name'] ?? j['customerName'] ?? 'Unknown',
      lat: (customer['latitude'] ?? j['lat'] ?? 0).toDouble(),
      lng: (customer['longitude'] ?? j['lng'] ?? 0).toDouble(),
      distanceKm: (j['distanceKm'] ?? 0).toDouble(),
      googleMapsUrl: j['googleMapsUrl'] ?? '',
      status: j['status'] ?? 'PENDING',
    );
  }
}

// ── Provider ──────────────────────────────────────────────────────────────────
final routeProvider = FutureProvider.autoDispose<List<RouteStop>>((ref) async {
  final dio = ref.watch(dioClientProvider);
  final gps = ref.watch(gpsServiceProvider);

  // Get current position for route start point
  final pos = await gps.getCurrentPosition();
  final latParam = pos?.latitude.toString() ?? '';
  final lngParam = pos?.longitude.toString() ?? '';

  final res = await dio.get(
    '/route?${[
      if (latParam.isNotEmpty) 'lat=$latParam',
      if (lngParam.isNotEmpty) 'lng=$lngParam',
    ].join('&')}',
  );

  // API returns { success, data: { totalStops, stops: [...] } }
  final responseData = res.data['data'] as Map<String, dynamic>? ?? res.data as Map<String, dynamic>? ?? {};
  final stops = (responseData['stops'] as List? ?? [])
      .map((e) => RouteStop.fromJson(e as Map<String, dynamic>))
      .where((s) => s.lat != 0 && s.lng != 0)
      .toList();
  return stops;
});

// ── Screen ────────────────────────────────────────────────────────────────────
class RouteMapScreen extends ConsumerStatefulWidget {
  const RouteMapScreen({super.key});

  @override
  ConsumerState<RouteMapScreen> createState() => _RouteMapScreenState();
}

class _RouteMapScreenState extends ConsumerState<RouteMapScreen> {
  final MapController _mapController = MapController();
  int _selectedStop = 0;

  // car: 40 km/h city average, walk: 5 km/h
  ({int car, int walk, double distKm}) _etaInfo(double fromLat, double fromLng, double toLat, double toLng) {
    const double earthR = 6371;
    final dLat = _toRadian(toLat - fromLat);
    final dLng = _toRadian(toLng - fromLng);
    final a = math.sin(dLat / 2) * math.sin(dLat / 2) +
        math.cos(_toRadian(fromLat)) * math.cos(_toRadian(toLat)) *
            math.sin(dLng / 2) * math.sin(dLng / 2);
    final distKm = earthR * 2 * math.asin(math.sqrt(a));
    final carMin = (distKm / 40.0 * 60).ceil();
    final walkMin = (distKm / 5.0 * 60).ceil();
    return (car: carMin, walk: walkMin, distKm: distKm);
  }

  Future<void> _sendETA(RouteStop stop) async {
    try {
      final gps = ref.read(gpsServiceProvider);
      final currentPos = await gps.getCurrentPosition();

      if (currentPos != null) {
        final info = _etaInfo(currentPos.latitude, currentPos.longitude, stop.lat, stop.lng);

        // Send ETA to admin dashboard
        final dio = ref.read(dioClientProvider);
        await dio.post(
          '/visits/${stop.visitId}/eta',
          data: {
            'visitId': stop.visitId,
            'customerName': stop.customerName,
            'eta_minutes': info.car,
            'eta_walk_minutes': info.walk,
            'eta_timestamp': DateTime.now().add(Duration(minutes: info.car)).toIso8601String(),
            'lat': currentPos.latitude,
            'lng': currentPos.longitude,
            'destination_lat': stop.lat,
            'destination_lng': stop.lng,
            'distance_km': info.distKm,
          },
        ).catchError((e) => null);

        // Check if officer is running late — notify admin via mobile endpoint
        dio.post(
          '/mobile/notify-late',
          data: {
            'visitId': stop.visitId,
            'etaMinutes': info.car,
            'customerName': stop.customerName,
          },
        ).catchError((e) => null);

        // Show ETA bottom sheet before opening maps
        if (mounted) {
          await _showETASheet(stop, info.car, info.walk, info.distKm);
        }
      }
    } catch (e) {
      // Still open maps even if ETA send fails
    }

    _openMaps(stop);
  }

  Future<void> _showETASheet(RouteStop stop, int carMin, int walkMin, double distKm) async {
    await showModalBottomSheet(
      context: context,
      shape: const RoundedRectangleBorder(borderRadius: BorderRadius.vertical(top: Radius.circular(24))),
      backgroundColor: Colors.white,
      builder: (_) => Padding(
        padding: const EdgeInsets.all(24),
        child: Column(mainAxisSize: MainAxisSize.min, children: [
          Container(width: 40, height: 4, decoration: BoxDecoration(color: Colors.grey.shade300, borderRadius: BorderRadius.circular(4))),
          const SizedBox(height: 20),
          Text(stop.customerName, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.w800)),
          const SizedBox(height: 4),
          Text('${distKm.toStringAsFixed(2)} km away', style: TextStyle(color: Colors.grey.shade500, fontSize: 13)),
          const SizedBox(height: 24),
          Row(children: [
            Expanded(child: _ETATile(icon: Icons.directions_car_rounded, label: 'By Car', minutes: carMin, color: AppColors.primary)),
            const SizedBox(width: 12),
            Expanded(child: _ETATile(icon: Icons.directions_walk_rounded, label: 'Walking', minutes: walkMin, color: Colors.blue.shade700)),
          ]),
          const SizedBox(height: 20),
          Container(
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(color: Colors.amber.shade50, borderRadius: BorderRadius.circular(12), border: Border.all(color: Colors.amber.shade200)),
            child: Row(children: [
              Icon(Icons.info_outline_rounded, color: Colors.amber.shade700, size: 16),
              const SizedBox(width: 8),
              Expanded(
                child: Text('ETA sent to admin dashboard. Opening navigation…',
                    style: TextStyle(fontSize: 12, color: Colors.amber.shade800)),
              ),
            ]),
          ),
          const SizedBox(height: 20),
          SizedBox(
            width: double.infinity, height: 50,
            child: ElevatedButton.icon(
              onPressed: () => Navigator.pop(context),
              icon: const Icon(Icons.navigation_rounded),
              label: const Text('Start Navigation', style: TextStyle(fontWeight: FontWeight.w700)),
              style: ElevatedButton.styleFrom(
                backgroundColor: AppColors.primary, foregroundColor: Colors.white,
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
              ),
            ),
          ),
        ]),
      ),
    );
  }

  Future<void> _openMaps(RouteStop stop) async {
    // Try Google Maps navigation intent (turn-by-turn guidance)
    final gmapsNavUri = Uri.parse(
      'google.navigation:q=${stop.lat},${stop.lng}&mode=d',
    );
    if (await canLaunchUrl(gmapsNavUri)) {
      await launchUrl(gmapsNavUri, mode: LaunchMode.externalApplication);
      return;
    }
    // Fallback: open Google Maps with destination
    final fallbackUri = Uri.parse(
      'https://www.google.com/maps/dir/?api=1&destination=${stop.lat},${stop.lng}&travelmode=driving',
    );
    if (await canLaunchUrl(fallbackUri)) {
      await launchUrl(fallbackUri, mode: LaunchMode.externalApplication);
    }
  }

  @override
  Widget build(BuildContext context) {
    final routeAsync = ref.watch(routeProvider);

    return Scaffold(
      backgroundColor: const Color(0xFF1A0A0A),
      body: routeAsync.when(
        loading: () => const Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              CircularProgressIndicator(color: AppColors.primary),
              SizedBox(height: 16),
              Text('Calculating optimal route...', style: TextStyle(color: Colors.white70)),
            ],
          ),
        ),
        error: (err, _) => _ErrorView(onRetry: () => ref.invalidate(routeProvider)),
        data: (stops) => _MapView(
          stops: stops,
          selectedStop: _selectedStop,
          onSelectStop: (i) => setState(() => _selectedStop = i),
          mapController: _mapController,
          onNavigate: (stop) => _sendETA(stop),
        ),
      ),
    );
  }
}

class _MapView extends StatelessWidget {
  final List<RouteStop> stops;
  final int selectedStop;
  final ValueChanged<int> onSelectStop;
  final MapController mapController;
  final Function(RouteStop) onNavigate;

  const _MapView({
    required this.stops,
    required this.selectedStop,
    required this.onSelectStop,
    required this.mapController,
    required this.onNavigate,
  });

  @override
  Widget build(BuildContext context) {
    final validStops = stops.where((s) => s.lat != 0 && s.lng != 0).toList();
    final center = validStops.isNotEmpty
        ? LatLng(validStops[0].lat, validStops[0].lng)
        : const LatLng(34.3512, 72.0189); // Default Thana Malakand

    return Stack(
      children: [
        // ── Full-screen Map ───────────────────────────────────────────────
        FlutterMap(
          mapController: mapController,
          options: MapOptions(
            initialCenter: center,
            initialZoom: 13,
          ),
          children: [
            // OSM tiles (free)
            TileLayer(
              urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
              userAgentPackageName: 'com.bookmark.sfa',
            ),

            // Route polyline
            if (validStops.length > 1)
              PolylineLayer<Object>(
                polylines: [
                  Polyline(
                    points: validStops.map((s) => LatLng(s.lat, s.lng)).toList(),
                    strokeWidth: 4,
                    color: AppColors.primary,
                  ),
                ],
              ),

            // Visit markers
            MarkerLayer(
              markers: validStops.asMap().entries.map((entry) {
                final i = entry.key;
                final stop = entry.value;
                final isSelected = i == selectedStop;
                final isDone = stop.status == 'COMPLETED';

                return Marker(
                  point: LatLng(stop.lat, stop.lng),
                  width: isSelected ? 56 : 44,
                  height: isSelected ? 56 : 44,
                  child: GestureDetector(
                    onTap: () {
                      onSelectStop(i);
                      mapController.move(LatLng(stop.lat, stop.lng), 15);
                    },
                    child: AnimatedContainer(
                      duration: const Duration(milliseconds: 200),
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: isDone
                            ? AppColors.success
                            : isSelected
                                ? AppColors.primary
                                : AppColors.primaryContainer,
                        border: Border.all(
                          color: Colors.white,
                          width: isSelected ? 3 : 2,
                        ),
                        boxShadow: [
                          BoxShadow(
                            color: (isSelected
                                    ? AppColors.primary
                                    : Colors.black)
                                .withOpacity(0.4),
                            blurRadius: isSelected ? 16 : 6,
                            spreadRadius: isSelected ? 2 : 0,
                          ),
                        ],
                      ),
                      child: Center(
                        child: isDone
                            ? const Icon(Icons.check, color: Colors.white, size: 18)
                            : Text(
                                '${stop.sequence}',
                                style: TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.w800,
                                  fontSize: isSelected ? 16 : 13,
                                ),
                              ),
                      ),
                    ),
                  ),
                );
              }).toList(),
            ),
          ],
        ),

        // ── Top bar with back ─────────────────────────────────────────────
        SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(12),
            child: ClipRRect(
              borderRadius: BorderRadius.circular(14),
              child: BackdropFilter(
                filter: ImageFilter.blur(sigmaX: 20, sigmaY: 20),
                child: Container(
                  padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 6),
                  decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.92),
                    borderRadius: BorderRadius.circular(14),
                    border: Border.all(color: Colors.white.withOpacity(0.5)),
                  ),
                  child: Row(
                    children: [
                      IconButton(
                        onPressed: () => context.pop(),
                        icon: const Icon(Icons.arrow_back_ios_new_rounded, size: 18),
                        visualDensity: VisualDensity.compact,
                      ),
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            const Text('Optimized Route',
                                style: TextStyle(fontWeight: FontWeight.w700, fontSize: 14, color: AppColors.primary)),
                            Text('${validStops.length} visits · Today',
                                style: const TextStyle(fontSize: 11, color: Color(0xFF64748B))),
                          ],
                        ),
                      ),
                      if (validStops.isNotEmpty)
                        Container(
                          padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
                          decoration: BoxDecoration(
                            color: AppColors.primary,
                            borderRadius: BorderRadius.circular(8),
                          ),
                          child: Text(
                            '${validStops.fold(0.0, (s, e) => s + e.distanceKm).toStringAsFixed(1)} km',
                            style: const TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.w700,
                              fontSize: 12,
                            ),
                          ),
                        ),
                    ],
                  ),
                ),
              ),
            ),
          ),
        ),

        // ── Bottom stop list ──────────────────────────────────────────────
        Positioned(
          bottom: 0,
          left: 0,
          right: 0,
          child: SafeArea(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                // Selected stop detail card
                if (validStops.isNotEmpty && selectedStop < validStops.length) ...[
                  Padding(
                    padding: const EdgeInsets.fromLTRB(12, 0, 12, 8),
                    child: _StopDetailCard(
                      stop: validStops[selectedStop],
                      onDirections: () => onNavigate(validStops[selectedStop]),
                    ),
                  ),
                ],

                // Horizontal scroll list of all stops
                SizedBox(
                  height: 80,
                  child: ListView.separated(
                    scrollDirection: Axis.horizontal,
                    padding: const EdgeInsets.fromLTRB(12, 0, 12, 12),
                    itemCount: validStops.length,
                    separatorBuilder: (_, __) => const SizedBox(width: 8),
                    itemBuilder: (_, i) {
                      final s = validStops[i];
                      final isSelected = i == selectedStop;
                      return GestureDetector(
                        onTap: () {
                          onSelectStop(i);
                          mapController.move(LatLng(s.lat, s.lng), 15);
                        },
                        child: AnimatedContainer(
                          duration: const Duration(milliseconds: 200),
                          width: 150,
                          padding: const EdgeInsets.all(10),
                          decoration: BoxDecoration(
                            color: isSelected
                                ? AppColors.primary
                                : Colors.white.withOpacity(0.92),
                            borderRadius: BorderRadius.circular(12),
                            border: Border.all(
                              color: isSelected
                                  ? Colors.transparent
                                  : Colors.white.withOpacity(0.5),
                            ),
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
                                    color: isSelected
                                        ? Colors.white.withOpacity(0.3)
                                        : AppColors.primary.withOpacity(0.1),
                                  ),
                                  child: Center(
                                    child: Text('${s.sequence}',
                                        style: TextStyle(
                                          fontSize: 10,
                                          fontWeight: FontWeight.w800,
                                          color: isSelected ? Colors.white : AppColors.primary,
                                        )),
                                  ),
                                ),
                                const SizedBox(width: 5),
                                Expanded(
                                  child: Text(
                                    s.customerName,
                                    style: TextStyle(
                                      fontSize: 11,
                                      fontWeight: FontWeight.w600,
                                      color: isSelected ? Colors.white : const Color(0xFF1E293B),
                                    ),
                                    maxLines: 1,
                                    overflow: TextOverflow.ellipsis,
                                  ),
                                ),
                              ]),
                              const SizedBox(height: 4),
                              Text(
                                '${s.distanceKm.toStringAsFixed(1)} km away',
                                style: TextStyle(
                                  fontSize: 10,
                                  color: isSelected ? Colors.white70 : const Color(0xFF94A3B8),
                                ),
                              ),
                            ],
                          ),
                        ),
                      );
                    },
                  ),
                ),
              ],
            ),
          ),
        ),
      ],
    );
  }

  double _toRadian(double degree) => degree * (math.pi / 180);
}

// ── Stop detail card (glassmorphism) ─────────────────────────────────────────
class _StopDetailCard extends StatelessWidget {
  final RouteStop stop;
  final VoidCallback onDirections;

  const _StopDetailCard({required this.stop, required this.onDirections});

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
                offset: const Offset(0, -4),
              ),
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
                      fontSize: 18,
                    )),
              ),
            ),
            const SizedBox(width: 12),
            Expanded(
              child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                Text(stop.customerName,
                    style: const TextStyle(
                      fontWeight: FontWeight.w700,
                      fontSize: 14,
                      color: Color(0xFF1E293B),
                    ),
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis),
                const SizedBox(height: 2),
                Text('${stop.distanceKm.toStringAsFixed(2)} km from previous stop',
                    style: const TextStyle(fontSize: 11.5, color: Color(0xFF64748B))),
              ]),
            ),
            const SizedBox(width: 8),
            GestureDetector(
              onTap: onDirections,
              child: Container(
                padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
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
                    Icon(Icons.navigation_rounded, color: Colors.white, size: 15),
                    SizedBox(width: 5),
                    Text('Go', style: TextStyle(color: Colors.white, fontWeight: FontWeight.w700, fontSize: 13)),
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

class _ETATile extends StatelessWidget {
  final IconData icon;
  final String label;
  final int minutes;
  final Color color;
  const _ETATile({required this.icon, required this.label, required this.minutes, required this.color});

  String _format(int m) {
    if (m < 60) return '$m min';
    final h = m ~/ 60;
    final rem = m % 60;
    return rem == 0 ? '${h}h' : '${h}h ${rem}m';
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
      Text(_format(minutes), style: TextStyle(fontSize: 20, fontWeight: FontWeight.w800, color: color)),
      const SizedBox(height: 2),
      Text(label, style: TextStyle(fontSize: 11, color: color.withOpacity(0.7), fontWeight: FontWeight.w500)),
    ]),
  );
}

class _ErrorView extends StatelessWidget {
  final VoidCallback onRetry;
  const _ErrorView({required this.onRetry});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(mainAxisSize: MainAxisSize.min, children: [
        const Icon(Icons.map_outlined, color: Colors.white30, size: 64),
        const SizedBox(height: 16),
        const Text('Could not load route', style: TextStyle(color: Colors.white70, fontSize: 16)),
        const SizedBox(height: 8),
        const Text('Make sure visits are scheduled today', style: TextStyle(color: Colors.white38, fontSize: 13)),
        const SizedBox(height: 24),
        ElevatedButton.icon(
          onPressed: onRetry,
          icon: const Icon(Icons.refresh_rounded),
          label: const Text('Retry'),
          style: ElevatedButton.styleFrom(backgroundColor: AppColors.primary),
        ),
      ]),
    );
  }
}
