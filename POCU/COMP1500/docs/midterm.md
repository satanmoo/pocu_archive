# MidTERM

## 단답형

### 컴파일 언어와 JIT 컴파일 언어의 비교

- 컴파일 결과물
컴파일 언어의 컴파일 결과 : 네이티브 코드(기계어)
JIT 컴파일 언어의 컴파일 결과 : 중간언어

- 종속성
컴파일 언어는 플랫폼에 종속적이다.
JIT 컴파일 언어는 가상 머신에 종속적이다.

### JIT 컴파일 언어에서 가상머신의 기능

중간언어를 실행 중(just in time)에 네이티브 코드로 변환

### 컴파일 언어와 인터프리터 언어 비교

컴파일 언어는 실행 전 컴파일을 통해서 코드의 문제(실수)를 찾아낼 수 있다.
인터프리터 언어는 실행 후 코드의 문제(실수)를 찾을 수 있다.

### 인터프리터 언어에서 인터프리터의 역할

실행 중 소스코드를 기계어의 집합으로 변환한다.

### 메모리의 life cycle을 관리하는 것의 필요성

컴퓨터의 구조상 프로그램이 실행되려면 운영체제로 부터 독립적인 메모리를 확보해야한다. 프로그램이 종료되면 메모리를 운영체제에 반납해야 한다.

### 메인 함수가 실행되는 시점

프로그램을 실행할 때 가장 먼저 실행되는 것이 메인 함수다. 따라서 exe파일을 실행하면 자동으로 메인 함수가 실행된다.

### static 키워드의 효과

- 함수에 static 키워드를 붙이면 붙은 함수는 전역 함수가 된다.
  - 전역 함수는 프로그램에서 오직 하나만 실행된다.
  - 프로그램 어디서든 접근할 수 있다.

### 메인 함수의 인자

- 자료형
문자열의 배열

- 입력받는 방법
커맨드 라인에서 입력하면 된다. 이 때 띄어쓰기로 문자열을 구분해서 배열의 원소로 입력받는다.

```shell
<filename>.exe Hi C# is fun
```

Hi, C#, is fun 총 4개가 인자로 들어간다.

### 메인함수의 반환형

- void
모든 함수(메인 함수를 포함)는 반환형이 존재한다. void는 반환값이 존재하지 않는다.

- int
반환값은 exe 프로그램이 올바르게 실행됬는지 여부를 알려주는 역할을 한다.
대부분 언어에서 0은 프로그램이 정상 실행된 것을 의미하고, 0이 아닌 값은 프로그램의 비정상적 오류를 의미한다.

### Using 키워드의 역할

Using은 지시어다. 이 때 지시하는 대상은 라이브러리다. 어떤 라이브러리를 사용하겠다고 컴파일러에게 알려준다.

### 라이브러리

함수의 집합

### 컴파일 오류와 버그

- 컴파일 오류
컴파일 오류가 발생하면 실행 파일이 생성되지 않는다.

- 버그
실행 중 문제가 생기면 버그라고 한다.

### 변수의 선언

컴파일러에게 변수의 이름과 자료형을 알려주는 것

### 변수의 대입

컴파일러에게 변수의 값을 알려주는 것

### 상수의 선언과 대입

- 상수는 반드시 선언과 동시에 대입해야 한다. 그 이후 값이 바뀌지 않는다.

- const 키워드를 사용한다.

### 기본 정수 자료형 비트 수

byte : 8

short : 16

int : 32

long : 64

### byte형의 부호

byte 형은 기본적으로 부호가 없다.

### Literal(매직 넘버)의 접두사와 접미사

- 접두사
0b10, 0x1a과 같이 진법을 나타낸다.

- 접미사
매직넘버의 자료형을 나타낸다.

✅ 실수형의 리터럴은 접미사를 생략하면 double, 접미사 f를 붙이면 float이다.

```C#
float num = 10.0; // 컴파일 오류(double을 float에 구겨넣으려 했기 때문이다.)
```

### Convert.ToString()

https://learn.microsoft.com/ko-kr/dotnet/api/system.convert.tostring?view=net-8.0#system-convert-tostring(system-int16-system-int32)

16비트 부호 있는 정수 값을 지정된 기수의 해당하는 문자열 표현으로 변환합니다.

