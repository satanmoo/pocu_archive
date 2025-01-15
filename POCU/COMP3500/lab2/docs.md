# Lab2

## Node 클래스

```java
package academy.pocu.comp3500.lab2.datastructure;

public final class Node {
    private final int data;
    private Node next;

    public Node(final int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public Node getNextOrNull() {
        return this.next;
    }

    public void setNext(final Node node) {
        this.next = node;
    }
}

```

## LinkedList 클래스의 메서드 구현하기

### append() 메서드를 구현한다

```java
public static Node append(final Node rootOrNull, final int data);
```

- 시간복잡도 O(N)
    - N은 연결리스트의 길이
- 현재 연결리스트에 노드가 없는 경우, 새로 노드 생성해서 생성한 노드 반환

- 마지막 노드까지 이동
    - Node.getNextOrNull 로 마지막 노드 찾을 수 있음
- 데이터로 노드 생성
- 마지막 노드의 다음으로 새로운 노드 추가
- 루트 반환

### prepend() 메서드를 구현한다

```java
public static Node prepend(final Node rootOrNull, final int data);
```

- 시간복잡도 O(1)
- 데이터로 노드 생성
- 새로 생성한 노드의 다음으로 로트 노드 추가
- 새로운 루트 노드 반환

### insertAt() 메서드를 구현한다

```java
public static Node insertAt(final Node rootOrNull, final int index, final int data);
```

- 시간복잡도 O(N)
    - N은 연결리스트의 길이

- 데이터로 노드 생성

- 루트노드부터 순회해서 index - 1 번째 노드 찾기
    - 0-based index
- index - 1 번째 다음에 생성한 노드 넣기
- 새로운 노드 다음에 index 번째 노드 넣기

- 루트 반환

- 맨 앞에 넣는 경우 edge case 처리

- 인덱스 범위 벗어나면 아무것도 안 하기
    - 순회해서 index - 1 번째 노드를 못 찾으면

### removeAt() 메서드를 구현한다

```java
public static Node removeAt(final Node rootOrNull, final int index);
```

- 시간복잡도 O(N)
    - N은 연결리스트의 길이

- 루트노드부터 순회해서 index - 1 번째 노드 찾기
    - 0-based index
- index - 1 번째 다음에 index + 1 번째 노드 넣기
- index 번째 노드에서 연결 끊기

- 맨 앞에 삭제하는 경우 edge case 처리

- 인덱스 범위 벗어나면 아무것도 안 하기
    - 순회해서 index - 1 번째 노드를 못 찾으면

### getIndexOf() 메서드를 구현한다

```java
public static int getIndexOf(final Node rootOrNull, final int data);
```

- 시간복잡도 O(N)
    - N은 연결리스트의 길이

- 루트 노드부터 순회
- 첫번째로 일치하는 데이터를 가진 노드 색인 반환
    - 0-based index
- 못 찾으면 -1 반환

### getOrNull() 메서드를 구현한다

```java
public static Node getOrNull(final Node rootOrNull, final int index);
```

- 시간복잡도 O(N)
    - N은 연결리스트의 길이

- 루트 노드부터 순회
- 색인 카운트해서 반환
    - 0-based index
    - 유효하지 않은 인댁스면 null 반환

### reverse() 메서드를 구현한다

```java
public static Node reverse(final Node rootOrNull);
```

- 시간복잡도 O(N)
    - N은 연결리스트의 길이

- 루트 부터 순회
- 이전 노드의 주소를 기억
- 다음 노드의 주소를 기억
    - 순회할 때 사용함
- 이번 노드의 다음(next)을 이전 노드로

- 순회 끝나면 마지막 노드가 새로운 루트 노드
- 새로운 루트 노드 반환하기!

```java
public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull);
```

- 시간복잡도 O(min(N,M))
    - N은 첫번째 연결리스트의 길이
    - M은 두번째 연결리스트의 길이

- 첫 번째 연결리스트의 다음 노드 기억
- 첫 번째 연결리스트의 노드의 다음 노드를 두번 째 연결리스트의 노드로
- 두 번째 연결리스트의 노드로 이동
- 두 번째 연결리스트의 다음 노드 기억
- 두 번째 연결리스트의 노드의 다음 노드를 첫번 째 연결리스트의 노드로
- 첫 번째 연결리스트의 노드로 이동
- ... 반복
- 둘 중 하나라도 먼저 끝나면 끝임
    - 길이 달라도 괜찮네용

- 새로운 루트 반환
    - 첫번째 연결리스트가 빈 연결리스트인 경우 두번째 연결리스트의 노드 반환

## Stack 클래스 구현하기

### 생성자를 구현한다

- 연결리스트의 루트 노드 멤버 변수로 저장
    - rootOrNull
- 스택의 크기 멤버 변수로 저장

### push() 메서드를 구현한다

- 시간복잡도 O(1)

- 연결리스트 prepend 호출
    - 매개변수로 rootOrNull 넘기기
- 스택 크기 늘리기

### peek() 메서드를 구현한다

- 시간복잡도 O(1)
- rootOrNull 데이터 반환

### pop() 메서드를 구현한다

- 시간복잡도 O(1)
- rootOrNull 에서 데이터 조회해서 저장
- rootOrNull 의 다음 노드 참조를 임시 변수로 저장
- rootOrNull 연결 끊기
- rootOrNull에 다음 노드 참조 저장

### getSize() 메서드를 구현한다

- 시간복잡도 O(1)
- 스택의 크기 멤버 변수를 반환

## Queue 클래스 구현하기

### 생성자를 구현한다

- 처음 노드에 대한 참조 멤버 변수로 저장
    - front
- 마지막 노드에 대한 참조 멤버 변수로 저장
    - rear
- 큐의 크기 멤버 변수로 저장

### enqueue() 메서드를 구현한다

- 시간 복잡도 O(1)
- rear 노드 뒤에 새로운 노드 추가
- rear 노드 참조 변수 변경
- 처음으로 넣는 경우 front와 rear 일치시키기
    - size == 0
- 큐의 크기 증가

### peek() 메서드를 구현한다

- 시간 복잡도 O(1)
- front 데이터 반환

### dequeue() 메서드를 구현한다

- 시간 복잡도 O(1)
- front에서 데이터 조회해서 저장
- 새로운 front 참조 임시 변수에 저장
- old front 노드 연결 끊기
- front 참조 멤버 변수 교체
- 큐가 비게되는 경우 rear 참조 변수도 null 처리
    - size == 1
- 큐의 크기 감소

### getSize() 메서드를 구현한다

- 시간 복잡도 O(1)
- 큐의 크기 반환
