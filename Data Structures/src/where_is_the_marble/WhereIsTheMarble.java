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
