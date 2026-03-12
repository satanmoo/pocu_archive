# week11

## 암기

### [문자열을 KEY로 사용하는 해시 맵(셋)에서 2가지 종류의 충돌이 발생함 각각에 대해서 설명]

- 해시 충돌
  - 해시값이 동일함
  - 즉 문자열을 해시 함수에 넣은 결과가 동일
- 배열의 사이즈에 맞게 나눠 색인값을 구했을 때 색인이 충돌
  - 해시값이 같으면 당연히 색인도 동일함
  - 해시값이 달라도 배열의 크기로 나눴을 때 색인이 동일해서 충돌
- 해결법: 연결 리스트, 색인 1칸씩 이동

### [문자열을 KEY로 저장하는 해시 맵(셋)에서 해시 충돌을 막으면 이점?]

- 문자열을 KEY로 저장할 필요가 없음
  - 문자열로 해시값을 만들어, 해시값을 KEY로 사용하면 됨
- 즉 문자열을 KEY로 저장할 때 보다 동적 메모리 할당을 줄일 수 있음
  - 해시값은 기본 자료형(정수)를 사용

- 해시 맵(셋)에 저장하는 함수 시그니처의 변화로 이해

```c++
int add(const char* key, int (*hash_func)(const char*), ? value);

int add(int hash_key, ? value);
```

### [전처리기로 할 수 있는 일]

- 다른 파일 인클루드
  - #include
- 매크로를 다른 텍스트로 대체
  - #define, #undef, 전처리기 연산자 #,##를 사용
- 소스 파일의 일부를 조건부로 컴파일
  - #if, #ifdef, #ifndef, #else, #elif, #endif
- 일부로 오류를 발생
  - #error

### [매크로 대체: #define A vs #define A (10)]

- 후자
  - 다른 전처리기 지시어로 A가 정의(define)돼 있는지 판단 가능
  - 전처리기가 소스 코드를 뒤져서 A를 모두 (10)으로 바꿔줌
- 전자
  - 다른 전처리기 지시어로 A가 정의(define)돼 있는지 판단 가능
    - A은 단순 공백으로 대체
- A를 `식별자`라고 부름

### [매크로 대체: #undef]

- 이미 정의된 식별자를 없앰

### [조건부 컴파일에서 주의할 점: #if 식별자 vs #if defined(식별자)]

- #if 식별자
  - 식별자의 값으로 참/거짓 판단
```c++
#if A == 10
#define B
#elif A == 1
#define C
#else
#define D (10)
#endif

```

- #if defined(식별자)
  - `#ifdef 식별자`와 동일
  - 식별자가 정의됬다면 참, 아니면 거짓
    - #define 식별자가 위에 있으면 참

### [조건부 컴파일: 버전 관리]

- `#ifdef 식별자`와 `#endif` 사이의 코드는 조건을 만족하면 코드에 남아있음, 아니면 사라짐
  - `#if defined(식별자)` == `#ifdef 식별자`

```c++
#define VERSION_2

#ifdef VERSION_1
#define A (10)
#elif defined(VERSION_2)
#define B (2)
#else
#define A (12)
#endif
```

### [컴파일 도중에 매크로 정의하기]

- -D 옵션
- 컴파일 도중에 매크로를 정의할 수 있음

```shell
clang -std=c89 -W -Wall -pedantic-errors -DA *.c
```

- `#define A (1)`과 똑같은 결과

```shell
clang -std=c89 -W -Wall -pedantic-errors -DA=52 *.c
```

- `#define A (52)`과 똑같은 결과

### [배포용으로 컴파일 하기: -DNDEBUG]

```shell
clang -std=c89 -W -Wall -pedantic-errors -DNDEBUG *.c
```

- `#define NDEBUG (1)`과 똑같은 결과
- `#if !defined(NDEBUG)`와 `#endif` 사이에 디버그 모드에서만 실행될 코드를 넣으면 됨 

### __asm { int 3 }

- x86 어셈블리에서 프로그램 실행을 중지하는 인터럽트

### 어서트를 재정의(오버로딩)할 때 장점

