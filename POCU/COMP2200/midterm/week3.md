# Week3

## 요약

### [지역 변수의 주소 출력하기]

```c++
#include <stdio.h>

int main(void)
{
    char ch = '5';

    printf("%p\n", (void*)&ch);

    return 0;
}
>>
0x16f5873ab
```

### [댕글링 포인터]

```c++
#include <stdio.h>

int* add(const int op1, const int op2);

int main(void)
{
    printf("%p\n", (void*)add(1, 1));

    return 0;
}

int* add(const int op1, const int op2)
{
    int res = op1 + op2;

    return &res;
}
>>
warning: address of stack memory associated with local variable 'res' returned [-Wreturn-stack-address]
```

- 지역변수의 주소값을 반환하는 것을 댕글링 포인터라고 함
- 컴파일 경고를 줌

### [포인터의 크기]

- 모든 포인터는 동일한 크기를 가짐
- 포인터의 크기는 워드의 크기랑 동일함
- 워드는 CPU에서 한 번에 처리할 수 있는 데이터의 크기

### [const 변수의 초기화]

- 선언과 동시에 초기화 해야함
- 초기화 이후 변경할 수 없음


## 코딩 문제

### [문제1]: 다음 코드가 안전한가? 안전하다면 출력값은?

```c++
#include <stdio.h>

int* add(const int op1, const int op2);

int main(void)
{
    printf("%d\n", *add(1, 1));
    printf("%d\n", *add(4, 4));

    return 0;
}

int* add(const int op1, const int op2)
{
    static int s_res = 0;

    s_res += (op1 + op2);

    return &s_res;
}
```

- 안전하다.
- 함수에서 전역 변수의 주소를 반환하는 것은 안전한 코드
  - 함수에서 반환할 수 있는 주소에는, 전역변수, malloc으로 할당한 힙 메모리의 주소가 있음
- 출력값

```shell
2
10
```

### [문제2]: 다음 코드가 컴파일 되는가? 되면 출력값은?

```c++
#include <stdio.h>

int* add(const int op1, const int op2);

int main(void)
{
    printf("%d\n", *add(1, 1));
    printf("%d\n", s_res);

    return 0;
}

int* add(const int op1, const int op2)
{
    static int s_res = 0;

    s_res += (op1 + op2);

    return &s_res;
}
```

- 컴파일 되지 않음
- 함수 내 static 변수의 수명은 프로그램 실행 중이지만, 함수 외부에서 접근할 수 없음

### [문제3]: 다음 코드가 컴파일 되는가? 되면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    int arr[3] = {0,1,2};

    int* ptr1 = arr;
    int* ptr2 = &arr[0];
    int* ptr3 = arr + 1;
    int* ptr4 = (int*)((char*)arr + sizeof(int));

    printf("%d %d %d %d", *ptr2, *(ptr3), ptr1[2], *ptr4);

    return 0;
}
>>
0 1 2 1
```

### [문제4]: 다음 코드가 컴파일 되는가? 되면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    int arr[3] = {0,1,2};

    int* ptr1 = arr;
    int* ptr2 = &arr[1];
    int* ptr3 = ptr1 + ptr2;
    int* ptr4 = ptr2 - ptr1;

    printf("%d %d %d %d", *ptr1, *ptr2, *ptr3, *ptr4);

    return 0;
}
```

- 컴파일 오류 발생
- 주소끼리 더하는 연산 불가능
- 주소끼리 빼면 offset을 구할 수 있음
  - offset은 정수값(long 타입)

### [문제5]: 다음 코드가 컴파일 되는가? 되면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    int arr[3] = {0, 1, 2};

    int* ptr1 = arr;
    int* ptr2 = &arr[1];
    int offset = (int)(ptr2 - ptr1);
    int* ptr3 = ptr2 + offset;

    printf("%d %d %d\n", *ptr1, *ptr2, *ptr3);
    printf("%d", offset);

    return 0;
}

