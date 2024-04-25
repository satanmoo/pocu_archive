#include <stdio.h>

void process(int nums[], const size_t n);

int main(void) {
    int i;
    int my_nums[5] = { 0, 1, 2, 3, 4 };
    process(my_nums, 5);
    
    for (i = 0; i < 5; ++i) {
	printf("%d\n", my_nums[i]);
    }

    return 0;
}

void process(int nums[], const size_t n) {
    size_t i;
    for (i = 0; i < n; ++i) {
	nums[i] *= 2;
    }
}
    
