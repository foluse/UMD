import java.util.List;
import java.util.ArrayList;

public class MinimumSnippet {
	private Iterable<String> doc; //the document where 

	private List<String> terms; //collection of the search terms

	//Collection that holds all the snippets. (Casted as an ArrayList)
	private ArrayList<List<String>> allSnippets; // collection that holds all the snippets

	private List<String> minSnippet; //the smallest snippet

	private ArrayList<Integer> positions; //collection of all starting positions

	private int startPos; //starting index of the snippet

	private int endPos; //last index of the snippet

	/* Computes a minimum snippet given a document and a set of terms. Finds the shortest subsequence containing all the 
	 * terms as they appear in the document. */
	public MinimumSnippet(Iterable<String> document, List<String> terms) {
		this.doc = document;
		this.terms = terms;

		//if the terms collection is empty, then code throws an exception
		if(terms.size() ==  0) {
			throw new IllegalArgumentException("NO TERMS PROVIDED");
		}

		//ArrayList representing the document
		ArrayList<String> docList = docToArrrayList(doc);

		//tracks the current index within the document
		int currIndex = 0;

		minSnippet = new ArrayList<String>();
		allSnippets = new ArrayList<List <String>>();
		positions = new ArrayList<Integer>();

		if(foundAllTerms()) {
			for(String s: docList) {
				//collection for the search terms
				ArrayList<String> snippet = new ArrayList<String>();

				/*if the search term is found in the documentList it gets added to
				 snippet. the snippet then gets stored to allSnippets which stores all 
				 possible snippets. the current index is stored in the positions collection. */
				if(terms.contains(s)) {
					snippet.add(s);
					allSnippets.add(snippet);
					positions.add(currIndex);
				}
				currIndex++;
			}

			// Copy of the position collection, ensures data encapsulation
			ArrayList<Integer> tempPositions = new ArrayList<Integer>();
			for(Integer pos: positions) {
				tempPositions.add(pos);
			}

			/* traverses through the snippet collection, finds the smallest snippet
			 * based on the snippet's length and sets it  */
			for(List<String> snippet: allSnippets) {
				for(int i = tempPositions.get(0) + 1; i < docList.size(); i++) {
					if(snippet.containsAll(terms)) {
						tempPositions.remove(0);
						break;
					} else {
						snippet.add(docList.get(i));
					}
				}
			} 

			/* Iterates through the snippet collection searching if the current snippet is the smallest. If it is
			   it gets stored as the minimum snippet. */

			int minIndex = 0; //stores the starting position of the minimum snippet in the doc
			int index = 0; //the location of the minimum snippet in the snippet collection
			while(index < allSnippets.size()) {
				if((minSnippet.size() > allSnippets.get(index).size() )|| minSnippet.isEmpty()) {
					minSnippet = allSnippets.get(index);
					minIndex = index;
				}
				index++; 
			}

			startPos = positions.get(minIndex); 
			endPos = (startPos + minSnippet.size()) - 1;

		}
	}

	//user created method, converts the document into an ArrayList
	private ArrayList<String> docToArrrayList(Iterable<String> doc){
		ArrayList<String> document = new ArrayList<String>();

		//traverses through the document and adds each term from the document into the document ArrayList
		for(String term: doc) {
			document.add(term);
		}
		return document;
	}
	
	//Returns whether or not all terms were found in the document.  
	//Returns whether or not all terms were found in the document. 
	public boolean foundAllTerms() {

		//tracks the num of times a search term is present in the document
		int counter = 0;

		for(String s: doc) {

			if(counter == terms.size()) {
				break;
			}

			//iterates through the terms list
			for(int i = 0; i < terms.size(); i++) {
				//checks if the search term at the current index matches the term in doc
				if(terms.get(i).equals(s)) {
					counter++;
				}
			}
		}

		if(counter == terms.size()) {
			return true;
		}
		return false;
	}

	//Returns the index in the document of the first element of the snippet
	//Returns the index in the document of the first element of the snippet
	public int getStartingPos() {
		if(!foundAllTerms()) {
			throw new IllegalArgumentException("SEARCH TERMS NOT FOUND IN DOCUMENT");
		}
		return startPos;
	}
	 
	//Returns the index in the document of the last element of the snippet
	//Returns the index in the document of the last element of the snippet
	public int getEndingPos() {
		if(!foundAllTerms()) {
			throw new IllegalArgumentException("SEARCH TERMS NOT FOUND IN DOCUMENTD");
		}
		return endPos;
	}
	
	//Returns total number of elements contained in the snippet. 
	//Returns total number of elements contained in the snippet.
	public int getLength() {
		if(!foundAllTerms()) {
			throw new IllegalArgumentException("SEARCH TERMS NOT FOUND IN DOCUMENT");
		}
		return minSnippet.size();
	}


	//Returns the position of the term as it appears in the document.
	public int getPos(int index) {
		int pos = 0; //temp variable
		int position = 0; //stores the position of the index in the doument

		if(!foundAllTerms()) {
			throw new IllegalArgumentException("SEARCH TERMS NOT FOUND IN DOCUMENT");
		}

		for(String s: doc) {
			if(pos >= startPos && pos <= endPos) {
				if(terms.get(index).equals(s)){
					position = pos;
					break;
				}
			}
			pos++;
		}

		return position;

	}

}
