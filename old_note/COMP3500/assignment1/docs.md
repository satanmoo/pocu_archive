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
```

### GamsStat 정렬 함수

- 퀵소트 구현
    - selector: 이름
- why 퀵소트?:
    - 시간 복잡도
    - 안정성이 문제가 되지 않음
        - 하나의 플레이어에 대해서 집계하기 때문에 동일한 이름을 가진 플레이어가 모여있기만 하면 됨

### 집계 함수

```pseudo

for GameStat:
    sum point
    sum assist
    sum pass
    sum goal
    sum goalAttempts

calculate avg, shootingPercentage
assign the calculated statistics to the output array
```

## findPlayerPointsPerGame() 메서드를 구현한다

- 이진 탐색 활용
    - 주어진 값과 가장 가까운 값 탐색하기
    - 인풋 배열이 오름차순으로 정렬 >> 이진 탐색 힌트
    - 변수에 차이값 기록

```pseudo
Function findPlayerPointsPerGame(players, targetPoints):
    left = 0
    right = players.size - 1
    closestPlayerIndex = INT.MIN

    While left <= right:
        mid = left + (right - left) / 2
        currentPlayerPointerPerGame = players[mid].pointerPerGame
        currentDiff = get abs(currentPlayerPointerPerGaem, targetPoints)
        
        if closestPlayerIndex == INT.MIN || currentDiff < abs(players[closestPlayerIndex].pointerPerGame, targetPoints)
        || (currentDiff == abs(players[closestPlayerIndex].pointerPerGame, targetPoints) && players[closestPlayerIndex].pointerPerGame < players[mid].pointerPerGame) 
            closestPlayerIndex = mid
        
        if currentPlayerPointerPerGame < targetPoints:
            left = mid + 1
        else if currentPlayerPointerPerGame > targetPoints:
            right = mid - 1
        else:
            return mid  // Exact match found

    return players[closestPlayerIndex]  // Return the player with the closest to target
```

## findPlayerShootingPercentage() 메서드를 구현한다

- 이진 탐색 활용
    - findPlayerPointsPerGame()와 동일

```pseudo
Function findPlayerShootingPercentage(players, targetShootingPercentage):
    left = 0
    right = players.size - 1
    closestPlayerIndex = INT.MIN

    While left <= right:
        mid = left + (right - left) / 2
        currentSP = players[mid].shootingPerGame
        curDiff = get abs(currentSP, targetPoints)
        
        if closestPlayerIndex == INT.MIN || curDiff < abs(players[closestPlayerIndex].shootingPerGame, targetShootingPercentage)
        || (curDiff == abs(players[closestPlayerIndex].shootingPerGame, targetShootingPercentage) && players[closestPlayerIndex].shootingPerGame < players[mid].shootingPerGame): 
            closestPlayerIndex = mid
        
        if currentSP < targetPoints:
            left = mid + 1
        else if currentSP > targetPoints:
            right = mid - 1
        else:
            return mid  // Exact match found

    return players[closestPlayerIndex]  // Return the player with the closest to target
