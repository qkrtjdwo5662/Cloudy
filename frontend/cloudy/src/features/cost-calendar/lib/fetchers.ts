import { CostCalendarResponse } from "../model/types";
import { handleApiRequest } from "@/shared/api/client";

export const fetchCalendar = async (serverId: string) => {
  return handleApiRequest<CostCalendarResponse, "get">(
    `/servers/daily-cost`,
    "get",
  );
};
