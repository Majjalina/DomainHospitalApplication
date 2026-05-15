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
 * Represents a Staff member in the application, inheriting general attributes 
 * from the Person class. This class includes additional details specific to 
 * staff such as ID, type, and qualification.
 * Implements Serializable to support serialization of Staff objects.
 * 
 * 
 */
public class Staff extends Person implements Serializable {

    private String ID; // Unique identifier for the staff member
    private String Type; // Type of staff (e.g., Operations and Administration)
    private String Qualification; // Staff's qualification (e.g., Receptionist, Doctor and Pharmacist)

    /**
     * Default constructor that initializes the Staff object with default values.
     * Calls the superclass (Person) default constructor.
     */
    public Staff() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Staff instance with specific 
     * values for its attributes.
     * 
     * @param personName 
     * @param personSurname 
     * @param personAge 
     * @param personGender 
     * @param staffType 
     * @param staffID 
     * @param staffQualification 
     * 
     */
    public Staff(String personName, String personSurname, int personAge, String personGender, 
                 String staffType, String staffID, String staffQualification) {
        // Initialize attributes inherited from the superclass
        super(personName, personSurname, personAge, personGender);

        // Initialize staff-specific attributes
        this.ID = staffID;
        this.Type = staffType;
        this.Qualification = staffQualification;
    }

    //-------------------------- Getter Methods ------------------------------
    /**
     * Retrieves the unique identifier (ID) of the staff member.
     *
     * @return String representing the staff member's ID
     */
    public String getStaffID() {
        return this.ID;
    }

    /**
     * Retrieves the type of the staff member.
     *
     * @return String representing the staff 
     */
    public String getStaffType() {
        return this.Type;
    }

    /**
     * Retrieves the qualification of the staff member
     *
     * @return String containing the staff member's qualification
     */
    public String getStaffQualification() {
        return this.Qualification;
    }

    //-------------------------- Setter Methods ------------------------------
    /**
     * Updates the unique identifier (ID) of the staff member.
     *
     * @param staffID String representing the new staff ID
     */
    public void setStaffID(String staffID) {
        this.ID = staffID;
    }

    /**
     * Updates the type of the staff member.
     *
     * @param staffType String representing the new staff type
     */
    public void setStaffType(String staffType) {
        this.Type = staffType;
    }

    /**
     * Updates the qualification of the staff member.
     *
     * @param staffQualification String representing the new qualification
     */
    public void setStaffQualification(String staffQualification) {
        this.Qualification = staffQualification;
    }
}
