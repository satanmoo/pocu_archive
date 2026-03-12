# Week4

## 요약

### [데이터 섹션의 문자열의 길이]

- char* 에 문자열을 대입하면, 데이터 섹션의 주소가 대입됨
- 데이터 섹션의 문자열도 C-style 문자열을 보장
- 널 문자의 값은 0, int로 출력하면 0, char로 출력하면 널문자(사람이 읽을 수 없는 이상한 모양)

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    char* str = "abc";

    printf("%zd\n", strlen(str));
    printf("%d", *(str + 3));

    return 0;
}
>> 출력
3
0
```

### [strcpy의 위험성]

- char *strcpy(char *dest, const char *src);

```c++
#include <string.h>

int main(void)
{
    char str1[4];
    const char* str2 = "abcDE";

    strcpy(str1, str2);

    return 0;
}

```

- str1에 4바이트 공간이 있음
- str2를 str1에 복사하면, 4바이트를 넘어감
- 소유하지 않은 메모리에 접근하게 됨

### [안전한 strncpy]

```c++
#include <string.h>

#define DEST_LENGTH (5)

int main(void)
{
    char dest[DEST_LENGTH + 1];
    const char* src = "abcdefg";

    strncpy(dest, src, DEST_LENGTH);    /* dest: {'a','b','c','d','e',?}*/
    dest[DEST_LENGTH] = '\0';   /* dest: {'a','b','c','d','e','\0'}*/

    return 0;
}

```

- char *strncpy(char *dest, const char *src, size_t count);
- 최대 count개 문자를 src에서 dest로 복사
  - src의 길이가 count 보다 작으면, 나머지를 널문자로 채워, count개 만큼 복사함
    - Ex) count 5, src = "abc"라면 {'a', 'b', 'c' ,'\0, '\0'}을 dest에 복사
  - src의 길이가 count 보다 크다면, dest에 널문자가 복사되지 않음
    - Ex) count 2, src = "abc"라면 {'a', 'b'}을 dest에 복사
    - 따라서 dest[2] = '\0';으로 dest가 c-style-string을 보장하기

### [암기사항]

- strcat, strncat: 널문자 보장
- strcpy: 널문자 보장
- strncpy: 널문자 보장X

### [strstr의 첫번째 인자에 스택 메모리의 문자열을 넘겨야하는 이유]

- char *strstr(const char *str, const char *substr);
- strstr의 반환값은 str에서 substr과 일치하는 문자열을 찾으면, str에서 겹치는 부분의 시작 주소를 반환함
- 즉 반환값은 char*라서 첫번째 인자로 입력한 문자열을 수정할 여지가 있음
- 만약 이 문자열에 char* p = "???";로 데이터 섹션의 문자열을 넘기면 수정할 수 없는 주소에 접근하게 됨

### [비트 마스킹을 이용한 대소문자 바꾸기]

- 0x20 == 32
- 대문자 |= 0x20 => 소문자
  - Ex) A[0100 0001(65)] => a[0110 0001(97)]
- 소문자 &= ~0x20 => 대문자
  - Ex) z[0111 1010(122)] => Z[0101 1010(90)]

### [서식 지정자]

- 시험 전 보고 들어가기
```c++
int num1 = 250;
float num2 = 250.123456f;

printf("%8X\n", num1);
printf("%8o\n", num1);
printf("%+8.2f\n", num2);
```

```shell
변환
250 >> 11111010 >> fa(hex) >> 011 111 010 >> 362(oct)

출력 (-는 공백)
------fa
-----362
-+250.12     
```

### [stdout 버퍼링]

- 라인 버퍼링
  - 버퍼가 꽉차면 비움
  - 버퍼에 \n이 들어오면 비움
  - fflush(stdout); 호출하면 비움

### [puts(const char* str) vs fputs(const char* str, FILE* stream)]

- puts는 '\n'을 넣어줌
- fpust는 아님
  
## 코딩문제

### [문제1] 실행된다면 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[4] = {'A', 'B'};

    printf("%zd\n", strlen(str));
    printf("%d", *(str + 2));

    return 0;
}

```

- char 배열에 {}로 배열을 선언과 동시에 초기화 하면, 배열의 규칙대로 대입됨
    - str[0] = 'A'
    - str[1] = 'B'
    - str[2] = 0
    - str[3] = 0

- 출력

```shell
2
0
```

### [문제2] 위 코드가 안전한가? 안전하다면 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[2] = {'A', 'B'};

    printf("%zd\n", strlen(str));
    printf("%d", *(str + 2));

    return 0;
}

```

- 안전하지 않다.
- 배열을 선언과 동시에 초기화
    - str[0] = 'A'
    - str[1] = 'B'
- str[2]에 널문자(0)이 들어오는 것은 확실하지 않음
- strlen은 널문자를 만날 때 까지 순회하면서 길이를 구하는 함수라, 길이를 정확하게 구할 수 없음

### [문제3] 위 코드가 실행되나요? 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char str[] = "AB";
    char* p = "AB";

    printf("%zd\n", sizeof(str));
    printf("%d", sizeof(p));

    return 0;
}

```

