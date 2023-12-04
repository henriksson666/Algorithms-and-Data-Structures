/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/2675
 * beecrowd | 2675 Steal Pack
 */

package stealing_pack; // stealing_pack package

import java.util.Scanner; // Scanner class

public class StealPack { // StealPack class
    public static void main(String[] args) { // main method
        Scanner scanner = new Scanner(System.in); // scanner object

        while (scanner.hasNextInt()) { // while there is another integer
            int N = scanner.nextInt(); // store number of items
            long ans = 0; // answer
            int[] n = new int[N]; // array of items

            for (int i = 0; i < N; i++) { // loop through items
                n[i] = scanner.nextInt(); // store item
            } // end for

            int mesa = n[N - 1]; // store last item

            for (int i = N - 2; i >= 0; i--) { // loop through items
                if (n[i] < mesa) { // if item is smaller than mesa
                    mesa = n[i]; // set mesa to item
                } else { // if item is bigger than mesa
                    ans += n[i]; // add item to answer
                } // end if
            }

            System.out.println(ans); // print answer
        } // end while

        scanner.close(); // close scanner
    } // end main
} // end StealPack class
