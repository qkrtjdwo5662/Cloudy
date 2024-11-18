
## 1. Gitlab 소스 클론 이후 빌드 및 배포할 수 있도록 정리한 문서
1) 사용한 JVM, 웹서버, WAS 제품 등의 종류와 설정 값, 버전(IDE버전 포함) 기재:
    - **JVM:** OpenJDK 21
    - **웹서버:** Nginx 1.27.1
    - **WAS:** Spring Boot 3.3.3
    - **IDE:** IntelliJ IDEA 2023.1.1
2) 빌드 시 사용되는 환경 변수 등의 내용 상세 기재:
    - **빌드 도구:** Gradle
    - **환경 변수:**
3) 배포 시 특이사항 기재:
    - **배포 방식:** Docker Compose를 이용한 컨테이너 기반 배포
    - **특이사항:** 도커 네트워크는 `cloudy`로 설정
4) DB 접속 정보 등 프로젝트(ERD)에 활용되는 주요 계정 및 프로퍼티가 정의된 파일 목록:
    - **DB 접속 정보:**
        - URL: `jdbc:mysql://mysql:3306/cloudy`
        - User: `cloudy`
        - Password: `cloudy1234@!`

