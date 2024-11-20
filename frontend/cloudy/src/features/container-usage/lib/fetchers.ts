import { handleApiRequest } from "@/shared/api/client";
import { ServiceMonitoringResponse, ServiceGetCounts } from "../model/types";

export const fetchServiceMonitoring = async (
  containerId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  const getCurrentDateTime = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  };
  const response = await handleApiRequest<ServiceMonitoringResponse, "get">(
    `/apiusages/services/monitoring?containerId=${containerId}&dateTime=${getCurrentDateTime()}&unit=${unit}&interval=${interval}&count=${count}`,
    "get",
  );
  return response;
};

export const fetchServiceCount = async (
  containerId: number,
  unit: string,
  interval: number,
  count: number,
) => {
  const getCurrentDateTime = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  };
  const response = await handleApiRequest<ServiceGetCounts, "get">(
    `/apiusages/services/monitoring-count?containerId=${containerId}&dateTime=${getCurrentDateTime()}&unit=${unit}&interval=${interval}&count=${count}`,
    "get",
  );
  return response;
};
