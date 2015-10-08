package ticTacToe;

import java.awt.EventQueue;

public class Program {
	/**
	 * Launch the application.
	 * main method we don't care about...
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe a = new TicTacToe();
					Form window = new Form(a);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
