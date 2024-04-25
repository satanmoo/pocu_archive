#include <stdio.h>
#include "add.h"

int* add(const int op1, const int op2) {
    
    int result;

    result = op1 + op2;

    return &result;
}
