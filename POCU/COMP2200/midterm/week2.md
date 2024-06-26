# Week2

## 요약

### [스택 메모리의 크기와 위치]

- 스택 메모리는 각 함수에서 사용하는 지역 변수 등을 임시적으로 저장하는 공간
- 스택 메모리의 크기는 `빌드 시` 결정됨
  - 컴파일 옵션으로 스택 메모리의 크기를 조절할 수 있음
- 스택 메모리의 위치는 `실행 시` 결정됨
  - 스택 메모리의 위치는 프로그램이 실행될 때마다 달라질 수 있음
  - 다른 사람의 컴퓨터에서 내가 빌드한 프로그램을 실행하면, 당연히 스택 메모리의 위치도 달라질 수 있음

### [함수를 호출할 때 스택 메모리의 변화]

- 함수를 호출하면 프롤로그
- push ebp: 호출자의 EBP를 스택에 저장
- mov ebp, esp: ESP의 값을 EBP에 저장
  - 리시버의 스택프레임의 시작 주소를 EBP에 저장
- sub esp, n(16진수 값): 지역 변수를 저장할 공간을 확보
  - 큰 주소에서 작은 주소로 쌓임
  - 리시버의 스택프레임 확보
- 함수 구현에 따라 어떤 처리를 하고, return을 만나면 에필로그
- add esp, n(16진수 값): 지역 변수를 저장한 공간을 해제
  - 이제 쓰레기 값이 들어있을 수 있음
  - ebp, esp가 동일한 값을 가짐
  - 리시버의 스택프레임 삭제
- pop ebp: 스택의 상단(esp)의 값(=호출자의 EBP)를 스택에서 꺼내어 EBP에 저장
  - 호출자의 EBP를 복원
- ret: esp에 저장된 값(돌아갈 명령어의 주소)으로 돌아감

### [지역변수인 배열과 스택 메모리]

- 지역변수인 배열은 스택 메모리에 저장됨
- 컴파일할 때 배열의 크기를 알 수 있고, 이를 고려해 스택 프레임을 결정함
  - sub esp, n(16진수 값): 지역 변수를 저장할 공간을 확보
  - n값이 지역변수인 배열의 크기에 따라 달라짐

### [재귀 함수와 스택 오버플로우]

- 스택의 크기는 한정되어 있음
- 재귀 함수 너무 깊게 호출하면, 스택 오버플로우가 발생함
- 함수를 호출할 때 마다, 스택 프레임을 계속 확보하게 되고 `함수를 반환하지 않고` 계속 호출하면, 스택 메모리가 부족해지게 됨

### [sizeof 연산자와 배열의 크기]

- 배열의 크기는 컴파일 시점에 알 수 있기 때문에 sizeof 연산자를 사용할 수 있음
- 배열이 스택에서 몇 바이트를 차지하는지 컴파일 중 알 수 있음

### [함수의 매개변수로 전달하는 배열의 크기]

- 배열을 함수의 매개변수로 전달하는 경우 매개변수로 입력된 배열의 크기는 알 수 없음
- 대신에 포인터의 크기만 알 수 있음

```c++
#include <stdio.h>

static void foo(int input[]);

int main(void)
{
    int arr[30] = {0,};

    foo(arr);

    return 0;
}

static void foo(int input[])
{
    int arr[30];

    printf("%lu\n", sizeof(arr));
    printf("%lu\n", sizeof(input));
}
>>
120
4
```

### [배열 초기화]

```c++
#include <stdio.h>

static void foo(int input[], size_t n);

int main(void)
{
    int arr[] = {0, 1, 2, 3};   /* 컴파일러가 배열의 크기를 결정 */
    int arr1[3] = {0, 1,};  /* 초기화 연산자를 사용할 때 초기화 하지 않은 원소는 0으로 초기화 */
    int arr2[2];    /* 초기화 하지 않으면 쓰레기값이 들어감 */
    int arr3[1] = {1};  /* 모두 초기화 */


    foo(arr, sizeof(arr) / sizeof(int));
    foo(arr1, sizeof(arr1) / sizeof(int));
    foo(arr2, sizeof(arr2) / sizeof(int));
    foo(arr3, sizeof(arr3) / sizeof(int));

    return 0;
}

static void foo(int input[], size_t n)
{
    size_t i;

    for (i = 0; i < n; ++i) {
        printf("%d ", input[i]);
    }
    printf("\n");
}
>>
0 1 2 3
0 1 0
341324 1234141
1
```

