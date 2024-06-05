# lab5

## int print_receipt(const char* filename, time_t timestamp);

### 구현

- fopen, w모드로 파일 열기, 파일 스트림 반환

- 주문한 음식이 없는 경우 예외처리
  - item_count가 0인 경우

- 고정 문자열값 출력
  - fputs(str, stream) 사용
  - [fputs](https://en.cppreference.com/w/c/io/fputs)는 개행 문자 안 붙여줌
  - 1줄, 2줄.. 등등

[주문 번호 줄]
- timestamp를 UTC 0으로 포메팅
  - "2019-08-24 22:44:41" 이렇게 출력(왼쪽 정렬)
  - char[20] 선언하고, strftime으로 포메팅해서 저장
  - 19자리 + 널문자
- 주문 번호를 5자리 + 오른쪽 정렬해서 출력
  - 주문 번호는 static 변수로, 출력 성공 시 1씩 증가
- 두 문자열을 합치기
  - 두 문자열 사이에 공백 25자리 출력
  - printf("%25s", "") 로 출력

[음식줄]
- 음식 이름 오른쪽 정렬해서 출력 "%33s", 공백 포함 최소 33자 보장
  - 이 때 문자열의 최대 크기는 25자
  -  item_s의 원소 item_t에서 strncpy으로 복사
- 가격은 오른쪽 정렬해서 출력 "%17.2f", 소수점 2자리까지 출력, 최소 너비17, 최소 너비보다 작으면 공백으로 채움
- fpirntf("%33s%17.2f\n", name_buffer, item_list[i].price);
  - 문자열을 만들고, 문자열을 printf로 포메팅할 수 있음

[총 주문 금액, Tip, Tax, Total]
- 고정 문자열은 "%33s"로 오른쪽 정렬해서 출력
- 숫자 값은 %17.2f", 소수점 2자리까지 출력, 최소 너비17

[메시지] 
- 메시지에 최대 75자까지 미리 저장해야함
  - char[75 + 1] 선언하고 strncpy으로 75자 복사 후 널문자 추가 
- 한번 출력할 때 메시지에서 50자까지 출력할 수 있음
  - char [50]으로 line_buffer 선언하고, strncpy으로 50자 복사 후 널문자 추가
    - 50자 이상이면 50자까지 출력하고, strcpy로 50자 이후를 line_buffer에 덮어쓰고 출력
    - 50자 이하이면 그대로 출력

[초기화]
- 출력 후 초기 상태로 초기화

### 개념

- fprintf: 파일에 출력하기
- 두 문자열을 합쳐서 저장하기
  - 버퍼에 각각 문자열을 저장하고, printf에서 포메팅해서 저장
- 버퍼로 문자열을 최대 길이 만큼 저장하고, 그 문자열을 다시 출력 포멧에 사용하기
- time_t, time() 함수 사용 방법
  - time(NULL) : 현재 시간을 반환
  - [gmtime](https://en.cppreference.com/w/c/chrono/gmtime)

## int add_item(const char* name, double price);

### 구현

- item_list에 음식 이름과 가격을 추가
  - 최대 10개라 크기 10개로 선언
  - 음식 이름은 깊은 복사 char[25]로 선언하고, strncpy으로 복사
  - strncpy의 best practice를 따르자
    - name의 길이가 25보다 크면 25까지만 복사하고, 널문자 추가, strncpy(name_buff, name, 25)
    - name의 길이가 25보다 작으면 name의 길이까지 복사하고, 나머지 공간은 널문자로 채움, strncpy(name_buff, name, name의 길이)
- item은 구조체로 선언
- 구조체 배열은 static 전역 변수로 선언, 구조체 배열의 개수(음식의 개수)를 나타내는 static 변수 선언
  - 인쇄 하면 초기화

### 개념

- 구조체
- 얕은 복사 vs 깊은 복사
- 전역 변수

## void set_tip(double tip);

### 구현

- static 전역 변수 tip에 tip을 set

## void set_message(const char* message);

### 구현

- 메시지 길이가 75자이기 때문에 char [76]로 선언
- strncpy으로 복사
  - strncpy은 널문자를 복사하지 않음 따라서 마지막에 널문자 추가하기
  - case1: 인자로 입력받은 메시지가 75자보다 긴 경우, 75자까지 복사하고, 마지막에 널문자 추가
  - case2: 인자로 입력받은 메시지가 75보다 짧은 경우, src의 길이가 count(75)보다 작기 때문에 나머지 공간 널문자로 채워짐

### 개념

- strncpy 동작 원리
