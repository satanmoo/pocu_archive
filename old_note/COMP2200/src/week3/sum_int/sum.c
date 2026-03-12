#include <stdio.h>
#include "sum.h"

int sum1(int* const arr, const size_t n)
{
    size_t i;
    int res = 0;

    for (i = 0; i < n; ++i) {
	res += arr[i];
    }

    return res;
}

int sum2(int* const arr, const size_t n)
{
    size_t i;
    int res = 0;

    for (i = 0; i < n; ++i) {
	res += *(arr + i);
    }

    return res;
}

