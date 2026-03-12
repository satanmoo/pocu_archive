# Week9

## 암기

### [레지스터의 특징]

- CPU가 사용하는 저장 공간 중 가장 **빠름**
- 메모리가 아니기 때문에 '주소'의 개념이 없음

### [저장 유형 지정자 register의 유효성]

- storage-class specifier
- `가능하다면` 해당 변수를 레지스터에 저장할 것을 `요청`
- 실제로 레지스터를 사용할지 말지는 컴파일러가 결정

### [저장 유형 지정자 register의 제약]

- 변수의 주소를 구할 수 없음
- 레지스터 배열 변수를 포인터로 사용 불가
- 블록 범위에서만 사용 가능
  - 전역 변수에서 사용할 수 없음
  - 레지스터는 몇 개 없는데, 이 레지스터를 프로그램 하나가 점유하면 치명적이기 때문

### [스택의 단점]

- 수명
  - 함수가 반환하면 함수 블록에 있던 데이터는 날아감
  - 지역 변수의 수명은 함수의 수명
    - 이를 해결하기 위한 방법은 static을 이용한 전역 변수화
- 크기
  - 특정 용도를 위해 컴파일 시에 결정하기 때문에 너무 크게 못 잡음
  - 정적 메모리

### [힘 메모리의 장점]

- 수명
  - 프로그래머가 원할 때 할당받고, 원할 때 반납할 수 있음
  - 수명을 프로그래머의 재량으로 결정 (동적 메모리의 특징)
- 크기
  - 범용적 메모리라 크기가 큼
  - 프로그래머가 원하는 양을 할당받을 수 있음 (동적 메모리의 특징)

### [힙 메모리의 단점]

- 메모리 누수 가능성
- 할당/해제 속도가 느림
  - 메모리 파편화
  - 요청한 크기만큼 연속된 메모리를 찾아야하기 때문에 검색 작업이 필요함

### [스택 메모리의 장점]

- 메모리 관리를 알아서 해줌
- 스택은 순차적이라 메모리의 구멍이 없음
- 스택은 offset 개념으로 스택 프레임이 관리되기 때문에 검색할 때 O(1) 연산

### [free() 함수에서 유일한 매개변수가 메모리 주소인데, free() 함수는 몇 바이트 해제할 지 어떻게 기억하고 해제할까?]

- malloc(n)을 호출했을 때, n바이트 보다 조금 더 큰 메모리를 할당하고, 앞 부분에 메타 데이터를 저장함
- malloc으로 반환하는 주소는 메타데이터가 끝나고 실제 요청한 메모리의 시작 주소
  - 메타 데이터에서 오프셋을 더해서 실제 요청한 메모리의 시작 주소를 구함
- free()로 요청한 메모리의 시작 주소를 입력 받으면, 오프셋을 빼서 메타데이터에서 실제 몇 바이트 할당됬는지 구할 수있음

### [void *memset(void *dest, int ch, size_t count)은 메모리를 초기화하는 함수다. 이 때 어떤 단위로 초기화하나요?]

- memset은 char 단위로 초기화함
  - ch값은 내부적으로 unsigned char로 형변환 됨
  - [dest, dest + count) 범위에서 1바이트씩, ch값으로 초기화
- 1바이트씩 말고, 다른 자료형의 크기만큼 초기화 하고 싶으면 for문으로 값을 직접 대입해야함
- dest + n(n <= count) 주소가 유효하지 않은 주소라면 문제가 발생할 수 있음

### [void *realloc(void *ptr, size_t new_size); 의 반환값은 첫번째 매개변수 ptr과 같을 수도, 다를 수도 있다]

- 기존의 ptr이 차지하는 공간 바로 뒤에 new_size로 확장했을 때 충분한 공간이 존재하면 첫번째 매개변수 ptr을 그대로 반환
- 충분한 공간이 존재하지 않다면, 연속으로 new_size 크기만큼 메모리 공간을 확보하고, 공간의 첫번째 주소를 반환

