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
	
	@Test
	public void testPreRoundPassIncrementingCurrentPlayer()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		int startingPlayer = r.currentTrick.currentPlayer;
		r.preRoundPass();
		assertEquals(startingPlayer+1,r.currentTrick.currentPlayer);
	}
	
	@Test
	public void testPreRoundPassStartingRound()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		r.currentTrick.currentPlayer = r.dealer;
		r.preRoundPass();
		assertFalse(r.isInPreGameState);
	}
	
	@Test
	public void testPreRoundCall()
	{
		Euchre e = new Euchre();
		Round r = e.currentRound;
		Card.SUIT turnedUpSuit = r.turnedUpCard.suit;
		int callingPlayer = r.currentTrick.currentPlayer;
		r.preRoundCall();
		assertEquals(turnedUpSuit,r.trump);
		assertEquals(callingPlayer %2,r.callingTeam );
	}
	
	@Test
	public void testNextRound(){
		Euchre e = new Euchre();
		Round firstRound = e.currentRound;
		firstRound.dealer = 0;
		Round secondRound = firstRound.getNextRound();
		assertEquals(firstRound.allCards, secondRound.allCards);
		assertEquals(firstRound.players, secondRound.players);
		assertEquals(1, secondRound.dealer);
	}
	
	@Test
	public void testTrickHistory()
	{
		Euchre e = new Euchre();
		Round firstRound = e.currentRound;
		firstRound.dealer = 0;
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		Card nineOfHearts = new Card(Card.SUIT.HEARTS, 9);
		t.playCardForCurrentPlayer(nineOfHearts);
		Card tenOfHearts = new Card(Card.SUIT.HEARTS, 10);
		t.playCardForCurrentPlayer(tenOfHearts);
		Card queenOfHearts = new Card(Card.SUIT.HEARTS, 12);
		t.playCardForCurrentPlayer(queenOfHearts);
		Card kingOfHearts = new Card(Card.SUIT.HEARTS, 13);
		t.playCardForCurrentPlayer(kingOfHearts);
		firstRound.trickHistory.add(t);
		
		Trick t2 = new Trick(0, Card.SUIT.CLUBS);
		Card nineOfSpades = new Card(Card.SUIT.SPADES, 9);
		t2.playCardForCurrentPlayer(nineOfSpades);
		Card tenOfSpades = new Card(Card.SUIT.SPADES, 10);
		t2.playCardForCurrentPlayer(tenOfSpades);
		firstRound.currentTrick = t2;
		//System.out.println(t.stringOfTrickPlayed());
		//System.out.println(t2.stringOfTrickPlayed());
		System.out.println(firstRound.produceTrickHistoryText());
		assertEquals(firstRound.produceTrickHistoryText(), "Previous Trick played:\n" +
														   "You: Nine of Hearts\n" +
														   "Player 1: Ten of Hearts\n" +
														   "Player 2: Queen of Hearts\n" +
														   "Player 3: King of Hearts\n" +
														   "\n" +
														   "Current Trick:\n" +
														   "You: Nine of Spades\n" +
														   "Player 1: Ten of Spades\n");
	}
}
