"use client";
import Table from "../server-usage/table";
import CostBarChart from "./barChart";

const sampleData = [
  { containerName: "컨테이너1", callCount: "14회" },
  { containerName: "컨테이너2", callCount: "10회" },
  { containerName: "컨테이너3", callCount: "48회" },
  { containerName: "컨테이너4", callCount: "22회" },
  { containerName: "컨테이너5", callCount: "22회" },
];

export default function CostCalendarPage() {
  return (
    <div className="flex h-full w-full bg-gray-100">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <header className="flex items-center justify-between pb-6">
          <h1 className="text-3xl font-bold text-indigo-500">비용 캘린더</h1>
        </header>

        <div className="flex h-full flex-col gap-6">
          <section className="flex h-full w-full flex-row gap-6">
            <div className="flex w-full rounded-lg border border-gray-200 bg-white p-4"></div>
            <div className="flex w-full rounded-lg border border-gray-200 bg-white p-4">
              <Table rows={sampleData} />
            </div>
          </section>

          <aside className="flex h-full gap-6">
            <div className="w-full rounded-lg border border-gray-200 bg-white p-4">
              <CostBarChart />
            </div>
          </aside>
        </div>
      </div>
    </div>
  );
}
