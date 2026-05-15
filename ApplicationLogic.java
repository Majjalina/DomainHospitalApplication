/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Iterator;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author eebej
 */
/**
 * Manages the application logic for the surgery application. This class handles
 * operations such as loading and saving data for Patients, Staff, Doctors,
 * Appointments, and Doctor Schedules. It also provides methods to manage these records.
 * Implements Serializable to allow instances to be serialized.
 * 
 * This class initializes HashMaps and Vectors for storing records and 
 * loads data from disk upon application start.
 * 
 */
public final class ApplicationLogic implements Serializable {

    // HashMaps to store records
    private static Map<String, Patient> myPatient = new HashMap<>();
    private static Map<String, Staff> myStaff = new HashMap<>();
    private static Map<String, Doctor> myDoctor = new HashMap<>();

    // Vectors to store appointments and doctor schedules
    public static Vector myAppointmentVector = new Vector();
    public static Vector myDoctorScheduleVector = new Vector();

    // File paths for storing data
    private final String patientFileName = "Patients.obj";
    private final String staffFileName = "Staff.obj";
    private final String doctorFileName = "Doctors.obj";
    private final String doctorScheduleFileNameVector = "DoctorScheduleVector.obj";
    private final String appointmentFileVector = "AppointmentsFile.obj";

    /**
     * Default constructor. Executes during the application initialization and
     * loads data from disk into HashMaps and Vectors. If loading fails, default
     * records are added.
     */
    public ApplicationLogic() {
        // Load patient records
        if (!loadFromDisk_Patient(patientFileName)) {
            addPatientRecord("1", "Emily", "Bonnici", 34, "Female", null, "NA", "NA", "No Special Requests");
        }

        // Load staff records
        if (!loadFromDisk_Staff(staffFileName)) {
            addStaffRecord("Jaina ", "Proudmoore", 34, "Female", "Administration", "1", "Receptionist");
        }

        // Load doctor records
        if (!loadFromDisk_Doctor(doctorFileName)) {
            addDoctorRecord("Medic", "Mc Medicface", 40, "Male", "Operations", "2", "Doctor", "Neurologist");
        }

        // Load doctor schedule records
        if (!loadFromDisk_DoctorScheduleVector(doctorScheduleFileNameVector)) {
            //JOptionPane.showMessageDialog(null, "Doctor Schedule Vector was not loaded");
            myDoctorScheduleVector = new Vector();
        }

        // Load appointment records
        if (!loadFromDisk_Appointments(appointmentFileVector)) {
            addAppointmentVector(
                "1-13/08/2024", 
                "1", 
                "2", 
                "13/08/2024", 
                "09:00AM - 09:30AM", 
                "Vertigo", 
                "Bed rest for a few days.", 
                "Ativan (lorazepam)", 
                "Finish", 
                "Attended"
            );
        }
    }

    /**
     * Adds a new patient record to the system and saves it to disk.
     * 
     * @param patientID Unique identifier for the patient
     * @param patientName First name of the patient
     * @param patientSurname Last name of the patient
     * @param patientAge Age of the patient
     * @param patientGender Gender of the patient
     * @param patientDOB Date of birth of the patient
     * @param patientPrescription Prescription history of the patient
     * @param patientAllergies Known allergies of the patient
     * @param patientRequest Special requests from the patient
     */
    public void addPatientRecord(String patientID, String patientName, String patientSurname, int patientAge,
                                 String patientGender, Date patientDOB, String patientPrescription, 
                                 String patientAllergies, String patientRequest) {
        // Add the patient record to the HashMap
        myPatient.put(patientID, new Patient(
            patientID, patientName, patientSurname, patientAge, patientGender,
            patientDOB, patientPrescription, patientAllergies, patientRequest
        ));

        // Save the updated patient records to disk
        saveToDisk_Patient(patientFileName);
    }

        /**
     * Edits or adds a patient record to the system. If the patient ID already exists, the record is updated.
     * Saves the updated records to disk.
     * 
     * @param patientID Unique identifier for the patient
     * @param patientName 
     * @param patientSurname
     * @param patientAge 
     * @param patientGender
     * @param patientDOB
     * @param patientPrescription Prescription history of the patient
     * @param patientAllergies
     * @param patientRequest
     */
    public void editPatientRecord(String patientID, String patientName, String patientSurname, int patientAge,
                                  String patientGender, Date patientDOB, String patientPrescription, 
                                  String patientAllergies, String patientRequest) {
        myPatient.put(patientID, new Patient(
            patientID, patientName, patientSurname, patientAge, patientGender, 
            patientDOB, patientPrescription, patientAllergies, patientRequest
        ));

        // Save the updated patient records to disk
        saveToDisk_Patient(patientFileName);
    }

