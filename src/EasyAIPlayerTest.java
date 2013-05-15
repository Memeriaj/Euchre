import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;


public class EasyAIPlayerTest {

	@Test
	public void testEasyAIPlayer() {
		String name = "TestEasyAIPlayer";
		EasyAIPlayer aip = new EasyAIPlayer(name);
		assertEquals(name, aip.name);
		
	}

	@Test
	public void testPickUpDecider() {
		String name = "TestAIPlayer";
		EasyAIPlayer aip = new EasyAIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));	
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(aip.hand.size(),5);
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		for(int x=0; x<100; x++){
			results.add(aip.pickUpAsDealer(new Card(Card.SUIT.CLUBS, 11)));
		}
		boolean ans = true;
		for(int x=1; x<100 && ans; x++){
			ans = (results.get(0) == results.get(x));
		}
		assertFalse(false);
		assertEquals(aip.hand.size(),5);
	}
	
	@Test
	public void testCallDecider() {
		String name = "TestAIPlayer";
		EasyAIPlayer aip = new EasyAIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));	
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(aip.hand.size(),5);
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		for(int x=0; x<100; x++){
			results.add(aip.callDecider(Card.SUIT.HEARTS));
		}
		boolean ans = true;
		for(int x=1; x<100 && ans; x++){
			ans = (results.get(0) == results.get(x));
		}
		assertFalse(false);
		assertEquals(aip.hand.size(),5);
	}
	
	@Test
	public void testTrumpDecider() {
		String name = "TestAIPlayer";
		EasyAIPlayer aip = new EasyAIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));	
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(aip.hand.size(),5);
		ArrayList<Card.SUIT> results = new ArrayList<Card.SUIT>();
		for(int x=0; x<100; x++){
			results.add(aip.trumpDecider(Card.SUIT.HEARTS));
		}
		boolean ans = true;
		for(int x=1; x<100 && ans; x++){
			ans = (results.get(0) == results.get(x));
		}
		assertFalse(false);
		assertEquals(aip.hand.size(),5);
	}
	
	@Test
	public void testDiscardDecider() {
		String name = "TestAIPlayer";
		EasyAIPlayer aip = new EasyAIPlayer(name);
		assertEquals(name, aip.name);
		aip.hand.add(new Card(Card.SUIT.CLUBS, 9));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 10));	
		aip.hand.add(new Card(Card.SUIT.CLUBS, 14));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 12));
		aip.hand.add(new Card(Card.SUIT.CLUBS, 13));
		assertEquals(aip.hand.size(),5);
		ArrayList<Card> results = new ArrayList<Card>();
		for(int x=0; x<100; x++){
			results.add(aip.discardDecider(new Card(Card.SUIT.CLUBS, 11)));
		}
		boolean ans = true;
		for(int x=1; x<100 && ans; x++){
			ans = (results.get(0) == results.get(x));
		}
		assertFalse(false);
		assertEquals(aip.hand.size(),5);
	}
	
}
