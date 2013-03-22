import static org.junit.Assert.*;

import org.junit.Test;


public class AIPlayerTest {

	@Test
	public void testAIPlayer() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
	}

	//currently just gets highest card
	@Test
	public void testGetCardToPlay() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertEquals(new Card(Card.SUIT.CLUBS, 14), aip.getCardToPlay());
	}

	@Test
	public void testGetCardToPlaySUITIntHasOneOfSuit() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertEquals(new Card(Card.SUIT.CLUBS, 14), aip.getCardToPlay(Card.SUIT.CLUBS,0));
	}
	
	@Test
	public void testGetCardToPlaySUITIntDoesntHaveOneOfSuit() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.getCardToPlay(Card.SUIT.HEARTS,0));
	}

}
