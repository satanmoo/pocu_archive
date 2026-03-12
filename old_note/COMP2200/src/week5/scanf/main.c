#include <stdio.h>

int main(void) {

    int num1;
    int num2;
    char str[10];

    scanf("%3d %3d %s", &num1, &num2, str);
    printf("%d, %d\n", num1, num2);
    printf("%s", str);

    return 0;
}
