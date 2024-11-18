import { ReactNode } from "react";

interface DropDownItemProps {
  onClick?: (e: React.MouseEvent<HTMLDivElement>) => void;
  children: ReactNode;
  isSelected?: boolean;
}

export const DropDownItem = ({
  onClick,
  children,
  isSelected,
}: DropDownItemProps) => {
  return (
    <div
      className={`flex w-full cursor-pointer items-center justify-center whitespace-nowrap rounded-4 px-57 py-8 transition-colors ${isSelected ? "bg-gray-200" : "hover:bg-gray-100"}`}
      onClick={onClick}
    >
      {children}
    </div>
  );
};
