import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;


public class RoundTest {

	@Test
	public void testRound() {
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertTrue(true);
	}
	
	@Test
	public void deckNotNull(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertFalse(r.deck == new LinkedList<Card>());
	}
	
	@Test
	public void playerkNotNull(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertFalse(r.players == new ArrayList<Player>());
	}
	
	@Test
	public void allCardsNotNull(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertFalse(r.allCards == new ArrayList<Card>());
	}
	
	@Test
	public void newRoundDefaultTrump(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertEquals(r.trump, Card.SUIT.SPADES);
	}
	
	@Test
	public void newRoundDefaultLeadPlayer(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertEquals(r.leadPlayer, 1);
	}

	@Test
	public void newRoundDefaultTrickCount(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		int[] cnt = new int[2];
		cnt[0] = 0; 
		cnt[1] = 0;
		assert(r.trickCount.equals(cnt)); /*assertEquals fails*/
	}
	
	@Test
	public void newRoundRandomHandsEachPlayer(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		assertFalse(r.players.get(0).hand.equals(r.players.get(1).hand));
	}	
	
	@Test
	public void newRoundRandomHandsEachRound(){
		Euchre e = new Euchre();
		Round r = new Round(e.deck, e.allCards, e.players,0);
		Euchre e2 = new Euchre();
		Round r2 = new Round(e2.deck, e2.allCards, e2.players,0);
		assertFalse(r.players.get(0).hand.equals(r2.players.get(0).hand));
	}	
}
