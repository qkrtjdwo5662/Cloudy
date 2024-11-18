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
import { useFetchServiceMonitoring } from "@/features/container-usage/hooks/useFetchServiceMonitoring";

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

type ServiceMonitoringChartProps = {
  containerId: number | string;
};

const ServiceMonitoringChart = ({
  containerId,
}: ServiceMonitoringChartProps) => {
  const interval = 30;
  const {
    data: monitoringData,
    error,
    isLoading,
  } = useFetchServiceMonitoring(1, "SECONDS", 3, interval);
  console.log(monitoringData);

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [
      {
        label: "서비스 호출 횟수",
        data: [],
        borderColor: "rgba(99, 102, 241, 1)",
        borderWidth: 2,
        fill: false,
      },
    ],
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
        serviceNameList = [],
      } = monitoringData;

      if (
        timeList.length === 0 ||
        countLists.length === 0 ||
        serviceNameList.length === 0
      ) {
        return;
      }

      const containerIndex =
        typeof containerId === "string"
          ? parseInt(containerId, 10)
          : containerId;

      if (
        isNaN(containerIndex) ||
        containerIndex >= countLists.length ||
        containerIndex < 0
      ) {
        return;
      }

      const formattedLabels = timeList.map((time: string) => {
        const date = new Date(time);
        return `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
      });

      const dataset = {
        label:
          serviceNameList[containerIndex] || `Service ${containerIndex + 1}`,
        data: countLists[containerIndex] || [],
        borderColor: colors[containerIndex],
        borderWidth: 2,
        fill: false,
      };

      setChartData({
        labels: formattedLabels,
        datasets: [dataset],
      });
    }
  }, [monitoringData, containerId]);

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
    return null;
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

export default ServiceMonitoringChart;
