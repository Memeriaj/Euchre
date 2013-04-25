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
		euchre.humanPlayCard(cardText);
		updateGUI();
		setUpIntitalRound();
	}
	
	private void setUpIntitalRound() {
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
				euchre.currentRound.trickCount[1], trump, printDealer());
		boolean[] cardsEnabled = {false, false, false, false, false};
		if(!euchre.currentRound.isInPreGameState)
			cardsEnabled = euchre.getPlayableCardsForHuman();
		applicationWindow.setPlayersCardsEnabled(cardsEnabled);
		applicationWindow.refreshWindow();
	}

	private String printDealer() {
		String out = "";
		switch(euchre.currentRound.dealer){
		case 0:
			out += "You";
			break;
		case 1:
			out += "Left";
			break;
		case 2:
			out += "Top";
			break;
		case 3:
			out += "Right";
			break;
		default:
			System.out.println("Problem with switch for printing dealer!!!!");
		}
		return out;
	}

	public void setAppWindow(ApplicationWindow appWin) {
		applicationWindow = appWin;
		updateGUI();
		setUpIntitalRound();
	}

	public void extraButtonSelected(String text) {
		System.out.println(text);
		if(euchre.currentRound.isCardTurnedUp){
			if(text == "Pass")
				euchre.humanPreRoundPass();
			else
				euchre.humanPreRoundCall();
		}
		String[] buttons = new String[0];
		applicationWindow.setExtraButtonDisplay(buttons);
		applicationWindow.refreshWindow();
		
		System.out.println("isinpregamestate: "+euchre.currentRound.isInPreGameState);
		if(!euchre.currentRound.isInPreGameState)
			updateGUI();
		else if(euchre.currentRound.dealerNeedsToDiscard)
			setUpDiscard();
		else
			pickTrump();
	}

	private void setUpDiscard() {
		updateGUI();
		ArrayList<ArrayList<Card>> hands = euchre.getAllHands();
		String[] extras = new String[6];
		for(int q=0; q<5; q++)
			extras[q] = hands.get(0).get(q).toString();
		extras[5] = euchre.currentRound.turnedUpCard.toString();
//		applicationWindow.updateHands(hands);
//		boolean[] enabled = {true, true, true, true, true, true};
//		applicationWindow.setPlayersCardsEnabled(enabled);
		applicationWindow.setExtraButtonDisplay(extras);
		applicationWindow.setMiddleTextArea("Please select a card to discard.");
		applicationWindow.refreshWindow();
	}
	
	private void pickTrump(){
		if(!euchre.currentRound.isInPreGameState){
			updateGUI();
			return;
		}
		boolean[] disabled = {false,false,false,false};
		applicationWindow.setPlayersCardsEnabled(disabled);
		String[] buttonsText;
		if(euchre.currentRound.isStickTheDealer)
			buttonsText = new String[3];
		else{
			buttonsText = new String[4];
			buttonsText[3] = "Pass ";
		}
		for(int q=0; q<3; q++)
			buttonsText[q] = euchre.currentRound.callableSuits[q].toString();
		applicationWindow.setExtraButtonDisplay(buttonsText);
		applicationWindow.setMiddleTextArea("");
		applicationWindow.refreshWindow();
	}

}
