import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
 
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * <P>
 * The HeavyBag class implements a Set-like collection that allows duplicates (a
 * lot of them).
 * </P>
 * <P>
 * The HeavyBag class provides Bag semantics: it represents a collection with
 * duplicates. The "Heavy" part of the class name comes from the fact that the
 * class needs to efficiently handle the case where the bag contains 100,000,000
 * copies of a particular item (e.g., don't store 100,000,000 references to the
 * item).
 * </P>
 * <P>
 * In a Bag, removing an item removes a single instance of the item. For
 * example, a Bag b could contain additional instances of the String "a" even
 * after calling b.remove("a").
 * </P>
 * <P>
 * The iterator for a heavy bag must iterate over all instances, including
 * duplicates. In other words, if a bag contains 5 instances of the String "a",
 * an iterator will generate the String "a" 5 times.
 * </P>
 * <P>
 * In addition to the methods defined in the Collection interface, the HeavyBag
 * class supports several additional methods: uniqueElements, getCount, and
 * choose.
 * </P>
 * <P>
 * The class extends AbstractCollection in order to get implementations of
 * addAll, removeAll, retainAll and containsAll.  (We will not be over-riding those).
 * All other methods defined in
 * the Collection interface will be implemented here.
 * </P>
 */
public class HeavyBag<T> extends AbstractCollection<T> implements Serializable {

    /* Leave this here!  (We need it for our testing) */
	private static final long serialVersionUID = 1L;

	
	/* Create whatever instance variables you think are good choices */
	
	/* the heavy bag, T is the java generic for the element, Integer represents the # of appearances of the element */
	private Map<T, Integer> heavyBag; 
	private int size = 0; //size of the heavy bag
	
	
	
	
	
	/**
     * Initialize a new, empty HeavyBag
     */
    public HeavyBag() {
    	heavyBag = new HashMap<T, Integer>();
    }

    /**
     * Adds an instance of o to the Bag
     * 
     * @return always returns true, since added an element to a bag always
     *         changes it
     * 
     */
    @Override
    public boolean add(T o) {
    	//increments the # of appearances of the element if its already in the bag
    	if(heavyBag.containsKey(o)) {
    		heavyBag.put(o, heavyBag.get(o) + 1);
    	//otherwise adds the element to the bag
    	} else {
    		heavyBag.put(o, 1);
    	}
    	size++;
    	return true;
    }

    /**
     * Adds multiple instances of o to the Bag.  If count is 
     * less than 0 or count is greater than 1 billion, throws
     * an IllegalArgumentException.
     * 
     * @param o the element to add
     * @param count the number of instances of o to add
     * @return true, since addMany always modifies
     * the HeavyBag.
     */
    public boolean addMany(T o, int count) {
    	if(count < 0 || count > 1000000000) {
    		throw new IllegalArgumentException();
    	}
    	//increments the # of appearances by count if its already in the bag
    	if(heavyBag.containsKey(o)) {
    		heavyBag.put(o, heavyBag.get(o) + count);
    	//otherwise adds the element to the bag and sets the # of appearances to count
    	} else {
    		heavyBag.put(o, count);
    	}
    	size += count;
    	return true;
    }
    
    /**
     * Generate a String representation of the HeavyBag. This will be useful for
     * your own debugging purposes, but will not be tested other than to ensure that
     * it does return a String and that two different HeavyBags return two
     * different Strings.
     */
    @Override
    public String toString() {
    	return heavyBag.keySet()+ " ";
    }

    /**
     * Tests if two HeavyBags are equal. Two HeavyBags are considered equal if they
     * contain the same number of copies of the same elements.
     * Comparing a HeavyBag to an instance of
     * any other class should return false;
     */
    @Override
    public boolean equals(Object o) {
    	if(this == o) { 
    		return true; //they are the exact same object
    	}
    	if(o == null) {
    		return false;
    	}
    	
    	if(this.getClass() != o.getClass()) {
    		return false;
    	}
    	
    	HeavyBag<?> heavy = (HeavyBag<?>)o;
    	//returns true if the two bags contain the same # of copies and if their hashCodes are the same
    	return heavy.heavyBag.equals(this.heavyBag) && this.hashCode() == heavy.hashCode();
    }

