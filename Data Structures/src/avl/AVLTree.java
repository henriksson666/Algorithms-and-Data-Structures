/*******************************************************************************************
 * Universidade Estadual do Sudoeste da Bahia - UESB
 * Departamento de Ciências Exatas e Tecnológicas - DCET
 * Colegiado do Curso de Ciência da Computação - CCComp
 * Disciplina: Algoritmos e Estruturas de Dados
 * Professor: Profa. Me. Maria Luisa Ghizoni Gonzalez
 * 
 * Algorithm: AVL Tree
 * 
 * Autores: João Henrique Silva Pinto (202210958), Cauê Rodrigues de Aguiar (202210181),
 *          Ademir de Jesus Reis Júnior (202210327)
 *******************************************************************************************
 */

 /*
  * Descrição AVL Tree:
  * Uma AVL Tree é uma árvor de busca binária balanceada, em que a diferença de altura entre
  * entre as subárvores esquerda e direita de cada nós, chamada fator de equilíbrio, é mantida
  * bem pequena ou igual a 1. Essa é a garantia de que a árvore permaneça balanceada, otimizando
  * operações de busca, inserção e remoção, mantendo a altura da árvore em O(log n).
  * O Algoritmo aqui apresentado implementa uma AVL Tree, com rotações simples e duplas, para manter
  * o fator de equilíbrio durante a inserção e a remoção de novos nós. As rotações rightRotate e leftRotate, por exemplo,
  * ajustam a estrutura da árvore para garantir o balanceamento. O método insert insere um novo nó na árvore,
  * atualizando as alturas dos nós e realizando as rotações quando necessário. O método delete remove um nó da árvore,
  * atualizando as alturas dos nós e realizando as rotações quando necessário. O método minValueNode retorna o nó
  * com o menor valor da árvore, para auxiliar na remoção de nós.
  * Além disso, o algoritmo ainda verifica o fator de equilíbrio após cada inserção, e executa rotações
  * simples ou duplas, dependendo da configuração dos nós. As mensagens de saída indicam as rotações realizadas em
  * cada etapa do processo. Por fim, o algoritmo imprime a árvore em pré-ordem, para facilitar a visualização.
  */

package avl;

import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read user input

class Node { // Node class
    int key, height; // Node attributes
    Node left, right; // Node children
    
    Node(int value) { // Node constructor
        key = value; // The key is the value of the node
        height = 1; // The height of a new node is 1
        left = right = null; // The left and right children of a new node are null
    } // End of Node constructor
} // End of Node class

public class AVLTree { // AVLTree class
    Node root; // Root of the AVL Tree

    /**
     * Method to get the height of a node in the AVL Tree
     * @param node The node to get the height
     * @return The height of the node
     */
    int height(Node node) { // Method to get the height of a node
        if (node == null) // If the node is null, return 0
            return 0; // If the node is null, return 0
        return node.height; // Return the height of the node
    } // End of height method

    /**
     * Method to get the balance factor of a node in the AVL Tree (left height - right height)
     * @param node The node to get the balance factor
     * @return The balance factor of the node
     */
    int balanceFactor(Node node) { // Method to get the balance factor of a node
        if (node == null) // If the node is null, return 0
            return 0; // If the node is null, return 0
        return height(node.left) - height(node.right); // Return the balance factor of the node
    } // End of balanceFactor method

    /**
     * Method to perform a right rotation in the AVL Tree
     * @param y The node to perform the right rotation
     * @return The new root of the AVL Tree
     */
    Node rightRotate(Node y) { // Method to perform a right rotation
        Node x = y.left; // Get the left child of the node
        Node T2 = x.right; // Perform the rotation

        x.right = y; // Perform the rotation
        y.left = T2; // Perform the rotation

        y.height = Math.max(height(y.left), height(y.right)) + 1; // Update the height of the nodes
        x.height = Math.max(height(x.left), height(x.right)) + 1; // Update the height of the nodes

        return x; // Return the new root
    } // End of rightRotate method

    /**
     * Method to perform a left rotation in the AVL Tree
     * @param x The node to perform the left rotation
     * @return The new root of the AVL Tree
     */
    Node leftRotate(Node x) { // Method to perform a left rotation
        Node y = x.right; // Get the right child of the node
        Node T2 = y.left; // Perform the rotation

        y.left = x; // Perform the rotation
        x.right = T2; // Perform the rotation

        x.height = Math.max(height(x.left), height(x.right)) + 1; // Update the height of the nodes
        y.height = Math.max(height(y.left), height(y.right)) + 1; // Update the height of the nodes

        return y; // Return the new root
    } // End of leftRotate method

    /**
     * Method to get the AVL Tree as array
     * @return The AVL Tree as array
     */
    int[] toArray() { // Method to get the AVL Tree as array
        ArrayList<Integer> list = new ArrayList<>(); // Create an ArrayList to store the values
        toArrayHelper(root, list); // Call the helper method

        int[] array = new int[list.size()]; // Create an array to store the values
        for (int i = 0; i < list.size(); i++) { // For loop to store the values
            array[i] = list.get(i).intValue(); // Store the value in the array
        } // End of for loop

        return array; // Return the array
    } // End of toArray method

