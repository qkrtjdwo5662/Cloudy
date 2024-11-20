"use client";

import { useParams } from "next/navigation";
import ServiceMonitoringChart from "../ServiceMonitoringChart";
import { Title } from "@/shared/ui";
import { useFetchServiceCount } from "@/features/container-usage/hooks/useFetchServiceMonitoring";
import { ServiceGetCount } from "@/features/container-usage/model/types";
import ServiceTable from "../serviceTable";
import BubbleChart from "../bubbleChart";

export default function ContainerUsagePage() {
  const { id } = useParams();

  const containerId = id as string;
  const numericId = parseInt(containerId, 10);

  const { data } = useFetchServiceCount(numericId, "SECONDS", 3, 15);

  const rows =
    data?.serviceUseGetResponses?.map((item: ServiceGetCount) => ({
      serviceId: item.serviceId,
      serviceName: item.serviceName,
      count: item.count,
    })) ?? [];

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">컨테이너 사용량</Title>

        <div className="flex h-full flex-row gap-6 pt-10">
          <main className="flex w-2/3 rounded-5 border border-gray-200 bg-white p-6">
            <div className="flex w-full rounded-lg p-20">
              <ServiceMonitoringChart id={containerId} />
            </div>
          </main>

          <aside className="flex h-full w-1/3 flex-col gap-6">
            <div className="flex h-full w-full rounded-5 border border-gray-200 bg-white">
              <ServiceTable rows={rows} />
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
