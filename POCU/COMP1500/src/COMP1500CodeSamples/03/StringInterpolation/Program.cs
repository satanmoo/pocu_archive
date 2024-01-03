using System;

namespace StringInterpolation
{
    class Program
    {
        static void Main(string[] args)
        {
            int num1 = 48;
            int num2 = 65;

            Console.WriteLine("The sum of " + num1 + " and " + num2 + " is " + (num1 + num2));
            Console.WriteLine(string.Format("The sum of {0} and {1} is {2}", num1, num2, num1 + num2));
            Console.WriteLine($"The sum of {num1} and {num2} is {num1 + num2}");
            
            // 아래 두 줄 결과 동일, 문자열 보간 사용되지 않음
            Console.WriteLine($"\x48\x65\x6C\x6C\x6F\x20\x57\x6F\x72\x6C\x64\x21");
            Console.WriteLine("\x48\x65\x6C\x6C\x6F\x20\x57\x6F\x72\x6C\x64\x21");
            // 그대로 출력
            Console.WriteLine(@"\x48\x65\x6C\x6C\x6F\x20\x57\x6F\x72\x6C\x64\x21");
            
            // num1 : 왼쪽 정렬(정렬 길이에 음수), num2 : 오른쪽 정렬 빈칸은 공백으로 채운다.
            Console.WriteLine("{0,-11} + {1,11} = {2}", num1, num2, num1 + num2);
            Console.WriteLine("{0,-11} - {1,11} = {2}", num1, num2, num1 - num2);
            Console.WriteLine("{0,-11} * {1,11} = {2}", num1, num2, num1 * num2);
            // num1/num2를 float로 형변환했다. / 연산자의 피연산자 중 하나만 명시적 형변환 해주면 승격 때문에 num1도 부동소수점 형이 되어 계산된다.
            Console.WriteLine("{0,-11} / {1,11} = {2:f4}", num1, num2, num1 / (float)num2);
            Console.WriteLine("{0,-11} % {1,11} = {2}", num2, num1, num2 % num1);

            Console.WriteLine();

            Console.WriteLine(string.Format("{0,-11} + {1,11} = {2}", num1, num2, num1 + num2));
            Console.WriteLine(string.Format("{0,-11} - {1,11} = {2}", num1, num2, num1 - num2));
            Console.WriteLine(string.Format("{0,-11} * {1,11} = {2}", num1, num2, num1 * num2));
            Console.WriteLine(string.Format("{0,-11} / {1,11} = {2:f4}", num1, num2, num1 / (float)num2));
            Console.WriteLine(string.Format("{0,-11} % {1,11} = {2}", num2, num1, num2 % num1));
        }
    }
}