### [배열 초기화 2]

```c++
#include <stdio.h>

static void foo(int input[], size_t n);

int main(void)
{
    int arr[3];

    arr = {0, 1, 2};    /* 컴파일 에러 */
    
    foo(arr, sizeof(arr) / sizeof(int));

    return 0;
}

static void foo(int input[], size_t n)
{
    size_t i;

    for (i = 0; i < n; ++i) {
        printf("%d ", input[i]);
    }
    printf("\n");
}
>>
컴파일 에러
```

- 배열의 초기값 결정은 반드시 선언과 동시에 해야함

### [다차원 배열]

```c++
#include <stdio.h>

int main(void)
{
    size_t i;
    size_t j;

    int square_matrix[3][3] = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
    };

    int square_matrix1[3 * 3] = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    for (i = 0; i < 3; ++i) {
        for (j = 0; j < 3; ++j) {
            if (square_matrix[i][j] == square_matrix1[3 * i + j]) {
                printf("equals: %d == %d\n", square_matrix[i][j], square_matrix1[3 * i + j]);
            }
        }
    }

    return 0;
}
>>
equals: 0 == 0
equals: 1 == 1
equals: 2 == 2
equals: 3 == 3
equals: 4 == 4
equals: 5 == 5
equals: 6 == 6
equals: 7 == 7
equals: 8 == 8
```

- 다차원 배열은 1차원 배열과 내부적으로 동일함
- 메모리에 일렬로 값이 나열됨

### [헤더 파일]

```c++
#ifndef C_BASE_ADD_H
#define C_BASE_ADD_H

#define MAX (20)

extern int g_num;

int add(int a, int b);

void check_num(void);

#endif //C_BASE_ADD_H
```

- 여러 소스코드에서 공통적으로 필요한 것들을 모아두는 파일
- 헤더 파일에는 함수의 선언, extern 변수 선언, 구조체, 열거형, 매크로 등을 포함함

### [C 파일]

```c++
#include "add.h"
#include <stdio.h>

int g_num = 0;

int add(int a, int b)
{
    g_num += (a + b);
    return a + b;
}

void check_num(void)
{
    printf("%d\n", g_num);
}
```

- 헤더 파일에 선언된 함수를 구현하는 파일
- C파일에서 정의한 전역변수를 다른 파일에서 사용하려면 extern 키워드를 사용해야함
  - 사용하는 사람이 헤더파일만 보고 전역변수를 사용할 수 있도록, 헤더파일에 extern 전역 변수 선언을 해야함
- #include 문을 통해서 헤더파일과 연결됨

### [헤더 파일을 사용하는 방법]

[other.c]
```c++
#include "add.h"

double other(void) {
    int res = g_num; /* 헤더파일에 extern 선언된 전역변수 사용 */
    add(10, 10); /* 헤더파일에 선언된 함수 사용 */
    check_num(); /* 헤더파일에 선언된 함수 사용 */

    /* 다른 로직... */
}
```

[main.c]
```c++
#include "add.h"

int main(void)
{
    int res;

    check_num();    /* 헤더파일에 선언된 함수 사용 */
    res = add(3, 4);
    check_num();
    g_num += res; /* 헤더파일에 extern 선언된 전역변수 사용 */
    check_num();
    if (g_num < MAX) { /* 헤더파일에 선언된 메크로 사용 */
        g_num = MAX;
    }
    check_num();

    return 0;
}

>>
0
7
14
20
```

- 헤더파일에 선언된 함수, extern 전역 변수, 메크로 등을 다른 파일에서 사용할 수 있음

