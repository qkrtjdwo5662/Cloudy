"use client";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { useFetchServerUsage } from "@/features/dashboard/hooks/useFetchServerUsage";

ChartJS.register(ArcElement, Tooltip, Legend);

export default function CpuUsageChart() {
  const { data, error, isLoading } = useFetchServerUsage(123);

  const curr = data?.cpuPercent ?? 0;
  const max = 100;
  const label = curr.toFixed(3);

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
    <div className="flex h-full w-full items-center justify-center overflow-hidden">
      <div className="relative" style={{ width: "100%", height: "60%" }}>
        <Doughnut data={chartData} options={options} />
        <div className="flex items-center justify-center pt-10">
          <p className="text-lg font-semibold">현재 CPU 사용량: {label}%</p>
        </div>
      </div>
    </div>
  );
}
