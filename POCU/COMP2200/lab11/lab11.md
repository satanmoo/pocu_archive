# Lab11

## common

- RELEASE: 릴리즈 모드를 표시하는 메크로
  - #define RELEASE (1)
  - `-DREALES` 컴파일러 플래그

## user_t 구조체를 구현

### 구현

- user.h 파일 안에 구현
- 문자열 멤버는 아래 요구 사항 만족
  - 50자 제한
  - 메모리 할당 필요 없음
  - stack 메모리를 이용하는 char 배열로 저장

## get_user_by_id_or_null() 구현

- 매개변수
  - user_t** users_or_null
    - 사용자의 목록
  - unsigned int id
    - 사용자의 id

- 동작
  - 사용자 ID가 id인 사용자를 찾아, 그 포인터를 반환

- 가정
  - id는 중복될 수 없음

- edge case
  - users_or_null이 NULL인 경우 NULL 반환
  - ID값이 매개변수 id인 사용자가 없는 경우 NULL 반환

### 구현

- data_store.h에 선언 구현
- data_store.c에 정의 구현
- 순회
  - 원소 개수를 모름
    - 마지막 원소가 NULL임을 이용해 간접적으로 원소 개수를 알 수 있음
- 값 비교
- 시간 복잡도 O(N)

### 개념

- 배열의 끝을 나타내는 법

## get_user_by_username_or_null() 구현

- 매개변수
  - user_t** users_or_null
    - 사용자의 목록
  - const char* username
    - 사용자 계정의 이름

- 동작
  - 사용자 계정 이름이 username인 사용자를 찾아 그 포인터를 반환

- 가정
  - email 중복될 수 없음

- edge case
  - users_or_null이 NULL인 경우 NULL 반환
  - 매개변수 username과 동일한 사용자 계정 이름을 가진 사용자가 없는 경우 NULL 반환

### 구현

- data_store.h에 선언 구현
- data_store.c에 정의 구현
- 순회
  - 원소 개수를 모름
    - 마지막 원소가 NULL임을 이용해 간접적으로 원소 개수를 알 수 있음
- 값 비교
  - 문자열 비교
- 시간 복잡도 O(N)

### 개념

- 배열의 끝을 나타내는 법
- 문자열 비교 

## update_email() 구현

- 매개변수
  - user_t** users_or_null
    - 사용자의 목록
  - unsigned int id
    - 사용자의 id
  - const char* email
    - 새로운 이메일 주소

- 동작
  - 입력받은 매개변수 id 값을 갖는 유저를 찾아 이메일을 업데이트
  - 로그 메시지 기록

- 가정
  - email은 유효한 이메일 주소, 형식을 가짐
  - 문자열 최대 길이 50자

- edge case
  - users_or_null이 NULL인 경우 false 반환
    - 업데이트 실패의 개념
  - 입력받은 매개변수 id 값을 갖는 유저를 못 찾은 경우 false 반환
    - 업데이트 실패의 개념

### 구현

- data_store.h에 선언 구현
- data_store.c에 정의 구현
- get_user_by_id_or_null() 호출
  - 매개변수로 id 값 넘김
  - 유저 찾음
- 결과 유저값이 NULL 이면 false 반환
  - users_or_null이 NULL인 경우
  - 입력받은 매개변수 id 값을 갖는 유저를 못 찾은 경우
- 결과 유저값이 NULL이 아닌 경우
  - 이메일 주소 업데이트
    - 문자열 깊은 복사
      - sprintf
      - strcpy
  - 로그 메시지 생성
    - 조건부 컴파일
      - 디버그 모드
        - 그대로
      - 릴리즈 모드
        - 문자열 수정 함수 호출
  - 로그 출력 함수 호출

### 개념

- 조건부 컴파일
  - #ifdef와 #ifndef는 괄호를 사용하지 않음
  - #if defined(), #if !defined()는 괄호를 사용함
  - #endif로 끝나야함

## update_password()

- 아래 항목을 제외하고 update_email과 동일
  - 매개변수에 수정할 패스워드
  - 패스워드 수정 동작
  - 로그 메시지 내용

### 참고 자료

https://www.cloudflare.com/ko-kr/learning/email-security/what-is-email/

## 문자열 수정 함수

- 매개변수
  - char* str
    - 수정할 문자열
  - size_t len
    - 문자열의 길이
    - 문자열에서 원하는 `길이까지` 수정하기 위함
      - 이메일의 경우 이메일 문자열 전체를 수정하기 보다, 이메일으 로컬 파트만 수정하면 됨

- 동작
  - 숨길 문자열의 길이가 1인 경우, 그 문자열 안에 있는 모든 문자를 *로 수정
  - 길이가 2인 경우, 마지막 문자를 *로 수정
  - 길이가 3 이상인 경우, 첫 번째와 마지막 문자를 제외한 모든 문자를 *로 수정

- 구현
  - 문자열을 규칙에 따라 수정하고 그대로 반환

## 로그 출력 함수

- 매개변수
  - const char* message
    - 로그 메시지

- 동작
  - log.txt 파일에 로그 메시지 출력
  - 이미 파일이 존재할 경우 파일의 제일 뒤에 로그 메시지를 덧붙여 씀

### 구현

- 파일 스트림 오픈
  - append 모드
- 로그 메시지 파일에 출력
  - fwrite
  - fprintf
- 파일 스트림 close

## 오개념 체크

- static 함수의 정의는 헤더파일에 포함하면 안 됨
  - static 함수는 파일 안에서만 사용한다는 개념을 포함함
  - 헤더 파일에 static 함수의 선언이 있어도, 헤더 파일을 인클루드 하는 외부 파일에서 사용할 수 없음

- 즉 static 함수의 선언과 정의는 모두 `.c` 파일 안에
- 외부에 노출할 함수의 경우 선언을 `.h` 파일에

## ETC

### 로그가 필요한 이유

- 라이브 환경(Production)에서는 디버거를 연결할 수 없음
    - 따라서 프로그램에서 발생하는 오류나 이벤트를 기록
    - 이 기록을 `로그`라고 정의함

### 로그 예시

- 예를 들면 사용자가 이메일 주소를 업데이트할 때마다 다음과 같은 로그를 남길 수 있습니다.

```shell
TRACE: User xxx updated email from "oldemail@email.com" to "newemail@email.com"
```

- 라이브 환경(릴리즈 모드)의 경우 이메일 같이 고객 정보는 숨기는게 좋음
    - 따라서 로그파일에 이메일 주소를 저장할 때, 'oldemail@email.com' 대신 'o******l@email.com'로 바꾸어 저장하는 게 좋음
- 디버그 환경(디버그 모드)의 경우 보통 가짜 사용자 정보를 사용하니 숨길 필요 없음

### 비밀번호

- 비밀번호를 그대로 문자열로 저장하는 것은 절대 하지 말아야 할 일임!
- 보안 상 이유로 보통 비밀번호를 저장할 때는 비밀번호 대신 해시 값을 저장함