using System;

namespace BitFlag
{
    class Program
    {
        static void Main(string[] args)
        {
            const int BIT_FLAG_SIZE = 8;
            
            // 00000000
            byte bitFlags = 0;
            
            // 00000100
            byte mask1 = 1 << 2;
            
            // 00000100
            bitFlags |= mask1;

            Console.WriteLine("bitFlags: " + Convert.ToString(bitFlags, 2).PadLeft(BIT_FLAG_SIZE, '0'));
            
            // 00101000
            byte mask3 = (1 << 3) | (1 << 5);
            
            // 00101100
            bitFlags |= mask3;

            Console.WriteLine("bitFlags: " + Convert.ToString(bitFlags, 2).PadLeft(BIT_FLAG_SIZE, '0'));
            
            // mask : 11111011
            // bifFlags : 00101000
            bitFlags &= (byte)~mask1;

            Console.WriteLine("bitFlags: " + Convert.ToString(bitFlags, 2).PadLeft(BIT_FLAG_SIZE, '0'));
            
            bitFlags &= 0;

            Console.WriteLine("bitFlags: " + Convert.ToString(bitFlags, 2).PadLeft(BIT_FLAG_SIZE, '0'));

            char char1 = 'A';
            int result1 = char1 | ' ';

            Console.WriteLine("result1: " + (char)result1);
            
            // char2 : 97 : 001100001
            char char2 = 'a';
            // mask : 95 -> 01011111
            // result2 : 001000001 -> 65 = 'A' 
            int result2 = char2 & '_';

            Console.WriteLine("result2: " + (char)result2);
        }
    }
}
