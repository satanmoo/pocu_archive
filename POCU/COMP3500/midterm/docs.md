# midterm

## Topics

### 훌륭한 알고리듬이 갖춰야할 자질

- 입쳑과 출력이 명확히 정의
- 알고리즘의 각 단계가 명확해야 한다.
- 유한 시간 안에 결과(출력)이 나와야 함

### 알고리듬의 효율성:

- 시간:
    - CPU 속도
- 공간:
    - 메모리 사용량

### 기본 자료구조 시간 복잡도, 공간복잡도:

- 배열,스택,큐,연결리스트 평균,최악 시간복잡도 그냥 똑같음
    - 배열 삽입/삭제에서 밀고 당기기
        - O(n)
    - 스택,큐 탐색 시 모든 요소 다 삭제해서 임시에 보관하고 복원과정
        - O(n)
- 해시 테이블은 평균, 최악 시간 복잡도 다름
    - 해시 충돌 발생하는 상황
        - O(n)
- 공간복잡도 O(N)

### 함수의 시간복잡도, 공간복잡도

- 시간복잡도는 함수의 입력 크기를 기준으로 계산
    - 매개변수의 크기
    - 배열의 경우 배열의 원소 개수

- 공간 복잡도는 추가적인 메모리를 얼마나 할당하는지 기준으로 계산

#### 유형 1

- 상수와 변수의 개념을 구분하기

```java
public static void heyStudents(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < n; k++) {
                for (int m = 0; m < 7; m++) {
                    System.out.println("!");
                }
            }
        }
    }
}
```

- 시간복잡도에 영향을 주는 입력값 변수 n과 상수 (5,7)을 구분하기
- 공간복잡도에 영향을 주는 메모리 추가 할당은 없음
    - 함수 콜스택 1개가 끝

```java
public static int dontWorry(int n, int sum) {
    for (int i = 0; i < n * 150; ++i) {
        for (int j = n; j > 0; j--) {
            sum++;
        }

        for (int k = 0; k < i; ++k) {
            sum++;
        }
    }

    return sum;
}
```

- 루프 변수와 매개 변수의 관계
    - i 값이 n에 영향을 받아 k loop 시간복잡도는 O(n)
    - j 값이 n에 영향을 받음

- 시간 복잡도: O(n^2)
- 공간 복잡도: O(1)

```java
private static void doMagic(final int[] nums) {

    for (int i = 0; i < nums.length; ++i) {

        for (int j = 0; j < nums.length * i; ++j) {
            // um....
        }

        for (int j = 0; j < nums.length * 10000; ++j) {
            // haha...
        }
    }
}
```

- 시간 복잡도: O(nums.length^3)
- 공간 복잡도: O(1)

#### 유형 2

- 재귀 함수 유형
    - 공간 복잡도를 신경 써야함
    - 어떤 매개변수가 콜 스택 수에 영향을 주는지 확인하고, 그를 바탕으로 해답 도출하기

```java
public static int beHappy(int num1, int num2) {
    if (num2 < 2) {
        return num2;
    }

    if (num1 < 1) {
        return num1;
    }

    if (num1 < 10) {
        return beHappy(num1 / num2, num2);
    }

    return beHappy(num1 - 1, num2);
}
```

- 콜 스택 수 만큼 공간복잡도 증가
- 시간 복잡도: O(num1)
- 공간 복잡도: O(num1)

```java
private static int funcRecursive(final int[] nums, final int begin, final int end, final int target) {

    if (begin >= end) {
        return -1;
    }

    int mid = (begin + end) / 2;

    if (nums[mid] == target) {
        return mid;
    }

    int left = funcRecursive(nums, begin, mid - 1, target);
    int right = funcRecursive(nums, mid + 1, end, target);

    if (left > right) {
        return left;
    }

    return right;
}
```

- 시간 복잡도: O(nums.length)
    - 시간 복잡도의 본질은 점화식을 세우는 것
- 공간 복잡도: O(log(nums.length))
    - 공간 복잡도의 본질은 추가 메모리 할당
    - 콜 스택의 깊이는 절반으로 나눌 때 하나씩 증가함

#### 유형 3

- 유형1 + 유형2
    - 재귀함수와 루프의 조합
    - 재귀 콜스택은 시간, 공간에 모두 영향주지만, 루프는 시간에만 영향을 줌

```java
public static int doMagic(int n) {
    int sum = 0;

    for (int i = 0; i < n; ++i) {
        sum += i;
    }

    if (n <= 0) {
        return 1;
    }

    return sum + doMagic(n - 1);
}
```

- 시간: O(n^2)
- 공간: O(n)

```java
private static void funcRecursive(final int[] nums, final int length) {

    if (length == 0) {
        return;
    }

    funcRecursive(nums, length - 1);

    for (int i = 0; i < nums.length; ++i) {
        // do something
    }
}
```

- 시간: O(nums.length * length)
- 공간: O(length)

#### 유형 4 (예상)

- 함수 내부에서 콜 스택이 아닌 추가 메모리를 할당하는 경우

```java
private static void doMagic(final int[] nums) {
    final int[] temp = new int[nums.length];

    for (int i = 0; i * i < nums.length * nums.length; ++i) {
        // do something
    }
}
```

- 시간 복잡도: O(nums.length)
    - temp 할당하고 모든 요소 0으로 초기화 O(nums.length)
    - loop 수가 O(nums.length)
- 공간 복잡도: O(nums.length)
    - temp 할당

```java
public static int[] allocateArrays(int n) {
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
        // Allocate a temporary array of size n in each iteration.
        int[] temp = new int[n];
        result[i] = temp[0]; // do something trivial with temp
    }
    return result;
}
```

- 시간 복잡도: O(n^2)
    - result 모든 요소 0으로 초기화 n
    - 매 루프마다 temp 0으로 초기화 n * 루프 n
- 공간 복잡도: O(n)
    - result 할당에서 n
    - 매 루프마다 temp 할당하고 해제해서 최대 n

#### 유형 5 (예상)

- 복잡한 점화식 도출하는 문제
    - Master Theorems

```java
private static void doMagic(final int[] nums) {
    int count = 0;
    for (int i = 0; i < nums.length; i = (nums.length + i) / 2) {

        if (i == (nums.length + i) / 2) {
            break;
        }

        System.out.println(++count);
    }
}

```

- 시간복잡도: O(log(nums.length))
    - n == nums.length 라고 표기
    - i == n 일 때 종료
    - dk = n - ik 로 정의
        - k loop에서 n(종료조건)과 i값의 차이
    - 귀납적으로 풀어보면
        - d0: n
        - d1: n/2
        - d2: n/4
        - d3: n/8
        - ...
        - dk+1 == dk / 2 점화식이 성립함
        - 따라서 loop 종료까지 d 항이 절반씩 줄어드는 개념
        - k(루프 수)와 d의 관계가 log 함수 관계
- 공간복잡도: O(1)
    - 추가적인 메모리 할당이 없음

