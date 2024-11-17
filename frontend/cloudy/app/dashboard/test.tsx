import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import { useQuery } from "@tanstack/react-query";
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
  const getCurrentDateTime = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    const seconds = String(now.getSeconds()).padStart(2, "0");
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  };

  const {
    data: monitoringData,
    error,
    isLoading,
  } = useFetchServerMonitoring(1, getCurrentDateTime(), "SECONDS", 5, 30);

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [
      {
        label: "Real-Time Monitoring Data",
        data: [],
        borderColor: "rgba(99, 102, 241, 1)",
        borderWidth: 2,
        fill: false,
      },
    ],
  });

  useEffect(() => {
    if (monitoringData) {
      setChartData((prevData) => {
        const newLabels = [...prevData.labels];
        const newValues = [...prevData.datasets[0].data];

        // 새로운 데이터를 차트에 추가
        monitoringData.forEach((dataPoint, index) => {
          newLabels.push(new Date().toLocaleTimeString()); // 현재 시간을 라벨로 사용
          newValues.push(dataPoint); // 모니터링 데이터를 추가
        });

        if (newLabels.length > 15) {
          newLabels.splice(0, newLabels.length - 15);
          newValues.splice(0, newValues.length - 15);
        }

        return {
          labels: newLabels,
          datasets: [
            {
              ...prevData.datasets[0],
              data: newValues,
            },
          ],
        };
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
          maxTicksLimit: 15,
        },
      },
    },
    animation: {
      duration: 0,
    },
  };

  if (isLoading) {
    return <div>Loading...</div>;
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
