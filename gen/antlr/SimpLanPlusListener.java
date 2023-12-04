// Generated from D:/Università/Primo Anno/Secondo Semestre/Complementi di Linguaggi di Programmazione/Progetto/Progetto Simplan2/src\SimpLanPlus.g4 by ANTLR 4.12.0
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpLanPlusParser}.
 */
public interface SimpLanPlusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decStmExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterDecStmExp(SimpLanPlusParser.DecStmExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decStmExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitDecStmExp(SimpLanPlusParser.DecStmExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterIdDec(SimpLanPlusParser.IdDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitIdDec(SimpLanPlusParser.IdDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idInit}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIdInit(SimpLanPlusParser.IdInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idInit}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIdInit(SimpLanPlusParser.IdInitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterFunStm(SimpLanPlusParser.FunStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitFunStm(SimpLanPlusParser.FunStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCompareExp(SimpLanPlusParser.CompareExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCompareExp(SimpLanPlusParser.CompareExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterVarExp(SimpLanPlusParser.VarExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitVarExp(SimpLanPlusParser.VarExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andOrExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAndOrExp(SimpLanPlusParser.AndOrExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andOrExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAndOrExp(SimpLanPlusParser.AndOrExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExp(SimpLanPlusParser.MulDivExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParExp(SimpLanPlusParser.ParExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParExp(SimpLanPlusParser.ParExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sumSubExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterSumSubExp(SimpLanPlusParser.SumSubExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sumSubExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitSumSubExp(SimpLanPlusParser.SumSubExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBoolExp(SimpLanPlusParser.BoolExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBoolExp(SimpLanPlusParser.BoolExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunExp(SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunExp(SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNotExp(SimpLanPlusParser.NotExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNotExp(SimpLanPlusParser.NotExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIntExp(SimpLanPlusParser.IntExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIntExp(SimpLanPlusParser.IntExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#ifS}.
	 * @param ctx the parse tree
	 */
	void enterIfS(SimpLanPlusParser.IfSContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#ifS}.
	 * @param ctx the parse tree
	 */
	void exitIfS(SimpLanPlusParser.IfSContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpLanPlusParser#ifE}.
	 * @param ctx the parse tree
	 */
	void enterIfE(SimpLanPlusParser.IfEContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpLanPlusParser#ifE}.
	 * @param ctx the parse tree
	 */
	void exitIfE(SimpLanPlusParser.IfEContext ctx);
}