import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationWindow {

	private Controller controller;
	private JFrame frame;
	private ArrayList<JPanel> playerPanels;
	private JLabel middleText;
	private JLabel[] scoreLabels;
	private JPanel extraButtonsPanel;
	private JCheckBox showOppCards;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow(
							new Controller());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. Need to remove the controller creation once rest
	 * has been implemented.
	 */
	public ApplicationWindow(Controller cont) {
		this.controller = cont;
		initialize();
		cont.setAppWindow(this);
	}

	/**
	 * Initialize the contents of the frame and also does an inital update on
	 * them.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(createMainDisplayPanel(),
				BorderLayout.CENTER);
		frame.getContentPane().add(createExtraButtonsPanel(),
				BorderLayout.SOUTH);
		frame.getContentPane().add(createScoreDisplayPanel(),
				BorderLayout.NORTH);

	}

	private Component createScoreDisplayPanel() {
		JPanel out = new JPanel();
		out.setLayout(new BorderLayout(0, 0));
		scoreLabels = new JLabel[3];
		for(int q=0; q<3; q++)
			scoreLabels[q] = new JLabel();
		out.add(scoreLabels[0], BorderLayout.WEST);
		out.add(scoreLabels[1], BorderLayout.EAST);
		scoreLabels[2].setHorizontalAlignment(JLabel.CENTER);
		out.add(scoreLabels[2], BorderLayout.CENTER);
		out.add(new JPanel(), BorderLayout.SOUTH);
		setScoreDisplay(0, 0, 0, 0, "", "");
		showOppCards = new JCheckBox();
		showOppCards.setText(Utils.internationalizeString("showOppCards"));
		showOppCards.setSelected(false);
		out.add(showOppCards, BorderLayout.SOUTH);
		return out;
	}

	public void setScoreDisplay(int playerOverallScore,
			int opponentOverallScore, int playerTrickScore,
			int opponentTrickScore, String suit, String dealer) {
		String lConvert = Utils.internationalizeString("overallScore") + "\n" +
				Utils.internationalizeString("yours") + ": " + playerOverallScore + "   " + 
				Utils.internationalizeString("opponent") + ": " + opponentOverallScore;
		String left = Utils.convertStringToHTML(lConvert);
		
		String rConvert = Utils.internationalizeString("trickScore") + "\n" +
				Utils.internationalizeString("yours") + ": " + playerTrickScore + "   " + 
				Utils.internationalizeString("opponent") + ": " + opponentTrickScore;
		String right = Utils.convertStringToHTML(rConvert);
		
		String mConvert = Utils.internationalizeString("trump") + ": " +
				suit + "       " + Utils.internationalizeString("dealer") + 
				": "+dealer;
		String middle = Utils.convertStringToHTML(mConvert);
		scoreLabels[0].setText(left);
		scoreLabels[1].setText(right);
		scoreLabels[2].setText(middle);
	}

	public void refreshWindow() {
		frame.revalidate();
		frame.repaint();
	}

	private Component createExtraButtonsPanel() {
		extraButtonsPanel = new JPanel();
		setExtraButtonDisplay(new String[0]);
		return extraButtonsPanel;
	}

	public void setExtraButtonDisplay(String[] buttonsText) {
		extraButtonsPanel.removeAll();
		extraButtonsPanel.setLayout(new GridLayout());
		for(String s : buttonsText){
			JButton button = new JButton();
			button.setText(Utils.internationalizeString(s));
			button.setName(s);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JButton buttonPressed = (JButton) arg0.getSource();
					controller.extraButtonSelected(buttonPressed.getName());
				}
			});
			extraButtonsPanel.add(button);
		}
	}

	private JPanel createMainDisplayPanel() {
		JPanel mainDisplay = new JPanel();
		mainDisplay.setLayout(new BorderLayout(0, 0));

		createPlayerPanels(mainDisplay);
		createMiddleTextArea(mainDisplay);
		return mainDisplay;
	}

	/**
	 * Creates the label that goes in the center of the window.
	 * 
	 * @param mainDisplay
	 */
	private void createMiddleTextArea(JPanel mainDisplay) {
		JPanel panel = new JPanel();
		mainDisplay.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridBagLayout());
		middleText = new JLabel();
		panel.add(middleText);
	}

	/**
	 * Updates the middle text. The method changes the string to HTML to
	 * display, care needs to be taken on any HTML escape charatcers or the
	 * like.
	 * 
	 * @param textForMiddle
	 *            The string to be placed in the center of window.
	 */
	public void setMiddleTextArea(String textForMiddle) {
		middleText.setText(textForMiddle);
	}

	/**
	 * Creates all of the panels that will display the players cards.
	 * 
	 * @param mainDisplay
	 */
	private void createPlayerPanels(JPanel mainDisplay) {
		playerPanels = new ArrayList<JPanel>();
		for (int q = 0; q < 4; q++)
			playerPanels.add(new JPanel());
		mainDisplay.add(playerPanels.get(0), BorderLayout.SOUTH);
		mainDisplay.add(playerPanels.get(1), BorderLayout.WEST);
		mainDisplay.add(playerPanels.get(2), BorderLayout.NORTH);
		mainDisplay.add(playerPanels.get(3), BorderLayout.EAST);

		for (int q = 0; q < 4; q++) {
			if (q == 0) {
				playerPanels.get(0).setLayout(new GridLayout(1, 0, 0, 0));
				for (int w = 0; w < 5; w++) {
					JButton toAdd = new JButton();
					toAdd.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							JButton buttonPressed = (JButton) arg0.getSource();
							controller.cardPlayed(buttonPressed.getName());
						}
					});
					playerPanels.get(0).add(toAdd);
				}
			} else {
				// 0 - Horiz, 1 - Vert, 2 - Horiv, 3 - Vert
				playerPanels.get(q).setLayout(
						new GridLayout((q + 1) % 2, q % 2, 0, 0));
				for (int w = 0; w < 5; w++) {
					JLabel toAdd = new JLabel();
					playerPanels.get(q).add(toAdd);
				}
			}
		}
	}

	/**
	 * Updates the GUI to reflect the the hand passed in
	 * 
	 * @param pos
	 *            0-Human, 1-Left, 2-Top, 3-Right
	 * @param currentHand
	 *            The hand of that player
	 */
	private void updateHands(int pos, ArrayList<Card> currentHand) {
		System.out.println(currentHand);
		for (int q = 0; q < currentHand.size(); q++) {
			Card currCard = currentHand.get(q);
			String toSet = currCard.toString();
			if (pos == 0){
				((JButton) playerPanels.get(pos).getComponent(q)).setName(toSet);
				((JButton) playerPanels.get(pos).getComponent(q))
					.setText(Utils.internationalizeString(toSet));
			}
			else{
				if(!showOppCards.isSelected() && toSet != "")
					toSet = "hidden";
				((JLabel) playerPanels.get(pos).getComponent(q))
					.setText(Utils.internationalizeString(toSet));
			}
			playerPanels.get(pos).getComponent(q).setVisible(true);
		}
		for (int q = currentHand.size(); q < 5; q++)
			playerPanels.get(pos).getComponent(q).setVisible(false);
	}

	/**
	 * Updates all player's hands to the GUI.
	 * 
	 * @param currentHands
	 *            All of the hands, must come in order, the human first then
	 *            around from his left.
	 */
	public void updateHands(ArrayList<ArrayList<Card>> currentHands) {
		for (int q = 0; q < currentHands.size(); q++)
			updateHands(q, currentHands.get(q));
	}
	
	public void setPlayersCardsEnabled(boolean[] enabled){
		JPanel humanButtons = playerPanels.get(0);
		for(int q=0; q<enabled.length; q++){
			JButton button = (JButton) humanButtons.getComponents()[q];
			button.setEnabled(enabled[q]);
		}
	}
}
