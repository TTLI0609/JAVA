package pobj.expr;

public abstract class BinOp {
	private final Expression left;
	private final Expression right;
	
	public BinOp(Expression l, Expression r){
		left = l;
		right =r;
	}
	
	
	public Expression getLeft() {
		return left;
	}
	public Expression getRight() {
		return right;
	}
	
	
}
