#include <stdio.h>
#include <string.h>

int main(void) {

    const char* src = "12345";
    char dest[20];

    strncat(dest, src, 10);

    printf("%s", dest);

    return 0;
}
