package tests;

import deckOfCards.*;
import blackjack.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.Test;

public class PublicTests {

	@Test
	public void testDeckConstructorAndDealOneCard() {
		Deck deck = new Deck();
		for (int suitCounter = 0; suitCounter < 4; suitCounter++) {
			for (int valueCounter = 0; valueCounter < 13; valueCounter++) {
				Card card = deck.dealOneCard();
				assertEquals(card.getSuit().ordinal(), suitCounter);
				assertEquals(card.getRank().ordinal(), valueCounter);
			}
		}
	}
	
	/* This test will pass only if an IndexOutOfBoundsException is thrown */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testDeckSize() {
		Deck deck = new Deck();
		for (int i = 0; i < 53; i++) {  // one too many -- should throw exception
			deck.dealOneCard();
		}
	}

	@Test
	public void testDeckShuffle() {
		Deck deck = new Deck();
		Random random = new Random(1234);
		deck.shuffle(random);
		assertEquals(new Card(Rank.KING, Suit.CLUBS), deck.dealOneCard());
		assertEquals(new Card(Rank.TEN, Suit.CLUBS), deck.dealOneCard());
		assertEquals(new Card(Rank.JACK, Suit.SPADES), deck.dealOneCard());
		for (int i = 0; i < 20; i++) {
			deck.dealOneCard();
		}
		assertEquals(new Card(Rank.SIX, Suit.CLUBS), deck.dealOneCard());
		assertEquals(new Card(Rank.FIVE, Suit.CLUBS), deck.dealOneCard());
		for (int i = 0; i < 24; i++) {
			deck.dealOneCard();
		}
		assertEquals(new Card(Rank.EIGHT, Suit.CLUBS), deck.dealOneCard());
		assertEquals(new Card(Rank.JACK, Suit.HEARTS), deck.dealOneCard());
		assertEquals(new Card(Rank.JACK, Suit.CLUBS), deck.dealOneCard());
	}
	
	@Test
	public void testGameBasics() {
		Random random = new Random(3723);
		BlackjackModel game = new BlackjackModel();
		game.createAndShuffleDeck(random);
		game.initialPlayerCards();
		game.initialDealerCards();
		game.playerTakeCard();
		game.dealerTakeCard();
		ArrayList<Card> playerCards = game.getPlayerCards();
		ArrayList<Card> dealerCards = game.getDealerCards();
		assertTrue(playerCards.get(0).equals(new Card(Rank.QUEEN, Suit.HEARTS)));
		assertTrue(playerCards.get(1).equals(new Card(Rank.SIX, Suit.DIAMONDS)));
		assertTrue(playerCards.get(2).equals(new Card(Rank.EIGHT, Suit.HEARTS)));
		assertTrue(dealerCards.get(0).equals(new Card(Rank.THREE, Suit.CLUBS)));
		assertTrue(dealerCards.get(1).equals(new Card(Rank.NINE, Suit.SPADES)));
		assertTrue(dealerCards.get(2).equals(new Card(Rank.FIVE, Suit.CLUBS)));		
	}
	
	@Test
	public void case1() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.ACE, Suit.CLUBS));
		playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.ACE, Suit.CLUBS));
		dealerCards.add(new Card(Rank.ACE, Suit.SPADES));
		dealerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 1 Expected: PUSH");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case2() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.ACE, Suit.CLUBS));
		playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.TWO, Suit.CLUBS));
		dealerCards.add(new Card(Rank.TWO, Suit.SPADES));
		dealerCards.add(new Card(Rank.FOUR, Suit.HEARTS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 2 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case3() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.ACE, Suit.CLUBS));
		playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.CLUBS));
		dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.FOUR, Suit.HEARTS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 3 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case4() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.ACE, Suit.CLUBS));
		playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.FOUR, Suit.HEARTS));
		dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 4 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case5() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.ACE, Suit.CLUBS));
		playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 5 Expected: WON");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case6() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.CLUBS));
		dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 6 Expected: WON");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case7() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 7 Expected: PUSH");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case8() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 8 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case9() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 9 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case10() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//playerCards.add(new Card(Rank.ACE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 10 Expected: WON");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case11() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 11 Expected: WON");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case12() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 12 Expected: WON");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case13() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FOUR, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 13 Expected: PUSH");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case14() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FOUR, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 14 Expected: PUSH");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case15() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FOUR, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.EIGHT, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 15 Expected: WON");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case16() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		//playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FOUR, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.SEVEN, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 16 Expected: BLACKJACK");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
	
	@Test
	public void case17() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		//playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FOUR, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 17 Expected: BLACKJACK");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case18() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		//playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 18 Expected: BLACKJACK");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case19() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		//playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 19 Expected: PUSH");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case20() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.SIX, Suit.CLUBS));
		//playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 20 Expected: BLACKJACK");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case21() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 21 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case22() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.NINE, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 22 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case23() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.NINE, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 23 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case24() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		//dealerCards.add(new Card(Rank.NINE, Suit.HEARTS));
		dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 24 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}

	@Test
	public void case25() {
		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.KING, Suit.CLUBS));
		playerCards.add(new Card(Rank.FIVE, Suit.SPADES));
		playerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		
		dealerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		dealerCards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
		//dealerCards.add(new Card(Rank.KING, Suit.CLUBS));
		//dealerCards.add(new Card(Rank.SIX, Suit.SPADES));
		dealerCards.add(new Card(Rank.NINE, Suit.HEARTS));
		//dealerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		
		System.out.println("----------------");
		System.out.println("CASE 25 Expected: LOST");
		System.out.println(BlackjackModel.assessHand(playerCards).toString());
		System.out.println(BlackjackModel.assessHand(dealerCards).toString());
		System.out.println(game.gameAssessment().toString());
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
		//assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		//assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
		//assertTrue(game.gameAssessment() == GameResult.PUSH);
	}
}
