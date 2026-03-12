# week 14

## 파일

![alt text](image.png)

파일 입출력은 파일의 내용을 바꾸는 것, C# 및 다른 프로그램 언어는 파일과 관련해서 파일의 내용을 바꾸는 것 말고도 다양한 기능을 지원한다.

C#에서는 File 클래스를 이용한다. File 클래스는 정적 클래스다. 정적 클래스의 정의에 따라 File 클래스의 모든 멤버 함수들도 `정적 메서드`다.

## 왜 정적 메서드로 File 클래스를 구성했을까?

![alt text](image-1.png)

파일 시스템은 파일을 관리하는 역할을 한다. 운영체제 입장에서 파일 시스템은 개체라기 보다 하나의 (전역)정적 클래스라고 볼 수 있음.

반면 파일 시스템에서 관리하는 파일 하나는 개체라고 볼 수 있다.

## File.Exists()

![alt text](image-2.png)

예외처리를 하기 싫다면 File.Exists를 호출해라는 의도가 있다.

## 파일 이동하기

![alt text](image-3.png)

코드를 분석해보자.

```C#
if (!File.Exists(source))
{
	return false;
}
```

File.Exists 메서드를 통해서 파일이 존재하는지 여부를 확인할 수 있다.
1) path 길이 0
2) path가 null
3) 올바른 path가 아닐 때
4) 올바른 path여도 파일 접근 권한이 없을 때

다음은 try문이다.

```C#
try
{
	File.Move(source, destination);
	return true;
}
```

File.Move는 파일을 이동하는 함수다. 이를 실행하다가 예외가 발생하면 return true에는 닿지 못한다. 그리고 catch 문으로 이동해서 예외 메세지를 출력하고 return false하게 된다.

정상적으로 File.Move가 작동하면 return true를 반환한다.

## File.Move

![alt text](image-4.png)

파일을 이동하면서 이름을 바꿀 수 있는데, 이 개념도 Move에 포함된다.

sourceFileName, detFileName 매개변수 모두 절대경로가 들어간다.

## is 키워드

![alt text](image-5.png)

런타임에 타입을 확인하는 키워드다.

\* 컴파일 타임에는 컴파일러가 알 수 있죠?

## 파일 이동하기 실패하는 케이스

1. 잘못된 경로

![alt text](image-6.png)

2. 기타 실패

![alt text](image-7.png)

결론은 파일 연산 할 때 문서 읽어보고 예외를 확인해서 필요한 경우 예외처리를 해줘야한다.

## File.Copy()

![alt text](image-8.png)

Move는 복사하고 원본을 지우는 것이라면, Copy는 복사하는 것임

## File.Delete()

![alt text](image-9.png)

## File.Encrypt() / File.Decrypt()

![alt text](image-10.png)

암호화할 때 로그인한 계정을 통해서 암호화한다. 따라서 로그인 계정에서 Decrypt해야지 암호화를 풀 수 있다. 즉 계정을 잃어버리면 안 되겠죠?

## 디렉터리

![alt text](image-11.png)

트리 구조

## Directory.Exists()

![alt text](image-12.png)

파일 때 처럼 예외를 최대한 처리하지 않기 위해 만든 함수

## Directory.Move()

![alt text](image-13.png)

\* Directory를 Copy하는 함수는 없다!

## Directory.CreateDirectory()

![alt text](image-14.png)

모든 중간 디렉터리도 만들어 준다는 점을 기억하자

이미 존재하는 디렉토리를 만드려고 하면 결과적으로 차이가 없기에 예외를 던지지 않는다. (예전에 결과적으로 원하는 바가 같으면 예외를 던지지 않는다는 것을 컬렉션 다룰 때 배움)

## Directory.Delete()

![alt text](image-15.png)

recursive 매개변수가 존재하는 이유는 재귀적으로 C: 같은 폴더도 다 지울 수 있기 때문이다.

## Directory.GetDirectories()

![alt text](image-16.png)

디렉터리 안에 있는 모든 하위 디렉터리 즉 top level 절대 경로만 문자열 배열로 반환함

![alt text](image-17.png)

검색 패턴에 맞는 top level 절대 경로만 반환하는 문자열의 배열에 포함시킬 수도 있음.

검색 패턴에는 와일드카드가 사용된다. 0개 이상만 치환하냐, 1개만 치환하냐의 차이

## Directory.GetFiles()

![alt text](image-18.png)

GetDirectory()와 마찬가지로 top level 파일의 절대 경로만 문자열 배열로 반환한다.

![alt text](image-19.png)

SearchOption이라는 enum을 지원한다. top level만 찾을 지 하위까지 재귀로 모두 찾을지의 옵션

여기서 중요한 점은 `절대경로`를 반환한다는 점

## Directory를 Copy하는 함수는 없다.

![alt text](image-20.png)

for문 돌거나 재귀 함수로 각 디렉토리에서 Directory.CreateDirectory()로 디렉토리 만들고 File.Copy() 호출해서 복사하는 식으로 하면 됨

## 경로

![alt text](image-21.png)

## 경로 관련 용어 정리

![alt text](image-22.png)

## 운영체제 마다 경로 구분 문자가 다르다

![alt text](image-23.png)

![alt text](image-24.png)

Path.DirectorySeparatorChar

## 경로를 합칠 때 구문 문자 중복/누락

![alt text](image-25.png)

![alt text](image-26.png)

Path.Combine()으로 하자!

## 파일 또는 폴더 이름만 가져오기 귀찮음

![alt text](image-27.png)

![alt text](image-28.png)

### Path.GetFileName(), Path.GetFileNameWithoutExtension()

![alt text](image-29.png)

