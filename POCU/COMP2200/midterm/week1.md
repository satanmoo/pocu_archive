# Week1

## 요약

### [자료형이 몇 바이트인지 찾는 법]

- char가 몇 바이트인지 찾는 방법은?
- <limit.h>를 인클루드하고, CHAR_BIT를 보면 몇 비트인지 알 수 있음

### [32비트, 64비트 환경에서 일반적인 long 자료형의 크기]

- 표준에서 long은 최소 32비트, int 이상의 크기다.
- 따라서 32비트 컴퓨터에서는 32비트, 64비트 컴퓨터에서는 64비트다.

### [부동 소수점형과 부호]

- 부동 소수점형 float, double은 unsigned 형이 없다.

```c++
signed double a = 12.0;  /* 컴파일 에러 */
signed float b = 18.0;  /* 컴파일 에러 */
signed float b = 18.0uf;  /* 컴파일 에러, float는 접미사 u를 못 씀 */
```

### [열거형과 정수형]

- C 언어에서 열거형은 정수형에 별명을 붙인 수준이다.

```c++
int main(void)
{
    typedef enum {
        KIM,
        JUNG,
        SOE,
    } name_t;

    enum month {
        MONTH_JAN, MONTH_FEB, MONTH_MAR
    };

    name_t a = KIM;
    a = 2;
    a = MONTH_JAN;

    if (KIM == MONTH_JAN) {
        printf("%d", KIM);  /* 0 출력 */
    }
    
    return 0;
}

>>
0
```

### [size_t와 -1]

- 배열의 유효하지 않은 색인을 반환하기 위해 size_t 자료형의 값에 -1을 대입해 반환할 수 있다.
- size_t는 unsigned int와 동일하고, -1을 대입하면 비트패턴으로 1...1(32개)를 저장한다.
- 즉 unsigned int가 가질 수 있는 양수 중 최대값을 표현한다.

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    size_t a = -1;
    size_t b = 2;

    if (a > b) {
        printf("%u", a);
    }

    return 0;
}

>>
4294967295
```

### [switch-case 문의 fall-through]

- case안에 break를 빼먹으면, 다른 case에서 break를 만나거나, switch 블록의 끝에서 탈출한다.

```c++
#include <stdio.h>

int main(void)
{
    const char* name = "mosi";

    switch (*name) {
    case 'a':
        printf("a");
        break;
    case 'm':
        printf("m");
    case 'o':
        printf("o");
    case 's':
        printf("s");
        break;
    default:
        printf("i");
        break;
    }

    return 0;
}

>>>
mos
```

### [switch-case문과 문자열]

- C에서 switch 표현식에는 정수형만 넣을 수 있다.

```c++
#include <stdio.h>

int main(void)
{
    const char* name = "mosi";

    switch (name) {
    case 'a':
        printf("a");
        break;
    case 'm':
        printf("m");
    case 'o':
        printf("o");
    case 's':
        printf("s");
        break;
    default:
        printf("i");
        break;
    }

    return 0;
}

>>
컴파일 에러: Statement requires expression of integer type ('const char *' invalid)
```

### [switch-case문과 상수]

- case 표현식에는 정수형 `상수`만 넣을 수 있다. 
  - 컴파일 시점에 결정되는 상수
- 배열에서 원소에 접근하는 것은 실행 중 결정되는 변수다.

```c++
#include <stdio.h>

int main(void)
{
    const char* name = "mosi";
    int* p = {0, 1, 2};

    switch (*name) {
    case name[0]:
        printf("a");
        break;
    case p[1]:
        printf("m");
    case 'o':
        printf("o");
    case 's':
        printf("s");
        break;
    default:
        printf("i");
        break;
    }

    return 0;
}

>> 컴파일 에러
Expression is not an integer constant expression
```

### [C언어에는 함수 오버로딩이 없음]

```c++
void print(void ) {
    int a = 1;
}

void print(int a) {
    a = 2;
}

>> 컴파일 에러
Conflicting types for 'print' previous definition is here
```

### [C89에서 함수 원형에 대한 가정]

- 함수 원형을 전방선언 하지 않았을 때, 컴파일러는 반환값은 int, 매개변수는 모른다고 가정한다.
  - int fun();
  - int fun(void); 가 아니다. void는 매개변수가 없다는 뜻, 매개변수가 없을 수도, 있을 수도, 여러 개일수도..

```c++
#include <stdio.h>

int main(void)
{
    foo();

    return 0;
}

int foo(void)
{
    int a = 1;
    printf("WWW");
    return 2;
}

>>
WWW
```

### [파일 범위에 있는 변수의 메모리 위치]

- 파일 범위(translate unit)에 있는 변수는 프로그램이 실행되는 동안 메모리의 공간을 차지함
- 메모리 영역은 데이터 섹션
- 전역 변수

### [함수 범위]

- goto의 레이블의 경우 함수 블록의 어디에서든 접근할 수 있음

```c++
#include <stdio.h>

int foo(void);

int main(void)
{
    foo();

    return 0;
}

int foo(void)
{
    int a = 1;

start:
    printf("WWW");

    if (1) {
        goto exit;
    }

    goto start;

exit:
    putchar('2');
    return 2;
}

>>>
WWW2
```
### [전역 변수와 지역 변수]

- 전역 변수는 프로그램이 실행되는 동안 메모리의 공간을 차지함
  - 따라서 어디서든 접근할 수 있음
- 지역 변수는 함수가 호출될 때마다 메모리의 공간을 차지함
  - 함수 블록 밖에서는 접근할 수 없음 

```c++
#include <stdio.h>

int g_x = 1;

static void foo(void);

int main(void)
{
    foo();
    printf("%d\n", g_x);
    printf("%d\n", a); /* 컴파일 에러 */

    return 0;
}

static void foo(void)
{
    int a = 1;
    a++;
    g_x++;
}

>>>
지역변수에 접근할 수 없기 때문에 컴파일 에러
```
