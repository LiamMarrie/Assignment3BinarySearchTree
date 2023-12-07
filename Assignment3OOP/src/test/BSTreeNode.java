package test;

public class BSTreeNode<E extends Comparable<? super E>> {
    private E data;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;

    public BSTreeNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public E getData() {
        return data;
    }

    public BSTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    public BSTreeNode<E> getRight() {
        return this.right;
    }

    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }
}