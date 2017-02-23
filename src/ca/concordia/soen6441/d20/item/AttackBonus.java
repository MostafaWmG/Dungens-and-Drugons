package ca.concordia.soen6441.d20.item;

public class AttackBonus {
	int point;
	
	public AttackBonus(){
		point = 0;
	}
	
	public AttackBonus(int point){
		this.point = point;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
	public void showPoint(){
		System.out.println("AttackBonusPoint:" + point);
	}
}
