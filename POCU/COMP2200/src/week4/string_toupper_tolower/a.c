#include "a.h"

int is_alpha(int c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
}

int to_upper(int c) {
    return c & ~0x20;
}

int to_lower(int c) {
    return c | 0x20;
}

void string_toupper(char* str) {
    while (*str != '\0') {
        *str = to_upper(*str);
        ++str;
    }
}

void string_tolower(char* str) {
    while (*str != '\0') {
        *str = to_lower(*str);
        ++str;
    }
}
