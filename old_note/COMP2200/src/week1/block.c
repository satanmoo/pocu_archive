#include<stdio.h>

int main(void) {

    int num1 = 10;
    printf("%d\n", num1);

    {
	int num2 = 10;
	int result = num1 + num2;
	printf("%d\n", result);
    }

    return 0;
}

