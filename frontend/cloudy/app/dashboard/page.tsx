"use client";

import { Title } from "@/shared/ui";
import ServerUsageChart from "./ServerUsageChart";
import CpuUsageChart from "./CpuUsageChart";
import ServerOption from "./serverOption";
import CostSummary from "./costSummary";
import Calendar from "./Calendar";
import RealTimeChart from "./ServerMonitoringChart";
import { useEffect, useState } from "react";
import { useFetchDailyCost } from "@/features/cost-calendar/hooks/useFetchDailyCost";
import ServerRecommand from "./serverRecommand";
import { useAuthStore } from "@/shared/stores/authStore";
import ContainerMonitoringChart from "../server-usage/ContinerMonitoringChart";

export default function DashBoardPage() {
  const [selectedDate, setSelectedDate] = useState("2024-11-13");
  const { cost, loading, error, fetchDailyCost } = useFetchDailyCost();
  const serverName = useAuthStore((state) => state.serverName);

  useEffect(() => {
    fetchDailyCost(selectedDate);
  }, [selectedDate, fetchDailyCost]);

  const handleDateChange = (date: string) => {
    setSelectedDate(date);
    // console.log("selected", selectedDate);
  };

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">{serverName}</Title>
        <div className="flex h-full flex-col gap-6 pt-10">
          <section className="flex h-1/2 w-full gap-6">
            <article className="flex h-full w-3/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
              <div className="flex h-full w-full">
                {/* <MainLineChart /> */}
                <RealTimeChart />
              </div>
            </article>
            <aside className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
              <div className="flex h-full w-full">
                <CostSummary />
              </div>
            </aside>
          </section>
          <section className="flex h-1/2 w-full gap-6">
            <article className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
              <div className="flex h-full w-full">
                <CpuUsageChart />
              </div>
            </article>
            <article className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
              <div className="flex h-full w-full">
                <ServerUsageChart />
              </div>
            </article>
            <article className="flex h-full w-1/4 flex-col overflow-hidden rounded-5 border border-gray-200 bg-white px-20 pt-20">
              <div className="flex h-full w-full flex-col">
                <ServerRecommand />
                {/* <ServerOption title="qwer" description="qwer" link="/" />
                <ServerOption title="qwer" description="qwer" link="/" />
                <ServerOption title="qwer" description="qwer" link="/" /> */}
              </div>
            </article>
            <article className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-14">
              <div className="flex h-full w-full">
                <Calendar onDateChange={handleDateChange} />
              </div>
            </article>
          </section>
        </div>
      </div>
    </div>
  );
}
