package blackjack;

import java.util.ArrayList;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {

	private ArrayList <Card> dealerCards;
	private ArrayList <Card> playerCards;

	Deck deck;

	//returns dealerCards
	public ArrayList<Card> getDealerCards(){
		ArrayList<Card> copy = new ArrayList<Card>(dealerCards.size());
		for(Card c: dealerCards) {
			copy.add(c);
		}
		return copy;
	}

	//returns  playerCards
	public ArrayList<Card> getPlayerCards(){
		ArrayList<Card> copy = new ArrayList<Card>(playerCards.size());
		for(Card c: playerCards) {
			copy.add(c);
		}
		return copy;
	}

	//sets the dealerCards
	public void setDealerCards (ArrayList<Card> cards) {
		dealerCards = new ArrayList<Card>(cards.size());
		ArrayList<Card> copy = new ArrayList<Card> (cards.size());
		for(Card c: cards) {
			copy.add(c);
		}

		for(Card c: copy) {
			dealerCards.add(c);
		}
	}

	//sets the playerCards
	public void setPlayerCards (ArrayList<Card> cards) {
		playerCards = new ArrayList<Card>(cards.size());
		ArrayList<Card> copy = new ArrayList<Card> (cards.size());
		for(Card c: cards) {
			copy.add(c);
		}

		for(Card c: copy) {
			playerCards.add(c);
		}
	}

	//creates and randomly shuffles a new deck
	public void createAndShuffleDeck(Random random) {
		deck = new Deck();
		deck.shuffle(random);
	}

	//deals the initial two cards for the dealer
	public void initialDealerCards() {
		dealerCards = new ArrayList <Card>();
		dealerCards.add(deck.dealOneCard());
		dealerCards.add(deck.dealOneCard());
	}

	//deals the initial two cards for the player
	public void initialPlayerCards() {
		playerCards = new ArrayList<Card>();
		playerCards.add(deck.dealOneCard());
		playerCards.add(deck.dealOneCard());

	}
	
	public void playerTakeCard() {
		playerCards.add(deck.dealOneCard());
	}

	public void dealerTakeCard() {
		dealerCards.add(deck.dealOneCard());
	}

	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand){
		//stores the value of the hand
		ArrayList<Integer> handValue = new ArrayList<Integer> (); 
		int numAces = 0; //counts # of Aces in the hand
		int value = 0; //value of the hand

		//adds up all values in the hand
		for(Card c: hand) {
			value = value + c.getRank().getValue();
			//increments numAces if an ace is present
			if(c.getRank()== Rank.ACE) {
				numAces++;
			}
		}

		/* a single number is added to handValue representing the value of the hand if: (1) there is no ace
		   in the hand OR (2) if its possible for value to be greater than 21 when an ace is present */
		if(numAces == 0 || (numAces >= 1 && (value + 10) > 21 )) {
			handValue.add(value);
		}

		/* two numbers are added to handValue when an ace is present, and when both possible values for the hand remain
		   less than 21 */
		if(numAces >= 1 && value + 10 <= 21) {
			handValue.add(value);
			handValue.add(value + 10);
		}
		return handValue;
	}

	public static HandAssessment assessHand(ArrayList<Card> hand) {
		if(hand.size() < 2) {
			return HandAssessment.INSUFFICIENT_CARDS;
		}

		//an arrayList of type Integer that allows for assessment of the hand
		ArrayList<Integer> handValue = BlackjackModel.possibleHandValues(hand);

		//checks if the current hand is a blackJack (value equals 21)
		boolean blackJack = false;

		//iterates through the hand and checks if its value is 21
		for(Integer h: handValue) {
			if(h == 21) {
				blackJack = true;
			}
		}

		if(hand.size() == 2 && blackJack) {
			return HandAssessment.NATURAL_BLACKJACK;
		}

		if(handValue.get(0) > 21) {
			return HandAssessment.BUST;
		}

		return HandAssessment.NORMAL;
	}

	//assesses the result of the game, returns PLAYER_WON, PLAYER_LOST, NATURAL_BLACKJACK, or PUSH
	public GameResult gameAssessment() {

		HandAssessment playerAssessment = BlackjackModel.assessHand(getPlayerCards());
		HandAssessment dealerAssessment = BlackjackModel.assessHand(getDealerCards());

		//handValue for the player's cards
		ArrayList<Integer> playerHand = BlackjackModel.possibleHandValues(getPlayerCards());

		//handValue for the dealer's cards
		ArrayList<Integer> dealerHand = BlackjackModel.possibleHandValues(getDealerCards());

		//handValue that is used for the player
		Integer playerValue = oneValue(playerHand);

		//handValue that is used for the dealer
		Integer dealerValue = oneValue(dealerHand);

		if(naturalBlackjack(playerAssessment) && !naturalBlackjack(dealerAssessment)) {
			return GameResult.NATURAL_BLACKJACK;
		}

		if((playerValue == 21) && naturalBlackjack(dealerAssessment)) {
			return GameResult.PUSH;
		}

		if(naturalBlackjack(playerAssessment) && naturalBlackjack(dealerAssessment)) {
			return GameResult.PUSH;
		}

		if(!bust(playerAssessment) && bust(dealerAssessment)) {
			return GameResult.PLAYER_WON;
		}

		if(bust(playerAssessment)) {
			return GameResult.PLAYER_LOST;
		}

		if(playerValue == 21 && bust(dealerAssessment)) {
			return GameResult.PLAYER_WON;
		}

		if(playerValue!=21 && naturalBlackjack(dealerAssessment)) {
			return GameResult.PLAYER_LOST;
		}

		if(playerAssessment == HandAssessment.NORMAL && dealerAssessment == HandAssessment.NORMAL) {
			if(playerValue > dealerValue) {
				return GameResult.PLAYER_WON;
			}  else if (playerValue < dealerValue) {
				return GameResult.PLAYER_LOST;
			} else if (playerValue == dealerValue) {
				return GameResult.PUSH;
			} else if (dealerValue > 21) {
				return GameResult.PLAYER_WON;
			} else if((playerValue == 21) && (dealerValue != 21)) {
				return GameResult.PLAYER_WON;
			} else if(playerValue != 21 && dealerValue == 21){
				return GameResult.PLAYER_LOST;
			}   else if((playerValue == 21) && (dealerValue == 21)){
				return GameResult.PUSH;
			} 
		}

		return null;
	}
	//user created method, checks if the handAssessment is normal

	//user created method, returns the most advantageous possible value of the hand
	//user created method, returns the most advantageous number of the given handValue
	private Integer oneValue(ArrayList<Integer> handValue) { 
		//checks the size of handValue, if the list is size 2 and the second element is larger than 21 it'll r
		//be removed from the list
		if(handValue.size() == 2) {
			if(handValue.get(1) > 21) { 
				handValue.remove(1); 
			} else {
				handValue.remove(0); 
			}  
		}
		//the new value for the hand
		Integer value = 0;
		//iterates through the now mutated handValue list and assigns the Integer inside it to value 
		for(Integer x: handValue) {
			value = value + x;
		}
		return value;
	}

	//user created method, checks if the hand value is a natural black jack
	private boolean naturalBlackjack(HandAssessment handAssessment) {
		if(handAssessment == HandAssessment.NATURAL_BLACKJACK) {
			return true;
		}
		return false;
	}

	//user created method, checks if the hand value is a bust
	private boolean bust(HandAssessment handAssessment) {
		if(handAssessment == HandAssessment.BUST) {
			return true;
		}
		return false;
	}

	//user created method, returns the most advantageous value for when possibleHandValues returns a list of size 2 


	//determines if the dealer should take another card
	public boolean dealerShouldTakeCard() {
		ArrayList<Integer> dealerValue = BlackjackModel.possibleHandValues(getDealerCards());

		//evaluates as true if there is an Ace present in the dealer's hand 
		if(dealerValue.size() == 2) {
			//returns if there is an ACE present in the dealer's hand and the hand can't be valued as 7
			if(dealerValue.get(0) >= 17 || (dealerValue.get(1) > 17 && dealerValue.get(1) <= 21)) {
				return false;
			} else if (dealerValue.get(0) <= 16){
				return true;
			}

		} else if(dealerValue.size() == 1) {
			if(dealerValue.get(0) <= 16) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
}



