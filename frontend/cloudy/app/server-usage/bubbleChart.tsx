import { Bubble } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  Tooltip,
  Legend,
} from "chart.js";
import React from "react";

ChartJS.register(CategoryScale, LinearScale, PointElement, Tooltip, Legend);

interface BubbleChartData {
  label: string;
  x: number;
  y: number;
  r: number;
  backgroundColor: string;
}

interface BubbleChartProps {
  rows: {
    containerName: string;
    serviceRequestCount: number;
  }[];
}

const options = {
  scales: {
    x: { display: false },
    y: { display: false },
  },
  plugins: {
    legend: {
      display: false,
    },
    tooltip: {
      callbacks: {
        label: function (context: any) {
          const label = context.dataset.label;
          const r = context.raw;
          return `${label}`;
        },
      },
    },
  },
  maintainAspectRatio: false,
  elements: {
    point: {
      borderWidth: 1,
    },
  },
};

export default function BubbleChart({ rows }: BubbleChartProps) {
  const totalServiceRequestCount = rows.reduce(
    (sum, row) => sum + row.serviceRequestCount,
    0,
  );

  const chartData = {
    datasets: rows.map((row, index) => ({
      label: row.containerName,
      data: [
        {
          x: index * 10 + 10,
          y: Math.random() * 100,
          r: (row.serviceRequestCount / totalServiceRequestCount) * 100,
        },
      ],
      backgroundColor: `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(
        Math.random() * 255,
      )}, ${Math.floor(Math.random() * 255)}, 0.8)`,
    })),
  };

  return (
    <div className="h-full w-full p-30">
      {rows.length > 0 ? (
        <Bubble data={chartData} options={options} />
      ) : (
        <div className="flex h-full items-center justify-center">
          <p className="text-gray-500">데이터가 없습니다.</p>
        </div>
      )}
    </div>
  );
}
