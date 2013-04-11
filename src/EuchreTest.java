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
	public void playCard_oneCard_emptyHand(){
		Euchre euchreTest = new Euchre();
		euchreTest.players.get(0).hand = new ArrayList<Card>();
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 9));
		while (euchreTest.isCurrentPlayerAI())
			euchreTest.makeAIPlay();
		euchreTest.humanPlayCard("Nine of Clubs");
		assertTrue(euchreTest.players.get(0).hand.isEmpty());
		assertEquals(1, euchreTest.currentRound.currentTrick.currentPlayer);
	}
	
	@Test
	public void playCard_fullHand_fourLeft(){
		Euchre euchreTest = new Euchre();
		euchreTest.players.get(0).hand = new ArrayList<Card>();
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 9));
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 10));
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 11));
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 12));
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 13));
		euchreTest.humanPlayCard("Nine of Clubs");
		assertEquals(4, euchreTest.players.get(0).hand.size());
		assertEquals(1, euchreTest.currentRound.currentTrick.currentPlayer);
	}
	
	@Test
	public void testIsCurrentPlayerAI()
	{
		Euchre e = new Euchre();
		assertFalse(e.isCurrentPlayerAI());
		e.humanPlayCard(e.players.get(0).hand.get(0).toString());
		assertTrue(e.isCurrentPlayerAI());
		e.makeAIPlay();
		assertTrue(e.isCurrentPlayerAI());
		e.makeAIPlay();
		assertTrue(e.isCurrentPlayerAI());
	}
	
	@Test
	public void testEndOfTrick()
	{
		Euchre e = new Euchre();
		Trick oldTrick = e.currentRound.currentTrick;
		e.humanPlayCard(e.players.get(0).hand.get(0).toString());
		e.makeAIPlay();
		e.makeAIPlay();
		e.makeAIPlay();
		assertTrue(oldTrick.isOver());
		assertTrue(e.currentRound.currentTrick != oldTrick);
		assertTrue(e.currentRound.trickCount[0] > 0 || e.currentRound.trickCount[1] > 0);
		assertTrue(e.currentRound.trickHistory.size()>0);
	}
}
