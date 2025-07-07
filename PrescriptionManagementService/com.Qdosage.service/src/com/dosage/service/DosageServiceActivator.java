package com.dosage.service;

import com.prescription.service.IPrescriptionService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


public class DosageServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {

	    serviceReference = context.getServiceReference(IPrescriptionService.class.getName());
	    
	    if (serviceReference == null) {
	        System.err.println("[Dosage Tracking System] ERROR: IPrescriptionService reference is null!");
	        return;
	    }

	    IPrescriptionService prescriptionService = (IPrescriptionService) context.getService(serviceReference);

	    if (prescriptionService == null) {
	        System.err.println("[Dosage Tracking System] ERROR: Failed to retrieve IPrescriptionService instance!");
	    }
	}
	@Override
	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Dosage Tracking System] Stopped.");
		context.ungetService(serviceReference);
	}

}
