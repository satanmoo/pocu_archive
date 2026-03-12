# Assignment4

## Enum 개체 비교 방법

https://learn.microsoft.com/ko-kr/dotnet/api/system.enum.equals?view=net-8.0

## String -> Enum 변환

```C#
public enum Color
{
    Red,
    Green,
    Blue
}

string colorName = "Green";
Color color = (Color)Enum.Parse(typeof(Color), colorName);

```

```C
string colorName = "Green";
bool success = Enum.TryParse(colorName, out Color color);
if (success)
{
    // Use the color variable, conversion successful
}
else
{
    // Handle the case where the conversion failed
}
```

\* 여기서 잠깐 typeof 에 대해서 알아보자

typeof는 연산자다. 이는 실행 중(runtime)에 타입에 대한 메타데이터를 제공한다.

Enum.Parse 메소드가 typeof 연산자를 요구하는 이유는 무엇일까?

이 메소드는 실행 중 특정 enum타입에 대한 정보가 필요하다. 이 정보를 통해 enum중 어떤 값으로 string을 변환할 지 결정한다. C#이 강한 타입 언어라는 것을 여기서 알 수 있다.