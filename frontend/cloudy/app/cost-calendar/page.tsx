"use client";

import { useState, useEffect } from "react";
import Table from "../server-usage/table";
import CostBarChart from "./barChart";
import Calendar from "../dashboard/Calendar";
import { Title } from "@/shared/ui";
import { useFetchDailyCost } from "@/features/cost-calendar";
import { useFetchWeeklyUsage } from "@/features/cost-calendar/hooks/useFetchWeeklyUsage";
import { useFetchWeeklyCost } from "@/features/cost-calendar/hooks/useFetchServerCost";

export default function CostCalendarPage() {
  const getTodayDate = () => {
    const today = new Date();
    return `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, "0")}-${String(
      today.getDate(),
    ).padStart(2, "0")}`;
  };

  const [selectedDate, setSelectedDate] = useState(getTodayDate());
  const { cost, fetchDailyCost } = useFetchDailyCost();
  const { weeklyUsage, loading, error, fetchWeeklyUsage } =
    useFetchWeeklyUsage();
  const { weeklyCost, fetchWeeklyCost } = useFetchWeeklyCost();

  useEffect(() => {
    if (!loading) {
      fetchDailyCost(selectedDate);
      fetchWeeklyUsage(selectedDate);
      fetchWeeklyCost(selectedDate);
      // console.log("weeklyUsage", weeklyUsage);
    }
  }, [selectedDate, fetchDailyCost, fetchWeeklyUsage, fetchWeeklyCost]);

  // useEffect(() => {
  //   fetchWeeklyUsage(selectedDate);
  // }, [selectedDate, fetchWeeklyUsage]);

  // useEffect(() => {
  //   fetchWeeklyCost(selectedDate);
  // }, [selectedDate, fetchWeeklyCost]);

  const handleDateChange = (date: string) => {
    setSelectedDate(date);
    // console.log("selected", selectedDate);
  };

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">비용 캘린더</Title>

        <div className="flex h-full flex-col gap-6 pt-10">
          <section className="flex h-1/2 w-full flex-row gap-6">
            <div className="flex w-full items-center justify-center rounded-lg border border-gray-200 bg-white p-20">
              <Calendar onDateChange={handleDateChange} />
            </div>
            <div className="flex w-full flex-col rounded-lg border border-gray-200 bg-white p-16">
              <Table rows={weeklyUsage} />
              {/* <div className="px-20 pt-10">
                각 컨테이너별 최근 일주일 사용 횟수에 대한 정보입니다.
              </div> */}
            </div>
          </section>

          <section className="flex h-1/2 w-full flex-row gap-6">
            <div className="h-full w-full rounded-lg border border-gray-200 bg-white p-20">
              <CostBarChart weeklyCost={weeklyCost} />
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}
