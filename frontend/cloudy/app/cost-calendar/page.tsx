"use client";
import Table from "../server-usage/table";
import CostBarChart from "./barChart";
import Calendar from "../dashboard/Calendar";
import { Title } from "@/shared/ui";
import { useFetchCalendar } from "@/features/cost-calendar/hooks/fetchCalendar";

const sampleData = [
  { containerName: "컨테이너1", callCount: "14회" },
  { containerName: "컨테이너2", callCount: "10회" },
  { containerName: "컨테이너3", callCount: "48회" },
  { containerName: "컨테이너4", callCount: "22회" },
  { containerName: "컨테이너5", callCount: "22회" },
];

export default function CostCalendarPage() {
  const { data, error, isLoading } = useFetchCalendar("1");
  console.log(data);

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">비용 캘린더</Title>

        <div className="flex h-full flex-col gap-6 pt-10">
          <section className="flex h-1/2 w-full flex-row gap-6">
            <div className="flex w-full items-center justify-center rounded-lg border border-gray-200 bg-white p-20">
              <Calendar />
            </div>
            <div className="flex w-full rounded-lg border border-gray-200 bg-white p-16">
              <Table rows={sampleData} />
            </div>
          </section>

          <div className="h-full w-full rounded-lg border border-gray-200 bg-white p-20">
            <CostBarChart />
          </div>
        </div>
      </div>
    </div>
  );
}
