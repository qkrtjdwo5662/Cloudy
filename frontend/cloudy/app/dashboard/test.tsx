import React from "react";
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

const fetchData = async () => {
  return {
    label: new Date().toLocaleTimeString(),
    value: Math.floor(Math.random() * 100),
  };
};

const RealTimeChart = () => {
  const [chartData, setChartData] = React.useState<ChartData>({
    labels: [],
    datasets: [
      {
        label: "Real-Time Data",
        data: [],
        borderColor: "rgba(99, 102, 241, 1)",
        borderWidth: 2,
        fill: false,
      },
    ],
  });

  const { data } = useQuery({
    queryKey: ["realTimeData"],
    queryFn: fetchData,
    refetchInterval: 500,
  });

  React.useEffect(() => {
    if (data) {
      setChartData((prevData) => {
        const newLabels = [...prevData.labels, data.label];
        const newValues = [...prevData.datasets[0].data, data.value];

        if (newLabels.length > 15) {
          newLabels.shift();
          newValues.shift();
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
  }, [data]);

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
          maxTicksLimit: 30,
        },
      },
    },
    animation: {
      duration: 0,
    },
  };

  return (
    <div className="h-full w-full">
      <Line data={chartData} options={options} />
    </div>
  );
};

export default RealTimeChart;
