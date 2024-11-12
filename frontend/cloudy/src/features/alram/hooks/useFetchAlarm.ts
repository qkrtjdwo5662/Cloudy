import { fetchAlarm } from "../lib/fetchers";
import { AlarmGetResponses } from "../model/types";
import { useQuery } from "@tanstack/react-query";

export const useFetchAlarm = () => {
  return useQuery<AlarmGetResponses, Error>({
    queryKey: ["Alarm"],
    queryFn: () => fetchAlarm(),
  });
};
