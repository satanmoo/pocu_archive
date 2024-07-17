# assignment 4

## 공통 요구사항

- hashmap.h 안에 선언된 함수들이 받는 포인터형 매개변수에 NULL이 들어오지 않는다고 가정하셔도 좋습니다
  - hashmap_t* 는 not_null

## init_hashmap_malloc() 함수를 구현한다

### 구현

- `hashmap_t* init_hashmap_malloc(size_t length, unsigned int (*p_hash_func)(const char* key));`
  - 매개변수 목록
    - 해시맵의 크기
      - `node_t** plist;` 의 길이
      - node_t*의 배열의 길이
        - node_t*는 연결리스트
      - 크기를 미리 알고 있어서, 동적 할당 malloc의 매개변수로 넘길 수 있음
      - hashmap->plist의 모든 요소는 NULL로 초기화
    - 해시 함수
      - 구조체 변수 `unsigned int (*hash_func)(const char* key);`에 대입하면 끝
        - 함수 포인터
        - 함수가 저장된 메모리(스택)의 주소를 값으로 대입

### 개념

- 미리 할당할 메모리의 크기를 알 때 동적할당
- memset으로 모든 바이트 0으로 초기화
  - NULL 포인터의 값
- 함수 포인터

## add_key() 함수를 구현한다

### 구현

- `int add_key(hashmap_t* hashmap, const char* key, const int value);`
  - 매개변수 목록
    - 해시맵의 주소
      - `hashmap->plist;`로 해시맵을 구현한 배열에 접근 가능
    - key
      - `hashmap->hash_func`로 해시 함수를 호출해 해시값을 구함
      - `hashmap->length` 로 해시맵을 구현한 배열의 크기를 구하고, 나머지 연산으로 배열의 인덱스를 구함
    - value
      - 동적 할당으로 새로운 노드 만들고, value 대입
  - 배열의 인덱스로 배열의 원소에 접근
    - 배열의 원소는 `*plist`로 연결 리스트의 헤드
    - 연결 리스트를 순회해서, 키가 중복되는지 확인
      - 키가 중복되면 FALSE 반환
    - 연결 리스트의 헤드에 삽입
      - 동적 할당으로 새로은 노드 만들기
        - key 대입
          - 동적 할당
          - 깊은 복사
        - value 대입
        - next에 기존의 헤드 대입
      - 헤드 교체
        - `hash_map->plist[index] = new_node;`
      - TRUE 반환

### 개념

- 해시 맵
  - 키에서 해시값 변환, 해시값에서 인덱스 변환, 연결리스트의 헤드 찾기
  - 연결 리스트 순회
  - 연결 리스트 헤드에 삽입

## get_value() 함수를 구현한다

### 구현

- `int get_value(const hashmap_t* hashmap, const char* key);`

## update_value() 함수를 구현한다
  - 매개변수 목록
    - 해시맵의 주소
      - `hashmap->plist;`로 해시맵을 구현한 배열에 접근 가능
    - key
      - `hashmap->hash_func`로 해시 함수를 호출해 해시값을 구함
      - `hashmap->length` 로 해시맵을 구현한 배열의 크기를 구하고, 나머지 연산으로 배열의 인덱스를 구함
  - 배열의 인덱스로 연결 리스트의 헤드에 접근
    - 순회, key 비교, value 반환
    - 못 찾으면 -1 반환

### 구현

- `int update_value(hashmap_t* hashmap, const char* key, int value);`
  - 매개변수 목록
    - 해시맵의 주소
      - `hashmap->plist;`로 해시맵을 구현한 배열에 접근 가능
    - key
      - `hashmap->hash_func`로 해시 함수를 호출해 해시값을 구함
      - `hashmap->length` 로 해시맵을 구현한 배열의 크기를 구하고, 나머지 연산으로 배열의 인덱스를 구함
    - value
      - 배열의 인덱스로 연결 리스트의 헤드에 접근
      - 순회 
        - 찾으면 업데이트, TRUE 반환
        - 못 찾으면 FALSE 반환

### 개념
  - 키에서 해시값 변환, 해시값에서 인덱스 변환, 연결 리스트의 헤드 찾기
  - 연결 리스트 순회

## remove_key() 함수를 구현한다
- 매개변수 목록
  - 해시맵의 주소
    - `hashmap->plist;`로 해시맵을 구현한 배열에 접근 가능
  - key
    - `hashmap->hash_func`로 해시 함수를 호출해 해시값을 구함
    - `hashmap->length` 로 해시맵을 구현한 배열의 크기를 구하고, 나머지 연산으로 배열의 인덱스를 구함
    - 배열의 인덱스로 연결 리스트의 헤드에 접근
      - 순회
      - strcmp
      - 찾으면 제거
        - 이전 노드의 주소를 기억해야함
        - free(node->key)
        - free(node)
        - TRUE 반환
      - 못 찾으면 FALSE 반환

### 개념
  - 키에서 해시값 변환, 해시값에서 인덱스 변환, 연결리스트의 헤드 찾기
  - 연결 리스트 순회
  - 연결 리스트의 제거

## destroy() 함수를 구현한다
  - 매개변수 목록
    - 해시맵의 주소
      - `hashmap->plist;`로 해시맵을 구현한 배열에 접근 가능
      - 배열을 순회하면서 연결 리스트의 헤드에 접근
        - 헤드에서 끝(꼬리)까지 순회하면서 메모리 해제
        - 각 노드에서 문자열 메모리 해제
      - 해시맵을 구현한 배열 메모리 해제
      - 해시맵의 주소의 메모리 해제

### 개념
  - 키에서 해시값 변환, 해시값에서 인덱스 변환, 연결리스트의 헤드 찾기
  - 연결 리스트의 모든 노드 메모리 해제

## 실수하기 좋은 포인터

- 다음 코드의 문제점은 무엇일까?

```c++
int remove_key(hashmap_t* hashmap, const char* key)
{
    unsigned int hash;
    node_t* head;
    node_t** p_node;

    hash = hashmap->hash_func(key);
    head = (hashmap->plist)[hash % (hashmap->length)];
    p_node = &head;
    while (*p_node != NULL) {
        if (strcmp((*p_node)->key, key) == 0) {
            node_t* tmp = *p_node;
            *p_node = tmp->next;
            free(tmp->key);
            free(tmp);
            return TRUE;
        }
        p_node = &((*p_node)->next);
    }

    return FALSE;
}
```

- p_node에는 정말로 hashmap의 내부 node_t**가 대입될까?
- p_node에는 지역 변수 head의 주소가 대입됨
  - head의 값은 node_t*로 연결리스트의 시작 주소(헤드)
- hashmap내부 node_t**의 값을 대입하려면?
  - p_node = &((hashmap->plist)[hash % (hashmap->length)]); 이렇게 바로 주소를 구해 대입
  - hashmap->plist + (hash % (hashmap->length))
    - node_t**에서 포인터 연산해서 주소를 구하는 것과 동일함