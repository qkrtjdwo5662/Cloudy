"use client";

import { useFetchAlarm } from "@/features/alram/hooks/useFetchAlarm";
import { Title } from "@/shared/ui";
import { AlarmGetResponse } from "@/features/alram/model/types";

export default function AlarmList() {
  const { data, error, isLoading } = useFetchAlarm();

  if (error) return <p>Error loading alarms</p>;

  const alarmData: AlarmGetResponse[] = Array.isArray(data) ? data : [];

  console.log(alarmData);

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
              내용
            </div>
            <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
              시간
            </div>
          </div>

          <div
            className="flex flex-col overflow-y-auto"
            style={{ maxHeight: "520px" }}
          >
            {alarmData.map((alarm) => (
              <div
                key={alarm.alarmId}
                className="flex w-full bg-white text-gray-800"
              >
                <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
                  {alarm.serverName}
                </div>
                <div className="flex w-3/5 items-center justify-center border-b border-gray-200 px-20 py-10">
                  {alarm.title}
                </div>

                <div className="flex w-1/5 items-center justify-center border-b border-gray-200 px-20 py-10">
                  {new Date(alarm.createdAt).toLocaleString()}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
