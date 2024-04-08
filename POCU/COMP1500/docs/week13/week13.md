# Week13

## 파일이 필요한 이유

![alt text](image.png)

누군가 파일을 지우거나 변경하기 전 까지는 내용이 유지됨

## 텍스트 파일 저장하기

![alt text](image-1.png)

\ 가 이스케이프 문자기 때문에 @를 사용해서 \ 를 이스케이프 하지 않게 만듬

\* 파일 경로를 입력할 때 @로 이스케이프를 하지 않게 만들 수 있음

## File.WriteAllLines

![alt text](image-2.png)

string 배열의 원소를 한줄씩 저장함

## File.WriteAllText

![alt text](image-3.png)

string을 파일에 저장함

줄바꿈 하고 싶으면 string에 줄바꿈 문자 포함하면 됨

![alt text](image-4.png)

문자열을 쓰고 파일을 닫음

파일이 저장됨

이미 파일이 존재한다면 덮어씀(WRITE)

## 파일에 덧 붙이기

APPEND

![alt text](image-5.png)

## 텍스트 파일 읽어오기

![alt text](image-6.png)

### ReadAllLines

![alt text](image-7.png)

### ReadAllText

![alt text](image-8.png)

![alt text](image-9.png)

파일을 닫아야지 다른 누군가가 읽고 쓸 수 있음

## 이진 파일 저장 및 읽기

![alt text](image-10.png)

`가장 작은 자료형` byte를 이진 파일에 저장할 때 사용한다는 것을 주목하자

메모장에서 열어보면 이상한 글자가 나온다. 그렇다면 hex editor로 열어서 확인하면 됨

대문자 A의 아스키코드 65

파일에 저장될 때는 binary값으로 저장됨

텍스트 파일을 화면에 출력할 때는 binary값이 아스키 코드로 인코딩 됨 따라서 65는 A로 보여짐

12, 3은 다른 특수문자로 보여짐

\* 의문점 : 텍스트 에디터는 어떻게 왜 8비트(바이트) 단위로 바이너리값을 읽어서 보여주는가? 컴퓨터의 바이너리 데이터를 텍스트 보여주기 위해 디코딩할 때 규약이 있음 그 약속이 UTF-8임! UTF-8 인코딩 방식은 ASCII를 모두 표현할 수 있다. ASCII는 0~127까지 표현한다. 즉 비트로 7비트면 충분하다. 따라서 8비트인 바이트를 단위로 사용한다.

파일에서 다시 읽어와서 byte배열을 출력하면 binary값인 12, 65, 3을 그대로 출력함

바이너리로 저장해도 아스키코드로 저장되어 있다면 텍스트로 보이죠?

\* 내부적으로 컴퓨터는 이진법을 기반으로 작동하기에 byte형으로 저장하면 이진수로 저장됨

![alt text](image-13.png)

byte[]를 한 번에 저장 및 읽는 경우의 예를 생각해보자

ZIP file의 모든 byte를 읽고 다른 곳에 저장할 일이 많을까? 아니다. ZIP에서 파일 하나만 골라서 읽고 저장하는 경우가 많다.

## 이진 파일 vs 텍스트 파일

![alt text](image-11.png)

텍스트 파일은 텍스트를 저장함. 텍스트 3을 저장하면 아스키 51번 '3'이 저장됨

이진 파일은 ASCII코드를 저장. byte 타입 3을 저장하면 아스키 3번이 저장됨

## 16진수 편집기

![alt text](image-12.png)

## 문자열이 아닌 값들을 이진 파일에 저장

![alt text](image-14.png)

문자열을 바이너리로 바이너리 파일에 저장하는 것 뿐만 아니라 다른 자료형을 바이너리 파일에 저장하고 싶을 때 필요함

## 존재하지 않는 파일 읽기

![alt text](image-15.png)

DIRECTORY NOT FOUND, FILE NOT FOUND

그래서 예외가 날 수 있는 상황을 미리 코드로 방지하기

## 파일 존재 여부를 미리 판단하기

![alt text](image-16.png)

이 방법도 충분하지 않음

## File.Exists()가 충분하지 않은 경우

![alt text](image-17.png)

멀티 스레딩 개념이 들어감

File.Exists의 결과 true가 반환되서 코드 block에 들어오는 사이에 다른 프로그램 또는 다른 누군가가 파일을 지웠음

