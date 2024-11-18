"use client";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { useFetchServerUsage } from "@/features/dashboard/hooks/useFetchServerUsage";

ChartJS.register(ArcElement, Tooltip, Legend);

export default function ServerUsageChart() {
  const { data, error, isLoading } = useFetchServerUsage(123);

  const curr = data?.memUsage ?? 0;
  const max = data?.memLimit ?? 1;
  const label = parseFloat(((curr / max) * 100).toFixed(3));

  const chartData = {
    labels: ["Current Speed", "Remaining"],
    datasets: [
      {
        label: "Speed",
        data: [curr, max - curr],
        backgroundColor: ["#6366F1", "#E5E7EB"],
        borderWidth: 0,
        cutout: "50%",
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

  return (
    <div className="main m-5 flex flex-col">
      <div className="header">
        <div className="mb-10 text-xl font-bold text-gray-600">
          메모리 사용량
        </div>
      </div>
      <div className="relative" style={{ width: "100%", height: "60%" }}>
        <Doughnut data={chartData} options={options} />
        <div className="flex items-center justify-center pt-10">
          <p className="text-lg font-bold text-indigo-700">{label}%</p>
        </div>
      </div>
    </div>
  );
}
