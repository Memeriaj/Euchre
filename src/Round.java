import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Round {
	public LinkedList<Card> deck = new LinkedList<Card>();
	public ArrayList<Card> allCards;
	public ArrayList<Player> players;
	public Trick currentTrick;
	public Card.SUIT trump = Card.SUIT.SPADES;
	public int[] trickCount = new int[2];
	public ArrayList<Trick> trickHistory = new ArrayList<Trick>();
	public int dealer;
	public int callingTeam;
	
	public Round(ArrayList<Card> allCardsIn, ArrayList<Player> playersIn, int dealerIn){
		allCards = allCardsIn;
		players = playersIn;
		shuffle();
		deal();
		dealer = dealerIn;
		currentTrick = new Trick(dealer,trump);
		trickCount[0] = 0;
		trickCount[1] = 0;
		
		callingTeam = dealer % 2; // to be figured out in a pre-round
	}
	
	
	public boolean isOver(){
		return trickHistory.size() == 5;
	}
	
	/*
	 * team that called trump get 3 or 4 tricks, get 1 point
	 * team that called trump get all 5, get 2 points
	 * team that didn't call get 3 or more, get 2 points
	 */
	public int[] scoreRound(){
		int[] ans = new int[2];
		
		if(trickCount[callingTeam] < 3)
			ans[(callingTeam+1)%2] = 2;
		else if(trickCount[callingTeam] < 5 )
			ans[callingTeam] = 1;
		else //trickCount[dealer] == 5)
			ans[callingTeam] = 2;
		
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
		return deck.pop();
	}
	
	/*Deals 5 cards off the top of the deck to each player*/
	public void deal(){
		for (int x=0; x<5; x++){
			for(int y=0; y<4; y++){
				players.get(y).hand.add(draw());
			}
		}
	}
	
	
	public void playCard(Card c)
	{
		currentTrick.playCardForCurrentPlayer(c);
		if (currentTrick.isOver())
		{
			trickCount[currentTrick.currentWinner % 2] ++;
			trickHistory.add(currentTrick);
			currentTrick = currentTrick.getNextTrick();
		}
	}

	public Round getNextRound() {
		return new Round(allCards, players, (dealer+1) % 4);
	}
}
