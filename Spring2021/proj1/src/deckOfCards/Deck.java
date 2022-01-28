package deckOfCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {

	private ArrayList <Card> cards;

	public Deck() {
		cards = new ArrayList <Card> (52);

		for(int suit = 0; suit < 4; suit++) { //divides the deck into 4 suits
			for(int rank = 0; rank < 13; rank++) { //assigns the cards their value the deck into values
				cards.add(new Card(Rank.values()[rank], Suit.values()[suit]));
			}
		}
	}


	//shuffles the deck
	public void shuffle(Random randomNumberGenerator) {
		Collections.shuffle(cards, randomNumberGenerator);
	}

	//deals the first card in the deck
	public Card dealOneCard() {
		Card removed = cards.get(0); 
		cards.remove(0);
		return removed;
		
	}
}
