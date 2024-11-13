"use client";
import Image from "next/image";
import React from "react";
import { Button } from "@/shared/ui/button/Button";
import { useDeleteServer } from "@/features/add-server";

interface TableRowProps {
  serverId: number;
  serverName: string;
  cloudIcon: string;
  cloudType: string;
  instanceSize: string;
  vCPU: string;
  memory: string;
  storage: string | null;
  networkBandwidth: string;
  paymentPlan: string;
}

interface TableContainerProps {
  servers: TableRowProps[];
  isLoading: boolean;
}

// 결제 방식 맵핑 (역변환)
const COST_TYPE_DISPLAY_MAPPING = {
  ON: "온디멘드(시간당)",
  ONE: "1년(예약)",
  THREE: "3년(예약)",
};

const TableRow = ({
  serverId,
  serverName,
  cloudIcon,
  cloudType,
  instanceSize,
  vCPU,
  memory,
  storage,
  networkBandwidth,
  paymentPlan,
}: TableRowProps) => {
  const { mutate: deleteServer } = useDeleteServer();

  const handleDelete = () => {
    if (confirm("정말로 이 서버를 삭제하시겠습니까?")) {
      deleteServer(serverId);
    }
  };

  return (
    <tr className="border-b">
      <td className="px-6 py-14 text-center">{serverName}</td>
      <td className="flex items-center justify-center px-6 py-8">
        {cloudIcon ? (
          <Image
            src={cloudIcon}
            alt={`${cloudType} icon`}
            width={24}
            height={24}
          />
        ) : (
          <span className="h-6 w-6 bg-gray-200" />
        )}
        <span className="ml-5 py-4">{cloudType}</span>
      </td>
      <td className="px-6 py-4 text-center">{instanceSize}</td>
      <td className="px-6 py-4 text-center">{vCPU}</td>
      <td className="px-6 py-4 text-center">{memory}</td>
      <td className="px-6 py-4 text-center">{storage || "-"}</td>
      <td className="px-6 py-4 text-center">{networkBandwidth}</td>
      <td className="px-6 py-4 text-center">
        {COST_TYPE_DISPLAY_MAPPING[
          paymentPlan as keyof typeof COST_TYPE_DISPLAY_MAPPING
        ] || paymentPlan}
      </td>

      <td className="px-6 py-4 text-center">
        <Button
          size="s"
          variant="primary"
          design="fill"
          mainText="삭제"
          type="button"
          onClick={handleDelete}
        />
      </td>
    </tr>
  );
};

const TableContainer = ({ servers, isLoading }: TableContainerProps) => {
  if (isLoading) {
    return (
      <div className="flex h-full w-full items-center justify-center text-lg">
        로딩중...
      </div>
    );
  }

  if (!servers || servers.length === 0) {
    return (
      <div className="flex h-full w-full items-center justify-center text-lg">
        등록된 서버가 없습니다.
      </div>
    );
  }

  return (
    <div className="h-full max-h-[230px] w-full overflow-y-auto">
      <table className="min-w-full border-collapse bg-white">
        <thead>
          <tr className="border-b bg-gray-100">
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              서버 이름
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              클라우드 종류
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              인스턴스 크기
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              vCPU
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              메모리(GiB)
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              인스턴스 스토리지(GB)
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              네트워크 대역폭(Gbps)
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              결제방식
            </th>
            <th className="sticky top-0 bg-gray-100 px-6 py-3 text-center font-medium text-gray-700">
              삭제
            </th>
          </tr>
        </thead>
        <tbody>
          {servers.map((server) => (
            <TableRow key={server.serverId} {...server} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableContainer;
