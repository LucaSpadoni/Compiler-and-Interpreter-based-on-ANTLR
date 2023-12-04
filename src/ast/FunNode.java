package ast;

import evaluator.SimpLanlib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class FunNode implements Node {
	private String id;
	private Type returntype ; 
	private ArrayList<ParNode> parlist ; 
	private ArrayList<Node> declist ; 
	private Node body ;
	private ArrowType funType ;
	private int nesting ;
	private String flabel ;
  
	public FunNode(String _id, Type _type, ArrayList<ParNode> _parlist, Node _body) {
		id = _id ;
		returntype = _type;
		parlist = _parlist ;
		body = _body ;
	}

	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
		nesting = _nesting ;

		// There's already a function called with the same name in the same scope
		if (ST.lookup(id) != null)
			errors.add(new SemanticError("Identifier " + id + " already declared"));
		else {
			ArrayList<Type> paramTypes = new ArrayList<>();
			ArrayList<Type> err = new ArrayList<>();

			// Checks the types of the function's parameters
			for (ParNode param : parlist) {
				paramTypes.add(param.typeCheck(err));
			}

			funType = new ArrowType(paramTypes, returntype); 	// Return type of the function
			flabel = SimpLanlib.freshFunLabel();   // Creates a new function label

			ST.insert(id, funType, nesting, flabel);  		// Adds the function (id, type, nesting level and label) to the ST
			int funScopeLevel = ST.addScope(new HashMap<String, STentry>());  	// Increases the scope level

			// Checks the semantic of the function parameters
			for (ParNode param : parlist) {
				errors.addAll(param.checkSemantics(ST, funScopeLevel));
			}

			ST.increaseoffset();    	// We increase the offset by 1 for the return value
			errors.addAll(body.checkSemantics(ST, funScopeLevel)); 	// Checks the semantic of the function body
			ST.removeScope(); 	// We exit the scope
		}

		return errors;
	}
  
 	public Type typeCheck(ArrayList<Type> err) {
		Type bodyType = body.typeCheck(err);

		if (bodyType.getClass().equals(returntype.getClass()))	// The return type of the function corresponds to the body type
			return bodyType;
		else {
			System.out.println("Wrong return type for function " + id);
			err.add(new ErrorType()); return new ErrorType();
		}  
  	}
  
  public String codeGeneration() {
	    SimpLanlib.putCode(
	    			flabel + ":\n"
	    			+ "pushr RA \n"
	    			+ body.codeGeneration()
	    			+ "popr RA \n"
	    			+ "addi SP " + parlist.size() + "\n"
	    			+ "pop \n"
	    			+ "store FP 0(FP) \n"
	    			+ "move FP AL \n"
	    			+ "subi AL 1 \n"
	    			+ "pop \n"
	    			+ "rsub RA \n" 
	    		);
	    
		return "push " + flabel + "\n";
  }
  
  public String toPrint(String s) {
		String parstr = "";
	  	String declstr = "";

		for (Node par : parlist){
			parstr += par.toPrint(s);
		}

		if (declist != null)
		  for (Node dec : declist)
		    declstr += dec.toPrint(s + " ");

	    return s + "Fun: " + id + "\n"
			   + parstr
		   	   + declstr
		   	   + "\n" 
	           + body.toPrint(s + "  ");
	  }
}  