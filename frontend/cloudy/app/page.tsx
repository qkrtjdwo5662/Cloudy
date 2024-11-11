import { redirect } from "next/navigation";
import "../src/shared/globals.css";
import setupInterceptors from "@/shared/api/interceptor";

export default function Home() {
  setupInterceptors();
  redirect("/signin");
  return null;
}
