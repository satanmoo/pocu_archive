#include <stdio.h>
#include <string.h>

int main(void) {
    char dest[3];
    const char* src = "12345";
    int len = 0 ;

    strncpy(dest, src, 3);
    dest[2] = '\0';
   printf("%s", dest);
   len = strlen(dest);
   printf("%d", len);

  return 0;
} 
