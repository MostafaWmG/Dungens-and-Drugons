package ca.concordia.soen6441.d20.item;

public class DamageBonus implements LevelUp{
	int point;
	
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

	@Override
	public void update(int modifier) {
		setPoint(modifier);
	}
}
