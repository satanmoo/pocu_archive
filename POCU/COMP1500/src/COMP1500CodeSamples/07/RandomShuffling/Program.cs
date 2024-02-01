using System;

namespace RandomShuffling
{
    class Program
    {
        static void Main(string[] args)
        {
            const int SEED = 0;
            int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

            Console.WriteLine("Before shuffling:");
            Console.WriteLine($"[{string.Join(", ", numbers)}]");

            Random random = new Random(SEED);

            for (int i = numbers.Length - 1; i > 0; i--)
            {
                int j = random.Next(0, i);
                int temp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = temp;
            }

            Console.WriteLine("After shuffling:");
            Console.WriteLine($"[{string.Join(", ", numbers)}]");
            
            int num1 = 5;
            int num2 = 15;
            int num3 = 2;
            
            int result3 = (num2 + num1) / num1 + ++num1;
            Console.WriteLine($"result3: {result3}");
            Console.WriteLine($"num1: {num1}, num2: {num2}, num3: {num3}");
        }
    }
}
