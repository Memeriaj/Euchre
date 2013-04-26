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
	
	@Test
	public void removeCardFromHand_emptyHand_noError(){
		Player testPlayer = new Player("test");
		testPlayer.removeCardFromHand("Ace of Spades");
		assertTrue(true);
	}
	
	@Test
	public void removeCardFromHand_cardNotInHand_fiveCardsRemaining(){
		Player testPlayer = new Player("test");
		testPlayer.hand.add(new Card(Card.SUIT.HEARTS, 10));
		testPlayer.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		testPlayer.hand.add(new Card(Card.SUIT.SPADES, 9));
		testPlayer.hand.add(new Card(Card.SUIT.SPADES, 13));
		testPlayer.hand.add(new Card(Card.SUIT.DIAMONDS, 14));
		testPlayer.removeCardFromHand("Ace of Spades");
		assertEquals(5, testPlayer.hand.size());
	}
	
	@Test
	public void removeCardFromHand_onlyCardInHand_noCardsLeft(){
		Player testPlayer = new Player("test");
		testPlayer.hand.add(new Card(Card.SUIT.SPADES, 14));
		testPlayer.removeCardFromHand("Ace of Spades");
		assertEquals(0, testPlayer.hand.size());
	}
	
	@Test
	public void removeCardFromHand_multipleCardsInHand_fourCardsLeft(){
		Player testPlayer = new Player("test");
		testPlayer.hand.add(new Card(Card.SUIT.HEARTS, 10));
		testPlayer.hand.add(new Card(Card.SUIT.DIAMONDS, 11));
		testPlayer.hand.add(new Card(Card.SUIT.SPADES, 14));
		testPlayer.hand.add(new Card(Card.SUIT.SPADES, 9));
		testPlayer.hand.add(new Card(Card.SUIT.SPADES, 13));
		testPlayer.removeCardFromHand("Ace of Spades");
		assertEquals(4, testPlayer.hand.size());
	}
	
	@Test
	public void testGetPlayableCardsAllLeadingSuit()
	{
		Player target = new Player("bob");
		target.hand.add(new Card(Card.SUIT.HEARTS,9));
		target.hand.add(new Card(Card.SUIT.HEARTS,10));
		target.hand.add(new Card(Card.SUIT.HEARTS,11));
		target.hand.add(new Card(Card.SUIT.HEARTS,12));
		target.hand.add(new Card(Card.SUIT.HEARTS,13));
		
		boolean[] playable = target.getPlayableCards(Card.SUIT.HEARTS, Card.SUIT.CLUBS);
		boolean[] test = {true, true, true, true, true};
		
		assertEquals(test[0],playable[0]);
		assertEquals(test[1],playable[1]);
		assertEquals(test[2],playable[2]);
		assertEquals(test[3],playable[3]);
		assertEquals(test[4],playable[4]);
	}
	
	@Test
	public void testGetPlayableCardsNoneLeadingSuit()
	{
		Player target = new Player("bob");
		target.hand.add(new Card(Card.SUIT.HEARTS,9));
		target.hand.add(new Card(Card.SUIT.HEARTS,10));
		target.hand.add(new Card(Card.SUIT.HEARTS,11));
		target.hand.add(new Card(Card.SUIT.HEARTS,12));
		target.hand.add(new Card(Card.SUIT.HEARTS,13));
		
		boolean[] playable = target.getPlayableCards(Card.SUIT.DIAMONDS, Card.SUIT.CLUBS);
		boolean[] test = {true, true, true, true, true};
		
		assertEquals(test[0],playable[0]);
		assertEquals(test[1],playable[1]);
		assertEquals(test[2],playable[2]);
		assertEquals(test[3],playable[3]);
		assertEquals(test[4],playable[4]);
	}
	
	@Test
	public void testGetPlayableCardsCanPlayLeft()
	{
		Player target = new Player("bob");
		target.hand.add(new Card(Card.SUIT.HEARTS,9));
		target.hand.add(new Card(Card.SUIT.HEARTS,10));
		target.hand.add(new Card(Card.SUIT.HEARTS,11));
		target.hand.add(new Card(Card.SUIT.HEARTS,12));
		target.hand.add(new Card(Card.SUIT.HEARTS,13));
		
		boolean[] playable = target.getPlayableCards(Card.SUIT.DIAMONDS, Card.SUIT.DIAMONDS);
		boolean[] test = {false, false, true, false, false};
		
		assertEquals(test[0],playable[0]);
		assertEquals(test[1],playable[1]);
		assertEquals(test[2],playable[2]);
		assertEquals(test[3],playable[3]);
		assertEquals(test[4],playable[4]);
	}
	
	@Test
	public void testGetPlayableCardsCantPlayLeft()
	{
		Player target = new Player("bob");
		target.hand.add(new Card(Card.SUIT.HEARTS,9));
		target.hand.add(new Card(Card.SUIT.HEARTS,10));
		target.hand.add(new Card(Card.SUIT.HEARTS,11));
		target.hand.add(new Card(Card.SUIT.HEARTS,12));
		target.hand.add(new Card(Card.SUIT.HEARTS,13));
		
		boolean[] playable = target.getPlayableCards(Card.SUIT.HEARTS, Card.SUIT.DIAMONDS);
		boolean[] test = {true, true, false, true, true};
		
		assertEquals(test[0],playable[0]);
		assertEquals(test[1],playable[1]);
		assertEquals(test[2],playable[2]);
		assertEquals(test[3],playable[3]);
		assertEquals(test[4],playable[4]);
	}
	
	@Test
	public void testGetPlayableCardsCanTrumpSinceDontHaveLead()
	{
		Player target = new Player("bob");
		target.hand.add(new Card(Card.SUIT.HEARTS,9));
		target.hand.add(new Card(Card.SUIT.HEARTS,10));
		target.hand.add(new Card(Card.SUIT.HEARTS,11));
		target.hand.add(new Card(Card.SUIT.HEARTS,12));
		target.hand.add(new Card(Card.SUIT.HEARTS,13));
		
		boolean[] playable = target.getPlayableCards(Card.SUIT.CLUBS, Card.SUIT.HEARTS);
		boolean[] test = {true, true, true, true, true};
		
		assertEquals(test[0],playable[0]);
		assertEquals(test[1],playable[1]);
		assertEquals(test[2],playable[2]);
		assertEquals(test[3],playable[3]);
		assertEquals(test[4],playable[4]);
	}

}
