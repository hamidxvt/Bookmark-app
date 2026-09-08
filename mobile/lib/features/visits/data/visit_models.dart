class Visit {
  final int id;
  final int? customerId;
  final String locationName;
  final String locationType;
  final String status;
  final int dailySequence;
  final double? latitude;
  final double? longitude;
  final double? customerLat;
  final double? customerLng;
  final String? contactPerson;
  final String? contactPhone;
  final String? notes;
  final String? visitType;
  final int carryForwardCount;
  final String? scheduledDate;
  final bool isAdhoc;

  const Visit({
    required this.id,
    this.customerId,
    required this.locationName,
    required this.locationType,
    required this.status,
    required this.dailySequence,
    this.latitude,
    this.longitude,
    this.customerLat,
    this.customerLng,
    this.contactPerson,
    this.contactPhone,
    this.notes,
    this.visitType,
    this.carryForwardCount = 0,
    this.scheduledDate,
    this.isAdhoc = false,
  });

  factory Visit.fromJson(Map<String, dynamic> json) {
    return Visit(
      id: json['id'] as int,
      customerId: json['customerId'] as int? ?? (json['customer'] as Map?)?['id'] as int?,
      // API returns flat fields: customerName, customerType
      locationName: json['customerName'] as String? ??
          (json['location'] as Map?)?['name'] as String? ?? 'Unknown',
      locationType: (json['customerType'] as String? ??
              (json['location'] as Map?)?['type'] as String? ?? 'school')
          .toLowerCase(),
      status: json['status'] as String? ?? 'pending',
      dailySequence: json['sequence'] as int? ?? json['dailySequence'] as int? ?? 1,
      latitude: (json['latitude'] as num?)?.toDouble() ??
          ((json['location'] as Map?)?['latitude'] as num?)?.toDouble(),
      longitude: (json['longitude'] as num?)?.toDouble() ??
          ((json['location'] as Map?)?['longitude'] as num?)?.toDouble(),
      customerLat: (json['latitude'] as num?)?.toDouble(),
      customerLng: (json['longitude'] as num?)?.toDouble(),
      contactPerson: json['contactPerson'] as String? ?? json['contact'] as String?,
      contactPhone: json['contactPhone'] as String? ?? json['phone'] as String?,
      notes: json['notes'] as String?,
      visitType: json['visitType'] as String?,
      carryForwardCount: json['carryForwardCount'] as int? ?? 0,
      scheduledDate: json['visitDate'] as String? ?? json['scheduledDate'] as String?,
      isAdhoc: json['isAdhoc'] as bool? ?? false,
    );
  }

  bool get isCompleted   => status == 'completed';
  bool get isMissed      => status == 'missed' || status == 'cancelled';
  bool get isInProgress  => status == 'in_progress';
  bool get isPlanned     => status == 'pending' || status == 'planned';
  bool get isCarryForward => carryForwardCount > 0;
}
