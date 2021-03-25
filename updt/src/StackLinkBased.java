/*
 * StackLinkBased
 *
 * DJJazzyBrett
 *
 * PTP-proj/updt
 *
 */
import java.util.NoSuchElementException;

/**
 * <h1>StackLinkBased</h1>
 *
 * A singly-linked list implementation of a stack data structure. See
 * StackADT.java for interface details.
 * <p>
 * This implementation relies on class Node.
 *
 * @author DJJazzyBrett
 * @version 2.0 25 March 2021
 * @since 1.0
 */
public class StackLinkBased<E> implements StackADT<E> {
    private Node<E> top;
    // private int size;

    public StackLinkBased() {
        setTop(null);
        // size = 0;
    } // END constructor

    private Node<E> getTop() {
        return top;
    }

    private void setTop(Node<E> top) {
        this.top = top;
    }

    public boolean isEmpty() {
        //return getTop() == null;
        return top == null;
    }

    public void push(E item) {
        setTop(new Node<E>(item, getTop()));
    }

    public void pull(E item) {
        if (getTop() == null) {
            push(item);
        } else {
            Node<E> tmp = getTop();
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(new Node<E>(item, null));
        }
    }

    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        } else {
            E result = getTop().getItem();
            setTop(getTop().getNext());
            return result;
        }
    }

    public void popAll() {
        while (!isEmpty()) {
            pop();
        }
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        } else {
            return getTop().getItem();
        }
    }

    public int size() {
        return Node.numNodes(top);
    }

    public int countOps(E target) {
        Node<E> pointer;
        int num;
        num = 0;
        pointer = Node.search(top, target);
        while (pointer != null) {
            num++;
            pointer = pointer.getNext();
            pointer = Node.search(pointer, target);
        }
        return num;

    }

    public StackLinkBased<E> cloneStack() {
        StackLinkBased<E> clone = new StackLinkBased<E>();
        Node<E> tmp = getTop();
        while (tmp != null) {
            clone.pull(tmp.getItem());
            tmp = tmp.getNext();
        }
        return clone;
    }

    public String origString() {
        Node<E> current = top;
        String stack = "";
        while (current != null) {
            stack = current.getItem() + "" + stack;
            current = current.getNext();
        }
        return stack;
    }

    @Override
    public String toString() {
        Node<E> current = top;
        String stack = "  [";
        while (current != null) {
            stack = stack + "" + current.getItem();
            current = current.getNext();
        }
        return stack + "]";
    }

}
