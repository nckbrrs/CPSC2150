package cu.cs.cpsc2150.project1;

public class VariableExpression extends Expression {

	protected char name;
	protected int value;

	public VariableExpression() {

	}

	public VariableExpression(String[] input) {
		// if variable name is longer than one character, print error message
		if (input[0].length() == 1)
			name = input[0].charAt(0);
		else
			System.out.println("Error: variable name too long. Try again.");

		// if value on the right side of the := operator is not an integer,
		// print error message
		try {
			value = Integer.parseInt(input[2]);
		} catch (Exception ex) {
			System.out.println("Error: invalid variable initialization. Try again.");
			throw ex;
		}
	}

	// add this expression to the symbol table
	public void evaluate() {
		symT.newSymbol(this);
	}

	// print expression
	public String toStringWithEvaluation() {
		return (name + " = " + value);
	}
}