### [#include <> vs #include ""]

- 디스크 어디에서 헤더파일을 찾느냐에 차이
- <>: 시스템 헤더파일을 찾음
  - system path에서만 찾음
- "": 사용자가 만든 헤더파일을 찾음
  - working directory에서 먼저 찾고, 없으면 system path에서 찾음

### [전처리 단계]

- 입력: 소스 코드

- 주석 제거
- 메크로 확장
- #include 처리
  - 헤더파일 소스 코드 복붙

- 결과: translate unit

### [컴파일 단계]

- 입력: tranlate unit

- 심볼의 선언만 있어도 컴파일 가능
  - 심볼은 함수의 전방 선언, 헤더 파일 안의 함수 선언, extern 변수 선언 등
- 심볼을 사용하는 코드에 구멍(place holder)으로 남겨 놓음

- 결과: 어셈블리어 코드
- 어셈블리어 코드부터는 플랫폼 종속적임
  - 시스템에 따라 자료형의 크기 등이 달라질 수 있음

### [어셈블 단계]

- 입력: 어셈블리어 코드

- 어셈블리어 코드를 조립해서 오브젝트 코드를 만듬
- 0과 1로 이루어진 기계어

- 출력: 오브젝트 코드

### [링크 단계]

- 입력: 오브젝트 `코드들`

- 모든 오브젝트 코드를 하나로 합침
- 심볼을 실제 주소로 매핑
- 심볼의 정의가 없으면, 링킹오류가 발생하고 실행파일이 만들어지지 않음

- 출력: 실행 파일

### [전처리~어셈블 과 링크의 구분]

- 전처리~어셈블: 하나의 파일 단위
- 링크: 여러 파일을 묶음

- 모든 단계를 하나로 묶으면 파일 하나가 추가될 때 마다 전체 파일을 다시 컴파일해야함
- 전처리~어셈블 단계만 다시 수행하면 되므로, 전처리~어셈블 단계와 링크 단계를 분리함

### [라이브러리]

- 오브젝트 코드의 모음
- 바로 실행할 필요가 없기 때문에 main 함수를 포함하지 않음
- 다른 오브젝트 코드와 링킹해서 사용

### [정적 링킹]

- 링커가 정적 라이브러리에 있는 기계어를 실행파일에 복사함
- 실행 파일의 크기가 커짐
- 메모리 사용량이 커짐
- 실행 속도는 빠름

### [동적 링킹]

- 링커가 동적 라이브러리에 대한 링크 정보만 실행 파일에 저장함
  - 심볼의 place holder와 유사함
- 실행 파일이 실행될 때, 운영체제가 라이브러리를 메모리에 로드함
- 실행 파일의 크기가 작음
- 메모리 사용량이 작음
- 실행 속도는 느림

### [extern 전역 변수의 위치]

- 헤더 파일에 extern 전역 변수 선언을 하는 경우
  - 보통 소스코드(c파일)을 공개하지 않고, 헤더 파일과 라이브러리만(오브젝트 코드) 공개하기 때문에, extern 전역 변수를 헤더 파일에 선언함
  - 내가 만든 c파일 구현에 전역 변수 정의가 있음
  - 다른 사람이 사용할 때, 헤더파일을 include 해서 extern 전역 변수를 사용할 수 있음
- c 파일에 extern 전역 변수를 선언하는 경우
  - 내가 만든 다른 파일(other.c)에 전역 변수가 정의됨
  - 내가 만든 c 파일(main.c)에서 extern 전역 변수를 사용할 수 있음
  - 이 경우 main.c 에서만 전역 변수를 사용한다는 의도를 명확히 할 수 있음

### [함수 내부의 static 변수]

- 함수 내부에 static 변수를 선언하면, 전역 변수와 유사한 특성을 가짐
- 초기화는 프로그램 시작 시 한 번만
- 함수 내부에서만 사용 가능

[add.h]

