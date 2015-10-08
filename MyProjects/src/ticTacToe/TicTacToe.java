package ticTacToe;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//Contains all logic for TicTacToe game!
public class TicTacToe {
	boolean xTurn;
	// Array is autofilled with null, when values are not assigned.
	// int (default is 0)
	int[] state = new int[9];
	JButton[] buttons = new JButton[9];
	int Xwins, Owins;
	JLabel xwin, owin, turn;
	
	public void setXLabel(JLabel Xlabel){
		xwin=Xlabel;
	}
	public void setOLabel(JLabel Olabel){
		owin=Olabel;
	}
	public void setTurnLabel(JLabel Turnlabel){
		turn=Turnlabel;
	}
	
	
	public void XorO(JButton butt, int position) {
		if(position>9 || position<1){
			JOptionPane.showMessageDialog(null, "Stupid!");
			System.exit(-1);
			return;
		}
		if(butt.getText().equals("")){
			if(xTurn){
				butt.setText("X");
				state[position-1]=1;
				buttons[position-1] = butt;
			}
			else{			
				butt.setText("O");
				state[position-1]=4;
				buttons[position-1] = butt;
			}
			xTurn=!xTurn;
			String t = (xTurn)? "X":"O";
			turn.setText(t);
			CheckVictory();
		}
	}
	
	private void CheckVictory(){
		// 3 % 3 = 0;
		// 3 % 2 = 1;	//Modulus % or Modulo
		// 12 % 4 = 0;
		// 12 % 5 = 2;  //subtracts 5 from 12 as many times as possible
						//without hitting 0, n giving back what's left
		int row1 = state[0]+state[1]+state[2];
		int row2 = state[3]+state[4]+state[5];
		int row3 = state[6]+state[7]+state[8];
		int col1 = state[0]+state[3]+state[6];
		int col2 = state[1]+state[4]+state[7];
		int col3 = state[2]+state[5]+state[8];
		int digL = state[0]+state[4]+state[8];
		int digR = state[2]+state[4]+state[6];
		
		if(row1 == 3 || row2 == 3 || row3 == 3
				|| col1 == 3 || col2 == 3 || col3 == 3
				|| digL == 3 || digR == 3)
			XVictory();
		else if(row1 == 12 || row2 == 12 || row3 == 12
				|| col1 == 12 || col2 == 12 || col3 == 12
				|| digL == 12 || digR == 12)
			OVictory();
		else if(row1 >= 6 && row2 >= 6 && row3 >= 6
				&& col1 >= 6 && col2 >= 6 && col3 >= 6
				&& digL >= 6 && digR >= 6)
			Draw();
	}
	
	private void XVictory(){
		Xwins++;
		xwin.setText(""+Xwins);
		JOptionPane.showMessageDialog(null, "Xwin!");
		Clear();
	}
	private void OVictory(){
		Owins++;
		owin.setText(""+Owins);
		JOptionPane.showMessageDialog(null, "Owin!");
		Clear();
	}
	private void Draw(){
		JOptionPane.showMessageDialog(null, "Draw!");
		Clear();
	}
	private void Clear(){
		state = new int[9];
		for (int i=0; i<buttons.length; i++){
			if(buttons[i]!=null){
				buttons[i].setText("");
			}
		}
		buttons = new JButton[9];
	}
}
