import { useEffect, useRef, useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { officersApi } from '../api/client'
import type { LiveOfficer } from '../types'

declare global {
  interface Window { google: typeof google }
}

export default function LiveMapPage() {
  const mapRef = useRef<HTMLDivElement>(null)
  const mapInstance = useRef<google.maps.Map | null>(null)
  const markersRef = useRef<Record<number, google.maps.Marker>>({})
  const [selected, setSelected] = useState<LiveOfficer | null>(null)

  const { data: officers } = useQuery({
    queryKey: ['live-officers'],
    queryFn: () => officersApi.livePositions().then((r) => r.data.officers as LiveOfficer[]),
    refetchInterval: 5000,
  })

  useEffect(() => {
    if (!mapRef.current || mapInstance.current) return
    mapInstance.current = new window.google.maps.Map(mapRef.current, {
      center: { lat: 24.8607, lng: 67.0011 },
      zoom: 11,
      mapTypeControl: false,
      streetViewControl: false,
    })
  }, [])

  useEffect(() => {
    if (!mapInstance.current || !officers) return

    officers.forEach((officer) => {
      const pos = { lat: officer.lat, lng: officer.lng }
      if (markersRef.current[officer.id]) {
        markersRef.current[officer.id].setPosition(pos)
      } else {
        const marker = new window.google.maps.Marker({
          position: pos,
          map: mapInstance.current!,
          title: officer.name,
          icon: {
            url: 'https://maps.google.com/mapfiles/ms/icons/blue-dot.png',
          },
        })
        marker.addListener('click', () => setSelected(officer))
        markersRef.current[officer.id] = marker
      }
    })

    // Remove stale markers
    Object.keys(markersRef.current).forEach((id) => {
      if (!officers.find((o) => o.id === Number(id))) {
        markersRef.current[Number(id)].setMap(null)
        delete markersRef.current[Number(id)]
      }
    })
  }, [officers])

  return (
    <div className="relative h-full">
      <div ref={mapRef} className="w-full h-full" />

      {/* Officers panel */}
      <div className="absolute top-4 right-4 bg-white rounded-xl border border-gray-100 w-56 shadow-sm">
        <div className="px-4 py-3 border-b">
          <p className="text-sm font-semibold text-gray-900">Live Officers</p>
          <p className="text-xs text-gray-400">{officers?.length ?? 0} in field · updates every 5s</p>
        </div>
        <div className="overflow-y-auto max-h-80">
          {(officers || []).map((o) => (
            <button
              key={o.id}
              onClick={() => {
                setSelected(o)
                mapInstance.current?.panTo({ lat: o.lat, lng: o.lng })
                mapInstance.current?.setZoom(15)
              }}
              className={`w-full text-left px-4 py-2.5 hover:bg-gray-50 border-b border-gray-50 last:border-0 ${selected?.id === o.id ? 'bg-blue-50' : ''}`}
            >
              <p className="text-sm font-medium text-gray-900">{o.name}</p>
              {o.current_visit && <p className="text-xs text-gray-400 truncate">{o.current_visit}</p>}
              <p className="text-xs text-gray-300">Updated {new Date(o.last_updated).toLocaleTimeString()}</p>
            </button>
          ))}
        </div>
      </div>

      {/* Selected officer tooltip */}
      {selected && (
        <div className="absolute bottom-6 left-1/2 -translate-x-1/2 bg-white rounded-xl border border-gray-100 px-5 py-3 shadow-sm">
          <p className="font-semibold text-gray-900">{selected.name}</p>
          {selected.current_visit && <p className="text-sm text-gray-500">{selected.current_visit}</p>}
          <p className="text-xs text-gray-400">{selected.lat.toFixed(5)}, {selected.lng.toFixed(5)}</p>
        </div>
      )}
    </div>
  )
}
