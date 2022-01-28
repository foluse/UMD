package foodManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	/*
	 * STUDENTS:  You may NOT add any other instance variables or
	 * static variables to this class!
	 */
	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		items = new Listable[other.items.length];
		for (int i = 0; i < other.items.length; i++) {
			items [i] = other.items[i];
		}
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {
		return items.length;
	}

	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		return items[i];
	}

	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 * 
	 * In order to accomodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
		//creates a new list that is 1 item larger to store the parameter
		Listable [] temp = new Listable[items.length + 1];
		//tracks if the item has been added
		boolean added = false;

		//copies items from the original list into the new list
		for (int i = 0; i < items.length; i++) {
			temp[i] = items[i];
		}

		/*Initially checks if there were zero items in the original list, if 
		 true the parameter is added as the first item in the new list. 
		 Otherwise, the name of the parameter is compared lexicographically 
		 to the current index of the original list.*/
		if (items.length == 0) {
			temp[0] = itemToAdd;
		} else {
			for (int j = 0; j < items.length; j++) {
				if(itemToAdd.getName().compareTo(items[j].getName()) < 0 && 
						added == false) {
					added = true;
					temp[j] = itemToAdd;
					for(int k = j + 1; k < temp.length; k++) {
						temp[k] = items[k-1];
					}
				}
			}
		}
		//Checks if item has been added at the end of the original list.
		if (added == false) {
			temp[items.length] = itemToAdd;
		}
		items = temp;
	}

	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
		for (int i = 0; i < listToAdd.items.length; i++) {
			add(listToAdd.items[i]);
		}
	}

	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
		//creates a new list that is 1 item smaller
		Listable[] temp = new Listable[items.length];
		//tracks the element being removed
		int track = 0;
		//checks if parameter is in the list
		boolean present = false;
		for (int i = 0; i < items.length; i++) {
			if (items[i].getName().equals(itemToRemove.getName())) {
				present = true;
				break;
			}
		}
		//creates a smaller list if parameter is found
		if(present) {
			temp = new Listable[items.length-1];
		} 
		
		//copies items into list if there is no parameter is found
		if(temp.length == items.length) {
			for(int i = 0; i < items.length; i++) {
				temp[i] = items[i];
			}
		}
		//scans the list to see if it contains the same item as the parameter
		if (temp.length == items.length-1) {
			for (int i = 0; i < items.length; i++) {
				if (items[i].getName().equals(itemToRemove.getName())) {
					track = i;
					for (int j = 0; j < track; j++) {
						temp[j] = items[j];
					} 
					for (int k = track + 1; k < items.length; k++) {
						temp[k-1] = items[k];
					}
					break;
				}
			}
		}
		items = temp;
	}



	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		for (int i = 0; i < listToRemove.getSize(); i++) {
			remove(listToRemove.get(i));
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int cost = 0;
		for (int i = 0; i < items.length; i++) {
			cost+=items[i].getWholesaleCost();
		}
		return cost;
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int sum = 0;
		for (int i = 0; i <items.length; i++) {
			sum+=items[i].getRetailValue();
		}
		return sum;
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		boolean availability = false;
		for (int i = 0; i < items.length; i++) {
			if(items[i].getName().contentEquals(itemToFind.getName())) {
				availability = true;
			}
		}
		return availability;
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		//checks if a list of items is contained in the current list
		boolean availability = false; 
		//copy of list
		SortedListOfImmutables temp = new SortedListOfImmutables(this);
		
		//checks if all items in the parameter are also in the current list
		for (int i = 0; i < listToCheck.items.length; i++) {
			if(temp.checkAvailability(listToCheck.items[i]) == false) {
				availability = false;
				
			} else {
				availability = true;
				for(int j = 0; j < temp.items.length; j++) {
					if(temp.items[j].equals(listToCheck.items[i])) {
						temp.remove(temp.items[j]);
						break;
					}
				}
			}
		}
		return availability;
	}

	/*
	 * We'll give you this one for free.  Do not modify this method or you
	 * will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
