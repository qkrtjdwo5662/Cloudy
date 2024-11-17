"use client";

interface ServerOptionProps {
  title: string;
  description: string;
  link: string;
}

export default function ServerOption({
  title,
  description,
  link,
}: ServerOptionProps) {
  return (
    <div className="px-4 py-4">
      <a href={link} target="_blank" rel="noopener noreferrer">
        <div
          className="each mb-3 flex cursor-pointer select-none rounded-md border border-gray-300 p-6 hover:border-gray-500 hover:shadow-lg"
          style={{ maxWidth: "296px" }}
        >
          <div className="left ml-4 flex-1 overflow-hidden p-2">
            <div
              className="header text-sm font-semibold text-indigo-500"
              style={{
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
                maxWidth: "256px",
              }}
            >
              {title}
            </div>
            <div
              className="desc text-xs text-gray-600"
              style={{
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
                maxWidth: "256px",
              }}
            >
              {description}
            </div>
          </div>
        </div>
      </a>
    </div>
  );
}
