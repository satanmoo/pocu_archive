# Lab10

## 자료구조

- 링크드 리스트

```c++
typedef struct todo_list {
    todo_node_t* head;
    size_t capacity;
    size_t count;
} todo_list_t;
```

```c++
typedef struct todo_node {
    const int32_t priority;
    const char task[1024];
    todo_node_t* next;
} todo_node_t;
```

## todo_list_t init_todo_list(size_t max_size);

- 매개변수
    - max_size: TODO 목록의 최대 크기
- 역할
    - 새 todo_list_t 구조체를 만들고, 초기화한 뒤, 반환

### 구현

- 스택에 todo_list_t 할당
- 멤버 초기화
    - 멤버 head: NULL;
    - 멤버 capacity: 입력받은 max_size 값;
    - 멤버 count: 0
- 값형으로 반환

## void finalize_todo_list(todo_list_t* todo_list);

- 매개변수
    - todo_list_t 구조체의 주소

- 역할
    - todo_list가 할당한 모든 메모리를 지움
    - `모든`에서 얻을 수 있는 힌트: todo_list 구조체의 멤버 중 힙에 메모리를 할당한 멤버가 존재할 수 있음

### 구현

- todo_list_t의 멤버 연결 리스트 할당 해제
    - 연결 리스트의 모든 노드 할당 해제
    - 각 노드의 문자열(task) 할당 해제

## bool add_todo(todo_list_t* todo_list, const int32_t priority, const char* task);

- 매개변수
    - todo_list_t 구조체의 주소
        - 구조체의 멤버에 리디랙션으로 접근할 수 있음
    - priority: 업무의 우선순위
    - task: 업무의 이름
        - `멤버로 업무의 이름, 업무의 우선순위를 가진 구조체가 필요함`

- 동작
    - 투두 리스트가 최대 크기에 도달하면 add 실패하고, false 반환
    - add할 때 우선순위 내림차순으로 정렬이 유지되야함
    - 시간 복잡도 O(N) 이하

### 구현

- capacity == count 면 false 반환
- capacity > count 면 아래 로직 실행
    - todo_node 구조체 메모리 할당
        - 매개변수로 입력 받은 값을 복사해서 초기화
            - task: 문자열 깊은 복사
            - priority: 값 복사
    - todo_list의 주소로 리디렉션해서 멤버 연결 리스트에 접근
    - 연결 리스트를 순회
        - 각 노드 우선 순위와 입력된 우선 순위와 비교해 입력된 우선 순위가 크다면, 노드 앞에 삽입
    - count 증가
    - true 반환

## bool complete_todo(todo_list_t* todo_list);

- 매개변수
    - todo_list의 주소

- 동작
    - 가장 높은 우선순위를 가진 업무를 제거
    - 업무가 투두 리스트에 존재할 때만 제거할 수 있음
    - 업무가 투두 리스트에 존재하지 않다면, 즉 투두 리스트가 빈 경우 false 반환
    - 시간 복잡도 O(N) 이하

### 구현

- count가 0이면 false 반환
- count가 0이 아니라면 아래의 로직 실행
    - todo_list의 주소로 리디렉션해서 멤버 연결 리스트에 접근
    - head 노드 삭제
        - head 노드 메모리 해제
        - head 노드의 문자열 메모리 해제
    - head를 head->next로 교체
    - count 감소
    - true 반환

## const char* peek_or_null(const todo_list_t* todo_list);

- 매개변수
    - todo_list의 주소
    - const 키워드 때문에 todo_list_t 구조체의 내용을 변경하면 안 됨

- 동작
    - 가장 높은 우선순위를 가진 업무를 반환
    - 업무가 투두 리스트에 존재할 때만 반환할 수 있음
    - 업무가 투두 리스트에 존재하지 않다면, 즉 투두 리스트가 빈 경우 NULL을 반환
    - 시간 복잡도 O(N) 이하

### 구현

- count == 0 이라면 NULL 반환
- count > 0 이라면 아래 로직 실행
  - todo_list의 주소로 리디렉션해서 멤버 연결 리스트에 접근
  - 연결 리스트의 head에 접근, head의 멤버 문자열을 반환

## size_t get_count(const todo_list_t* todo_list);

- 매개변수
  - todo_list의 주소
  - const 키워드 때문에 todo_list_t 구조체의 내용을 변경하면 안 됨

- 동작
  - 현재 링크드 리스트의 원소 개수 반환

### 구현

- todo_list의 주소로 리디렉션해서 멤버 count의 값을 반환

## bool is_empty(const todo_list_t* todo_list);

- 매개변수
  - todo_list의 주소
  - const 키워드 때문에 todo_list_t 구조체의 내용을 변경하면 안 됨

- 동작
  - 현재 링크드 리스트가 비었는지 여부를 반환

### 구현

- todo_list의 주소로 리디렉션해서 멤버 count == 0 인지 반환

## ETC

- 프로그래머들이 툭하면 마감일을 지키지 않네요. 우선순위가 높은 일부터 처리해야 하는데 그냥 모든 일을 한꺼번에 진행해서 이런 문제가 생기는 듯합니다.
    - 기타 교훈 ㅋㅋ;