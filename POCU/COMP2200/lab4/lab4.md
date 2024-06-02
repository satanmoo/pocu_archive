# lab4

## void reverse(char* str);

- 새로운 배열을 만들지 않음

### 구현

- 문자열의 길이를 구함
  - 널문자 만날 때 까지 카운팅
- 인덱스 2개 활용해서 swap
- 빈 문자열이면 바로 리턴

### 사용 개념

- strlen의 동작 원리

### 테스트 케이스

- ""
- "@*$^!"

## int index_of(const char* str, const char* word);

### 구현

- 널문자를 만날 때 까지 순회
- word의 첫 글자와 일치하는지 확인
  - 일치하면 word의 길이만큼 순회하면서 일치하는지 확인
  - 일치하지 않으면 다음 인덱스로 이동
  - 모두 일치하면 시작 주소 반환
- 못 찾으면 -1을 반환
- str, word 모두 빈 문자열이면 리턴 0
- str만 빈 문자열이면 리턴 -1
- word만 빈 문자열이면 리턴 0

### 사용개념

- strlen의 동작 원리
- strstr의 동작 원리

### 테스트 케이스

- "abc", "" -> 0
- "", "abc" -> -1
- "abc", "abc" -> 0

## void reverse_by_words(char* str);

### 구현

- 공백을 만난 경우
  - 이미 공백이 아닌 문자를 만난 경우
    - 공백이 아닌 마지막 문자 위치 저장
    - 뒤집기
  - 공백이 아닌 문자를 만나지 못한 경우
    - 넘어가기
- 공백이 아닌 문자를 만난 경우
  - 이미 공백이 아닌 문자를 만난 경우
    - 지나감
  - 처음으로 공백이 아닌 문자를 만난 경우
    - 공백이 아닌 문자 위치 저장
- 빈 문자열이면 바로 return

### 사용개념

- static 함수로 파일 내부에서 사용하는 함수 만들기

### 테스트 케이스

- "" -> ""
- "abc" -> "cba"
- "   "
- "   abc" -> "   cba"
- "  ab  ab  ab  " -> "  ba  ba  ba  " 

## char* tokenize(char* str_or_null, const char* delims);

### 구현

- 처음에 delims 연속으로 나오면 건너 뛰기
  - ddd****d -> ****d
  - ddddd -> 전부 구분 문자면 return NULL
- static 변수(다음 주소)를 시작 주소로 설정하고 delims에 있는 문자, 널문자를 만날 때 까지 순회
  - 만나면 널문자로 바꾸고, static 변수에 다음 주소 저장, 시작 주소를 반환

- 매개변수에 따른 케이스
- static 변수가 NULL이 아닌 경우
  - 첫번째 인자로 NULL이 아닌 것이 들어온 경우, 덮어씀, 토큰화 진행
  - 첫번째 인자로 NULL이 들어온 경우, 토큰화 진행
- static 변수가 NULL인 경우
  - 첫번째 인자로 NULL이 아닌 것이 들어온 경우, 덮어씀, 토큰화 진행
  - 첫번째 인자로 NULL이 들어온 경우, NULL 반환

- 구분문자를 못 만난 경우 시작 주소 반환

### 사용개념

- 전역변수(static 변수)
- strtok의 동작 원리

### 테스트 케이스

토큰화 케이스(*는 문자, d는 delims, 0은 널문자)
delims: "dD"

- ""
  - NULL (d를 못찾음)
- "dddDD"
  - NULL (d 밖에 없음)
- "ddd****d" 
  - "****"
  - NULL (d 밖에 없음)
- "ddd****d****"
  - "****"
  - "****"
  - 다시 "***" 입력
    - "***"
    - NULL (널문자에서 찾아서 d 를 못찾음)
## char* reverse_tokenize(char* str_or_null, const char* delims);

### 구현

- tokenize와 동일한데, 토큰화된 문자열을 뒤집어서 반환
- tokenize와 문자열을 공유(static 변수 공유)

### 사용개념

- 전역변수(static 변수)
- strtok의 동작 원리
