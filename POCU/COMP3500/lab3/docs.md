# Lab3

## Runtime Optimal Test

- B06_RuntimeOptimal
- C09_RuntimeOptimal

- findMaxAltitudeTime 함수 O(N) 시간복잡도로 구현했을 때 실패
    - O(log N) 로 변경

## 정렬된 데이터에서 최대값 찾기

- 인풋이 특수함
    - 단조 증가
    - 단조 감소
    - 극대값 오직 1개 (산 모양)
- 이분탐색 응용

- 중간 인덱스 구하기
    - mid
- [mid, mid + 1]이 증가 구간이면?
    - [mid + 1, end] 단조 증가
    - 극대값을 만나기 전
        - [mid + 1, end] 탐색
        - 단조 증가가 확실하지 않기 때문에, 이분 탐색으로 구간을 줄여나가기
        - 두 경우 모두 [mid + 1, end] 구간에 최대값이 있는 것은 확실함!
- [mid - 1, mid]이 감소 구간이면?
    - [start, mid - 1] 단조 감소
    - 극대값을 만난 이후
        - [start, mid - 1] 탐색
        - 단조 감소가 확실하지 않기 때문에, 이분 탐색으로 구간을 줄여나가기
            - 단조 감소가 확실하면 start 위치가 최대값
        - 두 경우 [start, mid - 1] 구간에 최대값 있는 것은 확실함!
- [mid - 1, mid] 증가, [mid, mid + 1] 감소
    - 어?
    - mid에 극대값(최대값)

- start == 0, end == 1 일 때 edge case
    - mid == 0, mid - 1 == -1 에서 문제 발생
    - 가장 왼쪽 구간에서는 적용 불가
- start == len - 2, end == len - 1
    - 가장 오른쪽 구간은 문제 없음
    - mid == len - 2, mid + 1 == len - 1
- mid 구할 때 버림을 활용하기 때문에 이런 현상이 발생함. start == 0 일 때 mid == 0 이 될 수 있음