```java
public static void doMagic(int N) {
    if (N == 0) {
        return;
    }

    for (int i = 0; i < 5; ++i) {
        doMagic(N - 1);
    }
}
```

- 시간: O(5^N)
    - 콜스택의 깊이와 반복문 모두 고려
    - T(N) = 5 * T(N - 1) 점화식 성립
- 공간: O(N)
    - 최대 콜스택 깊이

### 재귀 함수

#### 유형 1

- 꼬리 재귀 판별
- 꼬리 재귀로 변경
- acc 변수를 매개변수로 넘기는 식으로 정형화 되어있음

```java
public static int foo(int num) {
    if (num < 5) {
        return 10 - num;
    }

    int x = foo(num - 1) - num;

    return x;
}
```

```java
public static int foo(int num) {
    return fooTail(num, 0);
}

private static int fooTail(int num, int acc) {
    if (num < 5) {
        return (10 - num) + acc;
    }
    return fooTail(num - 1, acc - num);
}
```

```java
public static int bar(String str) {
    int length = str.length();

    if (length <= 1) {
        return length;
    }

    String subStr = str.substring(1);

    return length + bar(subStr);
}
```

```java
public static int bar(String str) {
    return barTail(str, 0);
}

public static int barTail(String str, int acc) {
    int length = str.length();

    if (length <= 1) {
        return length + acc;
    }

    String subStr = str.substring(1);

    return bar(subStr, acc + length);
}
```

#### 유형 2

- 개념 묻는 문제
- 재귀 함수의 단점과 꼬리 재귀 최적화의 연결
    - 스택 오버 플로우 방지

#### 유형 3

- 재귀를 반복문으로 변경하는 문제

- quick sort Lomuto partition
- 변경 전

```java
public static void quickSort(int[] nums) {
    quickSortRec(nums, 0, nums.length - 1);
}

private static void quickSortRec(int[] nums, int left, int right) {
    if (left >= right) {
        return;
    }

    int pivotIndex = partition(nums, left, right);
    printArray(nums);
    quickSortRec(nums, left, pivotIndex - 1);
    quickSortRec(nums, pivotIndex + 1, right);
}

private static int partition(int[] nums, int low, int high) {
    int pivot = nums[high];
    int i = low - 1;

    for (int j = low; j < high; ++j) {
        if (nums[j] <= pivot) {
            ++i;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    int pivotIndex = i + 1;
    nums[high] = nums[pivotIndex];
    nums[pivotIndex] = pivot;
    return pivotIndex;
}
```

- 변경 후

```java
public static void quickSort(int[] nums) {
    quickSortLoop(nums);
}

private static class Range {
    int left;
    int right;

    private Range(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

private static void quickSortLoop(int[] nums) {
    final Stack<Range> stack = new Stack<>();
    stack.add(new Range(0, nums.length - 1));

    while (!stack.isEmpty()) {
        Range range = stack.pop();

        if (range.left >= range.right) {
            continue;
        }

        int pivotIndex = partition(nums, range.left, range.right);
        printArray(nums);
        stack.push(new Range(range.left, pivotIndex - 1));
        stack.push(new Range(pivotIndex + 1, range.right));
    }
}

private static int partition(int[] nums, int low, int high) {
    int pivot = nums[high];
    int i = low - 1;

    for (int j = low; j < high; ++j) {
        if (nums[j] <= pivot) {
            ++i;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    int pivotIndex = i + 1;
    nums[high] = nums[pivotIndex];
    nums[pivotIndex] = pivot;
    return pivotIndex;
}
```

- 시험 준비:
    - 재귀 함수로 구현하는 코드 보기에서 풀 만한 것들 찾아보기
        - 이진 탐색
    - GPT 생성

#### 유형 4

- 최대 스택 프레임 깊이 계산
- 최대에 집중하자

```java
public static void puff(int n, int a, int b) {
    if (n <= 0) {
        return;
    }

    System.out.println(String.format("%d %d %d", n, a, b));

    puff(n - 3, a, b + n);
    puff(n - 1, b, a + n);
    puff(n - 2, b, a + n);
}
```

- puff(4, 6, 4) 호출
- caller 포함해서 6

```java
private static int fibonacciRecursive(int n) {

    if (n <= 1) {
        return n;
    }

    return fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1);
}
```

- fibonacciRecursive(7) 호출
- caller 포함해서 8

```java
private static void foo(int n, int k) {
    if (n <= 0 || k <= 0) {
        return;
    }

    if (n % 2 == 0) {
        foo(n, k - 1);
    }

    foo(n - 1, k);
}
```

- foo(5, 5) 호출
- caller 포함해서 11

```text
1.	External caller (depth 1)
2.	foo(5,5) (depth 2)
3.	foo(4,5) (depth 3)
4.	foo(4,4) (depth 4)
5.	foo(4,3) (depth 5)
6.	foo(4,2) (depth 6)
7.	foo(4,1) (depth 7)
8.	foo(4,0) (depth 8) returns → back to depth 7 → call foo(3,1) (depth 8)
9.	foo(3,1) calls foo(2,1) (depth 9)
10.	foo(2,1) calls foo(2,0) (depth 10) returns → still depth 9 → calls foo(1,1) (depth 10)
11.	foo(1,1) calls foo(0,1) (depth 11)
```

```java
private static void doMagic(int n) {

    if (n <= 0) {
        return;
    }

    for (int i = 0; i < n; ++i) {
        doMagic(n - 1);
    }
}
```

- doMagic(3)
- caller 포함 5

### P vs NP

#### 유형 1

- 현재 관계를 묻는 문제
- NP는 P를 포함함
- P가 아닌 것도 NP에 포함됨
- NPC 는 P와 베타적임
- NP중 일부는 NPC임
- NP-hard와 NP의 교집합은 NPC
    - NP-hard 중 일부는 NP와 겹침
    - NPC와 P는 베타
    - NP-hard도 P와 베타

- NP 문제 G가 있다면
    - G 문제를 푸는 다항식 시간 알고리듬이 존재하지 않는다?
        - 거짓
        - P 일 수도
    - G 문제를 결정론적으로 다항식 시간안에 풀 수 있다면?
        - P = NP = NPC 성립
        - 이를 포함하는 NP-hard
    - G 문제가 NP-hard라면, G는 NP에 속하기 때문에 NP와 NP-hard의 교집합은 NPC

#### 유형 2

- P == NP 증명
- NPC 하나라도 다항식 시간 안에 풀면 됨
- P와 NPC의 베타 관계 사라짐

### 이진 탐색

#### 유형 1

- 이진 탐색 구현

```java
private static int binarySearch(int[] arr, int val, int left, int right) {
    if (left > right) {
        return -1;
    }

    int mid = (left + right) / 2;

    if (arr[mid] == val) {
        return mid;
    }

    if (arr[mid] > val) {
        return binarySearch(arr, val, left, mid - 1);
    }
    return binarySearch(arr, val, mid + 1, right);
}
```

