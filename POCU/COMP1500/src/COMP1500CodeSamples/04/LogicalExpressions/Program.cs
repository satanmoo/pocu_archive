using System;

namespace LogicalExpressions
{
    class Program
    {
        static void Main(string[] args)
        {
            int num1 = 1;
            int num2 = 1;
            int num3 = 4;

            bool bExpression1 = !(num1 == num2 && num1 != num3);
            bool bExpression2 = num1 != num2 || num1 == num3;

            Console.WriteLine($"expression1: {bExpression1}");
            Console.WriteLine($"expression2: {bExpression2}");

            bool bExpression3 = num1 > num2 || num1 == num3 || ++num1 > num2;
            Console.WriteLine($"expression3: {bExpression3}");
            // num1 = 2, num2 = 1, num3 = 4
            // 따라서 true 출력

            bool bExpression4 = num3 >= num2 || num1-- == num2;
            Console.WriteLine($"expression4: {bExpression4}");
            // num3 >= num2 는 참이라서 두번째 표현식은 실해되지 않는다.
            // 따라서 true 출력

            bool bExpression5 = num3 == num1 && --num2 > num1;
            Console.WriteLine($"expression5: {bExpression5}");
            Console.WriteLine($"num1: {num1}, num2: {num2}, num3: {num3}");
            // num3 == num1 은 거짓이라서 두번째 표현식은 실행되지 않는다.
            // 따라서 false 출력
            // 최종적으로 num1 = 2, num2 = 1, num3 = 4

            int a = 1;
            int b = 2;

            int x = (++a) * b;
            Console.WriteLine(x);
            Console.WriteLine(a);
        }
    }
}
