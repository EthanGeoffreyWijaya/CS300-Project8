//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OrderIterator
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
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * This class serves as an iterator for the OrderQueue class
 * 
 * @author Ethan
 *
 */
public class OrderIterator implements Iterator<Order> {

  private LinkedOrder current;

  /**
   * Iterator constructor
   * 
   * @param start The LinkedOrder from which to start iterating
   */
  public OrderIterator(LinkedOrder start) {
    current = start;
  }

  /**
   * Returns true if the iteration has more elements. (In other words, returns true if next() would
   * return an element rather than throwing an exception.)
   * 
   * @return true if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the next element in the iteration.
   * 
   * @return the next element in the iteration\
   * @throws NoSuchElementException - if the iteration has no more elements
   */
  @Override
  public Order next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No next value available");
    }
    LinkedOrder order = current;
    current = current.getNext();
    return order.getOrder();
  }
}
