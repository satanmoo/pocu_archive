#include <stdio.h>
#include "a.h"

size_t get_length(const char* str) {

    size_t len = 0;

    while(*str != '\0') {
	++str;
	++len;
    }

    return len;
}