    /**
     * Helper method to get the AVL Tree as array
     * @param node The node to get the AVL Tree as array
     * @param list The ArrayList to store the values
     */
    void toArrayHelper(Node node, ArrayList<Integer> list) { // Helper method to get the AVL Tree as array
        if (node == null) { // If the node is null, return
            return; // If the node is null, return
        } // End of if statement

        toArrayHelper(node.left, list); // Call the helper method
        list.add(node.key); // Add the value to the ArrayList
        toArrayHelper(node.right, list); // Call the helper method
    } // End of toArrayHelper method

    /**
     * Method to insert a new node in the AVL Tree and perform the rotations when necessary
     * @param node The node to insert the new node
     * @param key The value of the new node
     * @return The new root of the AVL Tree
     */
    Node insert(Node node, int key) { // Method to insert a new node in the AVL Tree
        if (node == null) // If the node is null, return a new node
            return new Node(key); // If the node is null, return a new node

        if (key < node.key) // If the key is less than the node key, insert the new node in the left subtree
            node.left = insert(node.left, key); // If the key is less than the node key, insert the new node in the left subtree
        else if (key > node.key) // If the key is greater than the node key, insert the new node in the right subtree
            node.right = insert(node.right, key); // If the key is greater than the node key, insert the new node in the right subtree
        else // If the key is equal to the node key, return the node
            return node; // If the key is equal to the node key, return the node

        node.height = 1 + Math.max(height(node.left), height(node.right)); // Update the height of the node

        int balance = balanceFactor(node); // Get the balance factor of the node

        if (balance > 1) { // If the balance factor is greater than 1, the node is unbalanced
            if (key < node.left.key) { // If the balance factor is greater than 1, the node is unbalanced
                System.out.println("Right Rotation at Node: " + node.key); // Print the message
                return rightRotate(node); // Rotate the node to the right
            } else { // If the balance factor is greater than 1, the node is unbalanced
                System.out.println("Left-Right Rotation at Node: " + node.key); // Print the message
                node.left = leftRotate(node.left); // Rotate the node to the left
                return rightRotate(node);
            } // End of if statement
        } // End of if statement
        
        if (balance < -1) { // If the balance factor is less than -1, the node is unbalanced
            if (key > node.right.key) { // If the balance factor is less than -1, the node is unbalanced
                System.out.println("Left Rotation at Node: " + node.key); // Print the message
                return leftRotate(node); // Rotate the node to the left
            } else { // If the balance factor is less than -1, the node is unbalanced
                System.out.println("Right-Left Rotation at Node: " + node.key); // Print the message
                node.right = rightRotate(node.right); // Rotate the node to the right
                return leftRotate(node); // Rotate the node to the left
            } // End of if statement
        } // End of if statement

        return node; // Return the node
    } // End of insert method

    /**
     * Method to delete a node in the AVL Tree and perform the rotations when necessary
     * @param root The root of the AVL Tree
     * @param key The value of the node to be deleted
     * @return The new root of the AVL Tree
     */
    Node delete(Node root, int key) { // Method to delete a node in the AVL Tree
        if (root == null) // If the root is null, return the root
            return root; // If the root is null, return the root

        if (key < root.key) // If the key is less than the root key, delete the node in the left subtree
            root.left = delete(root.left, key); // If the key is less than the root key, delete the node in the left subtree

        else if (key > root.key) // If the key is greater than the root key, delete the node in the right subtree
            root.right = delete(root.right, key); // If the key is greater than the root key, delete the node in the right subtree

        else { // If the key is equal to the root key, delete the node
            Node temp = null; // Create a temporary node
            if((root.left == null) || (root.right == null)) { // If the node has one child or no child
                //Node temp = null; // Create a temporary node
                
                if (temp == root.left) // If the node has one child or no child
                    temp = root.right; // The temporary node is the right child
                else // If the node has one child or no child
                    temp = root.left; // The temporary node is the left child

                if (temp == null) { // If the node has no child
                    temp = root; // The temporary node is the root
                    root = null; // The root is null
                } else // If the node has no child
                    root = temp; // The root is the temporary node
            } else { // If the node has two children
                temp = minValueNode(root.right); // Get the node with the minimum value in the right subtree
                root.key = temp.key; // Replace the root key with the minimum value
                root.right = delete(root.right, temp.key); // Delete the node with the minimum value
            } // End of if statement
        } // End of if statement

        if (root == null) // If the root is null, return the root
            return root; // Return the root

        root.height = 1 + Math.max(height(root.left), height(root.right)); // Update the height of the node

        int balance = balanceFactor(root); // Get the balance factor of the node

        if (balance > 1 && balanceFactor(root.left) >= 0) { // If the balance factor is greater than 1, the node is unbalanced
            System.out.println("Right Rotation at Node: " + root.key); // Print the message
            return rightRotate(root); // Rotate the node to the right
        } // End of if statement

        if (balance > 1 && balanceFactor(root.left) < 0) { // If the balance factor is greater than 1, the node is unbalanced
            System.out.println("Left-Right Rotation at Node: " + root.key); // Print the message
            root.left = leftRotate(root.left); // Rotate the node to the left
            return rightRotate(root); // Rotate the node to the right
        } // End of if statement

        if (balance < -1 && balanceFactor(root.right) <= 0) { // If the balance factor is less than -1, the node is unbalanced
            System.out.println("Left Rotation at Node: " + root.key); // Print the message
            return leftRotate(root); // Rotate the node to the left
        }

        if (balance < -1 && balanceFactor(root.right) > 0) { // If the balance factor is less than -1, the node is unbalanced
            System.out.println("Right-Left Rotation at Node: " + root.key); // Print the message
            root.right = rightRotate(root.right); // Rotate the node to the right
            return leftRotate(root); // Rotate the node to the left
        } // End of if statement

        return root; // Return the root
    } // End of delete method

