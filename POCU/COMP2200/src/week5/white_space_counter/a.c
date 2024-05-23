#include <stdio.h>
#include <ctype.h>
#include "a.h"

void print_whitespace_stat(void)
{
    int c;
    unsigned int space_cnt;
    unsigned int newline_cnt;

    space_cnt = 0;
    newline_cnt = 0;
    c = getchar();

    while (c != EOF) {
        if (isspace(c) > 0) {
            ++space_cnt;
            if (c == '\n') {
                ++newline_cnt;
            }
        }
        c = getchar();
    }

    printf("space: %d\n", space_cnt);
    printf("newline: %d\n", newline_cnt);
}

