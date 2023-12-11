/*******************************************************************************************
 * Universidade Estadual do Sudoeste da Bahia - UESB
 * Departamento de Ciências Exatas e Tecnológicas - DCET
 * Colegiado do Curso de Ciência da Computação - CCComp
 * Disciplina: Algoritmos e Estruturas de Dados
 * Professor: Profa. Me. Maria Luisa Ghizoni Gonzalez
 * 
 * Algorithm: Red-Black Tree
 * 
 * Autores: João Henrique Silva Pinto (202210958), Cauê Rodrigues de Aguiar (202210181),
 *          Ademir de Jesus Reis Júnior (202210327)
 *******************************************************************************************/

 /******************************************************************************************
  * Descrição do código: Implementação da estrutura de dados Red-Black Tree.
  * A estrutura de dados Red-Black Tree é uma árvore binária de busca balanceada, onde
  * cada nó possui uma cor, que pode ser vermelha ou preta. As propriedades de uma Red-Black
  * Tree são:
  * 1. Todo nó é vermelho ou preto.
  * 2. A raiz é preta.
  * 3. Todo nó folha (NIL) é preto.
  * 4. Se um nó é vermelho, então seus filhos são pretos.
  * 5. Para cada nó, todos os caminhos da raiz até as folhas descendentes contêm o mesmo
  *    número de nós pretos.
  * A estrutura de dados Red-Black Tree possui as seguintes operações:
  * 1. Insert: Insere um novo nó na árvore.
  * 2. Delete: Remove um nó da árvore.
  * 3. Search: Busca um nó na árvore.
  * 4. Print: Imprime a árvore.
  * A estrutura de dados Red-Black Tree é utilizada para implementar a estrutura de dados
    * TreeMap, que é uma implementação da interface Map, e é utilizada para armazenar os
    * elementos em uma estrutura de dados balanceada, onde os elementos são ordenados de
    * acordo com a ordem natural dos elementos ou de acordo com um Comparator.
  *
  ******************************************************************************************/

package red_black_tree; // package declaration

import java.util.Scanner; // import Scanner class

enum Color { // enum for the color of the node
    RED, BLACK // red or black
} // end enum Color

class Node { // class for the node of the tree
    int data; // data of the node
    Node parent, left, right; // parent, left child and right child of the node
    Color color; // color of the node

    /**
     * Constructor for the Node class
     * @param data data of the node
     */
    public Node(int data) { // constructor
        this.data = data; // initialize data
        this.color = Color.RED; // initialize color as red
        this.parent = this.left = this.right = null; // initialize parent, left child and right child as null
    } // end constructor
} // end class Node

public class RedBlackTree { // class for the Red-Black Tree
    private Node root; // root of the tree
    private Node nil; // nil node

    /**
     * Constructor for the RedBlackTree class
     */
    public RedBlackTree() { // constructor
        nil = new Node(0); // initialize nil node
        nil.color = Color.BLACK; // initialize color of nil node as black
        root = nil; // initialize root as nil node
    } // end constructor

    /**
     * Left rotate method for the Red-Black Tree class (used in the insert method)
     * @param x node to rotate
     */
    private void leftRotate(Node x) { // left rotate
        Node y = x.right; // y is the right child of x
        x.right = y.left; // y's left child becomes x's right child

        if (y.left != nil) { // if y's left child is not nil
            y.left.parent = x; // y's left child's parent becomes x
        } // end if

        y.parent = x.parent; // y's parent becomes x's parent

        if (x.parent == null) { // if x's parent is null
            root = y; // y becomes the root
        } else if (x == x.parent.left) { // if x is the left child of its parent
            x.parent.left = y; // y becomes the left child of x's parent
        } else { // if x is the right child of its parent
            x.parent.right = y; // y becomes the right child of x's parent
        } // end if

        y.left = x; // x becomes the left child of y
        x.parent = y; // y becomes the parent of x

        System.out.println("Left Rotate: " + x.data + " -> " + y.data); // print left rotate
    } // end method leftRotate

    /**
     * Right rotate method for the Red-Black Tree class (used in the insert method)
     * @param y node to rotate
     */
    private void rightRotate(Node y) { // right rotate
        Node x = y.left; // x is the left child of y
        y.left = x.right; // x's right child becomes y's left child

        if (x.right != nil) { // if x's right child is not nil
            x.right.parent = y; // x's right child's parent becomes y
        } // end if

        x.parent = y.parent; // x's parent becomes y's parent

        if (y.parent == null) { // if y's parent is null
            root = x; // x becomes the root
        } else if (y == y.parent.right) { // if y is the right child of its parent
            y.parent.right = x; // x becomes the right child of y's parent
        } else { // if y is the left child of its parent
            y.parent.left = x; // x becomes the left child of y's parent
        } // end if

        x.right = y; // y becomes the right child of x
        y.parent = x; // x becomes the parent of y

        System.out.println("Right Rotate: " + y.data + " -> " + x.data); // print right rotate
    } // end method rightRotate

