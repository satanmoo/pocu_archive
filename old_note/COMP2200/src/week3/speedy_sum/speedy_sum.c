#include "speedy_sum.h"

int sum(int* start, int* end) {
    
    int sum = 0;
    int* ptr = start;
    
    while (ptr < end) {
	sum += *(ptr++);
    }

    return sum;
}
	
