"use client";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
);

const data = {
  labels: [
    "2024년 5월",
    "2024년 6월",
    "2024년 7월",
    "2024년 8월",
    "2024년 9월",
    "2024년 10월",
  ],
  datasets: [
    {
      label: "비용 (USD)",
      data: [0.1, 0.15, 0.13, 0.2, 0.17, 0.1],
      backgroundColor: "#6366F1", // 보라색
      borderColor: "#4F46E5", // 보라색의 조금 더 어두운 색상
      borderWidth: 1,
    },
  ],
};

const options = {
  maintainAspectRatio: false,
  responsive: true,
  plugins: {
    legend: {
      position: "top" as const,
    },
    title: {
      display: true,
      text: "월별 비용 현황",
    },
  },
  scales: {
    y: {
      beginAtZero: true,
      title: {
        display: true,
        text: "비용 (USD)",
      },
    },
    x: {
      title: {
        display: true,
        text: "기간",
      },
    },
  },
};

export default function CostBarChart() {
  return (
    <div className="flex h-full w-full flex-col items-center justify-center overflow-hidden">
      <div className="relative" style={{ width: "100%", height: "90%" }}>
        <Bar data={data} options={options} />
      </div>
    </div>
  );
}
