"use client";

import { useFetchAlarm } from "@/features/alram/hooks/useFetchAlarm";
import { Title } from "@/shared/ui";

export default function AlarmList() {
  const alarmData = [
    {
      serverName: "FinOps Server A",
      title: "CPU Usage High",
      time: "2024-11-07 12:30 PM",
    },
    {
      serverName: "FinOps Server B",
      title: "Memory Usage Near Limit",
      time: "2024-11-07 1:45 PM",
    },
    {
      serverName: "FinOps Server C",
      title: "Disk Space Warning",
      time: "2024-11-07 3:15 PM",
    },
    {
      serverName: "FinOps Server D",
      title: "Network Latency Spike",
      time: "2024-11-07 4:00 PM",
    },
    {
      serverName: "FinOps Server E",
      title: "High I/O Wait Time",
      time: "2024-11-07 4:30 PM",
    },
    {
      serverName: "FinOps Server A",
      title: "CPU Usage High",
      time: "2024-11-07 12:30 PM",
    },
    {
      serverName: "FinOps Server B",
      title: "Memory Usage Near Limit",
      time: "2024-11-07 1:45 PM",
    },
    {
      serverName: "FinOps Server C",
      title: "Disk Space Warning",
      time: "2024-11-07 3:15 PM",
    },
    {
      serverName: "FinOps Server D",
      title: "Network Latency Spike",
      time: "2024-11-07 4:00 PM",
    },
    {
      serverName: "FinOps Server E",
      title: "High I/O Wait Time",
      time: "2024-11-07 4:30 PM",
    },
    {
      serverName: "FinOps Server A",
      title: "CPU Usage High",
      time: "2024-11-07 12:30 PM",
    },
    {
      serverName: "FinOps Server B",
      title: "Memory Usage Near Limit",
      time: "2024-11-07 1:45 PM",
    },
    {
      serverName: "FinOps Server C",
      title: "Disk Space Warning",
      time: "2024-11-07 3:15 PM",
    },
    {
      serverName: "FinOps Server D",
      title: "Network Latency Spike",
      time: "2024-11-07 4:00 PM",
    },
    {
      serverName: "FinOps Server E",
      title: "High I/O Wait Time",
      time: "2024-11-07 4:30 PM",
    },
  ];
  const { data, error, isLoading } = useFetchAlarm();
  console.log(data);

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">알람 목록</Title>

        <div className="mt-10 flex h-full w-full flex-col rounded-5 border border-gray-200 bg-white p-24">
          <div className="flex w-full bg-indigo-500 font-semibold text-white">
            <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
              서버 이름
            </div>
            <div className="flex w-3/5 items-center justify-center border-b border-gray-200 px-20 py-10">
              제목
            </div>
            <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
              시간
            </div>
          </div>

          <div
            className="flex flex-col overflow-y-auto"
            style={{ maxHeight: "520px" }}
          >
            {alarmData.map((alarm, index) => (
              <div key={index} className="flex w-full bg-white text-gray-800">
                <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
                  {alarm.serverName}
                </div>
                <div className="flex w-3/5 items-center justify-center border-b border-gray-200 px-20 py-10">
                  {alarm.title}
                </div>
                <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
                  {alarm.time}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
