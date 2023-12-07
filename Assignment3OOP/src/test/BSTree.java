package test;

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
        return root;
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
}