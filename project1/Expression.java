package cu.cs.cpsc2150.project1;

public abstract class Expression {

	protected String op1, op2, operator;
	protected int op1value, op2value, result;
	protected static SymbolTable symT = new SymbolTable();

	public abstract void evaluate();
	public abstract String toStringWithEvaluation();

	// parse user input into individual strings
	// look at operator and decide which type of expression to create
	public static Expression parseInput(String userInput) {
		Expression exp = null;
		String[] inputElems = userInput.split("\\s+");
		if (inputElems.length > 3) return null;
		try {
			switch (inputElems[1]) {
			case "+":
			case "-":
			case "*":
			case "/":
				exp = new BinaryExpression(inputElems);
				break;
			case ":=":
				exp = new VariableExpression(inputElems);
				break;
			default:
				exp = null;
				System.out.println("Error: bad operator. Try again.");
				break;
			}
		} catch (Exception ex) {
			// check if user input is a single alphabetic character.
			// if so, print the value of its symbol in symT. otherwise, throw
			// exception.
			int var = (Character.getNumericValue(inputElems[0].charAt(0)) - 10);
			if (var >= 0 && var <= 25)
				System.out.println(Expression.symT.symbols[var].toStringWithEvaluation());
			else
				throw ex;
		}
		return exp;
	}

}