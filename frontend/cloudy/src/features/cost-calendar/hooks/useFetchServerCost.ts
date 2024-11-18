import { useState, useCallback } from "react";
import axios from "axios";
import { useAuthStore } from "@/shared/stores/authStore";

interface WeeklyCostResponse {
  [date: string]: number;
}

export const useFetchWeeklyCost = () => {
  const [weeklyCost, setWeeklyCost] = useState<WeeklyCostResponse>({});
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const serverId = useAuthStore((state) => state.serverId);
  const accessToken = useAuthStore((state) => state.accessToken);

  const fetchWeeklyCost = useCallback(
    async (date: string) => {
      if (!serverId || !accessToken) {
        setError("Server ID or Access token is missing.");
        return;
      }

      setLoading(true);
      setError(null);

      try {
        const response = await axios.get(
          `http://k11a606.p.ssafy.io:8081/servers/week-cost?serverId=${serverId}&dateTime=${date}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
              Accept: "*/*",
            },
          },
        );

        setWeeklyCost(response.data.data || {});
      } catch (err) {
        setError("Failed to fetch weekly cost data.");
        // console.error("Error fetching weekly cost:", err);
      } finally {
        setLoading(false);
      }
    },
    [serverId, accessToken],
  );

  return { weeklyCost, loading, error, fetchWeeklyCost };
};