File.ReadAllLines를 실행할 때 파일을 찾을 수 없어서 프로그램이 CRASH

## 프로그래머가 완벽히 통제할 수 없는 예외

![alt text](image-18.png)

예측 가능하지만 방지할 수 없음

따라서 예외를 처리해야함!

## 코드보기: 파일 읽기 및 쓰기

### readonly 키워드

변수에 한 번 값을 대입하면 읽기 전용이됨. 따라서 그 뒤로는 다른 값을 대입하지 못함

![alt text](image-19.png)

readonly는 runtime 상수라 생각하면 편함

### GetCurrentDirrectory()

현재 실행 컨텍스트의 경로(WORKING DIRECTORY)를 가져옴

`bin/Debug/net7.0`

### Path.Combine

하위 디렉토리 만들 때 쓰면 좋음

### 확장자?

확장자는 컴퓨터에게 의미가 없음. 모든 파일은 0과 1로 저장되기 때문

```C#
private static readonly string OUTPUT_FILE2_FULL_PATH = Path.Combine(CURRENT_DIRECTORY, "output", "outputtext2.txt");

byte[] bytes = new byte[12] { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33 };

File.WriteAllBytes(OUTPUT_FILE2_FULL_PATH, bytes);
```

바이트를 텍스트 파일(txt 확장자)에 저장했음.

사실 이 파일은 바이너리 파일임(그래서 원래는 .bin)

지금은 바이너리 값이 아스키 코드라 txt파일을 열어서 확인하면 텍스트로 잘 보임

## 예외

![alt text](image-20.png)

원래 코드는 위에서 아래로 선형적으로 읽는 것이 일반적임 예외가 복잡한 이유는 선형적이지 않기 때문임

### 예외적인 조건

![alt text](image-21.png)

예외적인 조건을 방지할 수 없음 하지만 예측이 가능해서 대응 할 수 있음

프로그램을 짜고 가장 중요한 일이 프로그램을 망가뜨려보는 것

이것을 잘 할수록 버그를 줄일 수 있음. 버그가 발생하더라도 버그의 원인을 찾아 고칠 수 있음

결론은 예외적인 조건을 제대로 처리하자!

![alt text](image-22.png)

### 사용 예 : FileNotFoundException

![alt text](image-23.png)

Exception을 throw하면 누군가 catch해야함

catch의 인자로 잡을 예외를 명시함

![alt text](image-24.png)

예외가 발생하면 아직 실행되지 않은 try 블록 안의 모든 코드를 건너뜀

\* 예외가 발생한 코드 위의 코드들은 실행됬으니까, 취소하는 처리가 필요할 수도

throw는 계속 위의 호출자로 던짐. 아무도 catch 안하면 main까지 올라오고 최종적으로 프로그램 디버거(ide에서 보여주는 창)에서 예외가 뜸

### try/catch 예외가 발생하지 않은 경우

![alt text](image-25.png)

### try/caych 파일이 없어지는 경우

![alt text](image-26.png)

### try/catch 다른 예외가 나는 경우

![alt text](image-27.png)

모든 예외를 잡을 수 있는 클래스가 Exception 클래스

## 여러 개의 catch 블록

![alt text](image-28.png)

## Exception 클래스

![alt text](image-29.png)

Exception에는 ArgumentNull, FileNotFound 모두 포함되서 catch 할 수 있음

![alt text](image-30.png)

## 발생한 예외의 내용을 알기

![alt text](image-31.png)

## 발생한 예외와 관련된 함수 호출의 정보

![alt text](image-32.png)

호출 스택은 현재 실행 중인 함수의 호출 정보를 기록한 `자료구조`임

```shell
at TryCatchFinally.Program.Main(String[] args) in /Users/gyo/studyroom/POCU_Archive/POCU/COMP1500/src/COMP1500CodeSamples/13/TryCatchFinally/Program.cs:line 21
```

\* Console.Error은 Error 스트림(stderr)을 사용함. 에러 메시지랑 제대로된 출력 메시지랑 구분하기 위해 분리되있음. 그래서 프로그램 시작할 때 에러 스트림만 따로 파일로 저장해서 에러 메시지 확인할 수 있음

### Message StackTrace 예

![alt text](image-33.png)

## 파일에 쓴다. 두번 씀

![alt text](image-34.png)

메인 함수 안 코드를 보면

path 변수는 파일 경로를 가짐

WirteByte를 2번 호출함

첫번째 호출은 잘 작동

