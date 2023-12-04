package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ProgNode implements Node {
	private Node exp;
  
	public ProgNode(Node _exp) {
		exp = _exp ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {		
		return exp.checkSemantics(ST, _nesting);
	}

	public Type typeCheck(ArrayList<Type> err) {
		// The type of the program is simply the type of the expression
		return exp.typeCheck(err);
	}  
  
	public String codeGeneration() {
		return exp.codeGeneration() +
				"halt\n";
	}  
  
	public String toPrint(String s) {
		return "Prog\n" + exp.toPrint("  ");
	}

}  