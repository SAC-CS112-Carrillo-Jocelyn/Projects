package connect4;

public class Engine {
	private int[][] board;
	
	public int[][] getBoard(){
		return board;
	}
	
	public void clear(){
		//board = new int[10][8];
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
	}
}
