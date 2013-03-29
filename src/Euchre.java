import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Euchre {
	public LinkedList<Card> deck = new LinkedList<Card>();
	public ArrayList<Card> allCards = new ArrayList<Card>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public Trick currentTrick;
	public Card.SUIT trump = Card.SUIT.SPADES;
	public int[] trickCount = new int[2];
	
	public Euchre(){
		setUpAllCards();
		setUpPlayers();
		shuffle();
		deal();
		currentTrick = new Trick(0,trump);
		trickCount[0] = 0;
		trickCount[1] = 0;
	}
	
	/*Shuffles the deck, refilling it with all 24 cards in a random order*/
	public void shuffle(){
		Random r = new Random();
		boolean[] used = new boolean[24];
		for (int x=0; x<24; x++){
			used[x] = false;
		}
		for (int x=0; x<24; x++){
			int y = r.nextInt(24);
			while (used[y]){
				y = r.nextInt(24);
			}
			deck.push(allCards.get(y));
			used[y] = true;
		}
	}
	
	/*Removes the card off the top of deck and returns it*/
	public Card draw(){
		if(!deck.isEmpty()) 
			return deck.pop();
		return new Card(Card.SUIT.SPADES, -1);
	}
	
	/*Deals 5 cards off the top of the deck to each player*/
	public void deal(){
		for (int x=0; x<5; x++){
			for(int y=0; y<4; y++){
				players.get(y).hand.add(draw());
			}
		}
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
		Card playedCard = players.get(currentTrick.currentPlayer).removeCardFromHand(cardBeingPlayed);
		playCard(playedCard);
	}
	
	public boolean isCurrentPlayerAI()
	{
		return (players.get(currentTrick.currentPlayer) instanceof AIPlayer); 
	}

	public void makeAIPlay() {
		AIPlayer aiPlayer = ((AIPlayer) players.get(currentTrick.currentPlayer));
		Card cardToPlay = aiPlayer.getCardToPlay(currentTrick);
		playCard(cardToPlay);
	}
	
	private void playCard(Card c)
	{
		if (c == null)
			return;
		currentTrick.playCardForCurrentPlayer(c);
		if (currentTrick.isOver())
		{
			trickCount[currentTrick.currentWinner % 2] ++;
			currentTrick = currentTrick.getNextTrick();
		}
	}
}
