package q2test;

import org.junit.Test;
import q2.BST;
import q2.Key;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BSTtest {
    private BST bst= new BST();;
    @Test(timeout = 1000)
    public void testEmptyTree() {
        List<Key> keys = this.bst.invertedPreOrder();
        assertTrue(keys.size() == 0);
    }

    @Test(timeout = 1000)
    public void testInvertedPreOrder1() {
        Arrays.asList(Key.A, Key.DOWN, Key.C, Key.S, Key.RIGHT, Key.D, Key.LEFT, Key.W, Key.X, Key.UP, Key.Z)
                .forEach(k -> this.bst.insert(k));

        List<Key> expected = Arrays.asList(Key.A, Key.C, Key.S, Key.X, Key.Z, Key.W, Key.DOWN, Key.RIGHT, Key.D,
                Key.LEFT, Key.UP);

        List<Key> actual = this.bst.invertedPreOrder();

        assertEquals(expected, actual);
    }
    @Test(timeout = 1000)
    public void testSingleNode() {
        BST bst = new BST();
        bst.insert(Key.A);

        List<Key> expected = Arrays.asList(Key.A);
        List<Key> actual = bst.invertedPreOrder();

        assertEquals(expected, actual);
    }
    @Test(timeout = 1000)
    public void testThreeNodesBalanced() {
        // Order: D < S < Z
        this.bst.insert(Key.S);
        this.bst.insert(Key.D);
        this.bst.insert(Key.Z);

        // Tree:   S
        //        / \
        //       D   Z
        // Inverted preorder: S, Z, D
        List<Key> expected = Arrays.asList(Key.S, Key.Z, Key.D);
        List<Key> actual = this.bst.invertedPreOrder();

        assertEquals(expected, actual);
    }
    @Test(timeout = 1000)
    public void testComplexTree1() {
        // Order: UP < DOWN < RIGHT < LEFT < D < A < W < S < Z < X < C
        // Insert: W (root)
        this.bst.insert(Key.W);
        this.bst.insert(Key.D);   // D < W, left
        this.bst.insert(Key.Z);   // Z > W, right
        this.bst.insert(Key.UP);  // UP < D, left of D
        this.bst.insert(Key.A);   // A > D, right of D
        this.bst.insert(Key.X);   // X > Z, right of Z

        // Tree:       W
        //           /   \
        //          D     Z
        //         / \     \
        //       UP   A     X
        // Inverted preorder: W, Z, X, D, A, UP
        List<Key> expected = Arrays.asList(Key.W, Key.Z, Key.X, Key.D, Key.A, Key.UP);
        List<Key> actual = this.bst.invertedPreOrder();

        assertEquals(expected, actual);
    }
}