```

- 컴파일 됨
- 출력값

```shell
0 1 2
1
```

### [문제6]: 다음 코드가 안전한가? 안전하다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    int i;
    int arr[3] = {0, 1, 2};

    for (i = -1; i <= 3; ++i) {
        printf("%d\n", arr[i]);
    }
    
    return 0;
}

```

- 안전하지 않음
- 유효하지 않은 주소에 접근함
  - arr - 1
  - arr + 3

### [문제7]: 다음 코드가 실행되는가? 실행된다면 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    size_t i;
    size_t len;
    char char_arr[] = "abc";

    len = strlen(char_arr);
    for (i = len - 1; i < len; --i) {
        char_arr[i] -= 32;
        printf("%c", char_arr[i]);
    }

    return 0;
}

```

- 지역 변수 char 배열에 문자열을 대입하면, 배열에 차례대로 문자가 들어가고 마지막에 널 문자가 저장된다.
  - char_arr[3] == '\0'
- 출력값
```shell
CBA
```

### [문제8]: 다음 코드가 실행되는가?? 실행된다면 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    size_t i;
    size_t len;
    char* char_ptr = "abc";

    len = strlen(char_ptr);
    for (i = len - 1; i < len; --i) {
        char_ptr[i] -= 32;
        printf("%c", char_ptr[i]);
    }

    return 0;
}

```

- 실행되지 않음
- 컴파일은 되지만, 실행 중 bad excess 오류가 발생함
- char*에 문자열을 대입하면, 데이터 색션의 주소를 char*에 저장함
- 데이텨 색션은 읽기 전용이라 이 데이터를 수정하려고 하면, bad excess 런타임 오류가 발생

### [문제9]: 다음 코드가 실행되는가?? 실행된다면 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    size_t i;
    size_t len;
    char* char_ptr = "abc";
    char char_arr[];

    len = strlen(char_ptr);
    char_arr = char_ptr;
    for (i = len - 1; i < len; --i) {
        char_arr[i] -= 32;
        printf("%c", char_arr[i]);
    }

    return 0;
}

```

- 컴파일 되지 않음
- 배열 변수(배열의 이름)에는 값을 대입할 수 없음
- 배열 변수는 읽기 전용

### [문제10]: 다음 코드가 실행되는가?? 실행된다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr = "abc";
    char char_arr[4] = "abc";

    if (sizeof(char_ptr) == sizeof(char_arr)) {
        printf("TRUE\n");
    } else {
        printf("char_ptr: %zd\n", sizeof(char_ptr));
        printf("char_arr: %zd\n", sizeof(char_arr));
    }

    return 0;
}

```

- 실행 됨
- 포인터 변수에 sizeof 연산자를 적용하면, 포인터의 크기가 나온다.
  - 이 크기는 CPU에서 한 번에 연산할 수 있는 단위인 워드의 크기와 동일함
  - 시스템마다 다를 수 있음
- 배열 변수에 sizeof 연산자를 적용하면, 배열이 메모리에서 차지하고 있는 크기가 나온다.
  - sizeof(char) * 4과 동일함
  - 4바이트
- 출력

```shell
char_ptr: 4
char_arr: 4
```

### [문제11]: 다음 코드가 실행되는가?? 실행된다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char char_arr[3] = "abc";

    while (*char_arr != '\0') {
        printf("%c\n", *char_arr);
        ++char_arr;
    }

    return 0;
}

```

- 컴파일 되지 않음
- 배열 변수에 증감 연산자를 사용하는 것은 불가능함
  - 배열 변수에 대입할 수 없기 때문
  - char_arr = char_arr + 1; 과 동일함

### [문제12]: 다음 코드가 실행되는가?? 실행된다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr;
    char char_arr[4] = "abc";

    char_ptr = char_arr;

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
    }

    return 0;
}

```

- 실행 됨
- 배열 변수는 주소값을 가짐
- 배열 변수를 포인터 변수에 대입하면, 주소값을 복사하게 됨
- 포인터 변수는 대입이 가능해서, 증감 연산자로 포인터 산술 연산이 가능
- 출력

