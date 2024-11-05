import { Bubble } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(CategoryScale, LinearScale, PointElement, Tooltip, Legend);

const data = {
  datasets: [
    {
      label: "컨테이너1",
      data: [{ x: 10, y: 10, r: 46 }], // 46%에 맞춘 반지름 크기
      backgroundColor: "rgba(99, 102, 241, 0.8)", // 보라색
    },
    {
      label: "컨테이너2",
      data: [{ x: 15, y: 65, r: 32 }], // 32%에 맞춘 반지름 크기
      backgroundColor: "rgba(167, 180, 255, 0.8)", // 연보라색
    },
    {
      label: "컨테이너3",
      data: [{ x: 20, y: 20, r: 18 }], // 18%에 맞춘 반지름 크기
      backgroundColor: "rgba(255, 99, 132, 0.8)", // 빨간색
    },
    {
      label: "기타",
      data: [{ x: 25, y: 95, r: 4 }], // 4%에 맞춘 반지름 크기
      backgroundColor: "rgba(255, 205, 86, 0.8)", // 노란색
    },
  ],
};

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
          const label = context.dataset.label; // 컨테이너 이름
          const r = context.raw.r; // 'r' 값
          return `${label}: ${r}%`; // "컨테이너 이름: r%" 형식으로 표시
        },
      },
    },
  },
  maintainAspectRatio: false,
  elements: {
    point: {
      borderWidth: 1, // 버블 테두리 두께
    },
  },
};

export default function BubbleChart() {
  return (
    <div className="h-full w-full p-30">
      <Bubble data={data} options={options} />
    </div>
  );
}
