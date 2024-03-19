# Week12

## 값형

![alt text](image.png)

원본을 바꾸고 싶으면 ref 키워드 사용

![alt text](image-1.png)

```C#
ref int nu2 = num1;	// 불가능
```

## 참조형

![alt text](image-2.png)

배열은 참조형

C#에서 모든 데이터형은 값형 또는 참조형임

![alt text](image-3.png)

enum은 값형임

## 값형과 참조형의 차이

값형은 CPU안에 저장될 수 있음

참조형은 언제나 메모리에 저장됨

CPU에서 참조형을 읽기 위해 메모리의 데이터를 읽어오고, 변수에서 참조형을 수정하면 메모리의 원본 데이터가 수정됨

## 참조형과 쓰레기 수집기

![alt text](image-4.png)

## 참조형의 인자 전달

![alt text](image-5.png)

swap

## 참조형의 인자 전달과 ref

![alt text](image-6.png)

클래스가 참조형인데 ref를 굳이 사용할 이유가 없다.

![alt text](image-7.png)

ref 키워드로 참조를 한 번 더해서 클래스 자체를 바꿀 수 있긴함

하지만 생각보다 쓸 일이 없음

참고로 SwapHuman의 매개변수에서 ref 키워드를 제거하면

## 참조형 vs 값형의 매개변수 전달

![alt text](image-8.png)

class를 값형으로 바꿀 방법은 없음

C#에서 `구조체`는 class와 비슷하지만 값형임

## 구조체

클래스랑 정말 비슷한데, 참조형이 아니라 값형이다.

(C, C++의 구조체와 혼동하면 안됨)

![alt text](image-9.png)

클래스에서는 할 수 있었지만 못하는 기능들에 대해서 언급하는 이유는 구조체를 자주 사용 안 하기 때문임

> C#에서 구조체 = 함수 + 값 값형

## 구조체 예시

![alt text](image-10.png)

프로퍼티, 생성자, 함수를 모두 가질 수 있다.

## 구조체의 생성자

![alt text](image-11.png)

매개변수가 있는 생성자만 만들 수 있음

매개변수가 없는 생성자는 프로그래머가 코딩으로 만들 수 없음

매개변수가 없는 생성자는 컴파일러가 자동으로 생성하고, 값형의 기본값인 0, 0.0 이 대입된다.

매개변수가 없는 생성자를 만들때 0대신 다른값을 멤버 변수의 기본값으로 대입하는 것을 방지하기 위함

값형의 기본값은 0이다라는 개념을 엄격하게 지킴

## 구조체의 멤버변수와 프로퍼티

![alt text](image-12.png)

마찬가지로 값형의 기본값은 0이라는 개념을 지키기 위해 멤버 변수와 프로퍼티에 선언과 동시에 대입이 불가능

바꾸고 싶으면 1) 매개변수를 가지는 생성자를 통해 대입해서 구조체를 생성하거나,  2) 생성 후 setter로 바꿔야함.

단 상수나 정적변수는 모든 구조체에 공유하는 값이라 선언과 동시에 초기화 할 수 있음. 구조체를 생성하는 개념이 아니라서

> 구조체 생성 ~ 구조체는 값형 ~ 값형의 원칙

## 구조체 생성 방법

![alt text](image-13.png)

## 구조체를 매개변수로 전달하기

![alt text](image-14.png)

### 값형을 매개변수로 전달

![alt text](image-15.png)

첫번째 함수는 매개변수의 구조체에 ref키워드를 사용하지 않았기 때문에 값형임.

따라서 원본 구조체의 값은 안 바뀜

### 참조형을 매개변수로 전달

![alt text](image-16.png)

두번째 함수는 매개변수의 구조체에 ref 키워드를 사용했고 참조형이다.

원본이 바뀐 것을 확인할 수 있음

## 클래스와 구조체 모두 자료형

![alt text](image-17.png)

## 클래스 vs 구조체

![alt text](image-18.png)

원본 대신 읽기 전용 복사본을 전달하고 싶을 때 구조체를 사용

## 면접 중요 질문

값형 vs 참조형 (구조체와 클래스의 비교)

가비지 컬렉터의 동작 원리와 C#에서 어떻게 개선해왔는지

## 코드보기 : 구조체

```C#
CartItem cartItem = new CartItem
{
	Name = "Hello World! Programming Book",
	Price = 10.99m,
	Quantity = 5
};
```

