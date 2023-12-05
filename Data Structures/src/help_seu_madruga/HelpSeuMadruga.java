/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1912
 * beecrowd | 1912 Help Seu Madruga
 */

package help_seu_madruga;

import java.util.Scanner;

public class HelpSeuMadruga {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int numberPeople = scanner.nextInt();
            int desiredHeight = scanner.nextInt();
            if (numberPeople + desiredHeight == 0) {
                break;
            }

            int[] personHeights = new int[numberPeople];
            int totalHeight = 0;
            for (int i = 0; i < numberPeople; i++) {
                personHeights[i] = scanner.nextInt();
                totalHeight += personHeights[i];
            }

            if (totalHeight < desiredHeight) {
                System.out.println("-.-");
                continue;
            } else if (totalHeight == desiredHeight) {
                System.out.println(":D");
                continue;
            }

            boolean isPossible = false;
            double left = 0.0;
            double right = 100001.0;
            double currentHeight = (left + right) / 2.0;
            double error = 0.0001;

            while (right - left > 0.000001) {
                double currentTotal = 0.0;
                for (int i = 0; i < numberPeople; i++) {
                    if (personHeights[i] > currentHeight) {
                        currentTotal += (personHeights[i] - currentHeight);
                    }
                }

                if (Math.abs(currentTotal - desiredHeight) < error) {
                    System.out.printf("%.4f\n", currentHeight);
                    isPossible = true;
                    break;
                }
                if (currentTotal < desiredHeight) {
                    right = currentHeight;
                    currentHeight = (right + left) / 2.0;
                }
                if (currentTotal > desiredHeight) {
                    left = currentHeight;
                    currentHeight = (right + left) / 2.0;
                }
            }

            if (isPossible) {
                continue;
            }
            System.out.printf("%.4f\n", currentHeight);
        }

        scanner.close();
    }
}

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