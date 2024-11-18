import { useState, useCallback } from "react";
import axios from "axios";
import { useAuthStore } from "@/shared/stores/authStore";

interface WeeklyUsageResponse {
  containerId: number;
  containerName: string;
  serviceRequestCount: number;
}

export const useFetchWeeklyUsage = () => {
  const [weeklyUsage, setWeeklyUsage] = useState<WeeklyUsageResponse[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const serverId = useAuthStore((state) => state.serverId);
  const accessToken = useAuthStore((state) => state.accessToken);

  const fetchWeeklyUsage = useCallback(
    async (date: string) => {
      if (!serverId || !accessToken) {
        setError("Server ID or Access token is missing.");
        return;
      }

      setLoading(true);
      setError(null);

      try {
        const response = await axios.get(
          `http://k11a606.p.ssafy.io:8081/containers/usage-week?serverId=${serverId}&date=${date}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
              Accept: "*/*",
            },
          },
        );

        setWeeklyUsage(response.data.data.containerGetUseResponses || []);
      } catch (err) {
        setError("Failed to fetch weekly usage data.");
        // console.error("Error fetching weekly usage:", err);
      } finally {
        setLoading(false);
      }
    },
    [serverId, accessToken],
  );

  return { weeklyUsage, loading, error, fetchWeeklyUsage };
};
