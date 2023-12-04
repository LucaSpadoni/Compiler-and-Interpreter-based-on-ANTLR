package ast;

import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class VoidNode implements Node {
	private Integer val;

	public VoidNode(Integer _val) {
		val = _val ;
	}
	
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
 	 	  return new ArrayList<SemanticError>();
	}
 	  
	public Type typeCheck(ArrayList<Type> err){
		return new VoidType();
	} 
  
	public String codeGeneration() {
		return "\n";
	}

	public String toPrint(String s) {
	    return s + " \n";
	}
}  