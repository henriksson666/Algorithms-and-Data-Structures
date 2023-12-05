package hash_table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class HashTable {

    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter outputWriter = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int testCases = readInt();

        while (testCases-- > 0) {
            int[] parameters = readIntArray();
            int size = parameters[0];
            String[] hashTable = new String[size];
            Arrays.fill(hashTable, "");

            String[] elements = readLine().split("\\s");
            for (String element : elements) {
                int index = toInt(element) % size;
                hashTable[index] += " -> " + element;
            }

            for (int i = 0; i < size; i++) {
                outputWriter.println(i + hashTable[i] + " -> \\");
            }

            if (testCases != 0) {
                outputWriter.println();
            }
        }

        outputWriter.close();
    }

    private static String readLine() throws IOException {
        return inputReader.readLine();
    }

    private static int readInt() throws IOException {
        return Integer.parseInt(inputReader.readLine());
    }

    private static int[] readIntArray() throws IOException {
        String[] line = inputReader.readLine().split("\\s");
        int length = line.length;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(line[i]);
        }
        return array;
    }

    private static int toInt(String s) {
        return Integer.parseInt(s);
    }
}