- 지역 변수인 배열은 스택 메모리에 저장됨
  - 크기는 sizeof(char) * 3
  - {'A', 'B', '\0'}
- 지역 변수인 포인터는 데이터 섹션의 주소값을 가짐
  - 크기는 sizeof(char*)

- 출력

```shell
3
4
```

### [문제4] 위 코드가 실행되나요? 출력값은?

```c++
#include <stdio.h>

static void test(char arr[]);

int main(void)
{
    char str[] = "AB";

    test(str);

    return 0;
}

static void test(char arr[])
{
    char str[] = "12345";

    printf("%zd\n", sizeof(arr));
    printf("%zd\n", sizeof(str));
}

```

- 배열을 매개변수로 넘길 때, 첫번째 원소의 주소를 넘김
- 주소의 크기는 sizeof(char*)

- 출력

```shell
4
6
```

### [문제5] 문자열의 길이를 구하는 함수를 구현하기

```c++
size_t get_length(const char* str)
{
    const char* p = str;
    size_t len = 0;

    while (*p != '\0') {
        ++len;
        ++p;
    }

    return len;
}
```

- 반복문 테스트
- "ab";
  - a일 때
    - len 1증가
    - 다음으로
  - b일 때
    - len 1증가
    - 다음으로
  - 널문자 일 때
    - 반복문 out
- p는 널문자의 주소, len은 2
- size_t strlen( const char* str );

### [문제6] 두 문자열을 비교하는 함수를 구현

```c++
size_t comp(const char* a, const char* b)
{
    while (*a != '\0') {
        if (*a != *b) {
            break;
        }
        ++a;
        ++b;
    }

    return *a - *b;
}
```

- 첫번째 매개변수로 받은 문자열만 끝까지 순회하면 됨
  - 두 문자열 중 하나라도 끝까지 가면, 널문자와 비교해서 반드시 차이를 구할 수 있음
- 두 문자가 다르면, 그 차이를 반환
- int strcmp( const char* lhs, const char* rhs );

### [문제7] strncmp 구현

```c++
size_t comp(const char* a, const char* b, size_t n)
{
    size_t i = 0;

    while (i < n - 1 && *a != '\0') {   /* n = 3이면, 인덱스 0,1,2 까지 비교 */
        if (*a != *b) {
            break;
        }
        ++a;
        ++b;
        ++i;
    }

    return *a - *b;
}
```

- i의 범위를 조심
- while 문에서는 n번째 문자 전까지 확인
  - 인덱스 [0, n-2]까지 확인
  - 확인 후 다음 문자(n-1번째)로 넘어감
- break 후 n-1번째 문자 확인
- n-1 까지 도달 못하는 경우는, strcmp와 동일
  - 두 문자가 다르면 비교, 다음으로 넘어가지 않음

### [문제8] strncat 구현

```c++
#include <stdio.h>
#include <string.h>

static void string_concat(char* dest, const char* src, size_t n);

int main(void)
{
    char dest[10] = "abcde";
    char* src = "1234";

    string_concat(dest, src, 2); /* 'a','b','c','d','e','1','2','\0' */
    printf("%s\n", dest);

    return 0;
}

static void string_concat(char* dest, const char* src, size_t n)
{
    size_t i = 0;

    while (*dest != '\0') {
        ++dest;
    }

    for (i = 0; i < n; ++i) {
        dest[i] = src[i];
    }
    dest[i] = '\0';
}
```

- srtncat, strcat 모두 붙이고 dest의 마지막에 `널문자 추가`해줌

### [중요❗] [문제9] 버퍼를 이용한 출력

- 버퍼가 꽉차면 출력하는 함수를 구현
- 버퍼의 크기는 32, 출력은 오직 버퍼가 꽉찼을 때
- 문자열을 입력 받아, 버퍼를 채움
  - 문자열의 길이가 버퍼보다 클 수도, 작을 수도 있음
- 여러 번 호출됨을 가정하자

