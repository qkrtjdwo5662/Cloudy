"use client";
import React, { useState } from "react";

interface CloudOptionCardProps {
  label: string;
  icon: string;
  value: string;
  selectedValue: string;
  onChange: (value: string) => void;
}

function CloudOptionCard({
  label,
  icon,
  value,
  selectedValue,
  onChange,
}: CloudOptionCardProps) {
  const isSelected = selectedValue === value;

  return (
    <div
      className={`flex cursor-pointer flex-col items-center justify-center gap-10 rounded-lg border p-10 ${
        isSelected ? "border-blue-500" : "border-gray-200"
      }`}
      onClick={() => onChange(value)}
    >
      <img src={icon} alt={`${label} logo`} className="h-full w-full p-10" />
      <div className="flex items-center">
        <input
          type="radio"
          name="cloud-option"
          value={value}
          checked={isSelected}
          onChange={() => onChange(value)}
          className="mr-2"
        />
        <label className="text-sm">{label}</label>
      </div>
    </div>
  );
}

export function CloudOptions() {
  const [selectedCloud, setSelectedCloud] = useState("aws");

  const options = [
    { label: "aws", icon: "/images/AWS.png", value: "aws" },
    { label: "azure", icon: "/images/Azure.png", value: "azure" },
    { label: "google", icon: "/images/Google.png", value: "google" },
  ];

  return (
    <div className="flex w-full flex-col items-start gap-10">
      <h1 className="text-g mb-4">클라우드 종류</h1>
      <div className="flex gap-4">
        {options.map((option) => (
          <CloudOptionCard
            key={option.value}
            label={option.label}
            icon={option.icon}
            value={option.value}
            selectedValue={selectedCloud}
            onChange={setSelectedCloud}
          />
        ))}
      </div>
    </div>
  );
}

export default CloudOptions;
