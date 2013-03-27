import java.util.ArrayList;

import javax.swing.JButton;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

public class Controller {

	private Euchre euchre = new Euchre();
	private String text = "";
	private ApplicationWindow applicationWindow;

	/**
	 * This could easily be refactored so that the UI could accept other object
	 * types or something.
	 * 
	 * @return An arrayList of all of the hands, clockwise starting from the
	 *         human player and moving left around the table.
	 */
	public ArrayList<ArrayList<Card>> getAllHands() {
		ArrayList<ArrayList<Card>> out = new ArrayList<ArrayList<Card>>();
		for(Player p : euchre.players)
			out.add(p.hand);
		return out;
	}

	/**
	 * Called when the human player presses one of the card buttons. Depending
	 * on usage may change it from giving a JButton to a something else.
	 * 
	 * @param source
	 *            The JButton that was pressed.
	 */
	public void cardPlayed(String cardText) {
		euchre.playCard(cardText);
		euchre.makeAIPlay();
		text = "Your score: "+euchre.trickCount[0]+"   Opponent's score: "+euchre.trickCount[0]+"\n";
		for(int q=0; q<euchre.currTrick.size(); q++){
			if(euchre.currTrick.get(q) != null)
				text += "Player "+q+": "+euchre.currTrick.get(q).toString()+"\n";
		}
		System.out.println(text);
		applicationWindow.updateHands(getAllHands());
		applicationWindow.setMiddleTextArea(Utils.convertStringToHTML(text));
	}

	/**
	 * 
	 * @return Specifies the text that will go in the middle of the window
	 */
	public String getTextForMiddle() {
		return text;
	}

	public void setAppWindow(ApplicationWindow appWin) {
		applicationWindow = appWin;
	}

}
