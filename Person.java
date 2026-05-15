/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;
import java.io.Serializable;

/**
 * This class represents a Person with basic attributes such as name, surname, age, and gender.
 * Implements Serializable for object serialization
 * @author eebej
 */
public class Person implements Serializable {

    private String name;
    private String surname;
    private int age;
    private String gender;

    /**
     * Default Constructor 
     */
    public Person() {        
    }

    // Overloaded Constructor
    /**
     * Initializes a Person instance with specified name, surname, age, and gender in this order.
     * 
     * @param personName   
     * @param personSurname 
     * @param personAge
     * @param personGender
     */
    public Person(String personName, String personSurname, int personAge, String personGender) {
        this.name = personName;
        this.surname = personSurname;
        this.age = personAge;
        this.gender = personGender;
    }

    //--------------------------Getter Methods------------------------------

    /**
     * 
     * @return The name of the Person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return The surname of the Person.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * 
     * @return The age of the Person.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * 
     * @return The gender of the Person.
     */
    public String getGender() {
        return this.gender;
    }

    //--------------------------Setter Methods------------------------------

    /**
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param surname The new surname to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * 
     * @param age The new age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 
     * @param gender The new gender to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    //--------------------------toString Method------------------------------

    /**
     * 
     * @return A string containing the Person's name, surname, age, and gender.
     */
    @Override
    public String toString() {
        // Formatting the personal details into a string.
        return "\nNAME: " + this.name +
               "\nSURNAME: " + this.surname +
               "\nAGE: " + this.age +
               "\nGENDER: " + this.gender;
    }
}