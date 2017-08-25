package models;

public class CostResult extends Cost {

	public double amount=0;
	

	public CostResult(Cost cost, Double amount ){
		super(cost);
		this.amount = amount;
	}

}
