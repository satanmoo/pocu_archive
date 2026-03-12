# Assignment2

## int translate(int argc, const char** argv);

## [사용자 입력과 유효성 검사]

- 사용자 입력은 커맨드 라인 인자로 받음
- RAW DATA
- 커맨드 라인 인자의 수(argc)에 따라 유효성 검사

### 구현

- 유효한 argc값
  - 옵션이 있는 경우 4
  - 옵션이 없으면 3
- argv로 두 문자 집합, 옵션을 받음
  - 옵션이 없으면 argv[1]과 argv[2]가 각각 두 문자 집합
  - 옵션이 있으면 argv[1]은 옵션, argv[2], argv[3]은 두 문자 집합
- argc == 4 인 경우(옵션이 있는 경우)
  - argv[1]은 반드시 "-i" 여야함, 아니면 ERROR_CODE_INVALID_FLAG
  - int strcmp(const char* lhs, const char* rhs)로 argv[1]과 "-i" 비교

### 개념

- 커맨드 라인 인자
  - 프로그램 실행 시 전달하는 인자, main 함수의 매개변수(argc, argv)로 읽어올 수 있음
  - 메모리뷰
    - argv들이 공백을 기준으로 구분되어, 메모리에 통채로 저장
    - strtok의 동작 방식과 유사하게 공백을 구분자로 토큰화
- 문자열 비교 strcmp

### 테스트 케이스

- argc 1,2 또는 4보다 큰 수 넣기

## [사용자 입력을 문자 집합으로 변환 후 문자 집합의 유효성 검증]

- 문자 집합은 512자 이하의 문자열

### 구현

[기본 동작]

- 문자 집합 버퍼를 스택 메모리에 할당, char set1[512], char set2[512] (최대 길이 널문자 포함 512)
- 사용자 입력 argv를 순회하면서 변환 해서 문자 집합에 대입(복사) 

- argv[1], argv[2]의 길이가 512를 넘으면 ERROR_CODE_ARGUMENT_TOO_LONG 오류 (argc == 4면 argv[2], argv[3]의 길이로 검사)
  - strlen(const char* str)로 문자열 길이 구해서 유효성 검사

[이스케이프 문자 변환]

- 입력받은 argv를 순회하면서 '\\'를 만났을 때 다음 문자로 허용되는 이스케이프 문자를 만들 수 있으면 변환  
  - argv의 문자 2개 consume
  - char set에 이스케이프 문자 1개 produce
- 이스케이프 문자 바로 뒤가 허용하는 문자가 아니면 에러 발생(ERROR_CODE_INVALID_FORMAT)
- argv 순환 끝나면 char set 마지막에 널문자를 붙여 C스타일 문자열을 보장하기

- 참고로 '\\'가 argv의 가장 끝에 나올 수 있을까? 불가능함
  - argv의 각 원소들은 기본적으로 널문자로 끝나기 때문이다. (C 스타일 문자열을 보장함, 커맨드 라인 인자 메모리 뷰를 참고)

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

[범위 적용]

- 입력받은 argv를 순회하면서 '-'를 만났을 때 이전 문자와 다음 문자를 통해 범위를 구함
  - 이전 문자는 이미 char set에 복사됨
  - 다음 문자는 raw data인 argv에서 가져옴
- 범위가 유효한지 확인
  - 유효하면 char set으로 복사
    - 복사할 때 char set의 남은 공간 확인(511 - 현재 인덱스 - 1) (널문자 포함해서 512자), 널문자를 제외하고 511개 공간이 있음
      - 남은 공간이 부족하면 ERROR_CODE_ARGUMENT_TOO_LONG 오류
  - 유효하지 않으면 ERROR_CODE_INVALID_RANGE 오류
  - 이전 문자가 다음 문자보다 크면 ERROR_CODE_INVALID_RANGE 오류
  - 범위를 구했는데, 범위 안에 허용되는 문자가 아닌 문자가 있는 경우는 입력되지 않음
- '-'를 범위 문자로 인식하지 않는 경우 고려
  - 붙임표가 문자 집합의 첫 번째
    - range_disabled = 1로 초기화 및 선언
  - 붙임표가 문자 집합의 마지막인 경우
    - '-' 다음이 널문자인 경우 성립
  - 붙임표 왼쪽의 문자가 이미 다른 범위의 문자로 사용된 경우
