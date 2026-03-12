#include<stdio.h>

int main(void) {
    enum color {
	RED,
	GREEN,
	BLUE
    };

    enum day {
	MON,
	TUE,
	WED
    };

    enum day _day = MON;
    /* _day = GREEN; */   /*컴파일 경고*/

    printf("%d", _day);

    return 0;
}

