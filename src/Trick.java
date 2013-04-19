import java.util.ArrayList;


class Trick {
	public int leadingPlayer;
	public Card.SUIT leadingSuit;
	public int currentPlayer;
	public int currentWinner;
	public Card currentWinningCard;
	public Card.SUIT trump;
	public ArrayList<Card> cardsPlayed;
	
	public Trick(int leadPlayer, Card.SUIT tr)
	{
		leadingPlayer = leadPlayer % 4;
		currentPlayer = leadPlayer % 4;
		trump = tr;
		cardsPlayed = new ArrayList<Card>();
	}
	
	public void playCardForCurrentPlayer(Card c)
	{
		if (currentPlayer == leadingPlayer)
		{
			leadingSuit = c.suit;
			currentWinner = currentPlayer;
			currentWinningCard = c;
		}
		else if (c.greater(currentWinningCard, trump, leadingSuit))
		{
			currentWinner = currentPlayer;
			currentWinningCard = c;
		}
		cardsPlayed.add(c);
		incrementTurn();
	}
	
	public void incrementTurn()
	{
		currentPlayer = (currentPlayer + 1) % 4;
	}
	
	public boolean isStarted()
	{
		return cardsPlayed.size() != 0;
	}
	
	public boolean isOver()
	{
		return cardsPlayed.size() == 4;
	}
	
	public Trick getNextTrick()
	{
		return new Trick(currentWinner,trump);
	}

	public String stringOfTrickPlayed(){
		String out = "";
		int currentPlayer = leadingPlayer;
		for(Card card : cardsPlayed){
			if(currentPlayer == 0)
				out += "You: ";
			else
				out += "Player " + currentPlayer + ": ";
			out += card + "\n";
			currentPlayer = (currentPlayer + 1) % 4;
		}
		return out;
	}
}
