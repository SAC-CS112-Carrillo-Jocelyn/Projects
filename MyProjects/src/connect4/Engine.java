package connect4;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Engine implements MouseMotionListener, MouseListener {
	public final int TILE_WIDTH = 34;
	public final int TILE_HEIGHT = 34;
	public final int COIN_WIDTH = 28;
	public final int COIN_HEIGHT = 28;
	public final int COIN_PADDING_X = 2;
	public final int COIN_PADDING_Y = 2;
	public final int COIN_DROP_SPEED = 10;
	
	private boolean isPlayerOnesTurn;
	private boolean isPlayerOneVictory;
	private boolean isPlayerTwoVictory;
	private int[][] board;
	private Point currentCoinPosition = new Point(0, 0);
	private int destinationRow;

    private List<ActionListener> stateChangedListeners = new ArrayList<ActionListener>();
    private List<ActionListener> coinDroppingListeners = new ArrayList<ActionListener>();
    private List<ActionListener> victoryListeners = new ArrayList<ActionListener>();
	
	public boolean isPlayerOnesTurn(){
		return isPlayerOnesTurn;
	}
	
	public boolean isPlayerTwosTurn(){
		return !isPlayerOnesTurn;
	}
	
	public boolean isPlayerOneVictory(){
		return isPlayerOneVictory;
	}
	
	public boolean isPlayerTwoVictory(){
		return isPlayerTwoVictory;
	}

	public int[][] getBoard(){
		return board;
	}

	public Point getCurrentCoinPosition(){
		return currentCoinPosition;
	}
	
	public int getDestinationRow(){
		return destinationRow;
	}
	
	public void addStateChangedListener(ActionListener listener){
		stateChangedListeners.add(listener);
	}
	
	public void addCoinDroppingListener(ActionListener listener){
		coinDroppingListeners.add(listener);
	}
	
	public void addVictoryListener(ActionListener listener){
		victoryListeners.add(listener);
	}
	
	public void clear(){
		// The more to the right, the more it's down.
		// The more to the top, the more it's left.
		board = new int[][]{
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, },
		};
		
		isPlayerOneVictory = false;
		isPlayerTwoVictory = false;
	}
	
	private void checkVictory(){
		// We skip 3 indices so that we can always do - 3. Then, we loop through all slots starting from the 4th slot, and
		// backtrack whether or not there is a horizontal, vertical or diagonal combination that sums to 4, be it negative
		// or positive. If so, then the player who owns that sign (negative or positive) has won the game. Since this check
		// method is called after every move, there can never be two player victories.
		for (int x = 3; x < board.length; x++){
			for (int y = 3; y < board[x].length; y++){
				int horizontal = board[x - 3][y] + board[x - 2][y] + board[x - 1][y] + board[x][y];
				int vertical = board[x][y - 3] + board[x][y - 2] + board[x][y - 1] + board[x][y];
				int diagonalLeftToRight = board[x - 3][y - 3] + board[x - 2][y - 2] + board[x - 1][y - 1] + board[x][y];
				int diagonalRightToLeft = board[x][y - 3] + board[x - 1][y - 2] + board[x - 2][y - 1] + board[x - 3][y];
				
				isPlayerOneVictory = horizontal == 4 || vertical == 4 || diagonalLeftToRight == 4 || diagonalRightToLeft == 4;
				isPlayerTwoVictory = horizontal == -4 || vertical == -4 || diagonalLeftToRight == -4 || diagonalRightToLeft == -4;
				
				if (isPlayerOneVictory || isPlayerTwoVictory){
					NotifyVictory();
					return;
				}
			}
		}
	}
	
	private void NotifyStateChanged(){
		for (ActionListener listener : stateChangedListeners){
			listener.actionPerformed(new ActionEvent(this, 0, "StateChanged"));
		}
	}
	
	private void NotifyCoinDropping(){
		for (ActionListener listener : coinDroppingListeners){
			listener.actionPerformed(new ActionEvent(this, 0, "CoinDropping"));
		}
	}

	private void NotifyVictory(){
		for (ActionListener listener : victoryListeners){
			listener.actionPerformed(new ActionEvent(this, 0, "Victory"));
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// Don't need this, stupid Java.
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Don't do anything if there's a victory, until a reset.
		if (isPlayerOneVictory || isPlayerTwoVictory){
			return;
		}
		
		// We use modulo to round the position of the coin so that it hovers right above one of the columns.
		Point currentPoint = arg0.getPoint();
		currentCoinPosition = new Point((int)(currentPoint.x / TILE_WIDTH), 0);

		NotifyStateChanged();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Don't do anything if there's a victory, until a reset.
		if (isPlayerOneVictory || isPlayerTwoVictory){
			return;
		}
		
		// Find the position the coin should land on.
		int currentColumn = currentCoinPosition.x;
		destinationRow = -1;

		// Loop over the coin's column and find the destination row. Stop looping if we've found an
		// occupied slot.
		for (int y = 0; y < board[currentColumn].length; y++){
			if (board[currentColumn][y] != 0) {
				//destinationRow = y - 1;
				break;
			}
			
			destinationRow++;
		}
		
		// If the entire column is full, we can't drop the coin there.
		if (destinationRow < 0){
			return;
		}
		
		// Notify listeners that the coin is dropping. They can perform their own logic, like animating the
		// coin while drawing the previous board state, until the coin has finished dropping.
		NotifyCoinDropping();
		
		// Change the board state to reflect the changes.
		board[currentColumn][destinationRow] = isPlayerOnesTurn() ? 1 : -1;

		// Check if the current move won the game.
		checkVictory();
		
		// Switch players, but only after checking for a victory. We wouldn't want to attribute the victory
		// to the wrong player.
		isPlayerOnesTurn = !isPlayerOnesTurn;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Don't need this, stupid Java.
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Don't need this, stupid Java.
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Don't need this, stupid Java.
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Don't need this, stupid Java.
	}
}
