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
import { useFetchServerMonitoring } from "@/features/dashboard/hooks/useFetchServerMonitoring";

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

const RealTimeChart = () => {
  const interval = 15;
  const {
    data: monitoringData,
    error,
    isLoading,
  } = useFetchServerMonitoring(1, "SECONDS", 3, interval);

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [
      {
        label: "서버 호출 횟수",
        data: [],
        borderColor: "rgba(99, 102, 241, 1)",
        borderWidth: 2,
        fill: false,
      },
    ],
  });

  useEffect(() => {
    if (
      monitoringData &&
      Array.isArray(monitoringData.timeList) &&
      Array.isArray(monitoringData.countList)
    ) {
      const { timeList, countList } = monitoringData;

      const formattedLabels = timeList.map((time: string) => {
        const date = new Date(time);
        return `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
      });

      setChartData({
        labels: formattedLabels,
        datasets: [
          {
            label: "서버 호출 횟수",
            data: countList,
            borderColor: "rgba(99, 102, 241, 1)",
            borderWidth: 2,
            fill: false,
          },
        ],
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

export default RealTimeChart;
