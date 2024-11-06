import type { Metadata } from "next";
import "../src/shared/globals.css";
import { NavigationBox } from "@/shared/ui";

// export const metadata: Metadata = {
//   title: "Cloudy",
//   description: "Cloudy",
// };

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <head>
        <link
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
          rel="stylesheet"
        />
      </head>
      <body>
        <div className="flex h-full gap-20 bg-stone-100 pr-20">
          <aside className="flex w-300 flex-col border-r border-gray-200 bg-white">
            <NavigationBox />
          </aside>

          <div className="flex h-full w-full flex-col gap-10 pb-20">
            {children}
          </div>
        </div>
      </body>
    </html>
  );
}
