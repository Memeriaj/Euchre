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

	@Test
	public void testPickUp() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 11)));
	}
	
	@Test
	public void testPickUp2() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 14)));
	}
	
	@Test
	public void testPickUp3() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testPickUp4() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testPickUp5() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.HEARTS, 11)));
	}
	
	@Test
	public void testPickUp6() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testPickUp7() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 11));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testPickUp8() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 9));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.DIAMONDS, 11)));
	}

	@Test
	public void testPickUp9() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertTrue(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testDiscardDecider() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));	
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 11)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.CLUBS, 11)));
	}
	
	@Test
	public void testDiscardDecider2() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 14)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.CLUBS, 14)));
	}
	
	@Test
	public void testDiscardDecider3() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.CLUBS, 9)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testDiscardDecider4() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 9)));
		assertEquals(new Card(Card.SUIT.HEARTS, 14), aip.discardDecider(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testDiscardDecider5() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.HEARTS, 11)));
		assertEquals(new Card(Card.SUIT.HEARTS, 11), aip.discardDecider(new Card(Card.SUIT.HEARTS, 11)));
	}
	
	@Test
	public void testDiscardDecider6() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
		assertEquals(new Card(Card.SUIT.HEARTS, 9), aip.discardDecider(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testDiscardDecider7() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 11));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
		assertEquals(new Card(Card.SUIT.SPADES, 12), aip.discardDecider(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testDiscardDecider8() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 9));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUp(new Card(Card.SUIT.DIAMONDS, 11)));
		assertEquals(new Card(Card.SUIT.DIAMONDS, 11), aip.discardDecider(new Card(Card.SUIT.DIAMONDS, 11)));
	}

	@Test
	public void testDiscardDecider9() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertTrue(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.HEARTS, 9)));
	}
		
	@Test
	public void testTrumpDeciderFullHandNoInvalid() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(Card.SUIT.CLUBS,aip.trumpDecider(Card.SUIT.HEARTS));
	}
	
	@Test
	public void testTrumpDeciderFullHandInvalid() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(Card.SUIT.SPADES,aip.trumpDecider(Card.SUIT.CLUBS));
	}
	
	@Test
	public void testTrumpDeciderMixedHandNoInvalid() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 12));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(Card.SUIT.CLUBS,aip.trumpDecider(Card.SUIT.SPADES));
	}
	
	@Test
	public void testTrumpDeciderMixedHandInvalid() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 12));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(Card.SUIT.HEARTS,aip.trumpDecider(Card.SUIT.CLUBS));
	}
	
	@Test
	public void testCallingHandValueFullHand() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(41,aip.callingHandValue(aip.hand,Card.SUIT.CLUBS));
	}
	
	@Test
	public void testCallingHandValueFullHandWrongSuit() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(8,aip.callingHandValue(aip.hand,Card.SUIT.HEARTS));
	}
	
	@Test
	public void testCallingHandValueMixedLow() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 9));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(18,aip.callingHandValue(aip.hand,Card.SUIT.HEARTS));
	}
	
	@Test
	public void testCallingHandValueMixedHigh() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(23,aip.callingHandValue(aip.hand,Card.SUIT.HEARTS));
	}	
	
	@Test
	public void testCallDeciderFullHand() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(41,aip.callingHandValue(aip.hand,Card.SUIT.CLUBS));
		assertEquals(true,aip.callDecider(Card.SUIT.HEARTS));
	}
	
	@Test
	public void testCallDeciderFullHandWrongSuit() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(false,aip.callDecider(Card.SUIT.CLUBS));
	}
	
	@Test
	public void testCallDeciderMixedLow() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 9));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(18,aip.callingHandValue(aip.hand,Card.SUIT.HEARTS));
		assertEquals(false,aip.callDecider(Card.SUIT.CLUBS));
	}
	
	@Test
	public void testCallDeciderMixedHigh() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(23,aip.callingHandValue(aip.hand,Card.SUIT.HEARTS));
		assertEquals(true,aip.callDecider(Card.SUIT.CLUBS));
	}
}
