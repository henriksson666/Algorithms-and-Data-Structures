/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1025
 * beecrowd | 1025 Where Is The Marble?
 */

package where_is_the_marble;

import java.util.Arrays;
import java.util.Scanner;

public class WhereIsTheMarble {

    public static int buscaBinaria(int[] v, int x) {
        int start = 0, middle = 0, end = 0;
        end = v.length - 1;

        while (start <= end) {
            middle = (start + end) / 2;
            if (v[middle] == x)
                break;
            else if (v[middle] > x)
                end = middle - 1;
            else
                start = middle + 1;
        }

        if (v[middle] == x) {
            int p = middle;
            while (p - 1 >= 0 && v[p - 1] == x)
                p--;
            return p;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int Number, Query, x, t = 1;

        while (true) {
            Number = scanner.nextInt();
            Query = scanner.nextInt();

            if (Number + Query <= 0) {
                break;
            }

            System.out.println("CASE# " + t++ + ":");

            int[] v = new int[Number];
            int currentIndex = 0;
            for (int i = 0; i < Number; i++) {
                x = scanner.nextInt();
                if (x != 0) {
                    v[currentIndex++] = x;
                }
            }

            v = Arrays.copyOf(v, currentIndex);
            Arrays.sort(v);

            for (int i = 0; i < Query; i++) {
                x = scanner.nextInt();
                int pos = buscaBinaria(v, x);

                if (pos >= 0) {
                    System.out.println(x + " found at " + (pos + 1));
                } else {
                    System.out.println(x + " not found");
                }
            }
        }

        scanner.close();
    }
}

/*
 * beecrowd | 1025
 * Where is the Marble?
 * By Monirul Hasan Tomal, SEU Bangladesh
 * 
 * Timelimit: 2
 * Raju and Meena love to play with Marbles. They have a lot of marbles with
 * numbers written on them. In the beginning, Raju would place the marbles one
 * after another in ascending order of the numbers written on them. Then, Meena
 * would ask Raju to find the first marble with a certain number. She would
 * count 1...2...3. Raju gets one point for correct answer, and Meena gets the
 * point if Raju fails. After some fixed number of attempts, the game ends and
 * the player with maximum points wins. Today it's your chance to play as Raju.
 * Being a smart kid, you have in your advantage the computer. But don't
 * underestimate Meena, she wrote a program to keep track how much time you're
 * taking to give all the answers. So now you have to write a program, which
 * will help you in your role as Raju.
 * 
 * Input
 * There can be multiple test cases. The total number of test cases is less than
 * 65. Each test case consists begins with 2 integers: N the number of marbles
 * and Q the number of queries Meena would make. The next N lines would contain
 * the numbers written on the N marbles. These marble numbers will not come in
 * any particular order. Following Q lines will have Q queries. Be assured, none
 * of the input numbers are greater than 10000 and none of them are negative.
 * 
 * Input is terminated by a test case where N = 0 and Q = 0.
 * 
 * Output
 * For each test case output there must be a serial number of the test case. For
 * each query, write one line of output. The format of this line will depend on
 * whether the number is consulted or not written in one of the marbles.
 * 
 * The two different formats are described below:
 * 'x found at y', if the first marble with number x was found at position y.
 * Positions are numbered 1, 2,..., N.
 * 'x not found', if the marble with number x is not present.
 * 
 * Input Sample
 * 4 1
 * 2
 * 3
 * 5
 * 1
 * 5
 * 5 2
 * 1
 * 3
 * 3
 * 3
 * 1
 * 2
 * 3
 * 0 0
 * 
 * Output Sample
 * 
 * CASE# 1:
 * 5 found at 4
 * CASE# 2:
 * 2 not found
 * 3 found at 3
 */