package com.pharmacy.service;

public interface IPharmacyService {

	void showAvailableMedicines();

	boolean checkAvailability(String medicineType, String medicineDosage);

	float getPrice();

}
