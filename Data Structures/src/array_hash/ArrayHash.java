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