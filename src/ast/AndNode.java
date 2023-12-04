package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class AndNode implements Node{
    private Node left;
    private Node right;
    public AndNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        // Semantic check for both right and left expressions
        errors.addAll(left.checkSemantics(ST,_nesting));
        errors.addAll(right.checkSemantics(ST,_nesting));

        return errors;
    }
    @Override
    public Type typeCheck(ArrayList<Type> err) {
        // The And expression is performed on boolean values
        if ((left.typeCheck(err) instanceof BoolType) && (right.typeCheck(err) instanceof BoolType) )
            return new BoolType();
        else {
            System.out.println("Type Error: Non booleans in And") ;
            err.add(new ErrorType()); return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String falsel = SimpLanlib.freshLabel();
        String end = SimpLanlib.freshLabel();

        return left.codeGeneration()+
                "storei T1 0 \n" +
                "beq A0 T1 " + falsel + "\n" +
                "pushr A0 \n" +
                right.codeGeneration() +
                "popr T1 \n" +
                "beq A0 T1 " + falsel + "\n" +
                "storei A0 1 \n" +
                "b " + end + "\n" +
                falsel + ":\n" +
                "storei A0 0 \n" +
                end + ":\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "And\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ");
    }
}
