package ast;

import evaluator.SimpLanlib;
import semanticanalysis.STentry;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgDecStmNode implements Node{
    private ArrayList<Node> dec ;
    private ArrayList<Node> stm ;
    private final Node exp;
    private int nesting ;

    public ProgDecStmNode(ArrayList<Node> dec, ArrayList<Node> stm, Node exp) {
        this.dec = dec;
        this.stm = stm;
        this.exp = exp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        nesting = _nesting + 1;

        HashMap<String, STentry> H = new HashMap<String, STentry>();
        ST.addScope(H);

        // Declare the resulting list
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

        for (Node d : dec) {
            errors.addAll(d.checkSemantics(ST, nesting)) ;
        }

        for (Node s : stm) {
            errors.addAll(s.checkSemantics(ST, nesting));
        }

        // Check semantics in the exp body (if there's one)
        if (exp != null){
            errors.addAll(exp.checkSemantics(ST, nesting));
        }

        ST.removeScope();    // Clean the scope
        return errors;  // Return the result
    }

    @Override
    public Type typeCheck(ArrayList<Type> err) {
        // The type the program is the type of the expression. If there are no expressions then the type of the whole program will be void.

        for (Node d: dec)
            d.typeCheck(err);

        for (Node s: stm)
            s.typeCheck(err);

        if (exp != null)
            return exp.typeCheck(err);
        else
            return new VoidType();
    }

    @Override
    public String codeGeneration() {
        String declCode = "";
        String stmCode = "";
        String expCode = "";

        for (Node d: dec)
            declCode += d.codeGeneration();

        for (Node s: stm)
            stmCode += s.codeGeneration();

        if (exp != null) {
            expCode = exp.codeGeneration();
        }

        return "pushr FP\n"
                + "pushr AL\n"
                + declCode
                + stmCode
                + expCode
                + "halt\n"
                + SimpLanlib.getCode();
    }

    @Override
    public String toPrint(String s) {
        String decList = "";
        String stmList = "";
        String ex = "";

        for (Node d : dec)
            decList += d.toPrint(s + "\t");

        for (Node st : stm)
            stmList += st.toPrint(s + "\t");

        if (exp != null)
            ex = exp.toPrint(s);

        return s + "ProgDecStm\n" + decList + stmList + ex;
    }
}
