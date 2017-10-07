package cu.cs.cpsc2150.project1;

public class SymbolTable {

	protected VariableExpression[] symbols;

	public SymbolTable() {
		symbols = new VariableExpression[26];
	}

	// assign the variable expression v to the index in the symbols array
	// corresponding to v's name's numeric value 
	// (for example, X goes in symbols[23])
	public void newSymbol(VariableExpression v) {
		symbols[Character.getNumericValue(v.name) - 10] = v;
	}

}
