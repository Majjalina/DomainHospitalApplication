/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * AppointmentUI provides a user interface for managing appointments.
 * Features include creating, searching, updating, and clearing appointments.
 * 
 * @author eebej
 */
public class AppointmentUI extends JFrame {

    // UI Components
    private JButton exitButton, clearButton, addButton, searchButton;
    private JComboBox<String> cmbAppointmentStatus, cmbAttendanceStatus, cmbAppTimeSlot;
    private JTextField txtPatientName, txtPatientID, txtDoctorName, txtAppointmentDate, 
                        txtPatientSymptoms, txtDoctorID, txtAppDateTimeSlot;
    private JTable tblAppointments;

    // Dropdown list options
    private final String[] appointmentStatusList = {"Pending"};
    private final String[] attendanceStatusList = {"Pending"};
    private final String[] appTimeSlotList = {
        "09:00AM - 09:30AM", "09:30AM - 10:00AM",
        "10:00AM - 10:30AM", "10:30AM - 11:00AM", "11:00AM - 11:30AM",
        "11:30AM - 12:00PM", "16:00PM - 16:30PM", "16:30PM - 17:00PM",
        "17:00PM - 17:30PM", "17:30PM - 18:00PM", "18:00PM - 18:30PM", "18:30PM - 19:00PM"
    };

    // Table headers and model
    private final String[] tableHeader = {"Appointment Date", "Time Slot", "Availability"};
    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, tableHeader);

    // Logic layer instance
    ApplicationLogic aLogic = new ApplicationLogic();

    /**
     * Constructor to initialize the UI and setup components.
     */
    public AppointmentUI() {
        buildAppointmentGUI();
        setupEventListeners();
    }
/**
 * 
 * @param columns The preferred width in columns.
 * @return 
 */
private JTextField createStyledTextField(int columns) {
    JTextField textField = new JTextField(columns);
    textField.setFont(new Font("Arial", Font.PLAIN, 14));
    textField.setPreferredSize(new Dimension(200, 25));
    return textField;
}


/**
 * Creates a styled combo box.
 * @param items The list of items for the dropdown.
 * @return 
 */
private JComboBox<String> createStyledComboBox(String[] items) {
    JComboBox<String> comboBox = new JComboBox<>(items);
    comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
    comboBox.setPreferredSize(new Dimension(200, 25));
    return comboBox;
}
/**
 * .
 * @param text Button text.
 * @return 
 */
private JButton createStyledButton(String text) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setPreferredSize(new Dimension(160, 30));
    return button;
}


    /**
     * Builds the Appointment UI layout.
     */