- 정렬된 배열에서 삽입, 삭제

```java
private static void insertTo(int[] arr, int val, int lastIndex) {
    int index = findIndexRec(arr, val, 0, lastIndex);
    for (int i = lastIndex; i >= index; --i) {
        arr[i + 1] = arr[i];
    }
    arr[index] = val;
}

private static void deleteFrom(int[] arr, int val, int lastIndex) {
    int index = findIndexRec(arr, val, 0, lastIndex);
    for (int i = index; i < lastIndex; ++i) {
        arr[i] = arr[i + 1];
    }
    arr[lastIndex] = 0;
}

private static int findIndexRec(int[] arr, int val, int left, int right) {
    if (arr[left] > val) {
        return left;
    }
    if (arr[right] < val) {
        return right + 1;
    }
    int mid = left + (right - left) / 2;
    if (arr[mid] == val) {
        return mid;
    }
    if (arr[mid] < val) {
        return findIndexRec(arr, val, mid + 1, right);
    }
    return findIndexRec(arr, val, left, mid - 1);
}
```

#### 유형 2

- 회전된 배열에서 이분 탐색
- 논리 암기
- 정렬 순서가 바뀔 수 있음

```java
public static int indexOfRotatedArray(int[] numbers, int start, int end, int num) {
    if (start > end) {
        return -1;
    }

    int mid = (start + end) / 2;
    if (numbers[mid] == num) {
        return mid;
    }

    if (numbers[start] <= numbers[mid]) {
        if (numbers[start] <= num && num <= numbers[mid]) {
            return indexOfRotatedArray(numbers, start, mid - 1, num);
        }

        return indexOfRotatedArray(numbers, mid + 1, end, num);
    }

    if (numbers[mid] <= num && num <= numbers[end]) {
        return indexOfRotatedArray(numbers, mid + 1, end, num);
    }

    return indexOfRotatedArray(numbers, start, mid - 1, num);
}
```

### 정렬

#### 유형 1

- 정렬 구현

```java
public static void bubbleSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
        for (int j = 0; j < nums.length - 1 - i; ++j) {
            if (nums[j] > nums[j + 1]) {
                int temp = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = temp;
            }
        }
    }
}
```

```java
public static void selectionSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; ++i) {
        int j = findMin(nums, i);
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

private static int findMin(int[] nums, int start) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;

    for (int i = start; i < nums.length; i++) {
        if (nums[i] < min) {
            min = nums[i];
            minIndex = i;
        }
    }

    return minIndex;
}
```

```java
public static void insertionSort(int[] nums) {
    for (int i = 1; i < nums.length; ++i) {
        int value = nums[i];
        int j = i - 1;
        while (j >= 0 && value < nums[j]) {
            nums[j + 1] = nums[j];
            --j;
        }
        nums[j + 1] = value;
    }
}
```

```java
// Romuto Partition
public static void quickSort(int[] nums) {
    quickSortRec(nums, 0, nums.length - 1);
}

private static void quickSortRec(int[] nums, int left, int right) {
    if (left >= right) {
        return;
    }

    int pivotIndex = partition(nums, left, right);
    quickSortRec(nums, left, pivotIndex - 1);
    quickSortRec(nums, pivotIndex + 1, right);
}

private static int partition(int[] nums, int low, int high) {
    int pivot = nums[high];
    int i = low - 1;

    for (int j = low; j < high; ++j) {
        if (nums[j] < pivot) {
            ++i;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    int pivotIndex = i + 1;
    nums[high] = nums[pivotIndex];
    nums[pivotIndex] = pivot;
    return pivotIndex;
}
```

```java
public static void quickSort2(int[] nums) {
    quickSortRec(nums, 0, nums.length - 1);
}

private static void quickSortRec2(int[] nums, int left, int right) {
    if (left >= right) {
        return;
    }

    int pivotIndex = partition2(nums, left, right);
    quickSortRec(nums, left, pivotIndex - 1);
    quickSortRec(nums, pivotIndex, right);
}

private static int partition2(int[] nums, int low, int high) {
    int pivot = nums[(low + high) / 2];
    int i = low - 1;
    int j = high + 1;

    while (true) {
        do {
            ++i;
        } while (nums[i] < pivot);

        do {
            --j;
        } while (nums[j] > pivot);

        if (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        } else {
            break;
        }
    }

    return j;
}
```

```java
public static int[] mergeSort(int[] nums) {
    if (nums.length <= 1) {
        return nums;
    }

    int mid = nums.length / 2;
    int[] left = Arrays.copyOfRange(nums, 0, mid);
    int[] right = Arrays.copyOfRange(nums, mid, nums.length);

    int[] res = mergeArray(mergeSort(left), mergeSort(right));
    printArray(res);
    return res;
}

private static int[] mergeArray(int[] arr1, int[] arr2) {
    int[] result = new int[arr1.length + arr2.length];

    int i = 0, j = 0, k = 0;
    while (i < arr1.length && j < arr2.length) {
        if (arr1[i] < arr2[j]) {
            result[k++] = arr1[i++];
        } else {
            result[k++] = arr2[j++];
        }
    }

    while (i < arr1.length) {
        result[k++] = arr1[i++];
    }

    while (j < arr2.length) {
        result[k++] = arr2[j++];
    }

    return result;
}
```

#### 유형 2

- Bubble:
    - 시간 평균: O(N^2)
    - 시간 최악: O(N^2)
    - 공간: O(1)
    - 안정성 YES
- Selection:
    - 시간 평균: O(N^2)
    - 시간 최악: O(N^2)
    - 공간: O(1)
    - 안정성 NO
- Insertion:
    - 시간 평균: O(N^2)
    - 시간 최악: O(N^2)
    - 공간: O(1)
    - 안정성 YES
- Quick:
    - 시간 평균: O(NlogN)
    - 시간 최악: O(N^2)
    - 공간: O(logN)
    - 안정성 NO
- Merge:
    - 시간 평균: O(NlogN)
    - 시간 최악: O(NlogN)
    - 공간: O(N)
    - 안정성 YES
- Heap:
    - 시간 평균: O(NlogN)
    - 시간 최악: O(NlogN)
    - 공간: O(1)
    - 안정성 NO

#### 유형 3

- 정렬 과정에서 데이터 위치 변화 시뮬레이션

#### 유형 4

- edge case 데이터 생성하기

##### 선택 정렬 안정성 깨지는 케이스

- {30a, 20a, 30b, 20b}
- {20a, 30a, 30b, 20b}
- {20a, 20b, 30b, 30a}

##### 퀵 정렬 romuto partition(가장 오른쪽 요소 pivot) 안정성 깨지는 케이스

- {7a, 7b}

- pivot: 7b
- 7a는 7b보다 작지 않기 때문에 skip
- 정렬 결과:
    - {7b, 7a}

##### 힙 정렬 안정성 깨지는 케이스

- [4a, 1, 4b, 3, 4c]

