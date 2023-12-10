public class AVLTree { // AVLTree class
    Node root; // Root of the AVL Tree
    int height(Node node) 
    int balanceFactor(Node node) 
    Node rightRotate(Node y)
    Node leftRotate(Node x)
    int[] toArray()
    void toArrayHelper(Node node, ArrayList<Integer> list)
    Node insert(Node node, int key)
    Node delete(Node root, int key)
    Node minValueNode(Node node)
    void printTree(Node root, String indent, boolean last)
    public static void main(String[] args)
}