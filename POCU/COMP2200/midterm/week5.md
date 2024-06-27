# Week5

## 암기

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