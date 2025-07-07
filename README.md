# Hospital Management System (OSGi) — Prescription Management Module

## Overview
This is a CLI-based Prescription Management System built using the Eclipse Equinox OSGi framework. It is part of a larger Hospital Management System project designed to practice Microkernel Architecture by implementing modular and extensible services.

The Prescription Management Module consists of three core services that communicate using the publisher-subscriber pattern:

- **Dosage Service** (Publisher)
- **Prescription Service** (Publisher)
- **Pharmacy Service** (Subscriber)

## Features
The module includes the following services:

#### 1) Dosage Service
- Calculates and validates medicine dosages.
- Publishes dosage events to subscribers.

#### 2) Prescription Service
- Manages creation, updating, and tracking of prescriptions.
- Publishes prescription events for other services to consume.

#### 3) Pharmacy Service
- Subscribes to dosage and prescription events.
- Handles medication dispensing and inventory updates.

## Technologies Used
- **Java**
- **Eclipse Equinox - OSGi Framework**
- **Microkernel Architecture**
- **Maven** (for build and dependency management)

## Project Structure

```
HospitalManagementSystem/
├── DosageService/ # Dosage calculations and event publishing
├── PrescriptionService/ # Prescription management and event publishing
└── PharmacyService/ # Subscriber service for pharmacy operations
```

## Running on Eclipse
1. Open **Eclipse IDE** and install the **OSGi Development Tools Plugin** if not already installed.
2. Import the project into Eclipse:
   - Click **File** → **Import** → **Existing Maven Projects**.
   - Select the project folder and click **Finish**.
3. Configure the **Run Configuration**:
   - Go to **Run** → **Run Configurations**.
   - Create a new **OSGi Framework** configuration.
   - Select all required bundles (DosageService, PrescriptionService, PharmacyService).
   - Click **Apply** and then **Run**.
4. Open the OSGi console in Eclipse to interact with the system.

## Usage
- Use the CLI interface in the OSGi console to create prescriptions, update dosages, and process pharmacy dispensing.
- Ensure all required bundles are installed and active before performing operations.

## Contributing
Feel free to fork this repository and submit pull requests for improvements and new features.
