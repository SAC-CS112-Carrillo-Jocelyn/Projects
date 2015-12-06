package ticTacToe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Contains all logic for TicTacToe game!
public class TicTacToe {
	// Array is autofilled with null, when values are not assigned.
	// int (default is 0)
	
	// array used to put in value which is then added to check if X or O wins
	private int[] state = new int[9];
	//# of times X & O wins
	private int Xwins, Owins;
	//if true, X turn. if false, O turn.
	private boolean xTurn;
	//game in progress
	private boolean gameActive;
	//AI
	private SimpleAI comX, comO;
	
	//labels that are displayed with values of X/Owins and xTurn
	private JLabel xwin, owin, turn;
	
	//gets value so tat others can't modify it
	public boolean isGameActive(){
		return gameActive;
	}
	public void setXLabel(JLabel Xlabel){
		xwin=Xlabel;
	}
	public void setOLabel(JLabel Olabel){
		owin=Olabel;
	}
	public void setTurnLabel(JLabel Turnlabel){
		turn=Turnlabel;
	}
	public String getTurn(){
		if(xTurn)
			return " X turn: ";
		else
			return " O turn: ";
	}
	
	//the people listening to event
	private List<ActionListener> gameStateChanged = new ArrayList<ActionListener>();
	//this is method used to add new listeners
	public void addGameStateChangedListener(ActionListener listener){
		gameStateChanged.add(listener);
	}
	//Method used to fire event, notify listeners of a change. Here specifically gameStateChange
	private void NotifyGameStateChanged(){
		//like for each loop, brah like like duh
		//so tells each listener the event has occurred,(source of event, eventID(we have none so we put 0), and event name)
		for (ActionListener listener : gameStateChanged){
			listener.actionPerformed(new ActionEvent(this, 0, "GameStateChanged"));
		}
	}
	
	//Made MoveListener Interface because I need to pass in a position & text
	//and an ActionListener don't cut it
	private List<MoveListener> moveMade = new ArrayList<MoveListener>(); //Make list to hold subscribed listeners
	//Method to subscribe new listeners to this event
	public void addMoveMadeListener(MoveListener moveListener) {
		moveMade.add(moveListener);
	}
	//Method to notify listeners of the event
	private void NotifyMoveMade(int position, String text){
		for(MoveListener moveListener : moveMade){
			moveListener.moveMade(new MoveEvent(position, text));
		}
	}
	
	//Method that starts AI stuff
	public void aiActivate(){
		comX = new SimpleAI(this);
		comO = new SimpleAI(this);
		comMove();
		
	}
	//computer takes move
	public void comMove(){
		if (xTurn) {
			comX.move();
		}
		else{
			comO.move();
		}
	}
	//Method takes in button pressed and button# then assigns X or O
	//to button, depending on whose turn it is, but only if button is empty.
	//The arrays state & button then record the move.
	public void XorO(int position) {
		gameActive = true;
		NotifyGameStateChanged();
		
		if(!validMove(position)){
			return;
		}
		if(xTurn){
			state[position-1] = 1;
			NotifyMoveMade(position, "X");
		}
		else{
			state[position-1] = 4;
			NotifyMoveMade(position, "O");
		}
		xTurn=!xTurn;
		String t = (xTurn)? "X":"O";
		turn.setText(t);
		CheckVictory();
	
		//if we got AI make another AImove
		if(comX!=null && comO !=null){
			comMove();
		}
	}
	//move is valid if that specific position is not taken,
	//indicated by state[position]
	public boolean validMove(int v){
		if(v>9 || v<1){
			return false;
		}
		if(state[v-1]==0){
			return true;
		}
		else
			return false;
	}
	
	/*
	Checks array state to see if someone has won.
	The winner is found by adding every possible win combination.
	In the method XorO we assign 1 to the specific state# that is 
	equivalent to the button# if it is X and 4 if it is O.
	These values are then added in every possible direction and if the 
	added values are 3 then we know we have 3 X's in a row and X won. 
	Likewise if the added values are 12 then we know we have 3 O's in a
	row. If there is no win then we check for a draw and we know that if the 
	values are 6 or greater then we know at least 3 moves have been made so 
	the row is full. We check every possible combination before calling it a draw.
	 */
	private void CheckVictory(){
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
	
	//Displays "XWins!"
	private void XVictory(){
		Xwins++;
		xwin.setText(""+Xwins);
		JOptionPane.showMessageDialog(null, "Xwin!");
		Clear();
	}
	//Displays "OWins!"
	private void OVictory(){
		Owins++;
		owin.setText(""+Owins);
		JOptionPane.showMessageDialog(null, "Owin!");
		Clear();
	}
	//Displays "Draw!"
	private void Draw(){
		JOptionPane.showMessageDialog(null, "Draw!");
		Clear();
	}
	
	//Clears arrays and resets buttons to empty
	private void Clear(){
		state = new int[9];
		comX = null;
		comO = null;
		gameActive = false;
		NotifyGameStateChanged();
		
	}
}