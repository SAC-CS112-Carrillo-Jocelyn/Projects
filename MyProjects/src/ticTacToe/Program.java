package ticTacToe;
import java.awt.EventQueue;
public class Program {
	/**
	 * Launch the application.
	 * want this separated so it doesn't get messed up...
	 * Java automatically made this when the form was made
	 * I tell it about TicTacToe, the game engine, otherwise
	 * game won't work.
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
