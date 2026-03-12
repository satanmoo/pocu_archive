#include <stdio.h>
#include "a.h"

int get_length(const char* str1, const char* str2) {

    while (*str1 != '\0' && *str1 == *str2) {
	++str1;
	++str2;
    }

    return *str1 - *str2;
}

