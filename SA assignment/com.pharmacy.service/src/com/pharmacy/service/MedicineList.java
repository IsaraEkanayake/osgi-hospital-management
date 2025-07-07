package com.pharmacy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MedicineList implements IPharmacyService {

    private List<Medicine> medicines; // Stores available medicines
    private float lastFoundPrice = 0.0f; // Stores the price of the last found medicine

    // Initializes the medicine list with predefined data
    public MedicineList() {
        medicines = new ArrayList<>();
        medicines.add(new Medicine(101, "Paracetamol", "500mg", 813));
        medicines.add(new Medicine(102, "Paracetamol", "400mg", 715));
        medicines.add(new Medicine(103, "Paracetamol", "300mg", 650));
        medicines.add(new Medicine(104, "Paracetamol", "200mg", 585));
        medicines.add(new Medicine(105, "Ibuprofen", "400mg", 975));
        medicines.add(new Medicine(106, "Ibuprofen", "300mg", 894));
        medicines.add(new Medicine(107, "Ibuprofen", "200mg", 813));
        medicines.add(new Medicine(108, "Amoxicillin", "500mg", 1463));
        medicines.add(new Medicine(109, "Amoxicillin", "300mg", 1300));
        medicines.add(new Medicine(110, "Amoxicillin", "250mg", 1235));
    }

    // Displays the available medicines in a formatted table
    public void showAvailableMedicines() {
        System.out.println("---------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Medicine Name", "Dosage", "Price (LKR)");
        System.out.println("---------------------------------------------------");
        for (Medicine medicine : medicines) {
            System.out.printf("%-10d %-20s %-10s LKR %-9.2f%n", medicine.getId(), medicine.getName(),
                    medicine.getDosage(), medicine.getPrice());
        }
        System.out.println("---------------------------------------------------");
    }

    // Checks if the requested medicine with the specified dosage is available
    public boolean checkAvailability(String medicineType, String medicineDosage) {
        System.out.println("[Pharmacy System] Checking....");

        try {
            TimeUnit.SECONDS.sleep(3); // Simulates processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[Pharmacy System] An error occurred while checking.");
            return false;
        }

        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(medicineType) && medicine.getDosage().equals(medicineDosage)) {
                System.out.println("[Pharmacy System] Item found in stock.");
                lastFoundPrice = medicine.getPrice(); // Store price for later retrieval
                return true;
            }
        }
        System.out.println("[Pharmacy System] Item not found.");
        return false;
    }

    // Returns the price of the last found medicine
    public float getPrice() {
        return lastFoundPrice;
    }

    // Represents a medicine with an ID, name, dosage, and price
    class Medicine {
        private int id;
        private String name;
        private String dosage;
        private float price;

        public Medicine(int id, String name, String dosage, float price) {
            this.id = id;
            this.name = name;
            this.dosage = dosage;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDosage() {
            return dosage;
        }

        public float getPrice() {
            return price;
        }
    }
}
