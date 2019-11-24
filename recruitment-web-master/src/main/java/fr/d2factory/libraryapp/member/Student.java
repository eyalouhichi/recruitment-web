package fr.d2factory.libraryapp.member;

public class Student extends Member{

	
	private int YearOfStudy;
	
	
	@Override
	public void payBook(int numberOfDays) {
	float	sum = 0;
		if (numberOfDays <= 30) {
			sum = (float) ((numberOfDays)*0.1);
			if ((YearOfStudy == 1) && (numberOfDays >15)){
				sum -= 1.5;
			}
			if ((YearOfStudy == 1) && (numberOfDays <=15)){
				sum = 0;
			}
		}
		else {
			sum = (float) ((numberOfDays-30)*0.15 + 3);
			if (YearOfStudy == 1){
				sum -= 1.5;
			}
	
		}
		if (super.getWallet() >= sum) {
			super.setWallet(super.getWallet()-sum) ;	
			System.out.println(sum  +" Euro is taken from your wallet");
		}
		else {
			System.out.println("you paid: " + super.getWallet() + " Euro. You still should pay" + (sum - super.getWallet()) +" Euro");
		}
	}
	public int getYearOfStudy() {
		return YearOfStudy;
	}
	public void setYearOfStudy(int yearOfStudy) {
		YearOfStudy = yearOfStudy;
	}

	public Student(int yearOfStudy, float wallet,String name) {
		super(wallet,name);
		YearOfStudy = yearOfStudy;
	
	}
	

}
