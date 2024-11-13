"use client";

import { useEffect, useState, useCallback } from "react";
import { useAuthStore } from "@/shared/stores/authStore";

interface Alarm {
  alarmId: number;
  serverName: string;
  title: string;
  content: string;
  createdAt: string;
  read: boolean;
}

const AlarmList = () => {
  const [alarms, setAlarms] = useState<Alarm[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [isConnected, setIsConnected] = useState(false);
  const accessToken = useAuthStore((state) => state.accessToken);

  const MAX_ALARMS = 50;

  const connectSSE = useCallback(() => {
    if (!accessToken) return;

    const url = new URL("/api/alarms", window.location.origin);
    url.searchParams.append("token", accessToken);

    const eventSource = new EventSource(url.toString(), {
      withCredentials: true,
    });

    eventSource.onopen = () => {
      setIsConnected(true);
      setError(null);
      console.log("SSE 연결 성공 - ReadyState:", eventSource.readyState);
    };

    eventSource.addEventListener("ALARM", (event: MessageEvent) => {
      try {
        const newAlarm: Alarm = JSON.parse(event.data);
        console.log("Parsed newAlarm:", newAlarm); // 알람 데이터 확인
        setAlarms((prevAlarms) => [newAlarm, ...prevAlarms]);
      } catch (err) {
        setError("알람 데이터를 처리하는 중 오류가 발생했습니다.");
        console.error("알람 데이터 파싱 에러:", err);
      }
    });

    // INIT 이벤트 처리
    eventSource.addEventListener("INIT", (event: MessageEvent) => {
      console.log("INIT 이벤트:", event.data);
    });

    eventSource.onerror = () => {
      setIsConnected(false);
      setError("서버와의 연결이 끊어졌습니다.");
      eventSource.close();
    };

    return eventSource;
  }, [accessToken]);

  useEffect(() => {
    const eventSource = connectSSE();
    return () => {
      eventSource?.close();
      setIsConnected(false);
    };
  }, [connectSSE]);

  const formatTimestamp = (timestamp: string) => {
    return new Date(timestamp).toLocaleString("ko-KR", {
      year: "numeric",
      month: "long",
      day: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    });
  };

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold">알람 목록</h1>
      <div className="status text-sm">
        {isConnected ? "연결됨" : "연결 끊김"}
      </div>

      {error && <div className="error text-red-600">{error}</div>}

      {alarms.length === 0 ? (
        <div>새로운 알람이 없습니다.</div>
      ) : (
        <ul>
          {alarms.map((alarm) => (
            <li key={alarm.alarmId} className="alarm">
              <h3>{alarm.title}</h3>
              <p>{alarm.content}</p>
              <small>{formatTimestamp(alarm.createdAt)}</small>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default AlarmList;
