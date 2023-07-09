package io.nextree.Tree.BinaryTree;

// Overview
// Tree -> (BinaryTree) -> BinarySearchTree -> (balance) 2-3 tree and red-black tree
public class BinTreeNode {
    //
    private int data;
    private BinTreeNode leftChild;
    private BinTreeNode rightChild;
    private BinTreeNode parent;

    public BinTreeNode() {
        //
    }

    public BinTreeNode(int data) {
        //
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public BinTreeNode getParent() {
        return parent;
    }

    public void setParent(BinTreeNode parent) {
        this.parent = parent;
    }
}