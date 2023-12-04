// Generated from D:/Università/Primo Anno/Secondo Semestre/Complementi di Linguaggi di Programmazione/Progetto/Progetto Simplan2/src\SimpLanPlus.g4 by ANTLR 4.12.0
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SimpLanPlusParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SimpLanPlusVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExp(SimpLanPlusParser.SingleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decStmExp}
	 * labeled alternative in {@link SimpLanPlusParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecStmExp(SimpLanPlusParser.DecStmExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdDec(SimpLanPlusParser.IdDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDec}
	 * labeled alternative in {@link SimpLanPlusParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(SimpLanPlusParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(SimpLanPlusParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(SimpLanPlusParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(SimpLanPlusParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idInit}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdInit(SimpLanPlusParser.IdInitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunStm(SimpLanPlusParser.FunStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link SimpLanPlusParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStm(SimpLanPlusParser.IfStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExp(SimpLanPlusParser.CompareExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExp(SimpLanPlusParser.VarExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrExp(SimpLanPlusParser.AndOrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExp(SimpLanPlusParser.ParExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(SimpLanPlusParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sumSubExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumSubExp(SimpLanPlusParser.SumSubExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(SimpLanPlusParser.BoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExp(SimpLanPlusParser.FunExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExp(SimpLanPlusParser.NotExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExp}
	 * labeled alternative in {@link SimpLanPlusParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExp(SimpLanPlusParser.IntExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#ifS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfS(SimpLanPlusParser.IfSContext ctx);
	/**
	 * Visit a parse tree produced by {@link SimpLanPlusParser#ifE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfE(SimpLanPlusParser.IfEContext ctx);
}