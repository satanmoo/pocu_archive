# lab6

## 공통 주의 사항

- 이 실습에서 계산을 할 때 소수점 이하는 무시하세요. 즉, 모든 결과는 무조건 내림을 합니다.

## int get_character(const char* filename, character_v3_t* out_character);

### [스펙 구분하기]

- 스펙의 고유한 특성
  - v1: ':', ':'가 문자열에 포함 됨 
  - v2: '|'가 문자열에 포함 되지 않음
  - v3: '|'가 문자열에 포함 됨

### [구조체 똥값 초기화]

### 구현

- memset

### 개념

- 구조체 초기화는 memset

### [스펙 1 읽기]

### 구현

- 개행문자 제거할 필요 없음
- 파일 읽기
  - 한 줄씩 읽기
- 토큰화 하기 전 개행문자 제거
- ','로 토큰화
- 토큰에서 ':'의 인덱스를 찾음, key, value 구하기
- key, value를 스펙 3에 맞게 mapping
  - id -> name
    - name: 캐릭터의 이름. 문자열 최대 50 글자이며 그 길이를 초과하는 부분은 무시함. 이는 버전 1의 id 속성을 대체. 버전 2로 변환시킨 버전 1 캐릭터들의 name 속성은 'player_<스펙 버전 1에서의 캐릭터 id>'이 됨. 플레이어는 로그인 후에 이 이름을 바꿀 수 있음. 영숫자와 _ 문자만 허용됨
  - lvl -> level
  - str -> strength
  - dex -> dexterity
  - intel -> intelligence
  - def -> armour
  - hp -> health
  - mp -> mana
  - evasion 추가
    - dexterity / 2
  - magic_resistance 추가
    - armour / 4
      - fire_res, cold_res, lightning_res 로 변환
        - magic_resistance / 3
  - minion_count 추가
    - 값 0
  - leadership 추가
    - level / 10
  

### 개념

- fopen 오류처리

- size_t fread(void*ptr, size_t size, size_t count, FILE *stream);
  - ptr: 읽어들인 데이터를 저장할 버퍼의 시작 주소
  - size: 읽어들일 데이터의 크기
  - count: 읽어들일 데이터의 개수
  - stream: 읽어들일 파일의 FILE 구조체 포인터
  - 성공 시 읽어들인 데이터의 개수를 반환, 실패 시 0을 반환
    - EOF를 만나면 파일 읽기 종료
    - EOF를 만나면 count보다 적은 수를 읽을 수 있음
    - 얼마나 읽을지 모르겠다? 버퍼를 크게, count를 크게 잡고 파일 읽기하면 됨, EOF 만나면 적게 읽도록 처리되니까!
  - 얼마를 읽을지 모르겠을 때 오류처리 하는 방법? [질문]
  - ❗️fread는 사용 안 해도 됨

- fgets(char* buffer, int count, FILE* stream)

- strtok

- 문자열 깊은 복사
  - strncpy
    - strncpy(dest, src, n)
      - src의 n개 문자를 dest에 복사
      - src의 n개 문자가 없으면 NULL로 채움
      - src의 n개 문자가 n보다 작으면 나머지는 NULL로 채움
      - src의 n개 문자가 n보다 크면 n개 문자만 복사


### [스펙 2 읽기]

### 구현

- 파일 읽기
- header 건너뛰기
- 토큰화 하기 전 개행문자 제거
- ","로 토큰화, line(body) 인덱스로 순회
- key-value 매핑
  - magic_resistance -> fire_res, cold_res, lightning_res 
    - magic_resistance / 3
  - minion_count 추가
    - 값 0
  - leadership 추가
    - level / 10 

### [스펙 3 읽기]

### 구현

- 파일 읽기
- header 건너뛰기
- 토큰화 하기 전 개행문자 제거
- "|"로 토큰화
- trim으로 공백 제거
  - 앞 공백: ' '아닐 때 까지 건너뛰기
  - 처음으로 나오는 공백 아닌 문자 주소 저장
  - 뒤 공백: '\0'으로 바꾸기
- key-value 매핑
- minion_count 값으로 토큰화 추가 진행 여부 결정
  - 0이면 추가 진행 안함
  - 0이 아니면 추가 진행
    - 3번째 줄: minion_header
    - 나머지줄: 순서대로 mapping

### [주의사항]

- 모든 작업 끝나고 파일 닫기

### 개념

- fclose