package ast;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/*
This class intercepts syntax errors and throws them as RuntimeException instead. This new ErrorListener overwrites the original ErrorListener
used by the SimpLanPlusParser since the program wouldn't stop when it found syntax errors.
 */
public class ErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new RuntimeException("\nSyntax error: line " + line + ", character number " + charPositionInLine + " -> " + msg + ".\n");
    }
}