```text
        4a
       /  \
      4c   4b
     / \
    1   3
```

```text
        
       / \
      4c  4b
     / \
    1   3
         
        3   
       / \
      4c  4b
     /
    1
    
        4c  
       / \
      3  4b
     /
    1

[4a]
```

```text
         
       / \
      3  4b
     /
    1
    
        1  
       / \
      3  4b

        4b  
       / \
      3  1

[4c, 4a]
```

```text
        
       / \
      3  1
      
        1 
       / 
      3
      
        3
       / 
      1

[4b, 4c, 4a]
```

```text
    1

[3, 4b, 4c, 4a]
```

```text
[1, 3, 4b, 4c, 4a]
```

```text
        4a
       /  \
      4c    4b
     / \
    1   3
```

##### 퀵 정렬 hoare partition 안정성 깨지는 케이스

- {7a,7b,7c}
- pivot: 7b
- 정렬 결과:
    - {7c,7b,7a}

#### 유형 5

- 퀵소트 romuto partition(가장 오른쪽 요소 pivot) 최악의 경우 데이터 생성하기
    - {2, 1, 3};

#### 유형 6

- heap sort tree 시뮬레이션

```text
7,2,5,1,3,7,4,8,6
```

- 주어진 순서대로 삽입해서 힙 구성하기
    - 시간복잡도 O(N)

```text
7
```

```text
    7
   / \
  2  
```

```text
    7
   / \
  2   5  
```

```text
            7
           / \
          2   5
         /
        1  
```

```text
            7
           / \
          3   5
         / \  
        1   2  
```

```text
            8
           / \
          /   \
         7     7
        / \   / \
       6   2 5   4
      / \
     1   3
```

- 구성한 힙에서 모든 요소를 삭제하기
    - 시간복잡도는 요소마다 O(logN), 요소 개수는 N 개
    - 총 O(NlogN)

```text
            
           / \
          /   \
         7     7
        / \   / \
       6   2 5   4
      / \
     1   3
                    
            3       
           / \
          /   \
         7     7
        / \   / \
       6   2 5   4
      /
     1   
     
            7       
           / \
          /   \
         6     7
        / \   / \
       3   2 5   4
      /
     1   
     
[8]
```

```text
                           
           / \
          /   \
         6     7
        / \   / \
       3   2 5   4
      /
     1   
            1      
           / \
          /   \
         6     7
        / \   / \
       3   2 5   4
       
       
            7      
           / \
          /   \
         6     1
        / \   / \
       3   2 5   4
       
            7      
           / \
          /   \
         6     5
        / \   / \
       3   2 1   4
                     
[7,8]
```

```text
                 
           / \
          /   \
         6     5
        / \   / \
       3   2 1   4

            4      
           / \
          /   \
         6     5
        / \   /
       3   2 1   
       
            6      
           / \
          /   \
         4     5
        / \   /
       3   2 1  
       
[7,7,8]
```

```text
                 
           / \
          /   \
         4     5
        / \   /
       3   2 1  

            5     
           / \
          /   \
         4     1
        / \ 
       3   2 
       
[6,7,7,8]
```

```text
            5     
           / \
          /   \
         4     1
        / \ 
       3   2 
       
            2
           / \
          /   \
         4     1
        / 
       3
       
            4
           / \
          /   \
         2     1
        / 
       3
       
            4
           / \
          /   \
         3     1
        / 
       2
       
[5,6,7,7,8]
```

```text
           / \
          /   \
         3     1
        / 
       2
       
            2   
           / \
          /   \
         3     1 
        
            3   
           / \
          /   \
         2     1 
       
[4,5,6,7,7,8]
```

```text
            
           / \
          /   \
         2     1 
            
            1            
           /
          /   
         2     
         
            2           
           /
          /   
         1     
       
[3,4,5,6,7,7,8]
```

```text
         
        1

[2,3,4,5,6,7,7,8]     
```

```text
[1,2,3,4,5,6,7,7,8]     
```

#### 유형 7

- 힙에서 루트말고 다른 노드를 삭제할 때 시뮬레이션

```text
      23
     /  \  
    /    \
   20    21
  /  \  /
 16  1  19
```

- 이 최대 힙에서 20 삭제하기

```text
      23
     /  \  
    /    \
   19    21
  /  \  
 16  1
```

- 21 삭제하기

```text
      23
     /  \  
    /    \
   19    1
  /  
 16
```

- 가장 마지막 요소(리프 중 가장 오른쪽)과 교환하고 힙 규칙에 맞게 교환하면서 내려보내기
- "완전 이진 트리"의 성질을 유지해야함

### 비암호학적 해시 함수

#### 유형 1