두번빼 호출에서 IOException을 catch해서 에러 메시지가 출력됬음

에러 메시지 내용을 살펴보자

다른 프로세스가 사용 중이라 못씀?

## 예외가 발생한 이유

![alt text](image-35.png)

Open 함수의 성질을 확인하자

파일을 운영체제에 부탁해서 열었으면 다른 프로세스에서는 열려있는 파일을 사용할 수 없음

따라서 파일을 사용하고 닫아줘야 다른 프로세스가 이를 사용할 수 있음

## 파일 닫는 처리

![alt text](image-36.png)

fs.Close()를 try, catch 블록에 둘다 썼음.

모든 분기마다 어떤 처리를 하는 코딩인데 이게 좋지 않음

## catch 블록에 없는 다른 예외가 발생하면?

분기문에서 else 같은게 필요하죠?

![alt text](image-37.png)

다른 예외가 발생하면 try문의 Close, catch문의 Close 모두 실행 안 됨

결국 파일을 못 닫음

어떤 예외는 잡고, 어떤 예외는 안 잡으면 굉장히 헷갈립니다.

## finally 블록

![alt text](image-38.png)

## finally 반영해서 수정한 코드

![alt text](image-39.png)

finally 문에서 fs가 null인지 확인한 이유는 Open할 때 못 연 경우를 고려한 것

fs = File.Open(path, FileMode.Open);에서 파일을 못열면 fs는 null이 됨

애초에 파일을 못 열었으면 닫을 필요도 없죠

UnauthorizedAccessException은 파일을 연것은 성공했는데, write 권한이 없기 때문에 발생함

## 커스텀 예외

![alt text](image-40.png)

![alt text](image-41.png)

## 커스텀 예외 만들기

![alt text](image-42.png)

## 예외를 던지는 방법 : throw

![alt text](image-43.png)

## 커스텀 예외를 catch 블록에서 잡는 법

![alt text](image-44.png)

## 코드보기 : 예외

ArgumentNullException은 콘솔에서 읽어오는 경우 발생하지 않음 반드시 NotNull임

## catch 블록에서 받은 예외를 다시 던지기?

rethrow라고 부름

가능하기는 함

추천하지 않음

![alt text](image-45.png)

![alt text](image-46.png)

## throw e의 문제점

호출 스택을 초기화 해버림!

## 호출 스택의 차이를 봅시다

![alt text](image-47.png)

![alt text](image-48.png)

throw e하면 rethrow한 부분부터 호출 스택 초기화되서 보여짐

## 올바른 예외 처리 방법

### 예외처리와 선조건

![alt text](image-49.png)

Assert로 입력값을 검증할 수 있음

### 함수의 선조건에 의해 처리 안 되는 경우

![alt text](image-50.png)

![alt text](image-51.png)

경계 상황에서 입력값을 검증

![alt text](image-52.png)

경계 상황에서 입력값을 검증하기 어려운 경우 내부 함수를 호출해서 확인할 수 밖에 없다. 예를 들어 DB를 조회한다던가

## 예외 vs 오류코드

![alt text](image-53.png)

![alt text](image-54.png)

결론은 경계에서 바로 처리하자!

예시는 다음과 같음

![alt text](image-55.png)

경계에서 예외를 잡고 에러메시지를 출력하고, return false함 즉 에러 코드를 반환하는 것임

포프님 입장은 return false와 같이 에러 코드를 반환하라는 입장

## 파일 스트림

![alt text](image-56.png)

기존 파일 입출력 방법(ReadAllLines, ReadAllBytes)은 문제가 있음 사용하지도 않을 파일 내용을 전부 읽어오게 됨. 굳이 그럴 필요가 없다. 낭비임

파일 스트림을 이용하면 파일의 일부분만 선택해 읽을 수 있음

## 스트림이란?

![alt text](image-57.png)

줄줄이 들어옴

시냇가에 물이 흐르는 느낌

유튜브 비디오를 보면 네트워크 패킷이 천천히 들어옴. 한 번에 모든 영상이 들어오는 것이 아니라 줄줄이 들어오는 경험이 있죠?

![alt text](image-58.png)

줄줄이 들어올 때 특정 위치만 볼 수 있음

## 파일 스트림 읽기 예

![alt text](image-59.png)

일단 이 코드에는 예외 처리 없긴한데 원래 필요합니다

파일 스트림의 프로퍼티 Length도 바이트 단위임을 주목하자

