import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../domain/entities/visit.dart';
import '../../../core/network/dio_client.dart';
import '../../../core/constants/api_constants.dart';

final visitsProvider = FutureProvider.autoDispose<List<Visit>>((ref) async {
  final dio = ref.read(dioClientProvider);
  final res = await dio.get(ApiConstants.todayVisits);
  final list = res.data['data']['visits'] as List;
  return list.map((v) => _mapVisit(v as Map<String, dynamic>)).toList();
});

Visit _mapVisit(Map<String, dynamic> v) {
  final loc = v['location'] as Map<String, dynamic>;
  return Visit(
    id: v['id'] as int,
    userId: 0,
    locationId: loc['id'] as int,
    locationName: loc['name'] as String,
    locationLat: (loc['latitude'] as num).toDouble(),
    locationLng: (loc['longitude'] as num).toDouble(),
    scheduledDate: DateTime.now(),
    dailySequence: v['sequence'] as int,
    status: v['status'] as String,
    carryForwardCnt: v['carryForwardCnt'] as int? ?? 0,
    isAdHoc: v['isAdHoc'] as bool? ?? false,
  );
}

class VisitListScreen extends ConsumerWidget {
  const VisitListScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final visitsAsync = ref.watch(visitsProvider);

    return Scaffold(
      appBar: AppBar(title: const Text("Today's Visits")),
      body: visitsAsync.when(
        loading: () => const Center(child: CircularProgressIndicator()),
        error: (e, _) => Center(child: Text('Error: $e')),
        data: (visits) => ListView.builder(
          padding: const EdgeInsets.all(12),
          itemCount: visits.length,
          itemBuilder: (context, i) => _VisitCard(visit: visits[i]),
        ),
      ),
      floatingActionButton: FloatingActionButton.extended(
        icon: const Icon(Icons.add_location_alt),
        label: const Text('Ad-hoc Visit'),
        onPressed: () {},
      ),
    );
  }
}

class _VisitCard extends StatelessWidget {
  final Visit visit;
  const _VisitCard({required this.visit});

  @override
  Widget build(BuildContext context) {
    Color statusColor;
    IconData statusIcon;
    switch (visit.status) {
      case 'completed':
        statusColor = Colors.green;
        statusIcon = Icons.check_circle;
        break;
      case 'in_progress':
        statusColor = Colors.blue;
        statusIcon = Icons.directions_walk;
        break;
      case 'missed':
        statusColor = Colors.red;
        statusIcon = Icons.cancel;
        break;
      default:
        statusColor = Colors.grey;
        statusIcon = Icons.radio_button_unchecked;
    }

    return Card(
      margin: const EdgeInsets.only(bottom: 8),
      child: ListTile(
        leading: CircleAvatar(
          backgroundColor: statusColor.withOpacity(0.15),
          child: Text('${visit.dailySequence}',
              style: TextStyle(fontWeight: FontWeight.bold, color: statusColor)),
        ),
        title: Text(visit.locationName, style: const TextStyle(fontWeight: FontWeight.w600)),
        subtitle: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            if (visit.carryForwardCnt > 0)
              Text('Carry-forward: ${visit.carryForwardCnt}/${5}',
                  style: const TextStyle(color: Colors.orange, fontSize: 12)),
            if (visit.isAdHoc)
              const Text('Ad-hoc', style: TextStyle(color: Colors.purple, fontSize: 12)),
          ],
        ),
        trailing: Icon(statusIcon, color: statusColor),
        onTap: visit.isPlanned || visit.isInProgress
            ? () => Navigator.of(context).pushNamed('/visits/${visit.id}/complete')
            : null,
      ),
    );
  }
}