    /**
     * Adds a staff record to the system and saves the updated records to disk.
     * 
     * @param personName 
     * @param personSurname
     * @param personAge
     * @param personGender
     * @param staffType
     * @param staffID 
     * @param staffQualification 
     */
    public void addStaffRecord(String personName, String personSurname, int personAge, String personGender, 
                               String staffType, String staffID, String staffQualification) {
        myStaff.put(staffID, new Staff(
            personName, personSurname, personAge, personGender, staffType, staffID, staffQualification
        ));

        // Save the updated staff records to disk
        saveToDisk_Staff(staffFileName);
    }

    /**
     * Edits or adds a staff record to the system. If the staff ID already exists, the record is updated.
     * Saves the updated records to disk.
     * 
     * @param personName
     * @param personSurname
     * @param personAge
     * @param personGender
     * @param staffType 
     * @param staffID
     * @param staffQualification
     */
    public void editStaffRecord(String personName, String personSurname, int personAge, String personGender, 
                                String staffType, String staffID, String staffQualification) {
        myStaff.put(staffID, new Staff(
            personName, personSurname, personAge, personGender, staffType, staffID, staffQualification
        ));

        // Save the updated staff records to disk
        saveToDisk_Staff(staffFileName);
    }

    /**
     * Adds a doctor record to the system. Saves the updated records to disk.
     * 
     * @param personName
     * @param personSurname
     * @param personAge
     * @param personGender
     * @param staffType
     * @param staffID
     * @param staffQualification
     * @param doctorSpecialisation
     */
    public void addDoctorRecord(String personName, String personSurname, int personAge, String personGender, 
                                String staffType, String staffID, String staffQualification, 
                                String doctorSpecialisation) {
        myDoctor.put(staffID, new Doctor(
            personName, personSurname, personAge, personGender, staffType, staffID, 
            staffQualification, doctorSpecialisation
        ));

        // Save the updated doctor records to disk
        saveToDisk_Doctor(doctorFileName);
    }

    /**
     * Edits or adds a doctor record to the system. If the doctor ID already exists, the record is updated.
     * Saves the updated records to disk.
     * 
     * @param personName 
     * @param personSurname
     * @param personAge
     * @param personGender
     * @param staffType
     * @param staffID
     * @param staffQualification
     * @param doctorSpecialisation
     */
    public void editDoctorRecord(String personName, String personSurname, int personAge, String personGender, 
                                 String staffType, String staffID, String staffQualification, 
                                 String doctorSpecialisation) {
        myDoctor.put(staffID, new Doctor(
            personName, personSurname, personAge, personGender, staffType, staffID, 
            staffQualification, doctorSpecialisation
        ));

        // Save the updated doctor records to disk
        saveToDisk_Doctor(doctorFileName);
    }

    /**
     * Adds a doctor's schedule to the system and saves it to disk.
     * 
     * @param doctorscheduleDoctorID
     * @param doctorscheduleAppDate 
     * @param doctorscheduleAppTimeSlot 
     * @param doctorschedule_appointmentAvailability
     */
    public void addDoctorScheduleVector(String doctorscheduleDoctorID, String doctorscheduleAppDate, 
                                        String doctorscheduleAppTimeSlot, boolean doctorschedule_appointmentAvailability) {
        myDoctorScheduleVector.add(new DoctorSchedule(
            doctorscheduleDoctorID, doctorscheduleAppDate, doctorscheduleAppTimeSlot, 
            doctorschedule_appointmentAvailability
        ));

        // Save the updated doctor schedules to disk
        saveToDisk_DoctorScheduleVector(doctorScheduleFileNameVector);
    }

