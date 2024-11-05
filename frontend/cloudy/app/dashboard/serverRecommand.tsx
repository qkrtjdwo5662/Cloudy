"use client";
import ServerOption from "./serverOption";

interface Option {
  title: string;
  description: string;
  link: string;
}

const options: Option[] = [
  {
    title: "AWS",
    description:
      "Amazon Web Services는 신뢰할 수 있고 확장 가능하며 저렴한 클라우드 컴퓨팅 서비스를 제공합니다.",
    link: "https://aws.amazon.com/",
  },
  {
    title: "Google Cloud Platform",
    description:
      "Google Cloud는 Google의 인프라에서 실행되는 클라우드 컴퓨팅 서비스 모음을 제공합니다.",
    link: "https://cloud.google.com/",
  },
  {
    title: "Microsoft Azure",
    description:
      "Microsoft Azure는 조직의 비즈니스 과제를 해결할 수 있도록 도와주는 확장 가능한 클라우드 서비스 모음입니다.",
    link: "https://azure.microsoft.com/",
  },
];

export default function ServerRecommand() {
  return (
    <div className="main m-5 flex flex-col">
      <div className="header">
        <div className="mb-4 p-20 text-3xl font-bold text-gray-600">
          서버 추천
        </div>
      </div>
      {options.map((option, index) => (
        <ServerOption
          key={index}
          title={option.title}
          description={option.description}
          link={option.link}
        />
      ))}
    </div>
  );
}
