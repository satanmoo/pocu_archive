#include <stdio.h>

int main(void) {

    size_t i;
    size_t j;
    int arr[6][6] = { 2 };
    
    for (i = 0; i < 6; ++i) {
	for (j = 0; j < 6; ++j) {
	    printf("%d\n", arr[i][j]);
	}
    }

    return 0;
}

