package ticTacToe;
//Holds event data for moveMade
public class MoveEvent{
	
	private int a;
	private String b;
	
	public MoveEvent(int position, String text){
		a = position;
		b = text;
	}
	
	public int getPosition(){
		return a;
	}
	
	public String getText(){
		return b;
	}

}
