/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1912
 * beecrowd | 1912 Help Seu Madruga
 */

package help_seu_madruga; // package declaration is mandatory

import java.util.Scanner; // import Scanner class

public class HelpSeuMadruga { // HelpSeuMadruga class declaration
    public static void main(String[] args) { // main method declaration
        Scanner scanner = new Scanner(System.in); // instantiate Scanner object
        while (scanner.hasNextInt()) { // loop through input
            int numberStrip = scanner.nextInt(); // read number of strips
            int desiredHeight = scanner.nextInt(); // read desired height
            if (numberStrip + desiredHeight == 0) { // if it is the last test case
                break; // break loop
            } // end of if

            int[] stripHeights = new int[numberStrip]; // instantiate strip heights array
            int totalHeight = 0; // instantiate total height
            for (int i = 0; i < numberStrip; i++) {
                stripHeights[i] = scanner.nextInt(); // read strip height
                totalHeight += stripHeights[i]; // add strip height to total height
            } // end of loop through strip heights

            if (totalHeight < desiredHeight) { // if it is impossible
                System.out.println("-.-"); // print result
                continue; // continue loop
            } else if (totalHeight == desiredHeight) { // if it is not necessary to cut
                System.out.println(":D"); // print result
                continue; // continue loop
            } // end of if

            boolean isPossible = false; // instantiate is possible
            double left = 0.0; // instantiate left
            double right = 100001.0; // instantiate right
            double currentHeight = (left + right) / 2.0; // instantiate current height
            double error = 0.0001; // instantiate error

            while (right - left > 0.000001) { // loop through binary search
                double currentTotal = 0.0; // instantiate current total
                for (int i = 0; i < numberStrip; i++) { // loop through strip heights
                    if (stripHeights[i] > currentHeight) { // if it is necessary to cut
                        currentTotal += (stripHeights[i] - currentHeight); // add cut height to current total
                    } // end of if
                } // end of loop through strip heights

                if (Math.abs(currentTotal - desiredHeight) < error) { // if it is possible
                    System.out.printf("%.4f\n", currentHeight); // print result
                    isPossible = true; // change is possible
                    break; // break loop
                } // end of if

                if (currentTotal < desiredHeight) { // if it is possible
                    right = currentHeight; // change right
                    currentHeight = (right + left) / 2.0; // change current height
                } // end of if
                
                if (currentTotal > desiredHeight) { // if it is not possible
                    left = currentHeight; // change left
                    currentHeight = (right + left) / 2.0; // change current height
                } // end of if
            } // end of loop through binary search

            if (isPossible) { // if it is possible
                continue; // continue loop
            } // end of if
            System.out.printf("%.4f\n", currentHeight); // print result
        }

        scanner.close(); // close Scanner object
    } // end of main method
} // end of HelpSeuMadruga class

/*
 * beecrowd | 1912
 * Help Seu Madruga
 * By Dâmi Henrique, Inatel BR Brazil
 * 
 * Timelimit: 4
 * Madruga finally got a job, that’s his big chance to pay the 14 months of rent
 * that are late. He is a paper cutter and he is getting lots of money with this
 * job.
 * 
 * Madruga will receive N rectangular strips of paper of 1 centimeter(cm) of
 * width and C cm of length. The strips should be placed one beside the other so
 * that their bases are aligned(check the image). The task is, with only one
 * straight cut, parallel to the base, Madruga needs to make the sum of the
 * areas of the cut strips equal to A cm².
 * 
 * See the illustration below with N = 5 and the strips 5, 3, 6, 2, and 3 cm
 * long, respectively, for an A = 3 cm².
 * 
 * width's strips
 * 
 * With a cut made at a height of 4 cm from the base, the resulting area,
 * painted red, is exactly equal to A cm². Your task is to find that heigth H
 * and help Madruga.
 * 
 * Input
 * There will be several test cases. The first line in each case begins with two
 * integers N (1 ≤ N ≤ 105) and A (1 ≤ A ≤ 109) representing respectively the
 * number of strips and the expected resulting area. The next line contains N
 * integers, representing the length Ci (1 <= Ci <= 104) of each strip.
 * 
 * The input ends with A = C = 0, which should not be processed.
 * 
 * Output
 * For each test case, output a single line, the height H of the cut that
 * Madruga must do so that the sum of the area of the cut strips is equal to A
 * cm². Print the answer with 4 decimal places.
 * 
 * Output ":D" if no cutting is required, or "-.-" if it’s impossible.
 * 
 * Input Sample
 * 5 3
 * 
 * 5 3 6 2 3
 * 
 * 4 14
 * 
 * 2 5 2 5
 * 
 * 0 0
 * 
 * Output Sample
 * 
 * 4.0000
 * 
 * :D
 */