- 개념 주관식 유형
    - 해커가 데이터베이스에서 파일을 훔쳐가도 비밀번호를 cracking 하기 힘들게 만드는 방법에 대해 설명하기
        - 데이터베이스에 해시값으로 비밀번호를 저장한다.
    - 비암호학적 해시 함수에 속하는 예
        - 체크섬
            - CRC
    - 비암호학적 해시 함수의 용도들
        - 해시 테이블에서 데이터를 저장할 위치(버킷의 인덱스)를 찾기 위함
        - 길이가 긴 데이터 둘을 빠르게 비교하기 위해
            - 단 다른지만 확인이 가능하다. 왜냐하면 함수의 성질 때문
            - 해시값이 같은 두 데이터 원본이 반드시 같지 않다. 해시충돌 때문
        - 고유한 ID값 생성
    - 해시 함수의 정의
        - 임의의 크기를 가진 값을 고정된 크기의 값에 대응시키는 함수
    - 균일성의 정의
        - 해시 함수의 출력값이 고르게 분포되는 정도
        - 균일성 값이 0.95 ~ 1.05 사이면 균일한 분포를 가진 해시 함수라고 본다.
    - 균일성을 높이는 방법
        - 눈사태 효과가 나도록 해시 함수 설계
        - 버킷의 수를 소수로 설정
    - 눈사태 효과의 정의
        - 입력값의 변화가 출력값의 변화에 큰 영향을 주는 효과
    - 눈사태 효과를 암호학적 알고리듬에서 선호하는 이유
        - 알고리듬의 규칙을 유추하기 어렵기 때문
    - 완벽한 눈사태 기준(strict avalanche criterion, SAC)
        - 입력값에서 1비트를 뒤집으면 출력값의 각 비트가 뒤집힐 확률이 50%
    - 지역 민감 해시의 정의
        - 해시 충돌의 최대화를 목표로하는 알고리듬
    - 지역 민감 해시의 용도
        - 스팸 메일 찾기
        - 음원, 사진 등의 저작권 검사
        - 웹 검색 엔진에서 비슷한 문서 추천하기
    - 일반적으로 저장 공간(메모리)를 더 낭비해도 효율성이 높은 해시 함수를 선호함
        - CPU가 메모리보다 더 비싼 자원이기 때문
    - 해시 충돌이 좀 더 나더라도 더 빠른 함수를 선호함
        - 해시 충돌로 생기는 비용(시간 복잡도 O(N)으로 증가)보다 매번 해시값을 계산하는 비용이 빈도를 따졌을 때 더 큼.
    - 효율성이 좋지 않은 해시 함수를 선호하는 경우
        - 암호학적 해시 함수에서 하드웨어 가속이 어려운 해시를 선호
    - 비트 패킹은 해시 함수라고 볼 수 있다.
    - 유니버셜 해싱의 정의
        - 입력값에 따라 다른 해시 함수를 사용하는 확률적 알고리듬
        - 해시 함수의 집합을 정의하고 그 집합에서 임의의 해시 함수를 선택하는 방식으로 동작
    - 올바른 해시 함수를 고르는 법
        - 실제 데이터로 속도, 해시 충돌 수, 메모리, 균일성을 테스트
        - 구글 검색
    - 체크섬의 정의
        - 여러 데이터로부터 도출한 작은 크기의 데이터
        - 해시 함수의 일종
    - 체크섬의 용도
        - 저장 혹은 전송 중에 발생한 오류를 찾기 위함
        - 주민번호 위조 여부 검사
        - 신용카드 유효성 검사
        - ISBN 유효성 검사
    - 체크섬은 오류 복구를 지원하지 않는다.
    - 오류 정정 코드는 데이터 복구를 지원한다.
    - 체크섬과 미러 사이트
        - 유용한 프로그램을 매우 많은 사람이 다운로드해서 웹 사이트에서 트래픽 감당이 안 되는 상황
        - 미러 사이트에서 파일을 호스팅
        - 미러 사이트에서 스파이웨어를 추가하는 것을 확인하도록 원래 사이트에서 체크섬을 배포
        - 미러 사이트에서 배포한 체크섬으로 미러 사이트에서 다운로드 받은 파일 유효성 검사는 당연히 의미가 없음
    - 패리티 비트 정의
        - 이진수로 저장된 데이터에 추가하는 1비트짜리 체크섬
        - 보통 1바이트 단위로 많이 사용함(1비트 체크섬 + 7비트 원본)
    - CRC는 체크섬 알고리듬의 일종이다.
    - CRC 검사값은 해시값으로 볼 수 있다.
    - CRC 다항식의 최고차항에 따라 검사값의 크기가 달라진다.
    - 암호학적 해시 알고리듬의 사용 예
        - 원본을 복원하지 않는 것이 핵심
        - 누출되면 곤란한 데이터의 원본을 저장하지 않기 위해
        - 디지털 서명
            - from + to + amount를 조합해서 SHA-256 해시로 송금 디지털 서명 만들기
        - 비밀번호 검증
            - 로그인할 때 디비에 저장된 해시값과 입력한 비밀번호를 암호학적 해시함수에 입력해 나온 결과를 비교
        - 블록체인 작업증명(Proof Of Work)
            - 블록 헤더는 다양한 필드로 구성되어 있는데, 그 중 "Nonce"라는 필드는 채굴자가 목표 해시값을 만족하기 위해 시도한 수를 의미한다.
            - 채굴자가 작업하면 "Nonce" 값이 변하고, 채굴자는 블록 헤더를 입력으로 SHA-256 같은 암호학적 해시 알고리듬으로 해시값을 구한다.
            - 구한 해시값이 목표 해시값과 다르면 채굴자는 더 시도해 "Nonce" 필드 값을 변화시키고, 다시 검증하고...
        - 메시지나 파일의 무결성 검사
            - 체크섬을 구할 때 SHA-256 같은 암호학적 해시 알고리듬으로 구한다.
            - 미러 사이트와 함께 파일을 호스팅할 때 원본 사이트에서 암호학적 해시 알고리듬으로 생성한 체크섬도 배포해 위조 여부를 확인할 수 있게 만든다.
    - 암호학적 해시 알고리듬의 추가 속성
        - 역상 저항성
        - 제2 역상 저항성
        - 충돌 저항성
    - 역상 저항성의 정의
        - 해시값에서 부터 원본값을 찾기 어려운 정도
    - 역상 저항성이 강하면 무차별 대입 공격으로 원본값을 찾을 수 밖에 없고, 이는 보안이 강력함을 의미한다.
    - 제2 역상 저항성의 정의
        - 똑같은 해시값을 출력하는 다른 입력값을 찾기 어려운 정도
        - 어떤 원본-해시값의 쌍을 가지고 있다고 가정할 때, 해시값을 출력하는 다른 입력값을 찾는 것이 제2 역상 공격
    - 충돌 저항성 정의
        - 해시값이 똑같은 두 입력값을 찾기 어려운 정도
        - 해시값도 입력값도 주어지지 않은 경우 해시값이 똑같은 두 입력값을 찾는 것이 충돌 공격
    - 생일 문제
        - 집단에서 생일이 9월 2일인 사람이 존재하는지 찾는 공격 == 역상 공격
        - 집단에서 생일이 겹치는 사람이 존재하는지 찾는 공격 == 충돌 공격
    - 모든 암호학적 해시 함수에서 생일 문제가 발생하는 근본적인 이유
        - 출력의 길이가 고정되어 있음(0101~1231)
        - 해시 함수의 정의가 고정된 출력의 크기를 갖는 것이기 때문에 피할 수 없음
    - 생일 문제에서 얻을 수 있는 교훈
        - 해시 함수 출력의 크기가 클 수록 안전하다.
    - 비밀번호가 길면 보안에 좋은 이유
        - 웹 환경에서 무차별 공격은 비밀번호 5회 틀리면 계정 잠금, 네트워크 등 제약 때문에 어려움
        - 비밀번호 해시값이 해커의 로컬에 있을 때 하드웨어 가속으로 무차별 대입 공격을 시도할 수 있다. 이 때 비밀번호가 길 수록 공격에 소모되는 비용(시간)이 증가한다.
    - 사전 공격
        - 사전에 있는 단어를 조합해서 무차별 공격의 경우의 수를 줄이는 방법
        - 사전 말고 랜덤 생성된 해시값(브라우저 자동 생성)을 비밀번호로 사용하면 사전 공격을 방지할 수 있다.
    - 레인보우 테이블의 정의
        - 특정 암호학적 알고리듬에 대한 해시값-원본값 쌍을 저장한 테이블
        - 원본값은 흔히 사용하는 비밀번호들 (e.g. abc12345)
        - 시간복잡도 O(1)
    - 사용자 입장에서 비밀번호 덜 털리는 법
        - 이미 털린 비밀번호(레인보우 테이블의 원본값)에 속하는 비밀번호를 설정 못하게 하기
        - 긴 비밀번호 사용하게 강제하기
        - 비밀번호를 저장하지 않고, 소셜 로그인이나 이메일 링크로 로그인 하게 하는 법을 사용
    - 솔트
        - 데이터베이스에 저장하는 랜덤 문자열
        - 비밀번호의 길이를 길게 만드는 효과가 있음
        - 사용자마다 각각 다른 문자열을 저장함
        - 레인보우 테이블을 파훼
    - 페퍼
        - 데이버테이스에 저장하지 않고, 웹 서버만 알고 있는 공통의 문자열
        - 비밀번호의 길이를 길게 만드는 효과가 있음
        - 레인보우 테이블 파훼
        - 데이터베이스에 저장하지 않기에 해커가 볼 수 없음(그래서 까만 후추)

