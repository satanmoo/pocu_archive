#include <stdio.h>

int main(void) {

    int* ptr;
    int a = 5;

    ptr = &a;

    if (ptr != NULL) {
    }
    if (ptr == NULL) {
    }

    ptr = NULL;
    *ptr = 10;

    return 0;
}

