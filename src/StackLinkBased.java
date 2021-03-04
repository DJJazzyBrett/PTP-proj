/*
 * StackLinkBased
 *
 * DJJazzyBrett
 * Data Structures with Java
 * ~/Java/PTP-proj
 *
 */
//package src;
import java.util.NoSuchElementException;

/**
 * <h1>StackLinkBased</h1>
 *
 * A singly-linked list implementation of a stack data structure.
 * See StackADT.java for interface details.
 * <p>
 * This implementation relies on class Node.
 *
 * @author DJJazzyBrett
 * @version 1.0 02 March 2021
 * @since 1.0
 */
public class StackLinkBased<E> implements StackADT<E> {
    private Node<E> top;
    private int size;

    public StackLinkBased() {
        setTop(null);
        size = 0;
    } // end constructor

    private Node<E> getTop() {
        return top;
    }

    private void setTop(Node<E> top) {
        this.top = top;
    }

    public boolean isEmpty() {
        return getTop() == null;
    }

    public void push(E item) {
        setTop(new Node<E>(item, getTop()));
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            E result = getTop().getItem();
            setTop(getTop().getNext());
            size--;
            return result;
        }
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return getTop().getItem();
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node<E> current = top;
        String stack = "[";

        if(current == null) {
            return stack + "]";
        }
        while (current != null) {
            stack = stack + "" + current.getItem();
            current = current.getNext();
        }
        return stack + "]";
    }
}