#### 유형 2

- xor8 체크섬 계산
- 데이터의 각 바이트에 대해 xor 연산을 연쇄적으로 적용해 최종 출력은 1바이트 체크섬
- input: [0x1A, 0x2B, 0x3C, 0x4D]
- 초기값: 0x00
    - 첫번째 바이트:
        - 0000 0000
        - 0001 1010
        - 0001 1010
    - 두번째 바이트:
        - 0001 1010
        - 0010 1011
        - 0011 0001
    - 세번째 바이트:
        - 0011 0001
        - 0011 1100
        - 0000 1101
    - 네번째 바이트:
        - 0000 1101
        - 0100 1101
        - 0100 0000
- output:
    - 0x40

#### 유형 3

- 패리티 비트 구하는 문제
- 패리티 비트로 원본이 뒤집혔는지 확인하는 문제

- 000 0000:
    - 홀수 패리티: 1
        - 패리티를 포함한 데이터: 000 0000
    - 짝수 패리티: 0
        - 패리티를 포함한 데이터: 0000 0000
- 010 1100:
    - 홀수 패리티: 0
        - 패리티를 포함한 데이터: 0010 1100
    - 짝수 패리티: 1
        - 패리티를 포함한 데이터: 1010 1100
    - 1010 1100 데이터를 수신했는데, 홀수 패리티가 1이면 데이터가 변조됬음을 의미함
- 111 1111:
    - 홀수 패리티: 0
    - 짝수 패리티: 1

#### 유형 4

- 다항식이 주어지고, CRC-3-GSM 계산 문제

- 다항식: x^3 + x + 1
- 원본 메시지: 10 1010 0101 1001

- 다항식을 이진수로 표현하면?
    - 011
    - 최고차항의 계수는 항상 1이라서 생략

- 체크섬 계산
- 10 1010 0101 1001 + 000
- 다항식을 이진수로 표현했을 때 비트 수만큼 0을 추가
    - 최고차수 - 1 만큼 0을 추가

- xor 연산 반복

```text
1.
10101001011001 000
1011
00011001011001 000

2.
00011001011001 000
   1011
00001111011001 000

3.
00001111011001 000
    1011
00000100011001 000

4.
00000100011001 000
     1011
00000001111001 000

00000001111001 000
       1011
       0100001 000
       
5.
0100001 000
 1011
 001101 000
 
6.
1101 000
1011
0110 000

7.
110 000
101 1
011 100

8.
011 100
 10 11
 01 010
 
9.
01 010
 1 011
 0 001
 
원본 비트 모두 0이됬으니 종료
체크섬: 001  
```

#### 유형 5

- 비트 패킹

```java
public class Participant {
    public byte age;
    public byte order;

    @Override
    public int hashCode() {
        return this.age << 8 | this.order;
    }
}
```

### 암호화

#### 유형 1

- 상황 해석해서 해시 vs 암호화 중 무엇을 사용하는지?
    - 정찰병이 적군의 동향에 대한 정보를 본부에 보내려고 한다.
        - 공개 키 암호화
        - 복호화할 수 있어함
    - 스팸 이메일 감지
        - 해시
        - 지역 민감 해시
    - 암호 화폐 송금 전자 서명
        - 비대칭 키 암호화
        - 키를 만들 때 암호학적 해시 사용하긴 함
    - 비밀번호를 DB에 저장
        - 해시
        - 원본을 복호화할 필요가 없음
    - 블록체인 작업 증명
        - 암호학적 해시(SHA-256)
        - 헤더를 해시로 변환
    - 메시지나 파일의 무결성 검사
        - 암호학적 해시
        - 체크섬
    - 체크섬
        - 해시
    - CRC
        - 해시
        - 체크섬의 일종
    - 패리티 비트
        - 해시
        - 체크섬의 일종
    - ISBN 유효성 검사
        - 해시
        - 체크섬의 일종
    - 신용카드 번호의 유효성 검사
        - 해시
        - 체크섬의 일종
    - 주민등록 번호의 유효성 검사
        - 해시
        - 체크섬의 일종
    - 비트 패킹
        - 해시
    - 웹 검색 엔진에서 비슷한 문서 추천하기
        - 해시
        - 지역 민감 해시
    - 음원, 사진 등의 저작권 검사
        - 해시
        - 지역 민감 해시
    - 독일군의 애니그마 기계
        - 암호화
    - WIFI
        - 대칭키 암호화
    - 하드 디스크에 저장할 때 원문 복구하게끔 저장
        - 대칭키 암호화
        - 키를 배포할 필요가 없음 (하드 디스크의 주인만 대칭키를 알면 됨)
    - DB에 고객 정보 저장(비밀번호 아님, 주소 같은 개인정보)
        - 대칭키 암호화
        - 사내에서만 대칭키를 알면 됨
    - 사내 인트라넷 통신 암호화
        - 대칭키 암호화
        - 통신 개념이 들어가면 암호화라고 봐야죠
        - 회사 외부로 키를 배포할 필요가 없음
    - HTTPS
        - 비대칭키 암호화
    - 메신저 앱의 비밀 채팅 모드
        - 세션 키를 생성 후 상대방에게 전송할 때는 상대방의 공개 키로 암호화
        - 메시지는 세션 키(대칭 키)로 암화화
        - 대칭 키 암호화가 효율성이 좋기 때문
    - 이메일 암호화
        - 당연히 상대방이 읽을 수 있어야되니 암호화

#### 유형 2

