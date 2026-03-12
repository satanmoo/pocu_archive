#include <stdio.h>

void swap(int* a, int* b);

int main(void) {
    int a = 5;
    int b = 3;
    swap(&a, &b);
    printf("a is %d", a);
    printf("b is %d", b);

    return 0;
}

void swap(int* a, int* b) {
    int temp;
    temp = *a;
    *a = *b;
    *b = temp;
    return;
} 