    /**
     * Adds an appointment record to the system and saves it to disk.
     * 
     * @param appointment_appointmentID 
     * @param appointment_patientID
     * @param appointment_doctorID
     * @param appointment_date 
     * @param appointment_timeslot
     * @param appointment_patientSymptoms
     * @param appointment_doctorNotes
     * @param appointment_doctorMedications
     * @param appointment_appointmentStatus
     * @param appointment_attendanceStatus
     */
    public void addAppointmentVector(String appointment_appointmentID, String appointment_patientID, 
                                     String appointment_doctorID, String appointment_date, 
                                     String appointment_timeslot, String appointment_patientSymptoms, 
                                     String appointment_doctorNotes, String appointment_doctorMedications, 
                                     String appointment_appointmentStatus, String appointment_attendanceStatus) {
        myAppointmentVector.add(new Appointment(
            appointment_appointmentID, appointment_patientID, appointment_doctorID, appointment_date, 
            appointment_timeslot, appointment_patientSymptoms, appointment_doctorNotes, 
            appointment_doctorMedications, appointment_appointmentStatus, appointment_attendanceStatus
        ));

        // Save the updated appointments to disk
        saveToDisk_AppointmentVector(appointmentFileVector);
    }

      /**
     * Modify doctor's schedule by removing an entry from the schedule vector 
     * at the specified index and saves the updated vector to disk
     * 
     * @param record Index of the record to be removed from the schedule vector
     */
    public void modifyDoctorScheduleVector(int record) {
        myDoctorScheduleVector.removeElementAt(record);

        // Save the updated doctor schedule vector to disk
        saveToDisk_DoctorScheduleVector(doctorScheduleFileNameVector);
    }

    /**
     * Retrieves a Patient object by its ID
     * 
     * @param getPatientData The ID of the patient to retrieve
     * @return Patient object corresponding to the given ID, or null if not found
     */
    public static Patient GetPatientData(String getPatientData) {
        return myPatient.get(getPatientData);
    }

    /**
     * Retrieves a Staff object by its ID.
     * 
     * @param getStaffData The ID of the staff member to retrieve
     * @return Staff object corresponding to the given ID, or null if not found
     */
    public static Staff GetStaffData(String getStaffData) {
        return myStaff.get(getStaffData);
    }

    /**
     * Retrieves a Doctor object by its ID.
     * 
     * @param getDoctorData The ID of the doctor to retrieve
     * @return Doctor object corresponding to the given ID, or null if not found
     */
    public static Doctor GetDoctorData(String getDoctorData) {
        return myDoctor.get(getDoctorData);
    }

    /**
     * Checks if a Patient ID already exists in the system.
     * 
     * @param patientID The ID of the patient to check
     * @return true if the patient ID exists, false otherwise
     */
    public static boolean GetPatientID(String patientID) {
        return myPatient.containsKey(patientID);
    }

    /**
     * Checks if a Staff ID already exists in the system.
     * 
     * @param staffID The ID of the staff member to check
     * @return true if the staff ID exists, false otherwise
     */
    public static boolean GetStaffID(String staffID) {
        return myStaff.containsKey(staffID);
    }

    /**
     * Checks if a Doctor ID already exists in the system.
     * 
     * @param doctorID The ID of the doctor to check
     * @return true if the doctor ID exists, false otherwise
     */
    public static boolean GetDoctorID(String doctorID) {
        return myDoctor.containsKey(doctorID);
    }

    /**
     * Retrieves a list of all patient IDs in the system.
     * 
     * @return Array of strings containing patient IDs
     */
    public static String[] listPatientName() {
        String p = "";

        for (String key : myPatient.keySet()) {
            p = key + "\n" + p;
        }

        return p.split("\n");
    }

    /**
     * Retrieves a list of patient IDs filtered by the given ID prefix.
     * 
     * @param Id The prefix to filter patient IDs by
     * @return Array of strings containing matching patient IDs
     */
    public static String[] listPatientNameByName(String Id) {
        String p = Id;

        for (String key : myPatient.keySet()) {
            p = key + "\n" + p;
        }

        return p.split("\n");
    }

    /**
     * Retrieves a list of all staff IDs stored in the system.
     * 
     * @return Array of strings containing all staff IDs
     */
    public static String[] listStaffName() {
        String p = "";

        for (String key : myStaff.keySet()) {
            p = key + "\n" + p;
        }

        return p.split("\n");
    }

    /**
     * Retrieves a list of all doctor IDs stored in the system.
     * 
     * @return Array of strings containing all doctor IDs
     */
    public static String[] listDoctorName() {
        String p = "";

        for (String key : myDoctor.keySet()) {
            p = key + "\n" + p;
        }

        return p.split("\n");
    }