- 개념 주관식
    - 암호화의 정의
        - from 평문 to 암호문
    - 해시 알고리듬과 암호화의 차이점
        - 해시 알고리듬은 원문 복구를 막는 것이 목표임
        - 암호화는 복호화로 원문을 알 수 있어야함
    - 암호학에서 사용하는 정수의 연산의 시간 복잡도는 단순하게 O(1)이 아니다.
        - 32비트, 64비트 기본 정수 자료형보다 훨씬 큼
        - 시간 복잡도는 비트 수에 비례하게 된다.
    - 대칭 키 암호화의 정의
        - 암호화, 복호화에 동일한 키를 사용하는 암호화
    - 대칭 키 암호화의 단점
        - 키를 배포하기 어려움
        - 통신 시 수신자에게 대칭 키를 비밀스럽게 알려주어야 함
    - 스트림 암호
        - 대칭 키 암호화의 일종으로 보통 1바이트 단위로 암호화
        - 블록 암호에 비해 속도가 빨라 스트리밍 서비스 등에 사용 됨
        - 패딩 개념이 없음
        - 각 바이트에 적용하는 키를 난수로 설정
        - 블록 암호에 비해 설정이 복잡함
    - 블록 암호
        - 대칭 키 암호화의 일종으로 정해진 블록 크기(보통 8바이트)를 한 번에 암호화
        - 스트림 암호에 비해 속도가 느리지만, 보안은 강력함
        - 패딩 개념이 존재해서 출력의 크기는 블록 크기의 배수일 수 밖에 없음
        - 각 블록에 적용하는 키는 동일함
        - 스트림 암호에 비해 설정이 간단함
    - WI-FI 비밀번호는 대칭키 암호화
        - 공유기(라우터)와 스마트폰이 통신할 때 비밀번호와 기기 식별자를 조합해 대칭키를 만들고 암호화
        - 카페의 접속자 각각은 서로 다른 키를 사용하게 됨
    - AES 는 블록 암호
        - 블록 크기 128 비트
        - 키 길이는 128, 192, 256로 다양함
        - 키 길이에 따라 라운드 수가 10, 12, 14로 다름
    - AES 알고리듬의 구성
        - 키 확장
        - 라운드 진행
    - AES 알고리듬의 룩업 테이블을 이용한 바이트 대체는 confusion 효과를 줌
        - 선형적인 변환이 아니라 사칙, 비트 연산으로 찾을 수 없음
    - AES 알고리듬의 행 이동은 확산 효과를 줌
    - AES 알고리듬의 열 섞기는 확산 효과를 줌
    - 비대칭 키 암호화의 다른 말
        - 공개 키 암호화
    - 비대칭 키 암호화는 키를 배포하기 위해 고안되었다.
    - 비대칭 키에 정수론 설질을 활용한다.
        - 공개 키로 암호화했을 때, 반드시 비밀 키로만 복호화할 수 있음
        - 공개 키로 암호화하고 동일한 키로 복호화 할 수 없음
    - RSA 는 대표적인 공개 키 암호화 방식
    - 메시지 전송 시
        - 송신자가 메시지를 수신자의 공개 키로 암호화
        - 수신자는 받은 메시지를 자신의 비밀 키로 복호화
    - 전자 서명(블록 체인 송금)
        - 송금자가 자신의 비밀 키로 전자 서명을 암호화
            - 전자 서명을 생성할 때는 SHA-256 같은 암호학적 해시 사용
        - 블록체인 시스템은 공개 키(지갑주소)로 복호화
    - RSA에서 사용하는 소수의 성질
        - 두 소수를 곱한 합성 수에서 역으로 두 소수를 찾을 때 브루트 포스로 엄청난 시간이 걸리는 성질을 이용
        - 10^200 자리 합성수라면 10^100 번 곱셈 실행
        - 우주의 나이보다 오래 걸림
    - RSA 키의 길이가 길 수록 브루트 포스로 풀 수 있는 연산 시간이 오래걸림
    - RSA의 비밀 키는 소수, 공개 키는 합성수
        - 공개 키를 알아아도 비밀 키를 알아내기 매우 힘듬
    - 일반적으로 공개 키 암호화 방식이 대칭 키 암호화 방식에 비해 효율성이 좋지 않다
        - 따라서 비밀 채팅에서 세션 키로 공개 키를 사용
    - 모든 암호화 기법은 언젠가 깨진다
        - 거짓으로 간주
        - 현실적으로 깨기 힘듬
            - RSA의 소수의 성질

#### 유형 2

- AES 패딩 개념
    - 블록 크기:
        - 16 바이트 == 128 비트 고정
    - 키 길이:
        - 3가지 종류:
            - 128, 192, 256
        - 키 길이가 어디에 영향을 주는가?
            - 라운드 수 10, 12, 14
    - 패딩 개념:
        - 블록 크기의 배수로 아웃풋이 결정됨
            - 인풋이 16바이트 초과 32비트 이하면 아웃풋은 32바이트
            - 인풋이 16바이트 이하면 아웃풋은 16바이트

#### 유형 3

- AES 암호화 계산

- 0 라운드:
    - 원문을 블록으로 나누기
    - 블록의 각 바이트를 4 * 4 행렬로 나타내기
    - 라운드 키 더하기
    - 0 라운드 키(주어짐)을 4 * 4 행렬로 나타태기
    - XOR 연산
- 1 ~ 최종 라운드 전:
    - 이전 라운드의 출력을 이번 라운드의 입력으로 사용
    - 바이트 대체: 블록의 각 바이트를 룩업 테이블을 참고해 대체
        - 문제에서 룩업 테이블 주어지면 찾아서 바꾸면 됨
    - 행 이동
        - 1행 이동 없음
        - 2행 1
        - 3행 2
        - 4행 3
    - 열 섞기
        - 벡터 곱을 활용
            - GF(2^8) 행렬과 입력 벡터가 주어지면 연산하면 됨
            - 행렬은 AES 표준에 따라 1,2,3으로 이루어진 정해진 행렬
            - 벡터는 입력 블록을 행렬로 나타낸 후 추출
        - 더하기는 XOR 연산
        - 곱하기는
            - 1을 곱하면 항등원, 원래 값 그대로 유지
            - 2를 곱하면 왼쪽으로 1만큼 비트 시프트, 원래 값의 최고가 1이었다면 0x1B로 xor
            - 3을 곱하면 2를 곱하는 규칙 적용 후 원래의 값을 xor 연산
            - GF(2^8)곱
    - 라운드 키 더하기
        - 라운드 키의 크기는 블록 크기와 동일하게 128비트(16바이트)
- 최종 라운드:
    - 열 섞기 빼고 최종 라운드 전과 동일하게
    - 바이트 대체
    - 행 이동
    - 라운드 키 더하기

- 행 이동 예시

```text
행 이동 예시

c5 df ac a2
39 0e f3 27
a0 d9 cd 34
66 dd 56 02

c5 df ac a2
0e f3 27 39 
cd 34 a0 d9
02 66 dd 56
```

- 열 섞기 예시

```text
2 3 1 1  c5
1 2 3 1  0e
1 1 2 3  cd
3 1 1 2  02
```

- 2행의 값을 구해보면

```
c5 + 2 * 0e + 3 * cd + 02

2 * 0e 
0000 1110
비트 쉬프트하면
0001 1100
맨 앞 비트가 1이 아니라서 끝

3 * cd
1100 1101
비트 쉬프트하면
1001 1010
맨 앞 비트가 1이라서 0x1B xor
1001 1010
0001 1011
1000 0001
여기에 원래 비트 xor
1000 0001
1100 1101
0100 1100

1100 0101
0001 1100
0100 1100
0000 0010
1001 0111
97
```

- AES 복호화
- 암호문, 대칭키를 이용해 역 연산
- 대칭키로 키 확장을 통해 모든 라운드 키 생성
- 암호문을 블록 단위로 나누고 아래 역연산 진행
- 최종 라운드
    - 라운드 키 더하기 역연산
        - XOR의 역연산은 XOR이라 계산은 XOR
    - 행 이동 역연산
    - 바이트 대체 역연산
