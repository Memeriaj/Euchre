import java.util.ArrayList;
import java.util.Random;


public class EasyAIPlayer extends AIPlayer {

	public EasyAIPlayer(String name) {
		super(name);
	}
	
	public Card getCardToPlay(Trick trick)
	{
		boolean[] playableTrueFalse = this.getPlayableCards(trick.leadingSuit, trick.trump);
		ArrayList<Integer> playableIndices = new ArrayList<Integer>();
		for (int i = 0 ; i < playableTrueFalse.length;i++)
		{
			boolean b = playableTrueFalse[i];
			if (b)
				playableIndices.add(i);
		}
		Random random = new Random();
		return this.hand.get(playableIndices.get(random.nextInt(playableIndices.size())));
	}
	
	public boolean pickUp(Card kitty)
	{
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public boolean pickUpAsDealer(Card kitty)
	{
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public Card discardDecider(Card kitty)
	{
		Random random = new Random();
		int ind = random.nextInt(6);
		if (ind == 5)
			return kitty;
		else
			return this.hand.get(ind);
	}
	
	public Card.SUIT trumpDecider(Card.SUIT invalid)
	{
		Card.SUIT ret = invalid;
		while (ret == invalid)
		{
			Random random = new Random();
			ret = Card.SUIT.values()[random.nextInt(4)];
		}
		return ret;
	}
	
	public boolean callDecider(Card.SUIT invalid)
	{
		Random random = new Random();
		return random.nextBoolean();
	}
	
	public boolean goAloneDecider(Card.SUIT trump)
	{
		Random random = new Random();
		return random.nextBoolean();
	}
}
