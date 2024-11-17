import { ReactNode } from "react";

type Size = "l" | "m" | "s";
type Color = "indigo" | "red" | "yellow" | "gray";
type Design = "fill" | "outline";

interface ChipsProps {
  size: Size;
  color: Color;
  design?: Design;
  children: ReactNode;
  disabled?: boolean;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

export const Chips = ({
  size,
  color,
  design = "fill",
  children,
  disabled = false,
  onClick,
}: ChipsProps) => {
  const styles = {
    base: "flex items-center justify-center rounded-full transition-colors duration-200",
    size: {
      l: "w-62 h-30 text-m",
      m: "w-54 h-26 text-13",
      s: "w-46 h-22 text-xs",
    },
    color: {
      indigo: {
        base: "border border-indigo-500 text-indigo-500",
        hover: "hover:bg-indigo-100",
        focus: "focus:bg-indigo-500 focus:text-white",
      },
      red: {
        base: "border border-red-500 text-red-500",
        hover: "hover:bg-red-100",
        focus: "focus:bg-red-500 focus:text-white",
      },
      yellow: {
        base: "border border-amber-500 text-amber-500",
        hover: "hover:bg-amber-100",
        focus: "focus:bg-amber-500 focus:text-white",
      },
      gray: {
        base: "border border-gray-500 text-gray-500",
        hover: "hover:bg-gray-100",
        focus: "focus:bg-gray-500 focus:text-white",
      },
    },
  };

  const colorStyle = styles.color[color];
  const classes = [
    styles.base,
    styles.size[size],
    colorStyle.base,
    colorStyle.hover,
    colorStyle.focus,
  ].join(" ");

  return (
    <button
      className={classes}
      onClick={onClick}
      disabled={disabled}
      type="button"
    >
      {children}
    </button>
  );
};
