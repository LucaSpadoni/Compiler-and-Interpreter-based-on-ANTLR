# Compiler-and-Interpreter-for-SimplanPlus

The delivery contains 4 main points to follow for completion:
- lexical analysis that returns the list of lexical errors in an output file;
- development of the program's Symbol Table and checking of undeclared identifiers/functions and identifiers/functions declared multiple times in the same environment;
- development of a semantic analysis that verifies both the correctness of the types such as number and type of current parameter if they are conform to the formal ones and use of uninitialized variables, assuming that the functions never access the global variables;
- implementation of the SimpLanPlus interpreter.

## Description of SimplanPlus

SimpLanPlus is a simple imperative language based on [ANTLR4](https://github.com/antlr/antlr4), in which:
- types also include the *void* type;
- variable declarations are: *type ID* (without initialization);
- functions can be recursive (but not mutually);
- there are commands (a program or the body of a function can be stm or dec stm);
- function bodies are of the type *{ stm; exp }* and in this case the function, after evaluating stm, returns the value of exp.



