package ca.concordia.soen6441.d20.item;

public class DamageBonus {
	int point;
	
	public DamageBonus(){
		point = 0;
	}
	
	public DamageBonus(int point){
		this.point = point;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
	public void showPoint(){
		System.out.println("DamageBonusPoint:" + point);
	}
}