### 묵시적 형 변환

기본 자료형간의 변환을 컴파일러가 알아서 해준다.

### 문자형 -> 정수형 묵시적 형변환

허용

### 자료의 정밀도 손실

정수형에서 부동소수점형으로 변환하면 자료의 정밀도 손실이 생긴다.

### 정수형 + 실수형 연산에서 승격의 결과

정수형이 실수형으로 묵시적 변환된다.

### 묵시적 형변환에서 큰 형 -> 작은 형 변환 : 컴파일 오류

- C# 에서는 컴파일 오류
- 정보의 손실이 발생할 수 있다. 따라서 실수를 막기 위해 컴파일러에서 막아준다.
- 예시
    - long -> char 컴파일 오류
    - 실수형 -> 정수형 컴파일 오류
    - 실수형 -> 문자형 컴파일 오류

### 명시적 형변환을 하는 이유

- 실수 안 한다는 보장이 있을 때 큰 형 -> 작은 형 변환도 가능하다.
- 작은 형 -> 큰 형 변환도 당연히 가능

### 정수형 큰 형 -> 작은 형 명시적 형변환 시 일어날 수 있는 문제

```C#
long num1 = 9223372036854775807;
int num2 = (int) num1; // -1
```

비트패턴에서 잘려서 32번째 비트가 1이라서 음수가 나와버린다.

### 실수형 -> 정수형 명시적 변환

- 컴파일러에서 값을 계산해서 버림한다.
- 52.16 -> 52
- 문자형 -> 실수형 명시적 형변환 가능 (문자형도 내부적으로 정수형과 동일하니까)

### 명시적 형변환이 불가능한 경우

정수형, 실수형, 문자형(char) -> 불린형 명시적 변환 불가능(컴파일 에러)

불린형 -> 정수형, 실수형, 문자형 명시적 변환 불가능(컴파일 에러)

### 부호없는 정수형 언더플로우

```C#
int numb1 = 10;
int numb2 = 30;

int result1 = numb1 - numb2;
uint result2 = (uint) result1; // 언더플로우 발생(컴파일 경고)
uint resul3 = (uint)(-20);  // 컴파일 에러 : 
```

음수 값을 부호없는 정수형에 대입한다? 주의하자.

### 연산과 대입에서 순서

- 오른쪽 항의 연산이 끝나고 대입하다고 생각하자.

```C#
int number1 = 10;

int number2 = 30;

double result2 = number1 / number2; // 0.0
```

### 명시적 형변환과 승격의 응용

```C#
int number1 = 10;

int number2 = 30;

double result2 = number1 / (double)number2; // 0.3333333
```

number1은 double로 승격된다.

### 증감 연산자는 정수형에만 쓰자

```C#
num1 = 0;
num2 = 10;

int result3 = num1-- + num1-- - --num2;

Console.WriteLine("num1: " + num1); // -2
Console.WriteLine("num2: " + num2); // 9
Console.WriteLine("result3: " + result3); // -10
```

```C#
num1 = 0;
num2 = 10;

int result4 = num2++ * num1++ - --num2 + num1;

Console.WriteLine("num1: " + num1); // 1
Console.WriteLine("num2: " + num2); // 10
Console.WriteLine("result4: " + result4); // -9
```

### 부동 소수점형에는 비트 쉬프트할 수 없음

- 비트가 가장 작은 단위인데 비트를 소수점처럼 쪼개는 것은 비트가 가장 작은 단위라는 개념에 위배된다.
- 비트 쉬프트 우항에 부동 소수점 형 불가능(컴파일 에러)
- 비트 쉬프트 좌항에도 부동 소수점 불가능(컴파일 에러)

### 대문자를 소문자로 바꾸는 법

- ' '와 bitwise or 연산
- space의 아스키 코드 값은 32

```C#
char a = 'A';
char space = ' ';
long b = a | space;
Console.WriteLine((char)b);
```

- 비트와이즈 연산은 int 형을 리턴한다.
- char 형에 바로 대입할 수 없다. 작은 형에서 큰 형으로 묵시적 변환이 되지 않고 컴파일 에러. char은 16비트로 32비트의 int보다 작다.

### 소문자를 대문자로 바꾸는 법

```C#
char a = 'a';
char underBar = '_';
long b = a & underBar;
Console.WriteLine((char)b);
```