- 최종 라운드 전 ~ 1라운드
    - 라운드 키 더하기 역연산
    - 열 섞기 역연산
    - 행 이동 역연산
    - 바이트 대체 역연산
- 0라운드
    - 라운드 키 더하기 역연산

### 트리 순회

#### 유형 1

- 트리 개념 주관식
    - 리프의 높이는 0이다.
    - 루트의 깊이는 0이다.
    - 자식이 최대 하나인 트리 == 연결리스트
    - 트리의 용도
        - HTML
        - XML
        - JSON
        - YAML
        - 프로그래밍 언어 추상 구문 트리(abstract syntax tree)
        - 인간의 언어를 나타내는 parsing tree
    - 형제, 자매
        - 같은 부모의 다른 자식
    - 자식은 노드가 참조하는 하위 자식만 포함
        - 손자는 제외

#### 유형 2

- 트리 순회의 활용
    - 중위 표기법
    - 전위 표기법
        - (폴란드 표기법)
    - 후위 표기법
        - 깊은 트리 복사
        - 역 폴란드 표기법

#### 유형 3

- 트리 순회 함수 코드 구현

```java
public static void traversePreOrder(Node node) {
    if (node == null) {
        return;
    }

    // do something
    traversePreOrder(node.left);
    traversePreOrder(node.right);
}

public static void traverseInOrder(Node node) {
    if (node == null) {
        return;
    }

    traverseInOrder(node.left);
    // do something
    traverseInOrder(node.right);
}

public static void traversePostOrder(Node node) {
    if (node == null) {
        return;
    }

    traversePostOrder(node.left);
    traversePostOrder(node.right);
    // do something
}
```

- 트리 깊은 복사 코드 구현

```java
public static Node copyRecursive(final Node node) {
    if (node == null) {
        return null;
    }

    Node newNode = new Node(node.data);
    newNode.left = copyRecursive(node.left);
    newNode.right = copyRecursive(node.right);

    return newNode;
}
```

#### 유형 4

- 전위 표기법(폴란드 표기법) 계산
- 스택에서 pop할 때 먼저 뽑은 피연산자가 좌항

```text
-*A-BC+DE

스택에 E,D push
연산자 + 를 만났기 때문에 스택에서 D,E pop
D + E 를 스택에 push
스택에 C, B push [D+E, C, B]
연산자 - 를 만났기 때문에 스택에서 B, C pop
B - C 를 스택에 push [D + E, B - C]
A 를 스택에 push [D + E, B - C, A]
연산자 * 를 만났기 때문에 스택에서 A, (B-C) pop
A * (B - C) 를 스택에 push [D + E, A * (B - C)]
연산자 - 를 만났기 때문에 스택에서 A * (B - C), D + E pop
A * (B - C) - (D + E) 를 스택에 push [A * (B - C) - (D + E)]
주어진 식을 모두 읽어서 스택에서 pop 하면 A * (B - C) - (D + E)
```

- 후위 표기법(역 폴란드 표기법) 계산
- 스택에서 pop할 때 먼저 뽑은 피연산자가 우항

```text
ABC-*DE+-

스택에 A,B,C push [A, B, C]
연산자 - 를 만났기 때문에 스택에서 C, B pop
나중에 뽑은 B를 좌항으로 B - C 스택에 push [A, B - C]
연산자 * 를 만났기 때문에 스택에서 B - C, A pop
나중에 뽑은 A를 좌항으로 A * (B - C) 스택에 push [A * (B-C)]
스택에 D, E push [A * (B-C), D, E]
연산자 + 를 만났기 때문에 스택에서 E, D pop
나중에 뽑은 D를 좌항으로 D + E 스택에 push[A * (B-C), D+E]
연산자 - 를 만났기 때문에 스택에서 D+E, A * (B-C) pop
나중에 뽑은 A * (B-C)를 좌항으로 (A * (B-C)) - (D+E) 스택에 push [A * (B-C) - (D+E)]
주어진 식을 모두 읽어서 스택에서 pop 하면 A * (B-C) - (D+E)
```

### 이진 탐색 트리

#### 유형 1

- 개념 주관식
    - 이진 탐색 트리 시간/공간 복잡도
        - 탐색, 삽입, 삭제 평균 시간 복잡도:
            - O(logn)
        - 탐색, 삽입, 삭제 최악 시간 복잡도:
            - O(n)
        - 공간 복잡도:
            - O(n)
    - 정렬된 배열은 메모리 한 덩어리
        - 삽입, 삭제 시 쉬프트 연산 때문에 시간 복잡도 O(n)
        - 삽입, 삭제마다 정렬을 한다면 정렬의 시간 복잡도
            - O(n^2)
            - O(nlogn)
    - 노드는 언제나 2개 이하의 자식을 가진다.
        - 2개의 자식만 가진다 >> 거짓
    -

#### 유형 2

- 코드 구현

```java
private static class Node {
    int key;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
    }
}

private static void traverseRec(Node root) {
    if (root == null) {
        return;
    }

    traverseRec(root.left);
    System.out.print(root.key + " ");
    traverseRec(root.right);
}

private static Node insertRec(Node root, int key) {
    if (root == null) {
        return new Node(key);
    }

    if (key < root.key) {
        root.left = insertRec(root.left, key);
    } else {
        root.right = insertRec(root.right, key);
    }

    return root;
}

private static Node deleteRec(Node root, int key) {
    if (root == null) {
        return null;
    }

    if (key < root.key) {
        root.left = deleteRec(root.left, key);
    } else if (key > root.key) {
        root.right = deleteRec(root.right, key);
    } else {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        Node predecessor = findInorderPredecessor(root.left);
        root.key = predecessor.key;
        root.left = deleteRec(root.left, predecessor.key);
    }
    return root;
}
```

### 레드 블랙 트리

- 주관식 개념
    - 레드-블랙 트리는 self-balancing tree
    - 레드-블랙 트리의 특성
        - 노드는 레드 또는 블랙
        - 루트노드는 블랙
        - 레드 노드의 자식은 반드시 블랙
        - 리프 노드는 NIL로 반드시 블랙
        - 어떤 노드에서 모든 블랙 높이는 동일함
            - 어떤 노드와 리프들 사이의 블랙 노드의 개수는 동일함
    - 가장 큰 깊이는 가장 작은 것의 2배를 넘지 않음
        - 탐색,삽입,삭제의 시간복잡도 O(logn) 보장
        - 블랙 높이가 x인 트리를 가정
            - 루트에서 리프의 길이(깊이)가 최소인 경우는 블랙 노드만 x개
            - 루트에서 리프의 길이(깊이)가 최대인 경우는 블랙 노드 x개, 레드 노드 x개
    - 트리 회전 시간복잡도 O(1)
    - 색 바꾸기 시간복잡도 O(1)
    - 트리 삽입할 때 삽입하는 노드의 색은 레드!
