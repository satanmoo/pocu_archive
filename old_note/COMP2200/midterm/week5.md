# Week5

## 암기

### [getchar(), fgetc(FILE* stream)의 반환값]

- int
  - 정상 입력이면 입력받은 문자를 int로 변환해서 반환
  - 비정상 입력이면 EOF 반환
    - EOF 만나는 경우 feof() 세팅
    - 오류가 발생한 경우 ferror() 세팅

```c++
#include <stdio.h>

int main(void)
{
    int c = getchar();  /* int 형 */
    while (c != EOF) {
        fputc(c, stdout);
        c = fgetc(stdin);
    }
    return 0;
}
```

### [gets(char* str)의 위험성]

- 인자로 전달한 str의 크기를 아무리 크게 잡아도, 버퍼 오버플로우가 발생할 수 있음

### [fgets(char* str, int count, FILE* stream), gets(char* str) 개행문자처리 차이]

- gets: stdin에서 개행문자 없애고, 버퍼에 추가하지 않음
- fgets: stdin에서 개행문자 없애고, 버퍼에 추가

### [scanf 너비]

```c++
#include <stdio.h>

int main(void)
{
    int a;
    int b;
    int num;

    num = scanf("%3d%d", &a, &b);
    printf("%d %d %d\n", a, b, num);

    return 0;
}
```

- 입력 종료 조건
  - 숫자가 너비에 도달
  - 숫자 말고 다른 문자가 들어옴
  - 공백문자(\s, \n, \t 등)을 만남

- scanf의 반환값은 몇 개의 데이터를 읽었는지
  - 첫 데이터를 읽기 전에 실패했다면 EOF

[너비에 도달]

```text
123456
>> a == 123, b ==456, num == 2
```

[숫자 말고 다른 문자가 들어옴]

```text
12a46
>> a == 12, b == 쓰레기값, num == 1
```

[공백 문자를 만남]

```text
12 46
>> a == 12, b == 46, num == 2 
```

### [scanf 반환값 EOF vs 0]

- 첫 데이터를 읽기 전 실패하면 EOF
- 첫 데이터를 읽었는데, 서식 문자열과 맞지 않아 읽지 못했으면 0

```c++
#include <stdio.h>

int main(void)
{
    int a = 99;
    int b = 99;
    int num;

    num = scanf("%3d%d", &a, &b);
    printf("%d %d %d\n", a, b, num);

    return 0;
}
```

```text
EOF
>> a == 99, b == 99, num == EOF
```

```text
abc
>> a == 99, b == 99, num == 0
```

### [fflush(FILE* stream)]

- fwrite(const void* buffer, size_t size, size_t count, FILE* stream)
- 바로 써지지 않음, 버퍼링 때문
- 라인 버퍼링이라 '\n'으로 버퍼를 비우고 싶어도, void* buffer의 타입이 보이드 포인터라 개행문자를 구분할 수 없음
- fflush()만이 유일하게 버퍼를 비우는 법

### [fwrite의 반환값]

```c++
#include <stdio.h>
#include <string.h>

#define LENGTH (1024)

void append_file(const char* filename)
{
    FILE* stream;
    char data[LENGTH];

    stream = fopen(filename, "ab");

    if (fgets(data, LENGTH, stdin) != NULL) {
        fwrite(data, strlen(data), 1, stream);
    }
}

append_file("hello.txt");
```

- count 이하의 수
  - 이 코드에서는 1 or 0 뿐

### [fclose(FILE* strem) 반환값]

- 성공 시 0
- 실패 시 EOF

### [fseek(FILE* stream, long offset, int origin) 실패하는 경우]

```c++
result = fseek(stream, -1, SEEK_SET);   /* result != 0 */
result = fseek(stream, 1, SEEK_END);   /* result == 0 */
```

- 파일의 처음 위치(인덱스 0)에서 음수로 앞으로 이동하는 것은 실패함
  - fseek은 실패하면 0이 아닌 값 반환
- 파일의 마지막 위치(파일의 실제 데이터의 마지막 바이트 다음 위치)에서 양수로 뒤로 이동하는 것은 가능함, 하지만 이상한 곳을 접근

### [ftell(FILE* stream)]

- 텍스트모드로 파일을 열었을 때: fseek()의 offset에 사용할 때 유효한 값을 반환
- 바이너리모드로 파일을 열었을 때: SEEK_SET에서 몇 바이트 떨어져있는가?

## 코딩

### [문제1] a,b,c의 값을 구하라

```c++
#include <stdio.h>

int main(void)
{
    const char* input = "123abc 456";
    char buffer[256];
    int a;
    int b;
    int c;

    c = sscanf(input, "%d%s%d", &a, buffer, &b);

    return 0;
}
```

- a: 123
- b: 456
- c: 3
- scanf는 입력받은 문자열에서 공백을 만나면 읽기를 중지함
  - 123까지 읽고, a를 만나서 %d에 대한 입력 중지
  - abc까지 읽고, 공백을 만나서 %s에 대한 입력 중지
  - 456까지 읽고, 널문자를 만나서 %b에 대한 입력 중지

### [문제2] 출력값을 구하시오

- 입력은 아래와 같음

```text
123\n45\n
```

```c++
#include <stdio.h>

int main(void)
{
    char buffer[4];

    while (fgets(buffer, 4, stdin) != NULL) {
        puts(buffer);
    }

    return 0;
}
```

- 출력

```text
123


45


```

- fgets의 입력 종료 조건
  - count - 1 만큼 읽기
  - \n 만나기
  - EOF 만나기
- 123까지 읽고 buffer에 저장
  - buffer == {'1', '2', '3', '\0'}
  - puts로 123\n 출력
- \n읽고 buffer에 저장
  - buffer == {'\n'}
  - puts로 \n\n출력
- 45\n까지 읽고 buffer에 저장
  - buffer == {'4', '5', '\n'}
  - puts로 45\n\n출력

### [문제3] 출력값을 구하시오

```c++
#include <stdio.h>

int main(void)
{
    int a = 99;
    float b = 99.0;
    int num;

    num = scanf("%3d%f", &a, &b);
    printf("%d %f %d\n", a, b, num);

    return 0;
}
```

- 입력

```text
123.456
```

- 출력

```text
123 0.456000 2
```