import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationWindow {

	private Controller controller;
	private JFrame frame;
	private ArrayList<JPanel> playerPanels;
	private JLabel middleText;

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

		createPlayerPanels();
		createMiddleTextArea();

		updateHands(controller.getAllHands());
		setMiddleTextArea(controller.getTextForMiddle());
	}

	/**
	 * Creates the label that goes in the center of the window.
	 */
	private void createMiddleTextArea() {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
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
	private void setMiddleTextArea(String textForMiddle) {
		String changedToHTML = "<html>"
				+ textForMiddle.replaceAll("\n", "<br>") + "</html>";
		middleText.setText(changedToHTML);
	}

	/**
	 * Creates all of the panels that will display the players cards.
	 */
	private void createPlayerPanels() {
		playerPanels = new ArrayList<JPanel>();
		for (int q = 0; q < 4; q++)
			playerPanels.add(new JPanel());
		frame.getContentPane().add(playerPanels.get(0), BorderLayout.SOUTH);
		frame.getContentPane().add(playerPanels.get(1), BorderLayout.WEST);
		frame.getContentPane().add(playerPanels.get(2), BorderLayout.NORTH);
		frame.getContentPane().add(playerPanels.get(3), BorderLayout.EAST);

		for (int q = 0; q < 4; q++) {
			if (q == 0) {
				playerPanels.get(0).setLayout(new GridLayout(1, 0, 0, 0));
				for (int w = 0; w < 5; w++) {
					JButton toAdd = new JButton();
					toAdd.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							JButton buttonPressed = (JButton) arg0.getSource();
							controller.cardPlayed(buttonPressed);
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
		for (int q = 0; q < currentHand.size(); q++) {
			Card currCard = currentHand.get(q);
			String toSet = currCard.toString();
			if (pos == 0)
				((JButton) playerPanels.get(pos).getComponent(q))
						.setText(toSet);
			else {
				((JLabel) playerPanels.get(pos).getComponent(q)).setText(toSet);
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
	private void updateHands(ArrayList<ArrayList<Card>> currentHands) {
		for (int q = 0; q < currentHands.size(); q++)
			updateHands(q, currentHands.get(q));
	}
}
