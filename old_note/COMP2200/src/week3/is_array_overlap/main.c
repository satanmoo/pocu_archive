#include <stdio.h>
#include "memory.h"

int main(void) {
    
    int arr1[] = { 3, 4, 5 };
    int arr2[] = { 1231, 1234314, 225, 234};
    int* arr3 = arr1 + 2;

    if (is_overlap(arr1, 3, arr2, 4)) {
	printf("noop\n");
    }
    
    if (is_overlap(arr1, 3, arr3, 10)) {
	printf("ok\n");
    }

    return 0;
}
