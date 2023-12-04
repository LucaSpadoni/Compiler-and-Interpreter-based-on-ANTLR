package semanticanalysis;

import ast.BoolType;
import ast.IntType;
import ast.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SymbolTable {
	private ArrayList<HashMap<String, STentry>> symbol_table;
	private ArrayList<Integer> offset;
	
	public SymbolTable() {
		symbol_table = new ArrayList<HashMap<String, STentry>>();
		offset = new ArrayList<>();
	}
	
	public Integer nesting() {
		return symbol_table.size() - 1;
	}

	// Lookups in the ST
	public STentry lookup(String id) {
		int n = symbol_table.size() - 1;
		boolean found = false;
		STentry T = null;

		while ((n >= 0) && !found) {
			HashMap<String, STentry> H = symbol_table.get(n);
			T = H.get(id);

			if (T != null)
				found = true;
			else
				n = n - 1;
		}
		return T;
	}

	// Top-lookups in the ST
	public boolean top_lookup(String id) {
		int n = symbol_table.size() - 1;
		STentry T = null ;
		HashMap<String, STentry> H = symbol_table.get(n) ;
		T = H.get(id);

		return (T != null) ;
	}

	// Enters into a new scope and returns the nesting level of the freshly created scope
	public int addScope(HashMap<String, STentry> H) {
		symbol_table.add(H);
		offset.add(1);		// It starts from 2 because FP and AL come first

		return symbol_table.size() - 1;	// Returns the nesting level of the new scope
	}

	// Cleans the scope
	public void removeScope() {
		int x = symbol_table.size();

		symbol_table.remove(x - 1);
		offset.remove(x - 1 );
	}

	// Used for the declarations, inserts a new entry into the SymbolTable
	public void insert(String id, Type type, int _nesting, String _label) {
		int n = symbol_table.size() - 1;
		int offs = offset.get(n);
		HashMap<String,STentry> H = symbol_table.get(n);
		STentry idtype = new STentry(type, offs, _nesting, _label);

		symbol_table.remove(n);
		offset.remove(n);
		H.put(id, idtype);
		symbol_table.add(H);

		if (type.getClass().equals((new BoolType()).getClass()))
			offs = offs + 1; // We always increment the offset by 1 otherwise we need ad-hoc bytecode operations
		else if (type.getClass().equals(IntType.class))
			offs = offs + 1;
		else offs = offs + 1;

		offset.add(offs);
	}

	// Increases the offset by 1
	public void increaseoffset() {
		int n = offset.size() - 1;
		int offs = offset.get(n);

		offset.remove(n);
		offs = offs + 1;
		offset.add(offs);
	}

	// Checks if 2 SymbolTables are equal
	@Override
	public boolean equals(Object obj) {
		SymbolTable ST2 = (SymbolTable) obj;

		for (HashMap<String, STentry> hm : ST2.symbol_table){
			if (!symbol_table.contains(hm)){
				return false;
			}
		}

		return true;
	}

	// Checks the intersection between 2 ST (since they're not equal). Practically it checks whether a variable has been initialized in both
	// branches (because otherwise it wouldn't be correct)
	public HashMap<String, STentry> common(SymbolTable ST) {
		boolean common = false;
		HashMap<String, STentry> entryInitThenBranch = new HashMap<>();
		HashMap<String, STentry> entryInitElseBranch = new HashMap<>();
		HashMap<String, STentry> commonEntry = new HashMap<>();

		// Builds the then branch ST by checking if (and which) there are initialized variables
		for (HashMap<String, STentry> hm : ST.symbol_table){
			for (String s : hm.keySet()){
				STentry sTentry = hm.get(s);

				if (sTentry.isInit()){
					entryInitThenBranch.put(s, sTentry);
				}
			}
		}

		// Builds the else branch ST by checking if (and which) there are initialized variables
		for (HashMap<String, STentry> hm : ST.symbol_table){
			for (String s : hm.keySet()){
				STentry sTentry = hm.get(s);

				if (sTentry.isInit()){
					entryInitElseBranch.put(s, sTentry);
				}
			}
		}

		// We check if a variable is initialized in both branches
		for (String s : entryInitElseBranch.keySet()){
			if (entryInitThenBranch.containsKey(s)){
				common = true;
				commonEntry.put(s, entryInitElseBranch.get(s));		// Common initialized variables are returned
			}
		}

		if (common)
			return commonEntry;

		return null;
	}

	public ArrayList<Integer> getOffset() {
		return offset;
	}

	public void setOffset(ArrayList<Integer> offset) {
		this.offset.addAll(offset);
	}

	// Returns the current ST
	public ArrayList<HashMap<String, STentry>> getST() {
		return symbol_table;
	}

	// Creates (copies) a new ST from a ST passed as input
	public void setST(ArrayList<HashMap<String, STentry>> symbol_table){
		for (HashMap<String, STentry> hm : symbol_table){
			HashMap<String, STentry> tmp = new HashMap<>();

			for (String s : hm.keySet()){
				STentry copy = hm.get(s);
				STentry other = new STentry(copy.gettype(), copy.getoffset(), copy.getnesting(), copy.getlabel(), copy.isInit());
				tmp.put(s, other);
			}

			this.symbol_table.add(tmp);
		}
	}
}
