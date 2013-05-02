
import java.util.ArrayList;

public class AIPlayer extends Player 
{
	public AIPlayer(String name) {
		super(name);
	}
	/*
	public Card getCardToPlay(Trick trick)
	{
		Card cardToPlay;
		if (trick.isStarted())
		{
			cardToPlay = this.getHighestValueCardOfSuit(trick.leadingSuit);
			if (cardToPlay == null)
			{
				cardToPlay = this.getLowestValueCard();
			}
		}
		else
		{
			cardToPlay = this.getHighestValueCard();
		}
		this.removeCardFromHand(cardToPlay);
		return cardToPlay;
	}
	*/
	public Card getCardToPlay(Trick trick)
	{
		Card cardToPlay = new NullCard();
		ArrayList<Card> choices = new ArrayList<Card>();
		boolean[] choiceArray = this.getPlayableCards(trick.leadingSuit, trick.trump);
		for(int x=0; x<this.hand.size();x++)
			if(choiceArray[x])
				choices.add(this.hand.get(x));
		for(int x=0;x<choices.size();x++)
			if (choices.get(x).cardValue(trick.trump, trick.leadingSuit) > cardToPlay.cardValue(trick.trump, trick.leadingSuit))
				cardToPlay = choices.get(x);
		this.removeCardFromHand(cardToPlay);
		return cardToPlay;
	}
	
	public boolean pickUp(Card kitty)
	{
		ArrayList<Card> h = super.hand;
		Card.SUIT choice = null;
		int max = 0;
		if(handValue(h,Card.SUIT.CLUBS)>max){
			choice = Card.SUIT.CLUBS;
			max = handValue(h,Card.SUIT.CLUBS);
		}
		if(handValue(h,Card.SUIT.SPADES)>max){
			choice = Card.SUIT.SPADES;
			max = handValue(h,Card.SUIT.SPADES);
		}
		if(handValue(h,Card.SUIT.HEARTS)>max){
			choice = Card.SUIT.HEARTS;
			max = handValue(h,Card.SUIT.HEARTS);
		}
		if(handValue(h,Card.SUIT.DIAMONDS)>max){
			choice = Card.SUIT.DIAMONDS;
			max = handValue(h,Card.SUIT.DIAMONDS);
		}
		if(max>22 && (choice == kitty.suit))
			return true;
		return false;
	}

	public boolean pickUpAsDealer(Card kitty)
	{
		ArrayList<Card> h = super.hand;
		int max = Math.max(Math.max(handValue(h,Card.SUIT.CLUBS),handValue(h,Card.SUIT.SPADES)),
				           Math.max(handValue(h,Card.SUIT.DIAMONDS),handValue(h,Card.SUIT.HEARTS)));
		h.add(kitty);
		for(int x=0; x<5;x++){
			Card discard = h.remove(0);
			int val = (handValue(h,kitty.suit));
			if (val > max){
				h.remove(kitty);
				h.add(discard);
				return true;
			}
			h.add(discard);
		}
		h.remove(kitty);
		if (handValue(h, kitty.suit) > 35)
			return true;
		return false;
	}
	
	public Card discardDecider(Card kitty)
	{
		ArrayList<Card> h = super.hand;
		Card ret = kitty;
		int max = Math.max(Math.max(handValue(h,Card.SUIT.CLUBS),handValue(h,Card.SUIT.SPADES)),
				           Math.max(handValue(h,Card.SUIT.DIAMONDS),handValue(h,Card.SUIT.HEARTS)));
		h.add(kitty);
		for(int x=0; x<5;x++){
			Card discard = h.remove(0);
			int val = (handValue(h,kitty.suit));
			if (val > max){
				max = val;
				ret = discard;
			}
			h.add(discard);
		}
		h.remove(kitty);
		return ret;
	}
	
	public Card.SUIT trumpDecider(Card.SUIT invalid)
	{
		//return false;
		ArrayList<Card> h = super.hand;
		Card.SUIT choice = null;
		int max = 0;
		if(invalid != Card.SUIT.CLUBS && (handValue(h,Card.SUIT.CLUBS)>max)){
			choice = Card.SUIT.CLUBS;
			max = handValue(h,Card.SUIT.CLUBS);
		}
		if(invalid != Card.SUIT.SPADES && (handValue(h,Card.SUIT.SPADES)>max)){
			choice = Card.SUIT.SPADES;
			max = handValue(h,Card.SUIT.SPADES);
		}
		if(invalid != Card.SUIT.HEARTS && (handValue(h,Card.SUIT.HEARTS)>max)){
			choice = Card.SUIT.HEARTS;
			max = handValue(h,Card.SUIT.HEARTS);
		}
		if(invalid != Card.SUIT.DIAMONDS && (handValue(h,Card.SUIT.DIAMONDS)>max)){
			choice = Card.SUIT.DIAMONDS;
			max = handValue(h,Card.SUIT.DIAMONDS);
		}
		return choice;
		
	}
	
	public boolean callDecider(Card.SUIT invalid)
	{
		ArrayList<Card> h = super.hand;
		int max = 0;
		if(invalid != Card.SUIT.CLUBS && (handValue(h,Card.SUIT.CLUBS)>max)){
			max = handValue(h,Card.SUIT.CLUBS);
		}
		if(invalid != Card.SUIT.SPADES && (handValue(h,Card.SUIT.SPADES)>max)){
			max = handValue(h,Card.SUIT.SPADES);
		}
		if(invalid != Card.SUIT.HEARTS && (handValue(h,Card.SUIT.HEARTS)>max)){
			
			max = handValue(h,Card.SUIT.HEARTS);
		}
		if(invalid != Card.SUIT.DIAMONDS && (handValue(h,Card.SUIT.DIAMONDS)>max)){
			
			max = handValue(h,Card.SUIT.DIAMONDS);
		}
		return max>22;	
	}

	public boolean goAloneDecider(Card.SUIT trump)
	{
		return handValue(super.hand,trump)>35;	
	}
	
	public int handValue (ArrayList<Card> h, Card.SUIT trump)
	{
		int val = 0;
		for(int x=0; x<5;x++)
			val += h.get(x).biddingValue(trump);
		return val;
	}
}
