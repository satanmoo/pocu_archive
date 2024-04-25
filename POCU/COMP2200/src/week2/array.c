#include<stdio.h>

int main(void) {
    int nums[5] = { 1, 2, 3, 4, 5 };
    size_t i;
    int sum;

    for (i = 0; i < 5; ++i) {
	nums[i] += i * 2;
    }

    sum = 0;
    for (i = 0; i < 5; ++i) {
	sum += nums[i];
    }

    printf("%d", sum);

    return 0;
}