```c++
#include <stdio.h>
#include <string.h>

#define BUFFER_LENGTH (32)

static size_t s_buffer_index = 0u;
static char s_buffer[BUFFER_LENGTH];

static void buffered_print(const char* src);

int main(void)
{
    buffered_print("Hello, ");             /* Hello, */
    buffered_print("World. ");             /* Hello, World. */
    buffered_print("C is awesome! ");      /* Hello, World. C is awesome! */
    buffered_print("C# is awesome too! "); /* Hello, World. C is awesome! C# */
    buffered_print("Is Java better? ");    /* is awesome too! Is Java better? */

    return 0;
}

static void buffered_print(const char* src)
{
    const char* p = src;
    size_t src_left = strlen(src);
    while (src_left > 0) {
        /* 버퍼에 남은 공간 확인 (마지막은 널문자) */
        size_t buffer_remaining = BUFFER_LENGTH - 1 - s_buffer_index;
        size_t copy_cnt = buffer_remaining;

        /* 두 길이 비교해서, 버퍼에 남은 공간이 충분하면 복사할 문자열의 길이는 src_left, 아니면 버퍼에 남은 공간만큼 복사 */
        if (src_left < buffer_remaining) {
            copy_cnt = src_left;
        }

        strncpy(s_buffer + s_buffer_index, p, copy_cnt);    /* s_buffer[s_buffer_index] 부터 복사해서 덮어쓰면 됨 */
        s_buffer_index += copy_cnt; /* 버퍼 인덱스 이동, 다음에 쓸 위치를 기억하기 위함 */
        src_left -= copy_cnt; /* 입력받은 문자열의 남은 크기를 알아야, 반복문의 조건에 사용할 수 있음 */
        p += copy_cnt; /* 어디서 부터 src를 복사할지 위치를 기억 */

        if (s_buffer_index == BUFFER_LENGTH - 1) {
            s_buffer[BUFFER_LENGTH - 1] = '\0'; /* 버퍼가 꽉차면 널문자를 추가해서 c-style stirng 보장 */
            printf("%s\n", s_buffer);
            s_buffer_index = 0;
        }
    }
}

```

### [문제 10] strstr 구현

```c++
char* find_str(const char* src, const char* substr)
{
    size_t i;
    
    if (strlen(substr) == 0) {
        return (char*)src;
    }

    while (*src != '\0') {
        if (*src == *substr) {
            for (i = 1; i < strlen(substr); ++i) {
                if (src[i] != substr[i]) {
                    goto next;
                }
            }
            return (char*)src;
        }
    next:
        ++src;
    }
    return NULL;
}
```

### [문제 11] strtok 구현

```c++
char* tokenize(char* src, const char* delims)
{
    char* token;
    char* p;

    if (src == NULL) {
        src = s_olds;
    }

    /* remove leading delims */
    src = remove_leading_delims(src, delims);

    if (*src == '\0') { /* 입력이 모두 토큰인 경우, 널문자의 주소를 s_olds에 저장 */
        s_olds = src;
        return NULL;
    }

    token = src;    /* delims가 아닌 문자로 시작하는 문자열 */
    p = token;
    while (*p != '\0') {
        if (is_delim(*p, delims) == 1) {
            s_olds = p + 1;
            *p = '\0';
            return token;
        }
        ++p;
    }

    s_olds = p; /* 널문자의 주소를 s_olds에 저장, 토큰화가 끝났음 */
    return token;
}

static int is_delim(char c, const char* delims)
{
    while (*delims != '\0') {
        if (c == *delims) {
            return 1;
        }
        ++delims;
    }
    return 0;
}

static char* remove_leading_delims(const char* p, const char* delims)
{
    while (*p != '\0') {
        if (is_delim(*p, delims) == 0) {
            return (char*)p; /* delims 건너뛰고, delims가 아닌 문자의 주소 반환 */
        }
        ++p;
    }
    return (char*)p; /* 모두 구분자로 구성된 경우 마지막의 널 문자의 주소 반환 */
}
```

- 함수를 반복적으로 호출해도 기존의 매개변수로 넘겼던 문자열을 그대로 사용할 수 있도록, static 변수에 저장
- 토큰화 대상
  - 처음 호출하면 src 그대로 사용
  - 두번째 이후 호출하면, src에 NULL이 들어오기 때문에, s_olds를 사용
- 토큰화 대상에서 leading delims 제거
  - 제거 후 모두 delims면 널문자의 주소 반환
- leading delims 제거후 토큰화
  - 문자가 없는 경우(널문자 뿐) NULL 반환
  - delims 나올 때 까지 순회해서 찾고, delims 다음 위치를 s_olds에 저장 후 시작 지점 반환
  - 끝까지 순회했는데, delims를 못 찾은 경우, 토큰화가 끝났음, s_olds에 널문자의 주소 저장 후 시작 지점 반환
- s_olds에 널문자만 있는 경우
  - 입력이 ""인 경우
  - 구분자만으로 이뤄진 경우, leading delims 제거 후 널문자의 주소만 남아서, ""와 동일해짐
  - 토큰화가 끝났는데 계속 호출할 경우, ""와 동일해짐

### [문제11] 다음 코드가 안전하다면, 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    char buffer[5];

    sprintf(buffer, "ronaldo%d", 7);
    printf("%s", buffer);

    return 0;
}
```

- 안전하지 않다
- 버퍼의 크기는 5라서 널문자가 들어갈 공간을 제외하면 포멧스트링의 길이가 4가 되어야겠죠?

