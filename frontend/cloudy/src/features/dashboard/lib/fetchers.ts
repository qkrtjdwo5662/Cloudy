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
  dateTime: string,
  unit: string,
  interval: number,
  count: number,
) => {
  return handleApiRequest<ServerMonitoringResponse, "get">(
    `/servers/monitoring?serverId=${serverId}&dateTime=${dateTime}&unit=${unit}&interval=${interval}&count=${count}`,
    "get",
  );
};