```c++
#define ASSERT(condition, msg) \
if (!(condition)) { \
    fprintf(stderr, "%s(%s: %d)\n", msg, __FILE__, __LINE__); \
    __asm { int 3 } \
}
```

- <assert.h>의 assert()함수는 실패하면 콜 스택의 현재 위치가 assert() 함수 속
- __asm { int 3 }는 실제로 어서트에 실패한 코드가 호출 스택의 현 위치
  - 사람이 읽기 편한 설명도 추가할 수 있음

### 코드보기: 전처리를 이용한 튜플

```c++
#include <stdio.h>

/* id(int), "name"(const char*), hp(int) */
#define MONSTER_DATA \
    MONSTER_ENTRY(0, "pope",    100)   \    
    MONSTER_ENTRY(1, "big rat", 30)    \
    MONSTER_ENTRY(2, "mama",    255)   \
    MONSTER_ENTRY(3, "dragon",  300000)\

int main(void)
{
    size_t i;

    int ids[] = {
#define MONSTER_ENTRY(id, name, hp) id, // id,와 name 사이 공백, name,와 hp사이 공백의 개수는 중요하지 않음, 전처리기에서 적절하게 하나의 공백으로 대체
        MONSTER_DATA
#undef MONSTER_ENTRY
    };

    const char* names[] = {
#define MONSTER_ENTRY(id, name, hp) name,
        MONSTER_DATA
#undef MONSTER_ENTRY
    };

    int healths[] = {
#define MONSTER_ENTRY(id, name, hp) hp,
        MONSTER_DATA
#undef MONSTER_ENTRY
    };

    for (i = 0; i < sizeof(ids) / sizeof(int); ++i) {
        printf("%3d %6d %s\n",
            ids[i], healths[i], names[i]);
    }

    return 0;
}
```

- 순서대로
- #define MONSTER_ENTRY(id, name, hp) id, 정의
- MONSTER_DATA 확장
  ```shell
  MONSTER_ENTRY(0, "pope", 100)
  MONSTER_ENTRY(1, "big rat", 30)
  MONSTER_ENTRY(2, "mama", 255)
  MONSTER_ENTRY(3, "dragon", 300000)
  ```
- 각 MONSTER_ENTRY(id, name, hp) 치환
  ```shell
  0,
  1,
  2,
  3,
  ```

## 코드 문제

### [문제1: 해시 테이블에 값이 저장되어 있는지 확인하는 함수를 구현하기]

```c++
#include <limits.h>

#define TRUE (1)
#define FALSE (0)
#define BUCKET_SIZE (23)
int s_numbers[BUCKET_SIZE];

void init_hashtable(void)
{
    size_t i;

    for (i = 0; i < BUCKET_SIZE; ++i) {
        s_numbers[i] = INT_MIN;
    }
}
```

```c++
int has_value(int value)
{
    int i;
    int start_index;

    start_index = value % BUCKET_SIZE;
    if (start_index < 0) {
        start_index += BUCKET_SIZE;
    }   /* int라 음수가 들어올 수도 있어서 */

    i = start_index;

    do {
        if (s_numbers[i] == value) {
            return TRUE;
        } else if (s_numbers[i] == INT_MIN) {
            return FALSE;   /* 이 코드가 작동할 수 있는 이유는, s_numbers에서 원소가 지워지지 않기 때문 */
        }

        i = (i + 1) % BUCKET_SIZE;
    } while (i != start_index);
    
    return FALSE;
}
```

- 로직은 아래와 같다.
- value % BUCKET_SIZE로 나머지값을 구함. (참고로 이 나머지값으로 배열에서 검색을 시작할 것이라 변수명을 `start_index`라고 지었음)
- 배열에서 start_index 색인의 값 확인
  - 일치하면 TRUE 반환 (일치 여부를 알아야하기 때문에 배열에 원본값을 그대로 저장함)
  - 비어있으면 FALSE 반환 (빈 여부를 표시하기 위해 INT_MIN값으로 초기화)
  - 값이 있는데, 원본값과 다르면 start_index에서 1씩 더함 (유효한 색인의 범위를 유지한다는 조건을 만족하면서 증가)
    - 종료 조건은 배열을 한 바퀴 돌았을 경우 (해시 테이블에서 최악의 경우 시간 복잡도 O(N)인 이유)

