# Compiler and Interpreter for SimplanPlus based on ANTLR

Implementation of a parser, a compiler and an interpreter for a programming language called “SimplanPlus” which is based on ANTLR. The program takes as input a file (src/input.simplanplus) which is then analyzed.

The delivery contains 4 main points to follow for completion:
- lexical analysis that returns the list of lexical errors written in an output file (*out/errors.txt*);
- development of the program's Symbol Table (according to the [ANTLR grammar](https://github.com/LucaSpadoni/Compiler-and-Interpreter-for-SimplanPlus/blob/main/src/SimpLanPlus.g4) used) and checking of:
    - undeclared identifiers/functions;
    - identifiers/functions declared multiple times in the same environment.
- development of a semantic analysis that:
    - verifies the correctness of the types, in particular if the number and types of the actual parameters of a function correspond to the formal ones;
    - checks the usage of uninitialized variables, assuming that functions never access global variables.
- implementation of the interpreter that interpretes the code outputted from the previous code generation phase.

## Description of SimplanPlus

SimpLanPlus is a simple imperative language based on [ANTLR4](https://github.com/antlr/antlr4), in which:
- programs are composed of:
    - a single expressions *Exp*;
    - a concatenation of declarations, statements and possible expressions { *Dec+; Stm*; Exp?* };
- declarations of variables/functions are of the form: *type ID* (without initialization);
- statements are sequences of commands (a program or the body of a function can be stm or dec stm);
- expressions are usual operations (>, ≥, <, ≤, ==) and logical operations (&&, ||, !); 
- functions can be recursive (but not mutually);
- function bodies are of the type *{ Stm; Exp }* and in this case the function, after evaluating Stm, returns the value of Exp;
- types also include the *void* type.




