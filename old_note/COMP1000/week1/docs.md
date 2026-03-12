# Week1

## 수학이란?

- 수학의 본질에 대해 생각해보자
- 왜 프로그래머에게 수학이 필요한지 이해해보자

![img.png](images/img.png)

- 공식을 외우는 것은 수학이 아니다

![img_1.png](images/img_1.png)

- 수학은 세상을 이해하기 위한 학문

![img_2.png](images/img_2.png)

![img_3.png](images/img_3.png)

![img_4.png](images/img_4.png)

![img_5.png](images/img_5.png)

![img_6.png](images/img_6.png)

- 현상을 이해하고자 하는 노력의 예시로 탈레스의 일화

![img_7.png](images/img_7.png)

![img_8.png](images/img_8.png)

![img_9.png](images/img_9.png)

- 물리도 마찬가지

## 과학적 사고

![img_10.png](images/img_10.png)

- 현상은 경험을 통해서만 알 수 있음
- 현상을 분석하려면 경험이 전제가 되어야한다
- 하지만 경험할 수 없는 영역은?

![img_11.png](images/img_11.png)

- 믿음의 도약
    - 누군가 증명해놓은 이론을 믿고
    - 그 이론을 기초삼아 새로운 이론을 만들고
    - 반복...
    - 수학은 이렇게 발전해왔음

- 이론에 기초해 새로운 이론을 만드는 과정이 과학적 사고방법의 일종

![img_12.png](images/img_12.png)

- 팩트는 아니지만 천동설도 과학적 사고방식의 예

![img_13.png](images/img_13.png)

- 과학적 사고방법
    - 가설을 세우고
    - 현상을 예측
    - 가설과 관찰 결과를 비교해서 확인

## 프로그래머에게 수학이 필요한 이유

![img_14.png](images/img_14.png)

- 프로그래머는 디지털을 위한 수학을 배움

![img_15.png](images/img_15.png)

- 이산수학

![img_16.png](images/img_16.png)

- 수학에서 배우는 논리적 사고방식은 매우 중요하다

### 모든 수학을 다 배워야하나?

![img_17.png](images/img_17.png)

- 프로그래머는 응용수학을 배우게 됨

![img_18.png](images/img_18.png)

- 순수수학은 천재의 영역

![img_19.png](images/img_19.png)

## COMP1000 과목에서 다루는 수학

![img_20.png](images/img_20.png)

![img_21.png](images/img_21.png)

## 프로그래밍 방식의 변화

- 프로그래밍에서도 증명이 필요하지 않나요?

![img_22.png](images/img_22.png)

- 복잡도 이론은 수학적 증명을 기반으로 만들어짐

![img_23.png](images/img_23.png)

- 알고리즘에서 기본적으로 배우죠

![img_24.png](images/img_24.png)

- 예전에는 증명하고 돌려야했음...

![img_25.png](images/img_25.png)

- 단말기(terminal)
- 메인프레임 공유하는 낭만의 시절

![img_26.png](images/img_26.png)

- 무한 루프 발생하면 종이 무한으로 출력 ㅋㅋ

![img_27.png](images/img_27.png)

- 개인용 PC의 도입
- 돌려보면서 디버깅하는 경우가 많음!

![img_28.png](images/img_28.png)

- 목적은 컴퓨터의 원리를 이해하는 것!

## 숫자 체계

![img_29.png](images/img_29.png)

- 수를 시각적으로 표시하는 방법
- 기수법:
    - 숫자를 기록하는 법

![img_30.png](images/img_30.png)

- 일반적으로 10진법을 사용

![img_31.png](images/img_31.png)

- 60진법도 있구

![img_32.png](images/img_32.png)

- 컴퓨터는 2진법을 이해함

![img_33.png](images/img_33.png)

- 16진법은 나중에 다룬다
- 2진법에서 가능한 특별한 것들도 배운다

## 10진법

### 진법이란?

![img_34.png](images/img_34.png)

- 진법의 정의:
    - 수를 표기하는 방법

- N진법에서 숫자 N의 의미:
    - 한 자리에 쓸 수 있는 수의 개수

### 10진법

![img_35.png](images/img_35.png)

- 한 자리에 쓸 수 있는 수의 개수가 10
- "나아갈 진" 한자의 생각하면 10이되면 나아감 == 자리 수가 올라감

### 10진법의 자리값

![img_36.png](images/img_36.png)

- 가장 오른쪽은 10^0 자리라고 표현
- 이후로 10^1 .. 10^n으로 자리 수가 증가

![img_37.png](images/img_37.png)

- 10진법을 자릿값 표현으로 표현한 방식

