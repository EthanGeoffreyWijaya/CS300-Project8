//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OrderQueueTester
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

/**
 * This class provides test methods for OrderQueue and OrderIterator
 * 
 * @author Ethan
 *
 */
public class OrderQueueTester {

  /**
   * tester method for OrderIterator class methods
   * 
   * @return true if all tests pass.
   */
  public static boolean testOrderIterator() {
    LinkedOrder order1 = new LinkedOrder(new Order("1", 1));
    LinkedOrder order2 = new LinkedOrder(new Order("2", 2));
    LinkedOrder order3 = new LinkedOrder(new Order("3", 3));
    LinkedOrder order4 = new LinkedOrder(new Order("4", 4));
    order1.setNext(order2);
    order2.setNext(order3);
    order3.setNext(order4);
    OrderIterator iterator = new OrderIterator(order1);
    Order next;

    // 1) hasNext() test for true scenario
    if (!iterator.hasNext()) {
      System.out
          .println("OrderIterator test 1 failed: hasNext() returned false when should've returned "
              + "true");
      return false;
    }

    // 2) next() test
    next = iterator.next();
    if (!next.equals(order1.getOrder())) {
      System.out.println("OrderIterator test 2 failed: next() returned incorrect value."
          + "Expected: 1. Actual: " + next.getDishName());
      return false;
    }

    // 3) next() exception test
    iterator.next();
    iterator.next();
    iterator.next();
    try {
      iterator.next();
      System.out.println("OrderIterator test 3.1 failed: Exception not thrown when calling next() "
          + "with no next values available");
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println("OrderIterator test 3.2 failed: Wrong exception thrown when calling next()"
          + " with no next values available. Exception details: " + e.getMessage());
      return false;
    }

    // 4) hasNext() tests for false scenario
    if (iterator.hasNext()) {
      System.out
          .println("OrderIterator test 4 failed: hasNext() returned true with no next values");
      return false;
    }

    return true;
  }

  /**
   * tester method for OrderQueue.enqueue() method
   * 
   * @return true if all tests pass
   */
  public static boolean testEnqueue() {
    Order.resetIDGenerator();
    OrderQueue queue = new OrderQueue();

    // 1) empty queue enqueue test
    queue.enqueue(new Order("1", 1));
    if (!queue.toString().equals("1001: 1 (1) -> END")) {
      System.out.println("testEnqueue() test 1 failed: Order was not added properly to empty queue"
          + " Expected: 1001: 1 (1) -> END. Actual: " + queue);
      return false;
    }

    // 2) ordinary scenario enqueue test 1
    queue.enqueue(new Order("2", 2));
    if (!queue.toString().equals("1001: 1 (1) -> 1002: 2 (2) -> END")) {
      System.out.println("testEnqueue() test 2 failed: Order not properly added to queue. "
          + "Expected: 1001: 1 (1) -> 1002: 2 (2) -> END. Actual: " + queue);
      return false;
    }

    // 3) ordinary scenario enqueue test 2
    queue.enqueue(new Order("3", 3));
    if (!queue.toString().equals("1001: 1 (1) -> 1002: 2 (2) -> 1003: 3 (3) -> END")) {
      System.out.println("testEnqueue() test 3 failed: Order not properly added to queue. "
          + "Expected: 1001: 1 (1) -> 1002: 2 (2) -> 1003: 3 (3) -> END. Actual " + queue);
      return false;
    }
    return true;
  }

