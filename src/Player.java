import java.util.ArrayList;


public class Player {
	public String name;
	public ArrayList<Card> hand;
	
	protected ArrayList<Card> getCardsInHandOfSuit(Card.SUIT suit)
	{
		ArrayList<Card> ret = new ArrayList<Card>();
		for (Card c: hand)
		{
			if (c.suit == suit)
				ret.add(c);
		}
		return ret;
	}
	
	protected Card getHighestValueCardOfSuit(Card.SUIT suit)
	{
		Card highestCard = null;
		for (Card c: hand)
		{
			if (c.suit == suit && (highestCard == null || c.value > highestCard.value))
			{
				highestCard = c;
			}
		}
		return highestCard;
	}
	
	protected Card getLowestValueCardOfSuit(Card.SUIT suit)
	{
		Card lowestCard = null;
		for (Card c: hand)
		{
			if (c.suit == suit && (lowestCard == null || c.value < lowestCard.value))
			{
				lowestCard = c;
			}
		}
		return lowestCard;
	}
	
	// ignores suit, used for leading
	protected Card getHighestValueCard()
	{
		Card highestCard = null;
		for (Card c: hand)
		{
			if (highestCard == null || c.value > highestCard.value)
			{
				highestCard = c;
			}
		}
		return highestCard;
	}
	
	// ignores suit, used for playing off
	protected Card getLowestValueCard()
	{
		Card lowestCard = null;
		for (Card c: hand)
		{
			if (lowestCard == null || c.value < lowestCard.value)
			{
				lowestCard = c;
			}
		}
		return lowestCard;
	}
	
	protected boolean hasCardOfSuit(Card.SUIT suit)
	{
		for (Card c: hand)
		{
			if (c.suit == suit)
			{
				return true;
			}
		}
		return false;
	}
	
	protected void removeCardFromHand(Card cardToRemove)
	{
		hand.remove(cardToRemove);
	}
}
