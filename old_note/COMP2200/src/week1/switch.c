#include<stdio.h>

int main(void) {

    enum fruit { FRUIT_APPLE, FRUIT_MANGO };

    enum fruit fruit = FRUIT_APPLE;

    switch (fruit) {
    case FRUIT_APPLE:
	printf("HHHH");
	/* intentional fallthrough */
    case FRUIT_MANGO:
	printf("lunch\n");
	break;
    default:
	printf("default\n");
	break;
    }

    return 0;
}
