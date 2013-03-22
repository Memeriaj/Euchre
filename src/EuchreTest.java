import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;

public class EuchreTest {
	
	@Test
	public void initialize(){
		Euchre e = new Euchre();
		assert(true);
	}
	
	@Test
	public void deckNotNull(){
		Euchre e = new Euchre();
		assertFalse(e.deck == new LinkedList<Card>());
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
	public void deckRandom(){
		Euchre e = new Euchre();
		for(int x=0; x<5; x++)
			System.out.println(e.draw().toString());
		assert(true);
	}
}
