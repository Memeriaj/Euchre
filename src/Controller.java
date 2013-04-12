
import java.util.ArrayList;

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
	 * @throws InterruptedException 
	 */
	public void cardPlayed(String cardText) {
		euchre.humanPlayCard(cardText);
		while (euchre.isCurrentPlayerAI())
		{
			euchre.makeAIPlay();
		}
		updateGUI();
	}
	
	private void updateGUI()
	{
		ArrayList<Trick> tricks = euchre.currentRound.trickHistory;
		text = "";
		if(!tricks.isEmpty()){
			text += "Previous Trick played:\n";
			text += stringOFTrickPlayed(tricks.get(tricks.size()-1));
		}
		text += "\nCurrent Trick:\n";
		text += stringOFTrickPlayed(euchre.currentRound.currentTrick);
		
		System.out.println(text);
		
		applicationWindow.updateHands(getAllHands());
		applicationWindow.setMiddleTextArea(Utils.convertStringToHTML(text));
		applicationWindow.setScoreDisplay(euchre.score[0], euchre.score[1], euchre.currentRound.trickCount[0],
				euchre.currentRound.trickCount[1], euchre.currentRound.trump.toString());
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
	
	public String stringOFTrickPlayed(Trick trick){
		String out = "";
		int currentPlayer = trick.leadingPlayer;
		for(Card card : trick.cardsPlayed){
			if(currentPlayer == 0)
				out += "You: ";
			else
				out += "Player " + currentPlayer + ": ";
			out += card + "\n";
			currentPlayer = (currentPlayer + 1) % 4;
		}
		return out;
	}

}
