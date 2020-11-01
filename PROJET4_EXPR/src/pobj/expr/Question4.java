package pobj.expr;


public class Question4 {
	public static Expression e1() {
		Constant c1 = new Constant(2);
		Constant c2 = new Constant(3);
		Constant c3 = new Constant (4);
		Add add = new Add(c1, c2);
		Mult mul = new Mult(add, c3);
		
		return mul;
	}
	
	public static Expression e2() {
		Var v1 = new Var("x");
		Constant c1 = new Constant(3);
		Constant c2 = new Constant(4);
		
		Add add1 = new Add(v1, c1);
		Add add2 = new Add(v1, c2);
		Mult mul = new Mult(add1, add2);
		return mul;
	}
	
	public static Expression e3() {
		Var v1 = new Var("x");
		Var v2 = new Var("y");
		Constant c1 = new Constant(10);
		Constant c2 = new Constant(-8);
		
		Add add1 = new Add(v1, c1);
		Add add2 = new Add(v2, c2);
		Mult mul = new Mult(add1, add2);
		return mul;
	}
	
	
	
}
