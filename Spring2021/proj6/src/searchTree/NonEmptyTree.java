package searchTree;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K,V> left, right;
	private K key;
	private V value;

	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) { 
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		if(key.compareTo(this.key) == 0) {
			return value;
		} else if (key.compareTo(this.key) < 0) {
			return left.search(key); //searches the left child
		} else { //this.key.compareTo(key) > 0
			return right.search(key); //searches the right child
		}
	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		if(key.compareTo(this.key) == 0) {
			this.value = value;
			return this;
		} else if(key.compareTo(this.key) < 0) {
			left = left.insert(key, value);
			return this;
		} else { //this.key.compareTo(key) > 0
			right = right.insert(key, value);
			return this;
		}
	}

	/**
	 * Deletes tree along with the key if its present in the tree.
	 */
	public Tree<K, V> delete(K key) {
		if(key.compareTo(this.key) == 0) {
			try {
				value = this.search(right.min());
				this.key = right.min();
				right = right.delete(right.min());
			} catch (TreeIsEmptyException e) {
				return left;
			}
		} else if(key.compareTo(this.key) < 0) {
			left = left.delete(key);
		} else { //this.key > 0
			right = right.delete(key);
		}
		return this;
	}

	/**
	 * Return the maximum key value in the map
	 * 
	 * @return the maximum key value in the map
	 * @throws NoSuchElementException if the map is empty
	 */
	public K max() {
		try {
			K max = this.right.max(); //stores the temporary maximum value
			return max; //final maximum value
		}
		catch(TreeIsEmptyException e) {
			return key;
		}
	}

	/**
	 * Return the minimum key value in the map
	 * 
	 * @return the minimum key value in the map
	 * @throws NoSuchElementException if the map is empty
	 */
	public K min() {
		try {
			K min = this.left.min(); //stores the temporary minimum value
			return min; //final minimum value
		}
		catch(TreeIsEmptyException e) {
			return this.key;
		}
	} 

	/** 
	 * Return the number of keys in the tree
	 *
	 * @return the number of keys in the tree
	 */
	public int size() {
		return 1 + left.size() + right.size();
	}

	/**
	 * Adds keys to a collection in sorted order.
	 */
	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);
	}

	/**
	 * Creates a subTree of the keys starting at the fromKey to the toKey. 
	 * 
	 * @return a subTree of keys beginning at fromKey to the toKey parameters.
	 */
	public Tree<K,V> subTree(K fromKey, K toKey) {
		if((fromKey.compareTo(this.min())) == 0  && (toKey.compareTo(this.max()) == 0)) {
			return this;
		}

		if(key.compareTo(fromKey) < 0) {
			return this.right.subTree(fromKey, toKey);

		} else if (key.compareTo(toKey) > 0) {
			return this.left.subTree(fromKey, toKey);

		} else {
			return new NonEmptyTree<K,V>(this.key, this.value, left.subTree(fromKey, toKey), 
					right.subTree(fromKey, toKey));
		}

	}
}


