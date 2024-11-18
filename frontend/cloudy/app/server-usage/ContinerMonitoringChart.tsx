import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { useFetchContinerMonitoring } from "@/features/server-usage/hooks/useFetchContinerMonitoring";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
);

type ChartData = {
  labels: string[];
  datasets: {
    label: string;
    data: number[];
    borderColor: string;
    borderWidth: number;
    fill: boolean;
  }[];
};

const ContainerMonitoringChart = () => {
  const interval = 30;
  const {
    data: monitoringData,
    error,
    isLoading,
  } = useFetchContinerMonitoring(1, "SECONDS", 3, interval);
  console.log(monitoringData);

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [],
  });

  const colors = [
    "rgba(99, 102, 241, 1)",
    "rgba(245, 158, 11, 1)",
    "rgba(239, 68, 68, 1)",
  ];

  useEffect(() => {
    if (monitoringData) {
      const {
        timeList = [],
        countLists = [],
        containerNameList = [],
      } = monitoringData;

      if (
        timeList.length === 0 ||
        countLists.length === 0 ||
        containerNameList.length === 0
      ) {
        console.warn("Monitoring data is incomplete", monitoringData);
        return;
      }

      const formattedLabels = timeList.map((time: string) => {
        const date = new Date(time);
        return `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
      });

      const datasets = countLists.map((counts: number[], index: number) => ({
        label: containerNameList[index] || `Container ${index + 1}`,
        data: counts,
        borderColor: colors[index % colors.length],
        borderWidth: 2,
        fill: false,
      }));

      setChartData({
        labels: formattedLabels,
        datasets: datasets,
      });
    }
  }, [monitoringData]);

  const options = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: "top" as const,
      },
    },
    scales: {
      x: {
        type: "category" as const,
        ticks: {
          maxTicksLimit: interval,
        },
      },
      y: {
        min: 0,
        max: 15,
        ticks: {
          stepSize: 3,
        },
      },
    },
    animation: {
      duration: 0,
    },
  };

  if (isLoading) {
    return;
  }

  if (error) {
    return <div>Error loading data</div>;
  }

  return (
    <div className="h-full w-full">
      <Line data={chartData} options={options} />
    </div>
  );
};

export default ContainerMonitoringChart;
