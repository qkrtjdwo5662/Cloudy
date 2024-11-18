"use client";
import Table from "./table";
import BubbleChart from "./bubbleChart";
import { Title } from "@/shared/ui";
import { useFetchWeeklyUsage } from "@/features/cost-calendar/hooks/useFetchWeeklyUsage";
import { useEffect } from "react";
import ContinerMonitoringChart from "./ContinerMonitoringChart";

export default function DashBoardPage() {
  const { weeklyUsage, loading, error, fetchWeeklyUsage } =
    useFetchWeeklyUsage();

  const getTodayDate = () => {
    const today = new Date();
    return `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, "0")}-${String(
      today.getDate(),
    ).padStart(2, "0")}`;
  };

  useEffect(() => {
    if (!loading) {
      fetchWeeklyUsage(getTodayDate());
      console.log("weeklyUsage", weeklyUsage);
    }
  }, [fetchWeeklyUsage]);
  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">서버 사용량</Title>

        <div className="flex h-full flex-row gap-6 pt-10">
          <main className="flex w-2/3 rounded-5 border border-gray-200 bg-white p-6">
            <div className="flex w-full rounded-lg p-20">
              <ContinerMonitoringChart />
            </div>
          </main>

          <aside className="flex h-full w-1/3 flex-col gap-6">
            <div className="flex h-full w-full rounded-5 border border-gray-200 bg-white">
              <Table rows={weeklyUsage} />
            </div>
            <div className="flex h-full w-full rounded-5 border border-gray-200 bg-white">
              <BubbleChart />
            </div>
          </aside>
        </div>
      </div>
    </div>
  );
}
