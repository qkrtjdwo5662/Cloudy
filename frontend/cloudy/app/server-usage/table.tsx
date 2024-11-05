import React from "react";

interface TableRowProps {
  containerName: string;
  callCount: string;
}

interface TableProps {
  rows: TableRowProps[];
}

export default function Table({ rows }: TableProps) {
  return (
    <div className="flex-1 rounded-lg bg-white p-20">
      <div className="flex bg-indigo-500 font-semibold text-white">
        <div className="flex-1 border-b border-gray-200 px-20 py-10">
          컨테이너 명
        </div>
        <div className="border-b border-gray-200 px-20 py-10">호출 횟수</div>
      </div>

      <div>
        {rows.map((row, index) => (
          <div
            key={index}
            className="flex items-center border-b border-gray-200 font-semibold text-gray-700 hover:bg-gray-50"
          >
            <div className="flex-1 px-20 py-10">{row.containerName}</div>
            <div className="w-1/4 px-20 py-10">{row.callCount}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
