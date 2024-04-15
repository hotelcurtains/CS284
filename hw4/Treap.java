package hw4;

/**
 * @author Daniel Detore
 * Section: CS 284-E/RJ
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
    private class Node<E> {
        /**
         * The data held by this Node.
         */
        public E data;

        /**
         * The priority held by this Node. See constructor for details.
         */
        public int priority;

        /**
         * The left child of this Node.
         */
        public Node<E> left;

        /**
         * The right child of this Node.
         */
        public Node<E> right;

        /**
         * Creates a new Node with the given data and priority.
         */
        public Node(E data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        /**
         * Rotates this node right/clockwise.
         */
        public Node<E> rotateRight() {
            Node<E> pivot = this.left;
            this.left = this.left.right;
            pivot.right = this;
            return pivot;
        }

        /**
         * Rotates this node left/counterclockwise.
         */
        public Node<E> rotateLeft() {
            Node<E> pivot = this.right;
            this.right = this.right.left;
            pivot.left = this;
            return pivot;
        }

        /**
         * Returns the String representation of this Node.
         */
        public String toString() {
            if (this.data == null)
                return "";
            String l = "";
            String r = "";
            if (this.left == null)
                l = "null";
            else
                l = "" + left.data;
            if (this.right == null)
                r = "null";
            else
                r = "" + right.data;
            return "[" + this.data + ", " + this.priority + "] (" + l + ") (" + r + ")";
        }

    }

    /**
     * The Random object used to generate random priorities.
     */
    private Random priorityGenerator;
    // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

    /**
     * The root node of this Treap.
     */
    private Node<E> root;

    /**
     * Creates a new empty Treap.
     */
    public Treap() {
        priorityGenerator = new Random();
    };

    /**
     * Creates a new empty Treap with a random priority generator using the given
     * seed.
     * 
     * @param seed The seed to use for the random priority generator.
     */
    public Treap(long seed) {
        priorityGenerator = new Random(seed);
    };

    /**
     * Adds a new Node with the given key to this Treap and sorts it into position.
     * Duplicate nodes cannot be added.
     * 
     * @param key The data for the Node to to hold.
     * @return true if the Node is added to this Treap, false otherwise.
     * @throws IllegalArgumentException if key is null
     */
    public boolean add(E key) {
        return add(key, priorityGenerator.nextInt());
    }

    /**
     * Adds a new Node with the given key and priority to this Treap and sorts it
     * into position.
     * Duplicate nodes cannot be added.
     * 
     * @param key      The data for the Node to to hold.
     * @param priority The priority for the Node to to hold.
     * @return true if the Node is added to this Treap, false otherwise.
     * @throws IllegalArgumentException if key is null
     */
    public boolean add(E key, int priority) {
        if (key == null)
            throw new IllegalArgumentException("key must have values!");
        if (this.root == null) {
            this.root = new Node<E>(key, priority);
            return true;
        }
        Node<E> current = this.root;
        Node<E> parent = current;
        Stack<Node<E>> stack = new Stack<Node<E>>();
        while (current != null) {
            parent = current;
            stack.push(parent);
            int diff = key.compareTo(current.data);
            if (diff < 0) {
                current = current.left;
            } else if (diff > 0) {
                current = current.right;
            } else {
                return false;
            }
        }
        if (key.compareTo(parent.data) < 0)
            parent.left = new Node<E>(key, priority);
        else
            parent.right = new Node<E>(key, priority);

        reheap(stack);
        return true;
    }

/**
 * Private method to assure this Treap has correct ordering.
 * @param stack
 */
    public void reheap(Stack<Node<E>> stack){
        if (stack.empty()) return;
        else if (stack.size() == 1){
            Node<E> current = stack.pop();
            while (current.left != null && current.left.priority > current.priority){
                current = current.rotateRight();
            }
            while (current.right != null && current.right.priority > current.priority){
                current = current.rotateLeft();
            }
            this.root = current;
        } else {
            Node<E> current = stack.pop();
            Node<E> parent = stack.peek();
            int child = -1;
            if (current.equals(parent.left)) child = -1;
            if (current.equals(parent.left)) child = 1;
            while (current.left != null && current.left.priority > current.priority){
                current = current.rotateRight();
            }
            while (current.right != null && current.right.priority > current.priority){
                current = current.rotateLeft();
            }
            if (child > 0) parent.left = current;
            else if (child < 0) parent.right = current;
        }
        reheap(stack);
    }

    /**
     * Deleted a Node with the given key from this Treap.
     * @param key
     * @return true if possible, false if a Node with key is not found.
     */
    public boolean delete(E key) {
        //locate or return false
        Node<E> current = this.root;
        Node<E> parent = current;
        Stack<Node<E>> stack = new Stack<Node<E>>();
        while(current != null){
            parent = current;
            stack.push(parent);
            int diff = key.compareTo(current.data);
            if (diff > 0 && current.right != null) {
                current = current.right;
            } else if (diff < 0 && current.left != null) {
                current = current.left;
            } else if (current.left == null && current.right == null) {
                return false;
            } else {break;}
        }
        System.out.println("a");

        //set priority to -inf
        if (parent.right == current) stack.peek = Integer.MIN_VALUE;
        else if (parent.left == current) parent.left.priority = Integer.MIN_VALUE;

        // rotate to a leaf
        reheap(stack);

        Stack<Node<E>> stock = new Stack<Node<E>>();
        // locate leaf
        current = this.root;
        parent = current;
        while(current != null){
            parent = current;
            stock.push(parent);
            int diff = key.compareTo(current.data);
            if (diff > 0 && current.right != null) {
                current = current.right;
            } else if (diff < 0 && current.left != null) {
                current = current.left;
            } else{
                stock.pop();
                stock.pop();
                break;
            }
        }
        reheap(stock);
        return true;
    }

    private boolean find(Node<E> root, E key) {
        return false;
    }

    public boolean find(E key) {
        Node<E> current = this.root;
        Stack<Node<E>> stack = new Stack<Node<E>>();
        while(current != null){
            int diff = key.compareTo(current.data);
            if (diff > 0 && current.right != null) {
                current = current.right;
            } else if (diff < 0 && current.left != null) {
                current = current.left;
            } else if (current.left == null && current.right == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the String representation of this Treap.
     * 
     * @return the String representation of this Treap.
     */
    public String toString() {
        return preorder(root);
    }

    /**
     * A recursive method that finds the String representation of this Treap.
     * 
     * @param pointer The current node. The String representation of this node will
     *                be calculated before being added to that of its children.
     * @return the String representation of this Treap.
     */
    private String preorder(Node<E> pointer) {
        if (pointer == null)
            return "";
        else
            return pointer.toString() + "\n" + preorder(pointer.left) + preorder(pointer.right);
    }

    public static void main(String[] args) {
        Treap<Integer> testTree = new Treap<Integer>();
        testTree.add(4, 19);
        testTree.add(2, 31);
        testTree.add(6, 70);
        testTree.add(1, 84);
        testTree.add(3, 12);
        testTree.add(5, 83);
        testTree.add(7, 26);
        System.out.println(testTree.toString());
        testTree.delete(6);
        System.out.println(testTree.toString());
    }

}