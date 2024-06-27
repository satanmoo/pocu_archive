# Week6

## 암기

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