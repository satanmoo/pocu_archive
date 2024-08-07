# Week7

## 암기

### 가변인자 사용법

```c++
int add_ints(const size_t param1, const int param2, ...)
{
    va_list ap;
    size_t i;
    int sum;

    va_start(ap, param2);
    {
        for (i = 0; i < count; i++) {
            sum += va_arg(ap, int);
        }
    }
    va_end(ap);

    return sum;
}
```

- va_list 자료형을 선언
- va_start(<가변인자목록>, <가변인자 시작 전 마지막 매개변수>)
  - 가변인자를 사용하기 위해 초기화
  - 가변인자 시작 전 마지막 매개변수로 가변인자가 스택 메모리 어디에서 시작하는지 알아냄
- 사용하기
  - va_arg(<가변인자목록>, 가변인자 자료형(int/double))
  - 자료형으로 메모리에서 구조체의 멤버 읽듯이 읽음
  - 표준상 승격이 발생
    - 정수형은 int
    - 부동소수점형 double
- va_end(<가변인자목록>)
  - 가변인자목록 정리
  - 더 이상 사용할 수 없음

### 구조체를 가변인자에 넣기

```c++
typedef struct {
    int num;
    float f;
} number_t;

void do_something(const size_t count, ...)
{
    va_list ap;
    number_t n;

    va_start(ap, count);
    {
        n = va_arg(ap, number_t);
        /* 멋진 코드를 여기에 호호호 */
    }
    va_end(ap);
}

int main(void) {
    number_t nums = { 10, 3.14f };
    do_something(1, nums);

    return 0;
}
```

- 이 때 n의 자료형은?
  - number_t로 읽었으니, number_t
  - 기본 자료형이 아니라서 승격은 없음

### C에는 실행 중 자료형을 판단하는 기준이 있나요? (O/X)

- 없음

### 함수 호출 규약과 가변 인자

- 함수 호츌 규약에 따라 리시버는 가변 인자가 아닌 매개 변수를 알 수 있음
- 전처리기는 매크로 함수 va_start(<가변인자>, <마지막 매개변수>)를 변환
- 변환된 코드를 실행하면 리시버는 <마지막 매개변수>의 주소를 통해 <가변인자>의 시작 주소를 알 수 있음
- 전처리기는 매크로 함수 va_arg(<가변인자>, <자료형>)를 변환
- 변환된 코드를 실행하면 리시버는 <자료형>을 통해 <가변인자>를 자료형에 맞게 읽을 수 있음
  - 포인터 형 변환

### 컴파일 타임에 가변 인자 함수는 가변 인자가 몇 개인지 알 수 있나요?

- NO
- 모름
- 그래서 매개변수로 가변 인자의 수를 넘겨줘야함
  - 마치 배열의 개수를 컴파일 타임에 모르는 것과 같음

## 코드

### 이 코드의 문제점은?

```c++
int add(int op1, int op2);
int add(int op1, int op2, int op3);
```

- C에서 함수 오버로딩 지원 X

```shell
main.c:8:5: error: conflicting types for 'add'
int add(int op1, int op2, int op3)
^
main.c:3:5: note: previous definition is here
int add(int op1, int op2)
^
```

### 다음 코드가 컴파일 되나요?

```c++
int add_ints(const int param1, ..., const char param2)
{
    va_list ap;
    size_t i;
    int sum;

    va_start(ap, param2);
    {
        for (i = 0; i < count; i++) {
            sum += va_arg(ap, int);
        }
    }
    va_end(ap);

    return sum;
}
```

- 안 됨
- 가변인자는 반드시 매개변수 중 마지막에 위치해야함
- 