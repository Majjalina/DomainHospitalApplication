/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

/**
 *
 * @author eebej
 */
import java.io.Serializable;

/**
 * Represents an Appointment in the system, containing details such as appointment ID,
 * patient ID, doctor ID, date, time slot, symptoms, doctor notes, medications, and 
 * appointment statuses. Implements Serializable to allow instances of this class to 
 * be serialized.
 * 
 * 
 */
public class Appointment implements Serializable {

    // Private Attributes
    private String appointment_appointmentID; // Unique identifier for the appointment
    private String appointment_patientID; // ID of the patient associated with the appointment
    private String appointment_doctorID; // ID of the doctor for the appointment
    private String appointment_date; // Date of the appointment
    private String appointment_timeslot; // Time slot for the appointment
    private String appointment_patientSymptoms; // Symptoms reported by the patient
    private String appointment_doctorNotes; // Notes added by the doctor
    private String appointment_doctorMedications; // Medications prescribed by the doctor
    private String appointment_appointmentStatus; // Current status of the appointment (e.g., Scheduled, Completed)
    private String appointment_attendanceStatus; // Attendance status (e.g., Attended, Missed)

    /**
     * Default constructor that initializes an Appointment object with default values.
     */
    public Appointment() {
    }

    /**
     * Parameterized constructor to initialize an Appointment instance with specific values.
     * 
     * @param a_AppointmentID Unique identifier for the appointment
     * @param a_PatientID ID of the patient associated with the appointment
     * @param a_DoctorID ID of the doctor for the appointment
     * @param a_Date Date of the appointment
     * @param a_TimeSlot Time slot for the appointment
     * @param a_PatientSymptoms Symptoms reported by the patient
     * @param a_DoctorNotes Notes added by the doctor
     * @param a_Medications Medications prescribed by the doctor
     * @param a_AppointmentStatus Current status of the appointment (e.g., Scheduled, Completed)
     * @param a_AttendanceStatus Attendance status (e.g., Attended, Missed)
     */
    public Appointment(String a_AppointmentID, String a_PatientID, String a_DoctorID, String a_Date, 
                       String a_TimeSlot, String a_PatientSymptoms, String a_DoctorNotes, 
                       String a_Medications, String a_AppointmentStatus, String a_AttendanceStatus) {
        this.appointment_appointmentID = a_AppointmentID;
        this.appointment_patientID = a_PatientID;
        this.appointment_doctorID = a_DoctorID;
        this.appointment_date = a_Date;
        this.appointment_timeslot = a_TimeSlot;
        this.appointment_patientSymptoms = a_PatientSymptoms;
        this.appointment_doctorNotes = a_DoctorNotes;
        this.appointment_doctorMedications = a_Medications;
        this.appointment_appointmentStatus = a_AppointmentStatus;
        this.appointment_attendanceStatus = a_AttendanceStatus;
    }

    //-------------------------- Getter Methods ------------------------------

    /**
     * Retrieves the appointment ID.
     *
     * @return String representing the appointment ID
     */
    public String getAppointment_AppointmentID() {
        return this.appointment_appointmentID;
    }

    /**
     * Retrieves the patient ID associated with the appointment.
     *
     * @return String representing the patient ID
     */
    public String getAppointment_PatientID() {
        return this.appointment_patientID;
    }

    /**
     * Retrieves the doctor ID associated with the appointment.
     *
     * @return String representing the doctor ID
     */
    public String getAppointment_DoctorID() {
        return this.appointment_doctorID;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return String representing the appointment date
     */
    public String getAppointment_AppointmentDate() {
        return this.appointment_date;
    }

    /**
     * Retrieves the time slot of the appointment.
     *
     * @return String representing the appointment time slot
     */
    public String getAppointment_AppointmentTimeSlot() {
        return this.appointment_timeslot;
    }

    /**
     * Retrieves the symptoms reported by the patient.
     *
     * @return String containing the patient symptoms
     */
    public String getAppointment_PatientSymptoms() {
        return this.appointment_patientSymptoms;
    }

    /**
     * Retrieves the notes added by the doctor.
     *
     * @return String containing the doctor notes
     */
    public String getAppointment_DoctorNotes() {
        return this.appointment_doctorNotes;
    }

    /**
     * Retrieves the medications prescribed by the doctor.
     *
     * @return String containing the prescribed medications
     */
    public String getAppointment_DoctorMedications() {
        return this.appointment_doctorMedications;
    }

    /**
     * Retrieves the status of the appointment.
     *
     * @return String representing the appointment status
     */
    public String getAppointment_AppointmentStatus() {
        return this.appointment_appointmentStatus;
    }

    /**
     * Retrieves the attendance status of the appointment.
     *
     * @return String representing the attendance status
     */
    public String getAppointment_AttendanceStatus() {
        return this.appointment_attendanceStatus;
    }

    //-------------------------- Setter Methods ------------------------------

    /**
     * Updates the appointment ID.
     *
     * @param app_AppointmentID New appointment ID
     */
    public void setAppointment_AppointmentID(String app_AppointmentID) {
        this.appointment_appointmentID = app_AppointmentID;
    }

    /**
     * Updates the patient ID associated with the appointment.
     *
     * @param app_PatientID New patient ID
     */
    public void setAppointment_PatientID(String app_PatientID) {
        this.appointment_patientID = app_PatientID;
    }

    /**
     * Updates the doctor ID associated with the appointment.
     *
     * @param app_DoctorID New doctor ID
     */
    public void setAppointment_DoctorID(String app_DoctorID) {
        this.appointment_doctorID = app_DoctorID;
    }

    /**
     * Updates the date of the appointment.
     *
     * @param app_AppointmentDate New appointment date
     */
    public void setAppointment_AppointmentDate(String app_AppointmentDate) {
        this.appointment_date = app_AppointmentDate;
    }

    /**
     * Updates the time slot of the appointment.
     *
     * @param app_AppointmentTimeslot New appointment time slot
     */
    public void setAppointment_AppointmentTimeSlot(String app_AppointmentTimeslot) {
        this.appointment_timeslot = app_AppointmentTimeslot;
    }

    /**
     * Updates the symptoms reported by the patient.
     *
     * @param app_PatientSymptoms New patient symptoms
     */
    public void setAppointment_PatientSymptoms(String app_PatientSymptoms) {
        this.appointment_patientSymptoms = app_PatientSymptoms;
    }

    /**
     * Updates the notes added by the doctor.
     *
     * @param app_DoctorNotes New doctor notes
     */
    public void setAppointment_DoctorNotes(String app_DoctorNotes) {
        this.appointment_doctorNotes = app_DoctorNotes;
    }

    /**
     * Updates the medications prescribed by the doctor.
     *
     * @param app_DoctorMedication New prescribed medications
     */
    public void setAppointment_DoctorMedication(String app_DoctorMedication) {
        this.appointment_doctorMedications = app_DoctorMedication;
    }

    /**
     * Updates the status of the appointment.
     *
     * @param app_AppointmentStatus New appointment status
     */
    public void setAppointment_AppointmentStatus(String app_AppointmentStatus) {
        this.appointment_appointmentStatus = app_AppointmentStatus;
    }

    /**
     * Updates the attendance status of the appointment.
     *
     * @param app_AttendanceStatus New attendance status
     */
    public void setAppointment_AttendanceStatus(String app_AttendanceStatus) {
        this.appointment_attendanceStatus = app_AttendanceStatus;
    }
}