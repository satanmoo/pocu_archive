#include <stdio.h>
#include "vector.h"

int main(void) {

    const int vec1[] = { 1, 2, 3};
    const int vec2[] = { 2, 4, 6};
    int vec3[VECTOR_LENGTH] = { 0,};
    size_t i;

    add_vec3(vec1, vec2, vec3);

    for (i = 0; i < VECTOR_LENGTH; ++i) {
	printf("%d ", vec3[i]);
    }

    return 0;
}
