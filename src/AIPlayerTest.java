import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class AIPlayerTest {
	
	Trick leadingSuitClubsTrick;
	Trick leadingSuitHeartsTrick;
	
	@Before
	public void oneTimeSetUp()
	{
		leadingSuitClubsTrick = new Trick(0,Card.SUIT.CLUBS);
		leadingSuitClubsTrick.playCardForCurrentPlayer(new Card(Card.SUIT.CLUBS,9));
		
		leadingSuitHeartsTrick = new Trick(0,Card.SUIT.CLUBS);
		leadingSuitHeartsTrick.playCardForCurrentPlayer(new Card(Card.SUIT.HEARTS,9));
	}

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
		
		assertEquals(new Card(Card.SUIT.CLUBS, 14), aip.getCardToPlay(new Trick(0,Card.SUIT.CLUBS)));
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
		
		assertEquals(new Card(Card.SUIT.CLUBS, 14), aip.getCardToPlay(leadingSuitClubsTrick));
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
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.getCardToPlay(leadingSuitHeartsTrick));
	}

}
