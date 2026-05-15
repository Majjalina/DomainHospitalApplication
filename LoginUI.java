/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author eebej
 */
public class LoginUI extends JFrame {

    //Declaration of JTextFields
    public JTextField txtUserID;

    //Declaration of JComboBox
    private JComboBox cmbLoginUserAs;

    //Declaration of JLabels
    private JLabel lblLoginUserAs, lblUserID;

    //Declaration of JButtons
    private JButton exitButton, clearButton, loginButton;

    // Instance of ApplicationLogic Class
    ApplicationLogic aLogic = new ApplicationLogic();

    //Declaration of String Array to be populated as list in the JComboBox
    private final String[] loginAsUser = {"Receptionist", "Doctor", "Pharmacists", "Patient"};
    String userID;

    //Default Constructor
    public LoginUI() {

        //Calling the buildLoginGUI() method
        buildLoginGUI();

        //On click event to login button. login() method will be executed
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Calling Login method
                Login();
            }
        });

        //On click event to clear button. clearData() method will be executed
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Calling clearData method
                clearData();
            }
        });

        //Exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // exiting / closing the screen
                System.exit(0);

            }
        });

    }

    /*
    *Creatde LoginUI Screen 
     */
    public void buildLoginGUI() {
        // Set the frame properties
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login Screen");
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Create the main container with a vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Title section
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(200, 230, 201));
        JLabel titleLabel = new JLabel("Welcome to Domain Hospital");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
        titleLabel.setForeground(new Color(51, 102, 153));
        titlePanel.add(titleLabel);

        // Login details section
        JPanel loginDetailPanel = new JPanel(new GridLayout(2, 2, 15, 15)); 
        loginDetailPanel.setBackground(Color.WHITE);
        loginDetailPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel lblLoginAs = new JLabel("Log in as:");
        lblLoginAs.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbLoginUserAs = new JComboBox<>(loginAsUser);
        cmbLoginUserAs.setPreferredSize(new Dimension(200, 25));

        JLabel lblUserID = new JLabel("User ID:");
        lblUserID.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUserID = new JTextField();
        txtUserID.setPreferredSize(new Dimension(200, 25));

        loginDetailPanel.add(lblLoginAs);
        loginDetailPanel.add(cmbLoginUserAs);
        loginDetailPanel.add(lblUserID);
        loginDetailPanel.add(txtUserID);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        // Style buttons for a unified look
        JButton[] buttons = {loginButton, clearButton, exitButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBackground(new Color(220, 220, 220));
            button.setForeground(new Color(51, 51, 51));
            button.setPreferredSize(new Dimension(100, 30));
            buttonPanel.add(button);
        }

        // Add panels to main 
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(loginDetailPanel);
        mainPanel.add(Box.createVerticalStrut(20)); 
        mainPanel.add(buttonPanel);

        // Add main panel
        add(mainPanel);
    }

    /**
     * Reset all form components to their default state
     */
    public void clearData() {
        // Clear the User ID field
        this.txtUserID.setText("");
        // Reset the "Log in as" dropdown to the first item
        this.cmbLoginUserAs.setSelectedIndex(0);
    }

    /**
     * Authenticate the user based on the User ID and role and 
     * redirects the user to the respective menu if authenticated successfully
     * 
     */
    public void Login() {
        // Get the entered User ID
        userID = txtUserID.getText().trim();

        // Validate if the User ID is empty
        if (userID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "User ID is required. Please input a User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Determine the selected role
        String selectedRole = cmbLoginUserAs.getSelectedItem().toString();

        // Handle login logic based on the selected role
        switch (selectedRole) {
            case "Patient":
                if (ApplicationLogic.GetPatientID(userID)) {
                    Patient patientData = ApplicationLogic.GetPatientData(userID);
                    JOptionPane.showMessageDialog(null, "Welcome, " + patientData.getName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    navigateToMenu(selectedRole);
                } else {
                    displayUserNotFoundMessage();
                }
                break;

            case "Doctor":
                if (ApplicationLogic.GetDoctorID(userID)) {
                    Doctor doctorData = ApplicationLogic.GetDoctorData(userID);
                    JOptionPane.showMessageDialog(null, "Welcome, Dr. " + doctorData.getName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    navigateToMenu(selectedRole);
                } else {
                    displayUserNotFoundMessage();
                }
                break;

            case "Receptionist":
                if (ApplicationLogic.GetStaffID(userID)) {
                    Staff staffData = ApplicationLogic.GetStaffData(userID);
                    JOptionPane.showMessageDialog(null, "Welcome, " + staffData.getName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    navigateToMenu(selectedRole);
                } else {
                    displayUserNotFoundMessage();
                }
                break;

            default:
                JOptionPane.showMessageDialog(null, "Role not supported!", "Error", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    /**
     * Redirects the authenticated user to the main menu
     *
     * @param role 
     */
    private void navigateToMenu(String role) {
        String getUserID = this.txtUserID.getText();
        MenuUI mainMenuInterface = new MenuUI(getUserID, role);
        mainMenuInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuInterface.setVisible(true);
    }

    /**
     * Displays a message indicating that the user was not found.
     */
    private void displayUserNotFoundMessage() {
        JOptionPane.showMessageDialog(null, "Uh no! No user found with the provided ID!", "Search", JOptionPane.INFORMATION_MESSAGE);

    }
}
