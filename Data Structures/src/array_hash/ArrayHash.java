/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1257
 * beecrowd | 1257 Array Hash
 */

package array_hash;

import java.util.Scanner;

public class ArrayHash {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int sum = 0;
            for (int j = 0; j < n; j++) {
                String str = scanner.next();
                int l = str.length();
                for (int k = 0; k < l; k++) {
                    sum += str.charAt(k) - 'A' + j + k;
                }
            }
            System.out.println(sum);
        }
        scanner.close();
    }
}

/*
 * beecrowd | 1257
 * Array Hash
 * By TopCoder* USA
 * 
 * Timelimit: 1
 * You will be given many input lines with strings. The value of each character
 * in input is computed as follows:
 * 
 * Value = (Alphabet Position) + (Element of input) + (Position in Element)
 * 
 * All positions are 0-based. 'A' has alphabet position 0, 'B' has alphabet
 * position 1, ... The returned hash is the sum of all character values in
 * input. For example, if input is:
 * 
 * CBA
 * DDD
 * 
 * then each character value would be computed as follows:
 * 
 * 2 = 2 + 0 + 0 : 'C' in element 0 position 0
 * 2 = 1 + 0 + 1 : 'B' in element 0 position 1
 * 2 = 0 + 0 + 2 : 'A' in element 0 position 2
 * 4 = 3 + 1 + 0 : 'D' in element 1 position 0
 * 5 = 3 + 1 + 1 : 'D' in element 1 position 1
 * 6 = 3 + 1 + 2 : 'D' in element 1 position 2
 * 
 * The final hash would be 2+2+2+4+5+6 = 21.
 * 
 * Input
 * The input contains many test cases. The first line of input contains an
 * integer N that indicates the amount of test cases. Each test case begin with
 * an integer L (1 ≤ L ≤ 100) that indicates the quantity of following lines.
 * Each of these L lines will contains a string with up to 50 uppercase letters
 * ('A' - 'Z').
 * 
 * Output
 * For each test case print the hash calculated according to the above
 * explanation.
 * 
 * Sample Input
 * 5
 * 2
 * CBA
 * DDD
 * 1
 * Z
 * 6
 * A
 * B
 * C
 * D
 * E
 * F
 * 6
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * 1
 * ZZZZZZZZZZ
 * 
 * Sample Output
 * 
 * 21
 * 25
 * 30
 * 4290
 * 295
 */