  /**
   * tester method for OrderQueue.dequeue() method
   * 
   * @return true if all tests pass
   */
  public static boolean testDequeue() {
    Order.resetIDGenerator();
    OrderQueue queue = new OrderQueue();
    queue.enqueue(new Order("1", 1));
    queue.enqueue(new Order("2", 1));
    queue.enqueue(new Order("3", 1));
    Order order;

    // 1) dequeue test for return value
    order = queue.dequeue();
    if (!order.toString().equals("1001: 1 (1)")) {
      System.out.println("testDequeue() test 1 failed: dequeue() returned incorrect value. "
          + "Expected: 1. Actual: " + order);
      return false;
    }

    // 2) dequeue test for normal order removal
    queue.dequeue();
    if (!queue.toString().equals("1003: 3 (1) -> END")) {
      System.out.println("testDequeue() test 2 failed: Order not removed correctly. Expected: "
          + "1003: 3 (1) -> END. Actual: " + queue.toString());
      return false;
    }

    // 3) dequeue test for removal from single order queue
    queue.dequeue();
    if (!queue.toString().equals("")) {
      System.out.println("testDequeue() test 3 failed: List should be empty. Actual: " + queue);
      return false;
    }

    // 4) dequeue exception test
    try {
      queue.dequeue();
      System.out.println("testDequeue() test 4.1 failed: No exception thrown when removing from "
          + "empty queue.");
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println("testDequeue() test 4.2 failed: Wrong exception thrown when removing from"
          + " empty queue. Exception details: " + e.getMessage());
      return false;
    }

    // 5) dequeue proper head/tail update test
    try {
      queue.enqueue(new Order("4", 1));
      queue.enqueue(new Order("5", 1));
      queue.dequeue();
    } catch (Exception e) {
      System.out.println(
          "testDequeue() test 5.1 failed: Exception thrown. queue no longer functions properly "
              + "after dequeue(). Exception details: " + e.getMessage());
      return false;
    }
    if (!queue.toString().equals("1005: 5 (1) -> END")) {
      System.out.println("testDequeue test 5.2 failed: queue no longer functions properly after "
          + "dequeue(). Expected: 1005: 5 (1) -> END. Actual: " + queue);
      return false;
    }

    return true;
  }

  /**
   * tester method for OrderQueue.peek() method.
   * 
   * @return true if all tests pass.
   */
  public static boolean testPeek() {
    Order.resetIDGenerator();
    OrderQueue queue = new OrderQueue();
    queue.enqueue(new Order("1", 1));
    queue.enqueue(new Order("2", 1));

    // 1) test if peek() alters queue
    queue.peek();
    if (!queue.toString().equals("1001: 1 (1) -> 1002: 2 (1) -> END")) {
      System.out.println("testPeek() test 1 failed: peek() should not alter the queue. Expected: "
          + "1001: 1 (1) -> 1002: 2 (1) -> END. Actual: " + queue);
      return false;
    }

    // 2) peek() test 1
    if (!queue.peek().toString().equals("1001: 1 (1)")) {
      System.out.println("testPeek() test 2 failed: peek() returned incorrect value. Expected: "
          + "1001: 1 (1). Actual: " + queue.peek());
      return false;
    }

    // 3) peek() test 2
    queue.dequeue();
    if (!queue.peek().toString().equals("1002: 2 (1)")) {
      System.out.println("testPeek() test 3 failed: peek() returned incorrect value. Expected: "
          + "1002: 2 (1). Actual: " + queue.peek());
      return false;
    }

    // 4) peek() exception test
    queue.dequeue();
    try {
      queue.peek();
      System.out
          .println("testPeek() test 4.1 failed: No exception thrown when called for empty queue");
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      System.out.println(
          "testPeek() test 4.2 failed: Wrong exception thrown when called for empty queue. "
              + "Exception details: " + e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Runs all tester methods.
   * 
   * @return true if all tester methods pass.
   */
  public static boolean runAllTests() {
    if (testOrderIterator() && testEnqueue() && testDequeue() && testPeek()) {
      return true;
    }
    return false;
  }

  /**
   * Main method. Calls runAllTests()
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
    /*
     * OrderQueue queue = new OrderQueue(); queue.enqueue(new Order("fries", 5)); queue.enqueue(new
     * Order("steak", 7)); queue.enqueue(new Order("milk", 1)); queue.enqueue(new Order("burger",
     * 3)); queue.enqueue(new Order("pancakes", 4)); System.out.println(queue); queue.dequeue();
     * queue.dequeue(); queue.dequeue(); System.out.println(queue); queue.dequeue();
     * queue.dequeue(); System.out.println(queue);
     */
  }

}
