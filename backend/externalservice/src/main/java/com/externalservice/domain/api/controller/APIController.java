package com.externalservice.domain.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class APIController {

    @GetMapping("/get")
    public ResponseEntity<?> getAPI(){

        return ResponseEntity.ok("external get API");
    }

    @PostMapping("/post")
    public ResponseEntity<?> postAPI(){
        int size = 500; // 행렬 크기 (메모리 사용량 조절)

        double[][] matrixA = generateMatrix(size);
        double[][] matrixB = generateMatrix(size);
        double[][] resultMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return ResponseEntity.ok("external post API");
    }

    private static double[][] generateMatrix(int size) {
        Random random = new Random();
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

}
