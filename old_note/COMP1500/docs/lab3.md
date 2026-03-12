# lab 3

## 정수 0, 부동소수점형 0.0을 0이 아닌 수로 나눌 수 있음

```<C#>
double tip = (costSum + tax) * tipPercent / 100;
```

costSum : Double
tax : Double
tipPercent : int

이런 경우 tipPercent가 0이라고 해도 전혀 문제가 되지 않는다. int 자료형의 0이 입력되면 승격하게 되어 double로 계산되고 연산자 우선 순위에 따라서 costSum + tax의 값에 0.0이 곱해져 결국 0.0에 100을 나누게 된다.

## Ceiling(올림) 수동 구현

- 소수점 첫째 자리에서 올림

```<C#>
double origin = 123.45; // input
int truncatedValue = (int)origin; // 123
int res = truncatedValue;
if (truncatedValue < origin) // truncatedValue 승격
{
	res++;
}
return res;
```

## Flooring(버림) 수동 구현

- 소수점 첫째 자리에서 올림

```<C#>
double origin = 123.45; // input
int truncatedValue = (int)origin; // 123
int res = truncatedValue;
return res;
```

## 반올림(round) 수동 구현

- 소수점 첫째 자리에서 반올림

```<C#>
double origin = 123.65; // input
int truncatedValue = (int)(origin + 0.5); // 124
int res = truncatedValue;
return res;
```

- 소수점 셋째 자리에서 반올림

```<C#>
double origin = 123.654; // input
double scaledOrigin = origin * 100; // 12365.4
int truncatedValue = (int)(origin + 0.5); // 12365
int res = truncatedValue / 100.0; // 123.65, 승격 발생
return res;
```
