import { fetchServerUsage } from "../lib/fetchers";
import { ServerUsageGetResponse } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchServerUsage = (serverId: number) => {
  return useQuery<ServerUsageGetResponse, Error>({
    queryKey: ["ServerUsage", serverId],
    queryFn: () => fetchServerUsage(serverId),
    refetchInterval: 1000,
  });
};
