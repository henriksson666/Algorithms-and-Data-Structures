package binary_search_heap_construction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Node {
    int index, value, leftChild, rightChild, parent;
}

public class BinarySearchHeapConstruction {
    static Node[] D = new Node[50005];
    static String[][] s = new String[50005][105];

    static Comparator<Integer> cmp = (a, b) -> s[a][0].compareTo(s[b][0]);

    static void insertCartesianTree(int index, Node[] D) {
        int p = index - 1;
        while (D[p].value < D[index].value)
            p = D[p].parent;
        D[index].leftChild = D[p].rightChild;
        D[p].rightChild = index;
        D[index].parent = p;
    }

    static String dfsPrint(int node, StringBuilder result) {
        if (node == 0)
            return "";
        result.append('(');
        dfsPrint(D[node].leftChild, result);
        result.append(String.format("%s/%d", s[D[node].index][0], D[node].value));
        dfsPrint(D[node].rightChild, result);
        result.append(')');

        return result.toString();
    }

    static void dfsPrint(int node, BufferedWriter writer) throws IOException { 
        if (node == 0 || D[node] == null)
            return;
        writer.write('(');
        //System.out.print('(');
        if (D[node].leftChild != 0)
            dfsPrint(D[node].leftChild, writer);
        writer.write(String.format("%s/%d", s[D[node].index][0], D[node].value));
        //System.out.printf("%s/%d", s[D[node].index][0], D[node].value);
        if (D[node].rightChild != 0)
            dfsPrint(D[node].rightChild, writer);
        //System.out.print(')');
        writer.write(')');
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N;

        while ((N = scanner.nextInt()) != 0) {
            Integer[] x = new Integer[N + 1];
            Integer[] A = new Integer[N + 1];

            for (int i = 1; i <= N; i++) {
                s[i] = scanner.next().split("/");
                x[i] = Integer.parseInt(s[i][1]);
                A[i] = i;
            }

            Arrays.sort(A, 1, N + 1, cmp);

            D[0] = new Node();
            D[0].value = Integer.MAX_VALUE;
            D[0].leftChild = D[0].rightChild = D[0].parent = 0;

            for (int i = 1; i <= N; i++) {
                D[i] = new Node();
                D[i].leftChild = D[i].rightChild = D[i].parent = 0;
                D[i].value = x[A[i]];
                D[i].index = A[i];
            }

            for (int i = 1; i <= N; i++) {
                insertCartesianTree(i, D);
            }

            //dfsPrint(D[0].rightChild, writer);
            writer.write(dfsPrint(D[0].rightChild, new StringBuilder()));
            writer.newLine();
            writer.flush();
            //System.out.println();
        }
        writer.close();
        scanner.close();
    }
}
