import { fetchServerMonitoring } from "../lib/fetchers";
import { ServerMonitoringResponse } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchServerMonitoring = (
  serverId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  return useQuery<ServerMonitoringResponse, Error>({
    queryKey: ["ServerMonitoring", serverId, unit, interval, count],
    queryFn: () => fetchServerMonitoring(serverId, unit, interval, count),
    refetchInterval: 5000,
  });
};
