package domainhospitalapplication;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * @author eebej
 */
public class MenuUI extends JFrame {

    // Parameters to identify user and role
    String param, userType;

    // Instances of other UI components
    PatientUI patientUserInterface = new PatientUI();
    StaffUI staffUserInterface = new StaffUI();
    DoctorScheduleUI doctorSchedulerInterface = new DoctorScheduleUI();
    AppointmentUI appointmentInterface = new AppointmentUI();
    AppMaintenanceUI appointmentMaintenanceInterface = new AppMaintenanceUI();
    ReportingUI reportingInterface = new ReportingUI();
    PaymentUI paymentInterface = new PaymentUI();

    /**
     * Default Constructor
     */
    public MenuUI() {
    }

    /**
     * Constructor to initialize the menu
     *
     * @param param  
     * @param userType Role of the user (e.g., Patient, Doctor, Receptionist).
     */
    public MenuUI(String param, String userType) {
        this.param = param;
        this.userType = userType;

        if ("Patient".equals(userType)) {
            setTitleForUser("Patient");
            buildPatientMenu();
        } else if ("Doctor".equals(userType)) {
            setTitleForUser("Doctor");
            buildDoctorMenu();
        } else if ("Receptionist".equals(userType)) {
            setTitleForUser("Receptionist");
            buildMainMenu();
        }

        // Set window properties
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * window title based on the user's role and details.
     *
     * @param role The user's role (e.g., Patient, Doctor, Receptionist).
     */
    private void setTitleForUser(String role) {
        String loginUser = "";
        if ("Patient".equals(role)) {
            Patient patientData = ApplicationLogic.GetPatientData(param);
            loginUser = patientData.getName() + " " + patientData.getSurname();
        } else if ("Doctor".equals(role)) {
            Doctor doctorData = ApplicationLogic.GetDoctorData(param);
            loginUser = doctorData.getName() + " " + doctorData.getSurname();
        } else if ("Receptionist".equals(role)) {
            Staff staffData = ApplicationLogic.GetStaffData(param);
            loginUser = staffData.getName() + " " + staffData.getSurname();
        }
        setTitle("Domain Hospital Management System - " + loginUser + " - " + role);
    }

    /**
     * main menu for Receptionist users.
     */
    private void buildMainMenu() {
        setupMenuBar(true, true, true, true);
        addBrandingPanel("Manage the hospital efficiently.");
    }

    /**
     *  Patient users.
     */
    private void buildPatientMenu() {
        setupMenuBar(false, false, false, true);
        addBrandingPanel("Manage the hospital efficiently.");
    }

    /**
     * menu for Doctor users.
     */
    private void buildDoctorMenu() {
        setupMenuBar(false, true, true, true);
        addBrandingPanel("Manage the hospital efficiently.");
    }

    /**
     *  menu bar with options based on user permissions.
     *
     * @param enableNew     
     * @param enableScheduler
     * @param enableReports
     * @param enableExit
     */
    private void setupMenuBar(boolean enableNew, boolean enableScheduler, boolean enableReports, boolean enableExit) {
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    // Create  menus
    JMenu fileMenu = createMenu("File");
    JMenu doctorSchedule = createMenu("Doctor Scheduler");
    JMenu reporting = createMenu("Reports");
    JMenu aboutMenu = createMenu("About");

    // Add  menus menu bar
    menuBar.add(fileMenu);
    menuBar.add(doctorSchedule);
    menuBar.add(reporting);
    menuBar.add(aboutMenu);

    // "Manage Payments" for Receptionists
    if ("Receptionist".equals(userType)) {
        JMenuItem managePayments = createMenuItem("Manage Payments", e -> paymentInterface.setVisible(true));
        JMenu paymentMenu = createMenu("Payments");
        paymentMenu.add(managePayments);
        menuBar.add(paymentMenu); // Add directly to the menu bar
    }

    if (enableNew) {
        JMenu newAction = createMenu("New");
        fileMenu.add(newAction);
        newAction.add(createMenuItem("Patient", e -> patientUserInterface.setVisible(true)));
        newAction.add(createMenuItem("Staff", e -> staffUserInterface.setVisible(true)));
        newAction.add(createMenuItem("Appointment", e -> appointmentInterface.setVisible(true)));
    }

    JMenu viewAction = createMenu("View");
    fileMenu.add(viewAction);
    viewAction.add(createMenuItem("Appointment Maintenance", e -> appointmentMaintenanceInterface.setVisible(true)));

    if (enableScheduler) {
        doctorSchedule.add(createMenuItem("Scheduler", e -> doctorSchedulerInterface.setVisible(true)));
    }

    if (enableReports) {
        reporting.add(createMenuItem("View Reports", e -> reportingInterface.setVisible(true)));
    }

    if (enableExit) {
        fileMenu.add(createMenuItem("Exit", e -> dispose())); // Close window directly
    }

    aboutMenu.add(createMenuItem("About", e -> JOptionPane.showMessageDialog(
        this, "- Domain Hospital Application -\nDeveloped by Emily Bonnici", "About", JOptionPane.INFORMATION_MESSAGE)));
}


    /**
     * welcome message to the main panel.
     *
     * @param message 
     */
    private void addBrandingPanel(String message) {
        JPanel brandingPanel = new JPanel(new BorderLayout());
        brandingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel logoLabel = new JLabel("Domain Hospital", JLabel.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        brandingPanel.add(logoLabel, BorderLayout.NORTH);
        brandingPanel.add(messageLabel, BorderLayout.CENTER);

        add(brandingPanel, BorderLayout.CENTER);
    }

    /**
     * specified title.
     *
     * @param title 
     * @return 
     */
    private JMenu createMenu(String title) {
        JMenu menu = new JMenu(title);
        menu.setFont(new Font("Arial", Font.BOLD, 14));
        return menu;
    }

    /**
     *  JMenuItem with the specified title and action.
     *
     * @param title  
     * @param action 
     * @return  JMenuItem instance.
     */
    private JMenuItem createMenuItem(String title, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Arial", Font.BOLD, 14));
        menuItem.addActionListener(action);
        return menuItem;
    }
}
