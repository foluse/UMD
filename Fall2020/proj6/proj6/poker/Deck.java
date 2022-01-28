                
package poker;

public class Deck {

	private Card[] cards;

	//initializes a new deck of 52cards
	public Deck() { 
		cards = new Card[52];
		for (int i = 0; i < cards.length; i++) {
			int x = (i + 1) % 13;
			if (x == 0) {
				x = 13; //splits deck into 4 suits
			}
			cards [i] = new Card(x, i/13); //assigns a value to each card
		}
	}

	//copy constructor
	public Deck(Deck other) { 
		cards = new Card[other.cards.length];
		for (int i = 0; i < cards.length; i++) {
			cards[i] = other.cards[i];
		}
	}

	//returns the position of a card in the array
	public Card getCardAt(int position) {
		return cards[position];
	}

	//returns the size of cards in the array
	public int getNumCards() {
		return cards.length;
	}

	/*Re-arranges cards in the deck. Cards are divided into two halves,
	top half and bottom half from a given card. This shuffle will result in a 
	new array with the first the first card from the topPacket, followed by the 
	first card of the bottomPacket If there are an odd number of cards the 
	topPacket will have one more card than the bottom.*/
	public void shuffle() {
		Card [] topPacket;
		Card [] bottomPacket = new Card[cards.length/2];
		
		if (cards.length % 2 == 0) {
			topPacket = new Card[bottomPacket.length];
		} else {
			topPacket = new Card[bottomPacket.length + 1];
		}
		for (int i = 0; i < topPacket.length; i++) {
			topPacket[i] = cards[i];
		}
		for (int i = 0; i< bottomPacket.length; i++) {
			bottomPacket[i] = cards[topPacket.length + i];
		}
		
		int countT = 0; //counts the number of cards in top packet
		int countB = 0; //counts the number of cards in bottom packet
		
		//shuffles top and bottom packets alternatively
		for (int i = 0; i < cards.length; i++) {
			if (i % 2 == 0) {
				cards [i] = topPacket[countT];
				countT++;
			} else {
				cards[i] = bottomPacket[countB];
				countB++;
			}
		}
	}
	/*Divides the deck into two subpackets: the part above a specified position,
	and the part that is at the specified position or below. The two packets are
	then reversed, with the top packet placed on the bottom and the bottom 
	packet being placed on the top. */
	public void cut(int position) {
		Card[] topPacket = new Card[position];
		for (int i = 0; i < topPacket.length; i++) {
			topPacket[i] = cards[i];
		}
		
		Card[] bottomPacket = new Card[cards.length - position];
		 for (int i = 0; i < bottomPacket.length; i++) {
			 bottomPacket[i] = cards[position + i];
		 }
		 
		 //places bottomPacket on top
		 for (int i = 0; i < bottomPacket.length; i++) {
			 cards[i] = bottomPacket[i];
		 }
		 //places topPacket on bottom
		 for (int i = 0; i < topPacket.length; i++) {
			 cards[i + bottomPacket.length] = topPacket[i];
		 }
		 
	}

	/*Removes a specified number of cards from the top of the deck and returns 
	 them as an array. */
	public Card[] deal(int numCards) {
		Card[] smaller = new Card[cards.length - numCards];
		Card[] dealt = new Card[numCards];
		

		for (int i = 0; i < dealt.length; i++) {
			dealt[i] = cards[i];
		}
		
		for (int i = 0; i < smaller.length; i++) {
			smaller[i] = cards[i + numCards];
		}
		
		cards = smaller;
		return dealt;
	}
		
}
     