# Lab11

## 스트림이 비어있는지 확인하는 방법

Length 프로퍼티의 값이 0인지 확인하면 됨

## 문자 데이터를 스트림으로 읽기

StreamReader 활용

## 문자 데이터를 한 글자씩 읽기

StreamReader의 Read() 활용

https://learn.microsoft.com/en-us/dotnet/api/system.io.streamreader.read?view=net-8.0

### StreamReader의 끝을 확인하는 방법

return값이 -1인지 확인하면 됨

## Setup 함수

- 출력 디렉토리 유무를 확인하고 없다면 디렉토리 만들기
- 출력 디렉토리에 파일 유무를 확인하고 있다면 파일 지우기

## BianryReader에서 Byte(Hex format)단위로 읽기

16진수 숫자 2자리로 나타내는 비트 패턴은 16 * 16 하면 2^8이다. 즉 Byte(8bit) 단위임

// data1.encoded.bin의 이진 데이터
/*
08 41 06 46 07 44 07 53 06 20 06 73
*/

이진 데이터를 해석하는 방법 16진수 08을 10진수로 바꿔보자. 8 따라서 byte 8을 저장하면 저렇게 저장된다.

16진수 44의 경우 68이다. 즉 byte 68을 저장하면 저렇게 저장된다.

### BinaryReader에서 끝인지 확인하는 방법

https://learn.microsoft.com/ko-kr/dotnet/api/system.io.binaryreader.readbyte?view=net-8.0

EndOfStreamException 예외를 통해서 확인한다.

```c#
using System;
using System.IO;

class Program
{
    static void Main()
    {
        // 파일 경로 지정
        string filePath = "example.bin";

        // 파일이 존재하는지 확인
        if (File.Exists(filePath))
        {
            using (FileStream stream = new FileStream(filePath, FileMode.Open))
            using (BinaryReader reader = new BinaryReader(stream))
            {
                try
                {
                    while (true) // 무한 루프
                    {
                        byte value = reader.ReadByte(); // 1바이트 읽기
                        Console.WriteLine(value); // 읽은 바이트 출력
                    }
                }
                catch (EndOfStreamException)
                {
                    Console.WriteLine("End of file reached.");
                }
            }
        }
        else
        {
            Console.WriteLine($"File not found: {filePath}");
        }
    }
}
```

BinaryReader에서는 EndOfStream 프로퍼티를 지원하지 않음. 수업에서 StreamReader의 경우 EndOfStream 프로퍼티를 사용했음

```C#

```

## C# character 반복하는 String 만들기

### C# 11이상

```C#
char character = 'a';
int characterCount = 3;

string result = new string(character, characterCount);
Console.WriteLine(result); // "aaa"
```

### C# 11미만

```C#
char character = 'a';
int characterCount = 3;

StringBuilder sb = new StringBuilder(characterCount);
for (int i = 0; i < characterCount; ++i)
{
	sb.Append(character);
}
string result = sb.ToString();
```

## StreamWriter Flush()

내부적으로 버퍼를 사용하기 때문에 다 쓰고 Flush() 호출해줘야 텍스트 파일에 쓰게 됨.

https://learn.microsoft.com/ko-kr/dotnet/api/system.io.streamwriter.flush?view=net-8.0