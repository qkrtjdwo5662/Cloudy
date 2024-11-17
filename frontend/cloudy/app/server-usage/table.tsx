import React from "react";

interface TableRowProps {
  containerId: number;
  containerName: string;
  serviceRequestCount: number;
}

interface TableProps {
  rows: TableRowProps[];
}

export default function Table({ rows }: TableProps) {
  return (
    <div className="flex-1 rounded-lg bg-white p-16">
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
        {rows.map((row, index) => (
          <div
            key={index}
            className="flex items-center border-b border-gray-200 font-semibold text-gray-700 hover:bg-gray-50"
          >
            <div className="flex w-1/2 items-center justify-center px-20 py-10">
              {row.containerName}
            </div>
            <div className="flex w-1/2 items-center justify-center px-20 py-10">
              {row.serviceRequestCount}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
