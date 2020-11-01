package pobj.tme6;

public class Turtle implements ITurtle {

	private int x,y,deg;
	/** go vaut true si le crayon est baissé, false sinon */
	private boolean go;
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getDeg() {
		return deg;
	}



	public Turtle() {
		this.x = 0;
		this.y = 0;
		this.deg = 0;
		this.go = true;
	}
	


	public boolean isGo() {
		return go;
	}



	@Override
	public void move(int length) {
		int newX = x + (int)(length * Math.sin(deg * Math.PI / 180.));
		int newY = y + (int)(length * Math.cos(deg * Math.PI / 180.));
		if(isGo()) {
			draw(x, y, newX, newY);
		}
		setX(newX);
		setY(newY);
	}

	@Override
	public void turn(int angle) {
		deg = (deg + angle)%360;
	}

	@Override
	public void up() {
		go = false;
	}

	@Override
	public void down() {
		go = true;
	}
	
	
	void draw(int x1, int y1, int x2, int y2) {
		System.out.println(x1+" "+y1+" "+x2+" "+y2);
	}

}
