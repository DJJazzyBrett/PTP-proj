/*
 * StackADT
 *
 * Brett Johnson
 * EN.605.202.81.SP21 Data Structures
 * Lab 1-updt
 *
 */

/**
 * <h1>StackADT</h1>
 *
 * Interface for stack data structure. Interface remains the same
 * regardless of underlying implementation.
 *
 * @author Brett Johnson
 * @version 2.0 25 March 2021
 * @since 1.0
 */
public interface StackADT<E> {

    /**
     * @param none
     * @return boolean True if empty, otherwise false
     */
    public boolean isEmpty();

    /**
     * @param E item The item to be inserted
     * @return none
     */
    public void push(E item);

    /**
     * @param none
     * @return E The item at the top of stack
     * @throws NoSuchElementException if stack empty
     */
    public E pop();

    /**
     * @param none
     * @return E The item at the top of the stack
     * @throws NoSuchElementException if stack empty
     */
    public E peek();

    /**
     * @param none
     * @return int The number of items in the stack
     */
    public int size();

    /**
     * @param none
     * @return String The items in the stack
     */
    public String toString();

}
