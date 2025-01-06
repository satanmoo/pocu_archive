# Assignment 1

## Overview

- 통계 기록 프로그램:
    - 가공되지 않은 데이터를 처리해 통계를 출력

## processGameStats() 메서드를 구현한다

```java
public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
}
```

- 목적:
    - GameStat 배열을 집계해서 Player 상태 초기화

- 제한:
    - 한 경기 기록에서 동일한 선수의 데이터가 여러 번 등장하는 경우는 없다고 가정
        - 경기를 기준으로 집계하는게 좋아보임
    - outPlayers의 요소는 모두 null이 아님 따라서 모든 선수가 어떤 경기에 최소 한 번은 출전함
    - outPlayers의 순서는 상관없음

- 알고리즘:
    - GameStat 배열 정렬
        - 이름 순
        - O(g log g) 시간 복잡도
    - 배열의 요소(GameStat) 순회
    - 동일한 이름을 가진 GameStat 구역 시작 인덱스, 끝 인덱스 구함
        - 집계 함수에 매개변수로 넘기기 위함
    - 집계 함수 호출
        - 구간에 대한 집계

```pseudo
for each GameStat in sortedGameStats:
    if (current PlayerName == previous playerName:
        continue to next stat
    else:
        start_new_group_at_current_index
        update_end_index_when_group_ends
        
        
for each group of GameStats for player:
    aggregate statistics (points, assists, passes, shotsMade, shotsAttempted)
    calculate avgPoints, avgAssists, avgPasses, shootingPercentage
```

### GamsStat 정렬 함수

- 

### 집계 함수
