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
		assertEquals(aip.hand.size(),5);
		assertEquals(new Card(Card.SUIT.CLUBS, 14), aip.getCardToPlay(new Trick(0,Card.SUIT.CLUBS)));
		assertEquals(aip.hand.size(),4);
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
	public void testPickUpAsDealer() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(aip.hand.size(),5);
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 11)));
		assertEquals(aip.hand.size(),5);
	}
	
	@Test
	public void testPickUpAsDealer2() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 14)));
	}
	
	@Test
	public void testPickUpAsDealer3() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testPickUpAsDealer4() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testPickUpAsDealer5() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 11)));
	}
	
	@Test
	public void testPickUpAsDealer6() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertFalse(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testPickUpAsDealer7() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 11));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testPickUpAsDealer8() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 9));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.DIAMONDS, 11)));
	}

	@Test
	public void testPickUpAsDealer9() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		aip.hand.add(new Card(Card.SUIT.SPADES, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 9)));
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
		assertEquals(aip.hand.size(),5);
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 11)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.CLUBS, 11)));
		assertEquals(aip.hand.size(),5);
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
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 14)));
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
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 9)));
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
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 9)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.CLUBS, 9)));
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
		assertFalse(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 11)));
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
		assertFalse(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 9)));
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
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 9)));
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
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.DIAMONDS, 11)));	
		assertEquals(new Card(Card.SUIT.HEARTS,9), aip.discardDecider(new Card(Card.SUIT.DIAMONDS, 11)));
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
		assertTrue(aip.pickUpAsDealer(new Card(Card.SUIT.HEARTS, 9)));
		assertEquals(new Card(Card.SUIT.CLUBS, 9), aip.discardDecider(new Card(Card.SUIT.HEARTS, 9)));
	}
		
	@Test
	public void testTrumpDeciderFullHandNoInvalid() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(aip.hand.size(),5);
		assertEquals(Card.SUIT.DIAMONDS,aip.trumpDecider(Card.SUIT.HEARTS));
		assertEquals(aip.hand.size(),5);
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
	public void testCallDeciderFullHand() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(aip.hand.size(),5);
		assertEquals(42,aip.handValue(aip.hand,Card.SUIT.DIAMONDS));
		assertEquals(true,aip.callDecider(Card.SUIT.HEARTS));
		assertEquals(aip.hand.size(),5);
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
		assertEquals(17,aip.handValue(aip.hand,Card.SUIT.HEARTS));
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
		assertEquals(23,aip.handValue(aip.hand,Card.SUIT.HEARTS));
		assertEquals(true,aip.callDecider(Card.SUIT.CLUBS));
	}
	
	@Test
	public void testPickUpFullHand() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		
		assertTrue(aip.pickUp(new Card(Card.SUIT.CLUBS, 9)));
	}
	
	@Test
	public void testPickUpFullHandWrongSuit() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		
		assertFalse(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testPickUpMixed() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(23,aip.handValue(aip.hand,Card.SUIT.HEARTS));
		assertTrue(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testPickUpMixed2() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(23,aip.handValue(aip.hand,Card.SUIT.HEARTS));
		assertFalse(aip.pickUp(new Card(Card.SUIT.HEARTS, 9)));
	}
	
	@Test
	public void testGoAloneDeciderFullHand() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 10));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 12));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(42,aip.handValue(aip.hand,Card.SUIT.DIAMONDS));
		assertEquals(true,aip.callDecider(Card.SUIT.HEARTS));
		assertEquals(true,aip.goAloneDecider(Card.SUIT.DIAMONDS));
	}
	
	@Test
	public void testGoAloneDeciderFullHandWrongSuit() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(false,aip.callDecider(Card.SUIT.CLUBS));
		assertEquals(false,aip.goAloneDecider(Card.SUIT.DIAMONDS));
	}
	
	@Test
	public void testGoAloneDeciderMixedLow() {
		String name = "TestAIPlayer";
		AIPlayer aip = new AIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 10));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 11));
		aip.hand.add(new Card(Card.SUIT.HEARTS, 9));
		aip.hand.add(new Card(Card.SUIT.DIAMONDS, 13));
		assertEquals(17,aip.handValue(aip.hand,Card.SUIT.HEARTS));
		assertEquals(false,aip.callDecider(Card.SUIT.CLUBS));
		assertEquals(false,aip.goAloneDecider(Card.SUIT.HEARTS));
	}
}
