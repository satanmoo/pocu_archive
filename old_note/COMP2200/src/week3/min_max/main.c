#include <stdio.h>

#include "my_algorithm.h"

#define LENGTH (5)

int main(void) {
    const int nums[LENGTH] = { 3, 10, 9, 8, 7};
    int min;
    int max;

    get_min_max(nums, LENGTH, &min, &max);

    printf("%d\n", min); 
    printf("%d\n", max);

    return 0;
}
