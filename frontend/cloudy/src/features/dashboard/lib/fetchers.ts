import { handleApiRequest } from "@/shared/api/client";
import { ServerUsageGetResponse } from "../model/types";

export const fetchServerUsage = async (serverId: number) => {
  return handleApiRequest<ServerUsageGetResponse, "get">(
    `/servers/monitoring/usage?serverId=${serverId}`,
    "get",
  );
};
