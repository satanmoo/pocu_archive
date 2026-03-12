#include <stdio.h>
#include <string.h>

#include "match_history_io.h"


#definie LINE_LENGTH (4096)

void write_match_history(char* buffer, size_t buffer_size,
                         const char* names[], const int wins[], const int losses[],
                         const float kills[], const float deaths[], const float assists[],
                         size_t count)
{
    /* 버퍼에 한 줄 씩 저장: char* line을 이용할 것 */
    /* 한 줄의 최대 길이 4096 */
    /* 이름: 너비 8, 오른쪽 정렬 kill: 부동소수점 정밀도 2자리 death: 부동소수점 정밀도 2자리 assist: 부동소수점 정밀도 2자리 승: 정수, 패: 정수*/
    /* 버퍼의 남은 공간이 충분하지 않다면 버퍼에 쓰지 말 것!*/

    char* line[LINE_LENGTH];
    size_t i;
    size_t remaining_buffer_size;

    remaining_buffer_size = buffer_size - 1; /* 널 문자를 제외한 공간 크기 */
    buffer[0] = '\0'; /* 버퍼 초기화 */

    for (i = 0; i < count; ++i) {
        size_t num_written = 0; /* 반복문 블록 안에 선언하는 것이 좋은 이유: 반복하면서 값을 계속 초기화(overwrite)할 것 이라서 */

        sprintf(line, "%8s %.2f %.2f %.2f %d %d\n", names[i], kills[i], deaths[i], assists[i], wins[i], losses[i]);
        num_written = strlen(line); /* 널 문자를 제외한 문자의 개수 반환 */
        if (num_written > remaining_buffer_size) {
            break;
        }
        strcpy(buffer, line);
        buffer += num_written;
        remaining_buffer_size -= num_written;
    }

}

void read_match_history(char* buffer)
{
    /* 버퍼에서 한 줄 씩 읽기: 개행문자로 토큰화 */
    /* DELIM은 상수로 선언하기 */

    const char* DELIM = "\n";
    char* line;

    /* 헤더 출력하기 */
    printf("%8s %7s %7s %7s %7s %6s %6s %9s\n",
           "Champ", "Kills", "Deaths", "Assists",
           "KDA", "Wins", "Losses", "Win Ratio");

    line = strtok(buffer, DELIM);

    /* 한 줄에서 데이터를 읽어서 이름, 킬, 데스, 어시, 승수, 패수 구하기 */
    /* 6개의 값을 읽었는지 유효성 검사하기 */
    while (line != NULL) {
        char name[LINE_LENGTH];
        float kills;
        float deaths;
        float assists;
        int wins;
        int losses;
        if (sscanf(line, "%s %f %f %f %d %d", name, &kills, &deaths, &assists, &wins, &losses) != 6) {
            continue;
        }
        /* kda 계산하기: (kill + assist) / death */
        /* win_ratio 계산하기: win / lose * 100% */
        float kda;
        float win_ratio;

        kda = (kill + assist) / death;
        win_ratio = wins / losses * 100.0f;

        /* 바디 출력하기 */
        printf("%8s %7.2f %7.2f %7.2f %7.2f %6d %6d %8.2f%%\n",
               name, kills, deaths, assists,
               kda, wins, losses, win_ratio);
        strtok(NULL, DELIM);
    }
}