### Path.GetDirectoryName()

![alt text](image-30.png)

## 확장자 관리 및 변경

![alt text](image-31.png)

### Path.GetExtension(), Path.ChangeExtension()

![alt text](image-32.png)

## 절대 경로 구하기

![alt text](image-33.png)

### Path.GetFullPath()

![alt text](image-34.png)
![alt text](image-35.png)

## 상대 경로 구하기

![alt text](image-36.png)

## Path.GetRelativePath()

![alt text](image-37.png)

## 라이브러리

함수는 왜 작성하는지 생각해보자. 재활용성이 있을 때 코드 중복을 막기 위해서다.

만약 2개의 프로그램을 만들고 있고 똑같은 기능을 두 프로그램에서 모두 사용하고 싶다면? 재사용하기 위해서 라이브러리를 만들어보자.

![alt text](image-38.png)

평균을 구하는 기능을 공유가 가능하지 않을까?

## 두 프로젝트가 다르다면?

![alt text](image-39.png)

## 클래스 라이브러리 프로젝트

![alt text](image-40.png)

## 비쥬얼 스튜디오의 솔루션과 프로젝트

![alt text](image-41.png)

솔루션은 프로젝트를 포함하는 개념이다. 솔루션 하위에 여러 프로젝트를 만들 수 있다.

## 솔루션에 여러 프로젝트를 만드는 방법

![alt text](image-42.png)

![alt text](image-43.png)

![alt text](image-44.png)

![alt text](image-45.png)

솔루션을 만든 상태로 새 프로젝트를 생성하려고 하기 때문에 솔루션 관련 내용은 `새 프로젝트 추가` 창에 없다.

![alt text](image-46.png)

## 클래스 라이브러리 만들기

![alt text](image-47.png)

![alt text](image-48.png)

![alt text](image-49.png)

![alt text](image-50.png)

클래스 라이브러리 자체는 실행할 수 없다.

![alt text](image-51.png)

종속성에서 어떤 라이브러리와 관계가 있는지 설정할 수 있다. 즉 참조 추가를 통해 클래스 라이브러리와 종속 관계를 맺을 수 있다.

![alt text](image-52.png)

이제 사용할 수 있다.

![alt text](image-53.png)

## 클래스 라이브러리 배포

![alt text](image-54.png)

dll 파일은 소스코드 빼고 컴파일한 결과이다. 이 파일을 다른 사람에게 배포할 수 있다.

요즘은 dll파일로 배포하는 것보다 NuGet이라는 패키지 배포 웹사이트를 사용한다.

## 남의 라이브러리 쓰기

dll 파일을 사용해보자

![alt text](image-55.png)

## 직렬화(Serialization)

직렬화를 지원하는 언어가 있고, 아닌 언어가 있다. 보통 실행 중 어떤 개체, 클래스 데이터 종류를 모두 알 수 있다면 직렬화를 지원하기가 쉽다. 그렇지 않은 경우 직렬화를 지원하기 어렵다.

## 파일을 한땀한땀 저장할 때 불편했다.

![alt text](image-56.png)

## 편하게 저장하고 싶다면?

![alt text](image-57.png)

개체를 바로 텍스트 파일로?

## 직렬화와 역직렬화

![alt text](image-58.png)

보통 개체 데이터는 메모리에 산재되있다. 컴퓨터에서 실행 중일 때는 메모리 참조를 할 수 있기 때문에 산재된 데이터를 가져다 쓸 수 있다.

하지만 파일에 저장할 때는 산재해서 저장할 수 없다. 일렬로 저장한다.

## 직렬화 방법1 : 직접구현

![alt text](image-59.png)

실행 중 데이터를 볼 수 없는 C, C++같은 언어에서는 이렇게 할 수 밖에 없다.

## 직렬화 방법2 : Serializer 이용

![alt text](image-60.png)

### XML

![alt text](image-61.png)

### 이진

![alt text](image-62.png)

.dat 확장자로 저장함

### XML vs 이진 비교

![alt text](image-63.png)

## Json

![alt text](image-64.png)

Json에서 숫자형은 그냥 숫자로 들어감. 지금 스크린샷에 "21"은 문자형 21

## Json 직렬화

![alt text](image-65.png)

## Json.NET 설치

![alt text](image-66.png)

솔루션에서 우클릭해서 솔루션용 Nuget 패키지 관리를 누른다.

![alt text](image-67.png)

찾아보기에서 검색하면 됩니다.

모든 프로젝트에서 패키지 버전 관리를 할 수 있다. 포프쌤 팁은 한 솔루션의 모든 프로젝트에서 패키지 버전을 동일하게 하는 것 추천

## Json.NET 사용

### 직렬화

![alt text](image-68.png)

![alt text](image-69.png)

```C#
string output = JsonConvert.SerializeObject(character);
```

SerailzeObject 메소드를 실행하면 바로 string의 return 값이 나오는데 이게 Json format이다.

### 역직렬화

![alt text](image-70.png)

```C#
Human copiedChar = JsonConvert.DeSerializeObject<Human>(character);
```

직렬화와 다르게 역직렬화는 어떤 클래스 타입으로 역직렬화해야하는지 명시해주기 위해 <T>(제너릭)을 사용한다.

### 딕셔너리, 리스트를 직렬화/역직렬화 가능하다.

![alt text](image-71.png)

### deep copy 꿀팁

참고로 가장 쉬운 deep copy방법이 어떤 개체를 직렬화해서 string을 추출한 다음. 이 string으로 역직렬화를 해서 새로운 개체를 만들면 된다.

물론 이 연산은 굉장히 느립니다. 하지만 코딩은 매우 간편하죵