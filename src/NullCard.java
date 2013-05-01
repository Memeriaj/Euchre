
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
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Card otherCard = (Card) obj;
		return this.value == otherCard.value && this.suit == otherCard.suit;
	}
	
//	public static SUIT getSameColorSuit(SUIT trump)
//	{
//		SUIT sameColor= SUIT.HEARTS;
//		switch (trump)
//		{
//		case CLUBS:
//			sameColor = SUIT.SPADES;
//			break;
//		case SPADES:
//			sameColor = SUIT.CLUBS;
//			break;
//		case HEARTS:
//			sameColor = SUIT.DIAMONDS;
//			break;
//		default:
//			sameColor = SUIT.HEARTS;
//			break;
//		}
//		return sameColor;
//	}
	
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
	
//	public boolean greater(Card other, SUIT trump, SUIT leading)
//	{
//		return this.cardValue(trump,leading) > other.cardValue(trump, leading);	
//	}

}
