#include <stdio.h>
#include "speedy_sum.h"

int main(void) {
    
    int res;
    int arr[] = { 1, 2, 3, 4, 5};

    res = sum(arr, arr+5);
    printf("%d", res);
	
    return 0;
}
