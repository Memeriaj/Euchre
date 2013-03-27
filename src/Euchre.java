import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Euchre {
	public LinkedList<Card> deck = new LinkedList<Card>();
	public ArrayList<Card> allCards = new ArrayList<Card>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public int currPlayer = -1; 
	public ArrayList<Card> currTrick = new ArrayList<Card>(4);
	public Card.SUIT trump = Card.SUIT.SPADES;
	public int[] trickCount = new int[2];
	
	public Euchre(){
		setUpAllCards();
		setUpPlayers();
		shuffle();
		deal();
		currPlayer = 1;
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
		players.add(new Player("AI 1"));
		players.add(new Player("AI 2"));
		players.add(new Player("AI 3"));
	}
	
	/*returns the player index of the player who won the trick and update the trick count*/
	public int scoreTrick(){
		int winner = 0;
		int high = currTrick.get(0).cardValue(trump);
		currTrick.set(0, null);
		for(int x=1; x<4; x++){
			if(currTrick.get(x).cardValue(trump) > high){
				winner = x;
				high = currTrick.get(x).cardValue(trump);
			}
			currTrick.set(x, null);
		}
		trickCount[winner % 2] ++;
		return winner;
	}
	
	public void playCard(String cardBeingPlayed){
		players.get(0).removeCardFromHand(cardBeingPlayed);
		currPlayer = 1;
	}

	public void makeAIPlay() {
		// TODO Auto-generated method stub
		currTrick = new ArrayList<Card>();
		currTrick.add(null);
		currTrick.add(new Card(Card.SUIT.CLUBS, 9));
		currTrick.add(null);
		currTrick.add(new Card(Card.SUIT.CLUBS, 13));
	}
}
