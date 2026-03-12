#include <stdio.h>
#include "a.h"

int main(void) {
    char msg[] = "hi my nam is no zzzzna name nozzz no";

    char* result = my_strstr(msg, "name");
    printf("result: %s\n", result == NULL ? "null" : result);
    if (result != NULL) {
	printf("src: %p, substr: %p\n", (void*)msg, (void*)result);
    }
        
    return 0;
}
