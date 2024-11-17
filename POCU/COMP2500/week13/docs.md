# week 13

## SOLID 설계 정신

![img.png](images/img.png)

- 포프쌤은 원칙이라고 표현 X
    - 정신 정도로 표현

![img_1.png](images/img_1.png)

![img_2.png](images/img_2.png)

- SOLID 정신의 목표

![img_3.png](images/img_3.png)

- 포프쌤은 베스트 프랙티스 정도라고 생각함

## SOLID 장점

![img_4.png](images/img_4.png)

- SOLID 정신에서 얻을 수 있는 것은 유연함
  - 추상화, 일반화, 인터페이스

![img_5.png](images/img_5.png)

- 디커플링이 중요한 프로젝트는 SOLID 도움 됨
- 항상 직접적/구체적인 게 더 이해하기 쉽다는 사실을 잊지 말자

![img_6.png](images/img_6.png)

- 규모가 커지면서 유연해지면 도움이 된다
- 시작은 작게, 커지면 거기에 맞는 설계로 수정하자

![img_7.png](images/img_7.png)

- 모든 걸 외주로 개발하는 시절에는 유연함이 중요했음

![img_8.png](images/img_8.png)

![img_9.png](images/img_9.png)

![img_10.png](images/img_10.png)

- 요구사항이 잘 안 바뀌어서, 설계에 시간을 많이 쓸 수 있음

![img_11.png](images/img_11.png)

![img_12.png](images/img_12.png)

![img_13.png](images/img_13.png)

![img_14.png](images/img_14.png)

## 단일 책임

![img_15.png](images/img_15.png)

- 클래스의 존재 이유는 하나

![img_16.png](images/img_16.png)

- 한 함수에서 너무 많은 일을 하지 말라는 말을 확장함

![img_17.png](images/img_17.png)

- 하나의 일이 굉장히 주관적임

![img_18.png](images/img_18.png)

- ???

![img_19.png](images/img_19.png)

- ㅋㅋ

![img_20.png](images/img_20.png)

![img_21.png](images/img_21.png)

![img_22.png](images/img_22.png)

![img_23.png](images/img_23.png)

![img_24.png](images/img_24.png)

![img_25.png](images/img_25.png)

- 애초에 이 주장은 단일 책임 원칙을 잘 못 이해

![img_26.png](images/img_26.png)

![img_27.png](images/img_27.png)

- 그래서 어떻게 하나요?
- 객관적으로 사람들을 많이 만족시키는 방법은?

![img_28.png](images/img_28.png)

- 참여자에 따라 결정해야함

![img_29.png](images/img_29.png)

![img_30.png](images/img_30.png)

![img_31.png](images/img_31.png)

![img_32.png](images/img_32.png)

- S 정신을 위배했을 때 바로 확인할 수 있는 객관적인 지표

![img_33.png](images/img_33.png)

![img_34.png](images/img_34.png)

![img_35.png](images/img_35.png)

- 필요한 만큼 간단하게 만들자

![img_36.png](images/img_36.png)

- 바꾸려는 이유가 하나?

![img_37.png](images/img_37.png)

- ㅋㅋ

![img_38.png](images/img_38.png)

- 여튼 교훈정도는 얻을 수 있음
- 필요한만큼 간단하게 바꾸자

## 개방-폐쇄

![img_39.png](images/img_39.png)

- 클래스 내부 수정 없이 동작을 확장
- 상속

![img_40.png](images/img_40.png)

![img_41.png](images/img_41.png)

![img_42.png](images/img_42.png)

![img_43.png](images/img_43.png)

- ???

![img_44.png](images/img_44.png)

![img_45.png](images/img_45.png)

![img_46.png](images/img_46.png)

![img_47.png](images/img_47.png)

![img_48.png](images/img_48.png)

![img_49.png](images/img_49.png)

![img_50.png](images/img_50.png)

![img_51.png](images/img_51.png)

- 고치는게 정상임
- 사용 안 하면 지우자

![img_52.png](images/img_52.png)

- 이런건 버전 관리로 가능하죠...

![img_53.png](images/img_53.png)

- 외부 도구로 해결할 수 있는 문제는 그걸로 해결하자

## 리스코프 치환

![img_54.png](images/img_54.png)

- 부모 클래스가 할 수 있는 일은 자식도 다 할 수 있어야함
- 상속, 다형성으로 부모 클래스 타입에 의존해서 코드를 작성했는데, 이게 작동 안 할 수도

![img_55.png](images/img_55.png)

- 현실적으로 100% 지키기 쉽지 않음

![img_56.png](images/img_56.png)

