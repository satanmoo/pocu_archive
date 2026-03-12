#include <stdio.h>
#include "a.h"

void str_copy(char* dest, const char* src) {
   while (*src != '\0') {
      *dest++ = *src++; 
   }
   *dest = '\0';
}