public void buildAppointmentGUI() {
    // Set up frame properties
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Appointment Scheduler");
    setSize(800, 600);
    setLocationRelativeTo(null);

    // Main container with padding
    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Appointment details panel with GridBagLayout
    JPanel detailsPanel = new JPanel(new GridBagLayout());
    detailsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.weightx = 0.3; 
    gbc.anchor = GridBagConstraints.WEST;

    // Adding input fields and dropdowns
    gbc.gridx = 0; gbc.gridy = 0;
    detailsPanel.add(new JLabel("Patient ID:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(txtPatientID = createStyledTextField(15), gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    detailsPanel.add(new JLabel("Patient Name:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(txtPatientName = createStyledTextField(25), gbc);

    gbc.gridx = 0; gbc.gridy = 2;
    detailsPanel.add(new JLabel("Patient Symptoms:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(txtPatientSymptoms = createStyledTextField(50), gbc);

    gbc.gridx = 0; gbc.gridy = 3;
    detailsPanel.add(new JLabel("Doctor ID:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(txtDoctorID = createStyledTextField(15), gbc);

    gbc.gridx = 0; gbc.gridy = 4;
    detailsPanel.add(new JLabel("Doctor Name:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(txtDoctorName = createStyledTextField(25), gbc);

    gbc.gridx = 0; gbc.gridy = 5;
    detailsPanel.add(new JLabel("Appointment Date:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(txtAppointmentDate = createStyledTextField(15), gbc);

    gbc.gridx = 0; gbc.gridy = 6;
    detailsPanel.add(new JLabel("Time Slot:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(cmbAppTimeSlot = createStyledComboBox(appTimeSlotList), gbc);

    gbc.gridx = 0; gbc.gridy = 7;
    detailsPanel.add(new JLabel("Appointment Status:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(cmbAppointmentStatus = createStyledComboBox(appointmentStatusList), gbc);

    gbc.gridx = 0; gbc.gridy = 8;
    detailsPanel.add(new JLabel("Attendance Status:"), gbc);
    gbc.gridx = 1;
    detailsPanel.add(cmbAttendanceStatus = createStyledComboBox(attendanceStatusList), gbc);

    // Appointment table
    tblAppointments = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(tblAppointments);
    tableScrollPane.setPreferredSize(new Dimension(750, 200));

    // Button panel
    JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    buttonPanel.add(addButton = createStyledButton("Add Appointment"));
    buttonPanel.add(clearButton = createStyledButton("Clear"));
    buttonPanel.add(searchButton = createStyledButton("Search"));
    buttonPanel.add(exitButton = createStyledButton("Exit"));

    // Add components to the main panel
    mainPanel.add(detailsPanel, BorderLayout.NORTH);
    mainPanel.add(tableScrollPane, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    // Add main panel to frame
    add(mainPanel);
}


    /**
     * Sets up event listeners for buttons and table actions.
     */
    private void setupEventListeners() {
        // Add Appointment Button
        addButton.addActionListener(e -> addAppointment());

        // Clear Button
        clearButton.addActionListener(e -> clearFields());

        // Search Button
        searchButton.addActionListener(e -> {
            searchPatient();
            searchDoctor();
            populateTable();
        });

        // Exit Button
        exitButton.addActionListener(e -> dispose());

        // Table Row Selection
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = tblAppointments.getSelectedRow();
                if (selectedRow >= 0) {
                    txtAppointmentDate.setText(tblAppointments.getValueAt(selectedRow, 0).toString());
                    txtAppDateTimeSlot.setText(tblAppointments.getValueAt(selectedRow, 1).toString());
                }
            }
        });
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearFields() {
        txtPatientID.setText("");
        txtPatientName.setText("");
        txtPatientSymptoms.setText("");
        txtDoctorID.setText("");
        txtDoctorName.setText("");
        txtAppointmentDate.setText("");
        txtAppDateTimeSlot.setText("");
        cmbAppointmentStatus.setSelectedIndex(0);
        cmbAttendanceStatus.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Form cleared successfully.", "Clear", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
 * Searches for a patient by ID and populates the relevant fields.
 */
public void searchPatient() {
    String patientID = JOptionPane.showInputDialog(this, "Enter Patient ID to search:");
    if (patientID != null && !patientID.trim().isEmpty()) {
        boolean exists = ApplicationLogic.GetPatientID(patientID); // Check if patient exists
        if (exists) {
            Patient patient = ApplicationLogic.GetPatientData(patientID);
            txtPatientID.setText(patient.getPatientID());
            txtPatientName.setText(patient.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No patient found with ID: " + patientID, "Error", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Patient ID cannot be empty.", "Error", JOptionPane.WARNING_MESSAGE);
    }
}

/**
 * Searches for a doctor by ID and populates the relevant fields.
 */
public void searchDoctor() {
    String doctorID = JOptionPane.showInputDialog(this, "Enter Doctor ID to search:");
    if (doctorID != null && !doctorID.trim().isEmpty()) {
        boolean exists = ApplicationLogic.GetDoctorID(doctorID); // Check if doctor exists
        if (exists) {
            Staff doctor = ApplicationLogic.GetDoctorData(doctorID);
            txtDoctorID.setText(doctor.getStaffID());
            txtDoctorName.setText(doctor.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No doctor found with ID: " + doctorID, "Error", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Doctor ID cannot be empty.", "Error", JOptionPane.WARNING_MESSAGE);
    }
}


    /**
     * Adds a new appointment after validation.
     */
    private void addAppointment() {
        String patientID = txtPatientID.getText().trim();
        String doctorID = txtDoctorID.getText().trim();
        String appointmentDate = txtAppointmentDate.getText().trim();
        String timeSlot = (String) cmbAppTimeSlot.getSelectedItem();

        if (patientID.isEmpty() || doctorID.isEmpty() || appointmentDate.isEmpty() || timeSlot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String appointmentID = patientID + "-" + appointmentDate;

        if (aLogic.searchAppointment(appointmentID) == null) {
            aLogic.addAppointmentVector(appointmentID, patientID, doctorID, appointmentDate, timeSlot,
                txtPatientSymptoms.getText(), "", "", 
                (String) cmbAppointmentStatus.getSelectedItem(), 
                (String) cmbAttendanceStatus.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Appointment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Appointment already exists for this date.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    

    /**
     * Populates the table with available schedules for the given doctor.
     */
    private void populateTable() {
        tableModel.setRowCount(0); // Clear existing data
        String doctorID = txtDoctorID.getText().trim();
        Vector<DoctorSchedule> schedules = aLogic.searchDoctorScheduleVectorByAvailabilityTRUE(doctorID);

        if (schedules.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No available schedule found.", "Info", JOptionPane.WARNING_MESSAGE);
        } else {
            for (DoctorSchedule schedule : schedules) {
                tableModel.addRow(new Object[]{schedule.getAvailableAppointmentDate(), schedule.getAvailableAppointmentTimeSlot(), schedule.getAppointmentAvailability()});
            }
        }
    }

}

