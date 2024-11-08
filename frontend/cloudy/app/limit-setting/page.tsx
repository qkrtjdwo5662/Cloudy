"use client";
import { NavigationBox } from "@/shared/ui";
import MainLineChart from "../dashboard/mainLineChart";
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
        <Title size="l">임계치 설정</Title>

        <div className="flex h-full flex-row gap-6 pt-10">
          <main className="flex w-full rounded-5 border border-gray-200 bg-white p-6">
            <div className="flex w-full rounded-lg p-20">
              <MainLineChart />
            </div>
          </main>
        </div>
      </div>
    </div>
  );
}
