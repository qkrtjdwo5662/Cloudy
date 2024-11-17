"use client";
import { useEffect } from "react";
import ServerOption from "./serverOption";
import { useFetchServerRecommend } from "@/features/dashboard/hooks/useFetchServerRecommend";

export default function ServerRecommand() {
  const { serverRecommend, loading, error, fetchServerRecommend } =
    useFetchServerRecommend();

  const cloudLinks: Record<string, string> = {
    AWS: "https://aws.amazon.com/",
    GCP: "https://cloud.google.com/",
    AZURE: "https://azure.microsoft.com/",
  };

  useEffect(() => {
    fetchServerRecommend();
  }, [fetchServerRecommend]);

  if (loading) {
    return <div className="text-center text-gray-600">로딩 중...</div>;
  }

  if (error) {
    return <div className="text-center text-red-500">{error}</div>;
  }

  return (
    <div className="main m-5 flex flex-col">
      <div className="header">
        <div className="mb-10 text-xl font-bold text-gray-600">서버 추천</div>
      </div>
      <div className="options flex flex-col gap-4">
        {serverRecommend.map((option, index) => (
          <ServerOption
            key={index}
            title={`${option.instanceName} - ${option.cloudType}`}
            description={`시간당 비용: $${option.costPerHour.toFixed(2)}`}
            link={cloudLinks[option.cloudType] || "#"}
          />
        ))}
      </div>
    </div>
  );
}
