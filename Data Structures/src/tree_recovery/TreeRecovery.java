/*
 * Author: João Henrique Silva Pinto
 * Link: https://www.beecrowd.com.br/judge/en/problems/view/1191
 * beecrowd | 1191 Tree Recovery
 */

package tree_recovery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class TreeRecovery {

    private static Node[] node;
    private static char[] preOrder, inOrder;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    private static class Node {
        int id;
        char data;

        public Node(int id, char data) {
            this.id = id;
            this.data = data;
        }
    }

    private static class BinaryTree {
        int size;
        int preIndex = 0;

        public BinaryTree(int size) {
            this.size = size - 1;
        }

        void printPostOrder(Node[] node, int insideStart, int insideEnd) throws IOException {
            if (insideStart > insideEnd)
                return;
            int insideIndex = search(inOrder, insideStart, insideEnd, preOrder[preIndex++]);
            printPostOrder(node, insideStart, insideIndex - 1);
            printPostOrder(node, insideIndex + 1, insideEnd);
            bufferedWriter.append(node[insideIndex].data);
        }

        int search(char[] inside, int startIndex, int endIndex, int data) {
            int i = 0;
            for (i = startIndex; i < endIndex; i++)
                if (inside[i] == data)
                    return i;
            return i;
        }
    }

    public static void main(String[] args) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String inside;
        while ((inside = bufferedReader.readLine()) != null) {
            if (inside.isEmpty())
                continue;
            String[] st = inside.split(" ");
            preOrder = st[0].toCharArray();
            inOrder = st[1].toCharArray();
            int size = preOrder.length;
            node = new Node[size];
            for (int j = 0; j < size; j++) {
                node[j] = new Node(j, inOrder[j]);
            }
            BinaryTree binaryTree = new BinaryTree(size);
            binaryTree.printPostOrder(node, 0, size - 1);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
}

/*
 * beecrowd | 1191
 * Tree Recovery
 * By Sebastião Alves Brazil
 * 
 * Timelimit: 1
 * Little Valentine liked playing with binary trees very much. Her favorite game
 * was constructing randomly looking binary trees with capital letters in the
 * nodes.
 * 
 * This is an example of one of her creations:
 * 
 * 
 * D
 * / \
 * / \
 * B E
 * / \ \
 * / \ \
 * A C G
 * /
 * /
 * F
 * 
 * 
 * To record her trees for future generations, she wrote down two strings for
 * each tree: a preorder traversal (root, left subtree, right subtree) and an
 * inorder traversal (left subtree, root, right subtree).
 * 
 * For the tree drawn above the preorder traversal is DBACEGF and the inorder
 * traversal is ABCDEFG.
 * 
 * She thought that such a pair of strings would give enough information to
 * reconstruct the tree later (but she never tried it).
 * 
 * Now, years later, looking again at the strings, she realized that
 * reconstructing the trees was indeed possible, but only because she never had
 * used the same letter twice in the same tree.
 * 
 * However, doing the reconstruction by hand, soon turned out to be tedious.
 * 
 * So now she asks you to write a program that does the job for her!
 * 
 * Input
 * The input file will contain one or more test cases. Each test case consists
 * of one line containing two strings preord and inord, representing the
 * preorder traversal and inorder traversal of a binary tree. Both strings
 * consist of unique capital letters. (Thus they are not longer than 26
 * characters.)
 * 
 * Input is terminated by end of file.
 * 
 * Output
 * For each test case, recover Valentine's binary tree and print one line
 * containing the tree's postorder traversal (left subtree, right subtree,
 * root).
 * 
 * Input Sample
 * DBACEGF ABCDEFG
 * BCAD CBAD
 * 
 * Output Sample
 * ACBFGED
 * CDAB
 */