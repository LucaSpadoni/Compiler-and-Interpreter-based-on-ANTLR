import antlr.SVMLexer;
import antlr.SimpLanPlusLexer;
import antlr.SimpLanPlusParser;
import ast.*;
import evaluator.ExecuteVM;
import org.antlr.v4.runtime.*;
import antlr.SVMLexer;
import antlr.SVMParser;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        The program executes four checks: one for lexical errors, one for syntax errors, one for semantic errors and one for type errors.
        If either one of these fail, the program will stop.
        */

        String inputProgramFile = "src/input.simplanplus";      // Input file where the program used by our compiler is stored
        String lexicalErrorsFile = "out/errors.txt";        // Output file where lexical errors are stored
        String codegenFile = "codegenerated.asm";       // Output file for the code generation process (used as input by the interpreter)

        FileInputStream is = new FileInputStream(inputProgramFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        tokens.fill();
        List<Token> lexicalErrors = new ArrayList<>();

        for (Token t : tokens.getTokens()){
            if (t.getType() == SimpLanPlusLexer.ERR){   // Checks for lexical errors (the type of the token is ERR)
                lexicalErrors.add(t);
            }
        }

        File file = new File(lexicalErrorsFile);
        if (!file.exists()){
            file.createNewFile();
        }
        else{
            file.delete();
            file.createNewFile();
        }

        // Writes into the output file all the lexical errors found in the input program
        for (int i = 0; i < lexicalErrors.size(); i++){
            int errLine = lexicalErrors.get(i).getLine();
            String errStr = lexicalErrors.get(i).getText();
            int errPos = lexicalErrors.get(i).getCharPositionInLine() + 1;
            String toWrite = "Error #" + (i + 1) + ": Line " + errLine + ", character number " + errPos + " -> " + errStr + "\n";
            Files.write(Paths.get(lexicalErrorsFile), toWrite.getBytes(), StandardOpenOption.APPEND);
        }

        // Checks for lexical errors
        if (!lexicalErrors.isEmpty()){
            System.out.println("\nThe program has got lexical errors! Exiting the compilation process now (check the 'errors.txt' output file).");
        }
        else {
            // If the program is lexically correct, it then checks for syntax errors
            ErrorListener listener = new ErrorListener();               // New listener for parsing errors
            SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
            parser.removeErrorListeners();                              // Removes the old listener
            parser.addErrorListener(listener);                          // Adds our listener

            SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();
            Node ast;

            // The program exits if it's got syntax errors
            try{
                ast = visitor.visit(parser.prog()); // AST generation
            }
            catch (RuntimeException e){
                // We have to throw a RuntimeException since the program wouldn't stop when a syntax error was found
                System.out.println(e.getMessage());
                System.out.println("Syntax checking is WRONG! Exiting the compilation process now.");
                return;
            }

            SymbolTable ST = new SymbolTable();
            ArrayList<SemanticError> errors = ast.checkSemantics(ST, 0);

            // The program exits if it's got semantic errors (from the checkSemantics() method)
            if (errors.size() > 0){
                System.out.println("You had " + errors.size() + " errors:");

                for(SemanticError e : errors)
                    System.out.println("\t" + e);

                System.out.println("\nSemantic checking is WRONG! Exiting the compilation process now.");
            }
            // Prints the AST and executes type checking for every node of it
            else {
                System.out.println("\nVisualizing AST...");
                System.out.println(ast.toPrint(""));

                ArrayList<Type> t = new ArrayList<>();
                boolean isErr = false;  // Flag used to search for type errors
                Type type = ast.typeCheck(t); // Bottom-up type check

                // If the ArrayList contains an instance of ErrorType it means that there was at least one type error
                for (Object o : t){
                    if (o instanceof ErrorType) {
                        isErr = true;
                        break;
                    }
                }

                // The program exits if it's got type errors (from the typeCheck() method)
                if (isErr) {
                    System.out.println("\nType checking is WRONG! Exiting the compilation process now.");
                }
                // Since the program is now error-free we generate the bytecode which is then passed to the interpreter
                else {
                    System.out.println(type.toPrint("Type checking ok! Type of the program is "));

                    // Code Generation output written into the 'codegenerated.asm' file
                    String code = ast.codeGeneration();
                    BufferedWriter out = new BufferedWriter(new FileWriter(codegenFile));
                    out.write(code);
                    out.close();
                    System.out.println("Code generated! Assembling and running generated code.\n");

                    FileInputStream inputAsm = new FileInputStream(codegenFile);    // The interpreter uses the codegen file as input
                    ANTLRInputStream inputASM = new ANTLRInputStream(inputAsm);
                    SVMLexer lexerASM = new SVMLexer(inputASM);
                    CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
                    SVMParser parserASM = new SVMParser(tokensASM);

                    SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
                    visitorSVM.visit(parserASM.assembly());

                    System.out.println("Starting Virtual Machine...");
                    ExecuteVM vm = new ExecuteVM(visitorSVM.code);
                    vm.cpu();
                }
            }
        }
    }
}