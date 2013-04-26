import static org.junit.Assert.*;

import org.junit.Test;


public class TrickTest {

	@Test
	public void testTrick() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		assertNotNull(t);
	}

	@Test
	public void testPlayCardForCurrentPlayerFirstCard() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		Card nineOfHearts = new Card(Card.SUIT.HEARTS, 9);
		t.playCardForCurrentPlayer(nineOfHearts);
		assertEquals(t.leadingSuit,Card.SUIT.HEARTS);
		assertEquals(t.currentWinner, 0);
		assertEquals(t.currentWinningCard, nineOfHearts);
		assertEquals(t.cardsPlayed.get(0), nineOfHearts);
	}
	
	@Test
	public void testPlayCardForCurrentPlayerSecondCard() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		Card nineOfHearts = new Card(Card.SUIT.HEARTS, 9);
		Card tenOfHearts = new Card(Card.SUIT.HEARTS, 10);
		t.playCardForCurrentPlayer(nineOfHearts);
		t.playCardForCurrentPlayer(tenOfHearts);
		assertEquals(t.leadingSuit,Card.SUIT.HEARTS);
		assertEquals(t.currentWinner, 1);
		assertEquals(t.currentWinningCard, tenOfHearts);
		assertEquals(t.cardsPlayed.get(0), nineOfHearts);
		assertEquals(t.cardsPlayed.get(1), tenOfHearts);
	}

	@Test
	public void testIsStarted() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		assertFalse(t.isStarted());
		t.playCardForCurrentPlayer(new Card(Card.SUIT.HEARTS, 10));
		assertTrue(t.isStarted());
	}

	@Test
	public void testIsOver() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		assertFalse(t.isOver());
		t.playCardForCurrentPlayer(new Card(Card.SUIT.HEARTS, 10));
		assertFalse(t.isOver());
		t.playCardForCurrentPlayer(new Card(Card.SUIT.HEARTS, 10));
		assertFalse(t.isOver());
		t.playCardForCurrentPlayer(new Card(Card.SUIT.HEARTS, 10));
		assertFalse(t.isOver());
		t.playCardForCurrentPlayer(new Card(Card.SUIT.HEARTS, 10));
		assertTrue(t.isOver());
	}

	@Test
	public void testGetNextTrick() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		Card nineOfHearts = new Card(Card.SUIT.HEARTS, 9);
		Card tenOfHearts = new Card(Card.SUIT.HEARTS, 10);
		t.playCardForCurrentPlayer(nineOfHearts);
		t.playCardForCurrentPlayer(tenOfHearts);
		
		Trick t2 = t.getNextTrick();
		assertEquals(1,t2.leadingPlayer);
		assertEquals(Card.SUIT.CLUBS,t2.trump);
	}

	@Test
	public void testTrickHistory() {
		Trick t = new Trick(0, Card.SUIT.CLUBS);
		Card nineOfHearts = new Card(Card.SUIT.HEARTS, 9);
		t.playCardForCurrentPlayer(nineOfHearts);
		assertEquals(t.leadingSuit,Card.SUIT.HEARTS);
		assertEquals(t.currentWinner, 0);
		assertEquals(t.currentWinningCard, nineOfHearts);
		assertEquals(t.cardsPlayed.get(0), nineOfHearts);
		assertEquals("You: Nine of Hearts\n", t.stringOfTrickPlayed());
	}
	
}
