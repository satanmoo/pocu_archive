# Assignment3

## int load_document(const char* document)

### 구현

[파일에서 읽어오기]

- 파일 크기를 모를 때 읽어오기
    - fseek + ftell + rewind 을 사용하는 방법

[문서 만들기]

- 파일에서 읽어온 후 상태
    - heap에 동적 할당으로 파일의 문자들이 저장된 상태 (char* raw_data)
- raw_data를 가공하자
- 문서 변수 초기화
    - 문단의 수 세기 "\n"의 개수 + 1
        - '\n'과 다음 '\n'사이 글자수를 세서, 0이면 문단 수 카운트하면 안 됨
        - '\n'의 주소를 저장하고 char* 포인터 연산했을 때 1차이 나면, '\n'이 연속해서 등장한다는 것
    - 각 문단의 시작 주소를 동적 배열에 저장하자
    - 문단의 수 * char*** 동적 할당
- 각 문단 초기화
    - 각 문단의 시작 주소를 알아야함
    - '\n'을 만날 때 까지 raw_data를 순회
    - 문장 수 세기
    - '.', '!', '?'을 만나면 문장 수를 하나 증가, 구분자 사이 글자수가 0이면 문장 수 카운트하면 안 됨
    - 구분자의 주소를 저장하고 char* 포인터 연산했을 때 1차이 나면, 구분자 연속해서 등장한다는 것
    - 문장의 수 * char** 동적 할당
    - 첫 글자의 주소, 구분자 다음 글자의 주소가 char** 문장의 주소
- 각 문장 초기화
    - '.', '!', '?'을 만날 때 까지 raw_data를 순회
    - 단어 수 세기
    - ',', ' ' 만나면 단어 수 하나 증가, 구분자 사이 글자수가 0이면 단어 수 카운트하면 안 됨
    - 구분자의 주소를 저장하고 char* 포인터 연산했을 때 1차이 나면, 구분자 연속해서 등장한다는 것
    - 단어의 수 * char* 동적 할당
    - 첫 글자의 주소, 구분자 다음 글자의 주소가 char* 단어의 주소
- 각 단어 초기화
    - ',', ' '을 만날 때 까지 raw_data 순회
    - 구분자를 만나면 각 시작 주소에서 offset을 구하고, 문자열 복사
        - 길이 아니까, sizeof(길이) 만큼 동적 할당, strncpy

[문서 만들기 재귀함수 디자인]
- 매개변수
  - 상위 단계의 주소(예를 들어 지금 단계가 단어면 단어들을 저장할 라인의 주소) / 자료형: void*
  - [시작주소 / 자료형: char*, 끝주소 / 자로형: char*] 묶어서 range_t 구조체로 만들어서 사용
  - 단계: 구분자를 알 수 있음, 종료 조건인지 알 수 있음 / 자료형: enum 
- 구현부
  - 시작주소 ~ 끝주소 범위를 알고 있는 상태
  - 범위를 순회하면서 구분자를 이용해 하위 단계(지금이 문장이면 하위 단계는 단어) 수 세기
  - 동적 할당
  - 구분자를 이용해 하위 단계의 시작 주소, 끝 주소 저장
  - 다음 단계로
- 종료 조건
  - 단어 단계
  - 구분자를 만나면, 시작주소와 offset을 이용해 문자열 복사
- 반환값
  - 없음

### 테스트 케이스

[문단]

- 일반화하면 3개의 공간
  - 시작 ~ 첫번째 \n을 만나는 공간(첫줄)
  - \n ~ \n 사이 공간(중간줄, 마지막 줄을 제외한 줄이 모두 여기 해당)
  - \n ~ 널문자 사이 공간(마지막 줄)

1. 구분자만 있는 경우: "\n\n"
2. 첫 줄이 빈 경우: "\nHello, World!\nPOCU"
3. 마지막 줄이 빈 경우: 
   - "Hello, World!\nPOCU\n"
   - "Hi\n"
4. 중간이 빈 경우: "Hello, World!\n\nPOCU"
5. 한 줄인 경우(\n이 없는 경우): "Hi!"

### 개념

- fread 사용 시 주의사항
  - 시스템 마다 뉴라인 캐릭터 처리가 다름
  - 유닉스의 경우, rb,r 모드 똑같음, 둘다 '\n'을 그대로 사용함
  - 윈도우즈의 경우, r로 열면 '\r\n'을 '\n'으로 변환해서 읽어옴
  - 따라서 시스템 호환성을 위해 r로 열자
    - 만약 채점 서버가 윈도우즈라면, '\r'을 제거해야 함
    - 채점 서버에서 r 모드로 파일을 썼다면, 파일에 '\n'이 '\r\n'으로 저장되어 있을 것
- 윈도우즈에서 r로 fwrite 하면 '\n'이 '\r\n'으로 저장됨
- 윈도우즈에서 r로 fread하면 '\r\n'이 '\n'으로 변환되어 읽어짐

