import { fetchServerMonitoring } from "../lib/fetchers";
import { ServerMonitoringResponse } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchServerMonitoring = (
  serverId: number,
  dateTime: string,
  unit: string,
  interval: number,
  count: number,
) => {
  return useQuery<ServerMonitoringResponse, Error>({
    queryKey: ["ServerMonitoring", serverId, dateTime, unit, interval, count],
    queryFn: () =>
      fetchServerMonitoring(serverId, dateTime, unit, interval, count),
    refetchInterval: 5000,
  });
};
