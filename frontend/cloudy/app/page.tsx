import { redirect } from "next/navigation";
import "../src/shared/globals.css";

export default function Home() {
  redirect("/signin");
  return null;
}
