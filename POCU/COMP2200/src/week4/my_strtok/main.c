#include <stdio.h>
#include <string.h>
#include "a.h"

int main(void) {
    char msg[] = "Hi, there. Hello. Bye";
    char msg2[] = "Hi, there. Hello. Bye";
    char* token;
    char* token2;
    const char delims[] = " ,.";

    token = my_strtok(msg, delims);
    while (token != NULL) {
        printf("%s\n", token);
        token = my_strtok(NULL, delims);
    }

    printf("--------------------\n");

    token2 = strtok(msg2, delims);
    while (token2 != NULL) {
        printf("%s\n", token2);
        token2 = strtok(NULL, delims);
    }

    return 0;
}
