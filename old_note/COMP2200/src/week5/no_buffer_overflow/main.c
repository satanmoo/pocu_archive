#include <stdio.h>

#define LENGTH (4096)
#define TRUE (1)

char line[LENGTH];
char word[LENGTH];

int main(void)
{
    while (TRUE) {
        if (fgets(line, LENGTH, stdin) == NULL) {
            clearerr(stdin);
            printf("ERROR!\n");
            break;
        }

        if (sscanf(line, "%s", word) == 1) {
            printf("%s\n", word);
        }
    }
}
