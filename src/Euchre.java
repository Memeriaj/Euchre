import java.util.ArrayList;

public class Euchre {
	public ArrayList<Card> allCards = new ArrayList<Card>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public Round currentRound;
	ArrayList<Round> roundHistory = new ArrayList<Round>();
	public int[] score = new int[2];
	
	public Euchre(){
		setUpAllCards();
		setUpPlayers();
		currentRound = new Round(allCards, players, 0);
		score[0] = 0;
		score[1] = 0;
	}
	
	
	/*Sets up the list of cards, should only be called when Euchre is initialized*/
	private void setUpAllCards(){
		for(int x=9; x<15; x++){
			allCards.add(new Card(Card.SUIT.CLUBS, x));
			allCards.add(new Card(Card.SUIT.DIAMONDS, x));
			allCards.add(new Card(Card.SUIT.HEARTS, x));
			allCards.add(new Card(Card.SUIT.SPADES, x));
		}
	}
	
	/*Adds each player to the list of players. Can edit later to allow input*/
	private void setUpPlayers(){
		players.add(new Player("Human"));
		players.add(new AIPlayer("AI 1"));
		players.add(new AIPlayer("AI 2"));
		players.add(new AIPlayer("AI 3"));
	}
	
	public void humanPlayCard(String cardBeingPlayed){
		Card playedCard = players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(cardBeingPlayed);
		playCard(playedCard);
	}
	
	public boolean[] getPlayableCardsForHuman()
	{
		return players.get(0).getPlayableCards(currentRound.currentTrick.leadingSuit, currentRound.trump);
	}
	
	public boolean isCurrentPlayerAI()
	{
		return (players.get(currentRound.currentTrick.currentPlayer) instanceof AIPlayer); 
	}

	public void makeAIPlay() {
		AIPlayer aiPlayer = ((AIPlayer) players.get(currentRound.currentTrick.currentPlayer));
		Card cardToPlay = aiPlayer.getCardToPlay(currentRound.currentTrick);
		playCard(cardToPlay);
	}
	
	private void playCard(Card c)
	{
		currentRound.playCard(c);
		if (currentRound.isOver())
		{
			int[] roundScore = currentRound.scoreRound();
			score[0] += roundScore[0];
			score[1] += roundScore[1];
			roundHistory.add(currentRound);
			currentRound = currentRound.getNextRound();
		}
	}
}
