import java.util.ArrayList;


public class Player {
	public String name;
	public ArrayList<Card> hand;
	
	public Player(String name)
	{
		this.name = name;
		hand = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getCardsInHandOfSuit(Card.SUIT suit)
	{
		ArrayList<Card> ret = new ArrayList<Card>();
		for (Card c: hand)
		{
			if (c.suit == suit)
				ret.add(c);
		}
		return ret;
	}
	
	public Card getHighestValueCardOfSuit(Card.SUIT suit)
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
	
	public Card getLowestValueCardOfSuit(Card.SUIT suit)
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
	public Card getHighestValueCard()
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
	public Card getLowestValueCard()
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
	
	public boolean hasCardOfSuit(Card.SUIT suit)
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
	
	public boolean[] getPlayableCards(Card.SUIT leadingSuit, Card.SUIT trumpSuit)
	{
		boolean[] toReturn = new boolean[hand.size()];
		boolean hasAtLeastOneOfSuit = false;
		Card.SUIT sameColorAsTrump = Card.getSameColorSuit(trumpSuit);
		Card c;
		for (int i = 0; i< hand.size() ; i++)
		{
			c=hand.get(i);
			if (c.suit == leadingSuit || (leadingSuit == trumpSuit && c.suit == sameColorAsTrump && c.value == 11)) // if they have a card to follow suit or the left
			{
				hasAtLeastOneOfSuit = true;
				toReturn[i]=true;
			}
			else
			{
				toReturn[i] = false;
			}
		}
		if (!hasAtLeastOneOfSuit) // If they can't follow suit, they can lay anything off
		{
			for (int i = 0;i<hand.size();i++)
			{
				toReturn[i]=true;
			}
		}
		return toReturn;
	}
	
	public void removeCardFromHand(Card cardToRemove)
	{
		hand.remove(cardToRemove);
	}
	
	public Card removeCardFromHand(String cardString){
		Card cardToReturn = null;
		for(int q=0; q<hand.size(); q++){
			cardToReturn = hand.get(q);
			System.out.println("CardToReturn: "+cardToReturn.toString());
			if(cardToReturn.toString().equals(cardString)){
				hand.remove(q);
				return cardToReturn;
			}
		}
		return null;
	}
}
