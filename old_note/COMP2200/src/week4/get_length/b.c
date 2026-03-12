#include <stdio.h>
#include "b.h"

size_t get_length_b(const char* str) {
    const char* ptr = str;

    while (*ptr != '\0') {
	++ptr;
    }

    return ptr - str;
}
