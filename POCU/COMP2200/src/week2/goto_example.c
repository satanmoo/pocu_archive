#include<stdio.h>

int main(void) {
    int res = 0;
    int flag = 0;
    /*	do A */
    res += 1;
    if (flag) {
	goto out_a;
    }
    /* do B */
    res += 2;
    if (flag) {
	goto out_b;
    }
    /* do C */
    res -= 1;
    if (flag) {
	goto out_c;
    }
    goto out;
    out_c:
	res +=1;
    out_b:
       	res -= 2;
    out_a:
	res -= 1;
    out:
	printf("%d",  res);
    
    return 0;
}
   
