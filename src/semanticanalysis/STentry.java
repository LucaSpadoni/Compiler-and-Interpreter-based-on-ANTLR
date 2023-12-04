package semanticanalysis;

import ast.Type;

import java.util.Objects;

public class STentry {
	private Type type;
	private int offset;
	private int nesting;
	private String label;		// Function label
	private boolean isInit;		// Checks if the variable is initialized

	public STentry(Type _type, int _offset, int _nesting, String  _label) {
		type = _type;
		offset = _offset;
		nesting = _nesting;
		label = _label;
		isInit = false;
	}

	public STentry(Type _type, int _offset, int _nesting, String  _label, boolean _isInit) {
		type = _type;
		offset = _offset;
		nesting = _nesting;
		label = _label;
		isInit = _isInit;
	}
	
	public Type gettype() {
		return type;
	}

	public int getoffset() {
		return offset ;
	}
	
	public int getnesting() {
		return nesting ;
	}

	public String getlabel() {
		return label;
	}

	// Initializes the variable
	public void initVar() {
		isInit = true;
	}

	// Checks if the variable is initialized
	public boolean isInit() {
		return isInit;
	}
}
