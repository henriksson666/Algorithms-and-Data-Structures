import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int key, height;
    Node left, right;

    Node(int value) {
        key = value;
        height = 1;
        left = right = null;
    }
}

public class AVLTree {
    Node root;

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int balanceFactor(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), 
                        height(y.right)) + 1;
        x.height = Math.max(height(x.left),
                        height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left),
                            height(x.right)) + 1;
        y.height = Math.max(height(y.left),
                            height(y.right)) + 1;

        return y;
    }


    int[] toArray() {
        ArrayList<Integer> list = new ArrayList<>();
        toArrayHelper(root, list);

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).intValue();
        }

        return array;
    }

    
    void toArrayHelper(Node node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }

        toArrayHelper(node.left, list);
        list.add(node.key);
        toArrayHelper(node.right, list);
    }

    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        if (balance > 1) {
            if (key < node.left.key) {
                System.out.println("Right Rotation at Node: " + node.key);
                return rightRotate(node);
            } else {
                System.out.println("Left-Right Rotation at Node: " + node.key);
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        
        if (balance < -1) {
            if (key > node.right.key) {
                System.out.println("Left Rotation at Node: " + node.key);
                return leftRotate(node);
            } else {
                System.out.println("Right-Left Rotation at Node: " + node.key);
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    Node delete(Node root, int key) {
        if (root == null) 
            return root;
        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            Node temp = null;
            if((root.left == null) || (root.right == null)) {
                if (temp == root.left) 
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else 
                    root = temp;
            } else {
                temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = balanceFactor(root);

        if (balance > 1 && balanceFactor(root.left) >= 0) {
            System.out.println("Right Rotation at Node: " + root.key);
            return rightRotate(root);
        }

        if (balance > 1 && balanceFactor(root.left) < 0) {
            System.out.println("Left-Right Rotation at Node: " + root.key);
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && balanceFactor(root.right) <= 0) {
            System.out.println("Left Rotation at Node: " + root.key);
            return leftRotate(root);
        }

        if (balance < -1 && balanceFactor(root.right) > 0) {
            System.out.println("Right-Left Rotation at Node: " + root.key);
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    void printTree(Node root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
        
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println("Key: " + root.key + " Height: " + root.height +
                                " Balance Factor: " + balanceFactor(root) + "\n");

            printTree(root.left, indent, false);
            printTree(root.right, indent, true);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        System.out.print("Type the number of elements of the tree, followed by the values "
                            + "to be inserted (Example: 5 10 25 18 41 3): ");
        String[] input = scanner.nextLine().split(" ");

        int[] values = new int[Integer.parseInt(input[0])];

        for (int i = 1; i < input.length; i++) {
            values[i - 1] = Integer.parseInt(input[i]);
        }

        for (int value : values) {
            tree.root = tree.insert(tree.root, value);
            System.out.println("Balanced AVL Tree after inserting " + value + ":");
            tree.printTree(tree.root, "", true);
            System.out.println("------------------------------");
        }

        while (true) {
            System.err.print("Enter (A) to add or (R) to remove a node, (P) to print the tree or (E) to exit: ");
            char choice = scanner.next().charAt(0); 

            if (choice == 'E' || choice == 'e') {
                break;
            }

            if (choice == 'A' || choice == 'a') {
                System.out.print("Enter the value to be inserted: ");
                int value = scanner.nextInt();
                tree.root = tree.insert(tree.root, value);
                System.out.println("Balanced AVL Tree after inserting " + value + ":");
                tree.printTree(tree.root, "", true);
            } else if (choice == 'R' || choice == 'r') {
                System.out.print("Enter the value to be removed: ");
                int value = scanner.nextInt();
                tree.root = tree.delete(tree.root, value);
                System.out.println("Balanced AVL Tree after removing " + value + ":");
                tree.printTree(tree.root, "", true);
            } else if(choice == 'P' || choice == 'p') {
                System.out.print("AVL Tree as Array: ");
                int[] array = tree.toArray();
                for (int value : array) {
                    System.out.print(value + " ");
                }
                System.out.println();
            } else {
                System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
    
}