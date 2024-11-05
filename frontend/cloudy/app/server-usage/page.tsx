"use client";
import { NavigationBox } from "@/shared/ui";
import MainLineChart from "../dashboard/mainLineChart";
import Table from "./table";
import BubbleChart from "./bubbleChart";

const sampleData = [
  { containerName: "컨테이너1", callCount: "14회" },
  { containerName: "컨테이너2", callCount: "10회" },
  { containerName: "컨테이너3", callCount: "48회" },
  { containerName: "컨테이너4", callCount: "22회" },
  { containerName: "컨테이너5", callCount: "22회" },
];

export default function DashBoardPage() {
  return (
    <div className="flex h-full w-full bg-gray-100">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <header className="flex p-20">
          <h1 className="text-4xl font-bold text-indigo-500">서버사용량</h1>
        </header>

        <div className="flex h-full flex-row gap-10">
          <main className="flex w-2/3 rounded-md bg-white p-6">
            <div className="flex w-full rounded-lg bg-white p-6">
              <MainLineChart />
            </div>
          </main>

          <aside className="flex h-full w-1/3 flex-col gap-10">
            <div className="flex h-full w-full rounded-lg bg-white">
              <Table rows={sampleData} />
            </div>
            <div className="flex h-full w-full rounded-lg bg-white">
              <BubbleChart />
            </div>
          </aside>
        </div>
      </div>
    </div>
  );
}
