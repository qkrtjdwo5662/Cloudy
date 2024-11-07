"use client";
import { NavigationBox } from "@/shared/ui";
import MainLineChart from "../dashboard/mainLineChart";
import Table from "./table";
import BubbleChart from "./bubbleChart";
import { Title } from "@/shared/ui";
const sampleData = [
  { containerName: "컨테이너1", callCount: "14회" },
  { containerName: "컨테이너2", callCount: "10회" },
  { containerName: "컨테이너3", callCount: "48회" },
  { containerName: "컨테이너4", callCount: "22회" },
  { containerName: "컨테이너5", callCount: "22회" },
];

export default function DashBoardPage() {
  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">서버 사용량</Title>

        <div className="flex h-full flex-row gap-6 pt-10">
          <main className="flex w-2/3 rounded-5 border border-gray-200 bg-white p-6">
            <div className="flex w-full rounded-lg p-20">
              <MainLineChart />
            </div>
          </main>

          <aside className="flex h-full w-1/3 flex-col gap-6">
            <div className="flex h-full w-full rounded-5 border border-gray-200 bg-white">
              <Table rows={sampleData} />
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
