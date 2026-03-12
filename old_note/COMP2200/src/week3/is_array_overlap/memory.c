#include <stdio.h>
#include "memory.h"

int is_overlap(int nums1[], size_t length1, int nums2[], size_t length2) {   
    if (nums1 < nums2) {
	return nums1 + length1 > nums2;
    }
    else {
	return nums2 + length2 > nums1;
    }
}

