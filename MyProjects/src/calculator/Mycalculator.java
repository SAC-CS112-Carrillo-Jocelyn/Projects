package calculator;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Mycalculator {

	public static void main(String[] args) {
		// Declare
		Scanner input = new Scanner(System.in);
		double a =0, b =0, c = 0, base;
		char oper='a';
		
		// Start
		String userInput= new String( JOptionPane.showInputDialog
				("Simple Calculator\n Enter your equation: "));
		
		for(int i=0; i<userInput.length(); i++)
		{
			//System.out.print(userInput.charAt(i));
			if(userInput.charAt(i)=='+'||userInput.charAt(i)=='-'
					|| userInput.charAt(i)=='*'|| userInput.charAt(i)=='/'
					|| userInput.charAt(i)=='^')
			{
				oper=userInput.charAt(i);
				String[] parts = userInput.split(Character.toString(oper));
				a =Double.parseDouble(parts[0].trim());
				b =Double.parseDouble(parts[1].trim());
				
				System.out.print(a+" "+oper+" "+b);//Debug
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
				base=c;
			for(int i=0; i< b; i++){
				base=base*c;
			}		
		default:
			System.out.printf("Invalid Operation. Try Again.");
			System.exit(0);
		}
		// List
		System.out.print(a+" "+oper+" "+b+" "+c);//Debug
		
		String out=String.format(" %d %s %d = %d", a, oper, b, c);
		JOptionPane.showMessageDialog(null,out);
		input.close();
		
	}

}