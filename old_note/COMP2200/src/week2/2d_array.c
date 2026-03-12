#include<stdio.h>

int main(void) {

    int table[3 * 2];
    int i;
    int j;

    for (i = 0; i < 3; ++i) {
       for (j = 0; j < 2; ++j) {
	  table[2 * i + j] = 0;
       }
    }

    return 0;
}


