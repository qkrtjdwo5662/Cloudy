import { CostCalendarResponse } from "../model/types";
import { handleApiRequest } from "@/shared/api/client";

export const fetchCalendar = async (containerId: string) => {
  return handleApiRequest<CostCalendarResponse, "get">(
    `/containers/${containerId}/monthly-cost`,
    "get",
  );
};
