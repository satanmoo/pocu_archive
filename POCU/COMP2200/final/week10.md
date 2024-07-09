# Week9

## 암기

### 원칙상 스택은 젤 위의 자료에만 접근 가능

- 구현할 때 신경써야하는 포인트

### 요소와 노드

- 요소는 메모리에 일렬로 나열
- 노드는 메모리에 산재

## 코드 문제

### 문제1: 배열의 삽입 구현하기

- 아래 배열을 바탕으로 배열의 색인에 값을 삽입하는 코드를 구현

```c++
#define MAX_NUMS (8)

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void insert_at(size_t index, int num);
```

```c++
#include <stdio.h>
#include <assert.h>

#define MAX_NUMS (8)

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void insert_at(size_t index, int num)
{
    size_t i;

    assert(index <= s_num_count);
    assert(s_num_count < MAX_NUMS);

    for (i = s_num_count; i > index; --i) {
        s_nums[i] = s_nums[i - 1];
    }
    s_nums[index] = num;
    ++s_num_count;
}
```

- 시간 복잡도
  - 평균: O(N)
  - 최악: O(N)
  - 최선: O(1), 제일 뒤에 넣는 경우

### 문제2: 배열의 삭제 구현하기

- 아래의 배열을 바탕으로, 특정 위치의 요소를 삭제하는 코드를 구현

```c++
enum {
    MAX_NUMS = 8,
    INVALID_INDEX = -1
};

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void remove_at(size_t index);
```

```c++
#include <stdio.h>
#include <assert.h>

enum {
    MAX_NUMS = 8,
    INVALID_INDEX = -1
};

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void remove_at(size_t index)
{
    size_t i;

    assert(index < s_num_count);

    --s_num_count;
    for (i = index; i < s_num_count; ++i) {
        s_nums[i] = s_nums[i + 1];
    }
}

```

- 시간 복잡도
  - 평균: O(N)
  - 최악: O(N)
  - 최선: O(1), 가장 마지막 원소 삭제(index == s_num_count - 1)

### 문제3: 배열의 검색 구현하기

```c++
enum {
    MAX_NUMS = 8,
    INVALID_INDEX = -1
};

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

size_t find_index(int num);
```

```c++
#include <stdio.h>
#include <assert.h>

enum {
    MAX_NUMS = 8,
    INVALID_INDEX = -1
};

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

size_t find_index(int num)
{
    size_t i;

    for (i = 0; i < s_num_count; ++i) {
        if (s_nums[i] == num) {
            return i;
        }
    }

    return INVALID_INDEX;
}
```

- 시간 복잡도
  - 평균: O(N)
  - 최악: O(N)
  - 최선: O(1), 맨 앞에 요소를 찾는 경우

### 문제4: 빠른 배열의 삭제 구현하기

- 배열의 원소를 삭제
- 배열의 순서는 보장되지 않음

```c++
#include <assert.h>
#include <stdio.h>

enum { MAX_NUMS = 8 };
enum { INVALID_INDEX = -1 };

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void remove_at_unordered(size_t index)
{
    assert(index < s_num_count);
    
    if (s_num_count == 1) {
        --s_num_count;
        return;
    }
    
    s_nums[index] = s_nums[--s_num_count];  // 빈 슬롯에 배열의 마지막 요소를 넣음
}
```

- 마지막 요소: s_nums[s_num_count - 1];
- s_num_count가 1인 경우, 삭제 후 마지막 요소라는 개념이 없어짐
- 시간 복잡도
  - O(N)

### 문제5: 스택의 삽입 구현하기

- 배열을 바탕으로 스택에 삽입하는 코드를 구현하기

```c++
#include <assert.h>
#include <stdio.h>

enum { MAX_NUMS = 8 };
enum { INVALID_INDEX = -1 };

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void push(int n)
```

```c++
#include <assert.h>
#include <stdio.h>

enum { MAX_NUMS = 8 };
enum { INVALID_INDEX = -1 };

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void push(int n)
{
    assert(s_num_count < MAX_NUMS);
    s_nums[s_num_count++] = n;
}
```

- `Push`라고 표현함
- 마지막 원소의 다음 위치(s_num_count)에 값을 대입
- 시간 복잡도
  - O(1)

### 문제6: 스택의 삭제 구현하기

- 배열을 바탕으로 스택에서 삭제하는 코드를 구현하기

