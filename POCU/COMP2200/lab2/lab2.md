# lab2

- 요구 사항 분석

## 공통

- 'element_count' != 배열의 길이
  - 'element_count' == 호출자가 사용하는 요소의 개수
- 호출자가 언제나 유효한(valid) `element_count`를 전달한다고 가정하셔도 좋습니다.
- global 변수나 static 변수를 사용하지 않습니다.

## int get_index_of(const int numbers[], const size_t element_count, const int num); 구현

- 원소의 인덱스를 반환합니다.
- 처음 등장하는 위치의 색인을 반환

## int get_last_index_of(const int numbers[], const size_t element_count, const int num);

- 원소의 인덱스를 반환합니다.
- 가장 마지막에 등장하는 위치의 색인을 반환
- 뒤에서 부터 검색
  - 인덱스가 감소하기 때문에 인덱스의 자료형에 주의

## int get_max_index(const int numbers[], const size_t element_count);

- 최대값의 인덱스를 반환합니다.
- 초기값을 INT_MIN으로 설정(?)
- O(N) 시간 복잡도로 구현
- 최대값이 여러 개인 경우, 가장 먼저 등장하는 인덱스를 반환
  - 이건 조건 비교에서, 부등호 처리로 해결 가능
- element_count가 0이면 -1 반환

## int get_min_index(const int numbers[], const size_t element_count);

- 최소값의 인덱스를 반환합니다.
- O(N) 시간 복잡도로 구현
- 최소값이 여러 개인 경우, 가장 먼저 등장하는 인덱스를 반환
  - 이건 조건 비교에서, 부등호 처리로 해결 가능
- - element_count가 0이면 -1 반환

## int is_all_positive(const int numbers[], const size_t element_count);

- 모든 원소가 양수인지 확인합니다.
- O(N) 시간 복잡도로 구현
- element_count가 0이면 FALSE 반환

## int has_even(const int numbers[], const size_t element_count);

- 짝수가 있는지 확인합니다.
- O(N) 시간 복잡도로 구현

## int insert(int numbers[], const size_t element_count, const int num, const size_t pos);

- 원소 밀기
  - 부호없는 정수와 부호 있는 정수 비교할 때 언더플로우 조심
- 원소의 개수가 늘어나면서 빈 슬롯 채우기
  - INT_MIN 메크로 제거
- pos가 유효한지 확인
  - 유효 범위:  0 <= pos <= element_count
- 배열이 가득 찼을 때(INT_MIN이 없는 경우) 함수를 호출하는 일 없음

## int remove_at(int numbers[], const size_t element_count, const size_t index);

- 원소 삭제
- pos가 유효한지 확인
  - 유효 범위:  0 <= pos < element_count
- 배열이 비어 있을 때 함수를 호출하는 일 없음(?)

## 이번 실습에 고민해 보면 좋은 사안입니다.

- 미래의 나를 위해 동료를 위해 어떤 식으로 코드를 작성하는 게 좋을까요?
- 좋은 코드는 실수를 덜 유발하는 코드다.
