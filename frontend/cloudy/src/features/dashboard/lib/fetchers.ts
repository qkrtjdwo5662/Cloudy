import { handleApiRequest } from "@/shared/api/client";
import {
  ServerUsageGetResponse,
  ServerMonitoringResponse,
} from "../model/types";

export const fetchServerUsage = async (serverId: number) => {
  return handleApiRequest<ServerUsageGetResponse, "get">(
    `/servers/monitoring/usage?serverId=${serverId}`,
    "get",
  );
};

export const fetchServerMonitoring = async (
  serverId: number,
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
  const response = await handleApiRequest<ServerMonitoringResponse, "get">(
    `/servers/monitoring?serverId=${serverId}&dateTime=${getCurrentDateTime()}&unit=${unit}&interval=${interval}&count=${count}`,
    "get",
  );
  // console.log("API Response:", response);
  return response;
};
