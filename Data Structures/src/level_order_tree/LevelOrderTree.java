/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1466
 * beecrowd | 1466 Level Order Tree
 */

package level_order_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class LevelOrderTree {

    private static class TreeNode {

        int data;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    private static TreeNode root;
    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCases = Integer.parseInt(bufferedReader.readLine());
        int caseNumber = 1;

        while (testCases-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine()) - 1;
            String[] dataStrings = bufferedReader.readLine().split(" ");
            int data = Integer.parseInt(dataStrings[0]);
            root = new TreeNode(data);
            int j = 1;

            while (n-- > 0) {
                TreeNode current = root;
                data = Integer.parseInt(dataStrings[j]);

                while (true) {
                    if (data < current.data) {
                        if (current.left == null) {
                            current.left = new TreeNode(data);
                            break;
                        }
                        current = current.left;
                    } else {
                        if (current.right == null) {
                            current.right = new TreeNode(data);
                            break;
                        }
                        current = current.right;
                    }
                }
                j++;
            }

            bufferedWriter.append("Case " + caseNumber + ":\n");
            printLevelOrderTraversal();
            bufferedWriter.newLine();
            caseNumber++;
        }
        bufferedWriter.flush();
    }

    private static void printLevelOrderTraversal() throws IOException {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean hasPrinted = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();

            if (!hasPrinted) {
                bufferedWriter.append("" + node.data);
                hasPrinted = true;
            } else {
                bufferedWriter.append(" " + node.data);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        bufferedWriter.newLine();
    }
}

/*
 * beecrowd | 1466
 * Level Order Tree Traversal
 * By Neilor Tonin, URI Brazil
 * 
 * Timelimit: 2
 * Level order traversal of a tree is breadth first traversal for the tree. It
 * is actually a BFS, which is not recursive by nature. It uses Queue instead of
 * Stack to hold the next vertices that should be opened. The reason for it is
 * in this traversal, you want to open the nodes in a FIFO order, instead of a
 * LIFO order, obtained by recursion.
 * 
 * So the work here is, after some operations of insertion over a binary search
 * tree, print the level order traversal of this tree. For example, an input
 * with the sequence of numbers: 8 3 10 14 6 4 13 7 1 will result in the
 * following tree:
 * 
 * 
 * 
 * With the level order traversal output: 8 3 10 1 6 14 4 7 13.
 * 
 * Input
 * The input file contains many test cases. The first line of input contains an
 * integer C (C ≤ 1000), indicating the number of test cases that follow. Each
 * test case contains two lines. The first line contains a number N (1 ≤ N ≤
 * 500) indicating the amount of numbers that will form each one of the trees.
 * The second line contains the N distinct non-negative numbers, each one
 * separated by a space.
 * 
 * Output
 * For each input set, you should print the message "Case n:", where n is the
 * sequential test case number, followed by one line with the level order
 * traversal of the tree, according to the given example.
 * 
 * Note: None space must be printed after the last number of each line and the
 * program should print a blank line after each test case, even after the last
 * test case. The result tree will have no repeated numbers and no more than 500
 * levels.
 * 
 * Sample Input
 * 2
 * 3
 * 5 2 7
 * 9
 * 8 3 10 14 6 4 13 7 1
 * 
 * Sample Output
 * 
 * Case 1:
 * 5 2 7
 * 
 * Case 2:
 * 8 3 10 1 6 14 4 7 13
 */