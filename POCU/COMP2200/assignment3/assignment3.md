# Assignment3

## int load_document(const char* document)

### 구현

[파일에서 읽어오기]

- 파일 크기를 모를 때 읽어오기
    - fseek + ftell + rewind 을 사용하는 방법
    - fseek(stream, 0, SEEK_END);으로 SEEK_END 로부터 0의 offset으로 이동
        - 즉 파일 위치 표시자를 읽은 파일 스트림의 끝으로 이동
    - ftell(stream)으로 파일 위치 표시자의 위치를 구함
        - 바이너리 모드로 열었기 때문에 파일의 시작 지점으로 부터 몇 바이트 떨어져있는지 파일 위치표시자의 위치를 계산
        - 파일이 총 몇 바이트인지 구할 수 있음
    - rewind(stream)으로 파일 위치표시자를 처음으로 되돌리기
    - 파일 크기만큼 동적 할당
    - 파일 읽기

[문서 구조 만들기]

- 입력 전 상태
    - 파일에서 읽어온 문자들
        - 문자 데이터를 저장할 메모리 동적 할당으로 확보한 상태
            - 읽기 전용으로 사용
            - 메모리의 주소로 문자값(내용)을 읽을 수 있음
        - 정적 변수로 문자 데이터를 저장한 메모리를 참조

- 매개변수 목록
    - 읽어서 처리할 문자들의 시작 주소
    - 읽어서 처리할 문자들의 끝 주소
    - 문서 레벨
    - 문서 레벨에 대응하는 개행문자 목록

- 알고리즘
    - 문서/문단/문장 레벨
        - 레벨에 맞는 구조체 포인터 선언
        - 구조체의 멤버는 아래와 같음
            - 다음 레벨(하위 레벨)의 동적 배열
                - Ex) sentence_t라면 word_t*
            - 다음 레벨의 배열의 크기
                - Ex) sentence_t라면 word_cnt;
        - 구조체 포인터로 indirection해서 구조체 초기화
            - 다음 레벨의 동적 배열 동적 할당
            - 다음 레벨 배열의 크기 0으로 초기화
        - 매개변수로 입력받은 [시작주소, 끝주소]를 순회하며 읽기
        - 토큰의 시작 주소를 저장
        - 구분자를 만나면 아래의 단계
            - 토큰의 끝 주소 == 구분자 주소 - 1
            - 토큰의 시작 주소와 토큰의 끝 주소로 토큰의 길이 구함
                - 토큰의 길이가 0이면 넘어감
                - 토큰의 길이가 1이상이면 아래 단계
                    - 다음 레벨의 동적 배열이 꽉 찬 경우 확장
                        - capacity == word_cnt을 만족하면 꽉참
                    - 재귀호출(토큰의 시작, 토큰의 끝, 문서 레벨 + 1, 다음 레벨에 해당하는 개행문자 목록)로 다음 레벨의 구조체 주소를 반환
                    - 반환 받은 다음 레벨 구조체의 주소에 indirection해서 동적 배열에 저장
                      - 깊은 복사
    - 단어 레벨
        - 매개변수로 입력받은 [시작주소, 끝주소]를 순회하며 읽기
        - 단어의 길이: 끝주소 - 시작주소 + 1
        - char* 동적할당
            - 단어의 길이 + 1 만큼 할당
            - 마지막에 널 문자가 들어감(C-stlye 문자열 보장)
        - 단어 깊은 복사
        - 단어의 주소 반환

### 테스트 케이스

[문단 분해의 예]

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
    - 뉴라인 캐릭터 처리를 편하게 하고 싶으면, 텍스트 모드로 열기
        - 시스템 마다 뉴라인 캐릭터 처리가 다름
        - 유닉스의 경우, rb,r 모드 똑같음, 둘다 '\n'을 그대로 사용함
        - 윈도우즈의 경우, r로 열면 '\r\n'을 '\n'으로 변환해서 읽어옴
        - 따라서 시스템 호환성을 위해 r로 열자
            - 만약 채점 서버가 윈도우즈라면, '\r'을 제거해야 함
            - 채점 서버에서 r 모드로 파일을 썼다면, 파일에 '\n'이 '\r\n'으로 저장되어 있을 것
        - 윈도우즈에서 r로 fwrite 하면 '\n'이 '\r\n'으로 저장됨
        - 윈도우즈에서 r로 fread하면 '\r\n'이 '\n'으로 변환되어 읽어짐
    - ftell로 파일 위치 표시자의 위치를 구할 때 바이트 단위로 구하고 싶으면 이진 모드로 열기

- 파일 읽어오기(Reading text file of unknown size)
    - [참고](https://stackoverflow.com/questions/31057175/reading-text-file-of-unknown-size)
    - fseek으로 SEEK_END로 이동하고, ftell을 사용하면 SEEK_SET에서 부터 offset을 알려주는데 이게 파일의 바이트 수, 글자 수와 동일
        - 파일을 이진 모드로 열었다는 가정
        - 예를들어 문서에 "ABCDE"가 있으면 SEEK_END로 이동하면 5를 반환
        - SEEK_SET == 인덱스 0, SEEK_END == 파일의 끝(마지막 문자 다음 위치)
    - 파일의 size를 구해서 정확히 알면, fread에서 한 번에 읽어오면 됨
        - fread(buffer, size, 1, file_stream);

- 구조체는 값형으로 사용하는 것이 best practice
  - 구조체 멤버를 포인터 변수로 선언하고, 깊은 복사하기
    - 동적 배열로 메모리 할당 후 값을 복사
  - 재귀 함수를 디자인할 때 다음 레벨의 구조체의 주소를 반환
    - indirection해서 구조체의 값을 이번 레벨의 구조체의 멤버(다음 레벨 구조체 배열)에 저장

## void dispose(void);

### 구현

- dispose 내부에서 free()의 인자로 넘길 static 포인터들은 NULL로 초기화할 것
    - (free(NULL)은 아무런 문제가 없음)

- dispose 호출하는 시점에 알고 있는 정보
    - 총 문단의 개수
    - 총 문장의 개수
    - 총 단어의 개수
- 알고 있는 정보로 반복문을 돌려서 각각 메모리 할당 해제
    - 단어 -> 문장 -> 문단 -> 문서
    - 작은 단위에서 부터 할당 해제
    - 재귀 느낌이..?

### 개념

- free() 함수의 인자로 NULL을 넘겨도 아무런 문제가 없음
- 할당 받은 포인터로 포인터 연산 금지
  - 포인터 연산이 끝나면, 원래 할당 받은 주소값을 잃어버려서 free할 때 오류 발생

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
