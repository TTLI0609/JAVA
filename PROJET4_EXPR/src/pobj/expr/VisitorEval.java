package pobj.expr;

public class VisitorEval implements IVisitor<Integer> {

	@Override
	public Integer visit(Constant c) {
		return c.eval();
	}

	@Override
	public Integer visit(Add e) {
		Integer s1 = e.getLeft().accept(this);
		Integer s2 = e.getRight().accept(this);
		return s1 + s2 ;
	}

	@Override
	public Integer visit(Mult e) {
		Integer s1 = e.getLeft().accept(this);
		Integer s2 = e.getRight().accept(this);
		return s1 * s2;
	}

	@Override
	public Integer visit(Var v) {
		return v.eval();
	}

}
