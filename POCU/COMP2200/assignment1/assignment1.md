# Assignment 1

## 공통

- 보드 크기의 최소, 최대가 존재한다.
  - 1. 메크로로 헤더 파일에 최소, 최대를 선언하자.
  - 2. 전역변수에 const 키워드를 붙여서 선언하자.
    - 전역 변수를 외부에서 사용하게 할 것인가? 아니면 static으로 선언해서 내부에서만 사용하게 할 것인가?
    - 요구사항을 봤을 때 내부에서만 사용하는게 옳다.

- 한 플레이어는 검은색 돌을, 다른 플레이어는 하얀색 돌을 사용합니다.
  - 돌의 색깔은 검은색, 하얀색 두 가지가 존재한다. 따라서 열거형으로 주어졌음
  - 흑은 0, 백은 1로 정의하자.
  - board는 정수형 배열 선언
    - 0: 흑, 1: 백, -1: 빈칸
  - 2차원 배열로 처음부터 최대 크기로 선언하고, 배열 안의 값을 사용 안 하면 되잖아?
    - 사용 안 하는 값은 -1로 정의 
    - 스택에 저장되는 방식, 선언 시 크기 고정 / 동적 할당 X
 
## init_game()

### 구현

- 보드는 초기 크기인 15 x 15로 초기화됩니다.
  - 2차원 배열을 사용하자.
  - 보드는 게임 실행 중 계속 상태를 유지해야함.
    - 지역 변수가 아님 
- 현재 보드의 크기를 나타내는 변수 s_row_size, s_col_size의 자료형은 모두 unsigned int
  - 주어진 함수 시그니처 get_row_count(), get_column_count()에서 반환값이 모두 unsigned int라서 그렇게 구현
- 모든 플레이어의 점수는 0으로 리셋
  - 점수 또한 게임 실행 중 계속 상태를 유지해야 한다.
  - static 변수로 선언하자.
  - 자료형은 int 
    - get_score() 함수의 반환 타입이 int라서
  - 최대 점수 넉넉함 

### 사용 개념

- 지역 변수와 전역 변수

## int get_score(const color_t color)

### 구현

- white, black, invalid에 따라서 반환하면 됨

### 사용 개념

- enum에서도 switch case를 사용할 수 있다.
- enum은 int에 별명을 지은 것 뿐이다.

## 2.6 get_color()

### 구현

- s_board의 인덱스로 값을 참조해서 반환하면 된다.

### 사용 개념
- 2차원 배열을 어떻게 초기화하지?
  - 2차원 배열도 1차원 배열과 똑같음

## 2.7 is_placeable()

- s_board에서 사용 중인 범위를 벗어나는지 확인
- 우리는 s_col, s_row의 크기를 알고 있잖아? 그러니까 그 범위를 넘어가면 안 된다.
- s_board의 값이 -1이 아니면 안 된다. 이미 돌이 있으면 못 놓기 때문이다.

## 2.8 place_stone()

- is_placeable을 활용해서 놓을 수 있는지 확인
- color 값 유효성 확인
- s_board에 돌을 놓는다.
- 점수를 갱신 [알고리즘]
- return TRUE;

## insert_row(), insert_column()

### 구현

- 삽입
- s_col_size, s_row_size를 증가


### 사용 개념

- 삽입할 때 값 밀어주기
- 인덱스의 자료형 오버플로우 방지

## remove_row() 함수를 구현한다, remove_column() 함수를 구현한다

### 구현

- 제거
- s_col_size, s_row_size를 감소시키기

### 사용 개념

- 제거하고 값 당겨주기

## swap_row(), swap_column()

### 사용 개념

- static 배열 속 원소를 swap
- pass by value가 아니라, pass by reference가 보장됨

## 알고리즘

- 점수 계산 알고리즘
- 가로
  - 놓은 돌을 기준으로 좌우로 연속해서 놓은 돌과 같은 색이 나오는지 탐색해서 최대 가로 길이 구함
- 세로
  - 놓은 돌을 기준으로 위아래로 연속해서 놓은 돌과 같은 색이 나오는지 탐색해서 최대 세로 길이 구함
- 대각선
  - 똑같음
- 역대각선
  - 똑같음

## 경험한 실수

```c++
for (i = s_row_size - 2; i >= row; --i) {
        for (j = 0; j < s_col_size; ++j) {
            s_board[i + 1][j] = s_board[i][j];
        }
    }
```

- row가 0 인 경우 문제점
- row, i가 size_t라서 --i경우 엄청 큰 수가 되어. 반복문을 다시 돌게 된다.
- 결국 반복문에서 사용하는 변수들을 모두 int로 바꾸자.

```c++
/* dst: s_board[s_row_size-1], src: s_board[s_row_size-2] ... */
    for (i = (int)s_row_size - 2; i >= (int)row; --i) {
        for (j = 0; j < s_col_size; ++j) {
            s_board[i + 1][j] = s_board[i][j];
        }
    }
```

- i >= (int)row로 바꾸자.
  - i >= row의 경우 int 와 unsigned int를 비교할 때 unsigned int로 비교하게 된다.