### [void *realloc(void *ptr, size_t new_size);에서 메모리 누수가 나는 경우?]

- realloc에서 첫번재 매개변수로 입력받은 주소가 아닌 새로운 주소를 반환하는 경우
  - new_size 만큼의 메모리를 할당받는데 실패하는 경우, realloc의 반환값은 NULL
  - 첫번째 매개변수로 넘긴 ptr은 해제하지 않아서 메모리 누수가 발생할 수 있음
- 첫번째 매개변수로 넘기는 ptr값을 변수에 저장하는 습관이 필요

### [int memcmp(const void* lhs, const void* rhs, size_t count); 에서 문제가 발생하는 경우]

- 메모리 영역의 시작 주소를 입력 받아, 메모리 영역을 비교하는 함수
- 첫번째 매개변수 lhs, 두번째 매개변수 rhs는 각각 메모리 영역의 시작 주소
  - lhs + count, rhs + count 결과 각각 메모리 영역을 벗어나면 문제가 생김 (유효하지 않은 주소)
- lhs, rhs 값이 NULL인 경우
  - 메모리 영역을 비교하려면 `역참조`를 해야함, NULL의 경우 역참조 불가능

### [동적 할당 메모리의 소유권 문제]

- 할당받은 동적 메모리의 소유주는 동적 메모리를 할당받은 함수
- 이 함수에서 할당받고, 해제까지 책임져야함
- 소유주가 아닌 곳에서는 이 메모리를 해제해서는 안 됨
  - 단지 사용만 가능

## 코드 문제

### [문제1: 다음 코드가 안전한가? 안전하다면 출력값]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
    int* nums;
    size_t i;
    int sum = 0;

    nums = malloc(10 * sizeof(int));

    for (i = 0; i < 10; ++i) {
        nums[i] = 10 * (i + 1);
    }

    for (i = 0; i < 10; ++i) {
        sum += nums[i];
    }
    printf("%d\n", sum);

    free(nums);

    return 0;
}
```

- 안전함
- 할당받은 주소를 적절하게 해제함

```text
550
```

### [문제2: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
    int* nums;
    size_t i;
    int sum = 0;

    nums = malloc(10 * sizeof(int));

    for (i = 0; i < 10; ++i) {
        *nums = 10 * (i + 1);
        ++nums;
    }

    for (i = 0; i < 10; ++i) {
        sum += nums[i]; /* 엉뚱한 주소 접근 */
    }
    printf("%d\n", sum);

    free(nums); /* 엉뚱한 주소 해제 */ 

    return 0;
}
```

- 안전하지 않음
    - sum += nums[i]; 에서 nums 값은 처음에 할당받은 주소 + 40바이트, 따라서 배열의 범위를 벗어남
    - free(nums); 에서 nums 값은 처음에 할당받은 주소 + 40바이트, free할 때 malloc으로 할당받은 메모리의 시작 주소를 입력해야하는데, 다른 주소를 넘겨서 결과가 정의되지 않음

- 이를 고치기 위해서 포인터 연산에 사용하는 변수를 따로 선언해야함
- 메모리 할당 함수에서 받아온 포인터와 포인터 연산에 사용하는 포인터를 분리하기

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
    int* nums;
    size_t i;
    int sum = 0;
    int* p;

    nums = malloc(10 * sizeof(int));

    p = nums;
    for (i = 0; i < 10; ++i) {
        *p = 10 * (i + 1);
        ++p;
    }

    for (i = 0; i < 10; ++i) {
        sum += nums[i];
    }
    printf("%d\n", sum);

    free(nums);

    return 0;
}
```

### [문제3: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]


```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
    int* nums;
    size_t i;
    int sum = 0;
    int* p;

    nums = malloc(10 * sizeof(int));

    p = nums;
    for (i = 0; i < 10; ++i) {
        *p = (i + 1);
        ++p;
    }

    for (i = 0; i < 10; ++i) {
        sum += nums[i];
    }
    printf("%d\n", sum);

    free(nums);
    nums = NULL;
    free(nums);

    return 0;
}
```

- 안전함
  - 해제한 포인터 변수에 NULL을 대입함
  - free(NULL)은 아무 문제도 일어나지 않게 구현되어 있음
- 출력값

```text
55
```

### [문제3: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void)
{
    int* nums;
    size_t i;
    double sum = 1.0;

    nums = calloc(10, sizeof(double));

    for (i = 0; i < 10; ++i) {
        sum += nums[i];
    }
    printf("%.2f\n", sum);

    free(nums);

    return 0;
}
```

