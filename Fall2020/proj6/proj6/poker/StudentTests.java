package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	//Deck.java tests
	@Test
	public void testDeckCopyConstructor() {
		Deck hand = new Deck();
		Deck other = new Deck(hand);
		assertTrue(hand.getCardAt(3).equals(other.getCardAt(2)));
	}
	
	@Test
	public void testDeckDeal() {
		Deck deck = new Deck();
		Card[] dealt = deck.deal(25);
		
		assertTrue(dealt[20].getValue() == 8);
		assertTrue(dealt[15].getSuit() == 1);
		assertTrue(dealt[9].getValue() == 10);
		assertTrue(dealt[6].getSuit() == 0);
	}
	
	//PokerHandEvaluator.java tests
	@Test
	public void testHasPair() {
		Card[] hand = new Card[5];
		hand[0] = new Card (2,3);
		hand[1] = new Card (2,0);
		hand[2] = new Card (6,3);
		hand[3] = new Card (4,2);
		hand[4] = new Card (5,0);
		
		assertTrue(PokerHandEvaluator.hasPair(hand));
 	}
	
	@Test
	public void testHasTwoPair() {
		Card[] hand = new Card[5];
		hand[0] = new Card(1,0);
		hand[1] = new Card(1,2);
		hand[2] = new Card(4,1);
		hand[3] = new Card(5,2);
		hand[4] = new Card(4,3);
		
		assertTrue(PokerHandEvaluator.hasTwoPair(hand));
	}
	
	@Test
	public void testHasThreeOfAKind() {
		Card[] hand = new Card[5];
		hand[0] = new Card(1,0);
		hand[1] = new Card(1,2);
		hand[2] = new Card(1,3);
		hand[3] = new Card(7,0);
		hand[4] = new Card(9,3);
		
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(hand));
	}
	
	@Test
	public void testHasStraight() {
		Card[] hand = new Card[5];
		hand[0] = new Card(10,0);
		hand[1] = new Card(11,2);
		hand[2] = new Card(12,4);
		hand[3] = new Card(13,1);
		hand[4] = new Card(1,3);
		
		assertTrue(PokerHandEvaluator.hasStraight(hand));
	}
	
	@Test
	public void testHasFlush() {
		Card[] hand = new Card[5];
		hand[0] = new Card(1,0);
		hand[1] = new Card(11,0);
		hand[2] = new Card(7,0);
		hand[3] = new Card(8,0);
		hand[4] = new Card(6,0);
		
		assertTrue(PokerHandEvaluator.hasFlush(hand));
	}
	
	@Test
	public void testHasFullHouse() {
		Card[] hand = new Card[5];
		hand[0] = new Card(5,2);
		hand[1] = new Card(5,0);
		hand[2] = new Card(5,1);
		hand[3] = new Card(3,2);
		hand[4] = new Card(3,1);
		
		assertTrue(PokerHandEvaluator.hasFullHouse(hand));
	}
}
