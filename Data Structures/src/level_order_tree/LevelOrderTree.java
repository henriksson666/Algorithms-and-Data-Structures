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