- 안전함
- calloc은 할당받은 메모리를 모두 0으로 초기화함
  - malloc + memset
- 출력
```text
1.00
```

### [문제4: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>

#define LENGTH (10)
#define TRUE (1)
#define FALSE (0)

int main(void)
{
    char buffer[LENGTH];
    size_t i;

    memset(buffer, 97, 11);

    for (i = 0; i < LENGTH; ++i) {
        printf("%c\n", buffer[i]);
    }

    return 0;
}

```

- 안전하지 않음
- memeset의 3번째 매개변수에서 문제가 발생
- 유효하지 않은 메모리에 접근함
  - 'memset' will always overflow; destination buffer has size 10, but size argument is 11
- 정상적으로 고치려면 세번째 매개변수 값을 10으로 수정

### [문제5: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>

#define LENGTH (10)

int main(void)
{
    int buffer[LENGTH];
    size_t i;

    memset(buffer, 0xFFFF, 40);

    for (i = 0; i < LENGTH; ++i) {
        printf("%d\n", buffer[i]);
    }

    return 0;
}

```

- 안전함
- buffer는 40바이트 유효함
  - [buffer, buffer + 40) 으로 유효한 범위의 주소를 초기화
- memset은 char단위로 초기화함
  - 1바이트 값을 11111111로 초기화
  - 0xFFFF여도, unsigned char로 변환하면 1바이트에 담을 수 있는 비트 패턴은 11111111이 끝, 앞의 FF는 잘리게 됨
  - int는 4바이트라서 buffer에서 int 하나의 비트 패턴은 11111111111111111111111111111111으로 값은 -1
- 출력

```c++
-1
-1
-1
-1
-1
-1
-1
-1
-1
-1

```

### [문제6: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```C++
#include <stdio.h>
#include <string.h>

#define LENGTH (10)

int main(void)
{
    unsigned short buffer[LENGTH];
    size_t i;

    memset(buffer, 0xFF, 20);

    for (i = 0; i < LENGTH; ++i) {
        printf("%d\n", buffer[i]);
    }

    return 0;
}
```

- 안전함
  - [buffer, buffer + 20) 의 유효한 주소를 memset으로 초기화
  - 세번째 인자에 어떤 값이 들어오는지 확인하고, 총 몇 바이트를 차지하는지 계산
- 두번째 인자의 비트패턴은 11111111
  - unsigned char로 형변환해도 11111111 그대로 동일함
  - unsigned short는 2바이트를 차지, 따라서 buffer의 각 값은 1111111111111111 => 65535
- 출력

```text
65535
65535
65535
65535
65535
65535
65535
65535
65535
65535

```

### [문제7: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LENGTH (10)

int main(void)
{
    unsigned short* buffer;
    unsigned short* tmp;
    size_t i;

    buffer = malloc(sizeof(unsigned short) * LENGTH);
    /* 메모리 할당 예외처리 생략 */
    memset(buffer, 0xFF, 20);
    for (i = 0; i < LENGTH; ++i) {
        printf("%d\n", buffer[i]);
    }
    
    printf("------------\n");

    tmp = realloc(buffer, 40);
    if (tmp != NULL) {
        buffer = tmp;
    }
    memset(buffer, 0x00, 20);
    memset((char*)buffer + 20, 0xFF, 20);
    for (i = 0; i < LENGTH * 2; ++i) {
        printf("%d\n", buffer[i]);
    }
    
    return 0;
}

```

