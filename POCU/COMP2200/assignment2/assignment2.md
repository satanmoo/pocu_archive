# Assignment2

## int translate(int argc, const char** argv);

## [매개변수]

### 구현

- 유효한 argc값
  - 옵션이 있는 경우 4
  - 옵션이 없으면 3
- argv로 두 문자 집합, 옵션을 받음
  - 옵션이 없으면 argv[1]과 argv[2]가 각각 두 문자 집합
  - 옵션이 있으면 argv[1]은 옵션, argv[2], argv[3]은 두 문자 집합
- argc == 4 인 경우(옵션이 있는 경우)
  - argv[1]은 반드시 "-i" 여야함, 아니면 ERROR_CODE_INVALID_FLAG
  - int strcmp(const char* lhs, const char* rhs)로 문자열 비교
  - int strncmp(const char* lhs, const char* rhs, size_t count)로 문자열 일부 비교

### 개념

- 커맨드 라인 인자
  - 프로그램 실행 시 전달하는 인자, main 함수의 매개변수(argc, argv)로 읽어올 수 있음
  - 메모리뷰
    - argv들이 공백을 기준으로 구분되어, 메모리에 통채로 저장
    - strtok의 동작 방식과 유사하게 공백을 구분자로 토큰화
- 문자열 비교

## [문자 집합 유효성 검증 및 처리]

## 길이 유효성 검증 및 char 배열로 복사

### 구현

- argv[1], argv[2]의 길이가 512를 넘으면 ERROR_CODE_ARGUMENT_TOO_LONG 오류 (argc == 4면 argv[2], argv[3]의 길이로 검사)
  - strlen(const char* str)로 문자열 길이 구해서 유효성 검사

## 1) 이스케이프 문자

### 구현

- 가장 먼저 적용되는 처리
- argv == 3 인 경우
  - SET1, SET2가 각각 argv[1], argv[2]
- argv == 4 인 경우
  - SET1, SET2가 각각 argv[2], argv[3]
- SET2 둘다 순회하면서 이스케이프 문자 확인하고,
  - 이스케이프 문자 바로 뒤가 허용하는 문자가 아니면 에러 발생(ERROR_CODE_INVALID_FORMAT)
    - 에러 코드를 out 매개변수로 넘기기
  - 허용하면 변형
    - 두 문자 합쳐서 새로운 문자 만들기
    - \n -> 아스키코드 10
- 순환 끝나면 마지막에 널문자 붙여야함!!!
- '\\'가 가장 끝에 나올 수 있을까? 불가능함
  - argv의 각 원소들은 기본적으로 널문자로 끝나기 때문이다. (C 스타일 문자열)

- \\: 92
- \': 39
- \": 34
- \a: 7
- \b: 8
- \t: 9
- \n: 10
- \v: 11
- \f: 12
- \r: 13
- 아스키 값이 순차적으로 1씩 증가함

### 개념

- 포인터가 out 매개변수로 사용될 경우 매개변수 이름 앞에 out_을 붙인다.
  - out 매개변수로 에러코드 반환 (정상 0)
- 문자열을 만들어줄 때 C 스타일 문자열로 만들어주는 것이 좋다!!!

### 오개념 기록

```c++
static char* parse_escape_sequences_or_null(char* output, const char* input, int* out_error_code)
{
    assert(out_error_code != NULL);

    int i = 0;
    while (*input != '\0') {
        if (*input == '\\') {
            char ch = *(input + 1);
            switch (ch) {
            case '\\':
                output[i++] = '\\';
                ++input;
                break;
            case 'a':
                output[i++] = '\a';
                ++input;
                break;
            case 'b':
                output[i++] = '\b';
                ++input;
                break;
            case 'f':
                output[i++] = '\f';
                ++input;
                break;
            case 'n':
                output[i++] = '\n';
                ++input;
                break;
            case 'r':
                output[i++] = '\r';
                ++input;
                break;
            case 't':
                output[i++] = '\t';
                ++input;
                break;
            case 'v':
                output[i++] = '\v';
                ++input;
                break;
            case '\'':
                output[i++] = '\'';
                ++input;
                break;
            case '\"':
                output[i++] = '\"';
                ++input;
                break;
            default:
                *out_error_code = ERROR_CODE_INVALID_FORMAT;
                return NULL;
            }
        } else {
            output[i++] = *input;
        }
        ++input;
    }
    *out_error_code = 0;

    return output;
}

char set1[512];

set1 = parse_escape_sequences_or_null(set1, argv[1], &error_code);
```

- 이렇게 불가능함
- set1은 `배열 변수`이기 때문에 대입이 불가능함
  - 주소의 대입이 불가능
  - 포인터와 차이

## 2) 범위

### 구현

