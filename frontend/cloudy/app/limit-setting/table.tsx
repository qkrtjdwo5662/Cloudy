import React from "react";
import { Button } from "@/shared/ui";

interface TableRowProps {
  serverName: string;
  threshold: string;
}

interface TableProps {
  rows: TableRowProps[];
}

export default function Table({ rows }: TableProps) {
  return (
    <div className="flex-1 rounded-lg bg-white p-16">
      <div className="flex bg-indigo-500 font-semibold text-white">
        <div className="flex w-1/2 items-center justify-center border-b border-gray-200 px-20 py-10">
          서버 이름
        </div>
        <div className="flex w-1/2 items-center justify-center border-b border-gray-200 px-20 py-10">
          임계치 정보
        </div>
      </div>

      <div
        className="flex flex-col overflow-y-auto"
        style={{ maxHeight: "475px" }}
      >
        {rows.map((row, index) => (
          <div
            key={index}
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
                />
                <div className="w-1/3">
                  <Button
                    size="m"
                    variant="primary"
                    design="fill"
                    mainText="저장"
                    type="button"
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