- 안전함
  - realloc이 실패할 경우를 대비해 임시 포인터 변수를 선언하고 조건문으로 확인
  - realloc으로 추가된 메모리를 초기화하기 위해 memset을 사용했고, char*로 형변환해 20바이트 만큼 이동한 주소를 첫번째 매개변수로 입력
- 출력

```text
65535
65535
65535
65535
65535
65535
65535
65535
65535
65535
------------
0
0
0
0
0
0
0
0
0
0
65535
65535
65535
65535
65535
65535
65535
65535
65535
65535

``` 

### [문제8: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LENGTH (10)

int main(void)
{
    int* buffer;
    int* tmp;
    size_t i;

    buffer = realloc(NULL, sizeof(int) * LENGTH);
    /* 메모리 할당 예외처리 생략 */
    memset(buffer, 0xFF, sizeof(int) * LENGTH);
    for (i = 0; i < LENGTH; ++i) {
        printf("%d\n", buffer[i]);
    }

    tmp = realloc(buffer, sizeof(int) * LENGTH * 2);
    if (tmp != NULL) {
        buffer = tmp;
    }
    memset((char*)buffer, 0x00, sizeof(int) * LENGTH);
    memset((char*)buffer + sizeof(int) * LENGTH, 0xFF, sizeof(int) * LENGTH);
    for (i = 0; i < LENGTH * 2; ++i) {
        printf("%d\n", buffer[i]);
    }

    return 0;
}

```

- 안전함
  - realloc의 첫번째 매개변수에 NULL을 넣으면, malloc과 동일함
  - memset에서 두번재 매개변수 비트패턴 11111111(0xFF)으로 초기화, 세번째 매개변수에 sizeof를 이용해 적절하게 초기화
  - 두번재 realloc에서 임시 포인터 변수를 이용해 buffer 포인터 변수에 저장된 값을 잃어버리지 않게 처리함
  - 두번째 memset에서 첫번째 매개변수에 char*로 형변환해 sizeof(int) * LENGTH바이트 만큼 이동
- 출력
  - 앞의 10개 원소는 memset()으로 모든 비트패턴을 0으로 초기화, 따라서 0을 출력
  - 뒤의 10개 원소는 4바이트의 모든 비트패턴이 1이, 따라서 -1을 출력

```text
-1
-1
-1
-1
-1
-1
-1
-1
-1
-1
0
0
0
0
0
0
0
0
0
0
-1
-1
-1
-1
-1
-1
-1
-1
-1
-1

```

### [문제9: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>

typedef struct {
    char name[4];
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;

    strncpy(t1.name, "joy", 3);
    t1.age = 8;
    strncpy(t2.name, "coy", 3);
    t2.age = 8;
    res = memcmp(&t1, &t2, sizeof(info_t));
    printf("%d", res);

    return 0;
}

```

- 안전하지 않음
  - 구조체 멤버 변수를 적절하게 초기화하지 못함. char 배열 name이 c 스타일 문자열이 아님
    - t1.name[3], t2.name[3]에 쓰레기 값이 있어서 적절히 비교할 수 없음
    - strncpy는 널문자를 넣어주지 않음
- 올바르게 고치려면 t1.name[3], t2.name[3]에 널문자를 대입해 c 스타일 문자열을 보장하기
- 또는 구조체를 비교하기 전 미리 구조체를 memset으로 모든 바이트를 0으로 초기화하기
- memcmp는 strcmp처럼 매개 변수로 입력받은 lhs, rhs에서 한 바이트씩 비교하는 함수

```c++
#include <stdio.h>
#include <string.h>

typedef struct {
    char name[4];
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;

    memset(&t1, 0, sizeof(info_t));
    memset(&t2, 0, sizeof(info_t));
    strncpy(t1.name, "joy", 3);
    t1.age = 8;
    strncpy(t2.name, "coy", 3);
    t2.age = 8;
    res = memcmp(&t1, &t2, sizeof(info_t));
    printf("%d", res);

    return 0;
}

```

### [문제10: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>

