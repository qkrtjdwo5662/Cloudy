"use client";
import { useState, useRef, useEffect } from "react";
import { DropDownItem } from "@/shared/ui/drop-down/drop-down-item/DropDownItem";
import { Button } from "@/shared/ui/button/Button";

interface AddDropDownBoxProps {
  options: string[];
  onChange: (value: string) => void;
  value?: string;
}

export const AddDropDownBox = ({
  options,
  onChange,
  value,
}: AddDropDownBoxProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedIndex, setSelectedIndex] = useState(
    value ? options.indexOf(value) : -1,
  );
  const dropdownRef = useRef<HTMLDivElement>(null);

  const selectedItem =
    selectedIndex === -1 ? "선택하세요" : options[selectedIndex];

  const toggleDropdown = () => setIsOpen((prev) => !prev);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(event.target as Node)
      ) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleItemClick = (index: number) => {
    setSelectedIndex(index);
    setIsOpen(false);
    onChange(options[index]);
  };

  return (
    <div
      ref={dropdownRef}
      className="relative w-full flex-col items-center justify-center gap-2 rounded-md border border-gray-200 p-4"
    >
      <Button
        size="m"
        variant="tertiary"
        design="fill"
        mainText={selectedItem}
        type="button"
        onClick={toggleDropdown}
      />
      {isOpen && (
        <div className="absolute top-full z-10 mt-2 w-full rounded-md bg-white shadow-lg">
          <div className="max-h-200 overflow-y-auto">
            {options.map((item, index) => (
              <DropDownItem
                key={index}
                isSelected={selectedIndex === index}
                onClick={() => handleItemClick(index)}
              >
                {item}
              </DropDownItem>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};
