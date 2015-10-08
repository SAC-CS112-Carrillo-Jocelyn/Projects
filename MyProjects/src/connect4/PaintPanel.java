package connect4;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintPanel extends JPanel {
	private static final long serialVersionUID = 6011762716581473787L;
	
	private final Color BOARD_COLOR = hex2Color("#5900AB");
	private final Color PLAYER_1_COLOR = hex2Color("#E90084");
	private final Color PLAYER_2_COLOR = hex2Color("#00A4E9");
	private final Color PLAYER_1_BORDER_COLOR = hex2Color("#980056");
	private final Color PLAYER_2_BORDER_COLOR = hex2Color("#00628B");
	
	private Engine engine;
	private Timer animationTimer;
	private Timer victoryTimer;
	private int[][] previousBoard;
	private Point currentDroppingCoinPosition;
	private boolean isDroppingCoin;
	private boolean isPlayerOnesTurn;

	public PaintPanel(Engine engine){
		this.engine = engine;
		engine.addStateChangedListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		engine.addCoinDroppingListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] newBoard = engine.getBoard();
				previousBoard = new int[newBoard.length][newBoard[0].length];

				// We have to copy the board to be able to draw the previous board state while animating towards
				// the next. This manual hard copy is necessary because the getBoard method returns a reference
				// to the engine's board, which will be updated after this event call. If we don't copy the board,
				// we'll receive the update in our previousBoard, because they reference the same instance.
				for (int x = 0; x < newBoard.length; x++){
					for (int y = 0; y < newBoard[x].length; y++){
						previousBoard[x][y] = newBoard[x][y];
					}
				}

				currentDroppingCoinPosition = getCurrentCoinPosition();
				isPlayerOnesTurn = engine.isPlayerOnesTurn();
				isDroppingCoin = true;
			}
		});
		engine.addVictoryListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				victoryTimer.start();
			}
		});
		
		animationTimer = new Timer(16, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// We only need to redraw if we're animating.
				if (isDroppingCoin){
					// Shift the coin down with the predefined speed. We do this in the timer, not the paint method,
					// because the paint method can be called any number of times, and will affect the animation speed
					// if called too often. The repaint is called when e.g. the mouse is moved over the board.
					currentDroppingCoinPosition.y += engine.COIN_DROP_SPEED;
					repaint();
				}
			}
		});
		animationTimer.start();
		
		victoryTimer = new Timer(3000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.clear();
				victoryTimer.stop();
				repaint();
			}
		});

		this.addMouseMotionListener(engine);
		this.addMouseListener(engine);
	}
	
	public Point getCurrentCoinPosition(){
		Point point = engine.getCurrentCoinPosition();
		return new Point(point.x * engine.TILE_WIDTH + engine.COIN_PADDING_X, engine.COIN_PADDING_Y);
	}
	
	public static Color hex2Color(String hexColor) {
	    return new Color(Integer.valueOf( hexColor.substring( 1, 3 ), 16 ),
	    				 Integer.valueOf( hexColor.substring( 3, 5 ), 16 ),
	    				 Integer.valueOf( hexColor.substring( 5, 7 ), 16 ) );
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// It's actually a Graphics2D, because it's 2D, and we need some special properties on this thing.
		Graphics2D g2d = (Graphics2D)g;

		// Make it look smooth.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw using the previous board if we're animating a coin drop, otherwise we're accidentally showing
        // the destination of the coin already.
		int[][] board = previousBoard != null ? previousBoard : engine.getBoard();
		
		// Draw the coins first. The board should overlay them so that the coin goes "behind" it. The current
		// coin should also go behind the board. The victory drawing should go on top of the board.
		DrawBoardState(g, g2d, board);
		DrawCurrentCoin(g, g2d, board);
		DrawBoard(g, g2d, board);
		DrawVictory(g, g2d);
	}
	
	private void DrawBoardState(Graphics g, Graphics2D g2d, int[][] board){
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[x].length; y++) {
				int nodeValue = board[x][y];
				
				if (nodeValue != 0){
					g.setColor(nodeValue > 0 ? PLAYER_1_COLOR : PLAYER_2_COLOR);
					g.fillOval(x * engine.TILE_WIDTH + engine.COIN_PADDING_X, y * engine.TILE_HEIGHT + engine.COIN_PADDING_Y + engine.TILE_HEIGHT, engine.COIN_WIDTH, engine.COIN_HEIGHT);
					g.setColor(nodeValue > 0 ? PLAYER_1_BORDER_COLOR : PLAYER_2_BORDER_COLOR);
					g2d.setStroke(new BasicStroke(4));
					g.drawOval(x * engine.TILE_WIDTH + engine.COIN_PADDING_X, y * engine.TILE_HEIGHT + engine.COIN_PADDING_Y + engine.TILE_HEIGHT, engine.COIN_WIDTH, engine.COIN_HEIGHT);
				}
			}
		}
	}
	
	private void DrawCurrentCoin(Graphics g, Graphics2D g2d, int[][] board){
		// Don't draw the current coin state if there is a victory, unless we're still animating
		if (!isDroppingCoin && (engine.isPlayerOneVictory() || engine.isPlayerTwoVictory())){
			return;
		}
		
		Point currentCoinPosition = getCurrentCoinPosition();
		boolean isPlayerOnesTurn = engine.isPlayerOnesTurn();
		
		if (isDroppingCoin){
			currentCoinPosition = currentDroppingCoinPosition;
			isPlayerOnesTurn = this.isPlayerOnesTurn;
			
			// Find the position the coin should land on. We should probably do this one time...
			int currentRow = (int)currentDroppingCoinPosition.y / engine.TILE_HEIGHT;
			int destinationRow = engine.getDestinationRow();
			
			// If we've reached our destination row, lock the coin in and stop animating it.
			if (currentRow >= destinationRow){
				previousBoard = null;
				currentDroppingCoinPosition = null;
				isDroppingCoin = false;
				repaint();
			}
		}

		g.setColor(isPlayerOnesTurn ? PLAYER_1_COLOR : PLAYER_2_COLOR);
		g.fillOval(currentCoinPosition.x, currentCoinPosition.y, engine.COIN_WIDTH, engine.COIN_HEIGHT);
		g.setColor(isPlayerOnesTurn ? PLAYER_1_BORDER_COLOR : PLAYER_2_BORDER_COLOR);
		g2d.setStroke(new BasicStroke(4));
		g.drawOval(currentCoinPosition.x, currentCoinPosition.y, engine.COIN_WIDTH, engine.COIN_HEIGHT);
	}
	
	private void DrawBoard(Graphics g, Graphics2D g2d, int[][] board) {
		// To "subtract" the holes for the coins, we need a buffer image to apply an alpha composite.
		BufferedImage bufferImage = new BufferedImage(340, 310, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gBuffer = bufferImage.createGraphics();

		// Make it look smooth.
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the board without the holes first.
		gBuffer.setColor(BOARD_COLOR);
		gBuffer.fillRoundRect(0, 34, 340, 310, 10, 10);
		
		// Set up "clearing" the buffer image. This only works on the buffer image, not on the panel's Graphics. Not sure why.
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f);
		gBuffer.setComposite(composite);
		
		// For each coin slot, clear the slot by "drawing" nothing.
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[x].length; y++) {
				gBuffer.fillOval(x * engine.TILE_WIDTH + engine.COIN_PADDING_X, y * engine.TILE_HEIGHT + engine.COIN_PADDING_Y + engine.TILE_HEIGHT, engine.COIN_WIDTH, engine.COIN_HEIGHT);
			}
		}
		
		// Draw the buffer image to the panel.
		g.drawImage(bufferImage, 0, 0, null);
		
		// Dispose of Graphics we have created or obtained ourselves. The panel's Graphics aren't ours to dispose.
		gBuffer.dispose();
	}
	
	private void DrawVictory(Graphics g, Graphics2D g2d){
		// Don't draw a victory if we're still animating.
		if (isDroppingCoin){
			return;
		}
		
		if (engine.isPlayerOneVictory()){
			g.setColor(PLAYER_1_BORDER_COLOR);
			g.fillRect(34, 130, 280, 70);
			g.setColor(PLAYER_1_COLOR);
			g.setFont(new Font("default", Font.BOLD, 40));
			g.drawString("Player 1 won!", 50, 180);
		}
		else if (engine.isPlayerTwoVictory()){
			g.setColor(PLAYER_2_BORDER_COLOR);
			g.fillRect(34, 130, 280, 70);
			g.setColor(PLAYER_2_COLOR);
			g.setFont(new Font("default", Font.BOLD, 40));
			g.drawString("Player 2 won!", 50, 180);
		}
	}
}
