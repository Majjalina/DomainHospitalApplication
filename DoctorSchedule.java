/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.io.Serializable;

/**
 * Represents the schedule of a doctor, including details such as schedule ID,
 * doctor ID, appointment date, time slot, and availability status.
 * Implements Serializable to allow instances of this class to be serialized.
 * 
 * @author eebej
 */
public class DoctorSchedule implements Serializable {

    private String scheduleID; // Unique identifier for the schedule
    private String scheduleDoctorID; // ID of the doctor associated with this schedule
    private String scheduleAppDate; // Date of the scheduled appointment
    private String scheduleAppTimeSlot; // Time slot for the scheduled appointment
    private boolean scheduleAppStatus; // Availability status of the appointment (true = available, false = not available)

    /**
     * Default constructor that initializes the DoctorSchedule object with default values.
     */
    public DoctorSchedule() {
    }

    /**
     * Parameterized constructor to initialize a DoctorSchedule instance with all attributes.
     * 
     * @param doctorSchedule_ScheduleID Unique identifier for the schedule
     * @param doctorscheduleDoctorID ID of the doctor
     * @param doctorScheduleAppDate Date of the scheduled appointment
     * @param doctorscheduleAppTimeSlot Time slot for the scheduled appointment
     * @param doctorScheduleScheduleStatus Availability status of the appointment
     */
    public DoctorSchedule(String doctorSchedule_ScheduleID, String doctorscheduleDoctorID, 
                          String doctorScheduleAppDate, String doctorscheduleAppTimeSlot, 
                          boolean doctorScheduleScheduleStatus) {
        this.scheduleID = doctorSchedule_ScheduleID;
        this.scheduleDoctorID = doctorscheduleDoctorID;
        this.scheduleAppDate = doctorScheduleAppDate;
        this.scheduleAppTimeSlot = doctorscheduleAppTimeSlot;
        this.scheduleAppStatus = doctorScheduleScheduleStatus;
    }

    /**
     * Parameterized constructor to initialize a DoctorSchedule instance without schedule ID.
     * 
     * @param doctorscheduleDoctorID ID of the doctor
     * @param doctorScheduleAppDate Date of the scheduled appointment
     * @param doctorscheduleAppTimeSlot Time slot for the scheduled appointment
     * @param doctorScheduleScheduleStatus Availability status of the appointment
     */
    public DoctorSchedule(String doctorscheduleDoctorID, String doctorScheduleAppDate, 
                          String doctorscheduleAppTimeSlot, boolean doctorScheduleScheduleStatus) {
        this.scheduleDoctorID = doctorscheduleDoctorID;
        this.scheduleAppDate = doctorScheduleAppDate;
        this.scheduleAppTimeSlot = doctorscheduleAppTimeSlot;
        this.scheduleAppStatus = doctorScheduleScheduleStatus;
    }

    //-------------------------- Getter Methods ------------------------------
    /**
     * Retrieves the unique schedule ID.
     *
     * @return String representing the schedule ID
     */
    public String getScheduleID() {
        return this.scheduleID;
    }

    /**
     * Retrieves the doctor ID associated with the schedule.
     *
     * @return String representing the doctor ID
     */
    public String getDoctorID() {
        return this.scheduleDoctorID;
    }

    /**
     * Retrieves the appointment date for the schedule.
     *
     * @return String representing the appointment date
     */
    public String getAvailableAppointmentDate() {
        return this.scheduleAppDate;
    }

    /**
     * Retrieves the time slot for the scheduled appointment.
     *
     * @return String representing the time slot
     */
    public String getAvailableAppointmentTimeSlot() {
        return this.scheduleAppTimeSlot;
    }

    /**
     * Retrieves the availability status of the appointment.
     *
     * @return Boolean representing the appointment availability
     */
    public Boolean getAppointmentAvailability() {
        return this.scheduleAppStatus;
    }

    //-------------------------- Setter Methods ------------------------------
    /**
     * Updates the unique schedule ID.
     *
     * @param doctorSchedule_ScheduleID New schedule ID
     */
    public void setScheduleID(String doctorSchedule_ScheduleID) {
        this.scheduleID = doctorSchedule_ScheduleID;
    }

    /**
     * Updates the doctor ID associated with the schedule.
     *
     * @param doctorscheduleDoctorID New doctor ID
     */
    public void setDoctorID(String doctorscheduleDoctorID) {
        this.scheduleDoctorID = doctorscheduleDoctorID;
    }

    /**
     * Updates the appointment date for the schedule.
     *
     * @param doctorScheduleAppDate New appointment date
     */
    public void setDoctorAppointmentDate(String doctorScheduleAppDate) {
        this.scheduleAppDate = doctorScheduleAppDate;
    }

    /**
     * Updates the time slot for the scheduled appointment.
     *
     * @param doctorscheduleAppTimeSlot New time slot
     */
    public void setDoctorAvailableAppointmentTimeSlot(String doctorscheduleAppTimeSlot) {
        this.scheduleAppTimeSlot = doctorscheduleAppTimeSlot;
    }

    /**
     * Updates the availability status of the appointment.
     *
     * @param doctorSchedule_available New availability status
     */
    public void setDoctorAvailability(Boolean doctorSchedule_available) {
        this.scheduleAppStatus = doctorSchedule_available;
    }

    /**
     * Provides a string representation of the doctor's schedule.
     *
     * @return String containing all schedule details
     */
    @Override
    public String toString() {
        return "\nSCHEDULE ID: " + this.scheduleID 
             + "\nDOCTOR ID: " + this.scheduleDoctorID 
             + "\nAVAILABLE DATE: " + this.scheduleAppDate 
             + "\nAVAILABLE TIME SLOT: " + this.scheduleAppTimeSlot 
             + "\nSTATUS: " + (this.scheduleAppStatus ? "Available" : "Not Available");
    }
}