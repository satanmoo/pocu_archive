using System;
using System.Collections.Generic;
using System.IO;

namespace UsingStatement
{
    public class Program
    {
        private static readonly string CURRENT_DIRECTORY = Directory.GetCurrentDirectory();
        private static readonly string OUTPUT_FOLDER_PATH = Path.Combine(CURRENT_DIRECTORY, "output");
        private static readonly string INPUT_FILE_FULL_PATH = Path.Combine(CURRENT_DIRECTORY, "input", "inputtext.txt");
        private static readonly string OUTPUT_FILE_FULL_PATH = Path.Combine(CURRENT_DIRECTORY, "output", "outputtext.txt");

        public static void Main(string[] args)
        {
            setup();

            string allText = string.Empty;
            
            using (StreamReader reader1 = new StreamReader(File.Open(INPUT_FILE_FULL_PATH, FileMode.Open, FileAccess.Read)))
            {
                Console.WriteLine("----------------------------------");
                allText = reader1.ReadToEnd();
                Console.WriteLine(allText);
                Console.WriteLine("----------------------------------");
            }
            // Reader가 사용하는 Stream까지 닫아줌
            List<string> allLines = new List<string>();
            using (StreamReader reader2 = new StreamReader(File.Open(INPUT_FILE_FULL_PATH, FileMode.Open, FileAccess.Read)))
            {
                allLines = new List<string>();
                
                while (!reader2.EndOfStream)
                {
                    allLines.Add(reader2.ReadLine());
                }

                foreach (string line in allLines)
                {
                    Console.WriteLine(line);
                }

                Console.WriteLine("----------------------------------");
            }

            using (StreamWriter writer = new StreamWriter(File.Open(OUTPUT_FILE_FULL_PATH, FileMode.OpenOrCreate, FileAccess.Write)))
            {
                writer.Write(allText);

                foreach (string line in allLines)
                {
                    writer.WriteLine(line);
                }

                // writer.Flush();  // way 1 buffer flush
                writer.BaseStream.Seek(0, SeekOrigin.Begin); // Flush 후 BaseStream을 0으로 해야함
                writer.Write("Overwritten text");
                StreamWriter writer2 =
                    new StreamWriter(File.Open(OUTPUT_FILE_FULL_PATH, FileMode.OpenOrCreate, FileAccess.Write));
            }

            // using (StreamWriter writer = new StreamWriter(File.Open(OUTPUT_FILE_FULL_PATH, FileMode.OpenOrCreate, FileAccess.Write)))
            // {
            //     writer.WriteLine("이건 overwrite됩니다. StreamWriter을 초기화 했기 때문에 버퍼도 초기화 됨");
            // }
        }

        private static void setup()
        {
            if (File.Exists(OUTPUT_FILE_FULL_PATH))
            {
                File.Delete(OUTPUT_FILE_FULL_PATH);
            }

            if (!Directory.Exists(OUTPUT_FOLDER_PATH))
            {
                Directory.CreateDirectory(OUTPUT_FOLDER_PATH);
            }
        }
    }
}
