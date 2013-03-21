import java.util.ArrayList;

import javax.swing.JButton;


public class Controller {

	/**
	 * This could easily be refactored so that the UI could accept other object
	 * types or something.
	 * 
	 * @return An arrayList of all of the hands, clockwise starting from the
	 *         human player and moving left around the table.
	 */
	public ArrayList<ArrayList<Card>> getAllHands() {
		ArrayList<ArrayList<Card>> out = new ArrayList<ArrayList<Card>>();
		for(int q=0; q<4; q++){
			ArrayList<Card> hand = new ArrayList<Card>();
			for(int w=0; w<5; w++){
				hand.add(new Card(Card.SUIT.CLUBS, 9+w));
			}
			out.add(hand);
		}
		return out;
	}

	public void cardPlayed(JButton source) {
		System.out.println("Played: "+source.getText());
	}

}
