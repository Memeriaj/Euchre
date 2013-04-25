import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testCard() {
		Card c = new Card(Card.SUIT.CLUBS, 9);
		assertNotNull(c);
	}

	@Test
	public void testEqualsObject() {
		Card c1 = new Card(Card.SUIT.CLUBS, 9);
		Card c2 = new Card(Card.SUIT.CLUBS, 9);
		assertEquals(c1,c2);
		Card c3 = new Card(Card.SUIT.HEARTS, 11);
		Card c4 = new Card(Card.SUIT.HEARTS, 11);
		assertEquals(c3,c4);
		Card c5 = new Card(Card.SUIT.SPADES, 13);
		Card c6 = new Card(Card.SUIT.SPADES, 13);
		assertEquals(c5,c6);
		Card c7 = new Card(Card.SUIT.DIAMONDS, 14);
		Card c8 = new Card(Card.SUIT.DIAMONDS, 14);
		assertEquals(c7,c8);
	}
	
	@Test
	public void testNotEqualsRandomObject()
	{
		Card c = new Card(Card.SUIT.CLUBS, 9);
		assertFalse(c.equals("Hello"));
	}
	
	@Test
	public void testNotEqualsObjectSameNumber() {
		Card c1 = new Card(Card.SUIT.CLUBS, 9);
		Card c2 = new Card(Card.SUIT.HEARTS, 9);
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsObjectSameSuit() {
		Card c1 = new Card(Card.SUIT.DIAMONDS, 10);
		Card c2 = new Card(Card.SUIT.DIAMONDS, 9);
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testNotEqualsObjectDifferentEverything() {
		Card c1 = new Card(Card.SUIT.SPADES, 13);
		Card c2 = new Card(Card.SUIT.CLUBS, 9);
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testCardValueNonTrumpNonLead(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.cardValue(Card.SUIT.HEARTS,Card.SUIT.DIAMONDS),12);
	}
	@Test
	public void testCardValueNonTrumpLead(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.cardValue(Card.SUIT.HEARTS,Card.SUIT.SPADES),26);
	}
	
	@Test
	public void testCardValueTrumpNonJack(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.cardValue(Card.SUIT.SPADES,Card.SUIT.DIAMONDS),40);
	}
	
	@Test
	public void testCardValueTrumpJack(){
		Card c = new Card(Card.SUIT.SPADES, 11);
		assertEquals(c.cardValue(Card.SUIT.SPADES,Card.SUIT.DIAMONDS),44);
	}
	
	@Test
	public void testCardValueSameColorJack(){
		Card c = new Card(Card.SUIT.CLUBS, 11);
		assertEquals(c.cardValue(Card.SUIT.SPADES,Card.SUIT.DIAMONDS),43);
	}
	
	@Test
	public void testCardValue_clubsTrumpHeartsLead_normalValue(){
		Card c = new Card(Card.SUIT.DIAMONDS, 9);
		assertEquals(c.cardValue(Card.SUIT.CLUBS,Card.SUIT.HEARTS),9);
	}
	
	@Test
	public void testCardValue_diamondTrumpHeartsLead_normalValue(){
		Card c = new Card(Card.SUIT.HEARTS, 9);
		assertEquals(c.cardValue(Card.SUIT.DIAMONDS,Card.SUIT.HEARTS),23);
	}
	
	@Test
	public void testGreaterNoTrump(){
		Card c1 = new Card(Card.SUIT.SPADES, 12);
		Card c2 = new Card(Card.SUIT.SPADES, 10);
		assertTrue(c1.greater(c2, Card.SUIT.HEARTS,Card.SUIT.SPADES));
	}
	
	@Test
	public void testGreaterTrump(){
		Card c1 = new Card(Card.SUIT.SPADES, 10);
		Card c2 = new Card(Card.SUIT.SPADES, 12);
		assertFalse(c1.greater(c2, Card.SUIT.SPADES,Card.SUIT.SPADES));
	}
	
	@Test
	public void testGreaterTrumpAndNoTrump(){
		Card c1 = new Card(Card.SUIT.SPADES, 10);
		Card c2 = new Card(Card.SUIT.HEARTS, 12);
		assertTrue(c1.greater(c2, Card.SUIT.SPADES,Card.SUIT.HEARTS));
	}
	
	@Test
	public void testGreaterLeadAndNoLead(){
		Card c1 = new Card(Card.SUIT.DIAMONDS, 10);
		Card c2 = new Card(Card.SUIT.HEARTS, 12);
		assertTrue(c1.greater(c2, Card.SUIT.SPADES,Card.SUIT.DIAMONDS));
	}
	
	@Test
	public void toString_9D_NineOfDiamonds(){
		Card card = new Card(Card.SUIT.DIAMONDS, 9);
		assertTrue(card.toString().equals("Nine of Diamonds"));
	}
	
	@Test
	public void toString_10H_TenOfHearts(){
		Card card = new Card(Card.SUIT.HEARTS, 10);
		assertTrue(card.toString().equals("Ten of Hearts"));
	}
	
	@Test
	public void toString_11C_JackOfClubs(){
		Card card = new Card(Card.SUIT.CLUBS, 11);
		assertTrue(card.toString().equals("Jack of Clubs"));
	}
	
	@Test
	public void toString_12D_QueenOfSpades(){
		Card card = new Card(Card.SUIT.SPADES, 12);
		assertTrue(card.toString().equals("Queen of Spades"));
	}
	
	@Test
	public void toString_13D_KingOfDiamonds(){
		Card card = new Card(Card.SUIT.DIAMONDS, 13);
		assertTrue(card.toString().equals("King of Diamonds"));
	}
	
	@Test
	public void toString_14D_AceOfDiamonds(){
		Card card = new Card(Card.SUIT.DIAMONDS, 14);
		assertTrue(card.toString().equals("Ace of Diamonds"));
	}
	
	@Test
	public void toString_7D_notValid(){
		Card card = new Card(Card.SUIT.DIAMONDS, 7);
		assertTrue(card.toString().equals("NOT VALID of Diamonds"));
	}
	
	@Test
	public void testCardValueNoLeadNonTrump(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.cardValueNoLead(Card.SUIT.HEARTS),12);
	}
	
	@Test
	public void testCardValueNoLeadTrumpNonJack(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.cardValueNoLead(Card.SUIT.SPADES),26);
	}
	
	@Test
	public void testCardValueNoLeadTrumpJack(){
		Card c = new Card(Card.SUIT.SPADES, 11);
		assertEquals(c.cardValueNoLead(Card.SUIT.SPADES),30);
	}
	
	@Test
	public void testCardValueNoLeadSameColorJack(){
		Card c = new Card(Card.SUIT.CLUBS, 11);
		assertEquals(c.cardValueNoLead(Card.SUIT.SPADES),29);
	}
	
	@Test
	public void testBiddingValueNoLeadNonTrump(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.biddingValue(Card.SUIT.HEARTS),0);
	}
	
	@Test
	public void testBiddingValueNoLeadTrumpNonJack(){
		Card c = new Card(Card.SUIT.SPADES, 12);
		assertEquals(c.biddingValue(Card.SUIT.SPADES),6);
	}
	
	@Test
	public void testBiddingValueNoLeadTrumpJack(){
		Card c = new Card(Card.SUIT.SPADES, 11);
		assertEquals(c.biddingValue(Card.SUIT.SPADES),12);
	}
	
	@Test
	public void testBiddingValueNoLeadSameColorJack(){
		Card c = new Card(Card.SUIT.CLUBS, 11);
		assertEquals(c.biddingValue(Card.SUIT.SPADES),11);
	}
	
	@Test
	public void testBiddingValueNotTrumpAce(){
		Card c = new Card(Card.SUIT.CLUBS, 14);
		assertEquals(c.biddingValue(Card.SUIT.SPADES),7);
	}
}
