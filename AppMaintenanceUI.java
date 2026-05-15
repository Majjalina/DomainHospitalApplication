/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.*;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * functionality for viewing, editing, and updating appointments, as well as
 * managing patient and doctor information associated with the appointments. The
 * UI includes interactive components such as buttons, text fields, combo boxes,
 * and a table to facilitate data input and display.
 *
 * @author eebej
 */
public class AppMaintenanceUI extends JFrame {

    // Buttons for user actions
    private JButton exitButton, clearButton, updateButton, searchButton;

    // Dropdowns comboboxes for status 
    private JComboBox cmbAppointmentStatus, cmbAttendanceStatus, cmbAppointmentDateTimeSlot;

    // Input fields labels
    private JLabel lblPatientName, lblPatientID, lblDoctorID, lblDoctorName, lblAppointmentDate,
            lblAppointmentDateTimeSlot, lblPatientSymptoms, lblDoctorNotes, lblDoctorMedications,
            lblAppointmentStatus, lblAttendanceStatus, lblappID;

    // User input Text fields
    private JTextField txtPatientName, txtPatientID, txtDoctorID, txtDoctorName, txtAppointmentDate,
            txtPatientSymptoms, txtDoctorNotes, txtDoctorMedications, txtAppointmentDateTimesSlot, txtappID;

    // combo box options (arrays)
    private final String[] appoitmentStatusList = {"Pending", "Attended", "Cancelled", "No Show"};
    private final String[] attendanceStatusList = {"Pending", "Checked In", "Diagnosed", "Finished"};

    // Table column names array
    private String[] tableHeader = {"Appointment Date", "Appointment Time Slot", "Appointment Availability"};

    // Aappointments table
    private JTable tblAppointments;
    DefaultTableModel model;

    // Backend logic instance
    ApplicationLogic aLogic = new ApplicationLogic();

    String appID = "", edit_doctorID = "", edit_date = "", edit_timeslot = "";

    /**
     * Default constructor initializes the user interface and sets up event listeners
     */
    public AppMaintenanceUI() {
        // Build the graphical user interface
        builtAppointmentMaintenanceGUI();

        // Set up event listeners for button actions
        setupEventListeners();
    }

