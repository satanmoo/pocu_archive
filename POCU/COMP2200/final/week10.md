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

// 테스트 코드
insert_at(0, 1);
insert_at(0, 2);
insert_at(0, 3);
insert_at(0, 4);
insert_at(0, 5);
insert_at(0, 6);
insert_at(0, 7);
insert_at(0, 8);
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

- 고려할 점
    - 배열의 처음, 중간에 삽입
    - 배열의 끝에 삽입
    - 배열이 가득 찼을 때

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

// 테스트 코드
insert_at(0, 1);
insert_at(0, 2);
insert_at(0, 3);
insert_at(0, 4);
insert_at(0, 5);
insert_at(0, 6);
insert_at(0, 7);
insert_at(0, 8);
remove_at(7);
remove_at(0);
remove_at(0);
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

- 고려할 점
    - 배열의 처음, 중간에 삭제
    - 배열의 끝 삭제
    - 배열이 비었을 때

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

- 고려할 점
    - 스택이 꽉찼는지 확인

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

bool search(int num);
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

bool search(int num)
{
    assert(s_num_count > 0);

    int tmp_stack[MAX_NUMS];
    int tmp_stack_count = 0;
    int original_stack_size = (int)s_num_count;
    bool result = false;

    // Pop elements and check for the target number
    for (int i = 0; i < original_stack_size; ++i) {
        int e = pop();
        if (e == num) {
            result = true;
            tmp_stack[tmp_stack_count++] = e;
            break;
        }
        tmp_stack[tmp_stack_count++] = e;
    }

    // Push the elements back to the original stack
    for (int i = tmp_stack_count - 1; i >= 0; --i) {
        push(tmp_stack[i]);
    }

    return result;
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

    for (int i = 0; i < temp_max; ++i) {
        int value = dequeue();
        if (value == n) {
            found = TRUE;
        }
        temp_q[temp_count++] = value;
    }

    for (int i = 0; i < temp_max; ++i) {
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


void print_node(const node_t* head)
{
    const node_t* node = head;

    puts("print node");

    while (node != NULL) {
        printf("%d\n", node->value);
        node = node->next;
    }
}

void print_node_reverse(const node_t* tail)
{
    const node_t* node = tail;

    puts("print node reverse");

    while (node != NULL) {
        printf("%d\n", node->value);
        node = node->prev;
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

void insert_front(node_t** phead, node_t** ptail, int n)
{
    node_t* new_node = NULL;

    new_node = malloc(sizeof(node_t));
    new_node->next = *phead;
    new_node->prev = NULL;
    new_node->value = n;

    if (*phead == NULL) {
        *ptail = new_node;
    } else {
        (*phead)->prev = new_node;
    }

    *phead = new_node;
}

void insert_sorted(node_t** phead, node_t** ptail, int n)
{
    node_t* new_node = NULL;
    node_t** p = phead;

    new_node = malloc(sizeof(node_t));
    new_node->value = n;

    while (*p != NULL) {
        if ((*p)->value >= n) {
            break;
        }
        p = &((*p)->next);
    }

    new_node->next = *p;
    if (*p == NULL) {
        if (*phead == NULL) {   // if adding a new node for the first time
            *ptail = new_node;
            *phead = new_node;
            new_node->prev = NULL;
        } else {    // if adding a new node after the tail
            new_node->prev = *ptail;
            (*ptail)->next = new_node;
            *ptail = new_node;
        }
    } else {    // adding a new node in the middle
        new_node->prev = (*p)->prev;
        (*p)->prev = new_node;
        *p = new_node;
    }
}

void remove_node(node_t** phead, node_t** ptail, int n)
{
    node_t** p = phead;

    while (*p != NULL) {
        if ((*p)->value == n) {
            node_t* found = *p;
            if (found == *ptail) {
                *ptail = (*p)->prev;
            } else {
                ((*p)->next)->prev = (*p)->prev;
            }
            *p = (*p)->next;
            free(found);
            break;
        }
        p = &((*p)->next);
    }
}

int main(void)
{
    node_t* head = NULL;
    node_t* tail = NULL;

    insert_sorted(&head, &tail, 3);
    insert_sorted(&head, &tail, 4);
    insert_sorted(&head, &tail, 1);

    remove_node(&head, &tail, 1);
    remove_node(&head, &tail, 3);
    remove_node(&head, &tail, 4);

    print_node(head);
    print_node_reverse(tail);

    destroy(head);

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
    node_t** p = phead;

    while (*p != NULL) {
        p = &((*p)->next);
    }

    node_t* new_node = malloc(sizeof(node_t));
    new_node->next = NULL;
    new_node->value = n;
    *p = new_node;
}
```

- 노드에서 next값이 NULL이면 마지막 노드임
    - *pp가 NULL이 되서 반복문이 종료되면, 노드를 추가할 수 없음

### 문제15: Shunting yard algorithm

- 식을 읽음
    - 숫자의 경우 output(큐로 구현)에 입력
    - 연산자의 경우
        - 스택에 입력
            - 스택에 입력하기 전 스택의 탑의 우선순위보다 낮은 경우 스택에서 pop해서 output에 입력
            - 스택에 입력하기 전 스택의 탑과 우선순위가 같고 연산자 평가 순서가 left to right 인 경우 스택에서 pop해서 output에 입력
    - 괄호의 경우
      - '(': output에 입력
      - ')': 스택에서 '('이 top에 위치할 때 까지 pop(), pop()한 결과를 output에 입력, '('가 top에 위치하면 pop()으로 제거
    - 식을 다 읽었을 때
        - 스택에서 모두 pop해서 output에 입력

```c++
#include <assert.h>
#include <string.h>
#include <stdbool.h>
#include <stdio.h>
#include <ctype.h>

enum {
    MAX_NUMS = 8
};

char s_operators[MAX_NUMS];
size_t s_operator_count = 0;

void push(char n)
{
    assert(s_operator_count < MAX_NUMS);
    s_operators[s_operator_count++] = n;
}

char pop(void)
{
    assert(s_operator_count > 0);
    return s_operators[--s_operator_count];
}

char top(void)
{
    assert(s_operator_count > 0);
    return s_operators[s_operator_count - 1];
}

bool is_empty(void)
{
    return s_operator_count == 0;
}

int get_precedence(char op)
{
    switch (op) {
    case '+':
    case '-':
        return 1;
    case '*':
    case '/':
        return 2;
    case '^':
        return 3;
    default:
        return 0;
    }

    return 0;   // unreachable
}

bool is_left_associative(char op)
{
    switch (op) {
    case '+':
    case '-':
    case '*':
    case '/':
        return true; // left associative
    case '^':
        return false; // right associative
    default:
        return true;  // default to left associative
    }

    return true;    // unreachable
}

void process_operator(char op, char* output, int* output_index)
{
    while (!is_empty() && get_precedence(top()) >= get_precedence(op) && is_left_associative(op)) {
        output[(*output_index)++] = pop();
        output[(*output_index)++] = ' ';
    }
    push(op);
}

void handle_remaining_operators(char* output, int* output_index)
{
    while (!is_empty()) {
        output[(*output_index)++] = pop();
        output[(*output_index)++] = ' ';
    }
}

int main(void)
{
    char expression[] = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
    const char* token = strtok(expression, " ");
    char output[50] = { '\0' };
    int output_index = 0;

    while (token != NULL) {
        if (isdigit(token[0])) {
            for (int i = 0; token[i] != '\0'; ++i) {
                output[output_index++] = token[i];
            }   // add multi-digit-number
            output[output_index++] = ' ';
        } else if (strcmp(token, "(") == 0) {
            push('(');
        } else if (strcmp(token, ")") == 0) {
            while (!is_empty() && top() != '(') {
                output[output_index++] = pop();
                output[output_index++] = ' ';
            }
            if (!is_empty() && top() == '(') {
                pop();
            }
        } else {
            process_operator(token[0], output, &output_index);
        }
        token = strtok(NULL, " ");
    }

    handle_remaining_operators(output, &output_index);
    output[output_index - 1] = '\0'; // remove the trailing space

    printf("Postfix expression: %s\n", output);

    return 0;
}
```

#### Reference

https://en.wikipedia.org/wiki/Shunting_yard_algorithm