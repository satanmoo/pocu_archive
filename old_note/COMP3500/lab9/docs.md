# Lab9

## PyramidBuilder

- 피라미드의 가장 윗 레벨 바로 아래에서 고려할 점
    - 돌을 몇 개 사용했는가?
    - 사용한 돌 중 가장 큰돌의 너비?
- 너비의 오름차순으로 정렬
    - 높은 곳에 너비가 작은 돌이 많을 수록 더 높게 쌓을 수 있음
- 그리디로 가장 윗 레벨 바로 아래에는 동상 너비를 초과할 때까지 가장 작은 너비의 돌로 구성하기

## ProfitCalculator

- 복잡해 보이지만 단순함
- 직원이 능력에서 할 수 있는 최대 수익의 일을 고르기만 하면 됨
    - 일을 여러번 할 수 있기 때문에 그리디로 골라도 글로벌 최적임

- [most-profit-assignments](https://leetcode.com/problems/most-profit-assigning-work/description/)

## CodingMan

- 겹쳐도 상관없으니 연속되는 구간을 만들기
- 시작 시간의 오름차순으로 정렬
- 시작 시간이 0인 클립 중 종료시간이 가장 늦은 클립 찾고 결과 클립 목록에 추가
    - 시작 시간이 0인 클립은 이제 정렬된 배열에서 모두 제거
- 결과 클립 목록에서 가장 나중에 추가한 클립, 즉 가장 늦은 끝나는 시간보다 시작 시간이 이른 클립들 중에서 가장 끝나는 시간이 늦는 클립을 찾기
    - 즉 구간이 겹치는 클립 중 가장 늦게 끝나는 클립 찾기
    - 방금 찾은 클립들은 정렬된 배열에서 모두 제거
- 클립 목록에 추가하고 반복

- 코드 구현 보면 암

- interval covering
- [video-stitching](https://leetcode.com/problems/video-stitching/description/)
