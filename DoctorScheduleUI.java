/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.text.DateFormatter;

/**
 * DoctorScheduleUI manages the scheduling interface for doctors' appointments.
 * Features include Creating, editing, searching, and clearing doctor schedules.
 * 
 * @author eebej
 */
public class DoctorScheduleUI extends JFrame {

    // UI variables
    private JComboBox<String> cmbDoctorID, cmbScheduleAvailability, cmbScheduleTimeslot;
    private JFormattedTextField txtAppointmentDate;
    private JButton exitButton, clearButton, addButton, editButton, searchButton;

    // Dropdown list data
    private String[] doctorList = {};
    private final String[] scheduleTimeSlot = {
        "09:00AM - 09:30AM", "09:30AM - 10:00AM",
        "10:00AM - 10:30AM", "10:30AM - 11:00AM", "11:00AM - 11:30AM",
        "11:30AM - 12:00PM", "16:00PM - 16:30PM", "16:30PM - 17:00PM",
        "17:00PM - 17:30PM", "17:30PM - 18:00PM", "18:00PM - 18:30PM", "18:30PM - 19:00PM"
    };
    private final String[] availability = {"Available", "Not Available"};

    // Logic layer reference
    ApplicationLogic aLogic = new ApplicationLogic();

    /**
     * Default Constructor
     */
    public DoctorScheduleUI() {
        buildDoctorScheduleGUI();
        populateDoctorData();
        setupEventListeners();
    }

    /**
     * Overloaded Constructor
     */
    public void buildDoctorScheduleGUI() {
    // Frame properties
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Doctor Schedule");
    setSize(800, 600);
    setLocationRelativeTo(null);

    // Main container
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // Schedule details panel
    JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 15, 15)); 
    JLabel detailsLabel = new JLabel("Schedule Details", JLabel.CENTER);
    detailsLabel.setFont(new Font("Arial", Font.BOLD, 16));
    detailsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); 

    // Components for schedule details
    cmbDoctorID = new JComboBox<>();
    txtAppointmentDate = createDateField();
    cmbScheduleTimeslot = new JComboBox<>(scheduleTimeSlot);
    cmbScheduleAvailability = new JComboBox<>(availability);

    // Adding fields to details panel
    detailsPanel.add(new JLabel("Doctor ID:"));
    detailsPanel.add(cmbDoctorID);
    detailsPanel.add(new JLabel("Schedule Date:"));
    detailsPanel.add(txtAppointmentDate);
    detailsPanel.add(new JLabel("Time Slot:"));
    detailsPanel.add(cmbScheduleTimeslot);
    detailsPanel.add(new JLabel("Availability:"));
    detailsPanel.add(cmbScheduleAvailability);

    // Button panel
    JPanel buttonPanel = new JPanel(new GridBagLayout()); 
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 10, 5, 10);
    
    // Buttons
    addButton = createStyledButton("Add Schedule");
    clearButton = createStyledButton("Clear");
    searchButton = createStyledButton("Search");
    editButton = createStyledButton("Update");
    exitButton = createStyledButton("Exit");

    // Add buttons to the panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    buttonPanel.add(addButton, gbc);

    gbc.gridx = 1;
    buttonPanel.add(clearButton, gbc);

    gbc.gridx = 2;
    buttonPanel.add(searchButton, gbc);

    gbc.gridx = 3;
    buttonPanel.add(editButton, gbc);

    gbc.gridx = 4;
    buttonPanel.add(exitButton, gbc);

    // Add components to the main panel
    mainPanel.add(detailsLabel, BorderLayout.NORTH);
    mainPanel.add(detailsPanel, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    // Main panel
    add(mainPanel);
}


    /**
     * Populates the Doctor ID dropdown with available doctor data.
     */
    private void populateDoctorData() {
        doctorList = ApplicationLogic.listDoctorName();
        if (doctorList.length == 0) {
            JOptionPane.showMessageDialog(
                this, "No doctors found in the system.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (String doctor : doctorList) {
                cmbDoctorID.addItem(doctor);
            }
        }
    }

    /**
     * Sets up action listeners for buttons.
     */
    private void setupEventListeners() {
        addButton.addActionListener(e -> addDoctorSchedule());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchDoctorSchedule());
        editButton.addActionListener(e -> updateDoctorSchedule());
        exitButton.addActionListener(e -> dispose()); // Direct exit without confirmation
    }

    /**
     * Clears fields
     */
    private void clearFields() {
        txtAppointmentDate.setText("");
        cmbDoctorID.setSelectedIndex(0);
        cmbScheduleAvailability.setSelectedIndex(0);
        cmbScheduleTimeslot.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Form cleared successfully.", "Clear", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Add new doctor schedule after checking validations 
     */
    private void addDoctorSchedule() {
        String doctorID = (String) cmbDoctorID.getSelectedItem();
        String appointmentDate = txtAppointmentDate.getText();
        String timeSlot = (String) cmbScheduleTimeslot.getSelectedItem();
        boolean isAvailable = "Available".equals(cmbScheduleAvailability.getSelectedItem());

        if (doctorID.isEmpty() || appointmentDate.isEmpty() || timeSlot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (aLogic.checkScheduleIfExists(doctorID, appointmentDate, timeSlot).isEmpty()) {
            aLogic.addDoctorScheduleVector(doctorID, appointmentDate, timeSlot, isAvailable);
            JOptionPane.showMessageDialog(this, "Schedule added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Schedule already exists.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Search for doctor schedule and populate field
     */
    private void searchDoctorSchedule() {
        String doctorID = (String) cmbDoctorID.getSelectedItem();
        String date = txtAppointmentDate.getText();
        String timeSlot = (String) cmbScheduleTimeslot.getSelectedItem();

        DoctorSchedule schedule = aLogic.searchDoctorSchedule(doctorID, date, timeSlot);
        if (schedule == null) {
            JOptionPane.showMessageDialog(this, "No schedule found.", "Search", JOptionPane.WARNING_MESSAGE);
        } else {
            cmbDoctorID.setSelectedItem(schedule.getDoctorID());
            txtAppointmentDate.setText(schedule.getAvailableAppointmentDate());
            cmbScheduleAvailability.setSelectedItem(schedule.getAppointmentAvailability()? "Available" : "Not Available");
            cmbScheduleTimeslot.setSelectedItem(schedule.getAvailableAppointmentTimeSlot());
            JOptionPane.showMessageDialog(this, "Schedule found.", "Search", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Update existing doctor schedule record
     */
    private void updateDoctorSchedule() {
        String doctorID = (String) cmbDoctorID.getSelectedItem();
        String date = txtAppointmentDate.getText();
        String timeSlot = (String) cmbScheduleTimeslot.getSelectedItem();

        if (aLogic.searchDoctorSchedule(doctorID, date, timeSlot) != null) {
            aLogic.searchAndDeleteDoctorSchedule(doctorID, date, timeSlot);
            addDoctorSchedule();
        } else {
            JOptionPane.showMessageDialog(this, "No record found to update!", "Update", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 
     *
     * @return JFormattedTextField for date input.
     */
    private JFormattedTextField createDateField() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return new JFormattedTextField(new DateFormatter(format));
    }

    /**
     * 
     *
     * @param text 
     * @return
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }
}
