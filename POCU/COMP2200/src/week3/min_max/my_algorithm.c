#include <assert.h>
#include <stddef.h>
#include "my_algorithm.h"

/* pre-condition: n >= 1 */
void get_min_max(const int arr[], const size_t n, int* out_min, int* out_max) {
   
    size_t i;
   
    assert(n >= 1);

    *out_min = arr[0];
    *out_max = arr[0];
    for (i = 1; i < n; ++i) {
	if (*out_min > arr[i]) {
	    *out_min = arr[i];
	}
        if (*out_max < arr[i]) {
	    *out_max = arr[i];
	}
    }
}

