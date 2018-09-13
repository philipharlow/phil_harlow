package model.Pokemon;

import java.io.Serializable;

public abstract class Ability implements Serializable{
	private int amount;
	private String name;
	
	public Ability() {
		amount = 0;
	}
	
	public int getAmount() {
		if(amount == 0) {
			System.out.println("amount has not been set");
			return 0;
		}
		return this.amount;
	}
	
	public void setAmount(int x) {
		this.amount = x;
	}
	
	
	
}
