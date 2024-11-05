"use client";
import { NavigationBox } from "@/shared/ui";
import MainLineChart from "./mainLineChart";
import GaugeChart from "./gaugeChart";
import RadarChart from "./radarChart";
import CostSummary from "./costSummary";
import ServerRecommand from "./serverRecommand";

export default function DashBoardPage() {
  return (
    <div className="flex h-full w-full bg-gray-100">
      <aside className="flex h-full w-64 bg-white p-4">
        <NavigationBox />
      </aside>

      <div className="flex h-full w-full flex-col gap-10 p-20">
        <header className="flex pb-10 pt-20">
          <h1 className="text-4xl font-bold text-indigo-500">Title</h1>
        </header>

        <section className="flex h-full w-full flex-row gap-10">
          <article
            className="h-full overflow-hidden rounded-lg border border-gray-200 bg-white p-6"
            style={{ width: "887px", minWidth: "0" }}
          >
            <MainLineChart />
          </article>

          <aside
            className="h-full w-1/4 overflow-hidden rounded-lg border border-gray-200 bg-white p-6"
            style={{ minWidth: "296px", maxWidth: "296px" }}
          >
            <CostSummary />
          </aside>
        </section>

        <section className="flex h-full w-full gap-10">
          <article className="flex h-full w-1/4 min-w-0 max-w-full overflow-hidden rounded-lg border border-gray-200 bg-white p-6">
            <RadarChart />
          </article>
          <article className="flex h-full w-1/4 min-w-0 max-w-full overflow-hidden rounded-lg border border-gray-200 bg-white p-6">
            <MainLineChart />
          </article>
          <article
            className="flex h-full w-1/4 min-w-0 max-w-full overflow-hidden rounded-lg border border-gray-200 bg-white p-10"
            style={{ minWidth: "296px", maxWidth: "296px" }}
          >
            <ServerRecommand />
          </article>
          <article className="flex h-full w-1/4 min-w-0 max-w-full overflow-hidden rounded-lg border border-gray-200 bg-white p-6">
            <GaugeChart />
          </article>
        </section>
      </div>
    </div>
  );
}
