import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class EuchreTest {
	
	@Test
	public void initialize(){
		Euchre e = new Euchre();
		assertNotNull(e);
	}
	
	@Test
	public void playerkNotNull(){
		Euchre e = new Euchre();
		assertFalse(e.players == new ArrayList<Player>());
	}
	
	@Test
	public void allCardsNotNull(){
		Euchre e = new Euchre();
		assertFalse(e.allCards == new ArrayList<Card>());
	}
	
	
	
	@Test
	public void getAllHands_oneCardEach_fourListswithOneCard(){
		Euchre e = new Euchre();
		Card testCard = new Card(Card.SUIT.CLUBS, 9);
		ArrayList<ArrayList<Card>> expected = new ArrayList<ArrayList<Card>>();
		for(Player p : e.players){
			p.hand = new ArrayList<Card>();
			p.hand.add(testCard);
			
			ArrayList<Card> subExpected = new ArrayList<Card>();
			subExpected.add(testCard);
			expected.add(subExpected);
		}
		assertEquals(expected, e.getAllHands());
	}
	
	@Test
	public void testRoundOverHuman(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=10;
		s[1]=2;
		e.score = s;
		assertTrue(e.isOver());
	}
	
	@Test
	public void testRoundOverAI(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=10;
		e.score = s;
		assertTrue(e.isOver());
	}
	
	@Test
	public void testRoundOverFalse(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=7;
		e.score = s;
		assertFalse(e.isOver());
	}
	
	@Test
	public void testWinnerHuman(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=10;
		s[1]=2;
		e.score = s;
		assertEquals(e.getWinner(),0);
	}
	
	@Test
	public void testWinnerAI(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=10;
		e.score = s;
		assertEquals(e.getWinner(),1);
	}
	
	@Test
	public void testWinnerFalse(){
		Euchre e = new Euchre();
		int[] s = new int[2];
		s[0]=3;
		s[1]=7;
		e.score = s;
		assertEquals(e.getWinner(),-1);
	}
}
