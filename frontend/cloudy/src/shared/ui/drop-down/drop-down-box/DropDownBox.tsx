"use client";

import { useState } from "react";
import { DropDownItem } from "../drop-down-item/DropDownItem";

export const DropDownBox = () => {
  const [selectedIndex, setSelectedIndex] = useState(0);

  const items = ["Dropdown Item 1", "Dropdown Ite 2", "Dropdown Ite 3"];

  return (
    <div className="flex w-full flex-col items-center justify-center gap-2 rounded-md p-10 shadow-2xl">
      {items.map((item, index) => (
        <DropDownItem
          key={index}
          isSelected={selectedIndex === index}
          onClick={() => setSelectedIndex(index)}
        >
          {item}
        </DropDownItem>
      ))}
    </div>
  );
};
