package ast;

import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class AsgnNode implements Node{

    private String stringId;
    private Node right;
    private Type type;
    private int varNesting;
    private int asgnNesting;
    private String offset;
    private STentry sTentry;

    public AsgnNode(String stringId, Node right) {
        this.stringId = stringId;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        asgnNesting = _nesting;
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        errors.addAll(right.checkSemantics(ST, _nesting));
        STentry entry = ST.lookup(stringId);

        // To be assigned, the variable needs to be first declared
        if (entry == null)
            errors.add(new SemanticError("Var " + stringId + " not declared"));
        else{
            type = ST.lookup(stringId).gettype();
            varNesting = ST.lookup(stringId).getnesting();
            offset = String.valueOf(ST.lookup(stringId).getoffset());

            ST.lookup(stringId).initVar();  // The variable is initialized
            sTentry = entry;
        }

        return errors;
    }

    @Override
    public Type typeCheck(ArrayList<Type> err) {
        // The type of the expression (right) needs to be the same of the type of the ID
        if (right.typeCheck(err).getClass().equals(type.getClass())) {
            return new VoidType();
        }
        else {
            System.out.println("Type Error: incompatible type of expression for variable " + stringId);
            err.add(new ErrorType()); return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String getAR = "";

        for (int i = 0; i < varNesting - sTentry.getnesting(); i++){
            getAR += "storei T1 0(T1)\n";   // The command is written as many times as it's needed to reach the ID'S nesting level
        }

        return right.codeGeneration() +
                "move AL T1 \n" +
                getAR +
                "subi T1 " + sTentry.getoffset() + " \n" + // Offset ID
                "load A0 0(T1) \n";
    }

    @Override
    public String toPrint(String s) {
        return s + "Assign\n" + stringId + " " + right.toPrint(s + "  ");
    }
}
