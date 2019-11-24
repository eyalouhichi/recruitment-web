package fr.d2factory.libraryapp.member;

public class Resident extends Member {

	public Resident(float wallet, String name) {
		super(wallet, name);
	}

	@Override
	public void payBook(int numberOfDays) {
		float	sum = 0;
		if (numberOfDays <= 60) {
			sum = (float) ((numberOfDays)*0.1);
		
		}
		else {
			sum = (float) ((numberOfDays-60)*0.2 + 6);
			
		}
		if (super.getWallet() >= sum) {
			super.setWallet(super.getWallet()-sum) ;	
			System.out.println(sum  +" Euro is taken from your wallet");
		}
		else {
			System.out.println("you paid " + super.getWallet() + "Euro. you still should pay" + (sum - super.getWallet()) +" Euro" );
		}
	}

	

}
