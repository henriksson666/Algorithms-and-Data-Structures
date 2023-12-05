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

/*
 * beecrowd | 2675
 * Steal Pack
 * By Hamilton José Brumatto, UESC BR Brazil
 * 
 * Timelimit: 1
 * The single-child difficulty (especially before the advent of video games) is
 * playing alone, so patience games have emerged. One of them is the Steal Pack
 * that Dr. Silvano Barbosa de Campos invented to play with N cards numbered
 * from 1 to N. You get these cards shuffled, and you must take one by one and
 * put them in a pack, but can only put a card on the pack, if the card at the
 * top of the pack is smaller than this one, otherwise you will steal cards
 * until you find a smaller one. In the end your score is the sum of the cards
 * you stole. As this game was tiring, you were asked to have an algorithm that,
 * given the sequence of cards, indicates the sum you have stolen.
 * 
 * Input
 * The input contains several test cases, each test case contains two lines, in
 * the first the number N (0 < N ≤ 105) (90% of the input cases are 0 < N ≤
 * 1,000) indicating the number of cards. In the second line N values, numbered
 * from 1 to N in any order. The test cases ends with the end of the input.
 * 
 * Output
 * It should be printed, for each test case, a value indicating the sum you will
 * receive at the end of the game on a single line.
 * 
 * Input Sample
 * 5
 * 3 2 5 1 4
 * 5
 * 1 2 3 4 5
 * 3
 * 3 2 1
 * 
 * Output Sample
 * 10
 * 0
 * 5
 */