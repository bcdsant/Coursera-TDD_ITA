package br.com.coursera.locadora.modelo;

public abstract class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	protected static final int DEFAULT_RENTER_POINTS = 1;
	protected static final int NEW_RELEASE_RENTER_POINTS = DEFAULT_RENTER_POINTS + 1;
	private String _title;

	public static Movie createMovie(String title, int priceCode) {
		if (priceCode == REGULAR)
			return new Regular(title);
		if (priceCode == CHILDRENS)
			return new Childrens(title);
		if (priceCode == NEW_RELEASE)
			return new NewRelease(title);
		throw new RuntimeException("Não existe esse tipo de filme.");
	}

	protected Movie(String title) {
		_title = title;
	}

	public String getTitle() {
		return _title;
	}

	public abstract double getAmount(int daysRented);

	public int getFrequentRenterPoints(int daysRented) {
		return DEFAULT_RENTER_POINTS;
	};
}