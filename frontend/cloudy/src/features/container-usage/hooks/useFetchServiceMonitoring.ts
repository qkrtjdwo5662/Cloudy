import { fetchServiceMonitoring } from "../lib/fetchers";
import { ServiceMonitoringResponse } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchServiceMonitoring = (
  containerId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  return useQuery<ServiceMonitoringResponse, Error>({
    queryKey: ["ServiceMonitoring", containerId, unit, interval, count],
    queryFn: () => fetchServiceMonitoring(containerId, unit, interval, count),
    refetchInterval: 3000,
  });
};
