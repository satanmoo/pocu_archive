#include <stdio.h>
#include <string.h>

#include "buffered_print.h"

#define BUFFER_LENGTH (32)

static size_t s_buffer_index = 0u;
static char s_buffer[BUFFER_LENGTH];

void buffered_print(const char* src) {
    
    size_t left_char_cnt;
    const char* p;

    left_char_cnt = strlen(src);
    p = src;

    while (left_char_cnt > 0) {
	
	size_t copy_cnt = BUFFER_LENGTH - s_buffer_index - 1;

	if (left_char_cnt < copy_cnt) {
	    copy_cnt = left_char_cnt;
	}

	if (s_buffer_index == 0) {
	    strncpy(s_buffer, p, copy_cnt);
	    s_buffer[s_buffer_index]  = '\0';   
	}
	else {
	    strncat(s_buffer, p, copy_cnt);
	}

	s_buffer_index += copy_cnt;

	if (s_buffer_index == BUFFER_LENGTH - 1) {
	    printf("%s\n", s_buffer);
	    s_buffer_index = 0;
	}

	p += copy_cnt;
	left_char_cnt -= copy_cnt;
    }
}