```shell
a
b
c
```

### [문제13]: 다음 코드가 안전한 코드인가? 안전하다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr;
    char char_arr[3] = "abc";

    char_ptr = char_arr;

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
    }

    return 0;
}

```

- 안전하지 않음
- char_arr의 크기가 3이라 a,b,c값이 저장되고, 널문자가 보장되지 않음
- char_ptr에 char_arr의 주소값을 대입하면, char_ptr에는 a의 주소가 저장됨
- 포인터를 이동하면서, a,b,c는 순서대로 접근하지만 그 이후 널문자의 존재는 보장하지 못함
- c-style-string을 만들지 못함

### [문제14]: 다음 코드가 안전한 코드인가? 안전하다면 출력값은?

```c++
#include <stdio.h>
#include <string.h>

int main(void)
{
    char* char_ptr;
    char char_arr[10] = "abc";
    size_t i = 0;
    size_t len = strlen(char_arr);

    char_ptr = char_arr;

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
        ++i;
    }

    if (len == i) {
        printf("%zd\n", i);
    }

    return 0;
}

```

- 안전함
- char_arr에 10바이트가 확보되어, a,b,c 그리고 널문자까지 넣을 수 있음
- len 값도 3으로 정상적으로 구함
- 출력

```shell
a
b
c
3
```

### [문제14]: 다음 코드의 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr;
    char char_arr[10] = "abc";
    char* p;
    char c;

    char_ptr = char_arr;
    p = char_ptr;

    c = *p++;
    printf("c: %c, offset: %ld\n", c, p - char_ptr);

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
    }

    return 0;
}

```

- 후위 연산자의 우선 순위는 *보다 높음
- 따라서 *p++ 는 *(p++)와 동일함
- p++는 평가 후 실행되기 때문에 c에는 *p가 대입됨
  - 즉 'a'가 대입됨
- 대입 후 p++로 p = p + 1을 실행함 따라서 p는 'b'의 주소를 가짐
- 출력

```shell
c: a offset: 1
a
b
c
```

### [문제15]: 다음 코드의 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr;
    char char_arr[10] = "abc";
    char* p;
    char c;

    char_ptr = char_arr;
    p = char_ptr;

    c = *++p;
    printf("c: %c, offset: %ld\n", c, p - char_ptr);

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
    }

    return 0;
}


```

- 전위 연산자의 우선 순위는 *와 동일함
- 연산자 결합법칙은 `오른쪽에서 왼쪽` 
- 따라서 *++p 는 *(++p)와 동일함
- ++p는 실행 후 평가되기 때문에 p = p + 1을 실행함
  - p 는 'b'의 주소를 가짐
- c에 대입할 때 ++p를 평가하고 대입함
  - c에 'b'가 대입됨
- 출력

```shell
c: b offset: 1
a
b
c
```

### [문제16]: 다음 코드의 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr;
    char char_arr[10] = "abc";
    char* p;
    char c;

    char_ptr = char_arr;
    p = char_ptr;

    c = ++*p;
    printf("c: %c, offset: %ld\n", c, p - char_ptr);

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
    }

    return 0;
}


```

- 전위 연산자의 우선 순위는 *와 동일함
- 연산자 결합법칙은 `오른쪽에서 왼쪽`
- 따라서 ++*p 는 ++(*p)와 동일함
- 우선 *p로 'a'의 주소에 접근
- ++는 실행 후 평가됨 ++('a')을 하면, 'b'가 됨 
    - 평가 후 c에 'b'를 대입
    - *p = *p + 1로 char_arr의 'a'의 주소게 있던 값을 변경하게 됨
- 출력

```shell
c: b offset: 0
b
b
c
```

### [문제17]: 다음 코드의 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char* char_ptr;
    char char_arr[10] = "abc";
    char* p;
    char c;

    char_ptr = char_arr;
    p = char_ptr;

    c = (*p)++;
    printf("c: %c, offset: %ld\n", c, p - char_ptr);

    while (*char_ptr != '\0') {
        printf("%c\n", *char_ptr);
        ++char_ptr;
    }

    return 0;
}

