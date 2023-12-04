package ast;
import java.util.ArrayList;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class CallNode implements Node {
	private String id;
	private STentry sTentry;
	private ArrayList<Node> parameters;
	private int nesting;

	public CallNode(String _id, ArrayList<Node> _parameters) {
		id = _id;
		parameters = _parameters ;
	}

	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting;
		sTentry = ST.lookup(id);

		// The function needs to be declared
		if (sTentry != null) {
			if (sTentry.gettype() instanceof ArrowType) {		// The return type needs to be an instance of ArrowType
				for (Node par : parameters)
					errors.addAll(par.checkSemantics(ST, nesting));

				// Checks whether the actual parameters number corresponds to the formal parameters number
				if (((ArrowType) sTentry.gettype()).get_inputtype().size() != parameters.size())
					errors.add(new SemanticError("Function " + id + " was called with the wrong numbers of parameters"));
			}
			else {
				errors.add(new SemanticError("You are treating a function as a variable"));
			}
		}
		else
			errors.add(new SemanticError("Function " + id + " not declared"));

		return errors;
	}

	public Type typeCheck(ArrayList<Type> err) {
		Type _type = sTentry.gettype();

		// If the type of the ID is an instance of ArrowType it means that we are dealing with a function
		if (_type instanceof ArrowType) {
			ArrayList<Type> _partype = ((ArrowType) _type).get_inputtype();

			// Checks whether the number of actual parameters corresponds to the number of formal parameters
			if ( _partype.size() != parameters.size() ) {
				System.out.println("Wrong number of parameters in the invocation of " + id);
				err.add(new ErrorType()); return new ErrorType();
			}
			else {
				boolean ok = true;

				for (int i = 0 ; i < parameters.size() ; i++) {
					Type par_i = (parameters.get(i)).typeCheck(err);

					// Checks whether the type of the i-th actual parameter corresponds to the type of the i-th formal parameter
					if (!(par_i.getClass().equals(_partype.get(i).getClass()))) {
						System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
						ok = false;
					}
				}

				if (ok)
					return ((ArrowType) _type).get_outputtype();
				else
					err.add(new ErrorType()); return new ErrorType();
			}
		}
		else {
			System.out.println("Invocation of a non-function " + id);
			err.add(new ErrorType()); return new ErrorType();
		}
	}

	public String codeGeneration() {
		  String parCode = "";
		  String getAR = "";

		  for (Node parameter : parameters)
			  parCode += parameter.codeGeneration() + "pushr A0\n";	// Codegen for each parameter

		  for (int i = 0; i < nesting - sTentry.getnesting(); i++)
			  getAR += "store T1 0(T1) \n";		// The command is written as many times as it's needed to reach the ID'S nesting level

		  return  "pushr FP \n"			// Loads the frame pointer
				  + "move SP FP \n"
				  + "addi FP 1 \n"		// Saves into the FP the pointer at the address of the loaded fp
				  + "move AL T1\n"		// Goes up the static chain
				  + getAR				// AR format: control_link + access link + parameters + return address + local declarations
				  + "pushr T1 \n"			// Saves into the stack the static access link
				  + parCode 				// Computes the actual parameters with the caller's access link
				  + "move FP AL \n"
				  + "subi AL 1 \n"
				  + "jsub " + sTentry.getlabel() + "\n";
	}

	public String toPrint(String s) {
		String parlstr = "";

		for (Node par : parameters)
			parlstr += par.toPrint(s+"  ");

		return s + "Call: " + id + " at nestlev " + nesting + "\n" + sTentry.gettype().toPrint(s + "  ") + parlstr;
	}
}  