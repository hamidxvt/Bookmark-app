import 'package:flutter/foundation.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:socket_io_client/socket_io_client.dart' as io;

import '../constants/api_constants.dart';

class SocketService {
  io.Socket? _socket;
  bool _connected = false;

  bool get isConnected => _connected;

  void connect({String? cityId}) {
    if (_connected || kIsWeb) return;

    final url = ApiConstants.baseUrl.replaceAll('/api/v1', '');

    _socket = io.io(url, io.OptionBuilder()
        .setTransports(['websocket', 'polling'])
        .disableAutoConnect()
        .build());

    _socket!.onConnect((_) {
      _connected = true;
      debugPrint('[socket] Connected to server');
      if (cityId != null) {
        _socket!.emit('watch:city', cityId);
      } else {
        _socket!.emit('watch:all');
      }
    });

    _socket!.onDisconnect((_) {
      _connected = false;
      debugPrint('[socket] Disconnected');
    });

    _socket!.onConnectError((err) {
      debugPrint('[socket] Connect error: $err');
    });

    _socket!.connect();
  }

  /// Listen for live booker location pings from the server
  void onLocationUpdate(void Function(Map<String, dynamic> data) callback) {
    _socket?.on('location:update', (data) {
      if (data is Map<String, dynamic>) {
        callback(data);
      }
    });
  }

  void disconnect() {
    _socket?.disconnect();
    _socket?.dispose();
    _socket = null;
    _connected = false;
  }

  void dispose() => disconnect();
}

final socketServiceProvider = Provider<SocketService>((ref) {
  final service = SocketService();
  ref.onDispose(service.dispose);
  return service;
});