## File.Open()

![alt text](image-60.png)

![alt text](image-61.png)

파일 모드에 따라 파일을 염(나혼자 사용용, 공유용, 쓰용용, 읽기용)

열면 파일 스트림을 반환함

## FileStream 프로퍼티

![alt text](image-62.png)

Seek은 탐색인데 파일 스트림에서 읽거나 쓰는 위치를 점프하는 기능이라고 보면 됨

- Seek이 불가능한 예시

네트워크 스트림에서 패킷이 들어오는 상황을 생각해보자. 50바이트 정도 읽었는데 보내는 사람이 처음부터 다시 보내지 않는 이상 처음으로 점프할 수 없음

## ReadByte() 함수

![alt text](image-63.png)

이 함수의 단점은 반복문으로 끝까지 돌려야한다는 점

## Read() 함수

![alt text](image-64.png)

count 수 만큼 필요한 부문만 끊어 읽기 위해 지원하는 함수

offset은 배열의 어디 위치부터 저장할 건지를 명시함

![alt text](image-65.png)

\* 함수 호출 결과 스트림 내의 위치가 변한다는 것을 기억하자!

리턴값은 읽어온 바이트 수다.

위의 예시에서는 n이 5가된다.

> Docs : 읽기 작업이 성공하면 스트림의 현재 위치는 읽은 바이트 수만큼 진행됩니다. 예외가 발생하면 스트림의 현재 위치는 변경되지 않습니다.
\* 5바이트 만큼 읽어서 32까지 읽고 현재 위치는 32가 된다. 다음에 Read를 호출하면 32 다음 위치인 75부터 읽게 된다.

https://learn.microsoft.com/ko-kr/dotnet/api/system.io.filestream.read?view=netcore-2.0

## ReadByte() , Read()의 문제점

![alt text](image-66.png)

student.txt라는 텍스트 파일도 내부적으로는 바이너리 형태임. 이 파일을 바이트 단위로 읽기 때문에 컴퓨터에서는 오른쪽과 같은 숫자로 바이트 자료형으로 저장된다.

텍스트파일, 이진 파일 모두 '바이트'로 보임

## WriteByte()

![alt text](image-92.png)

bytes 배열에 각각 byte를 아스키 코드 값으로 넣었음

![alt text](image-67.png)

스트림에 한 바이트 write함

## Write()

배열에서 offset부터 count개의 바이트를 파일 스트림에 write함

![alt text](image-68.png)

현재 스트림 내 위치에 주목하여 실행 결과를 살펴보자

파일 스트림을 열 때 OpenOrCreate 모드로 열었다. 따라서 파일을 생성하거나 열어서 덮어쓰게 된다. 만약 덮어쓰지 않고 이어서 쓰고 싶다면 Append 모드로 열어야한다.

![alt text](image-69.png)

![alt text](image-70.png)

write하면 현재 스트림 내 위치는 덮어쓴 내용의 가장 마지막 바이트의 위치다
(office hour 질문 필요)

## Seek()

![alt text](image-71.png)

위치를 바꾸기 위한 함수. 위치를 바꿔서 읽고, 쓰고 싶을 상황에서 사용한다.

offset 자료형이 long이고 음수가 될 수 있다.

offset의 부호는 방향을 의미한다. 양수 offset은 오른쪽 음수 offset은 왼쪽을 의미한다.

SeekOrigin이 End면 양수 offset은 유효하지 않다.

## Close()

![alt text](image-72.png)

파일 스트림을 열었으면 닫아줘야함

컴퓨팅 자원을 반납해야하죠

finally문에서 배웠음

## 코드보기 : FileStream

FileAcceess ENUM은 읽기 전용, 쓰기 전용, 읽고 쓰기 모두 가능한 권한을 나타냄

```C#
public enum FileAccess
    {
        // Specifies read access to the file. Data can be read from the file and
        // the file pointer can be moved. Combine with WRITE for read-write access.
        Read = 1,

        // Specifies write access to the file. Data can be written to the file and
        // the file pointer can be moved. Combine with READ for read-write access.
        Write = 2,

        // Specifies read and write access to the file. Data can be written to the
        // file and the file pointer can be moved. Data can also be read from the
        // file.
        ReadWrite = 3,
    }
```