```

- 괄호가 있으니 괄호 안에 부터 평가함
- *p로 'a'의 주소에 접근함
- ++는 평가 후 실행됨 
    - 평가 후 c에 'a'를 대입
    - 대입 후 실행되어, *p = *p + 1을 실행함, 'a'의 주소값에 있던 값을 변경
- 출력

```shell
c: a offset: 0
b
b
c
```

### [문제18]: 다음 코드가 실행된다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char c1 = 'a';
    char c2 = 'b';

    char* const p = &c1;

    *p = 'c';
    printf("%c\n", c1);
    printf("%c\n", c2);

    return 0;
}


```

- p는 const pointer to char
  - 포인터 변수의 값을 바꿀 수 없음
  - 포인터 변수로 indirection해서 값을 바꿀 수 있음
- 출력

```shell
c
b
```

### [문제19]: 다음 코드가 실행된다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char c1 = 'a';
    char c2 = 'b';

    char* const p = &c1;
    const char* q = &c2;

    *p = 'c';
    *q = 'd';
    printf("%c\n", c1);
    printf("%c\n", c2);

    return 0;
}

```

- q는 pointer to const char 
  - 포인터 변수의 값을 바꿀 수 있음
  - 하지만 포인터 변수로 indirection해서 값을 바꿀 수 없음
- 컴파일 에러 발생

### [문제20]: 다음 코드가 실행된다면 출력값은?

```c++
#include <stdio.h>

int main(void)
{
    char c1 = 'a';
    char c2 = 'b';

    char* const p = &c1;
    const char* q = &c2;

    p = q;
    q = p;
    printf("%c\n", c1);
    printf("%c\n", c2);

    return 0;
}

```

- q는 pointer to const char
  - 포인터 변수의 값을 바꿀 수 있음
  - 하지만 포인터 변수로 indirection해서 값을 바꿀 수 없음
- p는 const pointer to char
  - 포인터 변수의 값을 바꿀 수 없음
  - 포인터 변수로 indirection해서 값을 바꿀 수 있음
- p에 q를 대입하면, 포인터 변수의 값을 변경하려고 시도, 컴파일 에러 발생
- q = p는 문제 없음

### [문제20]: 다음 코드가 실행된다면 출력값은?

```c++
#include <stdio.h>

static void print_array(const int* const data[], const size_t size, const int lengths[]);


int main(void)
{
    int nums1[3] = {1, 2, 3};
    int nums2[1] = {99};
    int nums3[2] = {7,};
    int* nums[] = {nums1, nums2, nums3};
    int size[] = {3, 1, 2};

    print_array(nums, 3, size);

    return 0;
}

static void print_array(const int* const data[], const size_t size, const int lengths[])
{
    size_t i;
    size_t j;
    const int* p;

    for (i = 0; i < size; ++i) {
        p = data[i];
        for (j = 0; j < lengths[i]; ++j) {
            printf("%d ", p[j]);
        }
        printf("\n");
    }
}
```

- 출력

```shell
1 2 3
99
7

```

### [문제21]: 다음 코드가 실행된다면 출력값은?

```c++
#include <stdio.h>

static void print_array(const int* const data[], const size_t size, const int lengths[]);


int main(void)
{

    int nums[3][3] = {{1, 2, 3},
                     {4, 5, 6},
                     {7, 8, 9},};
    int size[] = {3, 3, 3};

    print_array(nums, 3, size);

    return 0;
}

static void print_array(const int* const data[], const size_t size, const int lengths[])
{
    size_t i;
    size_t j;
    const int* p;

    for (i = 0; i < size; ++i) {
        p = data[i];
        for (j = 0; j < lengths[i]; ++j) {
            printf("%d ", p[j]);
        }
        printf("\n");
    }
}
```

- 컴파일 되지 않음
- 2차원 배열은 1차원 배열과 동일함
- int[]과 int*의 배열은 다르기 때문에 컴파일 불가

