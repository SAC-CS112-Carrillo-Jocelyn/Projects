package connect4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int TILE_WIDTH = 34;
	private final int TILE_HEIGHT = 34;
	private final int COIN_WIDTH = 28;
	private final int COIN_HEIGHT = 28;
	private final int COIN_PADDING_X = 2;
	private final int COIN_PADDING_Y = 2;
	private final Color BOARD_COLOR = Color.getColor("#5900AB");
	private final Color PLAYER_1_COLOR = Color.getColor("#E90084");
	private final Color PLAYER_2_COLOR = Color.getColor("#00A4E9");
	private final Color PLAYER_1_BORDER_COLOR = Color.getColor("#E90084");
	private final Color PLAYER_2_BORDER_COLOR = Color.getColor("#00A4E9");
	private Engine engine;
	
	public PaintPanel(Engine engine){
		this.engine = engine;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int[][] board = engine.getBoard();
		
		g.setColor(Color.green);
		
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] == 0){
					g.fillArc(x * TILE_WIDTH + COIN_PADDING_X, y * TILE_HEIGHT + COIN_PADDING_Y + TILE_HEIGHT, COIN_WIDTH, COIN_HEIGHT, 0, 360);
					g.drawArc(x * TILE_WIDTH + COIN_PADDING_X, y * TILE_HEIGHT + COIN_PADDING_Y + TILE_HEIGHT, COIN_WIDTH, COIN_HEIGHT, 0, 360);
				}
			}
		}
	}
}