```c++
#include <assert.h>
#include <stdio.h>

enum { MAX_NUMS = 8 };
enum { INVALID_INDEX = -1 };

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

int pop(void);
```

```c++
#include <assert.h>
#include <stdio.h>

enum { MAX_NUMS = 8 };
enum { INVALID_INDEX = -1 };

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

int pop(void)
{
    assert(s_num_count > 0);
    
    return s_nums[--s_num_count];
}
```

- 'Pop'이라고 표현함
- 시간 복잡도
  - O(1)

### 문제7: 스택의 검색 구현하기

- 배열을 바탕으로 스택에서 검색하는 코드를 구현하기

```c++
#include <assert.h>
#include <stdio.h>

enum { MAX_NUMS = 8 };
enum { INVALID_INDEX = -1 };

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

int search(int num);
```

- 원칙상 스택의 맨 위(끝)요소가 아니면 직접 접근할 수 없음
  - push, pop으로 임시 배열에 옮기는 방식으로 확인해야함

```c++
#include <assert.h>
#include <stdio.h>

enum {
    MAX_NUMS = 8
};
enum {
    INVALID_INDEX = -1
};
enum {
    TRUE = 1,
    FALSE = 0
};

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void push(int n)
{
    assert(s_num_count < MAX_NUMS);
    s_nums[s_num_count++] = n;
}

int pop(void)
{
    assert(s_num_count > 0);

    return s_nums[--s_num_count];
}

int search(int num)
{
    int found = FALSE;
    int temp_count = 0;
    int temp_max = (int)s_num_count;
    int temp[MAX_NUMS];

    while (temp_count < temp_max) {
        int value = pop();
        if (value == num) {
            found = TRUE;
            break;
        }
        temp[temp_count++] = value;
    }

    while (temp_count > 0) {
        push(temp[--temp_count]);
    }

    return found;
}
```

- 시간 복잡도
  - O(N)
  - 요소 제거, 복구 각각 O(N)으로 O(2N)계산 되지만, 빅오 표기법에 따라 O(N)으로 표기

### 문제8: 후위 표기법으로 적힌 수식을 스택으로 계산 구현하기

- 한 글자를 읽음
- 글자를 읽는데 성공한 경우
  1. 피연산자면, 스택에 넣음
  2. 연산자면, 스택에서 피연산자 둘을 꺼내 계산하고, 그 결과를 다시 스택에 넣음
  3. 1번으로 돌아감
- 글자를 읽는데 실패한 경우(수식의 끝에 도달)
  - 스택에서 pop하면 수식의 결과 값임

```c++
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

enum {
    MAX_NUMS = 100
};
enum {
    INVALID_INDEX = -1
};
enum {
    TRUE = 1,
    FALSE = 0
};

int s_nums[MAX_NUMS];
size_t s_num_count = 0;

void push(int n)
{
    assert(s_num_count < MAX_NUMS);
    s_nums[s_num_count++] = n;
}

int pop(void)
{
    assert(s_num_count > 0);

    return s_nums[--s_num_count];
}

int main(void)
{

    char expression[] = "2 4 5 + * 15 3 / -";
    const char* token = strtok(expression, " ");

    while (token != NULL) {
        if (strcmp(token, "+") == 0) {
            int a = pop();
            int b = pop();
            push(b + a);
        } else if (strcmp(token, "-") == 0) {
            int a = pop();
            int b = pop();
            push(b - a);
        } else if (strcmp(token, "*") == 0) {
            int a = pop();
            int b = pop();
            push(b * a);
        } else if (strcmp(token, "/") == 0) {
            int a = pop();
            int b = pop();
            push(b / a);
        } else {
            push(atoi(token));
        }
        token = strtok(NULL, " ");
    }
    printf("%d", pop());

    return 0;
}
```

### 문제9: 원형 버퍼의 개념을 이용해 큐의 삽입/삭제 구현

- 이해를 돕기 위해서는 삽입/삭제를 동시에 구현하는 것이 편함

