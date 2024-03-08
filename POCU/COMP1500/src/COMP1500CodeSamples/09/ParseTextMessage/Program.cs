using System;
using System.IO;

namespace ParseTextMessage
{
    class Program
    {
        static void Main(string[] args)
        {
            string textMessage = File.ReadAllText(@"TextMessage.txt");

            // 1줄씩 읽기
            // [ "[POCU Web Message]", "Monday 2019-04-15 13:21:54.456", "student1234@fakeemail.com     ", "Course                COMP1500", "Term                    201905" ]
            string[] lines = textMessage.Split('\n');

            // 1줄에서 공백을 기준으로 문자열 나누기
            // [ "Monday", "2019-04-15", "13:21:54.456" ]
            string[] dateTimeString = lines[1].Split(' ');
            string nameOfDay = dateTimeString[0];

            // - 을 기준으로 문자열 나누기
            // [ "2019", "04", "15" ]
            string[] date = dateTimeString[1].Split('-');

            // TryParse를 사용하여 문자열을 정수로 변환하기
            if (!int.TryParse(date[0], out int year))
            {
                Console.WriteLine("Invalid year");
                return;
            }

            if (!int.TryParse(date[1], out int month))
            {
                Console.WriteLine("Invalid month");
                return;
            }

            if (!int.TryParse(date[2], out int day))
            {
                Console.WriteLine("Invalid day");
                return;
            }

            // [ "13", "21", "54.456" ]
            string[] time = dateTimeString[2].Split(':');

            if (int.TryParse(time[0], out int hours))
            {
                Console.WriteLine("Invalid hours");
                return;
            }

            if (int.TryParse(time[1], out int mins))
            {
                Console.WriteLine("Invalid minutes");
                return;
            }
            
            if (!int.TryParse(time[2], out int seconds))
            {
                Console.WriteLine("Invalid seconds");
                return;
            }

            string email = lines[2].Trim();
            // COMP1500 만 추출
            string courseCode = lines[3].Replace("Course", "").Trim();
            // 201905 만 추출
            string term = lines[4].Replace("Term", "").Trim();

            Console.WriteLine($"Name of Day: {nameOfDay}");
            Console.WriteLine($"Year: {year}");
            Console.WriteLine($"Month: {month}");
            Console.WriteLine($"Day: {day}");
            Console.WriteLine($"Hours: {hours}");
            Console.WriteLine($"Minutes: {mins}");
            Console.WriteLine($"Seconds: {seconds}");
            Console.WriteLine($"Email: {email}");
            Console.WriteLine($"Course Code: {courseCode}");
            Console.WriteLine($"Term: {term}");
        }
    }
}