    /**
     * Return a hashCode that fulfills the requirements for hashCode (such as
     * any two equal HeavyBags must have the same hashCode) as well as desired
     * properties (two unequal HeavyBags will generally, but not always, have
     * unequal hashCodes).
     */
    @Override
    public int hashCode() {
    	return heavyBag.hashCode(); //uses hashCode method from the HashMap collection
    }

    /**
     * <P>
     * Returns an iterator over the elements in a heavy bag. Note that if a
     * Heavy bag contains 3 a's, then the iterator must iterate over 3 a's
     * individually.
     * </P>
     */
    @Override
    public Iterator<T> iterator() {
    	return new Iterator<T> () {
    		
    		private int curr = 1; //the current position in the heavyBag
    		private T elem = null; //the current obj.
    		private Integer numElements = 0; //number of appearances of an element
    		private Set<T> bagSet = uniqueElements();
    		private Iterator<T> iterator = bagSet.iterator();
    		
    		@Override
    		public boolean hasNext() {
    			return curr <= size;
    		}
    		
    		@Override
    		public T next() {
    			if(hasNext()) {
    				if(numElements > 0) {
    					numElements--;
    				}
    				if(numElements == 0) {
    					elem = iterator.next();
    					numElements = heavyBag.get(elem);
    				}
    				curr++;
    			}
    			return elem;
    		}
    		
    		@Override
    		public void remove() {
    			//calls the remove method from the HeavyBag class
    			HeavyBag.this.remove(elem);
    		}
    	};
    }

    /**
     * return a Set of the elements in the Bag (since the returned value is a
     * set, it can contain no duplicates. It will contain one value for each 
     * UNIQUE value in the Bag).
     * 
     * @return A set of elements in the Bag
     */
    public Set<T> uniqueElements() {
    	return heavyBag.keySet();
    }

    /**
     * Return the number of instances of a particular object in the bag. Return
     * 0 if it doesn't exist at all.
     * 
     * @param o
     *            object of interest
     * @return number of times that object occurs in the Bag
     */
    public int getCount(Object o) {
    	if(!heavyBag.containsKey(o)) {
    		return 0;
    	}
    	return heavyBag.get(o);
    }

    /**
     * Given a random number generator, randomly choose an element from the Bag
     * according to the distribution of objects in the Bag (e.g., if a Bag
     * contains 7 a's and 3 b's, then 70% of the time choose should return an a,
     * and 30% of the time it should return a b.
     * 
     * This operation can take time proportional to the number of unique objects
     * in the Bag, but no more.
     * 
     * This operation should not affect the Bag.
     * 
     * @param r
     *            Random number generator
     * @return randomly chosen element
     */
    public T choose(Random r) {
    	T chosenEl = null; //the random element
    	int curr = r.nextInt(size()); //random number that falls is no larger than the bag size
    	int count = 0; //tracks the num of appearances of an element in the bag
    	
    	for(T elem: uniqueElements()) {
    		count += heavyBag.get(elem);
    		if(count > curr) {
    			chosenEl = elem;
    			break;
    		}
    	}
    	
    	return chosenEl;
    }

    /**
     * Returns true if the Bag contains one or more instances of o
     */
    @Override
    public boolean contains(Object o) {
    	if(heavyBag.containsKey(o)) {
    		return true;
    	}
    	return false;
    }


    /**
     * Decrements the number of instances of o in the Bag.
     * 
     * @return return true if and only if at least one instance of o exists in
     *         the Bag and was removed.
     */
    @Override
    public boolean remove(Object o) {
    	//checks if the element is even in the bag
    	if(!heavyBag.containsKey(o)) {
    		return false;
    	} else {
    		//removes the last instance of the object if there are multiple
    		if(heavyBag.get(o) >= 2) {
    			heavyBag.replace((T)o, heavyBag.get(o)-1);
    			size--;
    		//if there is only 1 appearance of the object key then it gets removed 
    		} else {
    			heavyBag.remove(o);
    			size--;
    		}
    	}
    	return true;
    }

    /**
     * Total number of instances of any object in the Bag (counting duplicates)
     */
    @Override
    public int size() {
    	return size;
    }
}