### [문제2: 해시 테이블에 값을 저장하는 함수 구현하기]

- 문제1과 이어지는 문제

```c++
int add(int value)
{
    int i;
    int start_index;

    start_index = value % BUCKET_SIZE;
    if (start_index < 0) {
        start_index += BUCKET_SIZE;
    }

    i = start_index;

    do {
        if (s_numbers[i] == value || s_numbers[i] == INT_MIN) {
            s_numbers[i] = value;   /* 중복되는 값이 있거나, 빈 경우 덮어씀 */

            return TRUE;
        }

        i = (i + 1) % BUCKET_SIZE;  /* 충돌한 경우 인덱스 이동 */
    } while (i != start_index);

    return FALSE;
}
```

- 로직은 아래와 같다.
- value % BUCKET_SIZE로 나머지값을 구함
- 배열에서 start_index 색인의 값 확인 (여기까지는 has_value() 함수와 동일)
  - value가 이미 존재하면 덮어씀 (안 덮어써도 결과는 동일, hash table에서 중복을 제거함을 알 수 있음)
  - 비었으면 배열에 저장
  - 값이 있는데, 원본값과 다르면 start_index에서 1씩 더함 (유효한 색인의 범위를 유지한다는 조건을 만족하면서 증가)
    - 종료 조건은 배열을 한 바퀴 돌았을 경우 (has_value() 함수와 동일)
- 배열에 성공적으로 저장했는지 여부를 반환
  - 성공적으로 저장하면 TRUE
  - 배열이 꽉차서 실패하면 FALSE

### [문제3: 해시 함수 65599 구현]

```c++
/* 65599를 곱해나가는 해시 함수
 * (출처: Compilers: Principles, Techniques, and Tools)
 */
size_t hash_65599(const char* string, size_t len)
{
    size_t i;
    size_t hash;

    hash = 0;
    for (i = 0; i < len; ++i) {
        hash = 65599 * hash + string[i];
    }

    return hash ^ (hash >> 16);
}
```

### [문제4: KEY: 문자열, VALUE: int 해시 맵에 저장하는 함수 구현]

```c++
enum {
    BUCKET_SIZE = 23,
    TRUE = 1,
    FALSE = 0
};

char* s_keys[BUCKET_SIZE];
int s_values[BUCKET_SIZE];

int add(const char* key, int value, size_t (* hash_func)(const char*, size_t))
{
    size_t i;
    size_t start_index;
    size_t hash_id;

    hash_id = hash_func(key, strlen(key));
    start_index = hash_id % BUCKET_SIZE;
    i = start_index;

    do {
        if (s_keys[i] == NULL) {    // empty
            s_keys[i] = malloc(strlen(key) + 1);
            strcpy(s_keys[i], key);
            s_values[i] = value;
            return TRUE;
        }

        if (strcmp(s_keys[i], key) == 0) {
            s_values[i] = value;    // input new value
            return TRUE;    // if the key already exists
        }
        i = (i + 1) % BUCKET_SIZE;  // if another key already exists
    } while (i != start_index); // exit when bucket is full

    return FALSE;
}
```

- 로직은 위의 해시 테이블과 동일
- 매개변수로 해시 함수를 입력 받아 함수 내부에서 해시값을 구하는 것이 차이점

```c++
int add_fast(size_t hash_key, const char* value)
{
    size_t i;
    size_t start_index;

    start_index = hash_key % BUCKET_SIZE;
    i = start_index;

    do {
        if (s_keys[i] == INT_MIN) { // empty
            /* 새 해시(키)-값 삽입 */
            return TRUE;
        }

        if (s_keys[i] == hash_key) {    // if the key alredy exists
            /* 새 해시(키)-값 삽입 */
            return TRUE;
        }

        i = (i + 1) % BUCKET_SIZE;  // if another key alredy exists
    } while (i != start_index);

    return FALSE;
}
```

- 해시 충돌이 없으면, 해시 함수를 함수 포인터 매개변수로 받을 필요도 없음
- value의 타입이 const char*라서 value를 저장하는 배열의 요소에 동적할당 필요함
  - 동적할당을 피하려면, key나 value 모두 기본 자료형으로 설정하면 됨

