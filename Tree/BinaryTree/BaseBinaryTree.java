package io.nextree.Tree.BinaryTree;

public class BaseBinaryTree implements BinaryTree {
    //
    protected BinTreeNode root;

    @Override
    public BinTreeNode getRoot() {
        //
        return root;
    }

    @Override
    public BinTreeNode searchNode(int key) {
        //
        BinTreeNode node = root;
        while (node != null) {
            if (key == node.getData()) {
                return node;
            } else if (key < node.getData()) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }

        return null;
    }

    @Override
    public void insertNode(int key) {
        //
        BinTreeNode newNode = new BinTreeNode(key);

        if (root == null) {
            root = newNode;
            return;
        }

        BinTreeNode node = root;
        while (true) {
            if (key < node.getData()) {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();
                } else {
                    node.setLeftChild(newNode);
                    return;
                }
            } else if (key > node.getData()) {
                if (node.getRightChild() != null) {
                    node = node.getRightChild();
                } else {
                    node.setRightChild(newNode);
                    return;
                }
            } else {
                throw new IllegalArgumentException("Tree already contains a node with key " + key);
            }
        }
    }

    @Override
    public void deleteNode(int key) {
        //
        BinTreeNode node = root;
        BinTreeNode parent = null;

        while (node != null && node.getData() != key) {
            parent = node;
            if (key < node.getData()) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }

        if (node == null) {
            throw new IllegalArgumentException("Tree doesn't contain a node with key " + key);
        }
        if (node.getLeftChild() == null || node.getRightChild() == null) {
            deleteNodeWithZeroOrOneChild(key, node, parent);
        } else {
            deleteNodeWithTwoChildren(node);
        }
    }

    private void deleteNodeWithZeroOrOneChild(int key, BinTreeNode node, BinTreeNode parent) {
        //
        BinTreeNode singleChild = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();

        if (node == root) {
            root = singleChild;
        } else if (key < parent.getData()) {
            parent.setLeftChild(singleChild);
        } else {
            parent.setRightChild(singleChild);
        }
    }

    private void deleteNodeWithTwoChildren(BinTreeNode node) {
        //
        BinTreeNode inOrderSuccessor = node.getRightChild();
        BinTreeNode inOrderSuccessorParent = node;
        while (inOrderSuccessor.getLeftChild() != null) {
            inOrderSuccessorParent = inOrderSuccessor;
            inOrderSuccessor = inOrderSuccessor.getLeftChild();
        }

        node.setData(inOrderSuccessor.getData());

        // Case a) Inorder successor is the deleted node's right child
        if (inOrderSuccessor == node.getRightChild()) {
            // --> Replace right child with inorder successor's right child
            node.setRightChild(inOrderSuccessor.getRightChild());
        }

        // Case b) Inorder successor is further down, meaning, it's a left child
        else {
            // --> Replace inorder successor's parent's left child
            //     with inorder successor's right child
            inOrderSuccessorParent.setLeftChild(inOrderSuccessor.getRightChild());
        }
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();
        appendNodeToStringRecursive(getRoot(), builder);
        return builder.toString();
    }

    private void appendNodeToStringRecursive(BinTreeNode node, StringBuilder builder) {
        //
        appendNodeToString(node, builder);
        if (node.getLeftChild() != null) {
            builder.append(" L{");
            appendNodeToStringRecursive(node.getLeftChild(), builder);
            builder.append('}');
        }
        if (node.getRightChild() != null) {
            builder.append(" R{");
            appendNodeToStringRecursive(node.getRightChild(), builder);
            builder.append('}');
        }
    }

    protected void appendNodeToString(BinTreeNode node, StringBuilder builder) {
        //
        builder.append(node.getData());
    }
}