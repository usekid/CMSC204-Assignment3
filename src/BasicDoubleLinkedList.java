
/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 3
 Instructor: Professor Alexander
 Description: Basic Double Linked List
 Due: 10/14/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ListIterator;

/**
 * This generic double-linked list relies on a head (reference to first element
 * of the list) and tail (reference to the last element of the list). Both are
 * set to null when the list is empty. Both point to the same element when there
 * is only one element in the list. A node structure has only three fields: data
 * and the prev and next references. The class must only define the following
 * entities: an inner class Node, an inner class that implements ListIterator
 * (for the iterator method), head and tail references and an integer
 * representing the list size. However only the hasNext(), next(), hasPrevious()
 * and previous() methods of ListIterator need to be implemented, all other
 * methods can throw the UnsupportedOperationException:
 * 
 * public void remove() throws UnsupportedOperationException{
 * 
 * throw new UnsupportedOperationException();}
 * 
 * All the entities are defined as protected so they can be accessed by the
 * subclasses.
 * 
 * @author Anthony Liu
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected int size;
	protected Node<T> head;
	protected Node<T> tail;

	protected class Node<T> {
		protected T data;
		protected Node<T> next;
		protected Node<T> prev;

		protected Node(T data) {
			this.data = data;

		}
	}

	public BasicDoubleLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	 * Checks if list is empty
	 * 
	 * @return True is list is empty
	 */
	public boolean isEmpty() {
		return size == 0; // head is null
	}

	/**
	 * Return size of list
	 * 
	 * @return The size of the linked list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param data The data for the node within the linked list
	 * @return Reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node<T> newNode = new Node<T>(data);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;

		return this;
	}

	/**
	 * Adds element to the front of the list
	 * 
	 * @param data The data for the node within the linked list
	 * @return Reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node<T> newNode = new Node<T>(data);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		size++;

		return this;
	}

	/**
	 * Returns but does not remove the last element from the list. If there are no
	 * elements the method returns null.
	 * 
	 * @return The data element or null
	 */
	public T getFirst() {
		if (isEmpty()) {
			return null;
		}

		return head.data;
	}

	/**
	 * Returns but does not remove the first element from the list. If there are no
	 * elements the method returns null.
	 * 
	 * @return The data element or null
	 */
	public T getLast() {
		if (isEmpty()) {
			return null;
		}

		return tail.data;
	}

	/**
	 * This method must be implemented using an inner class that implements
	 * ListIterator and defines the methods of hasNext(), next(), hasPrevious() and
	 * previous(). Remember that we should be able to call the hasNext() method as
	 * many times as we want without changing what is considered the next element.
	 * 
	 * @throws NoSuchElementException        Your next() method should throw
	 *                                       NoSuchElementException if there are no
	 *                                       more elements (at the end of the list
	 *                                       and calling next() or at the beginning
	 *                                       of the list and calling previous()).
	 * @throws UnsupportedOperationException You don't need to implement the
	 *                                       ListIterator's remove(), add(),
	 *                                       nextIndex() and previousIndex() and
	 *                                       set() methods, throw
	 *                                       UnsupportedOperationException if
	 *                                       called.
	 */
	@Override
	public ListIterator<T> iterator() {

		return new ListIterator<T>() {

			Node<T> current = head;
			Node<T> lastMove = null;

			@Override
			public boolean hasNext() {

				return current != null;
			}

			@Override
			public T next() throws NoSuchElementException {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T currentData = current.data;
				lastMove = current;
				current = current.next;
				return currentData;
			}

			public boolean hasPrevious() {
				return lastMove != null;
			}

			public T previous() throws NoSuchElementException {
				if (!hasPrevious()) {
					throw new NoSuchElementException();
				}
				T previousData = lastMove.data;
				current = lastMove;
				lastMove = lastMove.prev;
				return previousData;
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException();

			}

			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();

			}

			@Override
			public void set(T e) {
				throw new UnsupportedOperationException();

			}

		};
	}

	/**
	 * Removes the first instance of the targetData from the list. Notice that you
	 * must remove the elements by performing a single traversal over the list. You
	 * may not use any of the other retrieval methods associated with the class in
	 * order to complete the removal process. You must use the provided comparator
	 * (do not use equals) to find those elements that match the target. Do not
	 * implement this method using iterators.
	 * 
	 * @param targetData - The data element to be removed
	 * @param comparator - The comparator to determine equality of data elements
	 * @return Data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node<T> current = head;
		while (current != null) {
			if (comparator.compare(targetData, current.data) == 0) {
				if (current == head) {
					head = head.next;
					size--;
					break;
				} else if (current == tail) {
					tail = tail.prev;
					tail.next = null;
					size--;
					break;
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
					size--;
					break;
				}

			}
			current = current.next;

		}

		return this;
	}

	/**
	 * Remove and returns the first element from the list. If there are no elements
	 * the method reutrns null.
	 * 
	 * @return Data element or null
	 */
	public T retrieveFirstElement() {
		if (isEmpty()) {
			return null;
		}
		T firstData = head.data;
		head = head.next;
		head.prev = null;
		size--;
		return firstData;
	}

	/**
	 * Removes and returns the last element from the list. If there are no elements
	 * the method returns null.
	 * 
	 * @return Data element or null
	 */
	public T retrieveLastElement() {
		if (isEmpty()) {
			return null;
		}
		T lastData = tail.data;
		tail = tail.prev;
		tail.next = null;
		size--;
		return lastData;
	}

	/**
	 * Returns an arraylist of the items in the list from head of list to tail of
	 * list
	 * 
	 * @return An arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(getSize());

		Node<T> current = head;

		while (current != null) {
			list.add(current.data);
			current = current.next;
		}

		return list;
	}

}
