package cu.cs.cpsc2150.project1;

public class BinaryExpression extends Expression {

	public BinaryExpression() {

	}

	public BinaryExpression(String[] input) {
		// initialize operator/operand strings
		op1 = input[0];
		op2 = input[2];
		operator = input[1];

		// if op1 and is an integer, assign it to op1 value
		// otherwise, get op1's first character's numeric value
		// if that character is a capital letter, look for it in the symbol
		// table and try to assign its value to op1value
		// it that doesn't work, throw exception
		try {
			op1value = Integer.parseInt(op1);
		} catch (Exception ex) {
			int var = (Character.getNumericValue(op1.charAt(0)) - 10);
			if (var >= 0 && var <= 25) 
				op1value = symT.symbols[var].value;
			else 
				throw ex;
		}

		// same process for op2value
		try {
			op2value = Integer.parseInt(op2);
		} catch (Exception ex) {
			int var = (Character.getNumericValue(op2.charAt(0)) - 10);
			if (var >= 0 && var <= 25)
				op2value = symT.symbols[var].value;
			else
				throw ex;
		}
	}

	// evaluate expression based on operator
	public void evaluate() {
		switch (operator) {
		case "+":
			result = (op1value + op2value);
			break;
		case "-":
			result = (op1value - op2value);
			break;
		case "*":
			result = (op1value * op2value);
			break;
		default:
			result = (op1value / op2value);
			break;
		}
	}

	// print expression
	public String toStringWithEvaluation() {
		return (op1value + " " + operator + " " + op2value + " = " + result);
	}

}