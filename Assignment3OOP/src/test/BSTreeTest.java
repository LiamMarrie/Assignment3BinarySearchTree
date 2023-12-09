package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class BSTreeTest {
    private BSTree<Integer> bst;

    @Before
    public void setUp() {
        // initialize a new BST before each test
        bst = new BSTree<>();
    }

    @Test
    public void testAdd() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        assertEquals(3, bst.size());
        assertEquals(Integer.valueOf(5), bst.getRoot().getData());
        assertEquals(Integer.valueOf(3), bst.getRoot().getLeft().getData());
        assertEquals(Integer.valueOf(7), bst.getRoot().getRight().getData());
    }

    @Test
    public void testRemove() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);

        bst.remove(2);
        assertEquals(6, bst.size());
        assertNull(bst.getRoot().getLeft().getLeft());

        bst.remove(7);
        assertEquals(5, bst.size());
        assertEquals(Integer.valueOf(8), bst.getRoot().getRight().getData());

        bst.remove(3);
        assertEquals(4, bst.size());
        assertEquals(Integer.valueOf(4), bst.getRoot().getLeft().getData());
    }

    @Test
    public void testInorderIterator() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        Iterator<Integer> iterator = bst.inorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertFalse(iterator.hasNext());
    }
}
