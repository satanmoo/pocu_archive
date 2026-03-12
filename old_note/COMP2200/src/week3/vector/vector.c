#include <stdio.h>
#include "vector.h"

void add_vec3(const int* vec1, const int* vec2, int* vec3) {
    size_t i;
    for (i = 0; i < VECTOR_LENGTH; ++i) {
	*vec3++ = *vec1++ + *vec2++;
    }
}
