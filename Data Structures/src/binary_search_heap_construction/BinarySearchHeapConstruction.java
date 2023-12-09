/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1675
 * beecrowd | 1675 Binary Search Heap Construction
 */

package binary_search_heap_construction; // package declaration is mandatory

import java.io.BufferedWriter; // import BufferedWriter class
import java.io.IOException; // import IOException class
import java.io.OutputStreamWriter; // import OutputStreamWriter class
import java.util.Arrays; // import Arrays class
import java.util.Comparator; // import Comparator class
import java.util.Scanner; // import Scanner class

class Node { // Node class declaration
    int index, value, leftChild, rightChild, parent; // declare index, value, left child, right child and parent
                                                     // attributes
} // end of Node class

public class BinarySearchHeapConstruction { // BinarySearchHeapConstruction class declaration
    static Node[] D = new Node[50005]; // declare D array
    static String[][] s = new String[50005][105]; // declare s array

    static Comparator<Integer> cmp = (a, b) -> s[a][0].compareTo(s[b][0]); // declare cmp comparator

    static void insertCartesianTree(int index, Node[] D) { // insert cartesian tree method
        int p = index - 1; // initialize p
        while (D[p].value < D[index].value) // loop through cartesian tree
            p = D[p].parent; // change p
        D[index].leftChild = D[p].rightChild; // change left child
        D[p].rightChild = index; // change right child
        D[index].parent = p; // change parent
    } // end of insert cartesian tree method

    static String dfsPrint(int node, StringBuilder result) { // dfs print method
        if (node == 0) // if node is null
            return ""; // return empty string
        result.append('('); // append '('
        dfsPrint(D[node].leftChild, result); // dfs print left child
        result.append(String.format("%s/%d", s[D[node].index][0], D[node].value)); // append string
        dfsPrint(D[node].rightChild, result); // dfs print right child
        result.append(')'); // append ')'

        return result.toString(); // return result
    } // end of dfs print method

    static void dfsPrint(int node, BufferedWriter writer) throws IOException { // dfs print method
        if (node == 0 || D[node] == null) // if node is null
            return; // return
        writer.write('('); // write '('
        // System.out.print('('); // print '('
        if (D[node].leftChild != 0) // if left child is not null
            dfsPrint(D[node].leftChild, writer); // dfs print left child
        writer.write(String.format("%s/%d", s[D[node].index][0], D[node].value)); // write string
        // System.out.printf("%s/%d", s[D[node].index][0], D[node].value); // print
        if (D[node].rightChild != 0) // if right child is not null
            dfsPrint(D[node].rightChild, writer); // dfs print right child
        // System.out.print(')'); // print ')'
        writer.write(')'); // write ')'
    } // end of dfs print method

    public static void main(String[] args) throws IOException { // main method declaration
        Scanner scanner = new Scanner(System.in); // instantiate Scanner object
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); // instantiate BufferedWriter
                                                                                         // object
        int N; // declare N

        while ((N = scanner.nextInt()) != 0) { // loop through input
            Integer[] x = new Integer[N + 1]; // declare x array
            Integer[] A = new Integer[N + 1]; // declare A array
 
            for (int i = 1; i <= N; i++) { // loop through input
                s[i] = scanner.next().split("/"); // read string
                x[i] = Integer.parseInt(s[i][1]); // parse string to int
                A[i] = i; // initialize A
            } // end of loop through input

            Arrays.sort(A, 1, N + 1, cmp); // sort A array

            D[0] = new Node(); // initialize D
            D[0].value = Integer.MAX_VALUE; // initialize D
            D[0].leftChild = D[0].rightChild = D[0].parent = 0; // initialize D

            for (int i = 1; i <= N; i++) { // loop through input
                D[i] = new Node(); // initialize D
                D[i].leftChild = D[i].rightChild = D[i].parent = 0; // initialize D
                D[i].value = x[A[i]]; // initialize D
                D[i].index = A[i]; // initialize D
            } // end of loop through input

            for (int i = 1; i <= N; i++) { // loop through input
                insertCartesianTree(i, D); // insert cartesian tree
            } // end of loop through input

            // dfsPrint(D[0].rightChild, writer); // dfs print
            writer.write(dfsPrint(D[0].rightChild, new StringBuilder())); // dfs print
            writer.newLine(); // write new line
            writer.flush(); // flush writer
            // System.out.println(); // print new line
        }
        writer.close(); // close writer
        scanner.close(); // close Scanner object
    } // end of main method
} // end of BinarySearchHeapConstruction class

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