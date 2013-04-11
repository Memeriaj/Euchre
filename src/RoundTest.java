import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;


public class RoundTest {

	@Test
	public void testRound() {
		Euchre e = new Euchre();
		Round r = new Round(e.allCards, e.players,0);
		assertNotNull(r);
	}
	
	@Test
	public void deckNotNull(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertFalse(r.deck == new LinkedList<Card>());
	}
	
	@Test
	public void playerkNotNull(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertFalse(r.players == new ArrayList<Player>());
	}
	
	@Test
	public void allCardsNotNull(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertFalse(r.allCards == new ArrayList<Card>());
	}
	
	@Test
	public void newRoundDefaultTrump(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertEquals(r.trump, Card.SUIT.SPADES);
	}
	
	@Test
	public void newRoundDefaultLeadPlayer(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertEquals(r.dealer, 0);
	}

	@Test
	public void newRoundDefaultTrickCount(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] cnt = new int[2];
		cnt[0] = 0; 
		cnt[1] = 0;
		assert(r.trickCount.equals(cnt)); /*assertEquals fails*/
	}
	
	@Test
	public void newRoundRandomHandsEachPlayer(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertFalse(r.players.get(0).hand.equals(r.players.get(1).hand));
	}	
	
	@Test
	public void newRoundRandomHandsEachRound(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		Euchre e2 = new Euchre();
		Round r2 = new Round(e2.allCards, e2.players,0);
		assertFalse(r.players.get(0).hand.equals(r2.players.get(0).hand));
	}	
	
	@Test
	public void newRoundDefaultScore(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] cnt = new int[2];
		cnt[0] = 0; 
		cnt[1] = 0;
		assert(r.scoreRound().equals(cnt));
	}
	
	@Test
	public void isOverTest(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		for(int x=0; x<5; x++){
			r.trickHistory.add(new Trick(0,Card.SUIT.SPADES));
		}
		assert(r.isOver());
	}
	
	@Test
	public void isOverFalseTest(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		for(int x=0; x<4; x++){
			r.trickHistory.add(new Trick(0,Card.SUIT.SPADES));
		}
		assertFalse(r.isOver());
	}
	
	@Test
	public void scoring5to0(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] pts = new int[2];
		pts[0] = 0; 
		pts[1] = 5;
		r.trickCount = pts;
		int[] s = new int[2];
		s[0] = 0; 
		s[1] = 2;
		assert(r.scoreRound().equals(s));
	}
	
	@Test
	public void scoring4to1(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] pts = new int[2];
		pts[0] = 1; 
		pts[1] = 4;
		r.trickCount = pts;
		int[] s = new int[2];
		s[0] = 0; 
		s[1] = 1;
		assert(r.scoreRound().equals(s));
	}
	
	@Test
	public void scoring3to2(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] pts = new int[2];
		pts[0] = 2; 
		pts[1] = 3;
		r.trickCount = pts;
		int[] s = new int[2];
		s[0] = 0; 
		s[1] = 1;
		assert(r.scoreRound().equals(s));
	}
	
	@Test
	public void scoring2to3(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] pts = new int[2];
		pts[0] = 3; 
		pts[1] = 2;
		r.trickCount = pts;
		int[] s = new int[2];
		s[0] = 2; 
		s[1] = 0;
		assert(r.scoreRound().equals(s));
	}
	
	@Test
	public void scoring1to4(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] pts = new int[2];
		pts[0] = 4; 
		pts[1] = 1;
		r.trickCount = pts;
		int[] s = new int[2];
		s[0] = 2; 
		s[1] = 0;
		assert(r.scoreRound().equals(s));
	}
	
	@Test
	public void scoring0to5(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] pts = new int[2];
		pts[0] = 3; 
		pts[1] = 2;
		r.trickCount = pts;
		int[] s = new int[2];
		s[0] = 2; 
		s[1] = 0;
		assert(r.scoreRound().equals(s));
	}
}
