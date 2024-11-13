package com.cloudy.global.util;


public final class DockerStatsParser {

    // CPU %에서 숫자만 추출하여 double로 변환
    public static double parseCpuUsage(String cpu) {
        try {
            return Double.parseDouble(cpu.replace("%", ""));
        } catch (NumberFormatException e) {
            return 0.0; // 에러 발생 시 0으로 반환
        }
    }

    // Memory Usage나 Memory Limit에서 숫자와 단위(MiB, GiB 등)만 추출하여 double로 변환
    public static double parseMemory(String memory) {
        try {
            double value = Double.parseDouble(memory.replaceAll("[^0-9.]", ""));
            if (memory.contains("GiB")) {
                return value * 1024; // GiB를 MB로 변환
            } else if (memory.contains("MiB")) {
                return value; // 이미 MiB 단위이므로 그대로 사용
            } else {
                return value; // 기본적으로 MB 단위로 처리
            }
        } catch (NumberFormatException e) {
            return 0.0; // 에러 발생 시 0으로 반환
        }
    }
}