typedef struct {
    char* name;
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;

    memset(&t1, 0, sizeof(info_t));
    memset(&t2, 0, sizeof(info_t));
    t1.name = "abc";
    t1.age = 8;
    t2.name = "abc";
    t2.age = 8;
    res = memcmp(&t1, &t2, sizeof(info_t));
    printf("%d", res);

    return 0;
}

```

- 안전하지 않음
- memcmp로 비교하는 구조체의 멤버가 포인터 변수
  - t1.name, t2.name가 가리키는 값("abc")는 같아도, t1.name, t2.name의 값(주소) 는 다를 수 있음
  - memcmp로 비교할 때 char*의 값을 먼저 비트 패턴으로 비교하고, size_t의 값을 비트패턴으로 비교 
- 올바르게 수정하려면 구조체의 멤버가 깊은 복사를 할 수 있게, char 배열로 변경
- 또는 아래 코드 처럼 각 멤버에서 값으로 비교

```c++
#include <stdio.h>
#include <string.h>

typedef struct {
    char* name;
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;

    memset(&t1, 0, sizeof(info_t));
    memset(&t2, 0, sizeof(info_t));
    t1.name = "abc";
    t1.age = 8;
    t2.name = "abc";
    t2.age = 8;

    // 구조체의 각 필드를 개별적으로 비교
    if (t1.age == t2.age && strcmp(t1.name, t2.name) == 0) {
        res = 0; // 같음
    } else {
        res = 1; // 다름
    }
    
    printf("%d", res);

    return 0;
}
```

### [문제11: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char* name;
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;
    char* input;

    memset(&t1, 0, sizeof(info_t));
    memset(&t2, 0, sizeof(info_t));
    input = "abc";
    t1.name = malloc(strlen(input) + 1);
    strcpy(t1.name, input);
    t1.age = 8;
    t2.name = malloc(strlen(input) + 1);    // 주의 sizeof(input)를 넣으면 안 됨, 4byte 포인터의 크기임
    strcpy(t2.name, input);
    t2.age = 8;
    res = memcmp(&t1, &t2, sizeof(info_t));
    printf("%d", res);
    
    free(t1.name);
    free(t2.name);

    return 0;
}

```

- 안전하지 않음
  - memcmp에서 char*의 비트패턴을 비교하기 때문에, 깊은 복사로 내용을 복사해도 의미가 없음. 주소값만 비교함
- 안전하게 수정하기 위해서 구조체의 맴버를 char 배열로 변경하면 됨

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char name[4];
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;
    char* input;

    memset(&t1, 0, sizeof(info_t));
    memset(&t2, 0, sizeof(info_t));
    input = "abc";
    strcpy(t1.name, input);
    t1.age = 8;
    strcpy(t2.name, input);
    t2.age = 8;
    res = memcmp(&t1, &t2, sizeof(info_t));
    printf("%d", res);

    return 0;
}

```

- 또는 각 멤버의 값을 비교하면 됨

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char* name;
    size_t age;
} info_t;

int main(void)
{
    info_t t1;
    info_t t2;
    int res;
    char* input;

    memset(&t1, 0, sizeof(info_t));
    memset(&t2, 0, sizeof(info_t));
    input = "abc";

    // 문자열 길이를 정확히 할당
    t1.name = malloc(strlen(input) + 1);
    strcpy(t1.name, input);
    t1.age = 8;
    
    t2.name = malloc(strlen(input) + 1);
    strcpy(t2.name, input);
    t2.age = 8;

    // 각 필드를 개별적으로 비교
    if (t1.age == t2.age && strcmp(t1.name, t2.name) == 0) {
        res = 0; // 같음
    } else {
        res = 1; // 다름
    }

    printf("%d\n", res);

    // 동적 할당한 메모리 해제
    free(t1.name);
    free(t2.name);

    return 0;
}
```

### [문제12: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

const char* combine_string(const char* a, const char* b);

int main(void)
{
    char* res = combine_string("abc", "def");
    printf("%s", res);

    return 0;
}

