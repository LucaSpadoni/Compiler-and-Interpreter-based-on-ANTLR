package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class ParNode implements Node {

  private String id;
  private Type type;
  private int nesting;
  
  public ParNode(String _id, Type _type) {
   id = _id ;
   type = _type ;
  }
  
  public String getId(){
	  return id;
  }
  
  public Type getType(){
	  return type;
  }
  
  @Override
	public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
      ArrayList<SemanticError> errors = new ArrayList<>();
      nesting = _nesting;

      if (!ST.top_lookup(id)){
          // The parameter is both set as declared and initialized
          ST.insert(id, type, nesting, "");
          ST.lookup(id).initVar();
      }
      else{
          // There's already a parameter called with the same name
          errors.add(new SemanticError("Various parameters with the same name " + this.id + " were passed to the function"));
      }

      return errors;
	}

  public Type typeCheck(ArrayList<Type> err) {
      // Parameters cannot be of void type
      if (type instanceof VoidType) {
          System.out.println("Parameter " + id + " cannot be of void type");
          err.add(new ErrorType()); return new ErrorType();
      }
      else {
          return type;
      }
  }
  
  public String codeGeneration() {
		return "";
  }
  
  public String toPrint(String s) {
	  return s + "Par " + id + ":" + type.toPrint(s);
  }
}  