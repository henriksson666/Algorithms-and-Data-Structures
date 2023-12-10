import org.junit.Test;
import static org.junit.Assert.*;

public class AVLTreeTest {

    @Test
    public void testInsert() {
        AVLTree tree = new AVLTree();
        
        // Inserting values into the tree
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 7);
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 17);
        
        // Asserting the tree structure after insertion
        assertEquals("10\n5 15\n3 7 12 17\n", tree.toString());
    }
    
    @Test
    public void testDelete() {
        AVLTree tree = new AVLTree();
        
        // Inserting values into the tree
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 7);
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 17);
        
        // Deleting a value from the tree
        tree.root = tree.delete(tree.root, 7);
        
        // Asserting the tree structure after deletion
        assertEquals("10\n5 15\n3 12 17\n", tree.toString());
    }
    
    @Test
    public void testToArray() {
        AVLTree tree = new AVLTree();
        
        // Inserting values into the tree
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 7);
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 17);
        
        // Converting the tree to an array
        int[] array = tree.toArray();
        
        // Asserting the array contents
        assertArrayEquals(new int[]{3, 5, 7, 10, 12, 15, 17}, array);
    }
}