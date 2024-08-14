# week12

## 암기

### [C에서 라이브러리]

- 오브젝트 파일을 모아서 `라이브러리`로 만듦
- 배포한 라이브러리를 사용할 때 다시 컴파일 할 필요가 없음
    - 오브젝트 파일은 컴파일의 결과물이기 때문
    - 코드의 재활용
- 배포할 때 소스 코드를 공개하지 않아도 됨
    - 컴파일된 결과인 오브젝트 파일의 패키지로 공개하기 때문
    - 사용자가 라이브러리의 함수를 사용할 때 필요한 함수 시그니처가 포함된 헤더 파일은 공개해야함

### [정적 링킹]

- 정적 링킹
    - 정적 라이브러리 안에 있는 기계어를 최종 실행파일에 복사
- 장점
    - 실행 속도가 빠름
    - 해킹에서 안전
    - 최적화에 유리
        - 정적 링킹 과정에서 링커가 최적화 가능
        - 정적 라이브러리를 사용하는 소스 코드에서 어떤 정적 라이브러리의 함수를 호출하는지 알 수 있음
- 단점
    - 실행 파일의 크기가 커짐
    - 메모리를 더 많이 consume
    - CPU 세대 별로 실행 파일만들어 배포
    - 라이브러리의 소스 코드가 변경되면, 실행 파일도 수정해서 배포
    - 실행 중 다른 실행 파일과 라이브러리 공유 불가

### [정적 라이브러리 사용 절차]

1. 소스 코드를 컴파일해 오브젝트 파일을 만들고, 모아서 라이브러리 만듦
    - 확장자
        - 윈도우: *.lib
        - 리눅스: *.a (`아카이브`라고 부름)
2. 다른 소스 코드들을 작성할 때 정적 라이브러리의 헤더 파일을 사용
    - 이 소스 코드들은 실행 파일을 만들기 위해 main() 함수를 가지고 있는게 보통
        - 또 다른 라이브러리를 만드는 용도면, main() 함수가 없을 수도..
    - 정적 라이브러리의 함수를 사용하는 입장이라서, 이 소스 코드가 변경되도 정적 라이브러리를 다시 만들 필요 없음
3. 컴파일 과정에서 정적 라이브러리와 함께 `링킹`

### [오브젝트 파일을 생성하는 clang 명령어]

```shell
clang -std=c89 -W -Wall -pedantic-errors -c example.c -o example.o
```

### [정적 라이브러리 파일을 만드는 명령어]

- 플랫폼 마다 정적 라이브러리 파일을 만드는 프로그램이 다름
    - 윈도우: llvm-ar
    - 리눅스: ar

```shell
llvm-ar - 명령어<modifier> 정적_라이브러리_파일 <o파일들>
```

- 명령어
    - r: 정적_라이브러리_파일에 O파일들을 추가
    - d: 정적_라이브러리_파일에 O파일들을 제거
- modifier
    - c: 정적 라이브러리 파일이 처음 만들어질 때 경고 메시지 출력 X
        - 추가가 아니라 처음 만들여져서 경고가 발생하는데, 이 경고 메시지 출력을 막음

```shell
llvm-ar -rc simple_math.lib *.o
```

### [프로젝트]

- 라이브러리를 만들 때 어떤 파일들을 컴파일해야 하는지, 실행파일을 만들 때 어떤 소스코드와 어떤 라이브러리를 합쳐야하는지 등을 적어두는 파일
    - CMake

### [동적 링킹]

- 실행 파일에 구멍(place holder)을 남겨둠
- 실행 파일을 실행할 때 운영체제에서 링킹해줌
- 장점
    - 실행 파일의 크기가 작음
    - 메모리를 적게 소모함
    - 실행 파일을 바꾸지 않고 동적 라이브러리만 업데이트 가능
    - 동적 라이브러리 파일을 바꾸지 않고 실행파일만 업데이트 가능
    - 동적 라이브러리 파일 선택적 로딩
        - CPU 세대별로 동적 라이브러리 파일 만들어 두고 로딩
- 단점
    - 속도가 느림
    - DLL 지옥
    - DLL 인젝션(해킹)
        - 헤더 파일의 함수 시그니처만 알면 내가 만든 해킹 DLL 파일을 운영체제가 로드(링킹)하게 만들 수 있음

### [inline 키워드의 기능]

- 컴파일러가 힌트를 받아들이면 매크로 함수처럼 코드를 복붙
    - 함수 호출이 사라져, 함수 호출에 따른 오버헤드를 없앨 수 있음

### [inline 키워드는 컴파일러에게 `힌트`]

- 컴파일러가 무시할 수도 있음
- inline 키워드가 없어도 최적화 해줄 수도 있음

### [인라인 함수를 복사하려면 헤더 파일에 구현이 필요함]

