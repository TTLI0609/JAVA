package pobj.expr;

public class Mult extends BinOp implements Expression {

	public Mult(Expression l, Expression r) {
		super(l, r);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getLeft().toString());
		sb.append(" * ");
		sb.append(getRight().toString());
		return sb.toString();
	}
	
	@Override
	public int eval() {
		return getLeft().eval() * getRight().eval();
	}
	
	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
