/// Central place that turns any thrown object into a message a Sales
/// Officer can actually understand. Used by every SnackBar / error state
/// so the whole app talks with one voice.

import 'package:dio/dio.dart';

import '../network/dio_client.dart';

/// Convert any error into a short, friendly sentence.
/// - `ApiException.message` — already user-facing, returned as-is.
/// - `DioException` — routed through ApiException.fromDio so http codes,
///   timeouts and connection errors get mapped to friendly text.
/// - anything else — a safe generic fallback (never leaks a stack trace).
String friendlyError(Object? e, {String fallback = 'Something went wrong. Please try again.'}) {
  if (e == null) return fallback;
  if (e is ApiException) return e.message;
  if (e is DioException) return ApiException.fromDio(e).message;
  final s = e.toString();
  // Never surface a raw stack — trim aggressively.
  if (s.length > 140) return fallback;
  return s;
}