- 매크로 함수처럼 `컴파일 단계`에서 함수의 구현을 복붙하기 위해서는 헤더 파일에 함수 선언 + 함수 구현 모두 필요함
    - 전처리, 컴파일, 어셈블, 링킹에서 컴파일 단계

### [인라인 함수 구현 정석]

```c++
// simple_math.h
inline int add(int op1, int op2)
{
    return op1 + op2;
}
```

```c++
// simple_math.c
#include "simple_math.h"

extern inline int add(int op1, int op2);
```

```c++
// humanoid.c
#include "simple_math.h"

void walk(...)
{
    // 코드 생략
}
```

```c++
// bird.c
#include "simple_math.h"

void fly(...)
{
    // 코드 생략
}
```

- .h 파일에 인라인 함수를 구현
    - 함수 선언 + 정의
    - 전처리기는 #include를 헤더 파일의 코드로 대체함
- 그에 대응하는 .c 파일을 만듬
  - .c 파일에서 인라인 함수가 들어있는 .h파일 인클루드
  - .c 파일에서 인라인 함수를 extern 인라인 함수로 다시 선언
      - 구현부는 필요 없음
      - extern의 효과는 컴파일 단계에서 함수 정의(링커 입장에서 심볼)을 만들어 줌
      - 이 .c 파일에서만 함수의 심볼이 나옴(오직 1개)
      - 따라서 인라인이 적용 안 되면 이 심볼을 이용해서 링킹
      - 인라인이 적용되면, 헤더 파일의 구현을 사용

### [static inline 함수]

- 컴파일러
  - 인라인화 되지 않는 경우
    - 내부 링킹 함수(심볼) 생성
    - 함수 호출 시 내부 링킹 함수 호출
  - 인라인화 되는 경우
    - 심볼 생성 X
    - 함수 호출 코드를 인라인 함수로 교체

```c++
#ifndef STATIC_MATH_H
#define STATIC_MATH_H

static inline int static_add(int a, int b)
{
    return a + b;
}

#endif /* STATIC_MATH_H */
```

```c++
// adder.c
#include <stdio.h>

#include "adder.h"
#include "inline_math.h"
#include "static_math.h"

void run_adder(void)
{
    printf("1 + 2 = %d\n", static_add(1, 2));
    printf("addr of static_add: %p\n",
        (void*)static_add);

    printf("1 + 2 = %d\n", inline_add(1, 2));
    printf("addr of inline_add: %p\n", 
        (void*)inline_add);
}
```

### [인라인 함수 vs 매크로 함수]

- 인라인 함수
    - 함수 호출에 따른 오버헤드 없음
    - 인라인화 안 될수도 있음
      - 컴파일러 재량
    - 코드를 효율적으로 복붙
    - 함수 호출의 이점을 누림
        - 코드 읽기 편함
        - 디버깅 편함
- 매크로 함수
    - 함수 호출에 따른 오버헤드 없음
    - 반드시 복붙
    - 무식한 복붙
    - 함수 호출의 이점을 누릴 수 없음
        - 코드 읽기 불편함
        - 디버깅 너무 어려움

### [restrict 키워드의 효과]

- 컴파일러에게 메모리 범위가 겹치는 포인터가 아니라고 힌트를 줌
    - restrict 키워드는 포인터 변수에만 붙일 수 있음
- 컴파일러의 방어적 구현을 없애고, 성능을 올릴 수 있음

- 정의되지 않은 결과 때문에 컴파일러가 방어적으로 구현되어 있기 때문에 생긴 키워드
- 컴파일러가 힌트를 무시할 수 있음
    - 힌트를 무시하면 여전히 방어적으로 구현
    - 힌트를 무시하지 않으면 방어적으로 구현하지 않고 최적화로 성능이 좋아짐
- 여전히 함수 호출자가 비상식적으로 범위가 겹치는 포인터를 전달할 수 있음
    - 컴파일러가 이 키워드에 따라 최적화를 하면 컴파일러의 방어적인 구현이 작동하지 않고, 성능이 좋아짐
        - 이 경우 정의되지 않은 결과
    - 컴파일러가 이 키워드를 무시해 방어적인 구현이 작동하면, 성능은 저하
        - 정의되지 않은 결과를 막을 수 있음

### [snprintf() 문제가 발생하는 경우]

- 두번째 매개변수 bufsz의 값이 0일 때 널문자를 붙여주지 않음
  - buffer을 선언할 때 미리 '\0'으로 초기화하는 것도 좋음
- 첫번째 매개변수 buffer, 세번째 매개변수 format이 NULL이면 문제 발생
  - NULL 포인터에서는 리디렉션으로 값을 읽어올 수 없기 때문

## 코드

### [다음 코드가 컴파일되나요? 컴파일 되지 않는 이유는?]

```c++
// simple_math.h
int add(int op1, int op2)
{
    return op1 + op2;
}
```

```c++
// humanoid.c
#include "simple_math.h"

void walk(...)
{
    // 코드 생략
}
```

