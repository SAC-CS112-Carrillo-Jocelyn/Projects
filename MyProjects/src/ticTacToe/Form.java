package ticTacToe;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.UIManager;

public class Form {

	private JFrame frmTixtaxtows;
	private TicTacToe a;

	// edited to display X or O to user
	JButton[] buttons = new JButton[9];
	private JRadioButton pVSpRadio = new JRadioButton("P vs P");
	private JRadioButton cVScRadio = new JRadioButton("C vs C");
	private JButton comStart = new JButton("Start");
	
	public JFrame getFrame() {
		return frmTixtaxtows;
	}

	/**
	 * Create the application.
	 */
	public Form(TicTacToe tictactoe) {
		a=tictactoe;

		a.addGameStateChangedListener(new ActionListener() {
			//this is called whenever state is changed in TicTacToe
			public void actionPerformed(ActionEvent arg0) {
				// Disable/Enables buttons depending on gameState
				pVSpRadio.setEnabled(!a.isGameActive());
				cVScRadio.setEnabled(!a.isGameActive());
				comStart.setEnabled(!a.isGameActive());
				
				if (!a.isGameActive()){
					for (int i=0; i<buttons.length; i++){
						if(buttons[i]!=null){
							buttons[i].setText("");
						}
					}
				}
			}
		});
		
		//TicTacToe tells Form a move has been made & the Form reflects these changes
		//on the UI to notify user--
		//making itself a listener to TicTacToe, more specifically listening for moves
		a.addMoveMadeListener(new MoveListener() 
		{
			public void moveMade(MoveEvent arg0) {
				buttons[arg0.getPosition()-1].setText(arg0.getText());
			}
		});
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTixtaxtows = new JFrame();
		frmTixtaxtows.setTitle("NixNaxNoes");
		frmTixtaxtows.setBounds(100, 100, 450, 363);
		frmTixtaxtows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmTixtaxtows.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		// Row 1
		JLabel label = new JLabel("");
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		panel.add(label_1);
		
		JLabel lblTicTacToe = new JLabel("Tic Tac Toe!");
		lblTicTacToe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblTicTacToe);
		
		JLabel label_2 = new JLabel("");
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		panel.add(label_3);
		
		// Row 2
		JLabel label_5 = new JLabel("");
		panel.add(label_5);
		
		JLabel lblXWins = new JLabel("X wins: ");
		panel.add(lblXWins);
		
		JLabel lbl_Xwins = new JLabel("");
		panel.add(lbl_Xwins);
		a.setXLabel(lbl_Xwins);
		
		JLabel lblOWins = new JLabel("O wins:");
		panel.add(lblOWins);

		JLabel lbl_Owins = new JLabel("");
		panel.add(lbl_Owins);
		a.setOLabel(lbl_Owins);
		
		// Row 2
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		panel.add(label_7);
		
		JLabel lblTurn = new JLabel("Turn:");
		lblTurn.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblTurn);

		JLabel lbl_Turn = new JLabel("O");
		lbl_Turn.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lbl_Turn);
		a.setTurnLabel(lbl_Turn);
		
		JLabel label_9 = new JLabel("");
		panel.add(label_9);
		
		// Row 3
		JLabel label_10 = new JLabel("");
		panel.add(label_10);
				
		JButton button_2 = new JButton("");
		buttons[0] = button_2;
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.XorO(1);
				
			}
		});
		panel.add(button_2);

		JButton button_1 = new JButton("");
		buttons[1] = button_1;
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.XorO(2);

			}
		});
		panel.add(button_1);
		
		JButton button = new JButton("");
		buttons[2] = button;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(3);
				
			}
		});
		panel.add(button);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);

		// Row 4
		JLabel label_15 = new JLabel("");
		panel.add(label_15);
		
		JButton button_3 = new JButton("");
		buttons[3] = button_3;
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(4);
				
			}
		});
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		buttons[4] = button_4;
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(5);
				
			}
		});
		panel.add(button_4);
		
		JButton button_5 = new JButton("");
		buttons[5] = button_5;
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(6);
				
			}
		});
		panel.add(button_5);
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16);
		
		// Row 5
		JLabel label_17 = new JLabel("");
		panel.add(label_17);
		
		JButton button_6 = new JButton("");
		buttons[6] = button_6;
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(7);
				
			}
		});
		panel.add(button_6);
		
		JButton button_7 = new JButton("");
		buttons[7] = button_7;
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(8);
		
			}
		});
		panel.add(button_7);
		
		JButton button_8 = new JButton("");
		buttons[8] = button_8;
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(9);
			}
		});
		panel.add(button_8);
		
		JLabel label_19 = new JLabel("");
		panel.add(label_19);
		
		// Row 7
		JLabel label_20 = new JLabel("");
		panel.add(label_20);
		
		//Computer Start Button
		comStart.setVisible(false);
		//Player Radio Button
		panel.add(pVSpRadio);
		pVSpRadio.setSelected(true);
		//Player State Change
		pVSpRadio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cVScRadio.setSelected(!pVSpRadio.isSelected());
			}
		});
		
		JLabel label_21 = new JLabel("");
		panel.add(label_21);
		
		//Computer Radio Button
		panel.add(cVScRadio);
		//Computer State Change
		cVScRadio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				pVSpRadio.setSelected(!cVScRadio.isSelected());
				comStart.setVisible(cVScRadio.isSelected());
				
			}
		});	

		JLabel label_22 = new JLabel("");
		panel.add(label_22);
		
		JLabel label_23 = new JLabel("");
		panel.add(label_23);
		
		JLabel label_24 = new JLabel("");
		panel.add(label_24);
		
		JLabel label_25 = new JLabel("");
		panel.add(label_25);
		
		//CompStart button is pressed
		comStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.aiActivate();
			}
		});
		comStart.setBackground(UIManager.getColor("Button.background"));
		panel.add(comStart);
	}
}
