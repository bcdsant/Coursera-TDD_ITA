package br.com.coursera.locadora.modelo;

public class Childrens extends Movie {

	public Childrens(String title) {
		super(title);
	}

	@Override
	public double getAmount(int daysRented) {
		double thisAmount = 1.5;
		if (daysRented > 3)
			thisAmount += (daysRented - 3) * 1.5;
		return thisAmount;
	}

}
