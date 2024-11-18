import { fetchInstances } from "../lib/fetchers";
import { InstanceGetResponses } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchInstance = (cloudType: string) => {
  return useQuery<InstanceGetResponses, Error>({
    queryKey: ["Instance", cloudType],
    queryFn: () => fetchInstances(cloudType),
  });
};
