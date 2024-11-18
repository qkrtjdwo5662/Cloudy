"use client";
import { useEffect } from "react";
import { useFetchCostSummary } from "@/features/dashboard/hooks/useFetchCostSummary";
import { useAuthStore } from "@/shared/stores/authStore";

export default function CostSummary() {
  const { accumulatedCost, expectedCost, loading, error, fetchCostSummary } =
    useFetchCostSummary();
  const serverID = useAuthStore((state) => state.serverId);

  useEffect(() => {
    if (serverID) {
      const today = new Date();
      const formattedDate = today.toISOString().split("T")[0];
      fetchCostSummary(serverID, formattedDate);
    }
  }, [fetchCostSummary]);

  // if (loading) {
  //   return (
  //     <div className="max-w-xs rounded-lg bg-white p-20">
  //       <div className="flex items-center px-20">
  //         <span className="text-xl font-bold text-black">비용 요약</span>
  //       </div>
  //     </div>
  //   );
  // }

  if (error) {
    return (
      <div className="max-w-xs rounded-lg bg-white p-20">
        <div className="flex items-center px-20">
          <span className="text-xl font-bold text-gray-600">비용 요약</span>
        </div>
        <div className="p-20">
          <p className="text-sm text-gray-500">{error}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-xs rounded-lg bg-white p-20">
      <div className="flex items-center px-20">
        <span className="text-xl font-bold text-gray-600">비용 요약</span>
      </div>

      <div className="px-20 py-20">
        <p className="text-sm text-gray-500">월간 비용 누계</p>
        <p className="pt-2 text-2xl font-bold text-indigo-500">
          US ${accumulatedCost.toFixed(2)}
        </p>
        <p className="pt-2 text-xs text-gray-500">이번 달 누적 비용입니다.</p>
      </div>

      <div className="px-20 py-16">
        <p className="text-sm text-gray-500">이번 달의 총 예상 비용</p>
        <p className="pt-2 text-2xl font-bold text-indigo-500">
          US ${expectedCost.toFixed(2)}
        </p>
        <p className="pt-2 text-xs text-gray-500">
          이번 달의 총 예상 비용입니다.
        </p>
      </div>
    </div>
  );
}
