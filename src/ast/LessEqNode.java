package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class LessEqNode implements Node{
    private Node left;
    private Node right;
    public LessEqNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        errors.addAll(left.checkSemantics(ST,_nesting));
        errors.addAll(right.checkSemantics(ST,_nesting));

        return errors;
    }
    @Override
    public Type typeCheck(ArrayList<Type> err) {
        // Comparison operators only work with integers
        if ((left.typeCheck(err) instanceof IntType) && (right.typeCheck(err) instanceof IntType))
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non integers in comparison (less than or equal to)") ;
            err.add(new ErrorType()); return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String ltrue = SimpLanlib.freshLabel();
        String lend = SimpLanlib.freshLabel();

        return   left.codeGeneration()
                + "pushr A0 \n"
                + right.codeGeneration()
                + "popr T1 \n"
                + "bleq T1 A0 " + ltrue + "\n"
                + "storei A0 0\n"
                + "b " + lend + "\n"
                + ltrue + ":\n"
                + "storei A0 1\n"
                + lend + ":\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "LessThanOrEqualTo\n" + left.toPrint(s+"  ") + right.toPrint(s+"  ");
    }
}
