package ast;

import java.util.ArrayList;
import java.util.HashMap;

import evaluator.SimpLanlib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class IfNode implements Node {
	private Node guard;
	private ArrayList<Node> thenbranch;
    private ArrayList<Node> elsebranch;
    private boolean exp;
	private int nesting;

	public IfNode (Node _guard, ArrayList<Node> _thenbranch, ArrayList<Node> _elsebranch, boolean _exp) {
    	guard = _guard;
    	thenbranch = _thenbranch;
    	elsebranch = _elsebranch;
    	exp = _exp;
  }

   @Override
  public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
	   ArrayList<SemanticError> errors = new ArrayList<SemanticError>(guard.checkSemantics(ST, nesting));
	   nesting = _nesting;

	   // We create a ST for each If branch
	   SymbolTable thenST = new SymbolTable();
	   thenST.setST(ST.getST());	// Creating the ST (as a copy of the original ST)
	   thenST.setOffset(ST.getOffset());	// Setting the offset the same way

	   SymbolTable elseST = new SymbolTable();
	   elseST.setST(ST.getST());	// Creating the ST (as a copy of the original ST)
	   elseST.setOffset(ST.getOffset());	// Setting the offset the same way

	   for (Node t : thenbranch) {
		   errors.addAll(t.checkSemantics(thenST, nesting));
	   }

	   for (Node e : elsebranch) {
		   if (e != null)	// The else branch may be null for ifExp
			   errors.addAll(e.checkSemantics(elseST, nesting));
	   }

	   // The 2 ST are equal
	   if (thenST.equals(elseST)){
		   ST.setST(thenST.getST());
		   ST.setOffset(thenST.getOffset());
	   }
	   // The 2 ST are not equal
	   else{
		   if (thenST.common(elseST) != null){
			   HashMap<String, STentry> common;
			   common = thenST.common(elseST);	// We check the common initializations between the 2 branches

			   for (HashMap<String, STentry> hm : ST.getST()){
				   for (String s : hm.keySet()){
					   for (String s1 : common.keySet()){
						   if (s.equals(s1)){
							   // We initialize the variables that are in common between the original ST and the one resulting from the branches
							   hm.get(s).initVar();
						   }
					   }
				   }
			   }
		   }
	   }

	   return errors;
  }

	@Override
	public Type typeCheck(ArrayList<Type> err) {
		// We need to check the guard and the two branches. If the branch only contains statements then its type is void. On the other hand if
		// we have also got expressions then the type that will result is the type of the expression

		// The If guard has to be an expression that returns a boolean
		if (guard.typeCheck(err) instanceof BoolType) {
			Type thenexp = null;
			Type elseexp = null;

			for (Node tb : thenbranch) {
				thenexp = tb.typeCheck(err);
			}

			for (Node eb : elsebranch) {
				if (eb != null) {    // The else branch may be null for ifExp
					elseexp = eb.typeCheck(err);
				}
			}

			// If exp is true it means that we are dealing with ifExp
			if (exp) {
				if (thenexp.getClass().equals(elseexp.getClass()))
					return thenexp;
				else {
					// The type of the two branches must be the same
					System.out.println("Type Error: Different types between then and else statements");
					err.add(new ErrorType());
					return new ErrorType();
				}
			}
			else
				return new VoidType();
		}
		else {
			System.out.println("Type Error: non-boolean condition in if");
			err.add(new ErrorType()); return new ErrorType();
		}
	}

	public String codeGeneration() {
		String lthen = SimpLanlib.freshLabel();
		String lend = SimpLanlib.freshLabel();
		String thencode = "";
		String elsecode = "";

		for (Node tb : thenbranch) {
			thencode += tb.codeGeneration();
		}

		for (Node eb : elsebranch) {
			if (eb != null)		// The else branch may be null for ifExp
				elsecode += eb.codeGeneration();
		}

		return guard.codeGeneration() +
				"storei T1 1 \n" +
				"beq A0 T1 "+ lthen + "\n" +
				elsecode +
				"b " + lend + "\n" +
				lthen + ":\n" +
				thencode +
				lend + ":\n" ;
	}

	@Override
	public String toPrint(String s) {
		String thenstr = "";
		String elsestr = "";

		for (Node tb : thenbranch) {
			thenstr += tb.toPrint(s);
		}

		for (Node eb : elsebranch) {
			if (eb != null)		// The else branch may be null for ifExp
				elsestr += eb.toPrint(s);
		}

		return s + "If\n" + guard.toPrint(s + "  ") + thenstr + elsestr;
	}
}  