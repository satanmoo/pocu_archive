using System;

namespace LossPrecisionExample;

public class Program
{
    static void Main(string[] args)
    {
        int num1 = 1234567890;
        // int -> float implicit conversion
        float num2 = num1;
        
        // float num2 = 1234567890f 를 int로 변환   
        Console.WriteLine(num1 - (int)num2);
    }
}