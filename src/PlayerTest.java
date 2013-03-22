import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTest {

	@Test
	public void testPlayer() {
		String name = "TestPlayer";
		Player p = new Player("TestPlayer");
		assertEquals(name,p.name);
	}

	@Test
	public void testGetCardsInHandOfSuit() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.CLUBS, 9));
		p.hand.add(new Card(Card.SUIT.HEARTS, 9));
		p.hand.add(new Card(Card.SUIT.CLUBS, 9));
		p.hand.add(new Card(Card.SUIT.HEARTS, 9));
		p.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertEquals(3,p.getCardsInHandOfSuit(Card.SUIT.CLUBS).size());
		assertEquals(0,p.getCardsInHandOfSuit(Card.SUIT.DIAMONDS).size());
		assertEquals(2,p.getCardsInHandOfSuit(Card.SUIT.HEARTS).size());
		assertEquals(0,p.getCardsInHandOfSuit(Card.SUIT.SPADES).size());
	}

	@Test
	public void testGetHighestValueCardOfSuit() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.CLUBS, 10));
		p.hand.add(new Card(Card.SUIT.CLUBS, 11));
		p.hand.add(new Card(Card.SUIT.CLUBS, 12));
		p.hand.add(new Card(Card.SUIT.CLUBS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertEquals(new Card(Card.SUIT.CLUBS,14),p.getHighestValueCardOfSuit(Card.SUIT.CLUBS));
	}

	@Test
	public void testGetLowestValueCardOfSuit() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.CLUBS, 10));
		p.hand.add(new Card(Card.SUIT.CLUBS, 11));
		p.hand.add(new Card(Card.SUIT.CLUBS, 9));
		p.hand.add(new Card(Card.SUIT.CLUBS, 13));
		p.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertEquals(new Card(Card.SUIT.CLUBS,9),p.getLowestValueCardOfSuit(Card.SUIT.CLUBS));
	}

	@Test
	public void testGetHighestValueCard() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.HEARTS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.CLUBS, 9));
		p.hand.add(new Card(Card.SUIT.SPADES, 13));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		assertEquals(new Card(Card.SUIT.DIAMONDS,14),p.getHighestValueCard());
	}

	@Test
	public void testGetLowestValueCard() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.HEARTS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.CLUBS, 9));
		p.hand.add(new Card(Card.SUIT.SPADES, 13));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		assertEquals(new Card(Card.SUIT.CLUBS,9),p.getLowestValueCard());
	}

	@Test
	public void testHasCardOfSuit() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.HEARTS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.SPADES, 9));
		p.hand.add(new Card(Card.SUIT.SPADES, 13));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		assertEquals(true, p.hasCardOfSuit(Card.SUIT.HEARTS));
		assertEquals(true, p.hasCardOfSuit(Card.SUIT.DIAMONDS));
		assertEquals(true, p.hasCardOfSuit(Card.SUIT.SPADES));
		assertEquals(false, p.hasCardOfSuit(Card.SUIT.CLUBS));
	}

	@Test
	public void testRemoveCardFromHand() {
		Player p = new Player("TestPlayer");
		p.hand.add(new Card(Card.SUIT.HEARTS, 10));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		p.hand.add(new Card(Card.SUIT.SPADES, 9));
		p.hand.add(new Card(Card.SUIT.SPADES, 13));
		p.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		p.removeCardFromHand(p.hand.get(0));
		assertEquals(false, p.hasCardOfSuit(Card.SUIT.HEARTS));
		assertEquals(4,p.hand.size());
		p.removeCardFromHand(p.hand.get(0));
		assertEquals(true, p.hasCardOfSuit(Card.SUIT.DIAMONDS));
		assertEquals(3,p.hand.size());
		p.removeCardFromHand(p.hand.get(0));
		assertEquals(true, p.hasCardOfSuit(Card.SUIT.SPADES));
		assertEquals(2,p.hand.size());
		p.removeCardFromHand(p.hand.get(0));
		assertEquals(false, p.hasCardOfSuit(Card.SUIT.SPADES));
		assertEquals(1,p.hand.size());
		p.removeCardFromHand(p.hand.get(0));
		assertEquals(false, p.hasCardOfSuit(Card.SUIT.DIAMONDS));
		assertEquals(0,p.hand.size());
	}

}