    /**
     * Fix insert method for the Red-Black Tree class (used in the insert method)
     * @param z node to fix
     */
    private void fixInsert(Node z) { // fixInsert method
        while (z.parent != null && z.parent.color == Color.RED) { // while z's parent is not null and z's parent's color is red
            if (z.parent == z.parent.parent.left) { // if z's parent is the left child of z's grandparent
                Node y = z.parent.parent.right; // y is z's uncle
                if (y != null && y.color == Color.RED) { // if y is not null and y's color is red
                    // Case 1: z's uncle y is red
                    System.out.println("Case 1: Fixing RED-RED violation (Left): " + z.data); // print case 1
                    System.out.println("Changing colors: " + z.parent.data + " (BLACK) <- " + y.data + " (BLACK) <- "
                            + z.parent.parent.data + " (BLACK)"); // print changing colors

                    y.color = Color.BLACK; // y's color becomes black
                    z.parent.color = Color.BLACK; // z's parent's color becomes black
                    z.parent.parent.color = Color.RED; // z's grandparent's color becomes red
                    z = z.parent.parent; // z becomes z's grandparent
                } else { // if y is null or y's color is black
                    if (z == z.parent.right && z.parent != null) { // if z is a right child and z's parent is not null
                        // Case 2: z's uncle y is black and z is a right child
                        System.out.println("Case 2: Fixing RED-RED violation (Left): " + z.data); // print case 2
                        System.out.println("Left Rotate: " + z.parent.data + " -> " + z.data); // print left rotate
                        z = z.parent; // z becomes z's parent
                        leftRotate(z); // left rotate z
                    } // end if
                    // Case 3: z's uncle y is black and z is a left child
                    System.out.println("Case 3: Fixing RED-RED violation (Left): " + z.data); // print case 3
                    System.out.println("Changing colors: " + z.parent.data + " (BLACK) <- " + z.parent.parent.data
                            + " (RED)"); // print changing colors
                    if (z.parent != null) { // if z's parent is not null
                        z.parent.parent.color = Color.RED; // z's grandparent's color becomes red
                        z.parent.color = Color.BLACK; // z's parent's color becomes black
                        rightRotate(z.parent.parent); // right rotate z's grandparent
                    } // end if
                } // end if
            } else { // if z's parent is the right child of z's grandparent
                Node y = z.parent.parent.left; // y is z's uncle
                if (y != null && y.color == Color.RED) { // if y is not null and y's color is red
                    // Case 1: z's uncle y is red
                    System.out.println("Case 1: Fixing RED-RED violation (Right): " + z.data); // print case 1
                    System.out.println("Changing colors: " + z.parent.data + " (BLACK) <- " + y.data + " (BLACK) <- "
                            + z.parent.parent.data + " (RED)"); // print changing colors
                    z.parent.color = Color.BLACK; // z's parent's color becomes black
                    y.color = Color.BLACK; // y's color becomes black
                    z.parent.parent.color = Color.RED; // z's grandparent's color becomes red
                    z = z.parent.parent; // z becomes z's grandparent
                } else { // if y is null or y's color is black
                    if (z == z.parent.left && z.parent != null) { // if z is a left child and z's parent is not null
                        // Case 2: z's uncle y is black and z is a left child
                        System.out.println("Case 2: Fixing RED-RED violation (Right): " + z.data); // print case 2
                        System.out.println("Right Rotate: " + z.parent.data + " -> " + z.data); // print right rotate
                        z = z.parent; // z becomes z's parent
                        rightRotate(z); // right rotate z
                    } // end if
                    // Case 3: z's uncle y is black and z is a right child
                    System.out.println("Case 3: Fixing RED-RED violation (Right): " + z.data); // print case 3
                    System.out.println("Changing colors: " + z.parent.data + " (BLACK) <- " + z.parent.parent.data
                            + " (RED)"); // print changing colors
                    if (z.parent != null) { // if z's parent is not null
                        z.parent.color = Color.BLACK; // z's parent's color becomes black
                        if (z.parent.parent != null) { // if z's grandparent is not null
                            z.parent.parent.color = Color.RED; // z's grandparent's color becomes red
                            leftRotate(z.parent.parent); // left rotate z's grandparent
                        } // end if
                    } // end if
                } // end if
            } // end if
        } // end while

        if (root != null) // if root is not null
            root.color = Color.BLACK; // root's color becomes black
    } // end method fixInsert

