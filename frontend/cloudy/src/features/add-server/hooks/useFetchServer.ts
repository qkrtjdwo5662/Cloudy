import { fetchServer } from "../lib/fetchers";
import { ServerGetResponses } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchServer = () => {
  return useQuery<ServerGetResponses, Error>({
    queryKey: ["Server"],
    queryFn: () => fetchServer(),
  });
};