    /**
     * Save the patient records to disk using serialization
     * 
     * @param path The file path where patient data should be saved
     * @return true if the data was saved successfully, false otherwise
     */
    public boolean saveToDisk_Patient(String path) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(myPatient);
            out.close(); // Ensure the stream is closed
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Unsuccessful save
        }
        return true; // Successful save
    }

    /**
     * Save the staff records to disk using serialization.
     * 
     * @param path The file path where staff data should be saved
     * @return true if the data was saved successfully, false otherwise
     */
    public boolean saveToDisk_Staff(String path) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(myStaff);
            out.close(); // Ensure the stream is closed
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Unsuccessful save
        }
        return true; // Successful save
    }

    /**
     * Save the doctor records to disk using serialization.
     * 
     * @param path The file path where doctor data should be saved
     * @return true if the data was saved successfully, false otherwise
     */
    public boolean saveToDisk_Doctor(String path) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(myDoctor);
            out.close(); // Ensure the stream is closed
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Unsuccessful save
        }
        return true; // Successful save
    }

    /**
     * Save the doctor schedule vector to disk using serialization.
     * 
     * @param path The file path where doctor schedule data should be saved
     * @return true if the data was saved successfully, false otherwise
     */
    public boolean saveToDisk_DoctorScheduleVector(String path) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(myDoctorScheduleVector);
            out.close(); // Ensure the stream is closed
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Unsuccessful save
        }
        return true; // Successful save
    }

    /**
     * Saves the appointment vector to disk using serialization.
     * 
     * @param path The file path where appointment data should be saved
     * @return true if the data was saved successfully, false otherwise
     */
    public boolean saveToDisk_AppointmentVector(String path) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(myAppointmentVector);
            out.close();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR encountered while saving: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Loads patient records from disk using deserialization.
     * 
     * @param path The file path from which patient data should be loaded
     * @return true if the data was loaded successfully, false otherwise
     */
    public boolean loadFromDisk_Patient(String path) {
        if (path != null) {
            try {
                File file = new File(path);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myPatient = (HashMap<String, Patient>) in.readObject();
                in.close();
                return true;
            } catch (FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(null, "ERROR: " + fnfe.getMessage());
                return false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     * Loads staff records from disk using deserialization.
     * 
     * @param path The file path from which staff data should be loaded
     * @return true if the data was loaded successfully, false otherwise
     */
    public boolean loadFromDisk_Staff(String path) {
        if (path != null) {
            try {
                File file = new File(path);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myStaff = (HashMap<String, Staff>) in.readObject();
                in.close();
                return true; // Successfully loaded
            } catch (FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(null, "ERROR: File not found: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (Exception e) { // Any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false; // Invalid path
    }

    /**
     * Loads doctor records from disk using deserialization.
     * 
     * @param path The file path from which doctor data should be loaded
     * @return true if the data was loaded successfully, false otherwise
     */
    public boolean loadFromDisk_Doctor(String path) {
        if (path != null) {
            try {
                File file = new File(path);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myDoctor = (HashMap<String, Doctor>) in.readObject();
                in.close();
                return true; // Successfully loaded
            } catch (FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(null, "ERROR: File not found: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (IOException | ClassNotFoundException e) { // Any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false; // Invalid path
    }

    /**
     * Loads doctor schedule records from disk using deserialization.
     * 
     * @param path The file path from which doctor schedule data should be loaded
     * @return true if the data was loaded successfully, false otherwise
     */
    public boolean loadFromDisk_DoctorScheduleVector(String path) {
        if (path != null) {
            try {
                File file = new File(path);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myDoctorScheduleVector = (Vector) in.readObject();
                in.close();
                return true; // Successfully loaded
            } catch (FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(null, "ERROR: File not found: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (IOException | ClassNotFoundException e) { // Any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false; // Invalid path
    }

    /**
     * Loads appointment records from disk using deserialization.
     * 
     * @param path The file path from which appointment data should be loaded
     * @return true if the data was loaded successfully, false otherwise
     */
    public boolean loadFromDisk_Appointments(String path) {
        if (path != null) {
            try {
                File file = new File(path);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myAppointmentVector = (Vector) in.readObject();
                in.close();
                return true; // Successfully loaded
            } catch (FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(null, "ERROR: File not found: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } catch (IOException | ClassNotFoundException e) { // Any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false; // Invalid path
    }

    /**
     * Searches for a doctor schedule by doctor ID.
     * 
     * @param doctorID The ID of the doctor
     * @return The first matching DoctorSchedule object, or null if no match is found
     */
    public DoctorSchedule searchDoctorSchedule(String doctorID) {
        for (Object obj : myDoctorScheduleVector) {
            DoctorSchedule ds = (DoctorSchedule) obj;
            if (doctorID.equalsIgnoreCase(ds.getDoctorID())) {
                return ds; // Found
            }
        }
        return null; // Not found
    }

    /**
     * Searches for a doctor schedule by doctor ID, date, and time slot.
     * 
     * @param doctorID The ID of the doctor
     * @param scheduleDate The date of the schedule
     * @param scheduleTimeslot The time slot of the schedule
     * @return The matching DoctorSchedule object, or null if no match is found
     */
    public DoctorSchedule searchDoctorSchedule(String doctorID, String scheduleDate, String scheduleTimeslot) {
        for (Object obj : myDoctorScheduleVector) {
            DoctorSchedule ds = (DoctorSchedule) obj;
            if (doctorID.equalsIgnoreCase(ds.getDoctorID()) && 
                scheduleDate.equalsIgnoreCase(ds.getAvailableAppointmentDate()) && 
                scheduleTimeslot.equalsIgnoreCase(ds.getAvailableAppointmentTimeSlot())) {
                return ds; // Found
            }
        }
        return null; // Not found
    }

    /**
     * Searches for and deletes a doctor schedule by doctor ID, date, and time slot.
     * 
     * @param doctorID The ID of the doctor
     * @param scheduleDate The date of the schedule
     * @param scheduleTimeslot The time slot of the schedule
     */
    public void searchAndDeleteDoctorSchedule(String doctorID, String scheduleDate, String scheduleTimeslot) {
        for (int i = 0; i < myDoctorScheduleVector.size(); i++) {
            DoctorSchedule ds = (DoctorSchedule) myDoctorScheduleVector.get(i);
            if (doctorID.equalsIgnoreCase(ds.getDoctorID()) && 
                scheduleDate.equalsIgnoreCase(ds.getAvailableAppointmentDate()) && 
                scheduleTimeslot.equalsIgnoreCase(ds.getAvailableAppointmentTimeSlot())) {
                myDoctorScheduleVector.remove(i); // Found and removed
                return;
            }
        }
    }

    /**
     * Searches for and deletes an appointment by appointment ID.
     * 
     * @param appID The ID of the appointment to delete
     */
    public void searchAndDeleteAppointment(String appID) {
        for (int i = 0; i < myAppointmentVector.size(); i++) {
            Appointment app = (Appointment) myAppointmentVector.get(i);
            if (appID.equalsIgnoreCase(app.getAppointment_AppointmentID())) {
                myAppointmentVector.remove(i); // Found and removed
                return;
            }
        }
    }

    /**
     * Searches for all doctor schedules by doctor ID.
     * 
     * @param doctorID The ID of the doctor
     * @return A vector of matching DoctorSchedule objects
     */
    public Vector<DoctorSchedule> searchDoctorScheduleVector(String doctorID) {
        Vector<DoctorSchedule> result = new Vector<>();
        for (Object obj : myDoctorScheduleVector) {
            DoctorSchedule ds = (DoctorSchedule) obj;
            if (doctorID.equalsIgnoreCase(ds.getDoctorID())) {
                result.add(ds); // Add matching schedule
            }
        }
        return result;
    }

        /**
     * Searches for all doctor schedules for a specific doctor where availability is false.
     * 
     * @param doctorID The ID of the doctor
     * @return A vector of matching DoctorSchedule objects with availability set to false
     */
    public Vector<DoctorSchedule> searchDoctorScheduleVectorByAvailabilityFALSE(String doctorID) {
        Vector<DoctorSchedule> result = new Vector<>();
        for (Object obj : myDoctorScheduleVector) {
            DoctorSchedule ds = (DoctorSchedule) obj;
            if (doctorID.equalsIgnoreCase(ds.getDoctorID()) && !ds.getAppointmentAvailability()) {
                result.add(ds); // Add matching schedule
            }
        }
        return result;
    }
    
    /**
     * Searches for all doctor schedules for a specific doctor where availability is true.
     * 
     * @param doctorID The ID of the doctor
     * @return A vector of matching DoctorSchedule objects with availability set to true
     */
    public Vector<DoctorSchedule> searchDoctorScheduleVectorByAvailabilityTRUE(String doctorID) {
        Vector<DoctorSchedule> result = new Vector<>();
        for (Object obj : myDoctorScheduleVector) {
            DoctorSchedule ds = (DoctorSchedule) obj;
            if (doctorID.equalsIgnoreCase(ds.getDoctorID()) && ds.getAppointmentAvailability()) {
                result.add(ds); // Add matching schedule
            }
        }
        return result;
}    

    /**
     * Checks if a schedule exists for a specific doctor on a given date and time slot.
     * 
     * @param doctorID The ID of the doctor
     * @param scheduleDate The date of the schedule
     * @param scheduleTimeslot The time slot of the schedule
     * @return A vector of matching DoctorSchedule objects
     */
    public Vector<DoctorSchedule> checkScheduleIfExists(String doctorID, String scheduleDate, String scheduleTimeslot) {
        Vector<DoctorSchedule> result = new Vector<>();
        for (Object obj : myDoctorScheduleVector) {
            DoctorSchedule ds = (DoctorSchedule) obj;
            if (doctorID.equalsIgnoreCase(ds.getDoctorID()) && 
                scheduleDate.equalsIgnoreCase(ds.getAvailableAppointmentDate()) && 
                scheduleTimeslot.equalsIgnoreCase(ds.getAvailableAppointmentTimeSlot())) {
                result.add(ds); // Add matching schedule
            }
        }
        return result;
    }

    /**
     * Deletes schedules if they exist for a specific doctor on a given date and time slot.
     * 
     * @param doctorID The ID of the doctor
     * @param scheduleDate The date of the schedule
     * @param scheduleTimeslot The time slot of the schedule
     * @return A vector of deleted DoctorSchedule objects
     */
    public Vector<DoctorSchedule> deleteScheduleIfExists(String doctorID, String scheduleDate, String scheduleTimeslot) {
        Vector<DoctorSchedule> result = new Vector<>();
        for (int i = 0; i < myDoctorScheduleVector.size(); i++) {
            DoctorSchedule ds = (DoctorSchedule) myDoctorScheduleVector.get(i);
            if (doctorID.equalsIgnoreCase(ds.getDoctorID()) && 
                scheduleDate.equalsIgnoreCase(ds.getAvailableAppointmentDate()) && 
                scheduleTimeslot.equalsIgnoreCase(ds.getAvailableAppointmentTimeSlot())) {
                modifyDoctorScheduleVector(i); // Remove the schedule
                result.add(ds); // Add to the result list
            }
        }
        return result;
    }

    /**
     * Prints all doctor schedules using an iterator.
     * 
     * @return null (The method only prints schedules to the console)
     * @throws NoSuchElementException if there are no schedules to iterate over
     */
    public DoctorSchedule searchAllDoctorSchedule() {
        try {
            Iterator it = myDoctorScheduleVector.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (NoSuchElementException elementException) {
            throw elementException;
        }
        return null;
    }

    /**
     * Searches for an appointment by appointment ID.
     * 
     * @param appointmentID The ID of the appointment to search for
     * @return The matching Appointment object, or null if no match is found
     */
    public Appointment searchAppointment(String appointmentID) {
        for (Object obj : myAppointmentVector) {
            Appointment app = (Appointment) obj;
            if (appointmentID.equalsIgnoreCase(app.getAppointment_AppointmentID())) {
                return app; // Found
            }
        }
        return null; // Not found
    }

    /**
     * Searches for an appointment by doctor ID.
     * 
     * @param doctorID The ID of the doctor associated with the appointment
     * @return The first matching Appointment object, or null if no match is found
     */
    public Appointment searchAppointmentByDoctorID(String doctorID) {
        for (Object obj : myAppointmentVector) {
            Appointment app = (Appointment) obj;
            if (doctorID.equalsIgnoreCase(app.getAppointment_DoctorID())) {
                return app; // Found
            }
        }
        return null; // Not found
    }

    /**
     * Searches for all appointments associated with a specific patient by patient ID.
     * 
     * @param patientID The ID of the patient
     * @return A vector of matching Appointment objects
     */
    public Vector<Appointment> searchPatientAppointmentsVector(String patientID) {
        Vector<Appointment> result = new Vector<>();
        for (Object obj : myAppointmentVector) {
            Appointment app = (Appointment) obj;
            if (patientID.equalsIgnoreCase(app.getAppointment_PatientID())) {
                result.add(app); // Add matching appointment
            }
        }
        return result;
    }

}
