package ca.concordia.soen6441.d20.item;

public class AttackBonus implements LevelUp{
	int point;
		
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

	@Override
	public void update(int modifier) {
		setPoint(modifier);
	}
}
