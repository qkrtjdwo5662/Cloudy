"use client";
import { useEffect, useState } from "react";
import { Title } from "@/shared/ui";
import { useAuthStore } from "@/shared/stores/authStore";
import Table from "./table";
import axios from "axios";

export default function DashBoardPage() {
  const [serverData, setServerData] = useState([]);
  const accessToken = useAuthStore((state) => state.accessToken);

  useEffect(() => {
    if (!accessToken) return;

    const fetchServerData = async () => {
      try {
        const response = await axios.get(
          "http://k11a606.p.ssafy.io:8081/servers/limit",
          {
            headers: {
              Accept: "*/*",
              Authorization: `Bearer ${accessToken}`,
            },
          },
        );
        const formattedData = response.data.data.map((server: any) => ({
          serverId: server.serverId,
          serverName: server.serverName,
          threshold: server.serverLimit,
        }));
        setServerData(formattedData);
      } catch (error) {
        console.error("Error fetching server data:", error);
      }
    };

    fetchServerData();
  }, [accessToken]);

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">임계치 설정</Title>

        <div className="flex h-full flex-row gap-6 pt-10">
          <main className="flex w-full rounded-5 border border-gray-200 bg-white p-6">
            <div className="flex w-full rounded-lg p-20">
              <Table rows={serverData} />
            </div>
          </main>
        </div>
      </div>
    </div>
  );
}
