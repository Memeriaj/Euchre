import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class EuchreTest {
	
	@Test
	public void initialize(){
		Euchre e = new Euchre();
		assertNotNull(e);
	}
	
	@Test
	public void playerkNotNull(){
		Euchre e = new Euchre();
		assertFalse(e.players == new ArrayList<Player>());
	}
	
	@Test
	public void allCardsNotNull(){
		Euchre e = new Euchre();
		assertFalse(e.allCards == new ArrayList<Card>());
	}
	
	
	
	@Test
	public void getAllHands_oneCardEach_fourListswithOneCard(){
		Euchre e = new Euchre();
		Card testCard = new Card(Card.SUIT.CLUBS, 9);
		ArrayList<ArrayList<Card>> expected = new ArrayList<ArrayList<Card>>();
		for(Player p : e.players){
			p.hand = new ArrayList<Card>();
			p.hand.add(testCard);
			
			ArrayList<Card> subExpected = new ArrayList<Card>();
			subExpected.add(testCard);
			expected.add(subExpected);
		}
		assertEquals(expected, e.getAllHands());
	}
	
	@Test
	public void testRoundOverHuman(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=10;
		s[1]=2;
		e.score = s;
		assertTrue(e.isOver());
	}
	
	@Test
	public void testRoundOverAI(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=10;
		e.score = s;
		assertTrue(e.isOver());
	}
	
	@Test
	public void testRoundOverFalse(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=7;
		e.score = s;
		assertFalse(e.isOver());
	}
	
	@Test
	public void testWinnerHuman(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=10;
		s[1]=2;
		e.score = s;
		assertEquals(e.getWinner(),0);
	}
	
	@Test
	public void testWinnerAI(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=10;
		e.score = s;
		assertEquals(e.getWinner(),1);
	}
	
	@Test
	public void testWinnerFalse(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=7;
		e.score = s;
		assertEquals(e.getWinner(),-1);
	}
	
	@Test
	public void testDiscardDealer(){
		Euchre e = new Euchre();
		e.currentRound.currentTrick=new Trick(0,Card.SUIT.CLUBS);
		e.currentRound.outPlayer =-1;
		e.currentRound.turnedUpCard = new Card(Card.SUIT.SPADES, 14);
		e.currentRound.dealer = 0;
		Player p = e.players.get(0);
		e.players.add(p);
		p.hand.clear();
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		e.dealerDiscardForRoundStart(new Card(Card.SUIT.SPADES, 14).toString());
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
		assertFalse(p.hand.contains(new Card(Card.SUIT.SPADES, 14)));
		
	}
	
	@Test
	public void testDiscardDealer2(){
		Euchre e = new Euchre();
		e.currentRound.currentTrick=new Trick(0,Card.SUIT.CLUBS);
		e.currentRound.outPlayer =-1;
		e.currentRound.turnedUpCard = new Card(Card.SUIT.SPADES, 14);
		e.currentRound.dealer = 0;
		Player p = e.players.get(0);
		e.players.add(p);
		p.hand.clear();
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		e.dealerDiscardForRoundStart(new Card(Card.SUIT.CLUBS, 14).toString());
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertFalse(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.SPADES, 14)));
	}
	
	@Test
	public void testDiscardDealerOutplayer(){
		Euchre e = new Euchre();
		e.currentRound.currentTrick=new Trick(0,Card.SUIT.CLUBS);
		e.currentRound.turnedUpCard = new Card(Card.SUIT.SPADES, 14);
		e.currentRound.dealer = 0;
		e.currentRound.outPlayer = 1;
		e.currentRound.currentTrick.leadingPlayer = 1;
		Player p = e.players.get(0);
		e.players.add(p);
		p.hand.clear();
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		e.dealerDiscardForRoundStart(new Card(Card.SUIT.CLUBS, 14).toString());
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertFalse(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.SPADES, 14)));
		assertEquals(e.currentRound.currentTrick.leadingPlayer,2);
	}
	
	@Test
	public void testHumanPlayCard(){
		Euchre e = new Euchre();
		e.currentRound.currentTrick=new Trick(0,Card.SUIT.CLUBS);
		Player p = e.players.get(0);
		e.currentRound.currentTrick.currentPlayer =0;
		Player ai1 = e.players.get(1);
		ai1.hand.clear();
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertEquals(ai1.hand.size(), 5);
		
		Player ai2 = e.players.get(2);
		ai2.hand.clear();
		ai2.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai2.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai2.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai2.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai2.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertEquals(ai2.hand.size(), 5);
		
		Player ai3 = e.players.get(3);
		ai3.hand.clear();
		ai3.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai3.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai3.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai3.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai3.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertEquals(ai3.hand.size(), 5);
		
		p.hand.clear();
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
		assertEquals(p.hand.size(), 5);
		System.out.println(p.hand.toString());
		e.humanPlayCard(new Card(Card.SUIT.CLUBS, 14).toString());
		assertEquals(p.hand.size(), 4);
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertFalse(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
	}
	
	@Test
	public void testHumanPlayCardEndOfTrick(){
		Euchre e = new Euchre();
		e.currentRound.currentTrick=new Trick(0,Card.SUIT.CLUBS);
		e.currentRound.isInPreGameState = false;
		e.currentRound.trump = Card.SUIT.CLUBS;
		e.currentRound.currentTrick.leadingSuit = Card.SUIT.CLUBS;
		Player p = e.players.get(0);
		e.currentRound.currentTrick.leadingPlayer =1;
		e.currentRound.currentTrick.currentPlayer =1;
		e.currentRound.currentTrick.currentWinningCard = new Card(Card.SUIT.CLUBS, 1);
		Player ai1 = e.players.get(1);
		ai1.hand.clear();
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertEquals(ai1.hand.size(), 5);
		
		Player ai2 = e.players.get(2);
		ai2.hand.clear();
		ai2.hand.add(new Card(Card.SUIT.HEARTS, 10));
		ai2.hand.add(new Card(Card.SUIT.HEARTS, 10));
		ai2.hand.add(new Card(Card.SUIT.HEARTS, 10));
		ai2.hand.add(new Card(Card.SUIT.HEARTS, 10));
		ai2.hand.add(new Card(Card.SUIT.HEARTS, 10));
		assertEquals(ai2.hand.size(), 5);
		
		Player ai3 = e.players.get(3);
		ai3.hand.clear();
		ai3.hand.add(new Card(Card.SUIT.HEARTS, 12));
		ai3.hand.add(new Card(Card.SUIT.HEARTS, 12));
		ai3.hand.add(new Card(Card.SUIT.HEARTS, 12));
		ai3.hand.add(new Card(Card.SUIT.HEARTS, 12));
		ai3.hand.add(new Card(Card.SUIT.HEARTS, 12));
		assertEquals(ai3.hand.size(), 5);
		
		p.hand.clear();
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
		assertEquals(p.hand.size(), 5);
		System.out.println(p.hand.toString());
		assertEquals(e.currentRound.trickCount[0],0);
		assertEquals(e.currentRound.trickCount[1],0);
		e.makeGameReadyForHuman();
		e.humanPlayCard(new Card(Card.SUIT.CLUBS, 14).toString());
		assertEquals(e.currentRound.trickCount[0],1);
		assertEquals(e.currentRound.trickCount[1],0);
		assertEquals(p.hand.size(), 4);
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 10)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 11)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 12)));
		assertTrue(p.hand.contains(new Card(Card.SUIT.DIAMONDS, 13)));
		assertFalse(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
	}
	
	
	@Test
	public void testHumanPlayCardEndOfRound(){
		Euchre e = new Euchre();
		e.currentRound.currentTrick=new Trick(0,Card.SUIT.CLUBS);
		e.currentRound.isInPreGameState = false;
		e.currentRound.trump = Card.SUIT.CLUBS;
		e.currentRound.currentTrick.leadingSuit = Card.SUIT.CLUBS;
		Player p = e.players.get(0);
		e.currentRound.currentTrick.leadingPlayer =1;
		e.currentRound.currentTrick.currentPlayer =1;
		e.currentRound.currentTrick.currentWinningCard = new Card(Card.SUIT.CLUBS, 1);
		Player ai1 = e.players.get(1);
		ai1.hand.clear();
		ai1.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertEquals(ai1.hand.size(), 1);
		
		Player ai2 = e.players.get(2);
		ai2.hand.clear();
		ai2.hand.add(new Card(Card.SUIT.HEARTS, 10));
		assertEquals(ai2.hand.size(), 1);
		
		Player ai3 = e.players.get(3);
		ai3.hand.clear();
		ai3.hand.add(new Card(Card.SUIT.HEARTS, 12));
		assertEquals(ai3.hand.size(), 1);
		
		p.hand.clear();
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertTrue(p.hand.contains(new Card(Card.SUIT.CLUBS, 14)));
		assertEquals(p.hand.size(), 1);
		System.out.println(p.hand.toString());
		e.currentRound.trickCount[0] = 4;
		e.currentRound.trickCount[1] = 0;
		for(int x=0; x<4; x++)
			e.currentRound.trickHistory.add(new Trick(0,Card.SUIT.SPADES));
		e.makeGameReadyForHuman();
		e.humanPlayCard(new Card(Card.SUIT.CLUBS, 14).toString());
		assertEquals(e.currentRound.trickCount[0],0);
		assertEquals(e.currentRound.trickCount[1],0);
		assertEquals(p.hand.size(), 5);
	}
}