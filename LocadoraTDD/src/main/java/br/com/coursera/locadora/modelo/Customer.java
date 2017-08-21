package br.com.coursera.locadora.modelo;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();
	private int frequentRenterPoints = 0;
	private double totalAmount = 0.0;

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.addElement(rental);
		frequentRenterPoints += rental.getFrequentRenterPoints();
		totalAmount += rental.getAmount();
	}

	public String getName() {
		return name;
	}

	public String statement() {
		Enumeration<Rental> rentals = this.rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getAmount()) + "\n";
		}
		result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}

	public int getTotalFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

}