package pobj.expr;

public class VisitorDerive implements IVisitor<Expression> {
	private Var x;
	
	
	public VisitorDerive(Var x) {
		//x cest le variable par rapport a laquel on derive 
		this.x = x;
	}

	@Override
	public Expression visit(Constant c) {
		//derivee d'une constante est 0
		return new Constant(0);
	}

	@Override
	public Expression visit(Add e) {
		Expression der_e1 = e.getLeft().accept(this);
		Expression der_e2 = e.getRight().accept(this);
		return new Add(der_e1, der_e2 );
	}

	@Override
	public Expression visit(Mult e) {
		Expression der_e1 = new Mult(e.getLeft(), e.getRight().accept(this));
		Expression der_e2 = new Mult(e.getLeft().accept(this), e.getRight());
		return new Add(der_e1, der_e2);
	}

	@Override
	public Expression visit(Var v) {
		// on derive par rapport a la variable x
		if (v.equals(x))
			return new Constant(1);
		return new Constant(0);
	}

}
