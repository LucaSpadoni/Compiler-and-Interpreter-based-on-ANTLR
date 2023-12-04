# Compiler-and-Interpreter-for-SimplanPlus

The delivery contains 4 main points to follow for completion:
- lexical analysis that returns the list of lexical errors in an output file (*out/errors.txt*);
- development of the program's Symbol Table (according to the [ANTLR grammar](https://github.com/LucaSpadoni/Compiler-and-Interpreter-for-SimplanPlus/blob/main/src/SimpLanPlus.g4) used) and checking of:
    - undeclared identifiers/functions;
    - identifiers/functions declared multiple times in the same environment.
- development of a semantic analysis that:
    - verifies the correctness of the types, such as if the number and types of actual parameters correspong to the formal ones
    - checks the usage of uninitialized variables, assuming that functions never access global variables;
- implementation of the interpreter.

## Description of SimplanPlus

SimpLanPlus is a simple imperative language based on [ANTLR4](https://github.com/antlr/antlr4), in which:
- types also include the *void* type;
- variable declarations are: *type ID* (without initialization);
- functions can be recursive (but not mutually);
- there are commands (a program or the body of a function can be stm or dec stm);
- function bodies are of the type *{ stm; exp }* and in this case the function, after evaluating stm, returns the value of exp.



