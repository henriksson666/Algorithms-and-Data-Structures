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