### [문제5: 여러 데이터형에 맞는 해시 함수 구현하기]

```c++
#include <stddef.h>

#include "hash_function.h"

size_t hash_int(int value)
{
    return value;
}

size_t hash_float(float value)
{
    return *((size_t*)&value);
}

size_t hash_data(const void* data, size_t length)
{
    const char* p;
    size_t i;
    size_t hash;

    p = data;
    hash = 0;
    for (i = 0; i < length; ++i) {
        hash = 65599 * hash + *p++;
    }

    return hash ^ (hash >> 16);
}
```

- int: 그대로
- float: 포인터 형변환으로 비트패턴을 그대로 읽기
  - 그냥 형변환하면 문제가 생김 컴파일러가 정수형으로 소수점 아래를 모두 절삭하기 때문
  - 비트 패턴을 유지하되, 정수로 변경하는 법이 포인터 캐스팅
- 문자열: 65599 해시함수
  - 문자열은 범용적인 데이터라, 구조체 등등 어떤 데이터에도 동일한 해시함수 적용 가능
  - 그래서 매개변수의 타입이 const void*
  - hash값을 구하는 연산은 1바이트 단위

```c++
typedef struct {
    unsigned char age;
    unsigned int id;
    char name[64];
} employee_t;

memset(&person, 0, sizeof(employee_t));
person.age = 23;
person.id = 18274192;
strcpy(person.name, "Pope Kim");

hash = hash_data(&person, sizeof(employee_t));
printf("struct %u\n", hash);
```

### [문제6: 조건부 컴파일을 이용해 주석 처리를 편하게]

```c++
#if 0
int numIdenticalPairs(int* nums, int numsSize)
{
    int hash_table[SIZE + 1];
    int i;
    int res = 0;

    memset(hash_table, 0, SIZE + 1);

    for (i = 0; i < numsSize; ++i) {
        int key = nums[i];
        int value = hash_table[key];
        hash_table[key] = value + 1;
    }

    for (i = 1; i <= SIZE; ++i) {
        if (hash_table[i] > 1) {
            res += get_comb(hash_table[i]);
        }
    }

    return res;
}
#endif
```

- #if 0, #endif
- 조건부 컴파일에서 조건을 거짓으로 만들어서 주석처리

### [문제7: 다음 코드가 컴파일이되는가? 된다면 출력값은?]

```c++
#define VERSION 10

#if VERSION != 11
#error "unsupported version"
#endif

int main(void) {
    printf("%s", "Hello World!");
    
    return 0;
}
```

- 컴파일되지 않음
- VERSION이 10으로 변경
- #if 10 != 11은 #if 1과 동일함
- 참이기 때문에 #error 코드가 남아있게 됨, 에러 발생

### [문제8: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define ADD(a, b) a + b

int main(void) {
    int num1 = 10;
    int num2 = 20;
    int result;

    result = 3 * ADD(num1, num2);
    printf("%d\n", result);

    return 0;
}
```

- 컴파일 됨
- result == 3 * 10 + 20 == 50

- 출력값
```shell
50
```

- 이런 실수를 막기 위해서는 소괄호로 감싸주자!

### [문제9: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define ADD(a, b) a + b
#define POW(n, p, i, r) r = 1; \
                     for (i = 0; i < p; ++i) { \
                        r *= n;       \
                     }

int main(void)
{
    int num1 = 10;
    int num2 = 20;
    int result;
    int i;

    result = 3 * ADD(num1, num2);
    result = POW(result, 2, i, result);
    printf("%d\n", result);

    return 0;
}
```

- 메크로를 치환해보자

```shell
result = 1;
for (i = 0; i < 2; ++i) {
    result *= result;
}
```

- result의 값은 1이됨
- 출력값은 아래와 같다.

```shell
1
```

### [문제10: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define str(s) #s

int main(void)
{
    int num1 = '\n';

    printf("%s\n", str(num1));

    return 0;
}
```

- 컴파일 됨
- 출력값은 아래와 같음

```c++
num1
```

- str(s)를 #s로 변경
  - #num1은 "num1"과 동일함
- printf("%s\n", "num1");

### [문제11: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define str(s) #s

int main(void)
{
    printf("%s", str(\n));
    printf("%s", str("\n"));

    return 0;
}
```

