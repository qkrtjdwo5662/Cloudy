"use client";

import { Title } from "@/shared/ui";
import MainLineChart from "./mainLineChart";
import RadarChart from "./radarChart";
import GaugeChart from "./gaugeChart";
import ServerOption from "./serverOption";
import CostSummary from "./costSummary";
import Calendar from "./Calendar";

export default function DashBoardPage() {
  return (
    <div className="flex flex-col gap-4">
      <Title size="l">Title</Title>
      <div className="flex h-620 flex-col gap-4 px-4">
        <section className="flex h-1/2 w-full gap-4">
          <article className="flex h-full w-3/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
            <MainLineChart />
          </article>
          <aside className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
            <CostSummary />
          </aside>
        </section>
        <section className="flex h-1/2 w-full gap-4">
          <article className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
            <RadarChart />
          </article>
          <article className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
            <GaugeChart />
          </article>
          <article className="flex h-full w-1/4 flex-col overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
            <ServerOption title="qwer" description="qwer" link="/" />
            <ServerOption title="qwer" description="qwer" link="/" />
            <ServerOption title="qwer" description="qwer" link="/" />
          </article>
          <article className="flex h-full w-1/4 overflow-hidden rounded-5 border border-gray-200 bg-white p-20">
            <Calendar />
          </article>
        </section>
      </div>
    </div>
  );
}
