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
	
	
	public boolean isOver(){
		return (score[0] >= 10) || (score[1] >= 10);
	}

	/*Returns 0 if the human team won, 1 if the AI team won, -1 if no one won*/
	public int getWinner(){
		if(score[0] >= 10)
			return 0;
		if(score[1] >= 10)
			return 1;
		return -1;
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
		System.out.println(cardBeingPlayed);
		Card playedCard = players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(cardBeingPlayed);
		System.out.println(playedCard.toString());
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
	
	public void makeAIPlayPreRound() 
	{
		if (((AIPlayer) players.get(currentRound.currentTrick.currentPlayer)).pickUp(currentRound.turnedUpCard))
		{
			currentRound.preRoundCall();
		}
		else
		{
			currentRound.preRoundPass();
		}
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

	public ArrayList<ArrayList<Card>> getAllHands() {
		ArrayList<ArrayList<Card>> out = new ArrayList<ArrayList<Card>>();
		for(Player p : players)
			out.add(p.hand);
		return out;
	}
}
