package ticTacToe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Font;

public class Form {

	private JFrame frmTixtaxtows;
	private TicTacToe a;
	
	public JFrame getFrame() {
		return frmTixtaxtows;
	}

	/**
	 * Create the application.
	 */
	public Form(TicTacToe yourcupofwater) {
		a=yourcupofwater;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTixtaxtows = new JFrame();
		frmTixtaxtows.setTitle("NixNaxNoes");
		frmTixtaxtows.setBounds(100, 100, 450, 300);
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
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.XorO(button_2,1);
				
			}
		});
		panel.add(button_2);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.XorO(button_1,2);

			}
		});
		panel.add(button_1);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button, 3);
				
			}
		});
		panel.add(button);
		
		JLabel label_13 = new JLabel("");
		panel.add(label_13);

		// Row 4
		JLabel label_15 = new JLabel("");
		panel.add(label_15);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button_3,4);
				
			}
		});
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button_4,5);
				
			}
		});
		panel.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button_5,6);
				
			}
		});
		panel.add(button_5);
		
		JLabel label_16 = new JLabel("");
		panel.add(label_16);
		
		// Row 5
		JLabel label_17 = new JLabel("");
		panel.add(label_17);
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button_6,7);
				
			}
		});
		panel.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button_7,8);
		
			}
		});
		panel.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.XorO(button_8,9);
			}
		});
		panel.add(button_8);
		
		JLabel label_19 = new JLabel("");
		panel.add(label_19);
		
		// Row 7
		JLabel label_20 = new JLabel("");
		panel.add(label_20);
		
		JLabel label_21 = new JLabel("");
		panel.add(label_21);
		
		JLabel label_22 = new JLabel("");
		panel.add(label_22);
		
		JLabel label_23 = new JLabel("");
		panel.add(label_23);
		
		JLabel label_24 = new JLabel("");
		panel.add(label_24);
	}
}
