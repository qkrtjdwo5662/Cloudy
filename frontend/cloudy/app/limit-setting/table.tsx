import React, { useState } from "react";
import axios from "axios";
import { Button } from "@/shared/ui";
import { useAuthStore } from "@/shared/stores/authStore";

interface TableRowProps {
  serverId: number;
  serverName: string;
  threshold: number;
}

interface TableProps {
  rows: TableRowProps[];
}

export default function Table({ rows }: TableProps) {
  const [updatedThresholds, setUpdatedThresholds] = useState<{
    [key: number]: number;
  }>({});
  const accessToken = useAuthStore((state) => state.accessToken);

  const handleThresholdChange = (serverId: number, value: number) => {
    setUpdatedThresholds((prev) => ({ ...prev, [serverId]: value }));
  };

  const handleSaveClick = async (serverId: number) => {
    if (!accessToken) return;

    const updatedLimitValue =
      updatedThresholds[serverId] ||
      rows.find((row) => row.serverId === serverId)?.threshold;

    try {
      const response = await axios.put(
        "http://k11a606.p.ssafy.io:8081/servers/limit/update",
        {
          serverId,
          updatedLimitValue,
          useAlarm: true,
        },
        {
          headers: {
            Accept: "*/*",
            Authorization: `Bearer ${accessToken}`,
            "Content-Type": "application/json",
          },
        },
      );
      // console.log("Update successful:", response.data);
      alert("임계치가 성공적으로 업데이트되었습니다.");
    } catch (error) {
      // console.error("Error updating threshold:", error);
      alert("임계치 업데이트에 실패했습니다.");
    }
  };

  return (
    <div className="flex-1 rounded-lg bg-white p-16">
      <div className="flex bg-indigo-500 font-semibold text-white">
        <div className="flex w-1/2 items-center justify-center border-b border-gray-200 px-20 py-10">
          서버 이름
        </div>
        <div className="flex w-1/2 items-center justify-center border-b border-gray-200 px-20 py-10">
          임계치 정보($)
        </div>
      </div>

      <div
        className="flex flex-col overflow-y-auto"
        style={{ maxHeight: "475px" }}
      >
        {rows.map((row) => (
          <div
            key={row.serverId}
            className="flex items-center border-b border-gray-200 font-semibold text-gray-700 hover:bg-gray-50"
          >
            <div className="flex w-1/2 items-center justify-center px-20 py-10">
              {row.serverName}
            </div>
            <div className="flex w-1/2 items-center justify-center px-20 py-10">
              <div className="flex w-full items-center justify-center gap-4">
                <input
                  type="number"
                  defaultValue={row.threshold}
                  placeholder="임계치 설정"
                  className="h-40 w-2/3 rounded-lg border border-gray-200 p-2 text-center"
                  onChange={(e) =>
                    handleThresholdChange(row.serverId, Number(e.target.value))
                  }
                />
                <div className="w-1/3">
                  <Button
                    size="m"
                    variant="primary"
                    design="fill"
                    mainText="저장"
                    type="button"
                    onClick={() => handleSaveClick(row.serverId)}
                  />
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
