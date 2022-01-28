
package poker;
public class PokerHandEvaluator {

	//checks if hand has two cards of the same value
	public static boolean hasPair(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards.length; j++) {
				if (i!=j && cards[i].getValue() == cards[j].getValue()) {
					return true;
				}
			}
		}
		return false;
	}
	//checks if hand has two distinct matching pairs
	public static boolean hasTwoPair(Card[] cards) {
		int count = 0; //counts number of pairs
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards.length; j++) {
				if (i != j && cards[i].getValue() == cards [j].getValue()) {
					count++;
				}
			}
		}

		if (count == 2 || count == 4) {
			return true;
		} else {
			return false;
		}
	}
	//checks if hand has three cards with matching values
	public static boolean hasThreeOfAKind(Card[] cards) {
		boolean result = false;
		int x = 0;//counts num of times value is repeated
		for (int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if (i!= j && cards[i].getValue() == cards[j].getValue()) {
					x++;
				}
			}
		}
		if (x == 6 || x == 8) {
			result =  true;
		}
		return result;
	}

	//checks if hand has cards with values that are consecutive
	public static boolean hasStraight(Card [] cards) {
		boolean result = false;
		for (int i = 0; i < cards.length; i++) {
			for(int j = 0; j < cards.length; j++) {
				for (int k = 0; k < cards.length; k++) {
					for (int l = 0; l < cards.length; l++) {
						for (int m = 0; m < cards.length; m++) {
							if (i!=j && i!=k && i!=l && i!=m && 
									j!=k && j!=l && j!=m && l!=m) {
								int v1 = cards[i].getValue();// value of card 1
								int v2 = cards[j].getValue();// value of card 2
								int v3 = cards[k].getValue();// value of card 3
								int v4 = cards[l].getValue();// value of card 4
								int v5 = cards[m].getValue();// value of card 5
								if ((v2 == v1+1) && (v3 == v2+1) && (v4 == v3+1)
										&& (v5 == v4+1)) {
									if(v2 == 1 || v3 == 1 || v4 == 1) {
										result = false;
									} else {
										result = true;
									}
								}
								if (v1 == 10 && v2 == 11 && v3 == 12 && v4 == 13
										&& v5 == 1) {
									result = true;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	//checks if hand has cards all of the same suit
	public static boolean hasFlush(Card[] cards) {
		boolean result = false;
		int suit = cards[0].getSuit();

		for(int i = 0; i < cards.length; i++) {
			if(suit == cards[i].getSuit()) {
				result = true;
			}
		} 
		return result;
	}

	//checks if hand has a pair & three of a kind
	public static boolean hasFullHouse(Card[] cards) {
		if (hasTwoPair(cards) && hasThreeOfAKind(cards)) {
			return true;
		} else {
			return false;
		}
	}

	//checks if hand has four cards with the same value
	public static boolean hasFourOfAKind(Card[] cards) {
		boolean result = false;
		for (int i = 0; i < cards.length; i++) {
			for(int j = i + 1; j < cards.length; j++) {
				for (int k = j + 1; k < cards.length; k++) {
					for (int l = k + 1; l < cards.length; l++) {
						if (i!= j && j!=k && k!=j && 
								cards[i].getValue() == cards[j].getValue() && 
								cards[j].getValue() == cards[k].getValue() && 
								cards[k].getValue() == cards[l].getValue()) {
							result = true;
						}
					}

				}
			}
		}
		return result;
	}

	//checks if hand has consecutive values & if they are the same suit
	public static boolean hasStraightFlush(Card[] cards) {
		boolean result = false;
		if (hasStraight(cards) && hasFlush(cards)) {
			result = true;
		}  
		return result;
	}

}

