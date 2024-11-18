import { NextRequest, NextResponse } from "next/server";

export async function GET(req: NextRequest) {
  const { searchParams } = new URL(req.url);
  const accessToken = searchParams.get("token");

  if (!accessToken) {
    return NextResponse.json({ error: "Unauthorized" }, { status: 401 });
  }

  const backendUrl = "http://k11a606.p.ssafy.io:8081/alarms/subscribe";

  const response = await fetch(backendUrl, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  if (!response.body) {
    return NextResponse.json(
      { error: "Failed to connect to SSE" },
      { status: 500 },
    );
  }

  const readableStream = new ReadableStream({
    async start(controller) {
      const reader = response.body!.getReader();
      const textDecoder = new TextDecoder("utf-8");

      try {
        while (true) {
          const { done, value } = await reader.read();
          if (done) break;

          // Uint8Array를 텍스트로 변환
          const decodedValue = textDecoder.decode(value, { stream: true });
          controller.enqueue(decodedValue);
          console.log("Decoded Value:", decodedValue); // 디코딩된 문자열 출력
        }
      } finally {
        reader.releaseLock();
        controller.close();
      }
    },
  });
  return new NextResponse(readableStream, {
    headers: {
      "Content-Type": "text/event-stream",
      "Cache-Control": "no-cache",
      Connection: "keep-alive",
    },
  });
}
