package red_black_tree;

import java.util.Scanner;

enum Color {
    RED, BLACK
}

class Node {
    int data;
    Node parent, left, right;
    Color color;

    public Node(int data) {
        this.data = data;
        this.color = Color.RED;
        this.parent = this.left = this.right = null;
    }
}

public class RedBlackTree {
    private Node root;
    private Node nil;

    public RedBlackTree() {
        nil = new Node(0);
        nil.color = Color.BLACK;
        root = nil;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != nil) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != nil) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    private void fixInsert(Node z) {
        while (z.parent != null && z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right && z.parent != null) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    if (z.parent != null) {
                        z.parent.color = Color.BLACK;
                        if (z.parent.parent != null) {
                            z.parent.parent.color = Color.RED;
                            rightRotate(z.parent.parent);
                        }
                    }
                }
            } else {
                Node y = z.parent.parent.left;
                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left && z.parent != null) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    if (z.parent != null) {
                        z.parent.color = Color.BLACK;
                        if (z.parent.parent != null) {
                            z.parent.parent.color = Color.RED;
                            leftRotate(z.parent.parent);
                        }
                    }
                }
            }
        }
        if (root != null)
            root.color = Color.BLACK;
    }

    public void insert(int key) {
        Node node = new Node(key);
        Node y = null;
        Node x = root;

        while (x != nil) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        node.left = nil;
        node.right = nil;
        node.color = Color.RED;

        fixInsert(node);
        printTree();
    }

    private Node minimum(Node x) {
        while (x != null && x.left != nil) {
            x = x.left;
        }

        return x;
    }

    private Node maximum(Node x) {
        while (x != null && x.right != nil) {
            x = x.right;
        }

        return x;
    }

    private void fixDelete(Node x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w != null) {
                    if (w.color == Color.RED) {
                        w.color = Color.BLACK;
                        x.parent.color = Color.RED;
                        leftRotate(x.parent);
                        w = x.parent.right;
                    }
                    if (w.left != null && w.right != null && w.left.color == Color.BLACK
                            && w.right.color == Color.BLACK) {
                        w.color = Color.RED;
                        x = x.parent;
                    } else {
                        if (w.right != null && w.right.color == Color.BLACK) {
                            w.left.color = Color.BLACK;
                            w.color = Color.RED;
                            rightRotate(w);
                            w = x.parent.right;
                        }
                        if (w.right != null) {
                            w.color = x.parent.color;
                            x.parent.color = Color.BLACK;
                            if (w.right != null) {
                                w.right.color = Color.BLACK;
                            }
                            leftRotate(x.parent);
                            x = root;
                        }
                    }
                }
            } else {
                Node w = x.parent.left;
                if (w != null) {
                    if (w.color == Color.RED) {
                        w.color = Color.BLACK;
                        x.parent.color = Color.RED;
                        rightRotate(x.parent);
                        w = x.parent.left;
                    }
                    if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
                        w.color = Color.RED;
                        x = x.parent;
                    } else {
                        if (w.left != null && w.left.color == Color.BLACK) {
                            w.right.color = Color.BLACK;
                            w.color = Color.RED;
                            leftRotate(w);
                            w = x.parent.left;
                        }
                        if (w != null) {
                            w.color = x.parent.color;
                            x.parent.color = Color.BLACK;
                            if (w.left != null) {
                                w.left.color = Color.BLACK;
                            }
                            rightRotate(x.parent);
                            x = root;
                        }
                    }
                }
            }
        }

        if (x != null)
            x.color = Color.BLACK;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        v.parent = u.parent;
    }

    public void delete(int key) {
        Node z = search(key);

        if (z == null) {
            System.out.println("Node not found!");
            return;
        }

        Node y = z;
        Node x;
        Color originalColor = y.color;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            // y = minimum(z.right);
            // y = maximum(z.left);
            originalColor = y.color;
            x = y.left;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (originalColor == Color.BLACK) {
            fixDelete(x);
        }

        printTree();
    }

    public Node search(int key) {

        return search(root, key);
    }

    private Node search(Node root, int key) {
        if (root == nil || root.data == key) {

            return root;
        }

        if (key < root.data) {

            return search(root.left, key);
        } else {

            return search(root.right, key);
        }
    }

    private void printTree() {
        System.out.println("\nCurrent State of the Tree:");
        printTree(root, 0);
        System.out.println();
    }

    private void printTree(Node root, int indent) {
        if (root != nil) {
            printTree(root.right, indent + 4);

            for (int i = 0; i < indent; i++) {
                System.out.print(" ");
            }

            System.out.println(root.data + " " + (root.color == Color.RED ? "RED" : "BLACK"));
            printTree(root.left, indent + 4);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedBlackTree redBlackTree = new RedBlackTree();

        System.out.print("Enter the number of nodes to insert: ");
        int numNodes = scanner.nextInt();

        System.out.println("Enter the values of each node:");

        for (int i = 0; i < numNodes; i++) {
            System.out.print("Node " + (i + 1) + ": ");
            int value = scanner.nextInt();
            redBlackTree.insert(value);
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Print the tree");
            System.out.println("4. Exit");
            System.out.print("Option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    int insertValue = scanner.nextInt();
                    redBlackTree.insert(insertValue);
                    break;
                case 2:
                    System.out.print("Enter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    redBlackTree.delete(deleteValue);
                    break;
                case 3:
                    redBlackTree.printTree();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}