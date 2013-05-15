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
		if(euchre.gameOver){
			String endText = Utils.internationalizeString("gameOver")+"\n";
			if(euchre.score[0] > euchre.score[1])
				endText += Utils.internationalizeString("won");
			else
				endText += Utils.internationalizeString("lost");
			applicationWindow.setMiddleTextArea(Utils.convertStringToHTML(endText));
			return;
		}
		setUpIntitalRound();
	}
	
	private void setUpIntitalRound() {
		System.out.println("INTITAL ROUND");
		if(!euchre.currentRound.isInPreGameState){
			System.out.println("UPDATE GUI");
			updateGUI();
			return;	
		}
		else if(euchre.currentRound.dealerNeedsToDiscard){
			System.out.println("DISCARD");
			setUpDiscard();
			return;
		}
		boolean[] disabled = {false,false,false,false,false};
		applicationWindow.setPlayersCardsEnabled(disabled);
		String[] buttonsText = {"Pass", "Pick it up"};
		applicationWindow.setExtraButtonDisplay(buttonsText);
		System.out.println("SETUP BUTTONS");
		applicationWindow.setMiddleTextArea(Utils.internationalizeString("cardTurnedUp")+": "+
				Utils.internationalizeString(euchre.currentRound.turnedUpCard.toString()));
		applicationWindow.refreshWindow();
	}

	private void updateGUI()
	{
		String text = euchre.currentRound.produceTrickHistoryText();
		
		applicationWindow.updateHands(euchre.getAllHands());
		applicationWindow.setMiddleTextArea(Utils.convertStringToHTML(text));
		String trump = "";
		if(euchre.currentRound.trump == null)
			trump = Utils.internationalizeString("trumpNotSet");
		else
			trump = Utils.internationalizeString(euchre.currentRound.trump.toString());
		applicationWindow.setScoreDisplay(euchre.score[0], euchre.score[1], euchre.currentRound.trickCount[0],
				euchre.currentRound.trickCount[1], trump, printDealer());
		boolean[] cardsEnabled = {false, false, false, false, false};
		if(!euchre.currentRound.isInPreGameState && euchre.currentRound.outPlayer != 0)
			cardsEnabled = euchre.getPlayableCardsForHuman();
		applicationWindow.setPlayersCardsEnabled(cardsEnabled);
		if(euchre.currentRound.outPlayer == 0){
			String[] button = {"contWithGame"};
			applicationWindow.setExtraButtonDisplay(button);
		}
		applicationWindow.refreshWindow();
	}

	private String printDealer() {
		String out = "";
		switch(euchre.currentRound.dealer){
		case 0:
			out += Utils.internationalizeString("you");
			break;
		case 1:
			out += Utils.internationalizeString("left");
			break;
		case 2:
			out += Utils.internationalizeString("top");
			break;
		case 3:
			out += Utils.internationalizeString("right");
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
		System.out.println("Pre Current Player: "+euchre.currentRound.currentTrick.currentPlayer);
		if(text == "Go Alone" || text == "Nothing"){
			if(text == "Go Alone"){
				euchre.goAlone(0);
			}
			euchre.makeGameReadyForHuman();
		}
		else if(text == "Pass"){
			euchre.humanPreRoundPass();
			System.out.println("post Current Player: "+euchre.currentRound.currentTrick.currentPlayer);
		}
		else if(text == "Pick it up"){
			euchre.humanPreRoundCall();
			String[] buttons = {"Go Alone", "Nothing"};
			applicationWindow.setExtraButtonDisplay(buttons);
			applicationWindow.refreshWindow();
			return;
		}
		else if(text == "HEARTS" || text == "SPADES" || text == "DIAMONDS" || text == "CLUBS"){
			euchre.humanPreRoundCallSuit(text);
			String[] buttons = {"Go Alone", "Nothing"};
			applicationWindow.setExtraButtonDisplay(buttons);
			applicationWindow.refreshWindow();
			return;
		}
		else if(text == "ContWithGame"){
			euchre.humanPlayCard("");
			updateGUI();
			setUpIntitalRound();
			if(euchre.currentRound.outPlayer != 0 && !euchre.currentRound.isInPreGameState)
				applicationWindow.setExtraButtonDisplay(new String[0]);
			return;
		}
		else{
			euchre.dealerDiscardForRoundStart(text);
			System.out.println("Discarded a card.");
		}
		String[] buttons = new String[0];
		applicationWindow.setExtraButtonDisplay(buttons);
		applicationWindow.refreshWindow();
		
		System.out.println("isinpregamestate: "+euchre.currentRound.isInPreGameState);
		if(!euchre.currentRound.isInPreGameState)
			updateGUI();
		else if(!euchre.currentRound.isCardTurnedUp)
			pickTrump();
		else if(euchre.currentRound.dealerNeedsToDiscard)
			setUpDiscard();
	}

	private void setUpDiscard() {
		if(euchre.currentRound.outPlayer == 0){
			euchre.dealerDiscardForRoundStart(euchre.currentRound.turnedUpCard.toString());
			updateGUI();
			return;
		}
		updateGUI();
		ArrayList<ArrayList<Card>> hands = euchre.getAllHands();
		String[] extras = new String[6];
		for(int q=0; q<5; q++)
			extras[q] = hands.get(0).get(q).toString();
		extras[5] = euchre.currentRound.turnedUpCard.toString();
		applicationWindow.setExtraButtonDisplay(extras);
		applicationWindow.setMiddleTextArea(Utils.internationalizeString("selectCardDiscard"));
		applicationWindow.refreshWindow();
	}
	
	private void pickTrump(){
		boolean[] disabled = {false,false,false,false};
		applicationWindow.setPlayersCardsEnabled(disabled);
		String[] buttonsText;
		if(euchre.currentRound.isStickTheDealer)
			buttonsText = new String[3];
		else{
			buttonsText = new String[4];
			buttonsText[3] = "Pass";
		}
		for(int q=0; q<3; q++)
			buttonsText[q] = euchre.currentRound.callableSuits[q].toString();
		applicationWindow.setExtraButtonDisplay(buttonsText);
		applicationWindow.setMiddleTextArea("");
		applicationWindow.refreshWindow();
	}

}
