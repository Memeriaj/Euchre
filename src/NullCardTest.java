import static org.junit.Assert.*;

import org.junit.Test;


public class NullCardTest {

	@Test
	public void testNullCardInitializes() {
		assertNotNull(new NullCard());
	}
	
	@Test
	public void testNullCardValue()
	{
		assertEquals(Integer.MIN_VALUE,new NullCard().value);
	}
	
	@Test
	public void testNullCardString()
	{
		assertTrue(new NullCard().toString().equals(""));
	}
	
	@Test
	public void testNullCardCardValue()
	{
		Card.SUIT[] suits = {Card.SUIT.CLUBS, Card.SUIT.DIAMONDS, Card.SUIT.HEARTS, Card.SUIT.SPADES};
		for (int i = 0;i<4;i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(Integer.MIN_VALUE, new NullCard().cardValue(suits[i], suits[j]));
			}
		}
	}
	
	@Test
	public void testNullCardBiddingValue()
	{
		Card.SUIT[] suits = {Card.SUIT.CLUBS, Card.SUIT.DIAMONDS, Card.SUIT.HEARTS, Card.SUIT.SPADES};
		for (int i = 0;i<4;i++)
		{
				assertEquals(Integer.MIN_VALUE, new NullCard().biddingValue(suits[i]));
		}
	}
	
	@Test
	public void testNullCardCardValueNoLead()
	{
		Card.SUIT[] suits = {Card.SUIT.CLUBS, Card.SUIT.DIAMONDS, Card.SUIT.HEARTS, Card.SUIT.SPADES};
		for (int i = 0;i<4;i++)
		{
				assertEquals(Integer.MIN_VALUE, new NullCard().cardValueNoLead(suits[i]));
		}
	}

}
