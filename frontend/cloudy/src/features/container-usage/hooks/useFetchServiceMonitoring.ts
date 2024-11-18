import { fetchServiceCount, fetchServiceMonitoring } from "../lib/fetchers";
import { ServiceMonitoringResponse, ServiceGetCounts } from "../model/types";
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

export const useFetchServiceCount = (
  containerId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  return useQuery<ServiceGetCounts, Error>({
    queryKey: ["ServiceMonitoringCount", containerId, unit, interval, count],
    queryFn: () => fetchServiceCount(containerId, unit, interval, count),
    refetchInterval: 3000,
  });
};
