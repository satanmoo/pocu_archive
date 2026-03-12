#ifndef MEMORY_H
#define MEMORY_H

#define TRUE (1)
#define FALSE (0)

#define ARRAY_LENGTH(arr) (sizeof(arr)/sizeof(arr[0]))

int is_overlap(int arr1[], size_t length, int arr2[], size_t length2);

#endif
