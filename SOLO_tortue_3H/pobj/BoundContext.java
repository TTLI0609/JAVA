package pobj.tme6;

import javafx.scene.paint.Color;

public class BoundContext implements IContext {
	private int minX=99999999, maxX=0, minY=999999999, maxY=0;
	
	
	
	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}


	public int getMaxY() {
		return maxY;
	}




	@Override
	public void drawLine(int x1, int y1, int x2, int y2, Color color) {
		if(x2>maxX) {
			maxX=x2;
		}
		if(x1>maxX) {
			maxX=x1;
		}
		if(y2>maxY) {
			maxY=y2;
		}
		if(y1>maxY) {
			maxY=y1;
		}
		if(x1<minX) {
			minX=x1;
		}
		if(y1<minY) {
			minY=y1;
		}
		if(x2<minX) {
			minX=x2;
		}
		if(y2<minY) {
			minY=y2;
		}

	}

}
