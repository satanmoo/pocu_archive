# lab3

## const char* get_longest_safe_zone_or_null(const char* const cab_start_location, const size_t cab_length, const char* const cluster_start_locations[], const size_t cluster_lengths[], const size_t cluster_count, size_t* out_longest_safe_area_length);

- 반환값: 이 함수는 가장 긴 안전지역의 시작 주소를 반환합니다. 가장 긴 안전지역이 여러 개일 때, 가장 마지막 것을 기준으로 합니다.
- 반환값2: 가장 긴 안전 지역의 길이를 out_longest_safe_area_length로 반환한다. 즉 마지막 매개변수로 받은 주소값에 값을 넣어서 반환한다. 함수의 반환값은 1개일 수밖에 없다.
  - 3주차 강의에서 언급한 포인터의 용도: 함수의 반환값이 여러 개일 때
  - 코딩 표준에서 변수명에 out 붙임 -> out_longest_safe_area_length
- 안전 지대의 길이 개념: 안전 지대의 시작 주소(반환값 const char*) + 안전 지대의 길이(size_t).
  - 이 때 단위가 바이트
  - 3주차 강의에서 언급한 char*로 딱 1바이트 이동하는 개념

### 구현

- 전체 CAB를 순회하면서 각 주소를 확인할 수 있음
  - char* cyber_asteroid_belt는 시작 주소임, 원소의 개수도 알고 있어서 for문으로 순회할 수 있다.
- CAB의 각 주소를 순회하면서 안전지대인지 확인할 수 있음 
  - cluster_start_addresses의 원소에서 클러스터의 시작 위치를 알 수 있음
  - cluster_lengths로 각 클러스터의 길이를 알 수 있음
- 각 주소에서 클러스터 구간의 개수를 구할 수 있음
  - 클러스터 범위: [시작주소, 시작주소 + 길이)
  - 범위 구할 때 주의할점: 인덱스랑 똑같이 생각해야함. 시작주소가 0이고 길이가 15다? 시작 주소는 포함하고, 끝 주소는 0 + 15 - 1
- 각 주소에서 클러스터 구간이 짝수인지 홀수 인지에 따라서 안전지대를 결정
- 안전 지대가 연속되면 안전지대 길이를 측정
  - 연속으로 나오는지를 어떻게 판단하나요?: 어떤 flag 변수가 필요하다.
  - 짝수 -> 홀수: flag 끄면 됨, current 안전지대 길이는 누적합된 상태, current 안전 지대 길이와 goat 길이 비교해서 이상이면 goat 길이 갱신
  - 홀수 -> 짝수: flag 키면 됨, current 안전지대 길이 1으로 초기화, 시작 주소 갱신
  - 짝수 -> 짝수: flag 켜진 상태, current 안전지대 길이 증가
  - 홀수 -> 홀수: 아무 행동 X

### 사용 개념

- 포인터의 정의: 주소값
- 포인터의 산술 연산 더하기: 자료형에 따라 offset을 더함
  - char*에 1을 더하면 1바이트가 증가함
  - int*에 1을 더하면 4바이트가 증가함
- 매개변수로 포인터를 받아, 이 포인터를 역참조해서 값을 대입하면 함수의 반환값이 여러개인 것 과 같은 효과가 발생한다.
- 앞에서 부터 순회 할 때 제일 끝에 도달했을 경우 처리가 필요함
  - 지금 논리상 원소를 순회하면서 만나면 트리거가 되는데, 마지막 원소 다음에 무조건 안전/비안전 구간이 끝남
- 수업에서 코드 보기: 두 배열이 겹치는가에서 나온 개념이네용

## int get_travel_time(const char* const cab_start_location, const size_t cab_length, const char* const cluster_start_locations[], const size_t cluster_lengths[], const size_t cluster_count);

- 1번에서 추가된 개념이 없음

### 구현

- CAB 순회
- 안전지대 여부 검사
- 안전지대, 비 안전지대 길이 구해서 시간 구하기
  - 연산할 때 double 정확도 이슈를 고려
  - 다 더해서 한 번에 double로 계산하는게 더 정확하다.

### 개념

- 정수 연산을 먼저하고, 나중에 부동 소수점 연산을 하는 것이 값이 더 정확함
  - 매번 부동 소수점 연산으로 나누고, 각 결과를 더하기는 비추
  - 일단 더하고, 더한 결과를 부동 소수점 연산으로 한 번에 나누기
- 가장 가까운 정수로 반올림: + 0.5하고 int로 형변환해서 절삭하기
  - C#과 마찬가지로 정수로 형변환하면 절삭된다.