[object initializer](https://learn.microsoft.com/ko-kr/dotnet/csharp/programming-guide/classes-and-structs/how-to-initialize-objects-by-using-an-object-initializer)

json 과 같은 데이터를 단순하게 전달하기 위해서 사용하는 클래스(DTO)에 사용할 수 있는 유용한 방식

## Nuallble

null은 아무것도 없다는 개념

## 클래스 멤버 변수의 기본값

![alt text](image-19.png)

null은 비트패턴이 0임

## 구조체 멤버 변수의 기본값

차이점

- Head가 구조체라서 구조체 내부 값은 0에 준하는 값으로 초기화 된다.

- Head가 null이 아니라 존재하지만 초기값이 0임

## null

![alt text](image-20.png)

참조형에서 0번지 = 데이터가 없다

## 값형과 null

![alt text](image-21.png)

값형은 cpu에 있는데 null은 메모리에 없다는 개념이라 이상함

참조형의 null과 값형의 0이 비트 패턴은 동일해도 프로그래밍 언어(컴파일러) 입장에서는 다름

## 값형에 null 쓰고 싶으면?

![alt text](image-22.png)

## Nuallbale<T>

![alt text](image-23.png)

Nullable<T>는 구조체다 대신 T에는 값형만 가능

T는 값형만 가능하다는 것을 주의! 이 값형에는 구조체가 포함되죠?

이 개념이 값형에 null을 쓰기 위해서 나왔음!

왜냐하면 어차피 참조형의 경우 null을 가질 수 있잖아? 따라서 만드는 것이 말이 안 됨

## Nullable 변수 선언 및 대입

![alt text](image-24.png)

선언하고 대입 안하면 null

## Nullable 변수가 null인지 확인

![alt text](image-25.png)

## Nullable 변수의 값을 읽어오기

![alt text](image-26.png)

## Nullable 변수의 값 비교

![alt text](image-27.png)

내부적으로 null 체크 후 값을 비교해줌

## Nullable 변수 대입하기

![alt text](image-28.png)

Nullable에 값형 대입 불가능

대입 대상(Nullable 타입)이 null이면 대입할 수 없으니 굳이 null인지 아닌지 확인하지 않고, 그냥 대입을 못하게 막음

> 헷갈리죠? Nullable과 아닌 것을 섞어 쓰면 복잡해짐

## 그럼 다 Nullable을 쓰면 되지 않나?

![alt text](image-29.png)

## Nullable 사용 시 오버헤드

![alt text](image-30.png)

## 정말 필요한 순간에만 Nullable 쓰기

![alt text](image-31.png)

## LINQ

데이터가 많을 때 데이터를 쉽게 처리하는 방법

## 데이터 베이스 예시

![alt text](image-32.png)

표형태(table)

## 테이블의 각 행(row)를 클래스로!

![alt text](image-33.png)

## 클래스로 만든 행 개체를 컬렉션에 담으면?

![alt text](image-34.png)

## 쿼리(query)

![alt text](image-35.png)

데이터 베이스에 직접 쏠 수 있는 명령어(속된말로 데이터 베이스를 긁는다고 표현)

### 쿼리에서 *

![alt text](image-36.png)

wildcard로 모든 칼럼을 의미합니다.

### 쿼리에서 FROM

![alt text](image-37.png)

### 쿼리에서 조건 WHERE

![alt text](image-38.png)

이런 쿼리를 프로그래밍 언어에서 하고 싶어서 나온 것이 LINQ

## 방법1 : 반복문

![alt text](image-39.png)

반복문 보다 쿼리가 좋아서 나온 것이 LINQ

## 방법2 : LINQ

![alt text](image-40.png)

![alt text](image-41.png)

프로그래머에게 굉장히 어색하다.

## 이 과목에서 다루는 LINQ

![alt text](image-42.png)

## LINQ 함수들

![alt text](image-43.png)

## LINQ : WHERE

![alt text](image-44.png)

각각 코드를 읽어보자

![alt text](image-45.png)

![alt text](image-46.png)

내부적으로는 for문이 돌게 되어 있음

반환할 때는 새로운 컬렉션을 반환

![alt text](image-47.png)

![alt text](image-48.png)

## 메뉴 변경

![alt text](image-49.png)

## LINQ : ORDER BY()

![alt text](image-50.png)

오름차순으로 정렬해서 새로운 컬렉션을 반환

인자로 정렬의 기준을 넣음

정렬 기준이 가격인데 가격이 같은 경우 어떤 순서로 정렬되는지 의문이 생김

## LINQ : THEN BY()

![alt text](image-51.png)

두번째 정렬 기준을 추가함

이름 순

![alt text](image-52.png)

메소드 체이닝 호출

## OrderByDescending(), ThenByDescending()

![alt text](image-53.png)

## 섞어 사용하기

![alt text](image-54.png)

## Where과 섞어 쓰기

![alt text](image-55.png)

## First()

![alt text](image-56.png)

컬렉션에 아무 요소가 없으면 예외 발생

## FirstOrDefault()

컬렉션에 아무 요소가 없어서 발생하는 예외를 방지하는 방법

![alt text](image-57.png)

## ALL()

![alt text](image-58.png)

모든 요소가 조건을 만족하나요?

![alt text](image-59.png)

## Any()

![alt text](image-60.png)

하나의 요소라도 조건을 만족하는게 있나요?

참고로 Any()에 아무런 조건도 넣지 않으면? 하나라도 데이터가 있나? 를 의미함

```C#
menuItems.Any();

menuItems.Connt() > 0
```

## 컬렉션 변환

![alt text](image-61.png)

ToDictionary에서 이미 값은 알고, Key를 정해줘야함

## SELECT

![alt text](image-62.png)

좀 이질적이죠?

## SELECT 보다 나은 방법

![alt text](image-63.png)

새로운 클래스를 선언하고 클래스로 변환하면 됨

## 주의할 점 1 : 성능

![alt text](image-64.png)

![alt text](image-65.png)

![alt text](image-66.png)

## 주의할 점 2 : 가독성

![alt text](image-67.png)

해결하려면 쪼개서 변수에 대입하면 됨

![alt text](image-68.png)