# practice

## 1

```text
Hi

```

- --b: 0으로 평가됨
  - b == 0
  - || 이후 실행
- ++a: 4로 평가됨
  - a == 4
  - && 이후 실행
- c--: 1로 평가됨
  - c == 0
- 0 || 4 && 1 이라서 Hi\n 출력

## 2

a == 150
b == 50

## 3

a) 컴파일 오류
b) increase 함수 내부 static 변수를 함수 밖 범위로 이동

```c++
#include <stdio.h>

static int s_count = 0;

int increase(void)
{
    return ++s_count;
}

int decrease(void)
{
    return s_count--;
}

int main(void)
{
    int count;

    count = increase();
    count = decrease();
    count = increase();
    count = increase();
    count = decrease();

    printf("%d\n", count);

    return 0;
}
```

## 4

```text
2

```

## 5

a == 123
b == 쓰레기값
c == 2

## 6

a) 컴파일 오류
b) swap의 매개변수 목록을 (int* p, int* q)로 수정

```c++
#include <stdio.h>

void swap(int* p, int* q)
{
    int tmp;

    tmp = *p;
    *p = *q;
    *q = tmp;
}

int main(void)
{
    int a = 3;
    int b = 5;

    swap(&a, &b);

    return 0;
}
```

## 7

문제없음

## 8

```text
OCU 10.0030+55

```

## 9

a) 문제없음
c) 97 ('a'의 아스키 코드 값)

## 10

sz를 int로 캐스팅 or 조건문을 수정

```c++
#include <stddef.h>

size_t sz = /* 어떤 값 */;
while (sz-- > 0) {
    /* 어떤 코드 */
}
```

## 11

```text
4 (포인터의 크기라 다를 수 있음)

```

## 12

dest의 크기를 알 수 없어서, 소유하지 않은 메모리에 접근할 위험이 있음, 따라서 매개변수로 dest의 크기에 대응하는 값을 넣어주는 게 좋아보임
for문의 조건문에서 strlen(src)를 반복적으로 호출하기 보다, 변수에 미리 저장하는 것이 좋을 듯

## 13

0

## 14

a) 컴파일 오류
b) 매개변수에 enum school_rol role로 변경

```c++
#include <stdio.h>

#define TRUE (1)
#define FALSE (0)

enum school_role {
    ROLE_TEACHER,
    ROLE_STUDENT
};

int has_access(enum school_role role)
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
