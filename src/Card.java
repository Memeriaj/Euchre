
public class Card 
{
	public enum SUIT
	{
		CLUBS,
		DIAMONDS,
		HEARTS,
		SPADES
	}
	
	public final SUIT suit;
	public final int value;
	
	public Card(SUIT s, int val)
	{
		this.suit = s;
		this.value = val;
	}

}
