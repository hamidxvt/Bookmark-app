class AuthUser {
  final int id;
  final String name;
  final String email;
  final String role;
  final int? cityId;
  final int? areaId;
  final int leaveBalanceSick;
  final int leaveBalanceCasual;

  const AuthUser({
    required this.id,
    required this.name,
    required this.email,
    required this.role,
    this.cityId,
    this.areaId,
    this.leaveBalanceSick = 10,
    this.leaveBalanceCasual = 18,
  });

  factory AuthUser.fromJson(Map<String, dynamic> json) => AuthUser(
        id: json['id'] as int,
        name: json['name'] as String,
        email: json['email'] as String,
        role: json['role'] as String,
        cityId: json['cityId'] as int?,
        areaId: json['areaId'] as int?,
        leaveBalanceSick: json['leaveBalanceSick'] as int? ?? 10,
        leaveBalanceCasual: json['leaveBalanceCasual'] as int? ?? 18,
      );

  String get displayRole => switch (role) {
        'super_admin' => 'Super Admin',
        'city_head' => 'City Head',
        'coordinator' => 'Coordinator',
        _ => 'Sales Officer',
      };

  bool get isAdmin => role == 'super_admin' || role == 'city_head';
}

class AuthState {
  final AuthUser? user;
  final bool isLoading;
  final bool isRestoring;
  final String? error;

  const AuthState({
    this.user,
    this.isLoading = false,
    this.isRestoring = true,
    this.error,
  });

  bool get isAuthenticated => user != null;

  AuthState copyWith({
    AuthUser? user,
    bool? isLoading,
    bool? isRestoring,
    String? error,
    bool clearUser = false,
    bool clearError = false,
  }) {
    return AuthState(
      user: clearUser ? null : (user ?? this.user),
      isLoading: isLoading ?? this.isLoading,
      isRestoring: isRestoring ?? this.isRestoring,
      error: clearError ? null : (error ?? this.error),
    );
  }
}
