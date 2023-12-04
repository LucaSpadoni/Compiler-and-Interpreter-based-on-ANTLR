package ast;

import antlr.SimpLanPlusBaseVisitor;
import antlr.SimpLanPlusParser;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {
	public Node visitSingleExp(SimpLanPlusParser.SingleExpContext ctx) {

		// Simply returns the result of the visit to the inner exp
		return new ProgNode(visit(ctx.exp()));
	}

	public Node visitDecStmExp(SimpLanPlusParser.DecStmExpContext ctx) {

		// List of declarations in @res
		ArrayList<Node> declarations = new ArrayList<Node>();
		// List of statements in @res
		ArrayList<Node> statements = new ArrayList<Node>();
		// The expression in @res
		Node expV = null;

		// Visits all the nodes corresponding to declarations inside the context and store them in @declarations, the same
		// for the statements. Notice that the ctx.dec() method returns a list, this is because of the use of * or +
		// in the grammar as antlr detects this is a group and therefore returns a list.
		for (SimpLanPlusParser.DecContext dc : ctx.dec()) {
			declarations.add(visit(dc));
		}

		for (SimpLanPlusParser.StmContext sc : ctx.stm()) {
			statements.add(visit(sc));
		}

		// Visit the exp context if there's one
		if (ctx.exp() != null)
			expV = visit(ctx.exp());

		// Builds @res accordingly with the result of the visits to its content
		return new ProgDecStmNode(declarations, statements, expV);
	}

	public Node visitIdDec(SimpLanPlusParser.IdDecContext ctx) {
		// Visits the type of the variable
		Node typeNode = visit(ctx.type());

		// Builds the idDecNode
		return new DecNode(ctx.ID().getText(), typeNode);
	}

	public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
		// Initializes @res with the visits to the type and its ID and adds the argument declarations. We are getting a shortcut here by
		// constructing directly the ParNode. This could be done differently by visiting instead the IDDecContext
		ArrayList<ParNode> _param = new ArrayList<ParNode>();

		for (SimpLanPlusParser.ParamContext vc : ctx.param())
			_param.add(new ParNode(vc.ID().getText(), (Type) visit(vc.type())));

		// Visits the body of the function
		Node body = visit(ctx.body());

		return new FunNode(ctx.ID().getText(), (Type) visit(ctx.type()), _param, body);
	}

	public Node visitBody(SimpLanPlusParser.BodyContext ctx) {
		// List of declarations in @res
		ArrayList<Node> declarations = new ArrayList<Node>();
		// List of statements in @res
		ArrayList<Node> statements = new ArrayList<Node>();
		// The expression in @res
		Node exp = null;

		// Visits all the nodes corresponding to declarations inside the context and store them in @declarations, the same for the statements.
		// Notice that the ctx.dec() method returns a list, this is because of the use of * or + in the grammar as antlr detects this is a
		// group and therefore returns a list
		for (SimpLanPlusParser.DecContext dc : ctx.dec()) {
			declarations.add(visit(dc));
		}

		for (SimpLanPlusParser.StmContext sc : ctx.stm()) {
			statements.add(visit(sc));
		}

		// Visits exp context if there's one
		if (ctx.exp() != null)
			exp = visit(ctx.exp());

		return new BodyNode(declarations, statements, exp);
	}

	public Node visitType(SimpLanPlusParser.TypeContext ctx) {
		if (ctx.getText().equals("int"))
			return new IntType();
		else if (ctx.getText().equals("bool"))
			return new BoolType();
		else return new VoidType();
	}

	public Node visitIdInit(SimpLanPlusParser.IdInitContext ctx) {
		// Visits the exp
		Node exp = visit(ctx.exp());
		String nodeId = ctx.ID().getText();

		return new AsgnNode(nodeId, exp);
	}

	public Node visitFunStm(SimpLanPlusParser.FunStmContext ctx) {    // This corresponds to a function invocation
		// Gets the invocation arguments
		ArrayList<Node> args = new ArrayList<Node>();

		for (SimpLanPlusParser.ExpContext exp : ctx.exp())
			args.add(visit(exp));

		return new CallNode(ctx.ID().getText(), args);
	}

	public Node visitIfStm(SimpLanPlusParser.IfStmContext ctx) {
		// Visits the conditional, then the then branch and then the else branch. Notice once again the need of named terminals in the rule,
		// this is because we need to point to the right expression among the 3 possible ones in the rule.

		Node condExp = visit(ctx.cond);
		ArrayList<Node> thenB = new ArrayList<>();
		ArrayList<Node> elseB = new ArrayList<>();
		boolean exp = false;	// According to the grammar we cannot have expressions for ifStm

		for (SimpLanPlusParser.StmContext sc : ctx.thenBranch.stm()) {
			thenB.add(visit(sc));
		}

		// Visits the else context (if there's one)
		if (ctx.elseBranch != null){
			for (SimpLanPlusParser.StmContext sc : ctx.elseBranch.stm()) {
				elseB.add(visit(sc));
			}
		}

		return new IfNode(condExp, thenB, elseB, exp);
	}

	public Node visitCompareExp(SimpLanPlusParser.CompareExpContext ctx) {
		if (ctx.gt != null)
			return new GreatNode(visit(ctx.left), visit(ctx.right));
		else if (ctx.gte != null)
			return new GreatEqNode(visit(ctx.left), visit(ctx.right));
		else if (ctx.lt != null)
			return new LessNode(visit(ctx.left), visit(ctx.right));
		else if (ctx.lte != null)
			return new LessEqNode(visit(ctx.left), visit(ctx.right));
		else
			return new EqualNode(visit(ctx.left), visit(ctx.right));
	}

	public Node visitVarExp(SimpLanPlusParser.VarExpContext ctx) {
		// This corresponds to a variable access
		return new IdNode(ctx.ID().getText());
	}

	public Node visitAndOrExp(SimpLanPlusParser.AndOrExpContext ctx) {
		if (ctx.and != null)
			return new AndNode(visit(ctx.left), visit(ctx.right));
		else
			return new OrNode(visit(ctx.left), visit(ctx.right));
	}

	public Node visitMulDivExp(SimpLanPlusParser.MulDivExpContext ctx) {
		if (ctx.mul != null)
			return new MultNode(visit(ctx.left), visit(ctx.right));
		else
			return new DivNode(visit(ctx.left), visit(ctx.right));
	}

	public Node visitParExp(SimpLanPlusParser.ParExpContext ctx) {
		// This is actually nothing in the sense that for the ast the parenthesis are not relevant. The thing is that the structure of the ast
		// will ensure the operational order by giving a larger depth (closer to the leafs) to those expressions with higher importance.

		// This is actually the default implementation for this method in the SimpLanBaseVisitor class therefore it can be safely removed
		// here.

		return visit(ctx.exp());
	}

	public Node visitIfExp(SimpLanPlusParser.IfExpContext ctx) {
		// Visits the conditional, then the then branch and then the else branch. Notice once again the need of named terminals in the rule,
		// this is because we need to point to the right expression among the 3 possible ones in the rule.

		Node condExp = visit(ctx.cond);
		ArrayList<Node> thenB = new ArrayList<>();
		ArrayList<Node> elseB = new ArrayList<>();
		boolean exp = true;		// According to the grammar ifExp may contain expressions

		for (SimpLanPlusParser.StmContext sc : ctx.thenBranch.stm()) {
			thenB.add(visit(sc));
		}

		thenB.add(visit(ctx.thenBranch.exp()));

		for (SimpLanPlusParser.StmContext sc : ctx.elseBranch.stm()) {
			elseB.add(visit(sc));
		}

		elseB.add(visit(ctx.elseBranch.exp()));

		return new IfNode(condExp, thenB, elseB, exp);
	}

	public Node visitSumSubExp(SimpLanPlusParser.SumSubExpContext ctx) {
		if (ctx.sum != null)
			return new PlusNode(visit(ctx.left), visit(ctx.right));
		else
			return new MinusNode(visit(ctx.left), visit(ctx.right));
	}

	public Node visitBoolExp(SimpLanPlusParser.BoolExpContext ctx) {
		// There is no need to perform a check here, the lexer ensures this text is a boolean
		return new BoolNode(Boolean.parseBoolean(ctx.getText()));
	}

	public Node visitFunExp(SimpLanPlusParser.FunExpContext ctx) {
		// Gets the invocation arguments
		ArrayList<Node> args = new ArrayList<Node>();

		for (SimpLanPlusParser.ExpContext exp : ctx.exp())
			args.add(visit(exp));

		return new CallNode(ctx.ID().getText(), args);
	}

	public Node visitNotExp(SimpLanPlusParser.NotExpContext ctx) {
		// Visits the right exp
		Node exp = visit(ctx.exp());

		return new NotNode(exp);
	}

	public Node visitIntExp(SimpLanPlusParser.IntExpContext ctx) {
		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	}
}
