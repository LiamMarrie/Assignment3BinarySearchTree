package test;

import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.before;
import org.junit.Test;

public class BSTreeTest {
    private BSTree<integer> bst;

    @Before
    public void setUp() {
        // int a new bst before each test
        bst = new BSTree<>();
    }

    @Test
    public void testInsert() {
        // test inserting elements into the bst
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        // assert size of tree and check root and its children
        assertEquals(3, bst.size());
        assertEquals(5, bst.getRoot().getData());
        assertEquals(3, bst.getRoot().getLeft().getData());
        assertEquals(7, bst.getRoot().getRight().getData());
    }

    @Test
    public void testDelete() {
        // test deleting elements from the bst
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        // delete leaf node
        bst.delete(2);
        assertEquals(6, bst.size());
        assertNull(bst.getRoot().getLeft().getLeft());

        // delete node with one child
        bst.delete(7);
        assertEquals(5, bst.size());
        assertEquals(8, bst.getRoot().getRight().getData());

        // delete node with two children
        bst.delete(3);
        assertEquals(4, bst.size());
        assertEquals(4, bst.getRoot().getLeft().getData());
    }

    @Test
    public void testIterator() {
        // test iterator for in-order traversal
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        Iterator<Integer> iterator = bst.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertFalse(iterator.hasNext());
    }
}