```c++
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

enum {
    MAX_NUMS = 8
};
enum {
    INVALID_INDEX = -1
};
enum {
    TRUE = 1,
    FALSE = 0
};

int s_nums[MAX_NUMS];
size_t s_front = 0;
size_t s_back = 0;
size_t s_num_count = 0;

void enqueue(int n)
{
    assert(s_num_count < MAX_NUMS); /* s_num_count == MAX_NUMS가 되면 이후 로직을 진행할 수 없음 */

    s_nums[s_back] = n;
    s_back = (s_back + 1) % MAX_NUMS;
    ++s_num_count;
}

int dequeue(void)
{
    int res;

    assert(s_num_count > 0);

    res = s_nums[s_front];
    s_front = (s_front + 1) % MAX_NUMS;
    --s_num_count;

    return res;
}
```

- 삽입 시간 복잡도
  - O(1)
- 삭제 시간 복잡도
  - O(1)

### 문제10: 원형 버퍼의 개념을 이용해 큐의 검색 구현

- 원칙상 큐의 맨 앞의 요소에만 접근 가능

```c++
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

enum {
    MAX_NUMS = 8
};
enum {
    INVALID_INDEX = -1
};
enum {
    TRUE = 1,
    FALSE = 0
};

int s_nums[MAX_NUMS];
size_t s_front = 0;
size_t s_back = 0;
size_t s_num_count = 0;

void enqueue(int n)
{
    assert(s_num_count < MAX_NUMS); /* s_num_count == MAX_NUMS가 되면 이후 로직을 진행할 수 없음 */

    s_nums[s_back] = n;
    s_back = (s_back + 1) % MAX_NUMS;
    ++s_num_count;
}

int dequeue(void)
{
    int res;

    assert(s_num_count > 0);

    res = s_nums[s_front];
    s_front = (s_front + 1) % MAX_NUMS;
    --s_num_count;

    return res;
}

int search(int n)
{
    int found = FALSE;
    int temp_q[MAX_NUMS];
    size_t temp_max = s_num_count;
    size_t temp_count = 0;
    size_t i;

    while (temp_count < temp_max) {
        int value = dequeue();
        if (value == n) {
            found = TRUE;
        }
        temp_q[temp_count++] = value;
    }

    for (i = 0; i < temp_max; ++i) {
        enqueue(temp_q[i]);
    }

    return found;
}
```

### 문제11: 연결 리스트 삽입 구현

```c++
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct node {
    int value;
    struct node* next;
} node_t;

void print_node(const node_t* head)
{
    const node_t* current = head;

    while (current != NULL) {
        printf("%d\n", current->value);
        current = current->next;
    }
}

void destroy(node_t* head)
{
    node_t* current = head;

    while (current != NULL) {
        node_t* next = current->next;
        free(current);
        current = next;
    }
}

void insert_front(node_t** phead, int n)
{
    node_t* head = *phead;
    node_t* new = malloc(sizeof(node_t));
    /* 할당 오류 코드 생략 */
    new->value = n;
    new->next = head;
    *phead = new;
}
```

- 시간 복잡도
  - O(1)

### 문제11: 연결 리스트 오름차순 삽입 구현

```c++
typedef struct node {
    int value;
    struct node* next;
} node_t;

void insert_sorted(node_t** phead, int n)
{
    node_t** pp = phead;    /* 포인터 연산에 사용할 포인터와 원래의 헤드의 주소값을 가진 포인터를 분리 */
    node_t* new_node = malloc(sizeof(node_t));
    /* 메모리 할당 오류 코드 생략 */
    new_node->value = n;
    new_node->next = NULL;

    while (*pp != NULL) {
        if ((*pp)->value >= n) { /* *pp의 직전에 새로운 노드 삽입 */
            break;
        }
        pp = &((*pp)->next);
    }

    new_node->next = *pp;
    *pp = new_node;
}
```

### 문제12: 연결 리스트 삭제 구현

```c++
typedef struct node {
    int value;
    struct node* next;
} node_t;

void remove_node(node_t** phead, int n)
{
    node_t** pp = phead;

    while (*pp != NULL) {
        if ((*pp)->value == n) {
            node_t* found = *pp;
            *pp = (*pp)->next;
            free(found);
            break;
        }

        pp = &((*pp)->next);
    }
}
```

### 문제13: 이중 연결 리스트 구현