- underBar의 비트패턴은 "1011111" 즉 아스키 코드 값은 95다.
- bit wise and 연산을 하면 32를 나타내는 비트가 꺼진다. 32를 빼는 것과 결과가 같음

### C#에서 문자열 비교 연산할 때 값으로 비교함

### \x 이스케이프

```<C#>
Console.WriteLine("\x48\x65\x6C\x6C\x6F\x20\x57\x6F\x72\x6C\x64\x21");

>> Hello World!
```

- x는 소문자다.
- X 대문자 아님
- 다음 단계를 따른다.
  1. 대문자인지 소문자인지 x 확인
  2. 숫자를 16진수에서 10진수로 바꾼다.
  3. 아스키코드 값으로 변환해서 출력할 때 문자열로 출력한다.

### @: 이스케이프 방지

```C#
Console.WriteLine(@"\\\\\x61\x62");

>> \\\\\x61\x62
```

### Console.ReadLine의 리턴값

- string

### 문자열로된 숫자를 묵시적 변환할 수 있을까?

- 묵시적 변환은 기본 자료형끼리만 가능함
- 문자열은 기본 자료형이 아님

### parsing 예외

```<C#>
int result = int.Parse("parsing");
```

- 예외가 발생
- 예외는 실행 중(런타임) 발생

### 표현식과 구문의 관계

- 표현식은 평가의 대상이며 값을 반환한다.
- 표현식만 단독으로 사용할 수 없다. 단독으로 사용하면 컴파일 에러
- 구문에 표현식이 포함된다.

```C#
double area = 3.14 * 2;
```

### short cicruit evaluation

```<C#>
int num1 = 10;
int num2 = 20;
if (num1 == 10 || ++num2 == 20)
{
    num1++;
}

Console.WriteLine($"{num1}, {num2}");
```

정답은 "11, 20"이다. 두번째 표현식은 실행되지 않는다.

```<C#>
using System;

public class Program
{
    public static void Main()
    {
        int num = 10;

	if (3 * 6 != 18 || ++num == 11)
	{
	    num++;
	}

	if (12 + 2 == 14 && num++ == 11)
	{
    	    num++;
	}

	if (1 * 4 == 4 || num++ > 0)
	{
	    num++;
	}

	Console.WriteLine(num);
    }
}
```

정답은 14다.

```<C#>
using System;

namespace LogicalExpressions
{
    class Program
    {
        static void Main(string[] args)
        {
            int num1 = 1;
            int num2 = 1;
            int num3 = 4;

            bool bExpression1 = !(num1 == num2 && num1 != num3);
            bool bExpression2 = num1 != num2 || num1 == num3;

            Console.WriteLine($"expression1: {bExpression1}");
            Console.WriteLine($"expression2: {bExpression2}");

            bool bExpression3 = num1 > num2 || num1 == num3 || ++num1 > num2;
            Console.WriteLine($"expression3: {bExpression3}");
            // num1 = 2, num2 = 1, num3 = 4
            // 따라서 true 출력

            bool bExpression4 = num3 >= num2 || num1-- == num2;
            Console.WriteLine($"expression4: {bExpression4}");
            // num3 >= num2 는 참이라서 두번째 표현식은 실해되지 않는다.
            // 따라서 true 출력

            bool bExpression5 = num3 == num1 && --num2 > num1;
            Console.WriteLine($"expression5: {bExpression5}");
            Console.WriteLine($"num1: {num1}, num2: {num2}, num3: {num3}");
            // num3 == num1 은 거짓이라서 두번째 표현식은 실행되지 않는다.
            // 따라서 false 출력
            // 최종적으로 num1 = 2, num2 = 1, num3 = 4
        }
    }
}
```

### 연산자 우선순위 && 가 || 보다 우선한다.

### 연산자 우선순위와 결합법칙

```<C#>
int num1 = 5;
int num2 = 15;
int num3 = 2;

int result3 = num1 / (num2 + num1) + num1++;
Console.WriteLine($"result3: {result3}");
Console.WriteLine($"num1: {num1}, num2: {num2}, num3: {num3}");
```

