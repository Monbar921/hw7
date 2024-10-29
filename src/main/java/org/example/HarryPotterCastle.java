package org.example;

import java.util.Random;

public class HarryPotterCastle {
    private RoomNode root;
    private int nextRoomNumber;

    public HarryPotterCastle() {
        root = null;
        nextRoomNumber = 1;
    }

    public void add() {
        root = insert(root, nextRoomNumber);
        ++nextRoomNumber;
    }

    private RoomNode insert(RoomNode currentNode, int nextRoomNumber) {
        if (currentNode == null) {
            return new RoomNode(nextRoomNumber);
        }
        if (nextRoomNumber > currentNode.currentRoomNumber) {
            currentNode.right = insert(currentNode.right, nextRoomNumber);
            if (currentNode.right.priority < currentNode.priority) {
                currentNode = leftRotation(currentNode);
            }
        } else {
            currentNode.left = insert(currentNode.left, nextRoomNumber);
            if (currentNode.left.priority < currentNode.priority) {
                currentNode = rightRotation(currentNode);
            }
        }

        return currentNode;
    }

    public void remove(int key) {
        root = deleteNode(root, key);
        inOrderVisitor(key);
    }

    private RoomNode deleteNode(RoomNode node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.currentRoomNumber) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.currentRoomNumber) {
            node.right = deleteNode(node.right, key);
        } else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        } else if (node.left.priority < node.right.priority) {
            node = leftRotation(node);
            node.left = deleteNode(node.left, key);
        }else  {
            node = rightRotation(node);
            node.right = deleteNode(node.right, key);
        }
        return node;
    }

    public void inOrderVisitor(int key) {
        inOrderRec(root, key);
    }

    private void inOrderRec(RoomNode node, int key) {
        if (node != null) {
            inOrderRec(node.left, key);
            if(node.currentRoomNumber > key){
                --(node.currentRoomNumber);
            }
            inOrderRec(node.right, key);
        }
    }

    public int findRoom(int roomNumber) {
        RoomNode node = search(root, roomNumber);
        return node == null ? -1 : node.originalRoomNumber;
    }

    private RoomNode search(RoomNode node, int key) {
        if (node == null || node.currentRoomNumber == key) {
            return node;
        }
        if (key > node.currentRoomNumber) {
            return search(node.right, key);
        }
        return search(node.left, key);
    }

    private RoomNode rightRotation(RoomNode y) {
        RoomNode x = y.left;
        RoomNode T2 = x.right;

        x.right = y;
        y.left = T2;

        return y;
    }

    private RoomNode leftRotation(RoomNode x) {
        RoomNode y = x.right;
        RoomNode T2 = y.left;

        y.left = x;
        x.right = T2;

        return y;
    }


    public static class RoomNode {
        static Random RND = new Random();
        int currentRoomNumber;
        int originalRoomNumber;
        int priority;
        RoomNode left;
        RoomNode right;

        public RoomNode(int roomNumber) {
            this.currentRoomNumber = roomNumber;
            this.originalRoomNumber = roomNumber;
            this.left = null;
            this.right = null;
            this.priority = RND.nextInt();
        }
    }

}
