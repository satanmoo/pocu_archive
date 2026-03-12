#include <stdio.h>
#include "print_array.h"

void print_array(int* const data[], const size_t size, const size_t lengths[]) {

    /* 함수의 매개변수인 data에 int* 배열(배열의 배열)의 주소를 복사한다.
     * 이 복사된 int* 배열값을 가진 data라는 포인터의 값을 다른 주소로 변경할 수 없다.
     * */
    size_t i;
    size_t j;
    const int* ptr;
    
    for (i = 0; i < size; ++i) {
	ptr = data[i];
	printf("data[%lu] ", i);
	
	for (j = 0; j < lengths[i]; ++j) {
	    printf(" %d", *(ptr + j));
	}
	printf("\n");
    }
}

