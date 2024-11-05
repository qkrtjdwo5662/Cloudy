# ☁️ ClOUDY

## Convention

### Branch Types and Naming

**마스터 브랜치(Master branch)**

- 설명: 배포 가능한 코드만을 유지

**개발 브랜치(Develop branch)**

- 명명 방식: `be/develop` `fe/develop`
- 설명: 새로운 기능을 개발하고 버그를 수정하는 작업이 이루어지는 중간 통합 브랜치로, 다음 배포를 위한 준비가 진행

**기능 브랜치(Feature branch)**

- 명명 방식: `feature/A606-<jira-issue-no.>/<feature-name>`
- 설명: 새로운 기능을 개발하는 데 사용되며 기능 이름은 간단하고 설명적

### Commit Type and Message

`<commit-type>: <Content>`

`feat`: 새로운 기능을 추가할 때

`fix`: 버그를 수정했을 때

`style`: 코드의 스타일을 변경했지만 로직에는 영향을 주지 않을 때

`chore`: 빌드 과정이나 보조 도구 및 라이브러리에 변경사항이 있을 때

`docs`: 문서만 변경했을 때

## Git Flow

![git flow](https://github.com/user-attachments/assets/a5d3a73b-8123-42d0-99b5-d1e4bd5713b8)
