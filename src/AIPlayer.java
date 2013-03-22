import java.util.ArrayList;


public class AIPlayer extends Player 
{
	// used for leading
	public Card getCardToPlay()
	{
		Card cardToPlay = this.getHighestValueCard();
		this.removeCardFromHand(cardToPlay);
		return cardToPlay;
	}
	
	public Card getCardToPlay(Card.SUIT leadingSuit, int currentHighestValue)
	{
		Card cardToPlay = this.getHighestValueCardOfSuit(leadingSuit);
		if (cardToPlay == null)
		{
			cardToPlay = this.getLowestValueCard();
		}
		this.removeCardFromHand(cardToPlay);
		return cardToPlay;
	}
}
