"use client";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

const data = {
  labels: ["Current Speed", "Remaining"],
  datasets: [
    {
      label: "Speed",
      data: [8, 10],
      backgroundColor: ["#6366F1", "#E5E7EB"],
      borderWidth: 0,
      cutout: "70%",
    },
  ],
};

const options = {
  responsive: true,
  maintainAspectRatio: false,
  rotation: -90,
  circumference: 180,
  layout: {
    padding: {
      top: 0,
      bottom: 0,
      left: 0,
      right: 0,
    },
  },
  plugins: {
    legend: {
      display: false,
    },
    tooltip: {
      enabled: false,
    },
  },
};

export default function GaugeChart() {
  return (
    <div className="flex h-full w-full items-center justify-center overflow-hidden">
      {/* 차트 컨테이너의 크기를 고정 */}
      <div className="relative" style={{ width: "100%", height: "60%" }}>
        <Doughnut data={data} options={options} />
        <div className="flex items-center justify-center pt-10">
          <p className="text-sm font-bold">사용량 / 사용자 지정 사용량</p>
        </div>
      </div>
    </div>
  );
}