- 자릿값 표현을 이용하면 정형회된 방식으로 수를 표현할 수 있다
    - 이 표현 방식을 사용하면 10진법 말고도 표현 가능
    - 시그마로 표현하면 일반화 가능

### 10진법의 셈(counting)

![img_38.png](images/img_38.png)

- 0부터 9까지

![img_39.png](images/img_39.png)

- 최대숫자인 9에 도달한 이후 다음 자리로 넘어감
- 다음 자리로 넘어갈 때 기존 자리 수는 최소값인 0으로 돌아감

- 0부터 시작해서 9까지 존재하는 원칙은 모든 자리 수에 적용
    - 00, 01, 02 ... 09
    - 10, 11, 12 ... 19
    - ...
    - 90, 91, 92 ... 99
    - 다만 한 자리 수에서 가장 왼쪽의 leading zero를 생략하고 표기함

### 10진법의 덧셈

![img_40.png](images/img_40.png)

- carry-over:
    - 자리 수 올라가면서 "받아 올림"의 개념

### 10진법의 뺄셈

![img_41.png](images/img_41.png)

- borrowing:
    - 자리 수 내려가면서 "받아 내림"의 개념

## 2진법

![img_42.png](images/img_42.png)

- 각 자리수의 최대 2개의 수를 사용
- binary의 'b'를 따와서 표기할 때도 있음
    - 보통 프로그래밍 언어에서 표기 지원함

### 2진법의 자리값

![img_43.png](images/img_43.png)

- 가장 오른쪽은 2^0
- 한 자리씩 증가하면 2^1... 이렇게 증가

![img_44.png](images/img_44.png)

- 자리값을 이용해 일반화된 계산을 하면 10진수로 변경할 수 있음

### 2진법의 셈

![img_45.png](images/img_45.png)

- 0부터 1까지
    - 0이 최소
    - 1이 최대

![img_46.png](images/img_46.png)

- 진법에 일반적으로 적용되는 받아올림 적용
    - 2^0 자리는 최소값 0으로 돌아감

![img_47.png](images/img_47.png)

- 받아올림 적용할 때 기존 자릿 수를 *모두* 최소값 0으로 초기화
    - 11(2) 에 1을 더하면
    - 100(2)

![img_48.png](images/img_48.png)

- 아래에서 부터 위로 읽을 때 패턴이 있음
    - 2^0 자리의 경우:
        - 010101...
    - 2^1 자리의 경우:
        - 00110011...
    - 2^2 자리의 경우:
        - 0000111100001111

### 2진법의 덧셈

![img_49.png](images/img_49.png)

- 받아올림 적용
    - 11 + 11
    - 2^0 자리는 받아올리면 최소값인 0으로 초기화
    - ...

### 2진법의 뺄셈

![img_50.png](images/img_50.png)

- 받아내림 적용

- 결국 10진법에서 적용하는 원리를 그대로 적용하면 됨

## 8진법

![img_51.png](images/img_51.png)

- 한 자리에 8개의 숫자를 사용해서 수를 표현
    - 최소 0
    - 최대 7

### 8진법의 자릿값

![img_52.png](images/img_52.png)

- 8진수 보고 10진수로 변환할 줄 알면 끝

### 8진법의 셈

![img_53.png](images/img_53.png)

- 받아올림 적용
    - 올라갈 때 기존 자리수 최소값 0으로 초기화

### 8진법의 덧셈

![img_54.png](images/img_54.png)

- 받아올림
    - 3 + 7 - 8 == 2
    - 기존 자리의 수 계산

### 8진법의 뺄셈

![img_55.png](images/img_55.png)

- 받아내림
    - 8^0의 자리
        - 8^1 자리에서 8을 빌려와서 8 - 1
    - 8^1의 자리
        - 8^2 자리에서 8을 빌려와서 8 - 1

## 16진법

![img_56.png](images/img_56.png)

- 한 자리에 16개의 숫자를 사용해서 수를 표현
    - 최소 0
    - 최대 F

### 16진법의 셈

![img_57.png](images/img_57.png)

- 받아올림 적용

### 16진법 덧셈

![img_58.png](images/img_58.png)

- 10 == A

![img_59.png](images/img_59.png)

- C == 12

### 16진법 뺄셈

![img_60.png](images/img_60.png)

![img_61.png](images/img_61.png)

- 16 빌려와서 계산

## 진수 변환

### 10진수를 2진수로 표현

![img_62.png](images/img_62.png)

- 체계적인 알고리듬이 필요함
- 2로 나누면서 나머지를 기록하는 방식
    - 나누는 대상이 1 or 0 이면 종료

![img_63.png](images/img_63.png)

![img_64.png](images/img_64.png)

- 참고로 십진수를 'Decimal'로 불러서 *DEC* 라는 표현을 사용

![img_65.png](images/img_65.png)