- 자식을 추가하다가 부모 동작을 바꾸는 경우가 많음

![img_57.png](images/img_57.png)

- 추상화는 자식을 추가하다보면 변함

### 리스코프 치환 극단적 주장

![img_58.png](images/img_58.png)

![img_59.png](images/img_59.png)

![img_60.png](images/img_60.png)

![img_61.png](images/img_61.png)

![img_62.png](images/img_62.png)

- 소수설의 정신은 setter 가 문제가 되는데
- 이런 주장하는 사람들은 보통 setter 을 없애자고 주장함

![img_63.png](images/img_63.png)

![img_64.png](images/img_64.png)

## 코드보기: 스택

- 리스코프 치환을 잘 따른 경우

```java
package academy.pocu.comp2500samples.w13.stack;

import java.util.ArrayList;

public final class Stack<E> extends ArrayList<E> {
    @Override
    public void add(int index, E element) {
        super.add(element);
    }

    @Override
    public E remove(int index) {
        assert this.size() > 0;

        int lastIndex = size() - 1;
        E element = get(lastIndex);

        super.remove(lastIndex);

        return element;
    }

    @Override
    public boolean remove(Object o) {
        if (this.size() == 0) {
            return false;
        }

        remove(0);

        return true;
    }
}
```

- add() 함수를 오버라이딩해서 index를 함수 내부에서 사용하지 않게 만듬
- remove() 함수를 오버라이딩 해서 index 매개변수 값이 어떻든 무시하고 pop 기능으로 동작하게 마지막 원소를 제거하도록 만듬
  - remove() 함수가 오버로딩으로 여러 개라서 두번째 remove() 함수도 매개변수는 무시하고 pop 기능으로 동작하게 

```java
package academy.pocu.comp2500samples.w13.stack;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        while (!stack.isEmpty()) {
            int num = stack.remove(0);
            System.out.println(num);
        }

        System.out.println("-----------------");

        ArrayList<Integer> list = new ArrayList<>();

        addInOrder(list, 10);
        addInOrder(list, 2);
        addInOrder(list, 5);

        for (int num : list) {
            System.out.println(num);
        }

        System.out.println("-----------------");

        list = new Stack<>();

        addInOrder(list, 10);
        addInOrder(list, 2);
        addInOrder(list, 5);

        for (int num : list) {
            System.out.println(num);
        }
    }

    private static void addInOrder(ArrayList<Integer> list, int num) {
        int i;

        for (i = 0; i < list.size(); ++i) {
            if (list.get(i) > num) {
                break;
            }
        }

        list.add(i, num);
    }
}
```

- addInOrder 함수 내부에서 list.add() 호출함
  - list 개체가 ArrayList 타입이냐, Stack 이냐에 따라 동작이 다름
  - Stack 인 경우 정렬된채로 들어가는게 아니라, 스택 규칙에 따라 들어감
  - is-a 관계가 깨짐
  - ArrayList 관계에서 보장하는 것들이 Stack 에서 보장 X
- 상속을 남용하면 리스코프 치환 원칙을 깰 수 있음
- 그리고 애초에 스택 자료구조를 순회하는건 불가능하죠? 스택은 top 만 활용할 수 있음
- 올바르게 고치려면? Stack이 상속을 받는게 아니라 내부적으로 ArrayList를 컴포지션으로 들고 있으면 됨

## 인터페이스 분리

![img_65.png](images/img_65.png)

![img_66.png](images/img_66.png)

- 인터페이스를 분리하자는 정신인데
- 단일 책임 원칙처럼 사람이 동시에 이해할 수 있는 컨텍스트 크기의 문제

![img_67.png](images/img_67.png)

![img_68.png](images/img_68.png)

- 교훈은 밸런스 맞추기

## 의존 역전

![img_69.png](images/img_69.png)

- 개체끼리 통신할 때 추상적인 것에 의존하자
- 11주차에 배운 '인터페이스를 어디에 사용해야 하는가'를 가이드 삼으면 됨

## 소프트웨어 품질 보장에 대한 고찰

![img_70.png](images/img_70.png)

- 시작은 개발자의 실수를 줄이는 환경을 구축
- 모두가 이해하기 쉬운 코드는 구체적인 코드
  - 일반화, 추상화는 사람이 알기 어려움
- 규모가 커지면 디커플링 고려

![img_71.png](images/img_71.png)

- SOLID 마케팅에 현혹되지 말자

![img_72.png](images/img_72.png)

- 사실 결론은 필요하면 사용하자
  - 장,단점에 따라 밸런스를 잡자
