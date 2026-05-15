/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainhospitalapplication;

/**
 *
 * @author eebej
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Payment and Payment Management Interface.
 * Provides options for managing and tracking patient bills.
 */
public class PaymentUI extends JFrame {

    // UI Elements Declaration
    private JTextField patientIDField, patientNameField, totalAmountField, amountPaidField;
    private JComboBox<String> paymentMethodDropdown;
    private JButton generateInvoiceButton, clearFormButton, completePaymentButton, exitAppButton;

    // Payment Method Options
    private final String[] paymentOptions = {"Cash", "Credit Card", "Debit Card", "Insurance"};

    /**
     * Initializes the Payment UI.
     */
    public PaymentUI() {
        setupBillingUI();
        initializeActionListeners();
    }

    /**
     * PaymentUI UI layout and components.
     */
    private void setupBillingUI() {
        // Window properties
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment System");
        setSize(600, 450);
        setLocationRelativeTo(null); 

        // Main Panel Configuration
        JPanel mainContainer = new JPanel(new BorderLayout(15, 15));
        mainContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Section for Form Inputs
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add fields to form
        addLabelAndField(formPanel, gbc, "Patient ID:", patientIDField = new JTextField(20), 0);
        addLabelAndField(formPanel, gbc, "Patient Name:", patientNameField = new JTextField(25), 1);
        addLabelAndField(formPanel, gbc, "Total Amount:", totalAmountField = new JTextField(15), 2);
        addLabelAndField(formPanel, gbc, "Amount Paid:", amountPaidField = new JTextField(15), 3);
        addLabelAndDropdown(formPanel, gbc, "Payment Method:", paymentMethodDropdown = new JComboBox<>(paymentOptions), 4);

        // Section for Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(generateInvoiceButton = createButton("Generate Invoice"));
        buttonPanel.add(completePaymentButton = createButton("Complete Payment"));
        buttonPanel.add(clearFormButton = createButton("Clear"));
        buttonPanel.add(exitAppButton = createButton("Exit"));

        // Add all panels to the main container
        mainContainer.add(formPanel, BorderLayout.CENTER);
        mainContainer.add(buttonPanel, BorderLayout.SOUTH);

        // Add main container to frame
        add(mainContainer);
    }

    /**
     * Initializes event listeners for button actions.
     */
    private void initializeActionListeners() {
        // Generate Invoice
        generateInvoiceButton.addActionListener(e -> generateInvoice());

        // Complete Payment
        completePaymentButton.addActionListener(e -> processPayment());

        // Clear Form
        clearFormButton.addActionListener(e -> resetForm());

        // Exit Application
        exitAppButton.addActionListener(e -> {
            int confirmExit = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
            );
            if (confirmExit == JOptionPane.YES_OPTION) {
                dispose(); 
            }
        });
    }

    /**
     * Generates an invoice based on user input.
     */
    private void generateInvoice() {
        String patientID = patientIDField.getText().trim();
        String patientName = patientNameField.getText().trim();
        String totalAmount = totalAmountField.getText().trim();

        if (isAnyFieldEmpty(patientID, patientName, totalAmount)) {
            showMessage("Please complete all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        showMessage(
            "Invoice Generated Successfully!\n\nPatient ID: " + patientID +
                "\nPatient Name: " + patientName +
                "\nTotal Amount: €" + totalAmount,
            "Invoice Generated",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Processes the payment and checks if it covers the total amount.
     */
    private void processPayment() {
        String amountPaid = amountPaidField.getText().trim();
        String totalAmount = totalAmountField.getText().trim();
        String paymentMethod = (String) paymentMethodDropdown.getSelectedItem();

        if (isAnyFieldEmpty(amountPaid, totalAmount) || paymentMethod == null) {
            showMessage("Please complete all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double total = Double.parseDouble(totalAmount);
            double paid = Double.parseDouble(amountPaid);

            if (paid < total) {
                showMessage("Insufficient payment. Payment is not complete.", "Payment Error", JOptionPane.WARNING_MESSAGE);
            } else {
                showMessage(
                    "Payment Completed Successfully!\n\nPayment Method: " + paymentMethod +
                        "\nAmount Paid: €" + paid,
                    "Payment Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            showMessage("Please enter valid numerical values for amounts.", "Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Resets all fields to their default values.
     */
    private void resetForm() {
        patientIDField.setText("");
        patientNameField.setText("");
        totalAmountField.setText("");
        amountPaidField.setText("");
        paymentMethodDropdown.setSelectedIndex(0);
    }

    /**
     * Checks if any field in the provided arguments is empty.
     *
     * @param fields Variable number of field values.
     * @return True if any field is empty, false otherwise.
     */
    private boolean isAnyFieldEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * message dialog.
     *
     * @param message The message to display.
     * @param title   The dialog title.
     * @param type    The message type (e.g., ERROR_MESSAGE, INFORMATION_MESSAGE).
     */
    private void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }

    /**
     * Adds a label and text field to the form panel.
     */
    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    /**
     * Create label and dropdown menu to the form panel.
     */
    private void addLabelAndDropdown(JPanel panel, GridBagConstraints gbc, String labelText, JComboBox<String> comboBox, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(comboBox, gbc);
    }

    /**
     * Creates a styled button.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(160, 30));
        return button;
    }
}
