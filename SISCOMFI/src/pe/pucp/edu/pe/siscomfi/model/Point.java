package pe.pucp.edu.pe.siscomfi.model;

import java.util.Comparator;

public class Point implements Comparable<Point>{
	private int x;
	private int y;
	private int value;
	private int type;
	private double angle;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public int compareTo (Point other) {
	    if (this.y == other.y) {
	        return (this.x < other.x) ? -1 : ((this.x == other.x) ? 0 : 1);
	    } else {
	        return (this.y < other.y) ? -1 : 1;
	    }
	}
	
	public double euclideanDistance(Point px){
		double x_2 = Math.pow(this.x - px.getX(), 2);
		double y_2 = Math.pow(this.y - px.getY(), 2);
		return Math.sqrt(x_2 + y_2);
	}
	
	public double getAnglePoint(Point px){
		double dY = px.getY() - this.y;
		double dX = px.getX() - this.x;
		return Math.atan2(dY, dX);
	}
}
