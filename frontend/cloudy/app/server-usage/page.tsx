"use client";
import Table from "./table";
import BubbleChart from "./bubbleChart";
import { Title } from "@/shared/ui";
import ContinerMonitoringChart from "./ContinerMonitoringChart";
import { useFetchContinerCount } from "@/features/server-usage/hooks/useFetchContinerMonitoring";
import ContainerTable from "./containerTable";
import { ContainerGetUseResponse } from "@/features/server-usage/model/types";

export default function DashBoardPage() {
  const { data } = useFetchContinerCount(1, "SECONDS", 3, 15);

  const rows =
    data?.containerGetUseResponses?.map((item: ContainerGetUseResponse) => ({
      containerId: item.containerId,
      containerName: item.containerName,
      serviceRequestCount: item.serviceRequestCount,
    })) ?? [];

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
              <ContainerTable rows={rows} />
            </div>
            <div className="flex h-full w-full rounded-5 border border-gray-200 bg-white">
              <BubbleChart rows={rows} />
            </div>
          </aside>
        </div>
      </div>
    </div>
  );
}
