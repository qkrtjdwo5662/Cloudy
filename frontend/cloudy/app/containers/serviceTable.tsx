import React from "react";
import { ServiceGetCount } from "@/features/container-usage/model/types";

interface ServiceTableProps {
  rows: ServiceGetCount[];
}

export default function ServiceTable({ rows }: ServiceTableProps) {
  return (
    <div className="flex-1 rounded-lg bg-white p-10">
      <div className="flex bg-indigo-500 font-semibold text-white">
        <div className="flex w-1/2 items-center justify-center border-b border-gray-200 px-20 py-10">
          이름
        </div>
        <div className="flex w-1/2 items-center justify-center border-b border-gray-200 px-20 py-10">
          호출 횟수(회)
        </div>
      </div>

      <div
        className="flex flex-col overflow-y-auto"
        style={{ maxHeight: "220px" }}
      >
        {rows.length > 0 ? (
          rows.map((row, index) => (
            <div
              key={index}
              className="flex items-center border-b border-gray-200 font-semibold text-gray-700 hover:bg-gray-50"
            >
              <div className="flex w-1/2 items-center justify-center px-20 py-10">
                {row.serviceName}
              </div>
              <div className="flex w-1/2 items-center justify-center px-20 py-10">
                {row.count}
              </div>
            </div>
          ))
        ) : (
          <div className="flex items-center justify-center p-10 text-gray-500">
            데이터가 없습니다.
          </div>
        )}
      </div>
    </div>
  );
}
