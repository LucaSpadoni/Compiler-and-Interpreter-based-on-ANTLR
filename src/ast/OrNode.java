package ast;

import evaluator.SimpLanlib;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class OrNode implements Node{
    private Node left;
    private Node right;
    public OrNode(Node left, Node right) {
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
        // The Or expression is performed on boolean values
        if ((left.typeCheck(err) instanceof BoolType) && (right.typeCheck(err) instanceof BoolType))
            return new BoolType() ;
        else {
            System.out.println("Type Error: Non booleans in Or");
            err.add(new ErrorType()); return new ErrorType();
        }
    }

    @Override
    public String codeGeneration() {
        String truel = SimpLanlib.freshLabel();
        String end = SimpLanlib.freshLabel();

        return left.codeGeneration()+
                "storei T1 1 \n" +
                "beq A0 T1 " + truel + "\n" +
                "pushr A0 \n" +
                right.codeGeneration() +
                "popr T1 \n" +
                "beq A0 T1 " + truel + "\n" +
                "storei A0 0 \n" +
                "b " + end + "\n" +
                truel + ":\n" +
                "storei A0 1 \n" +
                end + ":\n";
    }

    @Override
    public String toPrint(String s) {
        return s + "Or\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
    }
}
