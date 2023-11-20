package cms.core;

public enum ServicePlan {
	SILVER(1000), 
	GOLD(2000),
	DIAMOND(5000),
	PLATINUM(10000);
	
	
	
	private double planAmount;
	
	private ServicePlan(double planAmount) {
		this.planAmount = planAmount;
	}

	public double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(double planAmount) {
		this.planAmount = planAmount;
	}
	
	@Override
	public String toString() {
		return name() + " " + "Amount:"+planAmount;
	}

}