```c++
#ifndef C_BASE_ADD_H
#define C_BASE_ADD_H

void add(int a, int b);

#endif //C_BASE_ADD_H

```

[add.c]

```c++
#include "add.h"
#include <stdio.h>

void add(int a, int b)
{
    static int s_num = 0;
    s_num += (a + b);
    printf("%d\n", s_num);
}
```

[main.c]

```c++
#include "add.h"

int main(void)
{
    add(1, 2);
    add(1, 3);
    add(0,3);

    return 0;
}
>> 
3
7
10
```

### [인클루드 가드]

```c++
#ifndef C_BASE_ADD_H
#define C_BASE_ADD_H

#endif //C_BASE_ADD_H
```

## 코딩 문제

### [1] 컴파일 오류가 발생하는 이유를 설명하시오

[monster_repo.h]

```c++
void add monster(void);
```

[monster_repo.c]

```c++
#include "monster_repo.h"

int g_mob_count = 0;

void add_monster(void)
{
    ++g_mob_count;
}
```

[main.c]

```
#include <stdio.h>
#include "monster_repo.h"

int main(void)
{
    add_monster();
    printf("%d\n", g_mob_count);

    return 0;
}
```

- 컴파일 단계에서 오류 발생
- g_mob_count가 선언되어 있지 않음

### [2] 컴파일 오류가 발생하는 이유를 설명하시오

[monster_repo.h]

```c++
void add monster(void);
```

[monster_repo.c]

```c++
#include "monster_repo.h"

int g_mob_count = 0;

void add_monster(void)
{
    ++g_mob_count;
}
```

[main.c]

```
#include <stdio.h>
#include "monster_repo.h"

int g_mob_count = 0;

int main(void)
{
    
    add_monster();
    printf("%d\n", g_mob_count);

    return 0;
}
```

- 링크 단계에서 오류 발생
- 전역 변수가 중복 선언됨

### [3] 컴파일 오류가 난다면 발생하는 이유를 설명하시오

[monster_repo.h]

```c++
int g_mob_count = 0;
void add monster(void);
```

[monster_repo.c]

```c++
#include "monster_repo.h"

void add_monster(void)
{
    ++g_mob_count;
}
```

[main.c]

```
#include <stdio.h>
#include "monster_repo.h"

int main(void)
{
    
    add_monster();
    printf("%d\n", g_mob_count);

    return 0;
}
```

- 링크 오류 발생
- 전처리기가 main.c에 monster_repo.h를 복붙하기 때문에 g_mob_count가 main.c에 선언됨 따라서 `컴파일`은 됨
- 링크 단계에서 main.c 말고 다른 c파일에도 g_mob_count가 선언되어 있어서 링크 오류 발생

### [4] 컴파일 오류가 난다면 발생하는 이유를 설명하시오

[monster_repo.h]

```c++
void add monster(void);
```

[monster_repo.c]

```c++
#include "monster_repo.h"

int g_mob_count = 0;

void add_monster(void)
{
    ++g_mob_count;
}
```

[main.c]

```
#include <stdio.h>
#include "monster_repo.h"

extern int g_mob_count;

int main(void)
{
    
    add_monster();
    printf("%d\n", g_mob_count);

    return 0;
}
```

- 컴파일 오류 발생 X
- main.c에서 extern 키워드로 전역 변수를 선언함

### [5] 컴파일 오류가 난다면 발생하는 이유를 설명하시오

[monster_repo.h]

```c++
void add monster(void);
```

[monster_repo.c]

```c++
#include "monster_repo.h"

static int s_mob_count = 0;

void add_monster(void)
{
    ++s_mob_count;
}
```

[main.c]

```
#include <stdio.h>
#include "monster_repo.h"

extern int s_mob_count;

int main(void)
{
    
    add_monster();
    printf("%d\n", s_mob_count);

    return 0;
}
```

- 컴파일 오류 발생 X
- main.c에서 extern 키워드로 전역 변수를 선언함
- 링크 단계에서 오류 발생
- extern 전역 변수를 찾을 수 없음, static 키워드 때문
