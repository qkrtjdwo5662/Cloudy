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

interface CostBarChartProps {
  weeklyCost: { [date: string]: number };
}

export default function CostBarChart({ weeklyCost }: CostBarChartProps) {
  const labels = Object.keys(weeklyCost);
  const dataPoints = Object.values(weeklyCost);

  const data = {
    labels,
    datasets: [
      {
        label: "비용 (USD)",
        data: dataPoints,
        backgroundColor: "#6366F1",
        borderColor: "#4F46E5",
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
        text: "주간 비용 현황",
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
          text: "날짜",
        },
      },
    },
  };

  return (
    <div className="flex h-full w-full flex-col items-center justify-center overflow-hidden">
      <div className="relative" style={{ width: "100%", height: "100%" }}>
        <Bar data={data} options={options} />
      </div>
    </div>
  );
}
