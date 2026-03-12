#include<stdio.h>

int main(void) {
    int nums1[41];  /* all is trash */
    int nums2[4] = { 1, 2, 3, 4 };   /* 정직한 방법 */
    int nums3[5] = { 1, 2 };	/* { 1, 2, 0, 0, 0}; */
    int nums4[2] = { 1, 2, 3 }; /* 컴파일 오류 */
    int nums5[] = { 1, 2, 3}; /* { 1, 2, 3 }; 컴파일러가 배열의 크기 알 수 이씀 */
    int nums6[12] = { 0, }; /* best practic */

    return 0;
}
