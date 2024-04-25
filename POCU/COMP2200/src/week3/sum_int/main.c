#include <stdio.h>
#include "sum.h"

int main(void) {

    int res1;
    int res2;

    int nums[] = { 1, 2, 3, 4, 5 };

    res1 = sum1(nums, 5);
    res2 = sum2(nums, 5);

    printf("%d %d\n", res1, res2);

    return 0;
}
