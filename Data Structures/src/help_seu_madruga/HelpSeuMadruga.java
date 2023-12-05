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