    /**
     * Insert method for the Red-Black Tree class
     * @param key key of the node to insert
     */
    public void insert(int key) { // insert method
        Node node = new Node(key); // create a new node with the given key
        Node y = null; // y is null
        Node x = root; // x is the root

        while (x != nil) { // while x is not nil
            y = x; // y becomes x
            if (node.data < x.data) { // if the key of the new node is less than the key of x
                x = x.left; // x becomes the left child of x
            } else { // if the key of the new node is greater than or equal to the key of x
                x = x.right; // x becomes the right child of x
            } // end if
        } // end while

        node.parent = y; // y becomes the parent of the new node
        if (y == null) { // if y is null
            root = node; // the new node becomes the root
        } else if (node.data < y.data) { // if the key of the new node is less than the key of y
            y.left = node; // the new node becomes the left child of y
        } else { // if the key of the new node is greater than or equal to the key of y
            y.right = node; // the new node becomes the right child of y
        } // end if

        node.left = nil; // the left child of the new node becomes nil
        node.right = nil; // the right child of the new node becomes nil
        node.color = Color.RED; // the color of the new node becomes red

        fixInsert(node); // fix the tree
        System.out.println("Node Inserted: " + key); // print node inserted
        printTree(); // print the tree
    } // end method insert

    /**
     * Fix delete method for the Red-Black Tree class (used in the delete method)
     * @param x node to fix
     * @return the minimum node
     */
    private Node minimum(Node x) { // minimum method
        while (x != null && x.right != nil) { // while x is not null and x's left child is not nil
            x = x.left; // x becomes the left child of x
        } // end while

        return x; // return the minimum node
    } // end method minimum

    /**
     * Fix delete method for the Red-Black Tree class (used in the delete method)
     * @param x node to fix
     * @return the maximum node
     */
    private Node maximum(Node x) { // maximum method
        while (x != null && x.right != nil) { // while x is not null and x's right child is not nil
            x = x.right; // x becomes the right child of x
        } // end while

        return x; // return the maximum node
    } // end method maximum

