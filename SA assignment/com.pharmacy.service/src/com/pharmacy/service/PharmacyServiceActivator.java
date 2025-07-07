package com.pharmacy.service;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.dosage.service.DosageServiceImpl;
import com.prescription.service.IPrescriptionService;
import com.prescription.service.PrescriptionServiceImple;

public class PharmacyServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	private boolean value; // Stores whether the requested medicine is available

	@Override
	public void start(BundleContext context) throws Exception {

		serviceReference = context.getServiceReference(IPrescriptionService.class.getName());

		if (serviceReference == null) {
			System.err.println("[Pharmacy System] ERROR: IPharmacyService reference is null!");
			return;
		}

		IPrescriptionService pharmacyService = (IPrescriptionService) context.getService(serviceReference);

		if (pharmacyService == null) {
			System.err.println("[Pharmacy System] ERROR: Failed to retrieve IPharmacyService instance!");
			return;
		}

		System.out.println("[Pharmacy System] Service started successfully.");
		System.out.println("[Pharmacy System] Checking for pending prescriptions...");
		System.out.println("[Pharmacy System] Do you have any prescription requests? (Yes/No)");

		Scanner sc = new Scanner(System.in);
		String prescReq;
		System.out.print("\n[Prescription Management System] ");
		prescReq = sc.nextLine();

		// If the user has a prescription request, proceed with prescription processing
		if (prescReq.equalsIgnoreCase("yes")) {
			System.out.println("\n[Pharmacy System] Please provide prescription details.");

			PrescriptionServiceImple prescService = new PrescriptionServiceImple();
			prescService.prescribe(); // Collects prescription details from the user

			this.MainAction(prescService); // Pass prescription details to process further actions
		} else {
			System.out.println("[Pharmacy System] No prescription request received. Exiting system.");
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[Pharmacy System] Stopped.");
		context.ungetService(serviceReference);
	}

	// Displays available medicines and checks if the prescribed medicine is in
	// stock
	private float showMedicines(PrescriptionServiceImple info) {
		MedicineList list = new MedicineList();
		list.showAvailableMedicines();
		value = list.checkAvailability(info.getMedicine(), info.getDosage());
		return list.getPrice(); // Returns the price of the found medicine
	}

	// Handles the main pharmacy workflow after retrieving prescription details
	public void MainAction(PrescriptionServiceImple info) {
		System.out.println(
				"[Pharmacy System] Checking inventory for " + info.getMedicine() + " " + info.getDosage() + "...\n");

		float price = showMedicines(info); // Fetches price and availability status

		Scanner sc = new Scanner(System.in);
		if (value) { // If medicine is available, proceed with payment option
			System.out.println(
					"[Pharmacy System] Would you like to proceed with the payment for your medicines? (Yes/No)");
			System.out.print("[Prescription Management System] Prescription Management System :");
			String decision = sc.nextLine();

			// If user agrees to purchase, calculate the total price
			if (decision.equalsIgnoreCase("yes")) {
				calTotal(price, info.getQty(), info);
			} else {
				System.out.println("[Pharmacy System] You chose not to proceed with the payment. Order canceled.");
			}
		}
	}

	// Calculates and displays the total cost of the prescribed medicine
	public void calTotal(float price, int qty, PrescriptionServiceImple info) {
		System.out.println("[Pharmacy System] Calculating total price...");

		float totalPrice = price * qty;

		// Displaying receipt details
		System.out.println("\n======================================");
		System.out.println("             PHARMACY RECEIPT         ");
		System.out.println("======================================");
		System.out.printf("%-20s : %d%n", "Quantity", qty);
		System.out.printf("%-20s : LKR %.2f%n", "Unit Price", price);
		System.out.printf("%-20s : LKR %.2f%n", "Total Price", totalPrice);
		System.out.println("--------------------------------------");
		System.out.println("Thank you for your purchase!");
		System.out.println("======================================\n");

		// Confirmation message
		System.out.println("[Pharmacy System] Payment of LKR " + totalPrice + " received for " + info.getMedicine()
				+ "(" + info.getDosage() + ").");
		System.out.println("[Pharmacy System] Prescription dispensed successfully.");
		System.out.println("[Pharmacy System] Notifying Dosage Tracking System...\n");

		endAction(info); // Proceed to dosage tracking system
	}

	// Notifies the Dosage Tracking System to track the prescribed dosage
	public void endAction(PrescriptionServiceImple info) {
		DosageServiceImpl dosService = new DosageServiceImpl();
		boolean decision = dosService.Dosage(info);

		// Confirms if the prescription process is completed successfully
		if (decision) {
			System.out.println("\n[Prescription Management System] Prescription process completed successfully.");
		} else {
			System.out.println("\n[Prescription Management System] Prescription process was not completed.");
		}
	}
}