enum 종류가 3가지이다. Read 모드면 Write는 안 된다. 따라서 CanWrite 프로퍼티 값이 false다. 마찬가지로 Write모드에서는 Read가 안 된다. ReadWrite일 때만 둘다 된다.

참고로 코드 보기 예시에는 CanSeek가 모두 true임

여기서 알 수 있는 것은 byte로 쓰더라도 이 바이트 각각이 ASCII 코드와 대응된다면 인코딩되서 텍스트로 잘 보여진다. 텍스트 에디터에서 알아서 인코딩이 된다는 것!

## using 문

![alt text](image-73.png)

using이라는 구문에 파일 스트림을 오픈하면 알아서 Close 함

Main함수에서 2번 Write했고 문제 없이 작동했다. 첫번째 Write에서 알아서 Close 했음을 알 수 있음

![alt text](image-74.png)

![alt text](image-75.png)

IDispose 인터페이스를 구현하면 Dispose()함수를 구현함

이 함수 내부에서 Close를 호출하기 때문에 시스템 자원을 반납하게 됨

## 문자열을 텍스트로 파일에 쓰기

`StreamWriter`

![alt text](image-76.png)

File.Open의 반환값 즉 FileStream 타입을 StreamWriter의 생성자의 매개변수로 넣게 됨

텍스트 파일에 씀

![alt text](image-77.png)

Console.Write와 유사함

이를 통해 결국 Console에 출력하나 텍스트 파일에 출력하나 똑같은 원리임을 유추할 수 있음

## StreamWriter

![alt text](image-78.png)

OOP 상속 개념이 들어감

텍스트를 쉽게 스트림에 저장할 수 있다 = byte로 저장하지 않고 문자열 단위로 저장한다

## StreamWriter 텍스트 쓰기

![alt text](image-79.png)

## 파일에서 텍스트 읽기

![alt text](image-80.png)

똑같음

파일 스트림을 상속받은 StreamReader을 만들고

이를 통해 텍스트 파일을 읽어올 수 있음

`ReadToEnd`는 한번에 모두 읽어오는 함수

## StreamReader

![alt text](image-81.png)

## StreamReader - 텍스트 읽기

![alt text](image-82.png)

`ReadLine`: 한줄 씩 읽어서 string 반환
`ReadToEnd()`: 모두 읽어서 string 반환

## 이진 파일 쓰기

![alt text](image-83.png)

(short) 30을 16진수로 바꾸면 1E에 2바이트라서 "1E 00"

1u는 32비트(unsigned int)

3.14는 64비트 (부동소수점 비트 포멧)

Hi에서 H의 아스키코드는 72임. 이를 16진수로 바꾸면 48 i도 마찬가지로 아스키코드를 16진수로 바꾸면 69 다만 앞의 02가 수상하다. BinaryWriter에서 자동으로 넣어준 값인데 문자열의 길이를 의미함

이게 자료형 마다 각각 `비트 패턴`으로 `인코딩` 하는 방법이 다르다는 것을 의미한다.

![alt text](image-84.png)

## BinaryWriter

![alt text](image-85.png)

## 이진 데이터 쓰기

![alt text](image-86.png)

결국 파일 스트림에 써지는 결과는 0과 1밖에 없다.

인자의 데이터 타입이 달라지는 것을 확인할 수 있는데, 함수 오버로딩 개념이 들어감

## 이진 파일 읽기

![alt text](image-87.png)

이진 데이터를 읽을 때 각 자료형에 따라 읽는다.

ReadInt16(), ReadUInt32() 등 함수의 이름이 달라져야하는 이유는 무엇일까?

함수의 오버로딩 조건을 만족하기 위해서 함수 이름은 동일하지만 매개 변수 목록은 달라져야한다. 하지만 위의 함수들은 매개변수가 없기에 오버로딩을 할 수 없었다.  (Read()라는 함수 시그니처를 상상하면 됨)

\* 이진 파일을 읽을 때는 바이너리를 읽어 디코딩해 각 자료형으로 변환하고, 이진 파일로 쓸 때는 각 자료형을 인코딩해 바이너리로 쓴다. 결국 자료형도 이진 패턴을 목적에 따라 분류한 것임을 알 수 있다.

## BinaryReader

![alt text](image-88.png)

## BinaryReader - 이진 데이터 읽기

![alt text](image-89.png)

## 보너스: 파일 대신 메모리에 저장하고 싶다면?

현재 프로그램이 실행되는 동안 임시로 저장하고 싶은 경우