```

## find3ManDreamTeam() 메서드를 구현한다

- 조합
    - 모든 조합에 대해 팀워크 값 구하고, 최대 팀워크 값 반환
    - 모든 조합 구하면 시간복잡도 통과 못함
        - F03_RuntimeOptimal

## findDreamTeam() 메서드를 구현한다

- 위 함수의 확장
- 마찬가지로 조합으로 구현하면 시간복잡도 테스트 통과 못함
    - code too slow

- 정렬 + 슬라이딩 윈도우
    - 정렬 시간복잡도:
        - O(n log n)
    - 슬라이딩 윈도우 + 탐색 시간복잡도:
        - O (n * k)
- 팀워크 = [팀에 속한 모든 선수의 경기당 패스수를 합한 결과] * [팀에 속한 각 선수의 경기당 어시스트수 중 최솟값]
    - 데이터의 성질을 응용

- 방법 1
- 정렬할 때 어시스트 수의 내림차순으로 정렬
    - (a, b, c) 에서 c의 어시스트 수가 가장 작음 따라서 이 조합의 [팀에 속한 각 선수의 경기당 어시스트수 중 최솟값]는 c의 어시스트 수 중 최소값
    - 이렇게 정렬해서 앞에서 k개 뽑으면 [팀에 속한 각 선수의 경기당 어시스트수 중 최솟값] 의 최대값을 구할 수 있음
- [팀에 속한 모든 선수의 경기당 패스수를 합한 결과]의 최대값을 만들 수 있도록 교체하면서 확인해야함
    - 가장 패스수가 낮은 선수를 조합에서 제거
    - 조합에 추가할 선수는 나머지 안 뽑힌 선수들 중에서 어시스트 수가 가장 높은 선수
        - 정렬 때문에 바로 찾을 수 있음
        - 교체할 때 [팀에 속한 각 선수의 경기당 어시스트수 중 최솟값]은 최대한 적게 감소하는 쪽으로 교체
        - 그리디
- 방법2
- 정렬할 때 패스수의 내림차순으로 정렬
    - 앞에서 부터 k 개 뽑으면 [팀에 속한 모든 선수의 경기당 패스수를 합한 결과]의 최대값을 구할 수 있음
- 슬라이딩 윈도우(팀)에서 가장 어시스트 수가 낮은 선수를 제거
- 다음으로 패스수가 많은 선수 추가

- 일반화하면 하나의 기준으로 조합을 구성하고, 다른 기준에 따라 선수 교체

## findDreamTeamSize() 메서드를 구현한다

- findDreamTeam() 메서드 완전탐색
    - 이거 하면 시간초과인데?
- 휴리스틱 풀이
    - 두 요소 중 하나를 우선으로 고려하는 방식
    - 시간 복잡도:
        - 정렬 비용:
            - O(n long n)
        - 순회 비용:
            - O(n)
- 방법1:
    - 어시스트 수 내림차순으로 정렬
        - 어시스트 수가 0이 되면 팀워크 값 0임
        - 극단값은 어시스트 수에서 발생
    - 팀 사이즈를 늘려가면서 팀워크 계산
    - 정렬했기 때문에 [팀에 속한 각 선수의 경기당 어시스트수 중 최솟값] 을 최대한 크게 뽑으면서 구성
- 방법2:
    - 패스 수 내림차순으로 정렬
    - 팀 사이즈를 늘려가면서 팀워크 계산
    - 팀 인원 수 늘릴 때 어시스트 수를 변수에 저장하고 갱신하는 식으로

- 이 두 방법 모두 사용해서 최적 구하기
    - 두 방법 각각 사용하고 그 두개 결과 비교하기
    - Commit cf63254

## ETC

### 입력에 조심

```java
private static long calculateTeamWork(final Player[] players, final int teamSize) {
    // players.length != teamSize
    if (players[0] == null) {
        return Long.MIN_VALUE;
    }

    long passSum = 0L;
    int minAssist = Integer.MAX_VALUE;

    for (int i = 0; i < teamSize; ++i) {
        Player p = players[i];
        passSum += p.getPassesPerGame();
        if (minAssist > p.getAssistsPerGame()) {
            minAssist = p.getAssistsPerGame();
        }
    }
    return passSum * minAssist;
}
```

- players 배열의 크기와 teamSize가 다를 수 있음
- findDreamTeamSize 함수의 매개변수로 Player[] scratch 최대 크기로 들어오기 때문임.
    - [p, p2, p3, null, null]; 이렇게 들어올 수 있음

### 정렬은 최대한 적게 하자

- commit-f569631
    - 정렬을 getMaxTeamWork 함수 호출할 때 마다 하는게 아니라, 호출자에서 미리 정렬하고 호출해서 정렬 횟수를 줄임
