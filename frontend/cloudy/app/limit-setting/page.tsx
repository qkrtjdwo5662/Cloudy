"use client";
import { Title } from "@/shared/ui";
import React from "react";
import Table from "./table";

const dummyData = [
  { serverName: "서버 A", threshold: "50" },
  { serverName: "서버 B", threshold: "75" },
  { serverName: "서버 C", threshold: "30" },
  { serverName: "서버 D", threshold: "60" },
  { serverName: "서버 E", threshold: "90" },
  { serverName: "서버 A", threshold: "50" },
  { serverName: "서버 B", threshold: "75" },
  { serverName: "서버 C", threshold: "30" },
  { serverName: "서버 D", threshold: "60" },
  { serverName: "서버 E", threshold: "90" },
];

export default function DashBoardPage() {
  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">임계치 설정</Title>

        <div className="flex h-full flex-row gap-6 pt-10">
          <main className="flex w-full rounded-5 border border-gray-200 bg-white p-6">
            <div className="flex w-full rounded-lg p-20">
              <Table rows={dummyData} />
            </div>
          </main>
        </div>
      </div>
    </div>
  );
}
