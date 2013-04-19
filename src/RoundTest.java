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
	public void newRoundDefaultLeadPlayer(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		assertEquals(r.dealer, 0);
	}

	@Test
	public void newRoundDefaultTrickCount(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		int[] target = r.trickCount;
		assertEquals(target[0],0);
		assertEquals(target[1],0);
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
		int[] target = r.scoreRound();
		assertEquals(target[0],0);
		assertEquals(target[1],0);
	}
	
	@Test
	public void isOverTest(){
		Euchre e = new Euchre();
		Round r = new Round( e.allCards, e.players,0);
		for(int x=0; x<5; x++){
			r.trickHistory.add(new Trick(0,Card.SUIT.SPADES));
		}
		assertTrue(r.isOver());
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
	public void testScoreRoundEuchre()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		r.callingTeam = 0;
		r.trickCount[0] = 2;
		r.trickCount[1] = 3;
		int[] target = r.scoreRound();
		assertEquals(0, target[0]);
		assertEquals(2, target[1]);
	}
	
	@Test
	public void testScoreRoundCallingTeamGetsThree()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		r.callingTeam = 0;
		r.trickCount[0] = 3;
		r.trickCount[1] = 2;
		int[] target = r.scoreRound();
		assertEquals(1, target[0]);
		assertEquals(0, target[1]);
	}
	
	@Test
	public void testScoreRoundCallingTeamGetsFour()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		r.callingTeam = 0;
		r.trickCount[0] = 4;
		r.trickCount[1] = 1;
		int[] target = r.scoreRound();
		assertEquals(1, target[0]);
		assertEquals(0, target[1]);
	}
	
	@Test
	public void testScoreRoundCallingTeamGetsFive()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		r.callingTeam = 0;
		r.trickCount[0] = 5;
		r.trickCount[1] = 0;
		int[] target = r.scoreRound();
		assertEquals(2, target[0]);
		assertEquals(0, target[1]);
	}
}