    /**
     * Appointment maintenance GUI for fields, buttons, tables
     * 
     */
    public void builtAppointmentMaintenanceGUI() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Appointment Maintenance");
        setSize(900, 650);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header label
        JLabel headerLabel = new JLabel("Appointment Maintenance", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Section for appointment details input
        JPanel appointmentDetailsPanel = new JPanel(new GridBagLayout());
        appointmentDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Adding input fields
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Appointment ID:", txtappID = new JTextField(15), 0);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Patient ID:", txtPatientID = new JTextField(15), 1);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Patient Name:", txtPatientName = new JTextField(25), 2);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Patient Symptoms:", txtPatientSymptoms = new JTextField(50), 3);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Doctor ID:", txtDoctorID = new JTextField(15), 4);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Doctor Name:", txtDoctorName = new JTextField(25), 5);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Appointment Date:", txtAppointmentDate = new JTextField(15), 6);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Time Slot:", txtAppointmentDateTimesSlot = new JTextField(15), 7);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Doctor Notes:", txtDoctorNotes = new JTextField(50), 8);
        addInputFieldToPanel(appointmentDetailsPanel, gbc, "Doctor Medications:", txtDoctorMedications = new JTextField(50), 9);

        // Adding dropdowns for status
        addComboBoxToPanel(appointmentDetailsPanel, gbc, "Appointment Status:", cmbAppointmentStatus = new JComboBox<>(appoitmentStatusList), 10);
        addComboBoxToPanel(appointmentDetailsPanel, gbc, "Attendance Status:", cmbAttendanceStatus = new JComboBox<>(attendanceStatusList), 11);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(850, 1));
        mainPanel.add(separator, BorderLayout.CENTER);

        // Section for appointment table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        tblAppointments = new JTable(new DefaultTableModel(new Object[][]{}, tableHeader));
        tblAppointments.setFillsViewportHeight(true);
        tblAppointments.setPreferredScrollableViewportSize(new Dimension(850, 250));
        tablePanel.add(new JScrollPane(tblAppointments), BorderLayout.CENTER);

        // Section for action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(updateButton = createStyledButton("Update"));
        buttonPanel.add(clearButton = createStyledButton("Clear"));
        buttonPanel.add(searchButton = createStyledButton("Search"));
        buttonPanel.add(exitButton = createStyledButton("Exit"));

        mainPanel.add(appointmentDetailsPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Adds a label and text field to the panel using GridBagLayout.
     *
     * @param panel
     * @param gbc
     * @param labelText 
     * @param textField
     * @param row
     */
    private void addInputFieldToPanel(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(textField, gbc);
    }

    /**
     * Add label plus combo box to panel with GridBagLayout
     *
     * @param panel 
     * @param gbc
     * @param labelText
     * @param comboBox 
     * @param row 
     */
    private void addComboBoxToPanel(JPanel panel, GridBagConstraints gbc, String labelText, JComboBox<String> comboBox, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(comboBox, gbc);
    }

    /**
     * 
     */
    private void setupEventListeners() {
        // Listeners Update, Exit, Clear, Search and table row selection buttons
        updateButton.addActionListener(e -> updateAppointment());
        exitButton.addActionListener(e -> dispose());
        clearButton.addActionListener(e -> clearRecord());
        searchButton.addActionListener(e -> searchAppointmentByAppID());
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAppointmentDate.setText(tblAppointments.getValueAt(tblAppointments.getSelectedRow(), 0).toString());
                txtAppointmentDateTimesSlot.setText(tblAppointments.getValueAt(tblAppointments.getSelectedRow(), 1).toString());
            }
        });
    }

    /**
     * 
     */
    public void searchAppointment() {
        boolean quit = false;

        while (!quit) {
            // input an Appointment ID to search
            String appointmentID = JOptionPane.showInputDialog(null, "Search by Appointment ID:", "APPOINTMENT - SEARCH BY ID", JOptionPane.PLAIN_MESSAGE);

            if (appointmentID != null && !appointmentID.trim().isEmpty()) {
                try {
                    // Call apopintment ID search function
                    searchAppointmentByAppID();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error while searching for Appointment ID!\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (appointmentID == null) {
                // exit the loop
                quit = true;
            } else {
                JOptionPane.showMessageDialog(null, "Empty or invalid Appointment ID! Please try again.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * 
     */
    public void searchAppointmentByAppID() {

        String view_ID = JOptionPane.showInputDialog(null, "Please provide Appointment ID:");
        Appointment app = aLogic.searchAppointment(view_ID);

        if (app == null) {
            JOptionPane.showMessageDialog(null, "Oh no! No records found!", "Search", JOptionPane.WARNING_MESSAGE);
        } else {

            appID = view_ID;
            this.txtappID.setText(app.getAppointment_AppointmentID());
            appID = app.getAppointment_AppointmentID();

            this.txtPatientID.setText(app.getAppointment_PatientID());
            this.txtDoctorID.setText(app.getAppointment_DoctorID());
            edit_doctorID = app.getAppointment_DoctorID();

            this.txtPatientSymptoms.setText(app.getAppointment_PatientSymptoms());
            this.txtAppointmentDate.setText(app.getAppointment_AppointmentDate());

            edit_date = app.getAppointment_AppointmentDate();
            this.txtDoctorMedications.setText(app.getAppointment_DoctorMedications());
            this.txtDoctorNotes.setText(app.getAppointment_DoctorNotes());
            this.txtAppointmentDateTimesSlot.setText(app.getAppointment_AppointmentTimeSlot());
            edit_timeslot = app.getAppointment_AppointmentTimeSlot();

            this.cmbAppointmentStatus.addItem(app.getAppointment_AppointmentStatus());
            this.cmbAttendanceStatus.addItem(app.getAppointment_AppointmentStatus());

            searchPatient();   // Get and display patient information
            searchDoctor();    // Get and display doctor information
            populateTable();   // Populate the table with the relevant schedule
        }
    }

    /**
     * 
     */
    public void searchPatient() {

        String view_ID = this.txtPatientID.getText();
        boolean patientExists = ApplicationLogic.GetPatientID(view_ID);

        if (patientExists) {
            this.txtPatientID.setEnabled(false);

            Patient patientData = ApplicationLogic.GetPatientData(view_ID);
            this.txtPatientName.setText(patientData.getName());
        } else {
            JOptionPane.showMessageDialog(null, "No such patient with ID: " + view_ID, "Search", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 
     */
    public void searchDoctor() {
        String view_ID = this.txtDoctorID.getText();
        boolean staffExists = ApplicationLogic.GetDoctorID(view_ID);

        if (staffExists) {
             Staff staffData = ApplicationLogic.GetStaffData(view_ID);
            this.txtDoctorName.setText(staffData.getName());
        } else {
             JOptionPane.showMessageDialog(null, "No such doctor with ID: " + view_ID, "Search", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Updating Doctor Schedule
     */
    public void editDoctorSchedule() {
        // Get current doctor schedule details
        String doctorID = this.txtDoctorID.getText();
        String scheduleDate = this.txtAppointmentDate.getText();
        String scheduleTimeslot = this.txtAppointmentDateTimesSlot.getText();

        // Search existing doctor schedule
        DoctorSchedule ds = aLogic.searchDoctorSchedule(edit_doctorID, edit_date, edit_timeslot);

        // Logic to handle cases if no  schedule is found
        if (ds.toString() == null) {
            JOptionPane.showMessageDialog(null, "Uh no! No such record found in current schedule!", "Search", JOptionPane.WARNING_MESSAGE);
        } else if (!ds.getAppointmentAvailability()) {
            // Ser previous schedule as 'available'
            aLogic.searchAndDeleteDoctorSchedule(edit_doctorID, edit_date, edit_timeslot);
            aLogic.addDoctorScheduleVector(edit_doctorID, edit_date, edit_timeslot, true);

            // Update new schedule as 'unavailable'
            aLogic.searchAndDeleteDoctorSchedule(doctorID, scheduleDate, scheduleTimeslot);
            aLogic.addDoctorScheduleVector(doctorID, scheduleDate, scheduleTimeslot, false);

            JOptionPane.showMessageDialog(null, "Doctor's schedule updated successfully!", "Doctor Schedule", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * 
     */
    public void updateAppointment() {
        // Check if the selected appointment status is "Cancel"
        if (this.cmbAppointmentStatus.getSelectedItem().toString().equals("Cancel")) {
            // Search for the appointment using the Appointment ID
            Appointment app = aLogic.searchAppointment(appID);

            if (app.toString() == null) {
                JOptionPane.showMessageDialog(null, "Oh no! No such Appointment found1", "Search", JOptionPane.WARNING_MESSAGE);
            } else {
                // Remove the appointment and update the doctor's schedule
                aLogic.searchAndDeleteAppointment(appID);
                DoctorSchedule ds = aLogic.searchDoctorSchedule(edit_doctorID, edit_date, edit_timeslot);

                if (ds.toString() == null) {
                    JOptionPane.showMessageDialog(null, "Oh no! Doctor's schedule record not found!", "Search", JOptionPane.WARNING_MESSAGE);
                } else if (!ds.getAppointmentAvailability()) {
                    aLogic.searchAndDeleteDoctorSchedule(edit_doctorID, edit_date, edit_timeslot);
                    aLogic.addDoctorScheduleVector(edit_doctorID, edit_date, edit_timeslot, true);

                    JOptionPane.showMessageDialog(null, "Doctor's schedule has been updated to available.", "Doctor Schedule", JOptionPane.INFORMATION_MESSAGE);
                }

                // Add a new appointment if necessary
                addAppointment();

                JOptionPane.showMessageDialog(null, "Appointment has been canceled!", "Cancel", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // Handle updates for non-canceled appointments
            Appointment app = aLogic.searchAppointment(appID);

            if (app.toString() == null) {
                JOptionPane.showMessageDialog(null, "Appointment record not found!", "Search", JOptionPane.WARNING_MESSAGE);
            } else {
                // Update the appointment details
                aLogic.searchAndDeleteAppointment(appID);
                addAppointment();
            }
        }
    }

    /**
     * 
     */
    public void populateTable() {
        // Get the Doctor ID from the input field
        String view_ID = this.txtDoctorID.getText();

        // Clear existing rows in the table
        DefaultTableModel dtm = (DefaultTableModel) tblAppointments.getModel();
        dtm.setRowCount(0);

        // Fetch available schedules for the doctor
        Vector<DoctorSchedule> searchDoctorScheduleVector = aLogic.searchDoctorScheduleVectorByAvailabilityTRUE(view_ID);

        // Populate the table with the available schedules
        for (DoctorSchedule schedule : searchDoctorScheduleVector) {
            dtm.addRow(new Object[]{schedule.getAvailableAppointmentDate(), schedule.getAvailableAppointmentTimeSlot(), schedule.getAppointmentAvailability()});
        }

        // Set focus on the first row
        if (tblAppointments.getRowCount() > 0) {
            tblAppointments.changeSelection(0, 0, false, false);
        }
    }

    /**
     * Reset fields methods
     */
    public void clearRecord() {
        // Reset all text fields
        txtappID.setText("");
        txtPatientID.setText("");
        txtPatientName.setText("");
        txtDoctorID.setText("");
        txtDoctorName.setText("");
        txtAppointmentDate.setText("");
        txtAppointmentDateTimesSlot.setText("");
        txtPatientSymptoms.setText("");
        txtDoctorNotes.setText("");
        txtDoctorMedications.setText("");

        // Reset combo boxes to their default state "pending"
        cmbAppointmentStatus.setSelectedIndex(0);
        cmbAttendanceStatus.setSelectedIndex(0);

        // Enable the Patient ID field if it was disabled
        txtPatientID.setEnabled(true);

        // Clear the table if applicable
        DefaultTableModel dtm = (DefaultTableModel) tblAppointments.getModel();
        dtm.setRowCount(0);

        // Reset tracking variables
        appID = "";
        edit_doctorID = "";
        edit_date = "";
        edit_timeslot = "";

        JOptionPane.showMessageDialog(null, "Form has been cleared.", "Clear", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Adds a new appointment and update schedules.
     * 
     */
    public void addAppointment() {
        String app_appID, app_patientID, app_doctorID, app_date, app_timeslot, app_patientSymptoms,
                app_doctorNotes, app_doctorMedications, app_appointmentStatus, app_attendanceStatus;

        // Get inputs from form fields
        app_patientID = txtPatientID.getText().trim();
        app_doctorID = txtDoctorID.getText().trim();
        app_date = txtAppointmentDate.getText().trim();
        app_timeslot = txtAppointmentDateTimesSlot.getText().trim();
        app_patientSymptoms = txtPatientSymptoms.getText().trim();
        app_doctorNotes = txtDoctorNotes.getText().trim();
        app_doctorMedications = txtDoctorMedications.getText().trim();
        app_appointmentStatus = cmbAppointmentStatus.getSelectedItem().toString();
        app_attendanceStatus = cmbAttendanceStatus.getSelectedItem().toString();

        // Validate required fields
        if (app_patientID.isEmpty() || app_doctorID.isEmpty() || app_date.isEmpty() || app_timeslot.isEmpty() || app_patientSymptoms.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All mandatory fields must be input!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set unique appointment ID with patient ID and date
        app_appID = app_patientID + "-" + app_date;

        // Check for duplicate appointments
        Appointment existingAppointment = aLogic.searchAppointment(app_appID);
        if (existingAppointment != null) {
            JOptionPane.showMessageDialog(null, "Oh no! Duplicate appointment detected! Please choose another date or time-slot.",
                    "Duplicate Appointment", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Add the appointment
        aLogic.addAppointmentVector(app_appID, app_patientID, app_doctorID, app_date, app_timeslot, app_patientSymptoms,
                app_doctorNotes, app_doctorMedications, app_appointmentStatus, app_attendanceStatus);

        // Update the doctor's schedule
        editDoctorSchedule();

        // Notify the user of successful addition
        JOptionPane.showMessageDialog(null, "Appointment successfully scheduled! Appointment ID: " + app_appID,
                "Appointment Added", JOptionPane.INFORMATION_MESSAGE);

        // resets the form
        clearRecord();
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
        button.setPreferredSize(new Dimension(140, 30));
        return button;
    }

}
