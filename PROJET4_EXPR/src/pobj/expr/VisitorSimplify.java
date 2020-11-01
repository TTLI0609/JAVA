package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression>{

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		
		// si cest des operations sur des constantes
		if(e.accept(new VisitorConstant()))
			return new Constant(e.eval());
		
		// element neutre 
		if (e.getLeft().accept(new VisitorConstant())) {
			if (e.getLeft().accept(this).eval() == 0)
				return e.getRight();
		} else {
			if (e.getRight().accept(this).eval() == 0)
				return e.getLeft();
		}
		
		// sinon on ne simplifie rien
		return e;
		
			
			
	}

	@Override
	public Expression visit(Mult e) {
		if (e.accept(new VisitorConstant())) {
			return new Constant(e.eval());
		}
		if (e.getLeft().accept(new VisitorConstant())) {
			if (e.getLeft().accept(this).eval() == 0) {
				return e.getLeft();
			} else if (e.getLeft().accept(this).eval() == 1) {
				return e.getRight();
			}
		} else if(e.getRight().accept(new VisitorConstant())){
			if (e.getRight().accept(this).eval() == 0) {
				return e.getRight();
			} else if (e.getRight().accept(this).eval() == 1) {
				return e.getLeft();
			}
		}
		return e;
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}
