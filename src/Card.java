
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
	
	@Override
	public String toString()
	{
		String ret = "Invalid";
		switch (this.value)
		{
		case 9:
			ret = "Nine";
			break;
		case 10:
			ret = "Ten";
			break;
		case 11:
			ret = "Jack";
			break;
		case 12:
			ret = "Queen";
			break;
		case 13:
			ret = "King";
			break;
		case 14:
			ret = "Ace";
			break;
		}
		ret += " of ";
		switch (this.suit)
		{
		case CLUBS:
			ret+="Clubs";
			break;
		case DIAMONDS:
			ret+="Diamonds";
			break;
		case HEARTS:
			ret+="Hearts";
			break;
		case SPADES:
			ret+="Spades";
			break;
		}
		return ret;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Card otherCard = (Card) obj;
		return this.value == otherCard.value && this.suit == otherCard.suit;
	}
}
