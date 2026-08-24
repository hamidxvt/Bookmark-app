"use client";

import { useState, useRef, useEffect } from "react";
import { Send, Circle } from "lucide-react";
import { formatDateTime } from "@/lib/utils";

const THREADS = [
  { id: 1, name: "Ahmed Raza", city: "Karachi", online: true, unread: 2, lastMessage: "Order placed at City School", lastTime: new Date(Date.now() - 120000) },
  { id: 2, name: "Sara Malik", city: "Lahore", online: true, unread: 1, lastMessage: "Need updated catalog", lastTime: new Date(Date.now() - 3600000) },
  { id: 3, name: "Ali Hassan", city: "Karachi", online: false, unread: 0, lastMessage: "Customer issue resolved", lastTime: new Date(Date.now() - 86400000) },
  { id: 4, name: "Usman Khan", city: "Multan", online: false, unread: 0, lastMessage: "Leave approved, thanks", lastTime: new Date(Date.now() - 86400000 * 2) },
];

const MOCK_MESSAGES: Record<number, { id: number; sender: "admin" | "booker"; text: string; time: Date }[]> = {
  1: [
    { id: 1, sender: "booker", text: "Sir, visited City School today. They placed an order for 50 Math books.", time: new Date(Date.now() - 3600000 * 3) },
    { id: 2, sender: "admin", text: "Great work Ahmed! What grade level?", time: new Date(Date.now() - 3600000 * 2) },
    { id: 3, sender: "booker", text: "Grade 5 and 6 both. Total Rs. 42,000", time: new Date(Date.now() - 1800000) },
    { id: 4, sender: "booker", text: "Order placed at City School", time: new Date(Date.now() - 120000) },
  ],
  2: [
    { id: 1, sender: "booker", text: "Assalam o Alaikum sir, can you please send the 2025 catalog?", time: new Date(Date.now() - 7200000) },
    { id: 2, sender: "admin", text: "Wa alaikum assalam Sara, I'll send it shortly.", time: new Date(Date.now() - 3600000) },
    { id: 3, sender: "booker", text: "Need updated catalog", time: new Date(Date.now() - 3600000) },
  ],
};

function timeLabel(date: Date) {
  const mins = Math.floor((Date.now() - date.getTime()) / 60000);
  if (mins < 1) return "just now";
  if (mins < 60) return `${mins}m`;
  const hrs = Math.floor(mins / 60);
  if (hrs < 24) return `${hrs}h`;
  return formatDateTime(date);
}

export default function InboxClient() {
  const [activeThread, setActiveThread] = useState(1);
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState(MOCK_MESSAGES);
  const bottomRef = useRef<HTMLDivElement>(null);

  const thread = THREADS.find(t => t.id === activeThread)!;
  const msgs = messages[activeThread] ?? [];

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [activeThread, msgs.length]);

  function send() {
    if (!input.trim()) return;
    setMessages(prev => ({
      ...prev,
      [activeThread]: [
        ...(prev[activeThread] ?? []),
        { id: Date.now(), sender: "admin", text: input.trim(), time: new Date() },
      ],
    }));
    setInput("");
  }

  return (
    <div className="flex h-[calc(100vh-64px)]">
      {/* Thread list */}
      <div className="w-72 shrink-0 border-r border-slate-200 bg-white flex flex-col">
        <div className="p-4 border-b border-slate-100">
          <p className="text-sm font-semibold text-slate-800">Messages</p>
          <p className="text-xs text-slate-400">{THREADS.filter(t => t.unread > 0).length} unread</p>
        </div>
        <div className="flex-1 overflow-y-auto divide-y divide-slate-50">
          {THREADS.map(t => (
            <button
              key={t.id}
              onClick={() => setActiveThread(t.id)}
              className={`w-full flex items-start gap-3 px-4 py-3.5 text-left transition-colors hover:bg-slate-50 ${activeThread === t.id ? "bg-red-50 border-r-2 border-[#C8102E]" : ""}`}
            >
              <div className="relative shrink-0">
                <div className="flex h-9 w-9 items-center justify-center rounded-full bg-[#C8102E] text-xs font-bold text-white">
                  {t.name[0]}
                </div>
                {t.online && <span className="absolute -bottom-0.5 -right-0.5 h-2.5 w-2.5 rounded-full border-2 border-white bg-emerald-500" />}
              </div>
              <div className="min-w-0 flex-1">
                <div className="flex items-center justify-between mb-0.5">
                  <p className="text-xs font-semibold text-slate-800 truncate">{t.name}</p>
                  <p className="text-[10px] text-slate-400 shrink-0 ml-1">{timeLabel(t.lastTime)}</p>
                </div>
                <p className="text-xs text-slate-400 truncate">{t.lastMessage}</p>
              </div>
              {t.unread > 0 && (
                <span className="shrink-0 flex h-4 min-w-4 items-center justify-center rounded-full bg-[#C8102E] px-1 text-[10px] font-bold text-white">
                  {t.unread}
                </span>
              )}
            </button>
          ))}
        </div>
      </div>

      {/* Chat window */}
      <div className="flex flex-1 flex-col bg-slate-50">
        {/* Chat header */}
        <div className="flex items-center gap-3 border-b border-slate-200 bg-white px-5 py-3">
          <div className="relative">
            <div className="flex h-9 w-9 items-center justify-center rounded-full bg-[#C8102E] text-xs font-bold text-white">
              {thread.name[0]}
            </div>
            {thread.online && <span className="absolute -bottom-0.5 -right-0.5 h-2.5 w-2.5 rounded-full border-2 border-white bg-emerald-500" />}
          </div>
          <div>
            <p className="text-sm font-semibold text-slate-800">{thread.name}</p>
            <p className="text-xs text-slate-400">{thread.city} · {thread.online ? "Online" : "Offline"}</p>
          </div>
        </div>

        {/* Messages */}
        <div className="flex-1 overflow-y-auto px-5 py-4 space-y-4">
          {msgs.map(m => (
            <div key={m.id} className={`flex ${m.sender === "admin" ? "justify-end" : "justify-start"}`}>
              <div className={`max-w-xs rounded-2xl px-4 py-2.5 ${
                m.sender === "admin"
                  ? "bg-[#0f1e3c] text-white rounded-br-sm"
                  : "bg-white border border-slate-200 text-slate-800 rounded-bl-sm shadow-xs"
              }`}>
                <p className="text-sm leading-relaxed">{m.text}</p>
                <p className={`text-[10px] mt-1 ${m.sender === "admin" ? "text-slate-400" : "text-slate-400"}`}>
                  {timeLabel(m.time)}
                </p>
              </div>
            </div>
          ))}
          <div ref={bottomRef} />
        </div>

        {/* Input */}
        <div className="border-t border-slate-200 bg-white px-4 py-3">
          <div className="flex items-center gap-3">
            <input
              value={input}
              onChange={e => setInput(e.target.value)}
              onKeyDown={e => e.key === "Enter" && !e.shiftKey && send()}
              placeholder={`Message ${thread.name}…`}
              className="flex-1 rounded-full border border-slate-200 bg-slate-50 px-4 py-2.5 text-sm text-slate-800 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition"
            />
            <button
              onClick={send}
              disabled={!input.trim()}
              className="flex h-10 w-10 items-center justify-center rounded-full bg-[#C8102E] text-white hover:bg-[#C8102E] disabled:opacity-40 transition-colors"
            >
              <Send className="h-4 w-4" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
