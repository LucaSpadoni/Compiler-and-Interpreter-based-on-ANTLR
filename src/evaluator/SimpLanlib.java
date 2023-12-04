package evaluator;

public class SimpLanlib {
	private static int labCount = 0;
	private static int funLabCount = 0;
	private static String funCode = "";
  
	public static String freshLabel() { 
		return "label" + (labCount++);
	} 

	public static String freshFunLabel() { 
		return "function" + (funLabCount++);
	} 
  
	public static void putCode(String c) { 
		funCode += "\n" + c; // Aggiunge una linea vuota di separazione prima della funzione
	} 
  
	public static String getCode() { 
		return funCode;
	} 
}