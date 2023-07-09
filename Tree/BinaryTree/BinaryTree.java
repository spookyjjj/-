package io.nextree.Tree.BinaryTree;

// Binary는 정렬과 탐색에 좋다
public interface BinaryTree {
    //
    BinTreeNode getRoot();                 // not exist -> null
    BinTreeNode searchNode(int key);       // not exist -> null
    void insertNode(int key);
    void deleteNode(int key);
}