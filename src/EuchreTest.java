import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

public class EuchreTest {
	
	@Test
	public void initialize(){
		Euchre e = new Euchre();
		assert(true);
	}
	
	@Test
	public void deckNotNull(){
		Euchre e = new Euchre();
		assertFalse(e.deck == new LinkedList<Card>());
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
	public void deckRandom(){
		Euchre e = new Euchre();
		for(int x=0; x<5; x++)
			System.out.println(e.draw().toString());
		assert(true);
	}
	
	@Test
	public void playCard_oneCard_emptyHand(){
		Euchre euchreTest = new Euchre();
		euchreTest.players.get(0).hand = new ArrayList<Card>();
		euchreTest.players.get(0).hand.add(new Card(Card.SUIT.CLUBS, 9));
		euchreTest.humanPlayCard("Nine of Clubs");
		assertTrue(euchreTest.players.get(0).hand.isEmpty());
		assertEquals(1, euchreTest.currentTrick.currentPlayer);
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
		assertEquals(1, euchreTest.currentTrick.currentPlayer);
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
		e.humanPlayCard(e.players.get(0).hand.get(0).toString());
		e.makeAIPlay();
		e.makeAIPlay();
		e.makeAIPlay();
		assertTrue(e.trickCount[0] > 0 || e.trickCount[1] > 1);
		assertTrue(e.trickHistory.size()>0);
	}
}
