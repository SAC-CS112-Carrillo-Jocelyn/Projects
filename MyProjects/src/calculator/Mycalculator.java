package calculator;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Mycalculator {

	public static void main(String[] args) {
		// Declare
		Scanner input = new Scanner(System.in);
		double a=0, b=0, c = 0;
		char oper='a';
		
		// Start
		String userInput= new String( JOptionPane.showInputDialog
				("Simple Calculator\n Enter your equation: "));
		
		for(int i=0; i<userInput.length(); i++)
		{
			if(userInput.charAt(i)=='+'||userInput.charAt(i)=='-'
					|| userInput.charAt(i)=='*'|| userInput.charAt(i)=='/'
					|| userInput.charAt(i)=='^')
			{
				oper=userInput.charAt(i);
				//1st we make an array called parts out of userInput which we split
				//along the oper character. ex. 3 + 2 gets split into parts[0]="3" & parts[1]="2"
				//We then convert those strings to doubles which we assign to the 
				//1st & 2nd #s tat we will do calculations with
				String[] parts = userInput.split("\\"+Character.toString(oper)); 
				a =Double.parseDouble(parts[0].trim()); //String.trim() --gets rid of any spaces
				b =Double.parseDouble(parts[1].trim()); //Double.parseDouble(String)--converts String to Double
				
				//System.out.printf("%f %s %f", a,oper,b);//Debug
			}
		}
		// Calculate
		switch (oper) {
		case '+':
			c = a + b;
			break;
		case '-':
			c = a - b;
			break;
		case '*':
			c = a * b;
			break;
		case '/':
			c = a / b;
			break;
		case '^':
			c = Math.pow(a, b);	
			break;
		default:
			System.out.printf("Invalid Operation. Try Again.");
			System.exit(0);
		}
		// List
		//System.out.printf("%d%s%d = %d", a,oper,b,c);//Debug
		//%.2f <-- the .2 makes it so tat we are only shown 2 places after the decimal														
		String out=String.format(" %.2f %s %.2f = %.2f", a, oper, b, c); 	
		JOptionPane.showMessageDialog(null,out);				 
		input.close();	
	}
}
