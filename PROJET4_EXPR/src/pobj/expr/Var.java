package pobj.expr;

public class Var implements Expression {
	private final String name;
	
	public Var(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals( Object o ){
		if (!(o instanceof Var)) 
			return false;
		Var c = (Var) o;
		return (name.equals(c.name)) ;
	}
	
	@Override 
	public String toString() { 
		return name;
	}

	
	@Override
	public int eval() {
		throw new UnsupportedOperationException();
	} 
	
	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
