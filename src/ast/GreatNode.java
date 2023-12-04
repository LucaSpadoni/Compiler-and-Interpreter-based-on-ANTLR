package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class GreatNode implements Node{
    private Node left;
    private Node right;

    public GreatNode(Node _left, Node _right) {
        left = _left ;
        right = _right ;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST, _nesting));
        errors.addAll(right.checkSemantics(ST, _nesting));

        return errors;
    }

    @Override
    public Type typeCheck(ArrayList<Type> err) {
        // Comparison operators only work with integers
        if ((left.typeCheck(err) instanceof IntType) && (right.typeCheck(err) instanceof IntType))
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non integers in comparison (greater than)");
            err.add(new ErrorType()); return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String ltrue = SimpLanlib.freshLabel();
        String lfalse = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();

        return	left.codeGeneration()+
                "pushr A0 \n" +
                right.codeGeneration()+
                "popr T1 \n" +
                "beq A0 T1 " + lfalse + "\n" +
                "bleq A0 T1 " + ltrue + "\n" +
                "b " + lfalse + "\n" +
                ltrue + ":\n" +
                "storei A0 1\n" +
                "b " + lend + "\n" +
                lfalse + ":\n" +
                "storei A0 0\n" +
                lend + ":\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "GreaterThan\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ");
    }
}
