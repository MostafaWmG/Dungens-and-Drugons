package ca.concordia.soen6441.d20.item;

public class ArmorClass {
	int point;
	
	public ArmorClass(){
		point = 0;
	}
	
	public ArmorClass(int point){
		this.point = point;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
	public void showPoint(){
		System.out.println("ArmorPoint:" + point);
	}
}
