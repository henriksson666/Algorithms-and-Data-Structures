/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1256
 * beecrowd | 1256 Hash Tables
 */

package hash_table; // package declaration is mandatory

import java.io.BufferedReader; // import BufferedReader class
import java.io.IOException; // import IOException class
import java.io.InputStreamReader; // import InputStreamReader class
import java.io.PrintWriter; // import PrintWriter class
import java.util.Arrays; // import Arrays class

public class HashTable { // HashTable class declaration

    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); // instantiate
                                                                                               // BufferedReader
                                                                                               // object
    static PrintWriter outputWriter = new PrintWriter(System.out); // instantiate PrintWriter object

    public static void main(String[] args) throws IOException { // main method declaration
        int testCases = readInt(); // read number of test cases

        while (testCases-- > 0) { // loop through test cases
            int[] parameters = readIntArray(); // read parameters
            int size = parameters[0]; // read size
            String[] hashTable = new String[size]; // instantiate hash table
            Arrays.fill(hashTable, ""); // fill hash table with empty strings

            String[] elements = readLine().split("\\s"); // read elements
            for (String element : elements) { // loop through elements
                int index = toInt(element) % size; // calculate index
                hashTable[index] += " -> " + element; // add element to hash table
            } // end of loop through elements

            for (int i = 0; i < size; i++) { // loop through hash table
                outputWriter.println(i + hashTable[i] + " -> \\"); // print hash table
            } // end of loop through hash table

            if (testCases != 0) { // if it is not the last test case
                outputWriter.println(); // print blank line
            } // end of if
        } // end of loop through test cases

        outputWriter.close(); // close PrintWriter object
    } // end of main method

    private static String readLine() throws IOException { // read line method
        return inputReader.readLine(); // return line
    } // end of readLine method

    private static int readInt() throws IOException { // read int method
        return Integer.parseInt(inputReader.readLine()); // return int
    } // end of readInt method

    private static int[] readIntArray() throws IOException { // read int array method
        String[] line = inputReader.readLine().split("\\s"); // read line
        int length = line.length; // get line length
        int[] array = new int[length]; // instantiate int array
        for (int i = 0; i < length; i++) { // loop through line
            array[i] = Integer.parseInt(line[i]); // parse line to int
        } // end of loop through line
        return array; // return int array
    } // end of readIntArray method

    private static int toInt(String s) { // to int method
        return Integer.parseInt(s); // return int
    } // end of toInt method
} // end of HashTable class

/*
 * beecrowd | 1256
 * Hash Tables
 * By Neilor Tonin, URI Brasil
 * 
 * Timelimit: 1
 * Hash tables are used to store elements based on the absolute value of their
 * keys and collision handling techniques. To calculate an address where should
 * be stored a determinated key, it uses a function called hash function which
 * transforms the key in one of the available addresses in the table.
 * 
 * Let's assume that an application uses a hash table with 13 base addresses
 * (indexes 0 through 12) and uses the dispersion function h(x) = x mod 13,
 * where x is the key whose base address would be calculated.
 * 
 * If x = 49, the hash function will return 10, indicating the location
 * (address) where this key should be stored. If we needed insert the key 88 in
 * the same application, the calculation returns the same value 10, a collision
 * will occurs. Treatment of collisions is used to solve conflicts in cases
 * where more than one key is mapped to the same address. This treatment may
 * consider key address recalculation or exterior chaining.
 * 
 * So the teacher asked you to write a program that calculates the address for
 * many keys in some tables, with functions of spreading and treatment of
 * collision by exterior chaining.
 * 
 * Input
 * The input contains many test cases. The firs line of input contains an
 * integer N indicating the number of test cases. Each test case is composed by
 * two lines. The first one contains a integer M (1 ≤ M ≤ 100) that indicates
 * the number of base addresses in the table (usually a prime number) followed
 * by an space and a integer C (1 ≤ C ≤ 200) that indicates the among of keys to
 * be stored. The second one contains each one of the C keys (with value between
 * 1 and 200), separated by an white space.
 * 
 * Output
 * The output must be printed like the following examples, where the quantity of
 * lines of each test case is determinated by the value of M. A blank line
 * should separate each set of output.
 * 
 * Sample Input
 * 2
 * 13 9
 * 44 45 49 70 27 73 92 97 95
 * 7 8
 * 35 12 2 17 19 51 88 86
 * 
 * 0 -> \
 * 1 -> 27 -> 92 -> \
 * 2 -> \
 * 3 -> \
 * 4 -> 95 -> \
 * 5 -> 44 -> 70 -> \
 * 6 -> 45 -> 97 -> \
 * 7 -> \
 * 8 -> 73 -> \
 * 9 -> \
 * 10 -> 49 -> \
 * 11 -> \
 * 12 -> \
 * 
 * Sample Output
 * 
 * 0 -> 35 -> \
 * 1 -> \
 * 2 -> 2 -> 51 -> 86 -> \
 * 3 -> 17 -> \
 * 4 -> 88 -> \
 * 5 -> 12 -> 19 -> \
 * 6 -> \
 */