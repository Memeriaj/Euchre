
import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player 
{
	public enum DIFFICULTY
	{
		EASY,
		NORMAL
	}
	
	private DIFFICULTY difficulty;
	
	public AIPlayer(String name) {
		this(name,DIFFICULTY.NORMAL);
	}
	

	public AIPlayer(String name, DIFFICULTY difficulty)
	{
		super(name);
		this.difficulty = difficulty;
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
		switch (difficulty)
		{
		case EASY:
			boolean[] playableTrueFalse = this.getPlayableCards(trick.leadingSuit, trick.trump);
			ArrayList<Integer> playableIndices = new ArrayList<Integer>();
			for (int i = 0 ; i < playableTrueFalse.length;i++)
			{
				boolean b = playableTrueFalse[i];
				if (b)
					playableIndices.add(i);
			}
			Random random = new Random();
			return this.hand.get(playableIndices.get(random.nextInt(playableIndices.size())));
		case NORMAL:
			Card cardToPlayMax = new NullCard();
			Card cardToPlayMin = null;
			ArrayList<Card> choices = new ArrayList<Card>();
			boolean[] choiceArray = this.getPlayableCards(trick.leadingSuit, trick.trump);
			for(int x=0; x<this.hand.size();x++)
				if(choiceArray[x])
					choices.add(this.hand.get(x));
			System.out.println(choices.toString());
			if(trick.leadingSuit == null){
				for(int x=0;x<choices.size();x++){
					if (choices.get(x).cardValueNoLead(trick.trump) > cardToPlayMax.cardValueNoLead(trick.trump))
						cardToPlayMax = choices.get(x);	
				}
				this.removeCardFromHand(cardToPlayMax);
				return cardToPlayMax;	
			}
			for(int x=0;x<choices.size();x++){
				if (cardToPlayMin == null)
					cardToPlayMin = choices.get(x);
				if (choices.get(x).cardValue(trick.trump, trick.leadingSuit) > cardToPlayMax.cardValue(trick.trump, trick.leadingSuit))
					cardToPlayMax = choices.get(x);
				else if (choices.get(x).cardValue(trick.trump, trick.leadingSuit) < cardToPlayMin.cardValue(trick.trump, trick.leadingSuit))
					cardToPlayMin = choices.get(x);
			}
			if(cardToPlayMax.cardValue(trick.trump, trick.leadingSuit) > trick.currentWinningCard.cardValue(trick.trump, trick.leadingSuit)){
				this.removeCardFromHand(cardToPlayMax);
				return cardToPlayMax;
			}
			this.removeCardFromHand(cardToPlayMin);
			return cardToPlayMin;
		default:
			return null;
		}		
	}
	
	public boolean pickUp(Card kitty)
	{
		switch (difficulty)
		{
		case EASY:
			Random random = new Random();
			return random.nextBoolean();
		case NORMAL:
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
		default:
			return false;
		}
	}

	public boolean pickUpAsDealer(Card kitty)
	{
		switch (difficulty)
		{
		case EASY:
			Random random = new Random();
			return random.nextBoolean();
		case NORMAL:
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
		default:
			return false;
		}
	}
	
	public Card discardDecider(Card kitty)
	{
		switch (difficulty)
		{
		case EASY:
			Random random = new Random();
			int ind = random.nextInt(6);
			if (ind == 5)
				return kitty;
			else
				return this.hand.get(ind);
		case NORMAL:
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
		default:
			return null;
		}
	}
	
	public Card.SUIT trumpDecider(Card.SUIT invalid)
	{
		switch (difficulty)
		{
		case EASY:
			Card.SUIT ret = invalid;
			while (ret == invalid)
			{
				Random random = new Random();
				ret = Card.SUIT.values()[random.nextInt(4)];
			}
			return ret;
		case NORMAL:
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
		default:
			return null;
		}
	}
	
	public boolean callDecider(Card.SUIT invalid)
	{
		switch (difficulty)
		{
		case EASY:
			Random random = new Random();
			return random.nextBoolean();
		case NORMAL:
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
		default:
			return false;
		}
	}

	public boolean goAloneDecider(Card.SUIT trump)
	{
		return false;
//		switch (difficulty)
//		{
//		case EASY:
//			Random random = new Random();
//			return random.nextBoolean();
//		case NORMAL:
//			//return handValue(super.hand,trump)>35;
//			return true;
//		default:
//			return false;
//		}
	}
	
	public int handValue (ArrayList<Card> h, Card.SUIT trump)
	{
		int val = 0;
		for(int x=0; x<5;x++)
			val += h.get(x).biddingValue(trump);
		return val;
	}
}
