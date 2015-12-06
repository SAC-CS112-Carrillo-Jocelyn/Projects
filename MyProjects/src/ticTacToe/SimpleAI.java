package ticTacToe;
import java.util.Random;

public class SimpleAI {
	private TicTacToe a;
	public SimpleAI(TicTacToe ttt){
		a = ttt;
	}
	
	int aiGuess;
	//ai generates a random # between 1-9 to play
	public void move(){
		Random move = new Random();
		//Will keep creating a random # till 
		//we generate a valid # to put into XorO
		do{
			aiGuess = (move.nextInt(9)+1);
			System.out.println(a.getTurn() + aiGuess);
		}while(!a.validMove(aiGuess));
		a.XorO(aiGuess);
	}
	
}