```c++
// bird.c
#include "simple_math.h"

void fly(...)
{
    // 코드 생략
}
```

- 컴파일되지 않음
- 전처리 단계에서 simple_math.h를 복붙
- 컴파일 단계에서 humanoid.c, bird.c에서 각각 심볼 생성
    - add() 함수를 호출하는 코드는 구멍(placeholder)로 남겨둠
- 링킹단계에서 bird.o, humanoid.o 파일에 add() 함수의 심볼이 있고, 링커는 함수 호출 코드를 어떤 심볼과 링킹하는지 알 수 없음!

### [다음 코드의 inline 키워드가 컴파일 단계에서 적용되지 않는다면 어떤 일이 발생하나요? 실행 파일이 생성될까요?]

```c++
// simple_math.h
inline int add(int op1, int op2)
{
    return op1 + op2;
}
```

```c++
// humanoid.c
#include "simple_math.h"

void walk(...)
{
    // 코드 생략
}
```

```c++
// bird.c
#include "simple_math.h"

void fly(...)
{
    // 코드 생략
}
```

- 실행 파일이 생성되지 않음
- 링킹 오류 발생
- 전처리 단계에서 simple_math.h를 복붙
- 컴파일 단계에서 inline 키워드가 붙은 함수는 심볼을 생성하지 않음
- 인리안화 되는 경우
  - 문제 없음
- 인라인화 되지 않는 경우
  - 링킹 단계에서 심볼(함수의 구현)을 찾을 수 없어서 오류가 발생

### [가변인자를 이용해 분산을 구하는 함수 구현하기]

```c++
double get_variance(int count, ...)
{
    va_list arg_list_avg;
    // 가변 인자 목록 arg_list_avg에 접근하기 전 반드시 호출

    va_list arg_list_v;
    // 가변 인자 목록 arg_list_avg을 가변 인자 목록 arg_list_v에 복사

    double avg = 0.0;
    for (size_t i = 0; i < count; ++i) {
        // 가변 인자 목록 arg_list_avg에서 다음 가변 인자를 가져올 때 자료형을 명시해야함
    }
    avg /= count;
    // 가변 인자 목록 arg_list_avg 사용 끝

    double variance = 0;
    for (size_t i = 0; i < count; ++i) {
        // 복사한 가변 인자 목록 arg_list_v에서 다음 가변 인자를 가져옴
    }
    variance /= count;
    // 복사한 가변 인자 목록 arg_list_v 사용 끝

    return variance;
}

// main 함수
printf("variance: %f\n", get_variance(4, 10.0, 20.0, 30.0, 40.0));
```

```c++
double get_variance(int count, ...)
{
    va_list arg_list_avg;
    va_start(arg_list_avg, count);  // 가변 인자 목록 arg_list_avg에 접근하기 전 반드시 호출

    va_list arg_list_v;
    va_copy(arg_list_v, arg_list_avg);  // 가변 인자 목록 arg_list_avg을 가변 인자 목록 arg_list_v에 복사

    double avg = 0.0;
    for (size_t i = 0; i < count; ++i) {
        double num = va_arg(arg_list_avg, double);  // 가변 인자 목록 arg_list_avg에서 다음 가변 인자를 가져올 때 자료형을 명시해야함
        avg += num;
    }
    avg /= count;
    va_end(arg_list_avg);   // 가변 인자 목록 arg_list_avg 사용 끝

    double variance = 0;
    for (size_t i = 0; i < count; ++i) {
        double num = va_arg(arg_list_v, double);    // 복사한 가변 인자 목록 arg_list_v에서 다음 가변 인자를 가져옴
        variance += (num - avg) * (num - avg);
    }
    variance /= count;
    va_end(arg_list_v); // 복사한 가변 인자 목록 arg_list_v 사용 끝

    return variance;
}

// main 함수
printf("variance: %f\n", get_variance(4, 10.0, 20.0, 30.0, 40.0));
```

- va_copy(dest, src) 를 호출하기 전 va_start(src, <바로 직전 매개변수>)를 호출해야함!
- va_copy(dest, src) 를 호출한 후 va_end(dest)를 호출해야함

### [다음 코드가 컴파일된다면 출력 결과는?]

```c++
int main(void)
{
    char buffer[10];
    const char* name = "poo bao";
    int score = 100;

    snprintf(buffer, 10, "%s's score is %d\n", name, score);
    buffer[9] = '\0';

    printf("%s", buffer);

    return 0;
}
```

- snprintf의 두번째 매개변수 bufsz - 1 만큼 문자열을 첫번째 매개변수에 복사함
- bufsz번째 문자, 즉 buffer[bufsz - 1]은 널문자로 채워줌
  - 헷갈리니까 buffer[bufsz - 1] = '\0'; 직접 대입해도 됨

- 출력

```shell
poo bao's
```
