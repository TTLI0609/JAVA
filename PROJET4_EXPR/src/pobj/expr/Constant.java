package pobj.expr;

public class Constant implements Expression{
	private int value;

	public Constant() {
		value = 0;
	}
	public Constant(int v) {
		value = v;
	}
	
	
	public int getValue() {
		return value;
	}
	
	@Override
	public boolean equals( Object o ){
		if (!(o instanceof Constant)) 
			return false;
		Constant c = (Constant) o;
		return (value == c.value) ;
	}
	
	@Override 
	public String toString() { 
		return String.valueOf(value);
	}
	
	@Override
	public int eval() {
		return value;
	} 
	
	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