```c++
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct node {
    int value;
    struct node* next;
    struct node* prev;
} node_t;

void print_node(node_t** head)
{
    node_t** pp = head;

    while (*pp != NULL) {
        printf("%d ", (*pp)->value);
        pp = &((*pp)->next);
    }
    printf("\n");
}

void destroy(node_t** head)
{
    node_t** pp = head;

    while (*pp != NULL) {
        node_t* next = (*pp)->next;
        free(*pp);
        pp = &next;
    }
}

void append(node_t** phead, int n)
{
    node_t* current = *phead;
    node_t* new_node = malloc(sizeof(node_t));
    new_node->value = n;
    new_node->next = NULL;
    new_node->prev = NULL;

    if (*phead == NULL) {
        *phead = new_node;
        return;
    }

    while (current->next != NULL) {
        current = current->next;
    }
    current->next = new_node;
    new_node->prev = current;
}

void insert_front(node_t** phead, int n)
{
    node_t* new_node = malloc(sizeof(node_t));
    new_node->value = n;
    new_node->next = NULL;
    new_node->prev = NULL;

    if (*phead == NULL) {
        *phead = new_node;
        return;
    }

    new_node->next = *phead;
    (*phead)->prev = new_node;
    *phead = new_node;
}

void insert_sorted(node_t** phead, int n)
{
    node_t* current = *phead;
    node_t* new_node = malloc(sizeof(node_t));
    new_node->value = n;
    new_node->next = NULL;
    new_node->prev = NULL;

    // 리스트가 비어있을 경우, 새로운 노드를 헤드로 삽입
    if (*phead == NULL) {
        *phead = new_node;
        return;
    }

    // 삽입 지점을 찾기 위해 리스트를 순회
    while (current != NULL && current->value < n) {
        current = current->next;
    }

    // 새로운 노드 삽입
    if (current == NULL) {
        // 리스트의 끝에 삽입
        current = *phead;
        while (current->next != NULL) {
            current = current->next;
        }
        current->next = new_node;
        new_node->prev = current;
    } else if (current == *phead) {
        // 리스트의 시작에 삽입
        new_node->next = current;
        current->prev = new_node;
        *phead = new_node;
    } else {
        // 리스트의 중간에 삽입
        new_node->next = current;
        new_node->prev = current->prev;
        current->prev->next = new_node;
        current->prev = new_node;
    }
}

void remove_node(node_t** phead, int n)
{
    node_t* current = *phead;

    // 삭제 노드를 찾기 위해 리스트를 순회
    while (current != NULL && current->value != n) {
        current = current->next;
    }

    if (current == NULL) {
        // 못 찾은 경우
        return;
    }

    if (current == *phead) {
        // 헤드 노드를 삭제하는 경우
        *phead = current->next;
        free(current);
        if (*phead != NULL) {   // 오직 헤드 노드만 있는 경우, current->next == NULL
            (*phead)->prev = NULL;
        }
    } else {
        // 리스트의 마지막 노드를 삭제하는 경우
        if (current->next == NULL) {
            current->prev->next = current->next;
            free(current);
            return;
        }
        // 리스트의 중간에서 삭제하는 경우
        current->prev->next = current->next;
        current->next->prev = current->prev;
        free(current);
    }
}

int main()
{
    node_t* head = NULL;
    print_node(&head);
    insert_sorted(&head, 1);
    print_node(&head);
    insert_sorted(&head, 1);
    print_node(&head);
    insert_sorted(&head, -1);
    print_node(&head);
    insert_sorted(&head, 2);
    print_node(&head);
    insert_sorted(&head, 0);
    print_node(&head);
    remove_node(&head, -99);
    print_node(&head);
    remove_node(&head, 2);
    print_node(&head);
    remove_node(&head, 0);
    print_node(&head);
    remove_node(&head, -1);
    print_node(&head);
    remove_node(&head, 1);
    print_node(&head);
    remove_node(&head, 1);
    print_node(&head);

    return 0;
}
```

### 문제14: 연결리스트 맨 뒤에 삽입하는 함수 구현

```c++
typedef struct node {
    int value;
    node_t* next;
} node_t;

void insert_back(node_t** phead, int n)
{
    node_t* newNode;
    node_t** pp;

    newNode = malloc(sizeof(node_t));
    newNode->next = NULL;
    newNode->value = n;

    pp = phead;

    if (*pp == NULL) {
        *pp = newNode;
        return;
    }

    while (*pp != NULL) {
        if ((*pp)->next == NULL) {
            (*pp)->next = newNode;
            break;
        }

        pp = &(*pp)->next;
    }
}
```

- 노드에서 next값이 NULL이면 마지막 노드임
  - *pp가 NULL이 되서 반복문이 종료되면, 노드를 추가할 수 없음