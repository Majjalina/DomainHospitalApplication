/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Patient Management User Interface
 * 
 * @author eebej
 * 
 */

public class PatientUI extends JFrame {

    // UI Components
    private JTextField txtPatientName, txtPatientSurname, txtPatientID, txtPatientAge, 
            txtPatientPrescriptionHistory, txtPatientSpecialRequests;
    private JComboBox<String> cmbPatientGender, cmbPatientAllergies;
    private JFormattedTextField txtPatientDOB;
    private JButton addButton, clearButton, editButton, searchButton, exitButton;

    private final String[] patientGenderList = {"Male", "Female"};
    private final String[] patientAllergiesList = {"Null", "Nuts", "People", "Latex"};

    private final ApplicationLogic aLogic = new ApplicationLogic();

    /**
     * Constructor initializes the Patient UI.
     */
    public PatientUI() {
        buildPatientUI();
        setupListeners();
    }

    /**
     * Builds the Patient Management User Interface.
     */
    private void buildPatientUI() {
        setTitle("Patient Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Patient Details Panel
        JPanel patientDetailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addFieldToPanel(patientDetailsPanel, gbc, "Patient ID:", txtPatientID = new JTextField(15), 0);
        addFieldToPanel(patientDetailsPanel, gbc, "Name:", txtPatientName = new JTextField(25), 1);
        addFieldToPanel(patientDetailsPanel, gbc, "Surname:", txtPatientSurname = new JTextField(25), 2);
        addFieldToPanel(patientDetailsPanel, gbc, "Age:", txtPatientAge = new JTextField(3), 3);

        addFieldToPanel(patientDetailsPanel, gbc, "Date of Birth (dd/MM/yyyy):", 
                txtPatientDOB = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))), 4);

        addDropdownToPanel(patientDetailsPanel, gbc, "Gender:", cmbPatientGender = new JComboBox<>(patientGenderList), 5);
        addFieldToPanel(patientDetailsPanel, gbc, "Prescription History:", txtPatientPrescriptionHistory = new JTextField(50), 6);
        addDropdownToPanel(patientDetailsPanel, gbc, "Allergies:", cmbPatientAllergies = new JComboBox<>(patientAllergiesList), 7);
        addFieldToPanel(patientDetailsPanel, gbc, "Special Requests:", txtPatientSpecialRequests = new JTextField(50), 8);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton = createButton("Add Patient"));
        buttonPanel.add(clearButton = createButton("Clear"));
        buttonPanel.add(searchButton = createButton("Search"));
        buttonPanel.add(editButton = createButton("Edit"));
        buttonPanel.add(exitButton = createButton("Exit"));

        // Add Panels to Main Panel
        mainPanel.add(patientDetailsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Adds a field and its label to the panel.
     */
    private void addFieldToPanel(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    /**
     * Adds a dropdown and its label to the panel.
     */
    private void addDropdownToPanel(JPanel panel, GridBagConstraints gbc, String label, JComboBox<String> dropdown, int row) {
        addFieldToPanel(panel, gbc, label, dropdown, row);
    }

    /**
     * Creates a styled button.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    /**
     * Sets up event listeners for all buttons.
     */
    private void setupListeners() {
        addButton.addActionListener(e -> addPatient());
        clearButton.addActionListener(e -> clearForm());
        editButton.addActionListener(e -> editPatient());
        searchButton.addActionListener(e -> searchPatient());
        exitButton.addActionListener(e -> dispose());
    }

    /**
     * Clears all fields in the form.
     */
    private void clearForm() {
        txtPatientID.setText("");
        txtPatientName.setText("");
        txtPatientSurname.setText("");
        txtPatientAge.setText("");
        txtPatientDOB.setText("");
        txtPatientPrescriptionHistory.setText("");
        txtPatientSpecialRequests.setText("");
        cmbPatientGender.setSelectedIndex(0);
        cmbPatientAllergies.setSelectedIndex(0);
        txtPatientID.setEnabled(true);
    }

    /**
     * Validates input fields and displays an error message if validation fails.
     */
    private boolean validateField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            showError(fieldName + " is required.");
            return true;
        }
        return false;
    }

    /**
     * Adds a new patient record after validating inputs.
     */
    private void addPatient() {
    if (!validatePositiveNumbers()) {
        return;
    }

    try {
        String patientID = txtPatientID.getText().trim();
        if (validateField(patientID, "Patient ID")) return;

        String patientName = txtPatientName.getText().trim();
        if (validateField(patientName, "Name")) return;

        String patientSurname = txtPatientSurname.getText().trim();
        if (validateField(patientSurname, "Surname")) return;

        String ageText = txtPatientAge.getText().trim();
        if (validateField(ageText, "Age")) return;
        int patientAge = Integer.parseInt(ageText);

        String dobText = txtPatientDOB.getText().trim();
        if (validateField(dobText, "Date of Birth")) return;
        Date patientDOB = new SimpleDateFormat("dd/MM/yyyy").parse(dobText);

        String patientGender = (String) cmbPatientGender.getSelectedItem();
        String patientPrescription = txtPatientPrescriptionHistory.getText().trim();
        String patientAllergies = (String) cmbPatientAllergies.getSelectedItem();
        String patientRequest = txtPatientSpecialRequests.getText().trim();

        if (ApplicationLogic.GetPatientID(patientID)) {
            showError("Patient ID already exists.");
            return;
        }

        aLogic.addPatientRecord(patientID, patientName, patientSurname, patientAge, patientGender, patientDOB, 
                                 patientPrescription, patientAllergies, patientRequest);

        showSuccess("Patient added successfully.");
        clearForm();

    } catch (ParseException e) {
        showError("Invalid date format. Please use dd/MM/yyyy.");
    }
    }

    /**
     * Searches for a patient by ID and populates the form.
     */
    private void searchPatient() {
        String patientID = JOptionPane.showInputDialog(this, "Enter Patient ID to search:");
        if (patientID == null || patientID.trim().isEmpty()) return;

        if (ApplicationLogic.GetPatientID(patientID)) {
            Patient patient = ApplicationLogic.GetPatientData(patientID);

            txtPatientID.setText(patient.getPatientID());
            txtPatientName.setText(patient.getName());
            txtPatientSurname.setText(patient.getSurname());
            txtPatientAge.setText(String.valueOf(patient.getAge()));
            cmbPatientGender.setSelectedItem(patient.getGender());
            txtPatientDOB.setText(new SimpleDateFormat("dd/MM/yyyy").format(patient.getPatientDOB()));
            txtPatientPrescriptionHistory.setText(patient.getPatientMedicationHistory());
            txtPatientSpecialRequests.setText(patient.getPatientSpecialRequests());
            cmbPatientAllergies.setSelectedItem(patient.getPatientAllergies());
            txtPatientID.setEnabled(false);
        } else {
            showError("Patient not found.");
        }
    }

    /**
     * Edits an existing patient record.
     */
    private void editPatient() {
        String patientID = txtPatientID.getText().trim();
        if (!ApplicationLogic.GetPatientID(patientID)) {
            showError("Patient ID not found.");
            return;
        }

        try {
            String patientName = txtPatientName.getText().trim();
            String patientSurname = txtPatientSurname.getText().trim();
            int patientAge = Integer.parseInt(txtPatientAge.getText().trim());
            Date patientDOB = new SimpleDateFormat("dd/MM/yyyy").parse(txtPatientDOB.getText().trim());
            String patientGender = (String) cmbPatientGender.getSelectedItem();
            String patientPrescription = txtPatientPrescriptionHistory.getText().trim();
            String patientAllergies = (String) cmbPatientAllergies.getSelectedItem();
            String patientRequest = txtPatientSpecialRequests.getText().trim();

            aLogic.editPatientRecord(patientID, patientName, patientSurname, patientAge, patientGender, patientDOB, 
                                     patientPrescription, patientAllergies, patientRequest);

            showSuccess("Patient record updated successfully.");
            clearForm();

        } catch (ParseException e) {
            showError("Invalid date format. Please use dd/MM/yyyy.");
        }
    }

    /**
     * Shows an error message
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a success message
     */
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    /**
    * Validates that Age and Patient ID are positive numbers.
    */
   private boolean validatePositiveNumbers() {
       try {
           String patientIDText = txtPatientID.getText().trim();
           if (Integer.parseInt(patientIDText) <= 0) {
               showError("Patient ID cannot be negative or zero.");
               return false;
           }
       } catch (NumberFormatException e) {
           showError("Patient ID must be a valid number.");
           return false;
       }

       try {
           String ageText = txtPatientAge.getText().trim();
           if (Integer.parseInt(ageText) <= 0) {
               showError("Age cannot be negative or zero.");
               return false;
           }
       } catch (NumberFormatException e) {
           showError("Age must be a valid number.");
           return false;
       }

       return true;
   }
    
    
    
}
