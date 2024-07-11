# lab8

## size_t find_matching_parentheses(ringbuffer_t* ringbuffer, const char* str);

### 구현

- 동적 배열 정의
  - 배열의 원소: {문자, 문자의 위치}
  - 배열의 크기: 입력받은 문자열의 길이
    - 모두 괄호로 이루어졌으면, 배열의 크기와 문자열의 길이  가 동일함
    - 배열의 크기의 최대값은 문자열의 길이

- 문자열 순회
  - 열린 괄호를 만나는 경우
    - 배열에 넣음
  - 괄호가 아닌 문자를 만나는 경우
    - 배열에 넣음
  - 닫는 괄호를 만나는 경우
    - 배열의 맨 뒤부터(rear) 처음까지 확인해서, 짝이 맞으면 링 버퍼에 저장
      - 짝이 맞는 원소 제거
    - 짝이 맞지 않으면, 스택에 넣음

- 링 버퍼
  - 닫는 괄호의 인덱스 오름차순 정렬
    - 순회하면서 닫는 괄호를 만나는 경우 스택의 원소와 비교하기 때문에 만족됨
  - max_size를 초과하면 closing_index가 작은 괄호를 무시
    - 링 버퍼 알고리즘 때문에 자동으로 무시 됨
    - 버퍼가 꽉 찰 경우 dequeue하고, enqueue
    - dequeue
      - q_front = (q_front + 1) % max_size
        - q_front 순회하도록 증가
      - q_size--
        - q_size 감소
    - enqueue
      - q_back 인덱스에 원소 저장
      - q_back = (q_back + 1) % max_size
        - q_back 순회하도록 증가
      - q_size++
        - q_size 증가

- 반환값
  - 링버퍼에서 enqueue 호출 횟 수
  - 링버퍼의 크기보다 클 수 있음

### 개념

- 링 버퍼 알고리즘
  - 버퍼가 꽉 찰 경우
    - dequeue하고, enqueue 하기

- 동적 배열의 활용
  - 중간의 요소를 삭제하는 경우
  - 삭제하고 뒤의 요소들을 앞으로 당겨야함
