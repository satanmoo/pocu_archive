# Week6

## 암기

### [구조체 선언 시 쓰레기 값]

```c++
#include <stdio.h>

int main(void)
{
    typedef struct date {
        int month;
        int day;
    }date_t;

    date_t a;
    printf("%d", a.month);

    return 0;
}
```

- a에 쓰레기값

## 코딩 문제

### [문제1] 이 코드가 컴파일 되나요? 된다면 출력은?

```c++
#include <stdio.h>

#define TRUE (1)
#define FALSE (0)

typedef enum school_role {
    ROLE_TEACHER,
    ROLE_STUDENT
} school_role_t;

int has_access(school_role_t role)
{
    switch (role) {
    case ROLE_TEACHER:
        return TRUE;
    case ROLE_STUDENT:
        return FALSE;
    default:
        return FALSE;
    }
}

int main(void)
{
    printf("%d\n", has_access(0));
    return 0;
}
```

### [문제2] 이 코드가 컴파일 되나요? 된다면 출력은?

```c++
#include <stdio.h>

int main(void)
{
    struct date {
        int month;
        int day;
    };

    date a;
    a.month = 10;
    a.day = 20;

    return 0;
}
```

- 컴파일 오류
- struct data a 로 수정하기

### [문제3] 출력값을 구하라

```c++
#include <stdio.h>

typedef union {
    unsigned char val;
    struct  {
        int month;
        int day;
    } date;
    float kal;
} union_t;

int main(void)
{
    union_t a;
    printf("%zd", sizeof(union_t));

    return 0;
}
```

- 공용체의 크기는 크기가 가장 큰 멤버의 크기로 결정됨
- int 2개로 이루어진 구조체가 8바이트로 가장 큼

### [문제4] 출력값을 구하라

```c++
#include <stdio.h>

typedef union {
    struct {
        char a: 4;
        char b: 4;
        char c: 4;
        char d: 4;
    } bits;
    unsigned char val;
} union_t;

int main(void)
{
    union_t a;
    printf("%zd", sizeof(union_t));

    return 0;
}
```

- 공용체 멤버
  - 구조체: 4비트 * 4개의 구조체: 16비트
  - unsigned char: 8비트
  - 따라서 공용체의 크기는 2바이트

```text
2
```