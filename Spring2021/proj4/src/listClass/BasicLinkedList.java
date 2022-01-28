package listClass;

import java.util.Iterator;

/*
 * A LinkedList class, contains a constructor, an add method, and two retrieval methods for the head and tail nodes
 */
public class BasicLinkedList<T> implements Iterable<T> {

	private static class Node<T> {
		private T data;
		private Node<T> next;

		private Node(T data) {
			this.data = data;
			next = null;
		}

	}

	//List head pointer
	private Node<T> head;

	//List tail pointer
	private Node<T> tail;

	//List size
	private int size;

	/* constructor, initiates the list as empty */
	public BasicLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	//returns the size of the list
	public int getSize() {
		return size;
	}

	/** adds element to the tail of the list
	 @return a reference to the current object */
	public BasicLinkedList<T> addToEnd(T data){
		Node<T> end = new Node<>(data);
		if(head == null) {
			addToFront(data);
		} else if (head.next == null){
			head.next = end;
			tail = end;
			size++;
		}
		else if(!(head.next == null && head == null)) {
			tail.next = end;
			tail = end;
			size++;
		}
		return this;
	}

	/** adds the element to the head of the list
	 * @param data
	 * @returns a reference to the current object
	 */
	public BasicLinkedList<T> addToFront(T data){
		Node<T> front = new Node<>(data);
		front.next = head;
		head = front;
		size++;
		if(head == null) {
			tail = head;
		}
		return this;

	}

	/**  @returns the head element (without removing it), or null if the list is empty
	 */
	public T getFirst() {
		if(head == null) {
			return null;
		}
		return head.data;
	}

	/**
	 * @returns the tail element (without removing it), or null if the list is empty
	 */
	public T getLast() {
		if(head == null) {
			return null;
		}
		else if (tail == null) {
			return head.data;
		}
		return tail.data;
	}

	/** 
	 * Removes and returns the head element. 
	 * @returns The head element, if the list is empty, returns null. */
	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		Node<T> firstEl = new Node<>(head.data);
		head = head.next;
		size--;
		return firstEl.data;
	}

	/**
	 * Removes and returns the tail element. Traverses through the list using a reference to the current node and a 
	 * reference to the previous node. Decrements the size of the list after removing the last element.
	 * @returns The tail element. If the list is empty, returns null. */
	public T retrieveLastElement() {
		if (head == null) {
			return null;
		}
		if(head.next == null) {
			return null;
		}
		
		
		Node<T> lastEl = new Node<>(tail.data); //the last element of the list
		Node<T> curr = this.head; //current node of the list, begins at the head of the list
		Node<T> prev = null; //the node preceding the current node
		
		while(curr != null) {
			if(curr == tail) {
				prev.next = null;
				tail = prev;
			}
			prev = curr;
			curr = curr.next;
		}
		
		size--; //decrements the list size by 1 after removing the element
		return lastEl.data;
		
	}

	/**
	 * Removes all instances of the target element from the list. Traverses through the list using a reference to the 
	 * current node, and a reference to the preceding node. If the content of parameter equals the current node then the
	 * next reference of the previous node is reassigned to the next reference of the current node and the size is 
	 * decremented. 
	 * @param targetData, the data being removed
	 * @returns a reference to the current object.
	 */
	public BasicLinkedList<T> removeAllInstances(T targetData){
		Node<T> curr = head; //the current node of the list, begins at the head
		Node<T> prev = null; //the node preceding the current node
		while(curr != null) {
			//removes the current node if it matches the parameter
			if(curr.data.equals(targetData)) {
				/* if targetData is at the head, then the head reference gets reassigned
				 * to the following node and the size is decremented */
				if(curr == head) {
					head = head.next;
					size--;
				} else {
					prev.next = curr.next;
					size--;
					curr = head;
				}
			}
			prev = curr;
			curr = curr.next;
		}
		
		return this;
	}

	@Override
	public Iterator<T> iterator(){
		return new Iterator<T>() {
			
			Node<T> curr = head; //the current node of the list, begins at the head node
			
			@Override
			public boolean hasNext() {
				return curr != null;
			}
			
			@Override
			public T next() {
				T data = curr.data;
				curr = curr.next;
				return data;
			}
		};
	}
}
