class Visit {
  final int id;
  final String locationName;
  final String locationType;
  final String status;
  final int dailySequence;
  final double? latitude;
  final double? longitude;
  final String? contactPerson;
  final String? contactPhone;
  final String? notes;
  final String? visitType;
  final int carryForwardCount;
  final String? scheduledDate;

  const Visit({
    required this.id,
    required this.locationName,
    required this.locationType,
    required this.status,
    required this.dailySequence,
    this.latitude,
    this.longitude,
    this.contactPerson,
    this.contactPhone,
    this.notes,
    this.visitType,
    this.carryForwardCount = 0,
    this.scheduledDate,
  });

  factory Visit.fromJson(Map<String, dynamic> json) {
    final loc = json['location'] as Map<String, dynamic>? ?? {};
    return Visit(
      id: json['id'] as int,
      locationName: loc['name'] as String? ?? 'Unknown',
      locationType: loc['type'] as String? ?? 'school',
      status: json['status'] as String? ?? 'planned',
      dailySequence: json['dailySequence'] as int? ?? 1,
      latitude: (loc['latitude'] as num?)?.toDouble(),
      longitude: (loc['longitude'] as num?)?.toDouble(),
      contactPerson: json['contactPerson'] as String?,
      contactPhone: json['contactPhone'] as String?,
      notes: json['notes'] as String?,
      visitType: json['visitType'] as String?,
      carryForwardCount: json['carryForwardCount'] as int? ?? 0,
      scheduledDate: json['scheduledDate'] as String?,
    );
  }

  bool get isCompleted => status == 'completed';
  bool get isMissed => status == 'missed';
  bool get isInProgress => status == 'in_progress';
  bool get isPlanned => status == 'planned';
  bool get isCarryForward => carryForwardCount > 0;
}