- 파일 읽어오기(Reading text file of unknown size)
    - [참고](https://stackoverflow.com/questions/31057175/reading-text-file-of-unknown-size)
    - fseek으로 SEEK_END로 이동하고, ftell을 사용하면 SEEK_SET에서 부터 offset을 알려주는데 이게 파일의 바이트 수, 글자 수와 동일
      - 예를들어 문서에 "ABCDE"가 있으면 SEEK_END로 이동하면 5를 반환
      - SEEK_SET == 인덱스 0, SEEK_END == 파일의 끝(마지막 문자 다음 위치) 
    - size를 정확히 알면, fread에서 한 번에 읽어오면 됨
      - fread(buffer, size, 1, file_stream);

-fseek
  - 성공하는 경우, 반환값 0
  - 실패하는 경우, 반환값 0이 아닌 값
- ftell
  - 성공하는 경우, 반환값 long offset, SEEK_SET에서 부터의 offset (텍스트 모드로 열었기 때문)
  - 실패하는 경우, -1

- 동적할당
    - malloc
    - realloc
      - memcpy의 세번째 인자로 0이 들어오는 경우
      - 임시 변수를 선언하고, 임시 변수에 적절한 크기의 메모리를 할당하고, memcpy로 복사하고, 할당된 메모리를 원래 변수에 저장하기    
      - 임시 변수를 선언하면 임시 변수에 메모리를 할당할 때, 실패하면 임시 변수만 해제해주면 되서 코드가 깔끔해진다.
- 동적 배열
  - 배열의 크기를 동적으로 할당하는 것
  - realloc
  - malloc + memcpy + free

- clearerr 함수

- 구조체는 값형으로 사용하는 것이 best practice
  - 구조체를 참조형처럼 사용하면, 얉은 복사의 위험이 생김
  - range_t의 배열에는 값을 복사하면 되지만, range_t*의 배열에는 주소를 복사해야 함
  - range_t*의 배열에 주소를 복사할 때, 함수 범위 안의 주소를 복사하게 되면 memory stomp 발생

## void dispose(void);

### 구현

- dispose 내부에서 static 포인터 변수에 접근해 메모리 할당을 해제함
  - 또는 FILE*
  - FILE*는 열려있다면, NULL이 아닐 때 fclose로 닫아줘야함
- dispose 내부에서 free()의 인자로 넘길 static 포인터들은 NULL로 초기화할 것
  - (free(NULL)은 아무런 문제가 없음)

- dispose 호출하는 시점에 알고 있는 정보
    - 문단의 개수
    - 문장의 개수
    - 단어의 개수
- 알고 있는 정보로 반복문을 돌려서 각각 메모리 할당 해제
    - 단어 -> 문장 -> 문단 -> 문서
    - 작은 단위에서 부터 할당 해제
    - 재귀 느낌이..?

### 개념

- free() 함수의 인자로 NULL을 넘겨도 아무런 문제가 없음
- C# 강의에서 파일/디렉토리 재귀로 열기

## unsigned int get_total_word_count(void);

### 구현

- static 변수에 문서화 할 때 총 단어수 저장
- 로딩된 문서가 없으면 총 단어 개수 0임

## unsigned int get_total_sentence_count(void);

### 구현

- static 변수에 문서화 할 때 총 문장수 저장
- 로딩된 문서가 없으면 총 문장 개수 0임

## unsigned int get_total_paragraph_count(void);

### 구현

- static 변수에 문서화 할 때 총 문단수 저장
- 로딩된 문서가 없으면 총 문단 개수 0임

## const char*** get_paragraph_or_null(const unsigned int paragraph_index);

### 구현

- 총 문단 개수 알고 있기 때문에, 인덱스로 배열에서 탐색 가능
- 범위 밖의 색인도 총 문단 개수를 알고 있어서 판별 가능
- 로딩된 문서가 없으면 총 문단 개수 0임

## unsigned int get_paragraph_word_count(const char*** paragraph);

### 구현

- get_paragraph_or_null에서 반환한 char***를 매개변수로 받음
- char***에서 문장의 개수를 알고 있음(배열의 길이)
- 각 문장 순회하면서, 각 문장의 단어 개수 합 구하기

## unsigned int get_paragraph_sentence_count(const char*** paragraph);

### 구현

- get_paragraph_or_null에서 반환한 char***를 매개변수로 받음
- char***에서 문장의 개수를 알고 있음(배열의 길이)
- 문장의 개수 반환

## const char** get_sentence_or_null(const unsigned int paragraph_index, const unsigned int sentence_index);

### 구현

- 문단 색인, 문장 색인을 매개변수로 받음
- 인덱스로 배열에서 탐색 하면 됨

## unsigned int get_sentence_word_count(const char** sentence);

### 구현

- get_sentence_or_null에서 반환한 char**를 매개변수로 받음
- char**에서 단어의 개수 알고 있어서 반환(배열의 길이)

## int print_as_tree(const char* filename);

### 구현

- 파일 출력
- 문서에서 문단 인덱스로 "Paragraph <문단 색인>:" 출력
    - 각 문단에서 문장 인덱스로 들여쓰기(스페이스 4개) + "Sentence < 문장 색인 >:" 출력
        - 각 문장에서 단어 인덱스로 들여쓰기(스페이스 4개) 2번 + 단어
        - 각 단어 출력 끝나면 '\n'
    - 각 문장 출력끝나면 '\n'
- 각 문단 출력 끝나면 '\n'

### 개념

- fwrite
- 재귀 함수