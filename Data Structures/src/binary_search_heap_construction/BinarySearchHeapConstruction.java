/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1675
 * beecrowd | 1675 Binary Search Heap Construction
 */

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
        // System.out.print('(');
        if (D[node].leftChild != 0)
            dfsPrint(D[node].leftChild, writer);
        writer.write(String.format("%s/%d", s[D[node].index][0], D[node].value));
        // System.out.printf("%s/%d", s[D[node].index][0], D[node].value);
        if (D[node].rightChild != 0)
            dfsPrint(D[node].rightChild, writer);
        // System.out.print(')');
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

            // dfsPrint(D[0].rightChild, writer);
            writer.write(dfsPrint(D[0].rightChild, new StringBuilder()));
            writer.newLine();
            writer.flush();
            // System.out.println();
        }
        writer.close();
        scanner.close();
    }
}

/*
 * beecrowd | 1675
 * Binary Search Heap Construction
 * Local Contest, University of Ulm DE Germany
 * 
 * Timelimit: 2
 * Read the statement of problem G for the definitions concerning trees. In the
 * following we define the basic terminology of heaps. A heap is a tree whose
 * internal nodes have each assigned a priority (a number) such that the
 * priority of each internal node is less than the priority of its parent. As a
 * consequence, the root has the greatest priority in the tree, which is one of
 * the reasons why heaps can be used for the implementation of priority queues
 * and for sorting.
 * 
 * A binary tree in which each internal node has both a label and a priority,
 * and which is both a binary search tree with respect to the labels and a heap
 * with respect to the priorities, is called a treap. Your task is, given a set
 * of label-priority-pairs, with unique labels and unique priorities, to
 * construct a treap containing this data.
 * 
 * Input
 * The input contains several test cases. Every test case starts with an integer
 * n. You may assume that 1≤ n ≤ 50000. Then follow n pairs of strings and
 * numbers l1/p1,...,ln/pn denoting the label and priority of each node. The
 * strings are non-empty and composed of lower-case letters, and the numbers are
 * non-negative integers. The last test case is followed by a zero.
 * 
 * Output
 * For each test case output on a single line a treap that contains the
 * specified nodes. A treap is printed as (<left sub-treap > <label
 * >/<priority><right sub-treap>). The sub-treaps are printed recursively, and
 * omitted if leafs.
 * 
 * Sample Input
 * 7 a/7 b/6 c/5 d/4 e/3 f/2 g/1
 * 
 * 7 a/1 b/2 c/3 d/4 e/5 f/6 g/7
 * 
 * 7 a/3 b/6 c/4 d/7 e/2 f/5 g/1
 * 
 * 0
 * 
 * Sample Output
 * 
 * (a/7(b/6(c/5(d/4(e/3(f/2(g/1)))))))
 * 
 * (((((((a/1)b/2)c/3)d/4)e/5)f/6)g/7)
 * 
 * (((a/3)b/6(c/4))d/7((e/2)f/5(g/1)))
 * 
 */