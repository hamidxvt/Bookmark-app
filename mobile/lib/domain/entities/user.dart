class User {
  final int id;
  final String name;
  final String email;
  final String role;
  final int? cityId;
  final int? areaId;
  final double basicSalary;
  final double dailyPerformanceRate;
  final double annualSampleLimitPkr;
  final double sampleUsedPkr;
  final int leaveBalanceSick;
  final int leaveBalanceCasual;

  const User({
    required this.id,
    required this.name,
    required this.email,
    required this.role,
    this.cityId,
    this.areaId,
    this.basicSalary = 0,
    this.dailyPerformanceRate = 3000,
    this.annualSampleLimitPkr = 0,
    this.sampleUsedPkr = 0,
    this.leaveBalanceSick = 10,
    this.leaveBalanceCasual = 18,
  });

  int get totalLeaveBalance => leaveBalanceSick + leaveBalanceCasual;

  bool get isSuperAdmin => role == 'super_admin';
  bool get isCityHead => role == 'city_head';
  bool get isCoordinator => role == 'coordinator';
  bool get isSalesOfficer => role == 'sales_officer';

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'] as int,
      name: json['name'] as String,
      email: json['email'] as String,
      role: json['role'] as String,
      cityId: json['cityId'] as int?,
      areaId: json['areaId'] as int?,
      leaveBalanceSick: json['leaveBalanceSick'] as int? ?? 10,
      leaveBalanceCasual: json['leaveBalanceCasual'] as int? ?? 18,
    );
  }
}
