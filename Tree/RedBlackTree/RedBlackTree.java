//package io.nextree.Tree.RedBlackTree;
//import io.nextree.Tree.BinaryTree.BaseBinaryTree;
//import io.nextree.Tree.BinaryTree.BinTreeNode;
//import io.nextree.Tree.BinaryTree.BinaryTree;
//
//public class RedBlackTree extends BaseBinaryTree implements BinaryTree {
//    //
//    //  Rules
//    //    1. Each node is either red or black.
//    //    2. (The root is black.)
//    //    3. All NIL leaves are black.
//    //    4. A red node must not have red children.
//    //    5. All paths from a node to the leaves below contain the same number of black nodes.
//
//    static final boolean RED = false;
//    static final boolean BLACK = true;
//
//    @Override
//    public BinTreeNode getRoot() {
//        return null;
//    }
//
//    @Override
//    public RbTreeNode searchNode(int key) {
//        //
//        BinTreeNode node = root;
//        while (node != null) {
//            if (key == node.data) {
//                return node;
//            } else if (key < node.data) {
//                node = node.left;
//            } else {
//                node = node.right;
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public void insertNode(int key) {
//        //
//        RbTreeNode node = root;
//        RbTreeNode parent = null;
//
//        // Traverse the tree to the left or right depending on the key
//        while (node != null) {
//            parent = node;
//            if (key < node.data) {
//                node = node.left;
//            } else if (key > node.data) {
//                node = node.right;
//            } else {
//                throw new IllegalArgumentException("BST already contains a node with key " + key);
//            }
//        }
//
//        // Insert new node
//        RbTreeNode newNode = new RbTreeNode(key);
//        newNode.color = RED;        // 우선은 red로 넣고 나중에 보정
//        if (parent == null) {
//            root = newNode;
//        } else if (key < parent.data) {
//            parent.left = newNode;
//        } else {
//            parent.right = newNode;
//        }
//        newNode.parent = parent;
//
//        fixRedBlackPropertiesAfterInsert(newNode);
//    }
//
//    //  fix cases
//    //    Case 1: New node is the root
//    //    Case 2: Parent node is red and the root
//    //    Case 3: Parent and uncle nodes are red
//    //    Case 4: Parent node is red, uncle node is black, inserted node is "inner grandchild"
//    //    Case 5: Parent node is red, uncle node is black, inserted node is "outer grandchild"
//    private void fixRedBlackPropertiesAfterInsert(RbTreeNode node) {
//        //
//        RbTreeNode parent = node.parent;
//
//        //
//        if (parent == null) {
//            return;
//        }
//        if (parent.color == BLACK) {
//            return;
//        }
//        RbTreeNode grandparent = parent.parent;
//
//        //
//        if (grandparent == null) {
//            parent.color = BLACK;
//            return;
//        }
//        RbTreeNode uncle = getUncle(parent);
//
//        //
//        if (uncle != null && uncle.color == RED) {
//            parent.color = BLACK;
//            grandparent.color = RED;
//            uncle.color = BLACK;
//            fixRedBlackPropertiesAfterInsert(grandparent);
//        } else if (parent == grandparent.left) {
//            if (node == parent.right) {
//                rotateLeft(parent);
//                parent = node;
//            }
//
//            rotateRight(grandparent);
//            parent.color = BLACK;
//            grandparent.color = RED;
//        } else {
//            if (node == parent.left) {
//                rotateRight(parent);
//                parent = node;
//            }
//
//            rotateLeft(grandparent);
//            parent.color = BLACK;
//            grandparent.color = RED;
//        }
//    }
//
//    private RbTreeNode getUncle(RbTreeNode parent) {
//        //
//        RbTreeNode grandparent = parent.parent;
//        if (grandparent.left == parent) {
//            return grandparent.right;
//        } else if (grandparent.right == parent) {
//            return grandparent.left;
//        } else {
//            throw new IllegalStateException("Parent is not a child of its grandparent");
//        }
//    }
//
//    @Override
//    public void deleteNode(int key) {
//        //
//        RbTreeNode node = root;
//
//        while (node != null && node.data != key) {
//            if (key < node.data) {
//                node = node.left;
//            } else {
//                node = node.right;
//            }
//        }
//
//        if (node == null) {
//            return;
//        }
//
//        RbTreeNode movedUpNode;
//        boolean deletedNodeColor;
//
//        if (node.left == null || node.right == null) {
//            movedUpNode = deleteNodeWithZeroOrOneChild(node);
//            deletedNodeColor = node.color;
//        } else {
//            RbTreeNode inOrderSuccessor = findMinimum(node.right);
//            node.data = inOrderSuccessor.data;
//
//            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
//            deletedNodeColor = inOrderSuccessor.color;
//        }
//
//        if (deletedNodeColor == BLACK) {
//            fixRedBlackPropertiesAfterDelete(movedUpNode);
//            if (movedUpNode.getClass() == NilNode.class) {
//                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
//            }
//        }
//    }
//
//    private RbTreeNode deleteNodeWithZeroOrOneChild(RbTreeNode node) {
//        //
//        if (node.left != null) {
//            replaceParentsChild(node.parent, node, node.left);
//            return node.left; // moved-up node
//        } else if (node.right != null) {
//            replaceParentsChild(node.parent, node, node.right);
//            return node.right; // moved-up node
//        } else {
//            RbTreeNode newChild = node.color == BLACK ? new NilNode() : null;
//            replaceParentsChild(node.parent, node, newChild);
//            return newChild;
//        }
//    }
//
//    private RbTreeNode findMinimum(RbTreeNode node) {
//        //
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
//
//    private void fixRedBlackPropertiesAfterDelete(RbTreeNode node) {
//        //
//        if (node == root) {
//            return;
//        }
//
//        RbTreeNode sibling = getSibling(node);
//
//        if (sibling.color == RED) {
//            handleRedSibling(node, sibling);
//            sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
//        }
//
//        if (isBlack(sibling.left) && isBlack(sibling.right)) {
//            sibling.color = RED;
//            if (node.parent.color == RED) {
//                node.parent.color = BLACK;
//            } else {
//                fixRedBlackPropertiesAfterDelete(node.parent);
//            }
//        } else {
//            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
//        }
//    }
//
//    private void handleRedSibling(RbTreeNode node, RbTreeNode sibling) {
//        //
//        sibling.color = BLACK;
//        node.parent.color = RED;
//
//        if (node == node.parent.left) {
//            rotateLeft(node.parent);
//        } else {
//            rotateRight(node.parent);
//        }
//    }
//
//    private void handleBlackSiblingWithAtLeastOneRedChild(RbTreeNode node, RbTreeNode sibling) {
//        //
//        boolean nodeIsLeftChild = node == node.parent.left;
//
//        if (nodeIsLeftChild && isBlack(sibling.right)) {
//            sibling.left.color = BLACK;
//            sibling.color = RED;
//            rotateRight(sibling);
//            sibling = node.parent.right;
//        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
//            sibling.right.color = BLACK;
//            sibling.color = RED;
//            rotateLeft(sibling);
//            sibling = node.parent.left;
//        }
//
//        sibling.color = node.parent.color;
//        node.parent.color = BLACK;
//        if (nodeIsLeftChild) {
//            sibling.right.color = BLACK;
//            rotateLeft(node.parent);
//        } else {
//            sibling.left.color = BLACK;
//            rotateRight(node.parent);
//        }
//    }
//
//    private RbTreeNode getSibling(RbTreeNode node) {
//        //
//        RbTreeNode parent = node.parent;
//        if (node == parent.left) {
//            return parent.right;
//        } else if (node == parent.right) {
//            return parent.left;
//        } else {
//            throw new IllegalStateException("Parent is not a child of its grandparent");
//        }
//    }
//
//    private boolean isBlack(RbTreeNode node) {
//        //
//        return node == null || node.color == BLACK;
//    }
//
//    private static class NilNode extends RbTreeNode {
//        //
//        private NilNode() {
//            super(0);
//            this.color = BLACK;
//        }
//    }
//
//    private void rotateRight(RbTreeNode node) {
//        //
//        RbTreeNode parent = node.parent;
//        RbTreeNode leftChild = node.left;
//
//        node.left = leftChild.right;
//        if (leftChild.right != null) {
//            leftChild.right.parent = node;
//        }
//
//        leftChild.right = node;
//        node.parent = leftChild;
//
//        replaceParentsChild(parent, node, leftChild);
//    }
//
//    private void rotateLeft(RbTreeNode node) {
//        //
//        RbTreeNode parent = node.parent;
//        RbTreeNode rightChild = node.right;
//
//        node.right = rightChild.left;
//        if (rightChild.left != null) {
//            rightChild.left.parent = node;
//        }
//
//        rightChild.left = node;
//        node.parent = rightChild;
//
//        replaceParentsChild(parent, node, rightChild);
//    }
//
//    private void replaceParentsChild(RbTreeNode parent, RbTreeNode oldChild, RbTreeNode newChild) {
//        //
//        if (parent == null) {
//            root = newChild;
//        } else if (parent.left == oldChild) {
//            parent.left = newChild;
//        } else if (parent.right == oldChild) {
//            parent.right = newChild;
//        } else {
//            throw new IllegalStateException("Node is not a child of its parent");
//        }
//
//        if (newChild != null) {
//            newChild.parent = parent;
//        }
//    }
//
//    @Override
//    protected void appendNodeToString(RbTreeNode node, StringBuilder builder) {
//        //
//        builder.append(node.data).append(node.color == RED ? "[R]" : "[B]");
//    }
//}