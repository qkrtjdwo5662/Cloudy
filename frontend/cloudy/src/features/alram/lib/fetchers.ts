import { AlarmGetResponses } from "../model/types";
import { handleApiRequest } from "@/shared/api/client";

export const fetchAlarm = async () => {
  return handleApiRequest<AlarmGetResponses, "get">(`/alarms`, "get");
};
