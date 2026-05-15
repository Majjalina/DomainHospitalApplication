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
import java.util.Date;

/**
 * Represents a Patient, inheriting general attributes from the Person class.
 * This class includes additional details specific to a patient such as ID, 
 * date of birth, medical history, allergies, and special requests.
 * Implements Serializable to allow instances of this class to be serialized.
 * 
 * 
 */
public class Patient extends Person implements Serializable {

    // Private Attributes
    private String ID; // Unique identifier for patient
    private Date DOB; // Date of birth of patient
    private String MedicationHistory; // Patient's Mecication history of medications
    private String Allergies; // Diagnosed allergies of the patient
    private String SpecialRequests; // Any special requests made by the patient

    /**
     * Default constructor that initializes the Patient object with default values.
     * Calls the superclass (Person) default constructor.
     */
    public Patient() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Patient instance with 
     * specific values for its attributes.
     * 
     * @param patientID Unique identifier for the patient
     * @param personName First name of the patient
     * @param personSurname Last name of the patient
     * @param personAge Age of the patient
     * @param personGender Gender of the patient
     * @param patientDOB Date of birth of the patient
     * @param patientMedicationHistory Medical history of the patient
     * @param patientAllergies Known allergies of the patient
     * @param patientSpecialRequests Special requests made by the patient
     */
    public Patient(String patientID, String personName, String personSurname, int personAge, String personGender, 
                   Date patientDOB, String patientMedicationHistory, String patientAllergies, 
                   String patientSpecialRequests) {
        // Initialize attributes inherited from the superclass
        super(personName, personSurname, personAge, personGender);

        // Initialize patient-specific attributes
        this.ID = patientID;
        this.DOB = patientDOB;
        this.MedicationHistory = patientMedicationHistory;
        this.Allergies = patientAllergies;
        this.SpecialRequests = patientSpecialRequests;
    }

    //-------------------------- Getter Methods ------------------------------
    /**
     * Retrieves the unique identifier (ID) of the patient.
     *
     * @return String representing the patient's ID
     */
    public String getPatientID() {
        return this.ID;
    }

    /**
     * Retrieves the medical history of the patient.
     *
     * @return String containing the patient's medical history
     */
    public String getPatientMedicationHistory() {
        return this.MedicationHistory;
    }

    /**
     * Retrieves the known allergies of the patient.
     *
     * @return String containing the patient's allergies
     */
    public String getPatientAllergies() {
        return this.Allergies;
    }

    /**
     * Retrieves any special requests made by the patient.
     *
     * @return String containing the patient's special requests
     */
    public String getPatientSpecialRequests() {
        return this.SpecialRequests;
    }

    /**
     * Retrieves the date of birth of the patient.
     *
     * @return Date object representing the patient's date of birth
     */
    public Date getPatientDOB() {
        return this.DOB;
    }

    //-------------------------- Setter Methods ------------------------------
    /**
     * Updates the unique identifier (ID) of the patient.
     *
     * @param patient_ID String representing the patient's new ID
     */
    public void setPatientID(String patient_ID) {
        this.ID = patient_ID;
    }

    /**
     * Updates the medical history of the patient.
     *
     * @param patient_MedicationHistory String containing the new medical history
     */
    public void setPatientMedicationHistory(String patient_MedicationHistory) {
        this.MedicationHistory = patient_MedicationHistory;
    }

    /**
     * Updates the known allergies of the patient.
     *
     * @param patient_Allergies String containing the new allergy information
     */
    public void setPatientAllergies(String patient_Allergies) {
        this.Allergies = patient_Allergies;
    }

    /**
     * Updates any special requests made by the patient.
     *
     * @param patient_SpecialRequests String containing the new special requests
     */
    public void setPatientSpecialRequests(String patient_SpecialRequests) {
        this.SpecialRequests = patient_SpecialRequests;
    }

    /**
     * Updates the date of birth of the patient.
     *
     * @param patient_DOB Date object representing the new date of birth
     */
    public void setPatientDOB(Date patient_DOB) {
        this.DOB = patient_DOB;
    }
}
