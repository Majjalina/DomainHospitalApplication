/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.awt.*;
import javax.swing.*;

/**
 * Staff Management User Interface
 * *@author eebej
 */
public class StaffUI extends JFrame {

    // UI Components
    private JTextField txtStaffName, txtStaffSurname, txtStaffID, txtStaffAge;
    private JComboBox<String> cmbStaffGender, cmbStaffType, cmbStaffQualification, cmbDoctorSpecialisation;
    private JButton exitButton, clearButton, addButton, searchButton, editButton;

    // Dropdown options
    private final String[] staffGenderList = {"Male", "Female"};
    private final String[] staffType = {"Operations", "Administration"};
    private final String[] staffQualification = {"Receptionist", "Doctor", "Pharmacist"};
    private final String[] doctorSpecialisation = {"Allergist", "Cardiologist", "Family Medicine Physician",
            "Gynecologist", "Pediatrician"};

    // Backend Logic
    private final ApplicationLogic aLogic = new ApplicationLogic();

    /**
     * Constructor initializes the Staff Management UI.
     */
    public StaffUI() {
        buildStaffGUI();
        setupListeners();
        configureSpecialisationVisibility();
    }

    /**
     * Builds the Staff Management User Interface.
     */
    private void buildStaffGUI() {
        setTitle("Staff Management Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 700);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Staff Details Panel
        JPanel staffDetailPanel = new JPanel(new GridBagLayout());
        staffDetailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding fields
        addLabelAndField(staffDetailPanel, gbc, "Staff ID:", txtStaffID = new JTextField(15), 0);
        addLabelAndField(staffDetailPanel, gbc, "Staff Name:", txtStaffName = new JTextField(25), 1);
        addLabelAndField(staffDetailPanel, gbc, "Staff Surname:", txtStaffSurname = new JTextField(25), 2);
        addLabelAndField(staffDetailPanel, gbc, "Staff Age:", txtStaffAge = new JTextField(5), 3);
        addLabelAndDropdown(staffDetailPanel, gbc, "Gender:", cmbStaffGender = new JComboBox<>(staffGenderList), 4);
        addLabelAndDropdown(staffDetailPanel, gbc, "Staff Section Type:", cmbStaffType = new JComboBox<>(staffType), 5);
        addLabelAndDropdown(staffDetailPanel, gbc, "Job Qualification:", cmbStaffQualification = new JComboBox<>(staffQualification), 6);
        addLabelAndDropdown(staffDetailPanel, gbc, "Doctor Specialisation:", cmbDoctorSpecialisation = new JComboBox<>(doctorSpecialisation), 7);

        // Hide doctor specialization by default
        cmbDoctorSpecialisation.setVisible(false);

        // Control Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.add(addButton = createButton("Add Staff Record"));
        buttonPanel.add(clearButton = createButton("Clear Record"));
        buttonPanel.add(searchButton = createButton("Search Staff Record"));
        buttonPanel.add(editButton = createButton("Edit Staff Record"));
        buttonPanel.add(exitButton = createButton("Exit"));

        // Add panels to main panel
        mainPanel.add(staffDetailPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Sets up event listeners for UI components.
     */
    private void setupListeners() {
        addButton.addActionListener(e -> addStaff());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchStaff());
        editButton.addActionListener(e -> editStaff());
        exitButton.addActionListener(e -> dispose());
    }

    /**
     * Configures visibility for the Doctor Specialisation field.
     */
    private void configureSpecialisationVisibility() {
        cmbStaffQualification.addActionListener(e -> {
            boolean isDoctor = "Doctor".equals(cmbStaffQualification.getSelectedItem().toString());
            cmbDoctorSpecialisation.setVisible(isDoctor);
        });
    }

    /**
     * Adds a label and text field to a panel.
     */
    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    /**
     * Adds a label and dropdown menu to a panel.
     */
    private void addLabelAndDropdown(JPanel panel, GridBagConstraints gbc, String label, JComboBox<String> comboBox, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(comboBox, gbc);
    }

    /**
     * Creates a styled button.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(160, 30));
        return button;
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        txtStaffID.setText("");
        txtStaffName.setText("");
        txtStaffSurname.setText("");
        txtStaffAge.setText("");
        cmbStaffGender.setSelectedIndex(0);
        cmbStaffType.setSelectedIndex(0);
        cmbStaffQualification.setSelectedIndex(0);
        cmbDoctorSpecialisation.setSelectedIndex(0);
        cmbDoctorSpecialisation.setVisible(false);
    }

    /**
     * Adds a new staff record.
     */
    private void addStaff() {
        String staffID = txtStaffID.getText().trim();
        if (validateField(staffID, "Staff ID")) return;

        if (ApplicationLogic.GetStaffID(staffID)) {
            showError("Staff ID already exists. Please use a different ID.");
            return;
        }

        String staffName = txtStaffName.getText().trim();
        String staffSurname = txtStaffSurname.getText().trim();
        String staffAgeInput = txtStaffAge.getText().trim();

        if (validateField(staffName, "Staff Name") || validateField(staffSurname, "Staff Surname") || validateField(staffAgeInput, "Staff Age")) {
            return;
        }

        int staffAge;
        try {
            staffAge = Integer.parseInt(staffAgeInput);
        } catch (NumberFormatException e) {
            showError("Staff Age must be a valid number.");
            return;
        }

        String staffGender = cmbStaffGender.getSelectedItem().toString();
        String staffType = cmbStaffType.getSelectedItem().toString();
        String staffQualification = cmbStaffQualification.getSelectedItem().toString();
        String doctorSpecialisation = "Doctor".equals(staffQualification) ? cmbDoctorSpecialisation.getSelectedItem().toString() : "";

        if ("Doctor".equals(staffQualification)) {
            aLogic.addDoctorRecord(staffName, staffSurname, staffAge, staffGender, staffType, staffID, staffQualification, doctorSpecialisation);
        } else {
            aLogic.addStaffRecord(staffName, staffSurname, staffAge, staffGender, staffType, staffID, staffQualification);
        }
        showSuccess("Staff record added successfully.");
        clearFields();
    }

    /**
     * Searches for a staff record.
     */
    private void searchStaff() {
        String staffID = JOptionPane.showInputDialog("Enter Staff ID to search:");
        if (staffID == null || staffID.trim().isEmpty()) {
            showError("Staff ID cannot be empty.");
            return;
        }

        if (!ApplicationLogic.GetStaffID(staffID)) {
            showError("No staff found with the given ID.");
            return;
        }

        Staff staff = ApplicationLogic.GetStaffData(staffID);
        populateFields(staff);
    }

    /**
     * Edits an existing staff record.
     */
    private void editStaff() {
        String staffID = txtStaffID.getText().trim();
        if (!ApplicationLogic.GetStaffID(staffID)) {
            showError("No staff found with the given Staff ID.");
            return;
        }
        addStaff(); // Reuse logic to save updated staff details
    }

    /**
     * Populates fields with staff data.
     */
    private void populateFields(Staff staff) {
        txtStaffID.setText(staff.getStaffID());
        txtStaffName.setText(staff.getName());
        txtStaffSurname.setText(staff.getSurname());
        txtStaffAge.setText(String.valueOf(staff.getAge()));
        cmbStaffGender.setSelectedItem(staff.getGender());
        cmbStaffQualification.setSelectedItem(staff.getStaffQualification());
        cmbStaffType.setSelectedItem(staff.getStaffType());
        if ("Doctor".equals(staff.getStaffQualification())) {
            cmbDoctorSpecialisation.setSelectedItem(staff.getStaffQualification());
            cmbDoctorSpecialisation.setVisible(true);
        }
    }

    /**
     * Validates a field value.
     */
    private boolean validateField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            showError(fieldName + " is required.");
            return true;
        }
        return false;
    }

    /**
     * Displays an error message.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays a success message.
     */
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}