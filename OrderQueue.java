//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OrderQueue
// Course: CS 300 Spring 2021
//
// Author: Ethan Geoffrey Wijaya
// Email: egwijaya@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates a queue made out of Order objects
 * 
 * @author Ethan
 *
 */
public class OrderQueue implements QueueADT<Order>, Iterable<Order> {

  private LinkedOrder front;
  private LinkedOrder back;
  private int size = 0;

  /**
   * Returns an iterator over elements of type Order.
   * 
   * @return an Iterator.
   */
  @Override
  public Iterator<Order> iterator() {
    return new OrderIterator(front); // Iterate starting from the value of the front field
  }

  /**
   * Add a new element to the back of the queue.
   * 
   * @param newElement the element to be added.
   */
  @Override
  public void enqueue(Order newElement) {
    LinkedOrder curOrder = new LinkedOrder(newElement);

    if (size == 0) { // Must update front and back fields if queue has no elements
      back = curOrder;
      front = back;
    } else { // If elements exist, onyl back field needs to be updated
      back.setNext(curOrder);
      back = curOrder;
    }
    size++;
  }

  /**
   * Remove the next element from the front of the queue.
   * 
   * @return the next element from the front of the queue.
   * @throws NoSuchElementException if the queue is empty.
   */
  @Override
  public Order dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove from empty queue");
    }

    LinkedOrder curOrder = front;
    if (size == 1) { // Must update front and back fields if last element is removed
      front = null;
      back = null;
    } else { // If other elements exist, only front field needs to be updated
      front = front.getNext();
    }
    size--;
    return curOrder.getOrder();
  }

  /**
   * Returns the next element from the front of the queue without removing it.
   * 
   * @return the next element from the front of the queue.
   * @throws NoSuchElementException if the queue is empty.
   */
  @Override
  public Order peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("No elements in queue");
    }
    // Not necessary to update front or back fields since list remains unchanged.
    return front.getOrder();
  }

  /**
   * Returns true if and only if the queue contains no elements.
   * 
   * @return true if the queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Creates and returns a String representation of this OrderQueue using an enhanced-for loop. For
   * example, a queue with three Orders might look like this: 1001: fries (2) -> 1002: shake (1) ->
   * 1003: burger (3) -> END
   *
   * @return A String representation of the queue
   */
  @Override
  public String toString() {
    if (this.size == 0)
      return "";
    String qString = "";
    for (Order o : this) {
      qString += o.toString();
      qString += " -> ";
    }
    qString += "END";
    return qString;
  }
}