const char* combine_string(const char* a, const char* b)
{
    char* str;
    size_t size = strlen(a) + strlen(b);

    str = malloc(size + 1);
    strncpy(str, a, strlen(a));
    strncpy(str + strlen(a), b, strlen(b));
    str[size] = '\0';

    return str;
}

```

- 안전하지 않음
- 메모리 할당의 소유주인 combine_string 함수에서 동적 메모리 해제하는 코드가 없음
- 올바르게 수정하기 위해서는 c-style 문자열 관련 함수처럼, 함수 밖에서 메모리를 넘기는 것이 좋음

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* combine_string(const char* a, const char* b, char* out_str, size_t size);

int main(void)
{
    size_t len_sum;
    char* res;

    len_sum = strlen("abc") + strlen("def");
    res = malloc(len_sum + 1);
    res = combine_string("abc", "def", res, len_sum + 1);
    printf("%s", res);

    free(res);
    res = NULL;

    return 0;
}

char* combine_string(const char* a, const char* b, char* out_str, size_t size)
{
    strncpy(out_str, a, strlen(a));
    strncpy(out_str + strlen(a), b, strlen(b));
    out_str[size] = '\0';

    return out_str;
}

```

### [문제13: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>

int main(void)
{
    int num1;
    int num2;
    int* p;
    int* q;
    int* r;
    int** pp;

    num1 = 10;
    num2 = 20;

    p = &num1;
    q = &num2;
    r = &num1;

    pp = &p;
    *pp = q;
    **pp = 1024;
    pp = &r;
    **pp = *p * 2;

    printf("num1: %d\n", num1);
    printf("num2: %d\n", num2);

    return 0;
}

```

- 안전함
- pp == &p
- *pp -> p의 주소에서 역참조해서 p의 값에 접근
  - p = q로 p에 &num2 대입
  - pp == &p, p == &num2 상태가 됨
- **p = 1024
  - num2 = 1024 상태가 됨
- pp = &r
  - pp == &r, r == &num1 상태가 됨
- *pp = *p * 2;
  - *p > num2의 주소에서 역참조해서 num2의 값에 접근: 1024
  - *pp > r의 주소에서 역참조해서 r의 값에 접근
  - **pp == *r > num1의 주소에서 역참조해서 num1의 값에 접근
  - num1 = 1024 * 2 대입
  - num1 = 2048 상태가 됨
- 출력

```text
num1: 2048
num2: 1024

```

### [문제14: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>

void swap(int** n1, int** n2)
{
    int* tmp = *n1;
    *n1 = *n2;
    *n2 = tmp;
}

int main(void)
{
    int num1 = 10;
    int num2 = 20;

    int* p;
    int* q;

    p = &num1;
    q = &num2;

    swap(&p, &q);

    printf("%d\n", *p);
    printf("%d\n", *q);

    return 0;
}

```

- 안전함
- swap 결과
  - p == &num2
  - q == &num1
- 출력

```text
20
10

```

### [문제15: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>

int main(void)
{
    int num1 = 10;
    int num2 = 13;
    int num3 = 19;

    int* p1 = &num2;
    int* p2 = &num1;
    int* p3 = &num3;

    int** pp1 = &p2;
    int** pp2 = &p3;
    int** pp3 = &p1;

    *pp1 = *pp2;
    **pp2 *= 2;
    pp3 = pp2;

    printf("%d, %d, %d", **pp1, **pp2, **pp3);

    return 0;
}

```

- 안전함
- num3 = 38
- p2 = &num3
- pp3 = &p3
- 출력값

```text
38, 38, 38
```

### [문제16: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>

int main(void)
{
    int num1 = 10;
    int num2 = 13;
    int num3 = 19;

    int* p1 = &num2;
    int* p2 = &num1;
    int* p3 = &num3;

    int** pp1 = &p2;
    int** pp2 = &p3;
    int** pp3 = &p1;

    int*** ppp1 = &pp3;
    int*** ppp2 = &pp1;
    int*** ppp3 = &pp2;

    *ppp1 = *ppp3;
    **ppp2 = **ppp1;
    ***ppp3 -= ***ppp1;

    printf("%d, %d, %d", ***ppp1, ***ppp2, ***ppp3);

    return 0;
}

```

