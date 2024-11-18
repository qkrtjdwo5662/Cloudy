import { useState, useCallback } from "react";
import axios from "axios";
import { useAuthStore } from "@/shared/stores/authStore";

export const useFetchDailyCost = () => {
  const [cost, setCost] = useState<number>(0);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const serverId = useAuthStore((state) => state.serverId);
  const accessToken = useAuthStore((state) => state.accessToken);

  const fetchDailyCost = useCallback(
    async (date: string) => {
      if (!accessToken) {
        setError("Access token is not available.");
        return;
      }

      setLoading(true);
      setError(null);

      try {
        const response = await axios.get(
          `http://k11a606.p.ssafy.io:8081/servers/daily-cost?serverId=${serverId}&dateTime=${date}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
              Accept: "*/*",
            },
          },
        );
        setCost(response.data.data.cost);
      } catch (err) {
        setError("Failed to fetch daily cost availability.");
        // console.error("Error daily cost:", err);
      } finally {
        setLoading(false);
      }
    },
    [accessToken, serverId],
  );

  return {
    cost,
    loading,
    error,
    fetchDailyCost,
  };
};
