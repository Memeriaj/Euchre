import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Euchre {
	public LinkedList<Card> deck = new LinkedList<Card>();
	public ArrayList<Card> allCards = new ArrayList<Card>();
	public ArrayList<Player> players = new ArrayList<Player>();
//	public Trick currentRound.currentTrick;
//	public int[] trickCount = new int[2];
//	ArrayList<Trick> trickHistory = new ArrayList<Trick>();
	public Round currentRound;
	ArrayList<Round> roundHistory = new ArrayList<Round>();
	
	public Euchre(){
		setUpAllCards();
		setUpPlayers();
		currentRound = new Round(deck, allCards, players, 0);
	}
	
	/*Shuffles the deck, refilling it with all 24 cards in a random order*/
//	public void shuffle(){
//		Random r = new Random();
//		boolean[] used = new boolean[24];
//		for (int x=0; x<24; x++){
//			used[x] = false;
//		}
//		for (int x=0; x<24; x++){
//			int y = r.nextInt(24);
//			while (used[y]){
//				y = r.nextInt(24);
//			}
//			deck.push(allCards.get(y));
//			used[y] = true;
//		}
//	}
	
	/*Removes the card off the top of deck and returns it*/
//	public Card draw(){
//		if(!deck.isEmpty()) 
//			return deck.pop();
//		return new Card(Card.SUIT.SPADES, -1);
//	}
	
	/*Deals 5 cards off the top of the deck to each player*/
//	public void deal(){
//		for (int x=0; x<5; x++){
//			for(int y=0; y<4; y++){
//				players.get(y).hand.add(draw());
//			}
//		}
//	}
	
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
		if (c == null)
			return;
		currentRound.currentTrick.playCardForCurrentPlayer(c);
		if (currentRound.isOver())
		{
			// modify game score somehow
			
			roundHistory.add(currentRound);
			currentRound = currentRound.getNextRound();
		}
	}
}
