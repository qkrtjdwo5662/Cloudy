import { fetchContainerMonitoring } from "../lib/fetchers";
import { ContainerMonitoringResponse } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchContinerMonitoring = (
  serverId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  return useQuery<ContainerMonitoringResponse, Error>({
    queryKey: ["ServerMonitoring", serverId, unit, interval, count],
    queryFn: () => fetchContainerMonitoring(serverId, unit, interval, count),
    refetchInterval: 3000,
  });
};
