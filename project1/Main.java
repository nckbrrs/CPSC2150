package cu.cs.cpsc2150.project1;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Expression exp;
		String input = "lorem ipsum";

		// create a new expression from the user's input, evaluate that
		// expression, and print it.
		// repeat this until the user inputs the word "quit".
		while (!input.equalsIgnoreCase("quit")) {
			try {
				System.out.print("> ");
				input = reader.readLine();
				exp = Expression.parseInput(input);
				exp.evaluate();
				System.out.println(exp.toStringWithEvaluation());
			} catch (Exception ex) {
				// if exception was not caused by the user entering "quit", print error message
				if (!input.equalsIgnoreCase("quit")) {
					System.out.println("Error: bad input. Try again.");
				}
			}
		}
	}
	
}
