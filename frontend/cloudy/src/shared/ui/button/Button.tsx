import { ReactNode } from "react";

type Variant = "primary" | "secondary" | "tertiary";
type Design = "fill" | "outline";
export type ClickType = "submit" | "button";
type Size = "l" | "m" | "s";

type StyleBase = {
  base: string;
  hover: string;
  disabled: string;
};

type DesignStyles = {
  fill: StyleBase;
  outline?: StyleBase;
};

type VariantStyles = {
  [key in Variant]: {
    design: DesignStyles;
  };
};

interface ButtonProps {
  size: Size;
  variant: Variant;
  design: Design;
  mainText?: string;
  leftSubText?: string;
  rightSubText?: string;
  leftIcon?: string;
  rightIcon?: string;
  disabled?: boolean;
  type: ClickType;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

export const Button = ({
  size,
  variant,
  design,
  mainText,
  leftSubText,
  rightSubText,
  leftIcon,
  rightIcon,
  disabled = false,
  type,
  onClick,
}: ButtonProps) => {
  const styles: {
    base: string;
    size: Record<Size, string>;
    variant: VariantStyles;
  } = {
    base: "flex items-center justify-center w-full gap-8 rounded-md px-16 py-12",

    size: {
      l: "h-48 text-lg",
      m: "h-40 text-base",
      s: "h-36 text-sm",
    },

    variant: {
      primary: {
        design: {
          fill: {
            base: "bg-indigo-500 text-white",
            hover: "hover:bg-indigo-700",
            disabled: "disabled:bg-gray-200",
          },
          outline: {
            base: "border-2 border-indigo-500 text-indigo-500",
            hover:
              "hover:border-2 hover:border-indigo-700 hover:text-indigo-700",
            disabled: "disabled:border-gray-200 disabled:text-gray-200",
          },
        },
      },
      secondary: {
        design: {
          fill: {
            base: "bg-gray-500 text-white",
            hover: "hover:bg-gray-700",
            disabled: "disabled:bg-gray-200 disabled:text-gray-400",
          },
        },
      },
      tertiary: {
        design: {
          fill: {
            base: "text-indigo-500",
            hover: "hover:text-indigo-700",
            disabled: "disabled:text-gray-200",
          },
        },
      },
    },
  };

  // primary일 때만 outline이 존재하므로 secondary, tertiary일 때는 fill이 default가 되도록 설정
  const isValidDesign = (variant: Variant, design: Design): boolean => {
    return !!styles.variant[variant].design[design];
  };

  if (!isValidDesign(variant, design)) {
    design = "fill";
  }

  const designStyles = styles.variant[variant].design;
  const currentDesign = designStyles[design] as StyleBase;

  const classes = [
    styles.base,
    styles.size[size],
    currentDesign.base,
    currentDesign.hover,
    disabled && currentDesign.disabled,
  ]
    .filter(Boolean)
    .join(" ");

  return (
    <button
      type={type}
      disabled={disabled}
      onClick={onClick}
      className={classes}
    >
      {leftIcon && (
        <span className="material-symbols-outlined">{leftIcon}</span>
      )}
      {leftSubText && <span>{leftSubText}</span>}
      {mainText && (
        <span className="whitespace-nowrap font-semibold">{mainText}</span>
      )}
      {rightSubText && <span>{rightSubText}</span>}
      {rightIcon && (
        <span className="material-symbols-outlined">{rightIcon}</span>
      )}
    </button>
  );
};
