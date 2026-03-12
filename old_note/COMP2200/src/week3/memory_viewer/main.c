#include <stdio.h>

int main(void) {
    const int NUM = 0x12345678;
    const char* ptr = (char*)&NUM;
    size_t i;

    for (i = 0; i < sizeof(NUM); ++i) {
	printf("%hhx ", ptr[i]);
    }
    printf("\n");

    printf("NUM is hex for: 0x%x", NUM);

    return 0;
}
