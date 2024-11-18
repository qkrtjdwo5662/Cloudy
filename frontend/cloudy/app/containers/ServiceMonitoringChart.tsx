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
  id: number | string;
};

const ServiceMonitoringChart = ({ id }: ServiceMonitoringChartProps) => {
  const interval = 15;

  const numericId = typeof id === "string" ? parseInt(id, 10) : id;

  const {
    data: monitoringData,
    error,
    isLoading,
  } = useFetchServiceMonitoring(numericId, "SECONDS", 3, interval);

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [],
  });

  const colors = [
    "rgba(99, 102, 241, 1)",
    "rgba(245, 158, 11, 1)",
    "rgba(239, 68, 68, 1)",
    "rgba(34, 197, 94, 1)",
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
        // console.warn("Monitoring data is incomplete", monitoringData);
        return;
      }

      const formattedLabels = timeList.map((time: string) => {
        const date = new Date(time);
        return `${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
      });

      const datasets = countLists.map((counts: number[], index: number) => ({
        label: serviceNameList[index] || `Service ${index + 1}`,
        data: counts,
        borderColor: colors[index % colors.length],
        borderWidth: 2,
        fill: false,
      }));

      setChartData({
        labels: formattedLabels,
        datasets,
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

export default ServiceMonitoringChart;
