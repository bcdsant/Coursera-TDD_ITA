package br.com.coursera.locadora.modelo;

public class NewRelease extends Movie {

	public NewRelease(String title) {
		super(title);
	}

	@Override
	public double getAmount(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int getFrequentRenterPoints(int daysRented) {
		if (daysRented > 1)
			return NEW_RELEASE_RENTER_POINTS;
		return DEFAULT_RENTER_POINTS;
	}

}
