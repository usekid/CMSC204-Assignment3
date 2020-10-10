
/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 3
 Instructor: Professor Alexander
 Description: Sorted Double Linked List
 Due: 10/14/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/
import java.util.Comparator;
import java.util.ListIterator;

/**
 * Implements a generic sorted double list using a provided Comparator. It
 * extends BasicDoubleLinkedList class
 * 
 * @author Anthony Liu
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	private final Comparator<T> comparator;

	/**
	 * Creates an empty list that is associated with the specified comparator
	 * 
	 * @param comparator2 Comparator to compare data elements
	 */
	SortedDoubleLinkedList(Comparator<T> comparator2) {
		comparator = comparator2;
	}

	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * Notice we can insert the same element several times. Your implementation must
	 * traverse the list only once in order to perform the insertion. Do not
	 * implement this method using iterators. Notice that you don't need to call any
	 * of the super class methods in order to implement this method.
	 * 
	 * @param data the data to be added to the list
	 * @return A reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		Node<T> newNode = new Node<T>(data);

		if (isEmpty()) {
			head = tail = newNode;
		} else if (comparator.compare(data, head.data) <= -1) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		} else if (comparator.compare(data, tail.data) >= 1) {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		} else {
			Node<T> current = head.next;
			while (current.next != null && comparator.compare(data, current.data) >= 1) {
				current = current.next;
			}
			newNode.next = current;
			newNode.prev = current.prev;
			current.prev.next = newNode;
			current.prev = newNode;
		}
		size++;
		return this;

	}
/**
 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
 * 
 * @throws UnsupportedOperationException if method is called
 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
/**
 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
 * 
 * @throws UnsupportedOperationException if method is called
 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
/**
 * Implements the iterator by calling the super class iterator method.
 */
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}
/**
 * Implements the remove operation by calling the super class remove method.
 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}
}
