public
package hw4;

class Treap<E extends Comparable<E>> {
    private class Node {
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
         * Rotates this node right.
         */
        public Node<E rotateRight(){
            //TODO
        }

        /**
         * Rotates this node left.
         */
        public Node<E rotateLeft(){
            //TODO
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
     * @param key
     * @return true if the Node is added to this Treap, false otherwise.
     */
    public boolean add(E key) {

    }

    public boolean add(E key, imt priority) {
    }

    public boolean delete(E key) {
    }

    private boolean find(Node<E> root, E key) {
    }

    public boolean find(E key) {
    }

    public String toString() {
    }

}