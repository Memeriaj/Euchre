import java.util.ArrayList;


public class Player {
	public String name;
	public ArrayList<Card> hand;
	
	public Player (String s){
		name = s;
		hand = new ArrayList<Card>();
	}
}
