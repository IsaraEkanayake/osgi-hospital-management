package com.dosage.service;

import java.util.concurrent.TimeUnit;
import com.prescription.service.PrescriptionServiceImple;

public class DosageServiceImpl implements IDosageService {

    // Method to process dosage instructions based on prescription details
    public boolean Dosage(PrescriptionServiceImple prescService) {

        System.out.println("[Dosage Tracking System] Receiving dosage instructions for " 
                + prescService.getPatientName() + "...");

        // Determine dosage instructions based on prescribed dosage
        switch (prescService.getDosage()) {
            case "500mg":
                System.out.println("[Dosage Tracking System] Dosage Instructions: Take once a day.");
                break;
            case "400mg":
                System.out.println("[Dosage Tracking System] Dosage Instructions: Take once in the morning and once in the evening.");
                break;
            case "300mg":
                System.out.println("[Dosage Tracking System] Dosage Instructions: Take twice a day.");
                break;
            case "200mg":
                System.out.println("[Dosage Tracking System] Dosage Instructions: Take three times a day.");
                break;
            default:
                System.out.println("[Dosage Tracking System] Dosage Instructions: Follow your doctor's prescription.");
                break;
        }

        // Simulate updating dosage records
        System.out.println("[Dosage Tracking System] Updating dosage records for " 
                + prescService.getPatientName() + "...");
        
        try {
            TimeUnit.SECONDS.sleep(3); // Pause for 3 seconds to simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            System.out.println("[Pharmacy System] An error occurred while updating dosage records.");
        }
        
        System.out.println("[Dosage Tracking System] Dosage tracking successfully updated.");
        return true;
    }
}
