/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * Reporting User Interface
 *
 * @author eebej
 */
public class ReportingUI extends JFrame {

    // UI Components
    private JButton viewAllPatientAppointmentHistory, viewAllDoctorSchedules, viewAllDoctorAppointments, exportReport, exitButton;
    private JTable tblReport;
    private DefaultTableModel model;

    // Backend Logic
    private final ApplicationLogic aLogic = new ApplicationLogic();

    /**
     * Constructor
     */
    public ReportingUI() {
        buildReportingGUI();
        setupListeners();
    }

    /**
     * Builds the Reporting User Interface
     */
    private void buildReportingGUI() {
        setTitle("Reporting Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel headerLabel = new JLabel("Data Access Reports", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addButtonToPanel(buttonPanel, gbc, viewAllDoctorSchedules = createButton("View All Doctor Schedules"), 0);
        addButtonToPanel(buttonPanel, gbc, viewAllDoctorAppointments = createButton("View All Doctor Appointments"), 1);
        addButtonToPanel(buttonPanel, gbc, viewAllPatientAppointmentHistory = createButton("View Patient Appointment History"), 2);
        addButtonToPanel(buttonPanel, gbc, exportReport = createButton("Export Report"), 3);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(null);
        tblReport = new JTable(new DefaultTableModel());
        JScrollPane tableScrollPane = new JScrollPane(tblReport);
        tableScrollPane.setBorder(null);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.add(exitButton = createButton("Exit"));
        footerPanel.setBorder(null);

        // Add Panels to Main Panel
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Sets up event listeners
     */
    private void setupListeners() {
        viewAllDoctorSchedules.addActionListener(e -> getAllDoctorSchedulesByDoctorID());
        //viewAllDoctorAppointments.addActionListener(e -> getAllDoctorAppointmentsByDoctorID()); mhux tahdem
        viewAllPatientAppointmentHistory.addActionListener(e -> getAllPatientAppointmentsByPatientID());
        exportReport.addActionListener(e -> exportReport());
        exitButton.addActionListener(e -> dispose());
    }

    /**
     * Adds a button to the GridBagLayout panel.
     */
    private void addButtonToPanel(JPanel panel, GridBagConstraints gbc, JButton button, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(button, gbc);
    }

    /**
     * Retrieves all doctor schedules by Doctor ID and populates the table.
     */
    private void getAllDoctorSchedulesByDoctorID() {
        String doctorID = JOptionPane.showInputDialog(this, "Enter Doctor ID:");
        if (doctorID == null || doctorID.trim().isEmpty()) {
            return;
        }

        Vector<DoctorSchedule> schedules = aLogic.searchDoctorScheduleVector(doctorID);
        if (schedules.isEmpty()) {
            showInfo("No schedules found for Doctor ID: " + doctorID);
            return;
        }

        updateTable(schedules, new String[]{"Appointment Date", "Time Slot", "Availability"});
    }

// NOT WORKING 
//    private void getAllDoctorAppointmentsByDoctorID() {
//        String doctorID = JOptionPane.showInputDialog(this, "Enter Doctor ID:");
//        if (doctorID == null || doctorID.trim().isEmpty()) {
//            return;
//        }
//
//        Vector<Appointment> appointments = aLogic.searchAppointmentByDoctorID(doctorID); 
//
//        if (appointments.isEmpty()) {
//            showInfo("No appointments found for Doctor ID: " + doctorID);
//            return;
//        }
//
//        // Update table with the fetched appointments
//        updateTable(appointments, new String[]{
//            "Appointment ID", "Date", "Time Slot", "Status", "Patient ID", "Symptoms", "Medications"
//        });
//    }
 
   
    /**
     * Retrieves all patient appointments by Patient ID and populates the table.
     */
    private void getAllPatientAppointmentsByPatientID() {
        String patientID = JOptionPane.showInputDialog(this, "Enter Patient ID:");
        if (patientID == null || patientID.trim().isEmpty()) {
            return;
        }

        Vector<Appointment> appointments = aLogic.searchPatientAppointmentsVector(patientID);
        if (appointments.isEmpty()) {
            showInfo("No appointments found for Patient ID: " + patientID);
            return;
        }

        updateTable(appointments, new String[]{"Appointment ID", "Date", "Time Slot", "Status", "Symptoms", "Medications"});
    }

    /**
     * Updates the table with new data.
     */
    private void updateTable(Vector<?> data, String[] columns) {
        DefaultTableModel newModel = new DefaultTableModel(columns, 0);
        for (Object obj : data) {
            if (obj instanceof DoctorSchedule) {
                DoctorSchedule schedule = (DoctorSchedule) obj;
                newModel.addRow(new Object[]{
                    schedule.getAvailableAppointmentDate(),
                    schedule.getAvailableAppointmentTimeSlot(),
                    schedule.getAppointmentAvailability()
                });
            } else if (obj instanceof Appointment) {
                Appointment appointment = (Appointment) obj;
                newModel.addRow(new Object[]{
                    appointment.getAppointment_AppointmentID(),
                    appointment.getAppointment_AppointmentDate(),
                    appointment.getAppointment_AppointmentTimeSlot(),
                    appointment.getAppointment_AppointmentStatus(),
                    appointment.getAppointment_PatientSymptoms(),
                    appointment.getAppointment_DoctorMedications()
                });
            }
        }
        tblReport.setModel(newModel);
    }

    /**
     * Exports the current table data to a text file
     */
    private void exportReport() {
        try {
            File exportFile = new File("Generated_report.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(exportFile))) {
                for (int i = 0; i < tblReport.getRowCount(); i++) {
                    for (int j = 0; j < tblReport.getColumnCount(); j++) {
                        writer.write(tblReport.getColumnName(j) + ": " + tblReport.getValueAt(i, j) + "\n");
                    }
                    writer.write("--------------------------------------------------\n");
                }
            }
            showSuccess("Report successfully generated and exported to Generated_report.txt");
        } catch (IOException e) {
            showError("Error exporting report: " + e.getMessage());
        }
    }

    /**
     * Creates a styled button
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(300, 40));
        return button;
    }

    /**
     * Show information message
     */
    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show success message
     */
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show error message
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
