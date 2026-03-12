#include <stdio.h>

int main(void) {

    float pi = 3.14134f;
    float* float_ptr = &pi;
    *float_ptr += 10;
    printf("%f\n", pi);

    return 0;
}