- 컴파일 됨
- 출력값은 아래와 같음

```c++

"\n"
```

- 첫번째 printf는 다음과 같음
  - printf("%s", "\n");
- 두번째 printf는 다음과 같음
  - printf("%s", "\"\\n\"");
  - 알아서 이스케이프 문자를 넣어줌

### [문제12: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define print(n) printf("%d\n", id_##n)

int main(void)
{
    int id_1 = 1;
    int id_2 = 2;

    print(1);
    print(2);

    return 0;
}
```

- 컴파일 됨
- 출력은 다음과 같음

```c++
1
2

```

- #define 식별자(매개변수) 대체_목록
- 매개변수를 대체_목록 안의 매개변수와 교체
- #과 달리 문자열 데이터를 만드는 것이 아님

### [문제13: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define combine1(a, b) (a#b)
#define combine2(a, b) (a##b)

int main(void)
{
    /* 메인 함수 */
    int student_id = 987654;

    printf("%d\n", combine1(student_, id));

    return 0;
}
```

- 컴파일 오류
- student_"id"로 변경됨
  - printf("%d\n", student_"id")는 문법상 맞지 않음

### [문제14: 다음 코드가 컴파일되는가? 된다면 결과는?]

```c++
#include <stdio.h>

#define combine1(a, b) (a#b)
#define combine2(a, b) (a##b)

int main(void)
{
    /* 메인 함수 */
    int student_id = 987654;

    /* 컴파일: student_id의 값인 987654를 출력 */
    printf("%d\n", combine2(student_, id));

    return 0;
}
```

- 컴파일 됨
- printf("%d\n", student_id);
  - 문법상 문제 없음

### [문제15: 다음 코드에서 전처리 과정 설명하기]

```c++
#include <stdio.h>

/* (type, name) */
#define MONSTER_STRUCT \
    MONSTER_MEMBER(int,           id)    \
    MONSTER_MEMBER(const char*,   name)  \
    MONSTER_MEMBER(int,           hp)    \

typedef struct {
#define MONSTER_MEMBER(type, name) type name;
    MONSTER_STRUCT
#undef MONSTER_MEMBER
} monster_t;

#define MONSTER_MEMBER(type, name)       \
type get_mob_##name(const monster_t* mob)\
{                                        \
    return mob->name;                    \
}                                        \

MONSTER_STRUCT

#undef MONSTER_MEMBER

int main(void)
{
    monster_t mob;
    mob.id = 0;
    mob.name = "Pope Mob";
    mob.hp = 10001;

    printf("%3d %6d %s\n",
        get_mob_id(&mob),
        get_mob_hp(&mob),
        get_mob_name(&mob));

    return 0;
}
```

- part1

```c++
typedef struct {
#define MONSTER_MEMBER(type, name) type name;
    MONSTER_STRUCT
#undef MONSTER_MEMBER
} monster_t;
```

- #define MONSTER_MEMBER(type, name) type name;
  - 매크로 함수 정의
  - 매개변수 type, name의 값이 type name;으로 변경
- MONSTER_STRUCT 아래와 같이 치환

```c++
typedef struct {
    int id;
    const char* name;
    int hp;
} monster_t;
```

- #undef MONSTER_MEMBER 
  - 식별자 삭제

- part2

```c++
#define MONSTER_MEMBER(type, name)       \
type get_mob_##name(const monster_t* mob)\
{                                        \
    return mob->name;                    \
}                                        \

MONSTER_STRUCT

#undef MONSTER_MEMBER
```

- MONSTER_MEMBER(type, name) ...
  - 매크로 함수 정의
  - 매개변수 type은 그대로, name은 대체 목록안의 매개변수와 교체
- MONSTER_STRUCT 아래와 같이 치환

```c++
int get_mob_id(const monster_t* mob)
{
    return mob->id;
}

const char* get_mob_name(const monster_t* mob)
{
    return mob->name ;
}

int get_mob_hp(const monster_t* mob)
{
    return mob->hp;
}
```

- #undef MONSTER_MEMBER
  - 매크로 함수 삭제
