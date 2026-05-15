/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.io.Serializable;

/**
 * Represents a Doctor in the system, extending attributes and behaviour from the
 * Staff and Person classes. Includes a specialization field specific to doctors.
 * Implements Serializable to allow instances of this class to be serialized.
 *
 * @author eebej
 */
public class Doctor extends Staff implements Serializable {

    private String specialisation; // Doctor's field of specialization

    /**
     * Default constructor that initializes a Doctor object with default values.
     * Calls the superclass (Staff) default constructor.
     */
    public Doctor() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Doctor instance with specific
     * values for attributes inherited from Person and Staff, as well as the 
     * Doctor's specialization.
     * 
     * @param personName First name of the doctor
     * @param personSurname Last name of the doctor
     * @param personAge Age of the doctor
     * @param personGender Gender of the doctor
     * @param staffType Type of staff (e.g., Operational)
     * @param staffID Unique identifier for the staff
     * @param staffQualification Qualification of the staff member
     * @param doctorSpecialisation Doctor's field of specialization
     */
    public Doctor(String personName, String personSurname, int personAge, String personGender, 
                  String staffType, String staffID, String staffQualification, String doctorSpecialisation) {
        // Initialize attributes inherited from the superclass
        super(personName, personSurname, personAge, personGender, staffType, staffID, staffQualification);

        // Initialize doctor-specific attributes
        this.specialisation = doctorSpecialisation;
    }

    //-------------------------- Getter Methods ------------------------------
    /**
     * Retrieves the specialization of the doctor.
     *
     * @return String representing the doctor's specialization
     */
    public String getDoctorSpecialisation() {
        return this.specialisation;
    }

    //-------------------------- Setter Methods ------------------------------
    /**
     * Updates the specialization of the doctor.
     *
     * @param doctorSpecialisation String representing the new specialization
     */
    public void setDoctorSpecialisation(String doctorSpecialisation) {
        this.specialisation = doctorSpecialisation;
    }
}