- 범위의 길이는 (이전 문자, 다음 문자] 즉, '-' 이전 문자는 이미 char set에 복사됨
  - 범위의 길이가 0인 경우 (y-y) 추가 안 함
  - 범위를 성공적으로 추가했다면 다음 다음 문자로 넘어감

- 허용되는 문자
  - 출력 가능한 문자: 32 ~ 126
  - 이스케이프 문자: 7 ~ 13, 34, 39, 92

### 개념

- 범위를 만들고, 버퍼의 남은 길이를 확인하고 범위를 버퍼에 대입하는 로직
  - 5주차 match_history의 구현에서 line, buffer에서 line을 만들고, line의 사이즈를 버퍼의 남은 사이즈와 비교하는 로직
- 문자열 깊은 복사
- 깊은 복사로 새로운 문자열을 만들 때 C 스타일 문자열로 만들어주는 것이 좋다!!!

### 테스트 케이스

- "abc-", "a-e-f"
- -b-g, "z-z-z"
- 11111.... (508개) a-z, "aaa"
- "z-", "z-z"

## [변형 단계 기초 동작]

### 구현

- SET1, SET2는 parse(이스케이프 문자 처리, 범위 처리) 완료 상태 

- SET1에서 같은 문자가 두번 이상 지정된 경우
  - 마지막으로 등장한 위치를 저장하는 배열 선언, 모든 요소의 초기화값은 -1
  - SET1 순회하면서 문자가 등장하면 int 배열에 마지막으로 등장한 위치 저장 (key가 char, value가 char가 마지막으로 등장한 위치인 map)
  - 예를 들어 SET1: "abada", SET2: "ijkbc"
  - b - 1 - j
  - d - 3 - b
  - a - 4 - c
  - SET2를 순회하면서, 문자를 만나면 그 문자의 위치를 통해 map에서 대응되는 문자를 찾아서 변경
- 두 문자 집합의 길이가 다르면
  - SET1의 길이가 SET2의 길이보다 짧은 경우 문제 없음
    - 예를 들어 SET1: "aaa", SET2: "xbzxcqp"
    - a - 2 - z
  - SET1의 길이가 SET2의 길이보다 길면
    - 예를 들어 SET1: "abadaxyz", SET2: "ijkbc"
    - b - 1 - j
    - d - 3 - b
    - a - 4 - c
    - x - 5 - ?
    - y - 6 - ?
    - z - 7 - ?
    - 5,6,7은 모두 strlen(SET2)보다 크다, 이를 이용해서 SET2의 마지막 문자로 대응할 수 있음
    - 예를 들어 SET1: "aabbb", SET2: "xb"
    - a - 1 - b
    - b - 4 - b

### 개념

- 배열을 map 처럼 사용하기
- memset으로 배열의 값 초기화

### 테스트 케이스

- "abc", "ab" -> "abc", "abb"
- "ab", "fgh" -> "ab", "fg"

## [변형 단계 대소문자 무시 플래그]

### 구현

- 변형 시 대문자인지 확인
  - 대문자라면, last_occurence 배열 확인 ('A' + 32)로 소문자로 변환 후 기초 동작

### 개념

- 배열을 map 처럼 사용
- 대문자와 소문자의 아스키 코드 값의 관계

### 테스트 케이스

- "abcABC", "ABC" -> "abcabc", "ABCCCC"

## [콘솔 입/출력]

### 구현

- getchar(void), fgetc(stdin)으로 한 글자씩 읽어들임
- printf("%c", c), putchar(c), fputc(c, stdin)로 한 글자씩 출력
- 콘솔 입력 대신 input.txt, output.txt로 입출력

### 개념

- 한 글자씩 읽는 알고리즘
  - 입력이 문자/문자열 일 때 매우 좋다!!!
- IO redirection

## [반환값]

### 구현

- 에러코드 반환

### 개념

- 열거형 <-> int 섞어서 사용가능
  - 정수에 별명을 붙이는 것
  - 대입 가능
  - int가 리턴이면 열거형을 리턴해도 됨

## 오개념 기록

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