- 두번째로 적용되는 처리
- SET1, SET2 둘다 확인
- 문자 집합을 처음부터 순회
  - 문자 집합 길이가 1인 경우 처리 필요 X
  - 2 이상인 경우 
    - flag = 1로 선언 (가장 처음의 '-'를 만나면 아스키 45로 인식하기 위함)
    - '-'가 아닌 문자를 만나면
      - flag가 1이면
        - flag를 0으로 변경
      - 넣기 전 남은 공간 확인(511 - 현재 인덱스 - 1) (널문자 포함해서 512자), 널문자를 제외하고 511개 공간이 있음
        - 남은 공간이 없으면 ERROR_CODE_ARGUMENT_TOO_LONG 오류
        - 남은 공간이 있으면 추가
    - flag가 0이고, '-'를 만나면, 이전 문자, 다음 문자로 범위를 구함
      - 다음 문자가 없는 경우, 즉 -가 마지막에 온 경우
        - '-' 를 아스키 45값으로 여기고
        - 넣기 전 남은 공간 확인
          - 남은 공간이 없으면 ERROR_CODE_ARGUMENT_TOO_LONG 오류
          - 남은 공간이 있으면 추가
      - 이전 문자가 다음 문자보다 크면 ERROR_CODE_INVALID_RANGE 오류
      - 범위를 구했는데, 범위 안에 허용되는 문자가 아닌 문자가 있는 경우는 입력되지 않음
      - 범위의 길이 (이전 문자, 다음 문자] 즉, 이전 문자는 이미 들어가있는 상태
        - 범위의 길이가 0인 경우 (y-y) 추가 안 함
        - 범위의 길이가 1 이상인 경우
          - 넣기 전 남은 공간 확인
          - 남은 공간이 없으면 ERROR_CODE_ARGUMENT_TOO_LONG 오류
          - 남은 공간이 있으면 추가
      - 범위를 성공적으로 추가했다면 ('-' 다음 문자도 들어갔음)
        - flag를 1로 변경
        - 다음 다음 문자로 넘어감
          - 다음 다음 문자가 널문자면 끝이니까 종료
    - flag가 1일 때, '-'를 만나면 그냥 아스키 45값 
      - flag를 0으로 변경
      - 넣기 전 남은 공간 확인
        - 남은 공간이 없으면 ERROR_CODE_ARGUMENT_TOO_LONG 오류
        - 남은 공간이 있으면 추가
    
- 허용되는 문자
  - 출력 가능한 문자: 32 ~ 126
  - 이스케이프 문자: 7 ~ 13, 34, 39, 92

### 개념

- 범위를 만들고, 버퍼의 남은 길이를 확인하고 범위를 버퍼에 대입하는 로직
  - 5주차 match_history의 구현에서 line, buffer에서 line을 만들고, line의 사이즈를 버퍼의 남은 사이즈와 비교하는 로직

## 2) 기초 동작

### 구현

- SET1에서 같은 문자가 두번 이상 지정된 경우
  - 마지막으로 등장한 위치를 기억하는 int 배열 선언
    - 아스키표에서 출력 가능한 문자의 개수 + 이스케이프 문자만큼 길이를 가짐
  - SET1 순회하면서 문자가 등장하면 int 배열에 등장한 위치 저장 (map 역할)
    - [4, 1, -1, 3]
  - 새로운 SET1을 구성하기 위해 char 배열을 선언, 이 때 길이는 5출력 가능한 문자의 개수 + 이스케이프 문자만큼
  - SET1 순회하면서, 문자의 색인이 int 배열의 값과 동일하면 마지막으로 나타난 문자라는 것이니까, 새로운 SET1에 추가
  - 추가할 때 마다 새로운 SET1의 인덱스 증가하고, 다음 값을 넣을 준비하기
- 두 문자 집합의 길이가 다르면
  - 1) SET1.len > SET2.len
    - SET2에서 SET1의 길이를 넘는 문자 제거
  - 2) SET1.len < SET2.len
    - SET2의 길이를 SET1의 길이로 맞춤
    - SET2의 마지막 문자로 채움

- 허용되는 문자
  - 출력 가능한 문자: 32 ~ 126
  - 이스케이프 문자: 7 ~ 13, 34, 39, 92

### 개념

- 배열을 map 처럼 사용하기
- 문자열의 중복 문자 제거(뒤에서 부터)
- 아스키 코드

## [변형 단계]

## 콘솔 입/출력

### 구현

- getchar(void), fgetc(stdin)으로 한 글자씩 읽어들임
- printf("%c", c), putchar(c), fputc(c, stdin)로 한 글자씩 출력
- 콘솔 입력 대신 input.txt, output.txt로 입출력

### 개념

- 한 글자씩 읽는 알고리즘
  - 입력이 문자/문자열 일 때 매우 좋다!!!
- IO redirection

## 대소문자 무시 플래그

### 구현

- 변형 단계에서 적용
- SET1, SET2에는 건드릴게 없음, 독립적인 규칙
- [변형]시 SET1, 대소문자 더블 체크

## [반환값]

### 구현

- 에러코드 반환

### 개념

- 열거형 <-> int 섞어서 사용가능
  - 정수에 별명을 붙이는 것
  - 대입 가능
  - int가 리턴이면 열거형을 리턴해도 됨