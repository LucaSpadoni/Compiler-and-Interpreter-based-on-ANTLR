package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class IdNode implements Node {
	private String id;
	private STentry sTentry;
	private int nesting;
	private String offset;

	public IdNode(String _id) {
		id = _id ;
	}
  
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;
		sTentry = ST.lookup(id);

		// Checks if the ID is either not declared or initialized (or both)
		if (sTentry == null)
			errors.add(new SemanticError("Id " + id + " not declared"));
		else if (!sTentry.isInit())
			errors.add(new SemanticError("Id " + id + " not initialized"));
		else{
			offset = String.valueOf(sTentry.getoffset());
		}

		return errors;
	}
  
	public Type typeCheck(ArrayList<Type> err) {
		// The ID must not be a function
		if (sTentry.gettype() instanceof ArrowType) {
			System.out.println("Wrong usage of function identifier");
			err.add(new ErrorType()); return new ErrorType();
		}
		else
			return sTentry.gettype() ;
	}
  
	public String codeGeneration() {
		String getAR = "";

		for (int i = 0; i < nesting - sTentry.getnesting(); i++)
			getAR += "store T1 0(T1) \n";	// The command is written as many times as it's needed to reach the ID'S nesting level

		return "move AL T1 \n" +
				getAR +
				"subi T1 " + sTentry.getoffset() + " \n" +	// Offset ID
				"store A0 0(T1) \n";
	}

	public String toPrint(String s) {
		return s + "Id: " + id + " at nestlev " + sTentry.getnesting() + "\n";
	}
  
}  