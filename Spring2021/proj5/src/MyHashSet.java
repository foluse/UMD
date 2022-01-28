import java.util.ArrayList;
import java.util.Iterator;

/** 
 * The MyHashSet API is similar to the Java Set interface. This
 * collection is backed by a hash table.
 */
public class MyHashSet<E> implements Iterable<E> {

	/** Unless otherwise specified, the table will start as
	 * an array (ArrayList) of this size.*/
	private final static int DEFAULT_INITIAL_CAPACITY = 4;

	/** When the ratio of size/capacity exceeds this
	 * value, the table will be expanded. */
	private final static double MAX_LOAD_FACTOR = 0.75;

	/* STUDENTS: LEAVE THIS AS PUBLIC */
	public ArrayList<Node<E>> hashTable;

	private int size;  // number of elements in the table


	// STUDENTS: DO NOT ADD ANY OTHER INSTANCE VARIABLES OR STATIC
	// VARIABLES TO THIS CLASS!


	/* STUDENTS: LEAVE THIS PUBLIC, and do not modify the Node class. */
	public static class Node<T> {
		private T data;
		public Node<T> next;  // STUDENTS: Leave this public, too!

		private Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/**
	 * Initializes an empty table with the specified capacity.  
	 *
	 * @param initialCapacity initial capacity (length) of the 
	 * underlying table
	 */
	// STUDENTS: Calling the ArrayList constructor that takes
	// an int argument doesn't do what we want here. 
	// You need to make an empty ArrayList, and then add a bunch 
	// of null values to it until the size reaches the 
	// initialCapacity requested.  
	public MyHashSet(int initialCapacity) {
		//creates an empty hashTable
		hashTable = new ArrayList<>();
		/** adds null values to all elements of the hashTable 
		until the size reaches the initialCapacity **/
		for(int i = 0; i < initialCapacity; i++) {
			hashTable.add(null);
		}
	}

	/**
	 * Initializes an empty table of length equal to 
	 * DEFAULT_INITIAL_CAPACITY
	 */
	// STUDENTS:  This constructor should just call the other
	// constructor
	public MyHashSet() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * Returns the number of elements stored in the table.
	 * @return number of elements in the table
	 */
	public int size(){
		return size;
	}

	/**
	 * Returns the length of the table (the number of buckets).
	 * @return length of the table (capacity)
	 */
	public int getCapacity(){
		return hashTable.size();
	}

	/** 
	 * Looks for the specified element in the table.
	 * 
	 * @param element to be found
	 * @return true if the element is in the table, false otherwise
	 */
	public boolean contains(Object element) {
		//the bucket position of the current object
		int bucketPos = Math.abs(element.hashCode() % getCapacity());

		if(hashTable.get(bucketPos) == null) {
			return false; 

		} else if (hashTable.get(bucketPos) != null) {
			//head of the bucket
			Node<E> head = hashTable.get(bucketPos);
			//traverses through the bucket searching for the parameter
			//if the parameter is found returns true
			for(Node <E> i = head; i != null; i = i.next) {
				if (i.data.equals(element)) {
					return true;
				}
			}
		}

		return false;
	}

	/** Adds the specified element to the collection, if it is not
	 * already present.  If the element is already in the collection, 
	 * then this method does nothing.
	 * 
	 * @param element the element to be added to the collection
	 */
	// STUDENTS:  After adding the element to the table, consider
	// the load factor. If it is greater than MAX_LOAD_FACTOR then 
	// you must double the size of the table. [Create a new table 
	// that is twice as big as the current one and then re-hash 
	// of all of the data from the old table into the new one.
	// Hint: It's OK to iterate over the original table.]
	public void add(E element) {
		if(!contains(element)) {

			//index position of the element in the hashTable
			int pos = Math.abs(element.hashCode() % getCapacity());

			if(hashTable.get(pos) == null) {
				//if the bucket at the current position is empty, then 
				//the parameter is set as the head of that bucket 
				hashTable.set(pos, new Node<E> (element));
				size++;

				//if the current position isn't empty, then the parameter
			} else {
				//represents the bucket
				Node<E> node = new Node<E>(element);
				node.next = hashTable.get(pos);
				//adds the parameter to the head of the bucket
				hashTable.set(pos, node);
				size++;
			}
		}

		//rehashes the hashTable if the load factor is greater than 
		//MAX_LOAD_FACTOR

		if((double)size/getCapacity() > MAX_LOAD_FACTOR) {
			//the new hashTable thats twice the size of the previous one
			//contains the rehashed elements
			ArrayList<Node<E>> newTable = new ArrayList<>();

			//fills all values of newTable to null
			for(int i = 0; i < (getCapacity()*2); i++) {
				newTable.add(null);
			}

			Iterator<E> iterator = iterator();
			while(iterator.hasNext()) {
				E next = iterator.next();
				if(next != null) {
					//rehashes the index positions of the elements in the original
					//hashTable
					int newPos = Math.abs(next.hashCode() % (getCapacity()*2));
					if(newTable.get(newPos) == null) {
						//if the bucket at the current position is empty, then
						//the parameter is set as the head of that bucket
						newTable.set(newPos, new Node<E>(next));

					} else {
						//represents the bucket
						Node <E> newNode = new Node<E>(next);
						newNode.next = newTable.get(newPos);
						//adds the parameter to the head of the bucket
						newTable.set(newPos, newNode);
					}
				}
			}

			//alias the old hashTable to the larger hashTable
			hashTable = newTable;
		}
	}

	/** Removes the specified element from the collection.  If the
	 * element is not present then this method should do nothing.
	 *
	 * @param element the element to be removed
	 * @return true if an element was removed, false if no element 
	 * removed
	 */
	public boolean remove(Object element) {
		if(contains(element)) {
			//index position of the element in the hashTable
			int pos = Math.abs(element.hashCode() % getCapacity());
			Node <E> head = hashTable.get(pos);
			//if the parameter is the head of the bucket then the next 
			//node of that bucket becomes the head and the parameter is removed
			//and the size gets decremented
			if(head.data.equals(element)) {
				hashTable.set(pos, head.next);
				size--;

				//otherwise we traverse through the bucket for the parameter
			} else {
				Node<E> prev = head;
				//start at the head of the bucket
				Node<E> curr = head.next;
				while(curr != null) {
					//if the data at the current node equals the parameter
					//then we remove it and decrement the size
					if(curr.data.equals(element)) {
						prev.next = curr.next;
						size--;
					} else {
						prev = curr;
					}
					curr = curr.next;
				}
			}

		}
		return false;
	}

	/** Returns an Iterator that can be used to iterate over
	 * all of the elements in the collection.
	 * 
	 * The order of the elements is unspecified.
	 */
	// STUDENTS: You may NOT copy the data from the hash table
	// into a different structure.  You must write an iterator
	// that iterates over your hash table directly, without 
	// copying the data anywhere.
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			//current position of the hashTable
			int pos = 0;
			//node at the current position
			Node<E> curr = hashTable.get(pos);

			@Override
			public boolean hasNext() {
				while((curr == null) && pos != getCapacity() - 1) {
					pos++;
					curr = hashTable.get(pos);
				}

				if(curr == null) {
					return false;
				}
				return true;
			}

			@Override
			public E next() {
				E data = curr.data;
				if(curr != null) {
					curr = curr.next;
				}
				return data;
			}			
		};
	}
}
