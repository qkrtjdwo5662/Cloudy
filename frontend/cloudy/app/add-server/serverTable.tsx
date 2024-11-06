"use client";
import Image from "next/image";
import React from "react";

interface TableRowProps {
  serverName: string;
  cloudIcon: string;
  cloudType: string;
  instanceSize: string;
  vCPU: number;
  memory: number;
  storage: string;
  networkBandwidth: string;
  paymentPlan: string;
}

const TableRow = ({
  serverName,
  cloudIcon,
  cloudType,
  instanceSize,
  vCPU,
  memory,
  storage,
  networkBandwidth,
  paymentPlan,
}: TableRowProps) => (
  <tr className="border-b">
    <td className="px-6 py-14 text-center">{serverName}</td>
    <td className="flex items-center justify-center px-6 py-8">
      <Image src={cloudIcon} alt={`${cloudType} icon`} width={24} height={24} />
      <span className="ml-5 py-4">{cloudType}</span>
    </td>
    <td className="px-6 py-4 text-center">{instanceSize}</td>
    <td className="px-6 py-4 text-center">{vCPU}</td>
    <td className="px-6 py-4 text-center">{memory}</td>
    <td className="px-6 py-4 text-center">{storage}</td>
    <td className="px-6 py-4 text-center">{networkBandwidth}</td>
    <td className="px-6 py-4 text-center">{paymentPlan}</td>
    <td className="px-6 py-4 text-center">
      <button className="rounded-6 border border-gray-500 p-2 text-gray-500 hover:border-red-500 hover:bg-red-500 hover:text-white">
        삭제
      </button>
    </td>
  </tr>
);

const TableContainer = () => {
  const rows = [
    {
      serverName: "어쩌구서버",
      cloudIcon: "/images/AWS.png",
      cloudType: "aws",
      instanceSize: "m8g.medium",
      vCPU: 1,
      memory: 4,
      storage: "EBS 전용",
      networkBandwidth: "최대 12.5",
      paymentPlan: "3년",
    },
    {
      serverName: "어쩌구서버",
      cloudIcon: "/images/Azure.png",
      cloudType: "azure",
      instanceSize: "m8g.medium",
      vCPU: 1,
      memory: 4,
      storage: "EBS 전용",
      networkBandwidth: "최대 12.5",
      paymentPlan: "3년",
    },
    {
      serverName: "어쩌구서버",
      cloudIcon: "/images/Google.png",
      cloudType: "google",
      instanceSize: "m8g.medium",
      vCPU: 1,
      memory: 4,
      storage: "EBS 전용",
      networkBandwidth: "최대 12.5",
      paymentPlan: "3년",
    },
  ];

  return (
    <div className="h-full w-full overflow-x-auto">
      <table className="min-w-full border-collapse bg-white">
        <thead>
          <tr className="border-b bg-gray-100">
            <th className="px-6 py-13 text-center font-medium text-gray-700">
              서버 이름
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              클라우드 종류
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              인스턴스 크기
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              vCPU
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              메모리(GiB)
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              인스턴스 스토리지(GB)
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              네트워크 대역폭(Gbps)
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              결제방식
            </th>
            <th className="px-6 py-3 text-center font-medium text-gray-700">
              삭제
            </th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row, index) => (
            <TableRow key={index} {...row} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableContainer;
