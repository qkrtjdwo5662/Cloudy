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
  rotation: -90,
  circumference: 180,
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
    <div className="flex flex-col items-center">
      <Doughnut data={data} options={options} />
      <div className="text-center">
        <p className="text-xl font-bold">사용량 / 사용자 지정 사용량</p>
      </div>
    </div>
  );
}