    /**
     * Method to get the node with the minimum value in the AVL Tree (leftmost node)
     * @param node The root of the AVL Tree
     * @return The node with the minimum value
     */
    Node minValueNode(Node node) { // Method to get the node with the minimum value
        Node current = node; // Get the root of the AVL Tree

        while (current.left != null) // While the left child is not null
            current = current.left; // Get the left child

        return current; // Return the node with the minimum value
    } // End of minValueNode method

    void printTree(Node root, String indent, boolean last) { // Method to print the AVL Tree
        if (root != null) { // If the root is not null
            System.out.print(indent); // Print the indent
            
            if (last) { // If the node is the last node
                System.out.print("R----"); // Print the message
                indent += "     "; // Update the indent
            } else { // If the node is not the last node
                System.out.print("L----"); // Print the message
                indent += "|    ";  // Update the indent
            } // End of if statement

            System.out.println("Key: " + root.key + " Height: " + root.height + " Balance Factor: " + balanceFactor(root) + "\n"); // Print the node information

            printTree(root.left, indent, false); // Print the left subtree
            printTree(root.right, indent, true); // Print the right subtree
        } // End of if statement
    } // End of printTree method

    /**
     * Main method
     * @param args The command line arguments
     */
    public static void main(String[] args) { // Main method
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        AVLTree tree = new AVLTree(); // Create an AVL Tree
        System.out.print("Type the number of elements of the tree, followed by the values "
                            + "to be inserted (Example: 5 10 25 18 41 3): "); // Print the prompt to the user
        String[] input = scanner.nextLine().split(" "); // Get the user input

        int[] values = new int[Integer.parseInt(input[0])]; // Create an array to store the values

        for (int i = 1; i < input.length; i++) { // For loop to store the values
            values[i - 1] = Integer.parseInt(input[i]); // Store the value in the array
        } // End of for loop

        for (int value : values) { // For each value
            tree.root = tree.insert(tree.root, value); // Insert the value in the AVL Tree
            System.out.println("Balanced AVL Tree after inserting " + value + ":"); // Print the message
            tree.printTree(tree.root, "", true); // Print the AVL Tree
            System.out.println("------------------------------"); // Print the message
        } // End of for loop

        while (true) { // While loop to add or remove nodes
            System.err.print("Enter (A) to add or (R) to remove a node, (P) to print the tree or (E) to exit: "); // Print the prompt to the user
            char choice = scanner.next().charAt(0); // Get the user input

            if (choice == 'E' || choice == 'e') { // If the user wants to exit
                break; // Break the loop
            } // End of if statement

            if (choice == 'A' || choice == 'a') { // If the user wants to add a node
                System.out.print("Enter the value to be inserted: "); // Print the prompt to the user
                int value = scanner.nextInt(); // Get the user input
                tree.root = tree.insert(tree.root, value); // Insert the value in the AVL Tree
                System.out.println("Balanced AVL Tree after inserting " + value + ":"); // Print the message
                tree.printTree(tree.root, "", true); // Print the AVL Tree
            } else if (choice == 'R' || choice == 'r') { // If the user wants to remove a node
                System.out.print("Enter the value to be removed: "); // Print the prompt to the user
                int value = scanner.nextInt(); // Get the user input
                tree.root = tree.delete(tree.root, value); // Delete the value in the AVL Tree
                System.out.println("Balanced AVL Tree after removing " + value + ":"); // Print the message
                tree.printTree(tree.root, "", true); // Print the AVL Tree
            } else if(choice == 'P' || choice == 'p') { // If the user wants to print the AVL Tree
                System.out.print("AVL Tree as Array: "); // Print the message
                int[] array = tree.toArray(); // Get the AVL Tree as array
                for (int value : array) { // For each value
                    System.out.print(value + " "); // Print the value
                } // End of for loop
                System.out.println(); // Print a new line
            } else { // If the user enters an invalid option
                System.out.println("Invalid option!"); // Print the message
            } // End of if statement

            //tree.printTree(tree.root, "", true); // Print the AVL Tree
        } // End of while loop

        scanner.close(); // Close the scanner
    } // End of main method
} // End of AVLTree class
