package ast;

import org.stringtemplate.v4.ST;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class DecNode implements Node {
	private String id;
	private Node type;
	private int nesting;

	public DecNode(String _id, Node _type) {
		id = _id ;
		type = _type ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
   		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
  		nesting = _nesting;

		// There's already a variable called with the same name in the same scope
		if (ST.top_lookup(id))
        	errors.add(new SemanticError("Var " + id + " already declared"));
        else {
			ST.insert(id, (Type) type, nesting, "");	// The variable is added to the ST
		}

        return errors;
	}
  
	public Type typeCheck(ArrayList<Type> err) {
		// Variables cannot be of void type
		if (type instanceof VoidType) {
			System.out.println("Variable " + id + " cannot be of void type");
			err.add(new ErrorType()); return new ErrorType();
		}
		else {
			return (Type) type;
		}
	}
  
	public String codeGeneration() {
		String offset = "1";

		return "subi SP " + offset + "\n";
	}  
    
	public String toPrint(String s) {
		return s + "Var: " + id + type.toPrint(" ") + "\n";
	}
}  