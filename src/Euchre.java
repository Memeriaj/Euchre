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
		makeGameReadyForHuman();
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
	
	public void makeGameReadyForHuman()
	{
		while(isCurrentPlayerAI() && currentRound.isInPreGameState)
			makeAIPlayPreRound();
		while(isCurrentPlayerAI() && !currentRound.isInPreGameState)
			makeAIPlay();
		
		// there is a possible case that AI finished round and need to do pregame now
		while(isCurrentPlayerAI() && currentRound.isInPreGameState)
			makeAIPlayPreRound();
		while(isCurrentPlayerAI() && !currentRound.isInPreGameState)
			makeAIPlay();
	}
	
	public void dealerDiscardForRoundStart(String c)
	{
		currentRound.dealerDiscardForRoundStart(c);
		makeGameReadyForHuman();
	}
	
	public void humanPlayCard(String cardBeingPlayed){
		System.out.println(cardBeingPlayed);
		Card playedCard = players.get(currentRound.currentTrick.currentPlayer).removeCardFromHand(cardBeingPlayed);
		System.out.println(playedCard.toString());
		playCard(playedCard);
		makeGameReadyForHuman();
	}
	
	public boolean[] getPlayableCardsForHuman()
	{
		return players.get(0).getPlayableCards(currentRound.currentTrick.leadingSuit, currentRound.trump);
	}
	
	public boolean isCurrentPlayerAI()
	{
		return currentRound.isCurrentPlayerAI(); 
	}
	
	public void humanPreRoundPass()
	{
		currentRound.preRoundPass();
		makeGameReadyForHuman();
	}
	
	public void humanPreRoundCall()
	{
		currentRound.preRoundCall();
	}

	public void makeAIPlay() {
		AIPlayer aiPlayer = ((AIPlayer) players.get(currentRound.currentTrick.currentPlayer));
		Card cardToPlay = aiPlayer.getCardToPlay(currentRound.currentTrick);
		playCard(cardToPlay);
	}
	
	public void makeAIPlayPreRound() 
	{
		int playerIndex = currentRound.currentTrick.currentPlayer;
		AIPlayer aip = ((AIPlayer) players.get(playerIndex));
		if (currentRound.isCardTurnedUp)
		{
			if (aip.pickUp(currentRound.turnedUpCard))
			{
				currentRound.preRoundCall();
				if (aip.goAloneDecider(currentRound.trump))
					goAlone(playerIndex);
			}
			else
			{
				currentRound.preRoundPass();
			}
		}
		else
		{
			if (currentRound.isStickTheDealer || aip.callDecider(currentRound.turnedUpCard.suit))
			{
				currentRound.preRoundCall(aip.trumpDecider(currentRound.turnedUpCard.suit));
				if (aip.goAloneDecider(currentRound.trump))
					goAlone(playerIndex);
			}
			else
			{
				currentRound.preRoundPass();
			}
		}
	}
	
	public void goAlone(int goAlonePlayer)
	{
		int outPlayer = (goAlonePlayer + 2) % 4;
		currentRound.outPlayer = outPlayer;
		Player p = players.get(outPlayer);
		p.hand.clear();
		for (int i = 0;i<5;i++)
			p.hand.add(new NullCard());
	}
	
	public void humanPreRoundCallSuit(String suit)
	{
		if (suit.equals("Diamonds"))
		{
			currentRound.preRoundCall(Card.SUIT.DIAMONDS);
		}
		else if (suit.equals("Clubs"))
		{
			currentRound.preRoundCall(Card.SUIT.CLUBS);
		}
		else if (suit.equals("Spades"))
		{
			currentRound.preRoundCall(Card.SUIT.SPADES);
		}
		else //if (suit.equals("Hearts"))
		{
			currentRound.preRoundCall(Card.SUIT.HEARTS);
		}
	}
	
	// used for both AI and humans
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
			System.out.println("NEW DEALER IS "+currentRound.dealer);
		}
	}

	public ArrayList<ArrayList<Card>> getAllHands() {
		ArrayList<ArrayList<Card>> out = new ArrayList<ArrayList<Card>>();
		for(Player p : players)
			out.add(p.hand);
		return out;
	}
}
