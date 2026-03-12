#include <stdio.h>
#include "a.h"

void print_ascii_table(void)
{
    const int MIN_ASCII = 32;
    const int MAX_ASCII = 126;
    const int NUM_CHARS = MAX_ASCII - MIN_ASCII + 1;
    const int NUM_COLS = 3;
    const int NUM_ROWS = (NUM_CHARS + NUM_COLS - 1) / NUM_COLS;

    size_t r;
    unsigned char ch;

    printf("Dec Hex  Char\tDec Hex  Char\tDec Hex  Char\n");

    for (r = 0; r < NUM_ROWS - 1; ++r) {
	ch = MIN_ASCII + r;
	printf("%03d %#X %c\t", ch, ch, ch);

	ch += NUM_ROWS;
	printf("%03d %#X %c\t", ch, ch, ch);

	ch += NUM_ROWS;
	printf("%03d %#X %c\n", ch, ch, ch);
    }

    for (ch = MIN_ASCII + r; ch <= MAX_ASCII; ch += NUM_ROWS) {
	printf("%03d %#X %c\t", ch, ch, ch);
    }
}
