/*
 * Node
 *
 * DJJazzyBrett
 *
 * PTP-proj/updt
 *
 */

/**
 * <h1>Node</h1>
 *
 * A separate class that is used in the implementation of
 * linked-list data structures.
 *
 * @author DJJazzyBrett
 * @version 2.0 25 March 2021
 * @since 1.0
 */
public class Node<E> {
    private E item;
    private Node<E> next;

    public Node() {
        item = null;
        next = null;
    } // END constructor

    public Node(E dataItem) {
        item = dataItem;
        next = null;
    } // END constructor

    public Node(E dataItem, Node<E> nodeNext) {
        item = dataItem;
        next = nodeNext;
    } // END constructor

    /**
     * Accessor method for item retrieval.
     *
     * @param none
     * @return E Return item associated with node
     */
    public E getItem() {
        return item;
    }

    /**
     * Mutator method for item.
     *
     * @param E dataItem
     * @return none
     */
    public void setItem(E dataItem) {
        item = dataItem;
    }

    /**
     * Accessor method for node retrieval.
     *
     * @param none
     * @return Node<E> Node to which current is pointing
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Mutator method for node.
     *
     * @param Node<E> nodeNext Node to which current is pointing
     * @return none
     */
    public void setNext(Node<E> nodeNext) {
        next = nodeNext;
    }

    public static <E> int numNodes(Node<E> top) {
        Node<E> pointer;
        int num;
        num = 0; // initialize target variable
        for (pointer = top; pointer != null; pointer = pointer.next)
            num++;
        return num;
    }

    //  public boolean search(Node<E> top, E target) {
    //  Node<E> pointer = top;
    //  boolean isMember = false;
    //  return true;
    //  }

    public static <E> Node<E> search(Node<E> top, E target) {
        Node<E> pointer;

        if (target != null) {
            for (pointer = top; pointer != null; pointer = pointer.next) {
                if (target.equals(pointer.item)) {
                    return pointer;
                }
            }
        }
        return null;
    }
}
