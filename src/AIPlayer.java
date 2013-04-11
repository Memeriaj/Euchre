


public class AIPlayer extends Player 
{
	public AIPlayer(String name) {
		super(name);
	}
	
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

}
