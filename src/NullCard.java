
public class NullCard extends Card {

	
	public NullCard()
	{
		super(Card.SUIT.CLUBS, Integer.MIN_VALUE);
	}
	
	@Override
	public String toString()
	{
		return "";
	}
	
	public int cardValue (SUIT trump, SUIT leading)
	{
		return this.value;
	}
	
	public int biddingValue (SUIT trump)
	{
		return this.value;
	}
	
	public int cardValueNoLead (SUIT trump)
	{
		return this.value;
	}

}
