import { useState } from "react";
import axios from "axios";
import { useAuthStore } from "@/shared/stores/authStore";

export const useFetchCostSummary = () => {
  const [accumulatedCost, setAccumulatedCost] = useState<number>(0);
  const [expectedCost, setExpectedCost] = useState<number>(0);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const accessToken = useAuthStore((state) => state.accessToken);

  const fetchCostSummary = async (serverId: number, date: string) => {
    if (!accessToken) {
      setError("Access token is not available.");
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const response = await axios.post(
        `http://k11a606.p.ssafy.io:8081/servers/cost/summary`,
        {
          serverId,
          date,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            Accept: "*/*",
          },
        },
      );

      setAccumulatedCost(response.data.data.accumulatedCost);
      setExpectedCost(response.data.data.expectedCost);
    } catch (err) {
      setError("Failed to fetch cost summary availability.");
      console.error("Error cost summary:", err);
    } finally {
      setLoading(false);
    }
  };

  return {
    accumulatedCost,
    expectedCost,
    loading,
    error,
    fetchCostSummary,
  };
};
