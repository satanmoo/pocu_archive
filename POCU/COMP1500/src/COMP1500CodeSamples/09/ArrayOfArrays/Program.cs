using System;

namespace ArrayOfArrays
{
    class Program
    {
        static void Main(string[] args)
        {
            const int MONTHS_IN_A_YEAR = 12;
            int[] daysInEachMonth = new int[MONTHS_IN_A_YEAR] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            // MONTHS_IN_A_YEAR 크기의 배열의 배열 선언하기
            string[][] calendar = new string[MONTHS_IN_A_YEAR][];
            // 각 월의 일수에 맞게 배열의 배열의 내부 배열을 초기화하기
            for (var i = 0; i < MONTHS_IN_A_YEAR; i++)
            {
                calendar[i] = new string[daysInEachMonth[i]];
            }

            while (true)
            {
                Console.Write("Enter the Month (1 - 12): ");
                string monthString = Console.ReadLine();
                // Out 변수를 사용하여 TryParse 메소드를 호출하기, 입력값 검증하기
                
                if (!int.TryParse(monthString, out int month))
                {
                    Console.WriteLine("Invalid input. Terminating program");
                    break;
                }

                if (0 >= month || month > 12)
                {
                    Console.WriteLine("Invalid range of month. Terminating program");
                    break;
                }

                Console.Write($"Enter the Day (1 - {calendar[month - 1].Length}): ");
                string dayString = Console.ReadLine();
                // Out 변수를 사용하여 TryParse 메소드를 호출하기, 입력값 검증하기
                if (!int.TryParse(dayString, out int day))
                {
                    Console.WriteLine("Invalid input. Terminating program");
                    break;
                }

                if (0 >= day || day > calendar[month - 1].Length)
                {
                    Console.WriteLine("Invalid range of day. Terminating program");
                    break;
                }

                Console.WriteLine("Enter your schedule: ");
                string schedule = Console.ReadLine();
                calendar[month - 1][day - 1] = schedule;

                Console.WriteLine("-----------------------------");
                // 배열의 배열을 순회하며 값이 있는 월과 일에 대한 스케줄 출력하기
                // 스케쥴이 없는 경우 출력하지 않기
                // Console.WriteLine($"{i + 1} / {j + 1}: {calendar[i][j]}");
                for (int i = 0; i < MONTHS_IN_A_YEAR; i++)
                {
                    for (int j = 0; j < calendar[i].Length; j++)
                    {
                        if (!string.IsNullOrEmpty(calendar[i][j]))
                        {
                            Console.WriteLine($"{i + 1} / {j + 1}: {calendar[i][j]}");
                        }
                    }
                }
                Console.WriteLine("-----------------------------");
            }
        }
    }
}