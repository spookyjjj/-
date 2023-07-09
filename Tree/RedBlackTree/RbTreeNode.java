package io.nextree.Tree.RedBlackTree;

import io.nextree.Tree.BinaryTree.BinTreeNode;

public class RbTreeNode extends BinTreeNode{
    //
    int data;

    BinTreeNode left;
    BinTreeNode right;
    BinTreeNode parent;
    boolean color;          // t: black, f: red

    public RbTreeNode(int data) {
        //
        super();
        this.data = data;
        this.color = true;
    }

    public int getData() {
        //
        return data;
    }
}
