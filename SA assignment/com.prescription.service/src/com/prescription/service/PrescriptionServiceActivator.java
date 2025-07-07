package com.prescription.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class PrescriptionServiceActivator implements BundleActivator {

	ServiceRegistration prescServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("[Prescription Management System] Service started.");
	
		 IPrescriptionService prescService = new PrescriptionServiceImple();
		 prescServiceRegistration = context.registerService(IPrescriptionService.class.getName(), prescService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("[Prescription Management System] Service stopped.");
		prescServiceRegistration.unregister();
	}

}
