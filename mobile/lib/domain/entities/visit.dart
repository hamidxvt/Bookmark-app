class Visit {
  final int id;
  final int userId;
  final int locationId;
  final String locationName;
  final double locationLat;
  final double locationLng;
  final DateTime scheduledDate;
  final int dailySequence;
  final String status;
  final DateTime? arrivalTime;
  final DateTime? completionTime;
  final String? contactPerson;
  final String? designation;
  final String? phone;
  final String? notes;
  final String? visitType;
  final int sampleDistributed;
  final String? photoUrl;
  final String? missedReason;
  final int carryForwardCnt;
  final bool isAdHoc;
  final String syncStatus;

  const Visit({
    required this.id,
    required this.userId,
    required this.locationId,
    required this.locationName,
    required this.locationLat,
    required this.locationLng,
    required this.scheduledDate,
    required this.dailySequence,
    required this.status,
    this.arrivalTime,
    this.completionTime,
    this.contactPerson,
    this.designation,
    this.phone,
    this.notes,
    this.visitType,
    this.sampleDistributed = 0,
    this.photoUrl,
    this.missedReason,
    this.carryForwardCnt = 0,
    this.isAdHoc = false,
    this.syncStatus = 'synced',
  });

  bool get isCompleted => status == 'completed';
  bool get isMissed => status == 'missed';
  bool get isInProgress => status == 'in_progress';
  bool get isPlanned => status == 'planned';

  Visit copyWith({
    String? status,
    DateTime? arrivalTime,
    double? arrivalLat,
    double? arrivalLng,
    DateTime? completionTime,
    String? contactPerson,
    String? notes,
    String? syncStatus,
  }) {
    return Visit(
      id: id,
      userId: userId,
      locationId: locationId,
      locationName: locationName,
      locationLat: locationLat,
      locationLng: locationLng,
      scheduledDate: scheduledDate,
      dailySequence: dailySequence,
      status: status ?? this.status,
      arrivalTime: arrivalTime ?? this.arrivalTime,
      completionTime: completionTime ?? this.completionTime,
      contactPerson: contactPerson ?? this.contactPerson,
      designation: designation,
      phone: phone,
      notes: notes ?? this.notes,
      visitType: visitType,
      sampleDistributed: sampleDistributed,
      photoUrl: photoUrl,
      missedReason: missedReason,
      carryForwardCnt: carryForwardCnt,
      isAdHoc: isAdHoc,
      syncStatus: syncStatus ?? this.syncStatus,
    );
  }
}