- 32 채우고
- 4
- 1
- 100101(2)

### 2진수를 10진수로 변환

![img_66.png](images/img_66.png)

- 2진법의 셈과 동일함

![img_67.png](images/img_67.png)

### 10진수 <-> 8진수

![img_68.png](images/img_68.png)

- 10진수를 8진수로 표현할 때
    - 8로 나누면서 나머지 기록
    - 나누는 대상이 8보다 작으면 종료

### 10진수 <-> 16진수

![img_69.png](images/img_69.png)

- 동일한 논리

### 2진수에서 8진수로 변환

![img_70.png](images/img_70.png)

- 2진수의 특수한 성질을 활용

### 8진수에서 2진수로 변환

![img_71.png](images/img_71.png)

![img_72.png](images/img_72.png)

- 2와 8의 관계를 활용했음
- 8진수의 한자릿수는 2진수의 세자릿수와 동일

### 2진수를 16진수로 변환

![img_73.png](images/img_73.png)

- 4자리씩 끊으면 됨

### 16진수를 2진수로 변환

![img_74.png](images/img_74.png)

### 8진수 <-> 16진수

![img_75.png](images/img_75.png)

- 특수한 관계가 없음
- 중간에 2진수로 변환하고 다시 변환하기

## 컴퓨터는 왜 2진수만 사용할까?

![img_76.png](images/img_76.png)

- 트랜지스터는 반도체라 전류의 흐름을 조절할 수 있음

![img_77.png](images/img_77.png)

- 트랜지스터의 두 상태를 숫자로 표현할 때 이진법이 적함

![img_78.png](images/img_78.png)

- 트랜지스터의 상태를 기록하는 가장 최소의 단위를 '비트'라고 부름

## 비트

![img_79.png](images/img_79.png)

- 디지털에서 가장 작은 단위
- 2진법과 어울림

![img_80.png](images/img_80.png)

- 큰 값을 표현하기 위해서는 여러 비트를 사용

![img_81.png](images/img_81.png)

### 비트의 순서

![img_82.png](images/img_82.png)

![img_83.png](images/img_83.png)

- 정답은 없음
- 기본적으로 제일 낮은 비트가 오른쪽으로 가정

## 비트 수와 오디오 포맷

![img_84.png](images/img_84.png)

- 오디오 품질은 비트 수에 따라 결정
    - 스트리밍 퀄리티

- 비트레이트:
    - 1초에 들어오는 데이터의 크기
    - Premium 2배로 데이터를 받으니까 음질이 2배

![img_85.png](images/img_85.png)

- 비트 뎁스:
    - 양자화 개념
    - 그래프의 수직축
    - 음파의 높이를 몇 단계로 나누느냐?
        - 16비트라면 16단계로 나눔

![img_86.png](images/img_86.png)

- 샘플링 레이트:
    - 그래프의 수평축
    - 1초에 막대 몇 개 넣을거냐?
    - 초마다 샘플 몇 개를 챕쳐하냐?

### 오디오 파일의 크기 계산하기

![img_87.png](images/img_87.png)

- wave 파일은 압축하지 않음

![img_88.png](images/img_88.png)

- 'KB' 대문자임
    - 킬로바이트

### 데이터를 저장할 때 단위

![img_89.png](images/img_89.png)

![img_90.png](images/img_90.png)

![img_91.png](images/img_91.png)

- 저장할 때 최소 단위는 '바이트'

## 바이트

![img_92.png](images/img_92.png)

- 8b == 1B

### 왜 저장할 때 바이트를 사용해?

![img_93.png](images/img_93.png)

- 비트는 너무 작음

![img_94.png](images/img_94.png)

- 읽어 오기

![img_95.png](images/img_95.png)

- 쓰기

### 전송에는 비트를 사용함

![img_96.png](images/img_96.png)

- 마케팅에 좋아서 사용함
    - 인터넷이 빨라 보이는...

- 관습적으로 네트워크 쪽에서는 비트를 많이 사용

## 컴퓨터의 데이트 단위

![img_97.png](images/img_97.png)

- 서구권에서 세자리씩 끊음
    - a thousand
    - a million
    - ...

### 컴퓨터의 1K는 1024

![img_98.png](images/img_98.png)

- 2진수로 표현할 때 깔끔하기 위함

![img_99.png](images/img_99.png)

- 1000 대신 단위 올라갈 때 1024를 곱하자

### 예외도 있음

![img_100.png](images/img_100.png)

- 예외:
    - 하드디스크 제조사
    - 네트워크 카드 제조사
    - 인터넷 망 회사

![img_101.png](images/img_101.png)

- 숫자 뻥튀기 용도

![img_102.png](images/img_102.png)

- 기본은 1024
