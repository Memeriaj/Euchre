import java.util.ArrayList;



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
		if(euchre.currentRound.isInPreGameState && euchre.currentRound.dealer == 0){
			
		}
		euchre.humanPlayCard(cardText);
		while (euchre.isCurrentPlayerAI() && !euchre.currentRound.isInPreGameState)
		{
			euchre.makeAIPlay();
		}
		updateGUI();
		if(euchre.currentRound.isInPreGameState){
			setUpIntitalRound();
		}
	}
	
	private void setUpIntitalRound() {
		while(euchre.isCurrentPlayerAI() && euchre.currentRound.isInPreGameState)
			euchre.makeAIPlayPreRound();
		while(euchre.isCurrentPlayerAI())
			euchre.makeAIPlay();
		if(!euchre.currentRound.isInPreGameState){
			updateGUI();
			return;
		}
		boolean[] disabled = {false,false,false,false};
		applicationWindow.setPlayersCardsEnabled(disabled);
		String[] buttonsText = {"Pass", "Pick it up"};
		applicationWindow.setExtraButtonDisplay(buttonsText);
		applicationWindow.setMiddleTextArea("Card Turned up: "+euchre.currentRound.turnedUpCard.toString());
		applicationWindow.refreshWindow();
	}

	private void updateGUI()
	{
		String text = euchre.currentRound.produceTrickHistoryText();
		
		applicationWindow.updateHands(euchre.getAllHands());
		applicationWindow.setMiddleTextArea(Utils.convertStringToHTML(text));
		String trump = "";
		if(euchre.currentRound.trump == null)
			trump = "Trump hasn't been set yet";
		else
			trump = euchre.currentRound.trump.toString();
		applicationWindow.setScoreDisplay(euchre.score[0], euchre.score[1], euchre.currentRound.trickCount[0],
				euchre.currentRound.trickCount[1], trump);
		boolean[] cardsEnabled = {false, false, false, false, false};
		if(!euchre.currentRound.isInPreGameState)
			cardsEnabled = euchre.getPlayableCardsForHuman();
		applicationWindow.setPlayersCardsEnabled(cardsEnabled);
		applicationWindow.refreshWindow();
	}

	public void setAppWindow(ApplicationWindow appWin) {
		applicationWindow = appWin;
		updateGUI();
		if(euchre.currentRound.isInPreGameState)
			setUpIntitalRound();
	}

	public void extraButtonSelected(String text) {
		System.out.println(text);
		if(euchre.currentRound.isCardTurnedUp){
			if(text == "Pass")
				euchre.currentRound.preRoundPass();
//			else if(euchre.currentRound.dealer == 0)
//				setUpDiscard();
			else
				euchre.currentRound.preRoundCall();
		}
		String[] buttons = new String[0];
		applicationWindow.setExtraButtonDisplay(buttons);
		applicationWindow.refreshWindow();
		while(euchre.isCurrentPlayerAI() && euchre.currentRound.isInPreGameState)
			euchre.makeAIPlayPreRound();
		while(euchre.isCurrentPlayerAI())
			euchre.makeAIPlay();
		if(!euchre.currentRound.isInPreGameState)
			updateGUI();
	}

	private void setUpDiscard() {
		updateGUI();
		ArrayList<ArrayList<Card>> hands = euchre.getAllHands();
		hands.get(0).add(euchre.currentRound.turnedUpCard);
		applicationWindow.updateHands(hands);
		boolean[] enabled = {true, true, true, true, true, true};
		applicationWindow.setPlayersCardsEnabled(enabled);
		applicationWindow.setMiddleTextArea("Please select a card to discard.");
		applicationWindow.refreshWindow();
	}

}