    /**
     * Fix delete method for the Red-Black Tree class (used in the delete method)
     * @param x node to fix
     */
    private void fixDelete(Node x) { // fixDelete method
        while (x != root && x.color == Color.BLACK) { // while x is not the root and x's color is black
            if (x == x.parent.left) { // if x is the left child of its parent
                Node w = x.parent.right; // w is x's sibling
                if (w != null) { // if w is not null
                    if (w.color == Color.RED) { // if w's color is red
                        w.color = Color.BLACK; // w's color becomes black
                        x.parent.color = Color.RED; // x's parent's color becomes red
                        leftRotate(x.parent); // left rotate x's parent
                        System.out.println("Left Rotate: " + x.parent.data + " -> " + x.parent.right.data); // print left rotate
                        w = x.parent.right; // w becomes x's sibling
                    } // end if
                    if (w.left != null && w.right != null && w.left.color == Color.BLACK
                            && w.right.color == Color.BLACK) { // if w's left child and w's right child are black
                        w.color = Color.RED; // w's color becomes red
                        System.out.println("Changing color: " + w.data + " (RED)"); // print changing color
                        x = x.parent; // x becomes x's parent
                    } else { // if w's left child or w's right child is red
                        if (w.right != null && w.right.color == Color.BLACK) { // if w's right child is black
                            w.left.color = Color.BLACK; // w's left child's color becomes black
                            w.color = Color.RED; // w's color becomes red
                            rightRotate(w); // right rotate w
                            System.out.println("Right Rotate: " + w.data + " -> " + w.left.data); // print right rotate
                            w = x.parent.right; // w becomes x's sibling
                        } // end if
                        if (w.right != null) { // if w's right child is not null
                            if (w.right.color == Color.RED) { // if w's right child's color is red
                                w.color = x.parent.color; // w's color becomes x's parent's color
                                x.parent.color = Color.BLACK; // x's parent's color becomes black
                                if (w.right != null) { // if w's right child is not null
                                    w.right.color = Color.BLACK; // w's right child's color becomes black
                                } // end if
                                leftRotate(x.parent); // left rotate x's parent
                                System.out.println("Left Rotate: " + x.parent.data + " -> " + w.data); // print left rotate
                                System.out
                                        .println("Changing colors: " + x.parent.data + " (BLACK) <- " + w.data + " (RED)"); // print changing colors
                                x = root; // x becomes the root
                            } // end if
                        } // end if
                    } // end if
                } // end if
            } else { // if x is the right child of its parent
                Node w = x.parent.left; // w is x's sibling
                if (w != null) { // if w is not null
                    if (w.color == Color.RED) { // if w's color is red
                        w.color = Color.BLACK; // w's color becomes black
                        x.parent.color = Color.RED; // x's parent's color becomes red
                        rightRotate(x.parent); // right rotate x's parent
                        System.out.println("Right Rotate: " + x.parent.data + " -> " + x.parent.left.data); // print right rotate
                        w = x.parent.left; // w becomes x's sibling
                    } // end if

                    if (w.left != null && w.left.color == Color.RED) { // if w's left child is red
                        w.left.color = Color.BLACK; // w's left child's color becomes black
                        w.color = Color.RED; // w's color becomes red
                        leftRotate(w); // left rotate w
                        System.out.println("Left Rotate: " + w.data + " -> " + w.left.data); // print left rotate
                        w = x.parent.left; // w becomes x's sibling
                    } // end if

                    if (w.right != null && w.right.color == Color.BLACK && w.left != null && w.left.color == Color.BLACK) {
                    //if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) { // if w's right child and w's left child are black
                        w.color = Color.RED; // w's color becomes red
                        System.out.println("Changing color: " + w.data + " (RED)"); // print changing color
                        x = x.parent; // x becomes x's parent
                    } else { // if w's right child or w's left child is red
                        if (w.left != null && w.left.color == Color.BLACK) { // if w's left child is black
                            w.right.color = Color.BLACK; // w's right child's color becomes black
                            w.color = Color.RED; // w's color becomes red
                            leftRotate(w); // left rotate w
                            System.out.println("Left Rotate: " + w.data + " -> " + w.right.data); // print left rotate
                            w = x.parent.left; // w becomes x's sibling
                        } // end if
                        if (w != null) { // if w is not null
                            w.color = x.parent.color; // w's color becomes x's parent's color
                            x.parent.color = Color.BLACK; // x's parent's color becomes black
                            if (w.left != null) { // if w's left child is not null
                                w.left.color = Color.BLACK; // w's left child's color becomes black
                            } // end if
                            rightRotate(x.parent); // right rotate x's parent
                            System.out.println("Right Rotate: " + x.parent.data + " -> " + w.data); // print right rotate
                            System.out.println(
                                    "Changing colors: " + x.parent.data + " (BLACK) <- " + w.data + " (BLACK)"); // print changing colors
                            x = root; // x becomes the root
                        } // end if
                    } // end if
                } // end if
            } // end if
        } // end while

        if (x != null) // if x is not null
            x.color = Color.BLACK; // x's color becomes black
    } // end method fixDelete

    /**
     * Transplant method for the Red-Black Tree class (used in the delete method)
     * @param u node to transplant
     * @param v node to transplant
     */
    private void transplant(Node u, Node v) { // transplant method
        if (u.parent == null) { // if u's parent is null
            root = v; // v becomes the root
            System.out.println("Changing root: " + u.data + " -> " + v.data); // print changing root
        } else if (u == u.parent.left) { // if u is the left child of its parent
            u.parent.left = v; // v becomes the left child of u's parent
            System.out.println("Changing left child: " + u.parent.data + " -> " + v.data); // print changing left child
        } else { // if u is the right child of its parent
            u.parent.right = v; // v becomes the right child of u's parent
            System.out.println("Changing right child: " + u.parent.data + " -> " + v.data); // print changing right child
        } // end if

        v.parent = u.parent; // u's parent becomes v's parent
        System.out.println("Setting parent of: " + v.data + ": " + (u.parent != null ? u.parent.data : "null")); // print setting parent
    } // end method transplant -

    /**
     * Delete method for the Red-Black Tree class
     * @param key key of the node to delete
     */
    public void delete(int key) { // delete method
        Node z = search(key); // z is the node to delete

        if (z == null) { // if z is null
            System.out.println("Node not found!"); // print node not found
            return; // return
        } // end if

        System.out.println("Node Deleted: " + key); // print node deleted

        Node y = z; // y is z
        Node x; // x is null
        Color originalColor = y.color; // originalColor is y's color

        if (z.left == nil) { // if z's left child is nil
            x = z.right; // x becomes z's right child
            transplant(z, z.right); // transplant z with z's right child
        } else if (z.right == nil) { // if z's right child is nil
            x = z.left; // x becomes z's left child
            transplant(z, z.left); // transplant z with z's left child
        } else { // if z has two children
            y = minimum(z.right); // y is the minimum node of z's right subtree
            originalColor = y.color; // originalColor is y's color
            x = y.right; // x is y's left child

            if (y.parent == z) { // if y's parent is z
                x.parent = y; // y becomes x's parent
            } else { // if y's parent is not z
                transplant(y, y.right); // transplant y with y's right child
                y.right = z.right; // z's right child becomes y's right child
                y.right.parent = y; // y becomes y's right child's parent
            } // end if

            transplant(z, y); // transplant z with y
            y.left = z.left; // z's left child becomes y's left child
            y.left.parent = y; // y becomes y's left child's parent
            y.color = z.color; // z's color becomes y's color
        } // end if

        if (originalColor == Color.BLACK) { // if originalColor is black
            fixDelete(x); // fix the tree
        } // end if

        printTree(); // print the tree
    } // end method delete