1. num1 / (num2 + num1) + num1++
2. 5 / (15 + 5) + 5++ (num1의 현재 값은 5이므로)
3. 5 / 20 + 5++
4. 0 + 5++ (5 / 20은 정수 나눗셈이므로 0)
5. 5 (num1++은 현재 값을 반환하고 나서 1 증가)

`C#에서 후위 증감연산자는 해당 구문(statement)의 연산이 모두 끝나고 적용된다.`

```<C#>
int num1 = 5;
int num2 = 15;
int num3 = 2;

int result3 = num1 / (num2 + num1) + ++num1;
Console.WriteLine($"result3: {result3}");
Console.WriteLine($"num1: {num1}, num2: {num2}, num3: {num3}");
```

1. num1 / (num2 + num1) + ++num1
2. 6 / (15 + 6) + ++num1 (num1의 현재 값은 6이므로)
3. 6 / 21 + ++num1
4. 0 + ++num1 (6 / 21은 정수 나눗셈이므로 0)
5. 6 (++num1은 현재 값에 1을 더한 후 반환)

`전위 증감연산자가 가장 우선순위가 높아서 먼저 실행되어 num1이 증가한 상태로 다른 표현식들이 평가되었다.`

### 모든 case 구문에 break를 넣자

### case에서 사용할 수 있는 상수형

- int
- long
- char
- bool
- `string(C# 전용)`

- 정수형 + 문자열
- 부동소수점은 안 된다.

### do-while

```C#
using System;
					
public class Program
{
    public static void Main()
    {
	int result = 2;
	int count = 0;

	do
	{
	    result += 4;
	} while (++count < 4);

	Console.WriteLine(result);
    }
}
```


### do-while을 while로

```<C#>

while (true)
{
	if (조건식)
	{
		break
	}
}

```

## Lab2

### 1. 아래 코드가 컴파일 에러가 나는 이유

```C#
public static int GetSum(int num1, int num2, int num3, int num4)
        {
            int sum;
            num1 + num2 + num3 + num4 = sum;
            return sum;
        }
```

대입 연산자의 왼쪽 피연산자에만 대입할 수 있다.

### 2. 아래 코드가 컴파일 에러가 나는 이유

```C#
public static double GetAverage(int num1, int num2, int num3, int num4)
        {
            GetSum(num1, num2, num3) = int sum;
            return sum / 4;
        }
```

- GetSum은 4개의 매개변수를 가지고 있는데 매개변수의 수가 부족하다. 즉 함수 시그니처에 맞지 않음
- 대입 연산자의 왼쪽 피연산자에만 대입할 수 있다.
- GetAverage의 반환형은 double인데 함수의 바디에서는 int형 자료를 반환했다.

### 3. 아래 코드가 컴파일 에러가 나는 이유

```C#
public static int Multiply(int num1, int num2)
        {
            const int product = num1 * num2;
            return product;
        }
```

num1 * num2 식은 실행 중 계산(평가)된다. const 키워드가 붙은 상수는 컴파일 전 대입과 할당을 동시에 해야한다. num1 * num2는 실행되어야만 값을 알수 있고, 그에 따라 대입할 수 있기 때문에 컴파일 오류가 발생한다.

### 4. 아래 코드가 컴파일 에러가 나는 이유

```C#
public static uint Subtract(int num1, int num2)
        {
            return num1 - num2;
        }

```

함수의 반환형은 uint인데 int 타입의 값을 반환한다.

### 5. 아래 코드가 컴파일 에러가 나는 이유

```C#
public static int CombineStrings(string s1, string s2)
        {
            string result = s1 + s2;
            return result;
        }
```

함수의 반환형은 int인데 문자열 string을 반환한다.

## Assingment3

### StreamReader input, StreamReader output 사용법

```C#
string read = input.ReadLine();
output.Write("Write with no new line character");
output.WriteLine("Write with new line character");
output.WriteLine("{0,-5} {1:x}", someVar1, someInt);
```

Console.*랑 동일하게 작동함.

### 유연하게 변수로 string.Format 정렬 길이 설정하기

```C#
string result = string.Format("{0," + width + "} " +
                                      "{1," + width + "} " +
                                      "{2," + width + "}\n", "oct", "dec", "hex");
```

"{<인덱스>,<정렬길이>}"에서 정렬 길이를 변수로!

### 소수점 3자리까지 문자열 포메팅

```C#
result += $"{num5,25:f3}\n";
```

