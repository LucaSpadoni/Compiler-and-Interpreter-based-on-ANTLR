package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class NotNode implements Node{
    private Node right;

    public NotNode(Node right) {
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    @Override
    public Type typeCheck(ArrayList<Type> err) {
        // We assume the Not operator only works with booleans
        if ((right.typeCheck(err) instanceof BoolType))
            return new BoolType();
        else {
            System.out.println("Type Error: Non boolean in Not");
            err.add(new ErrorType()); return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String ltrue = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();

        return right.codeGeneration() +
                "storei T1 1 \n" +
                "beq A0 T1 " + ltrue + "\n" +
                "storei A0  1 " + "\n" +
                "b " + lend + "\n" +
                ltrue + ":\n" +
                "storei A0 0 " + "\n" +
                lend + ":\n" ;
    }

    @Override
    public String toPrint(String s) {
        return s + "Not\n"  + right.toPrint(s + "  ");
    }
}
