#include <stdio.h>
#include "a.h"
#include "b.h"

int main(void) {
    const char* str1 = "123";
    const char* str2 = "1234";
    
    size_t len1 = get_length(str1);
    size_t len2 = get_length_b(str2);

    printf("%d %d\n", len1, len2);

    return 0;
}

