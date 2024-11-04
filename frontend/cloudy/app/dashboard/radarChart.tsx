"use client";
import { Radar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  RadialLinearScale,
  PointElement,
  LineElement,
  Filler,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  RadialLinearScale,
  PointElement,
  LineElement,
  Filler,
  Tooltip,
  Legend,
);

const defaultData = {
  labels: ["컨테이너1", "컨테이너2", "컨테이너3", "컨테이너4", "컨테이너5"],
  datasets: [
    {
      label: "컨테이너별 호출 수",
      data: [65, 59, 90, 81, 20],
      fill: true,
      backgroundColor: "rgba(199, 210, 290, 0.3)",
      borderColor: "#6366F1",
      pointBackgroundColor: "#4338CA",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgb(34, 202, 236)",
    },
  ],
};

const options = {
  scales: {
    r: {
      beginAtZero: true,
    },
  },
};

export default function RadarChart({
  data = defaultData,
  title = "Radar Chart",
}) {
  return (
    <div className="flex flex-col items-center">
      <h3 className="mb-2 text-lg font-bold">{title}</h3>
      <Radar data={data} options={options} />
    </div>
  );
}
