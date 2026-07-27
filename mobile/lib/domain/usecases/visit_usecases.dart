import '../../domain/entities/visit.dart';
import '../repositories/visits_repository.dart';
import '../../core/utils/mock_location_guard.dart';
import '../../core/utils/geo_utils.dart';
import '../../core/constants/app_constants.dart';
import 'package:geolocator/geolocator.dart';

class GetTodayVisitsUseCase {
  final VisitsRepository _repo;
  GetTodayVisitsUseCase(this._repo);

  Future<List<Visit>> execute() => _repo.getTodayVisits();
}

class StartVisitUseCase {
  final VisitsRepository _repo;
  StartVisitUseCase(this._repo);

  Future<Visit> execute(Visit visit) async {
    final permission = await Geolocator.requestPermission();
    if (permission == LocationPermission.denied ||
        permission == LocationPermission.deniedForever) {
      throw Exception('Location permission required to start a visit.');
    }

    final position = await Geolocator.getCurrentPosition(
      locationSettings: const LocationSettings(accuracy: LocationAccuracy.high),
    );

    final isMocked = await MockLocationGuard.isMocked(position);
    if (isMocked) {
      throw const MockLocationException(
          'Mock GPS detected. Cannot start visit.');
    }

    final distance = GeoUtils.haversine(
      position.latitude, position.longitude,
      visit.locationLat, visit.locationLng,
    );

    if (distance > AppConstants.geofenceRadiusMeters) {
      throw GeofenceException(
          'You must be within ${AppConstants.geofenceRadiusMeters}m of "${visit.locationName}" to check in. '
          'You are ${distance.toStringAsFixed(0)}m away.');
    }

    return _repo.startVisit(visit.id, position.latitude, position.longitude);
  }
}

class CompleteVisitUseCase {
  final VisitsRepository _repo;
  CompleteVisitUseCase(this._repo);

  Future<void> execute({
    required int visitId,
    required String contactPerson,
    required String designation,
    required String phone,
    required String notes,
    required String visitType,
    required int sampleDistributed,
    String? followUpDate,
  }) =>
      _repo.completeVisit(
        visitId: visitId,
        contactPerson: contactPerson,
        designation: designation,
        phone: phone,
        notes: notes,
        visitType: visitType,
        sampleDistributed: sampleDistributed,
        followUpDate: followUpDate,
      );
}

class MarkMissedUseCase {
  final VisitsRepository _repo;
  MarkMissedUseCase(this._repo);

  Future<void> execute({
    required int visitId,
    required String reason,
    required String photoUrl,
  }) =>
      _repo.markMissed(visitId: visitId, reason: reason, photoUrl: photoUrl);
}
