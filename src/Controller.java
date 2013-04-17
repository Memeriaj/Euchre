

public class Controller {

	Euchre euchre = new Euchre();
	private ApplicationWindow applicationWindow;

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
		String text = euchre.currentRound.produceTrickHistoryText();
		
		applicationWindow.updateHands(euchre.getAllHands());
		applicationWindow.setMiddleTextArea(Utils.convertStringToHTML(text));
		applicationWindow.setScoreDisplay(euchre.score[0], euchre.score[1], euchre.currentRound.trickCount[0],
				euchre.currentRound.trickCount[1], euchre.currentRound.trump.toString());
		applicationWindow.setPlayersCardsEnabled(euchre.getPlayableCardsForHuman());
		applicationWindow.refreshWindow();
	}

	public void setAppWindow(ApplicationWindow appWin) {
		applicationWindow = appWin;
		updateGUI();
	}

}
