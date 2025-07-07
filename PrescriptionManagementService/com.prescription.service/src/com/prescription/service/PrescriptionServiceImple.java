package com.prescription.service;

import java.util.Scanner;

public class PrescriptionServiceImple implements IPrescriptionService {

	Scanner sc = new Scanner(System.in);

	private String patientName;
	public String medicine;
	public String dosage;
	public int qty;

	@Override
	public void prescribe() {
		System.out.print("[Prescription Management System] Prescription for: ");
		patientName = sc.nextLine();

		System.out.print("[Prescription Management System] Enter Medicine: ");
		medicine = sc.nextLine(); 

		System.out.print("[Prescription Management System] Enter Dosage: ");
		dosage = sc.nextLine(); 

		System.out.print("[Prescription Management System] Enter Quantity: ");
		
		// Ensure valid integer input for quantity
		while (true) { 
			try {
				qty = Integer.parseInt(sc.nextLine().trim()); 
				break; 
			} catch (NumberFormatException e) {
				System.out.println("[Error] Please enter a valid integer for quantity.");
				System.out.print("[Prescription Management System] Enter Quantity again: ");
			}
		}

		System.out.println("\n[Prescription Management System] Processing prescription for " + patientName);
		System.out.println("[Prescription Management System] Sending prescription to Pharmacy System...");
	}

	// Getter methods to access prescription details
	public String getPatientName() {
		return this.patientName;
	}

	public String getMedicine() {
		return this.medicine;
	}

	public String getDosage() {
		return this.dosage;
	}

	public int getQty() {
		return this.qty;
	}
}
