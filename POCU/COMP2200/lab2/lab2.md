# lab2

## assertion

- 'element_count' != 배열의 길이
    - 'element_count' == 호출자가 사용하는 요소의 개수
    - 배열에서 사용되지 않는 공간을 '슬롯'이라고 정의, 슬롯의 값은 INT_MIN
- 호출자가 언제나 유효한(valid) `element_count`를 전달한다고 가정

## int get_index_of(const int numbers[], const size_t element_count, const int num);

- 처음 등장하는 위치의 색인을 반환
- 시간 복잡도 O(N)

## int get_last_index_of(const int numbers[], const size_t element_count, const int num);

- 뒤에서 부터 검색
- 시간 복잡도 O(N)
- 인덱스 언더플로우 주의

## int get_max_index(const int numbers[], const size_t element_count);

- 최대값이 여러 개인 경우, 가장 먼저 등장하는 인덱스를 반환
    - 앞에서 부터 검색
- 시간 복잡도 O(N)

## int get_min_index(const int numbers[], const size_t element_count);

- 위와 동일

## int is_all_positive(const int numbers[], const size_t element_count);

- 사용 중인 배열의 공간 모두 순회
    - 방향은 상관없음
- 시간 복잡도 O(N)

## int has_even(const int numbers[], const size_t element_count);

- 사용 중인 배열의 공간 모두 순회
    - 방향은 상관없음
- 시간 복잡도 O(N)

## int insert(int numbers[], const size_t element_count, const int num, const size_t pos);

- 원소 밀기
- pos가 유효한지 확인
    - 유효 범위:  0 <= pos <= element_count
- 배열이 가득 찼을 때(슬롯이 없는 경우) 함수를 호출하는 일 없다고 가정
- 시간 복잡도 O(element_count - pos)

## int remove_at(int numbers[], const size_t element_count, const size_t index);

- 원소 당기기
- pos가 유효한지 확인
    - 유효 범위:  0 <= pos < element_count
- 배열이 비어 있을 때 함수를 호출하는 일 없다고 가정
- 시간 복잡도 O(element_count - index)
