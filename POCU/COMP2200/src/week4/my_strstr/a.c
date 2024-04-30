#include <stdio.h>
#include "a.h"

char *my_strstr(const char *str, const char *substr) {

    size_t i;
    size_t j;

    /* Check for NULL pointers */
    if (str == NULL || substr == NULL) {
        return NULL;
    }

    /* If the substring is empty, return the original string */
    if (*substr == '\0') {
        return (char *) str;
    }

    for (i = 0; str[i] != '\0'; ++i) {
        if (str[i] == *substr) {
            for (j = 0; substr[j] != '\0'; ++j) {
                if (str[i + j] != substr[j]) {
                    break;
                }
            }
            if (substr[j] == '\0') {
                return (char *) str + i;
            }
        }
    }

    return NULL;

}
