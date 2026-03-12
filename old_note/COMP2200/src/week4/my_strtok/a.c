#include <stdio.h>
#include "a.h"

static char *next = NULL;

char *my_strtok(char *str, const char *delims) {

    char *start;

    if (str != NULL) {
        next = str;
    }

    if (next == NULL) {
        return NULL;
    }

    while (*next != '\0') {
        if (my_strchr(delims, *next) == 0) {
            break;
        }
        ++next;
    }

    start = next;
    while (*next != '\0') {
        if (my_strchr(delims, *next) == 1) {
            *next = '\0';
            next += 1;
            return start;
        }
        ++next;
    }

    next = NULL;

    return start;
}

int my_strchr(const char *str, const char ch) {
    while (*str != '\0') {
        if (*str == ch) {
            return 1;
        }
        ++str;
    }
    return 0;
}