파일 스트림 대신 메모리 스트림을 사용하면 됨

![alt text](image-90.png)

## MemoryStream의 예

using을 중첩해서 사용할 수 있음에 주목. 3개 using문 모두 같은 block을 가짐

MemoryStream, BinaryWriter, BinaryReader 모두 사용하고 닫아줌

![alt text](image-91.png)

## 코드보기 : using문

\* point1 : FileStream에서 Length를 사용해서 파일 끝까지 읽을 수 있었으며 StreamReader에서는 EndOfStream을 이용해 파일 끝가지 읽을 수 있다. 왜 기능을 구분했을까?

FilseStream의 Length 프로퍼티는 파일의 용량을 바이트 단위로 나타낸 것임 반면 EndOfStream은 내부적으로 읽고 있는 위치(Reader의 경우)가 파일의 끝에 도달했는지 여부를 나타냄. 이를 통해 파일의 끝을 알기 위해 파일의 용량을 반드시 알 필요 없다는 것을 알 수 있음.

따라서 EndOfStream은 네트워크 스트림에서 유용함. 네트워크 스트림의 용량은 알 수 없기 때문임

코드보기 예시에서는 파일 스트림으로 코드를 작성했기에 FileStream의 Length를 사용하던 StreamReader의 EndOfStream을 사용하던 상관없음

\* point 2

```C#
using (StreamWriter writer = new StreamWriter(File.Open(OUTPUT_FILE_FULL_PATH, FileMode.OpenOrCreate, FileAccess.Write)))
            {
                writer.Write(allText);

                foreach (string line in allLines)
                {
                    writer.WriteLine(line);
                }

                writer.BaseStream.Seek(0, SeekOrigin.Begin);
                writer.Write("Overwritten text");
            }
```

wirter의 BaseStream 프로퍼티는 writer가 읽는 스트림이다. BaseStream에 접근해서 Seek를 호출해서 Stream내의 위치를 가장 처음으로 초기화했다. 어떤 일이 발생할까?

writer은 아무런 영향도 받지 않는다. 왜냐하면 아직 BaseStream에는 아무것도 써지지 않았기 때문에 파일 스트림의 위치(쓰기 포인터)는 여전히 SeekOrigin.Begin 즉 처음 위치다.

writer.Write("Overwritten text");의 결과 지금까지 write한 텍스트 뒤 즉 버퍼의 마지막에 Overwritten text가 append된다.

using문 block이 끝나면 close 되면서 buffer가 flush 되는데 이 때 buffer의 내용이 모두 파일 스트림에 써지고, 파일 스트림의 위치는 쓴 내용의 마지막으로 이동하게 된다.

그렇다면 만약에 overwrite 즉 덮어쓰고 싶으면 어떻게 해야할까?

1) 다시 writer을 생성하면 된다.

```C#
using (StreamWriter writer = new StreamWriter(File.Open(OUTPUT_FILE_FULL_PATH, FileMode.OpenOrCreate, FileAccess.Write)))
            {
                writer.Write(allText);

                foreach (string line in allLines)
                {
                    writer.WriteLine(line);
                }

                writer.BaseStream.Seek(0, SeekOrigin.Begin);    // 이 코드가 실행되도 아무런 영향이 없음. 이 코드가 실행전 BaseStream의 마지막 위치는 SeekOrigin.Begin
                writer.Write("Overwritten text");   // overwrite 되지 않고 append 됨
            }

            using (StreamWriter writer = new StreamWriter(File.Open(OUTPUT_FILE_FULL_PATH, FileMode.OpenOrCreate, FileAccess.Write)))
            {
                writer.WriteLine("이건 overwrite됩니다. StreamWriter을 초기화 했기 때문에 버퍼도 초기화 됨");
            }
```

2) Writer가 사용하는 버퍼를 비우는 flush를 해준 뒤 Seek으로 파일 스트림의 처음으로 돌아가 쓰면 된다.

버퍼를 flush 하면 파일 스트림에 써지고, 스트림의 마지막 위치는 쓴 내용만큼 이동한다. 

```C#
writer.Flush(); // BaseStream의 마지막 위치 이동됨
writer.BaseStream.Seek(0, SeekOrigin.Begin);    // BaseStream의 마지막 위치 처음으로 이동
writer.Write("Overwritten text");   // 버퍼에 쓰고 using 문이 닫히면 BaseStream의 처음 위치부터 덮어써짐
```
