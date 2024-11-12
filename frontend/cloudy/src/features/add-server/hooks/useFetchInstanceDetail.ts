import { fetchInstanceDetail } from "../lib/fetchers";
import { InstanceDetailGetResponses } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchInstanceDetail = () => {
  return useQuery<InstanceDetailGetResponses, Error>({
    queryKey: ["InstanceDetail"],
    queryFn: () => fetchInstanceDetail(),
  });
};
