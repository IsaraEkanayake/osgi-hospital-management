package com.prescription.service;

public interface IPrescriptionService {
    void prescribe();
    String getPatientName();
    String getMedicine();
    String getDosage();
    int getQty();
}
