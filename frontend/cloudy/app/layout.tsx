"use client";
import type { Metadata } from "next";
import "../src/shared/globals.css";
import localFont from "next/font/local";
import { NavigationBox } from "@/shared/ui";
import { usePathname } from "next/navigation";

// export const metadata: Metadata = {
//   title: "Cloudy",
//   description: "Cloudy",
// };

const pretendard = localFont({
  src: "../src/app/fonts/PretendardVariable.woff2",
  display: "swap",
  weight: "45 920",
});

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const pathname = usePathname();
  const hideNavbar = pathname === "/join" || pathname === "/signin";

  return (
    <html lang="en">
      <head>
        <link
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
          rel="stylesheet"
        />
      </head>
      <body className={`${pretendard.className}`}>
        <div className="flex h-full gap-20 bg-gray-100 pr-20">
          {!hideNavbar && (
            <aside className="flex w-300 flex-col bg-white">
              <NavigationBox />
            </aside>
          )}
          <div className="flex h-full w-full flex-col gap-10 pb-20">
            {children}
          </div>
        </div>
      </body>
    </html>
  );
}
