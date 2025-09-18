package groupwork;

public class rectangle {
	private double length;
	private double width;
	private double area;
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getArea() {
		return area;
	}
	public void setArea() {
		this.area= (this.length*this.width) ;
	}
	public double calculatearea() {
		return this.area;
	}
	
	

}
