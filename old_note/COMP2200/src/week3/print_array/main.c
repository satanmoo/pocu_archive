#include <stdio.h>
#include "print_array.h"

int main(void) {

    int* arr[5]; /* 여기에 const 포인터로 선언하면 어떻게 될까 각각 int*가 const효과를 받아서 아래에서 arr[0] = nums1과 같은 대입을 할 수 없다. */
    const size_t lengths[5] = { 3, 3, 4, 0, 3};
    int nums1[] = { 1, 2, 3 };
    int nums2[] = { 11, 22, 33};
    int nums3[] = { 111, 222, 333, 444};
    int nums4[] = { -1, -2, -3 };

    arr[0] = nums1;
    arr[1] = nums2;
    arr[2] = nums3;
    arr[4] = nums4;
    print_array(arr, 5, lengths);

    return 0;
}