- 안전함
- num3 = 0
- p2 = &num3
- pp3 = &p3
- *ppp1 = *ppp3;  // pp3 = pp2 = &p3
- **ppp2 = **ppp1;    // pp1의 값, &p2에서 역참조 p2의 값 = &num3
- ***ppp3 -= ***ppp1; // pp2의 값, &p3에서 역참조, &num3에서 역참조 num3의 값 -= 19
- 출력값

```text
0, 0, 0
```

### [문제17: 다음 코드가 안전한가? 안전하다면 출력값, 올바르게 고치려면?]

```c++
#include <stdio.h>

int main(void)
{
    int num1 = 10;  /* num1의 주소값: 0x100 */
    int num2 = 13;  /* num2의 주소값: 0x104 */
    int num3 = 19;  /* num3의 주소값: 0x108 */

    int* p1 = &num2;  /* p1의 주소값: 0x10C */
    int* p2 = &num1;  /* p2의 주소값: 0x110 */
    int* p3 = &num3;  /* p3의 주소값: 0x114 */

    int** pp1 = &p2;  /* pp1의 주소값: 0x118 */
    int** pp2 = &p3;  /* pp2의 주소값: 0x11C */
    int** pp3 = &p1;  /* pp3의 주소값: 0x120 */

    int*** ppp1 = &pp3;
    int*** ppp2 = &pp1;
    int*** ppp3 = &pp2;

    pp1 = pp3;
    **pp2 += **pp3;
    pp3 = pp2;

    *ppp1 = *ppp2;
    ppp2 = ppp3;
    *ppp3 = *ppp2;

    /* pp1, pp2, pp3, ppp1, ppp2, ppp3의 값은? */
    printf("%p, %p, %p\n", (void*)*ppp1, (void*)*ppp2, (void*)*ppp3);
    printf("%p, %p, %p\n", (void*)*pp1, (void*)*pp2, (void*)*pp3);

    return 0;
}

```

- 안전함
- 출력값

```text
pp1: 0x10C

pp2: 0x114

pp3: 0x10C

ppp1: 0X120

ppp2: 0x11C

ppp3: 0x11C
```

### [문제18: 여러 줄 입력받아 출력하기 구현]

```c++
#define INCERMENT (2)
#define LINE_LENGTH (2048)

char** lines;
char line[LiNE_LENGTH]; // 한 줄에 2048바이트까지 입력받을 수 있음
size_t max_lines;
size_t num_lines;
size_t i;
char** tmp;

max_lines = 0;
num_lines = 0;
lines = NULL;   // 반드시 NULL로 초기화 하는 습관, realloc()를 호출할 때 NULL 말고 쓰레기값이 들어가면 크래시

while (1) {
    if (fgets(line, LINE_LENGTH, stdin) == NULL) {  // 최대 LINE_LENGTH - 1 만큼 입력받을 수 있음, \n(개행문자) 포함
        clearerr(stdin);    // fgets 성공하면 매개변수의 line, 실패하면 NULL 반환
        break;
    }
    
    if (num_lines == max_lines) {   // 현재 줄 수가 최대 줄 수와 같으면
        tmp = realloc(lines, (max_lines + INCREMENT) * sizeof(char*));    // realloc 실패하면 NULL 반환
        // 처음에 lines == NULL이면, malloc((0 + 2) * sizeof(char*))과 동일하게 동작
        
        if (tmp == NULL) {
            fprintf(stderr, "%s\n", "out of memory");   // realloc 실패 시 errno를 설정한다는 보장이 없어서 perror 사용 불가
            break;
        }
        
        lines = tmp;
        max_lines += INCREMENT;
    }
    
    lines[num_lines] = malloc(strlen(line) + 1);    // 문자열이라 sizeof(char) 생략해도 됨, 1바이트
    if (lines[num_lines] == NULL) { // malloc 실패 시 NULL 반환
        fprintf(stderr, "%s\n", "out of memory");
        break;
    }
    strcpy(lines[num_lines], line); // strcpy해도 안전함, 메모리가 충분히 할당되었기 때문임, strcpy는 복사 후 널문자까지 추가해줌
    ++num_lines;
}
    
for (i = 0; i < max_lines; ++i) {
    printf("%s", lines[i]);
}

for (i = 0; i < max_lines; ++i) {
    free(lines[i]);
}
    
free(lines);    // realloc으로 할당했기 때문에 free 필요함
```

