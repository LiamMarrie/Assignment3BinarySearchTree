package test;

import test.BSTreeADT;
import test.BSTreeNode;
import test.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
    private BSTreeNode<E> root;
    private int size;
    
    public BSTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public BSTreeNode<E> getRoot() {
        return this.root;
    }

    @Override
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(BSTreeNode<E> node) {
        if (node == null)
            return 0;
        else {
            int leftHeight = calculateHeight(node.getLeft());
            int rightHeight = calculateHeight(node.getRight());

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E entry) throws NullPointerException {
       return search(entry) != null;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        return searchRecursively(root, entry);
    }

    private BSTreeNode<E> searchRecursively(BSTreeNode<E> node, E entry) {
        if (node == null || node.getData().equals(entry))
            return node;

        int compareResult = entry.compareTo(node.getData());

        if (compareResult < 0)
            return searchRecursively(node.getLeft(), entry);
        else 
            return searchRecursively(node.getRight(), entry);
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null)
            throw new NullPointerException("Cannot add null entry to the tree");

        root = insertRecursively(root, newEntry);
        size++;
        return true;
    }

    private BSTreeNode<E> insertRecursively(BSTreeNode<E> node, E entry) {
        if (node == null)
            return new BSTreeNode<>(entry);

        int compareResult = entry.compareTo(node.getData());

        if (compareResult < 0)
            node.setLeft(insertRecursively(node.getLeft(), entry));
        else if (compareResult > 0)
            node.setRight(insertRecursively(node.getRight(), entry));

        return node;
    }

    @Override
    public BSTreeNode<E> removeMin() {
        if (isEmpty())
            return null;

        BSTreeNode<E> parent = null;
        BSTreeNode<E> current = root;
        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }

        if (parent != null)
            parent.setLeft(current.getRight());
        else
            root = current.getRight();

        size--;
        return current;
    }

    @Override
    public BSTreeNode<E> removeMax() {
        if (isEmpty())
            return null;

        BSTreeNode<E> parent = null;
        BSTreeNode<E> current = root;
        while (current.getRight() != null) {
            parent = current;
            current = current.getRight();
        }

        if (parent != null)
            parent.setRight(current.getLeft());
        else
            root = current.getLeft();

        size--;
        return current;
    }

    @Override
    public Iterator<E> inorderIterator() {
        return new InorderIterator(root);
    }

    @Override 
    public Iterator<E> preorderIterator() {
        return new PreorderIterator(root);
    }

    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator(root);
    }

    private class InorderIterator implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack;

        public InorderIterator(BSTreeNode<E> root) {
            stack = new Stack<>();
            populateStack(root);
        }

        private void populateStack(BSTreeNode<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("No more elements in the tree");

            BSTreeNode<E> node = stack.pop();
            populateStack(node.getRight());
            return node.getData();
        }
    }

    private class PreorderIterator implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack;

        public PreorderIterator(BSTreeNode<E> root) {
            stack = new Stack<>();
            if (root != null)
                stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("No more elements in the tree");

            BSTreeNode<E> node = stack.pop();
            if (node.getRight() != null)
                stack.push(node.getRight());
            if (node.getLeft() != null)
                stack.push(node.getLeft());

            return node.getData();
        }
    }

    private class PostorderIterator implements Iterator<E> {
       private Stack<BSTreeNode<E>> stack;
       private BSTreeNode<E> lastVisited;

       public PostorderIterator(BSTreeNode<E> root) {
           stack = new Stack<>();
           lastVisited = null;
           populateStack(root);
       }

       private void populateStack(BSTreeNode<E> node) {
           while (node != null) {
               stack.push(node);
               node = node.getLeft();
           }
       }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("No more elements in the tree");

            BSTreeNode<E> node = stack.peek();
            if (node.getRight() != null && node.getRight() != lastVisited) {
                populateStack(node.getRight());
            } else {
                lastVisited = stack.pop();
                return lastVisited.getData();
            }

            return next();
        }
    }
}