    /**
     * Search method for the Red-Black Tree class (used in the delete method)
     * @param key key of the node to search
     * @return the node with the given key
     */
    public Node search(int key) { // search method

        return search(root, key); // return the node with the given key
    } // end method search

    /** Search method for the Red-Black Tree class (used in the delete method)
     * @param root root of the tree
     * @param key key of the node to search
     * @return the node with the given key
     */
    private Node search(Node root, int key) { // search method
        if (root == nil || root.data == key) { // if root is nil or root's data is equal to the key

            return root; // return root
        } // end if

        if (key < root.data) { // if the key is less than root's data

            return search(root.left, key); // return the left child of root
        } else { // if the key is greater than or equal to root's data

            return search(root.right, key); // return the right child of root
        } // end if
    } // end method search

    /**
     * Print method for the Red-Black Tree class
     */
    private void printTree() { // printTree method
        System.out.println("\nCurrent State of the Tree:"); // print current state of the tree
        printTree(root, 0); // print the tree
        System.out.println(); // print the tree
    } // end method printTree

    /**
     * Print method for the Red-Black Tree class
     * @param root root of the tree
     * @param indent indent of the tree
     */
    private void printTree(Node root, int indent) { // printTree method
        if (root != nil) { // if root is not nil
            printTree(root.right, indent + 4); // print the right subtree

            for (int i = 0; i < indent; i++) { // for each indent
                System.out.print(" "); // print a space
            } // end for

            System.out.println(root.data + " " + (root.color == Color.RED ? "RED" : "BLACK")); // print root's data and color
            printTree(root.left, indent + 4); // print the left subtree
        } // end if
    } // end method printTree

    /**
     * Main method for the Red-Black Tree class
     * @param args command line arguments
     */
    public static void main(String[] args) { // main method
        Scanner scanner = new Scanner(System.in); // scanner for input
        RedBlackTree redBlackTree = new RedBlackTree(); // create a new Red-Black Tree

        System.out.print("Enter the number of nodes to insert: "); // prompt for the number of nodes to insert
        int numNodes = scanner.nextInt(); // number of nodes to insert

        System.out.println("Enter the values of each node:"); // prompt for the values of each node

        for (int i = 0; i < numNodes; i++) { // for each node
            System.out.print("Node " + (i + 1) + ": "); // prompt for the node
            int value = scanner.nextInt(); // value of the node
            redBlackTree.insert(value); // insert the node
        } // end for

        while (true) { // while true
            System.out.println("\nChoose an option:"); // prompt for an option
            System.out.println("1. Insert a node"); // prompt for an option
            System.out.println("2. Delete a node"); // prompt for an option
            System.out.println("3. Print the tree"); // prompt for an option
            System.out.println("4. Exit"); // prompt for an option
            System.out.print("Option: "); // prompt for an option

            int option = scanner.nextInt(); // option

            switch (option) { // switch for the option
                case 1: // if the option is 1
                    System.out.print("Enter the value to insert: "); // prompt for the value to insert
                    int insertValue = scanner.nextInt(); // value to insert
                    redBlackTree.insert(insertValue); // insert a node
                    break; // insert a node
                case 2: // if the option is 2 
                    System.out.print("Enter the value to delete: "); // prompt for the value to delete
                    int deleteValue = scanner.nextInt(); // value to delete
                    redBlackTree.delete(deleteValue); // delete a node
                    break; // delete a node
                case 3: // if the option is 3
                    redBlackTree.printTree(); // print the tree
                    break; // print the tree
                case 4: // if the option is 4
                    System.out.println("Exiting program."); // print exiting program
                    scanner.close(); // close the scanner
                    System.exit(0); // exit the program
                default: // if the option is invalid
                    System.out.println("Invalid option. Please try again."); // print invalid option
            } // end switch
        } // end while
    } // end method main
} // end class RedBlackTree