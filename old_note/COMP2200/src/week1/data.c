#include <stdio.h>
#include <limits.h>

int main(void) {
    char char_size = CHAR_BIT;
    char default_char = 127;	/*clang 에서 default: signed char(-128..127)*/
    signed char signed_char = -128;
    unsigned char unsigned_char = 255;
    signed short signed_short = -32768;
    unsigned short unsigned_short = 65535;
    signed int signed_int = -2147483648;
    unsigned int unsigned_int = 4294967295U;
    signed long signed_long = LONG_MIN;
    unsigned long unsigned_long = ULONG_MAX;

    printf("%d\n", char_size);	/*8*/
    printf("%d\n", default_char);   /*	127 */   
    printf("%d\n", signed_char);    /*	-128  */
    printf("%d\n", unsigned_char);  /*	255 */

    printf("%d\n", signed_short);
    printf("%d\n", unsigned_short);

    printf("%d\n", signed_int);
    printf("%u\n", unsigned_int);

    printf("%ld\n", signed_long);
    printf("%lu\n", unsigned_long);

    return 0;
}

