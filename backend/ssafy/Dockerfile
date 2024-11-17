# 베이스 이미지
FROM mdernst/cf-ubuntu-jdk21

# 작업 디렉토리
WORKDIR /app

# 빌드된 JAR 파일을 컨테이너에 복사
COPY build/libs/*.jar /app/app.jar

# 8081 포트 노출
EXPOSE 8084

#애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]