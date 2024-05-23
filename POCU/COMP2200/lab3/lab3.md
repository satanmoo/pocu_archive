# lab3

## 공통

- 소행성 클러스터의 집합 -> CAB
- CAB시작 위치(16진수), 길이
- 소행성 클러스터 개수
- 소행성 클러스터의 시작위치(16진수), 길이
- 안전지대: 겹치는 소행성 클러스터 
  - 1분에 5바이트 항해
- 비안전지대: 홀수개의 소행성 클러스터
  - 1분에 10바이트 항해
- 목표: 
  - 1. 안전 지대 중 가장 긴 것의 길이
  - 2. CAB 전체 통과시간
- stdlib.h 헤더 파일 사용 불가
- 전역변수(global)/정적변수(static) 사용 금지

## const char* get_longest_safe_zone_or_null(const char* const cab_start_location, const size_t cab_length, const char* const cluster_start_locations[], const size_t cluster_lengths[], const size_t cluster_count, size_t* out_longest_safe_area_length);

- 반환값: 이 함수는 가장 긴 안전지역의 시작 주소를 반환합니다. 가장 긴 안전지역이 여러 개일 때, 가장 마지막 것을 기준으로 합니다.
  - 반환값2: 가장 긴 안전 지역의 길이를 out_longest_safe_area_length로 반환한다. 즉 마지막 매개변수로 받은 주소값에 값을 넣어서 반환한다. 함수의 반환값은 1개라서 이렇게 구현함
  - 이 함수의 목적은 안전 지대를 구하는 것, 안전 지대의 개념: 안전 지대의 시작 주소(반환값 const char*) + 안전 지대의 길이(size_t)
- edge case: cab_length의 값이 0이면 NULL을 반환하고 out_longest_safe_area_length를 0으로 설정합니다.
- cluster_count가 0일 경우, cluster_start_locations와 cluster_lengths의 값으로는 NULL이 전달됩니다.

### 구현

- char* cyber_asteroid_belt는 시작 주소임, 원소의 개수도 알고 있어서 for문으로 순회할 수 있다.
- const cab_start_location, cab_length을 이용해서 순회가능하다.
- 안전 지대 길이를 구하는 변수 선언
- 클러스터 구간의 개수를 세는 변수 선언
- 순회할 때 원소보다는 주소에 집중해서, 주소의 대소를 비교할 수 있다.
- 주소값을 얻어서 cluster_start_addresses의 원소 + cluster_lengths의 원소를 조합해 범위에 속하는지 확인할 수 있다.
- 범위에 속하면, 클러스터 구간의 개수를 증가시킨다.
  - 여기서 범위: [시작주소, 시작주소 + 길이)
  - 인덱스랑 똑같이 생각해야함. 시작주소가 0이고 길이가 15다? 시작 주소는 포함하고, 끝 주소는 0 + 15 - 1
- 클러스터 구간이 짝수인지 홀수 인지에 따라서 안전지대를 결정함
- 짝수의 시작 주소 저장함
- 안전지대가 연속으로 나오면 안전지대 길이 변수 증가
  - 연속으로 나오는지를 어떻게 판단하나요?: 어떤 flag 변수가 필요하다.
  - 짝수 -> 홀수: flag 끄면 됨, current 안전지대 길이는 누적합된 상태, current 안전 지대 길이와 goat 길이 비교해서 이상이면 goat 길이 갱신하고, res 갱신
  - 홀수 -> 짝수: flag 키면 됨, current 안전지대 길이 1으로 초기화, 시작 주소 갱신
  - 짝수 -> 짝수: flag 켜진 상태, current 안전지대 길이 증가
  - 홀수 -> 홀수: 아무 행동 X
- 현재 상태: 짝수의 시작 주소, 안전지대 길이
- 홀수 만나면 그냥 지나감
- 짝수 다시 만나면 임시로 짝수의 시작 주소 저장, 임시로 안전지대 길이 저장
- 홀수 만나서 길이를 넘어서는지 확임(같거나 큼: 가장 긴 안전지역이 여러 개일 때, 가장 마지막 것을 기준으로 합니다.)

### 사용 개념

- 포인터의 정의: 주소값
- 포인터의 산술 연산 더하기: 자료형에 따라 offset을 더함
  - char*에 1을 더하면 1바이트가 증가함
  - int*에 1을 더하면 4바이트가 증가함
- 매개변수로 포인터를 받아, 이 포인터를 역참조해서 값을 대입하면 함수의 반환값이 여러개인 것 과 같은 효과가 발생한다.
- 앞에서 부터 순회 할 때 제일 끝에 도달했을 경우 처리가 필요함
  - 지금 논리상 원소를 순회하면서 만나면 트리거가 되는데, 마지막 원소 다음에 무조건 안전/비안전 구간이 끝남

## int get_travel_time(const char* const cab_start_location, const size_t cab_length, const char* const cluster_start_locations[], const size_t cluster_lengths[], const size_t cluster_count);

- 사이버 소행성대(CAB)를 항해하는데 걸리는 시간(분)을 반환합니다. 반환할 때 그 결과를 가장 가까운 정수로 반올림해주세요.
- 계산을 할 때 double을 사용하고, 반환하기 바로 직전에만 정수로 반올림합니다.
- edge case: cluster_count가 0일 경우, cluster_start_locations와 cluster_lengths의 값으로는 NULL이 전달됩니다.

### 구현

- char* cyber_asteroid_belt는 시작 주소임, 원소의 개수도 알고 있어서 for문으로 순회할 수 있다.
- const cab_start_location, cab_length을 이용해서 순회가능하다.
- 안전 지대 길이를 구하는 변수 선언
- 클러스터 구간의 개수를 세는 변수 선언
- 순회할 때 원소보다는 주소에 집중해서, 주소의 대소를 비교할 수 있다.
- 주소값을 얻어서 cluster_start_addresses의 원소 + cluster_lengths의 원소를 조합해 범위에 속하는지 확인할 수 있다.
- 범위에 속하면, 클러스터 구간의 개수를 증가시킨다.
  - 여기서 범위: [시작주소, 시작주소 + 길이)
  - 인덱스랑 똑같이 생각해야함. 시작주소가 0이고 길이가 15다? 시작 주소는 포함하고, 끝 주소는 0 + 15 - 1
- 클러스터 구간이 짝수인지 홀수 인지에 따라서 안전지대를 결정함
- 안전지대가 연속으로 나오면, 안전지대 길이를 측정해서 속력으로 나눈다. (double로 형변환)
  - 위 구현할 때와 같은 논리, 이번에는 시작 주소는 필요없죠?
~~- 안전지대/비안전지대가 연속으로 나오는지 판단~~
  ~~- 홀 -> 홀: flag off상태 유지, 길이 증가~~
  ~~- 짝 -> 짝: flag on상태 유지, 길이 증가~~
  ~~- 홀 -> 짝: flag on, 길이 1로 초기화, 속력으로 나눠서 시간 구하고 누적하기~~
  ~~- 짝 -> 홀: flag off, 길이 1로 초기화, 속력으로 나눠서 시간 구하고 누적하기~~~~~~
- 연속 여부도 판단할 필요가 없음, 그냥 호
- 누적된 시간을 가장 가까운 정수로 반올림

### 개념

- 가장 가까운 정수로 반올림: + 0.5하고 int로 형변환해서 절삭하기
  - C#과 마찬가지로 정수로 형변환하면 절삭된다.