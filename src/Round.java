import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Round {
	public LinkedList<Card> deck = new LinkedList<Card>();
	public ArrayList<Card> allCards = new ArrayList<Card>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public Trick currentTrick;
	public Card.SUIT trump = Card.SUIT.SPADES;
	public int[] trickCount = new int[2];
	public ArrayList<Trick> trickHistory = new ArrayList<Trick>();
	public int leadPlayer;
	
	public Round(LinkedList<Card> deckIn, ArrayList<Card> allCardsIn, ArrayList<Player> playersIn, int dealer){
		deck = deckIn;
		allCards = allCardsIn;
		players = playersIn;
		shuffle();
		deal();
		trump = getTrump(dealer);
		currentTrick = new Trick(0,trump);
		trickCount[0] = 0;
		trickCount[1] = 0;
	}
	
	/*To be edited later*/
	public Card.SUIT getTrump(int dealer){
		leadPlayer = (dealer+1)%4;
		return Card.SUIT.SPADES;
	}
	
	public void playNext(String s){
		Card c = null;
		if (s == "AI")
			c=makeAIPlay();
		else
			c=humanPlayCard(s);
		currentTrick.playCardForCurrentPlayer(c);
		if(currentTrick.isOver()){
				trickCount[currentTrick.currentWinner%2]++;
				trickHistory.add(currentTrick);
				currentTrick = currentTrick.getNextTrick();
		}
	}
	
	public boolean isOver(){
		return (trickHistory.size() >= 5);
	}
	
	/*
	 * team that called trump get 3 or 4 tricks, get 1 point
	 * team that called trump get all 5, get 2 points
	 * team that didn't call get 3 or more, get 2 points
	 */
	public int[] scoreRound(){
		int[] ans = new int[2];
		if(trickCount[leadPlayer] == 3 ||trickCount[leadPlayer] == 4 )
			ans[leadPlayer] = 1;
		if(trickCount[leadPlayer] == 5)
			ans[leadPlayer] = 2;
		if(trickCount[(leadPlayer+1)%2] >= 3)
			ans[(leadPlayer+1)%2] = 2;
		return ans;
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
	
	public Card humanPlayCard(String cardBeingPlayed){
		Card playedCard = players.get(currentTrick.currentPlayer).removeCardFromHand(cardBeingPlayed);
		playCard(playedCard);
		return playedCard;
	}
	
	public boolean isCurrentPlayerAI()
	{
		return (players.get(currentTrick.currentPlayer) instanceof AIPlayer); 
	}

	public Card makeAIPlay() {
		AIPlayer aiPlayer = ((AIPlayer) players.get(currentTrick.currentPlayer));
		Card cardToPlay = aiPlayer.getCardToPlay(currentTrick);
		playCard(cardToPlay);
		return cardToPlay;
	}
	
	private void playCard(Card c)
	{
		if (c == null)
			return;
		currentTrick.playCardForCurrentPlayer(c);
		if (currentTrick.isOver())
		{
			trickCount[currentTrick.currentWinner % 2] ++;
			trickHistory.add(currentTrick);
			currentTrick = currentTrick.getNextTrick();
		}
	}
}