- 아래는 3줄 입력받는 예시

```c++
#define LINE_LENGTH (2048)

int main(void)
{
    char** lines = NULL;
    char buffer[LINE_LENGTH + 1];
    size_t max_lines = 0;
    size_t num_lines = 0;
    int count = 3;

    while (count > 0) {
        memset(buffer, 0, LINE_LENGTH + 1);
        if (fgets(buffer, LINE_LENGTH, stdin) == NULL) {
            clearerr(stdin);
            break;
        };

        if (num_lines == max_lines) {
            char** tmp = realloc(lines, (max_lines + 1) * sizeof(char*));
            if (tmp != NULL) {
                lines = tmp;
                ++max_lines;
            }
        }

        char* line = malloc(strlen(buffer) + 1);
        memset(line, 0, strlen(buffer) + 1);
        strcpy(line, buffer);
        lines[num_lines++] = line;

        --count;
    }

    for (int i = 0; i < max_lines; ++i) {
        printf("%s", lines[i]);
    }

    for (int i = 0; i < max_lines; ++i) {
        free(lines[i]);
    }
    free(lines);

    return 0;
}
```

### [문제19: 단어 정렬 구현]

- qsort()는 배열 속에 저장된 각 요소들의 주소를 전달해 줌
  - char*의 주소니까 char**로 캐스팅해야함

```c++
enum {
    NUM_WORDS = 6
};

int compare_string(const void* a, const void* b)
{
    char** p_str_a = (char**)a; // words의 요소의 주소가 인자로 입력, &words[i]
    char** p_str_b = (char**)b;
    char* str_a = *p_str_a;
    char* str_b = *p_str_b;

    while (*str_a != '\0') {
        if (*str_a != *str_b) {
            break;
        }
        ++str_a;
        ++str_b;
    }

    return *str_a - *str_b;
}

int compare_string_desc(const void* a, const void* b)
{
    char** p_str_a = (char**)a;
    char** p_str_b = (char**)b;
    char* str_a = *p_str_a;
    char* str_b = *p_str_b;

    while (*str_a != '\0') {
        if (*str_a != *str_b) {
            break;
        }
        ++str_a;
        ++str_b;
    }

    return *str_b - *str_a;
}

int main(void)
{
    size_t i;
    const char* words[NUM_WORDS] = {
            "premium", "level", "cultured",
            "moaning", "skinny", "curve"
    };  // 정렬할 단어 6개

    puts("\n== sort ascending ==");

    qsort(words, NUM_WORDS, sizeof(char*), compare_string);

    for (i = 0; i < NUM_WORDS; ++i) {
        printf("%s\n", words[i]);
    }

    puts("\n== sort descending ==");

    qsort(words, NUM_WORDS, sizeof(const char*), compare_string_desc);
    for (i = 0; i < NUM_WORDS; ++i) {
        printf("%s\n", words[i]);
    }

    return 0;
}
```

- 비교함수 아래와 같이 구현해도 됨

```c++
int compare_string(const void* a, const void* b)
{
    const char** w0 = (const char**)a;
    const char** w1 = (const char**)b;

    return strcmp(*w0, *w1);
}

int compare_string_desc(const void* a, const void* b)
{
    const char** w0 = (const char**)a; // strcmp의 매개변수가 const char*로 받기 때문
    const char** w1 = (const char**)b;

    return strcmp(*w1, *w0);    
}
```