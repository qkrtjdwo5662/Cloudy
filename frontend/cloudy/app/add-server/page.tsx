"use client";
import { useState } from "react";
import { Button } from "@/shared/ui/button/Button";
import { Input } from "@/shared/ui/input/Input";
import { AddDropDownBox } from "./dropDownBox";
import { CloudOptions } from "./cloudOption";
import TableContainer from "./serverTable";
import { Title } from "@/shared/ui";
import {
  useFetchInstance,
  useCreateServer,
  useFetchServer,
} from "@/features/add-server";

interface Instance {
  instanceName: string;
}

interface ServerData {
  serverId: number;
  serverName: string;
  cloudType: CloudType;
  instanceType: string;
  paymentType: CostType;
  cpu: string;
  memory: string;
  instanceStorage: string | null;
  networkBandwidth: string;
}

const COST_TYPE_MAPPING = {
  "온디멘드(시간당)": "ON",
  "1년(예약)": "ONE",
  "3년(예약)": "THREE",
} as const;

type CostTypeLabels = keyof typeof COST_TYPE_MAPPING;
type CostType = (typeof COST_TYPE_MAPPING)[CostTypeLabels];
type CloudType = "AWS" | "AZURE" | "GOOGLE";

export default function AddServer() {
  const [serverName, setServerName] = useState("");
  const [selectedCloud, setSelectedCloud] = useState<CloudType>("AWS");
  const [selectedInstance, setSelectedInstance] = useState("");
  const [selectedCostType, setSelectedCostType] = useState<CostType>("ON");

  const { data, error, isLoading } = useFetchInstance(selectedCloud);
  const { mutate: createServer } = useCreateServer();
  const {
    data: serverList,
    isLoading: isServerLoading,
    refetch: refetchServers,
  } = useFetchServer();

  const instanceOptions = Array.isArray(data)
    ? data.map((instance: Instance) => instance.instanceName)
    : [];

  const handleCloudChange = (cloudType: CloudType) => {
    setSelectedCloud(cloudType);
  };

  const handleInstanceChange = (selectedValue: string) => {
    setSelectedInstance(selectedValue);
  };

  const handleCostTypeChange = (selectedLabel: string) => {
    const costType = COST_TYPE_MAPPING[selectedLabel as CostTypeLabels];
    setSelectedCostType(costType);
  };

  const handleServerNameChange = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setServerName(event.target.value);
  };

  const handleCreateServer = () => {
    if (!serverName.trim()) {
      alert("서버 이름을 입력해주세요.");
      return;
    }

    if (!selectedInstance) {
      alert("인스턴스 종류를 선택해주세요.");
      return;
    }

    if (!selectedCloud) {
      alert("클라우드 종류를 선택해주세요.");
      return;
    }

    if (!selectedCostType) {
      alert("결제 방식을 선택해주세요.");
      return;
    }

    const serverData: ServerData = {
      serverId: 0,
      serverName: serverName.trim(),
      cloudType: selectedCloud,
      instanceType: selectedInstance,
      paymentType: selectedCostType,
      cpu: "",
      memory: "",
      instanceStorage: "",
      networkBandwidth: "",
    };

    createServer(serverData, {
      onSuccess: () => {
        alert("서버가 성공적으로 생성되었습니다.");
        setServerName("");
        setSelectedCloud("AWS");
        setSelectedInstance("");
        setSelectedCostType("ON");

        refetchServers();
      },
      onError: (error) => {
        alert("서버 생성 중 오류가 발생했습니다.");
        console.error("Error creating server:", error);
      },
    });
  };

  const serverData = Array.isArray(serverList)
    ? serverList.map((server) => ({
        serverId: server.serverId,
        serverName: server.serverName,
        cloudIcon: `/images/${server.cloudType}.png`,
        cloudType: server.cloudType,
        instanceSize: server.instanceType,
        vCPU: server.cpu,
        memory: server.memory,
        storage: server.instanceStorage || "-",
        networkBandwidth: server.networkBandwidth,
        paymentPlan: server.paymentType,
        refetchServers,
      }))
    : [];

  return (
    <div className="flex h-full w-full">
      <div className="flex h-full w-full flex-col gap-6 p-20">
        <Title size="l">서버 설정</Title>
        <div className="flex h-full w-full flex-col gap-6">
          <section className="flex h-full w-full flex-col gap-6 rounded-5 border border-gray-200 bg-white p-10">
            <section className="flex h-full w-full gap-6">
              <div className="flex w-1/3 rounded-lg bg-white p-4">
                <div className="w-full p-20">
                  <Input
                    placeholder="내용을 입력하세요."
                    showStar={false}
                    showButton={false}
                    label="서버 이름"
                    value={serverName}
                    onChange={handleServerNameChange}
                  />
                </div>
              </div>
              <div className="flex h-full w-1/3 rounded-lg">
                <div className="flex w-full p-20">
                  <CloudOptions onCloudChange={handleCloudChange} />
                </div>
              </div>
              <div className="flex w-1/3 rounded-lg">
                <div className="flex w-full flex-col gap-20 p-20">
                  <div className="flex w-full flex-col gap-6">
                    <h1>인스턴스 종류</h1>
                    <AddDropDownBox
                      options={instanceOptions}
                      onChange={handleInstanceChange}
                      value={selectedInstance}
                    />
                  </div>
                  <div className="flex w-full flex-col gap-6">
                    <h1>결제 방식</h1>
                    <AddDropDownBox
                      options={Object.keys(COST_TYPE_MAPPING)}
                      onChange={handleCostTypeChange}
                      value={selectedCostType}
                    />
                  </div>
                </div>
              </div>
            </section>
            <section className="flex h-1/6 w-full gap-6">
              <div className="flex w-full rounded-lg bg-white">
                <Button
                  size="l"
                  variant="primary"
                  design="fill"
                  mainText="서버 추가하기"
                  type="button"
                  onClick={handleCreateServer}
                />
              </div>
            </section>
          </section>
          <section className="flex h-full w-full">
            <div className="flex w-full rounded-5 border border-gray-200 bg-white p-20">
              <TableContainer
                servers={serverData}
                isLoading={isServerLoading}
                refetchServers={refetchServers}
              />
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}
