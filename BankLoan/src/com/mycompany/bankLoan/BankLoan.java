package com.mycompany.bankLoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class BankLoan extends JPanel {
    private JLabel nameLabel, accountTypeLabel, amountLabel, durationLabel, interestRateLabel, monthlyDueLabel,
            totalPaymentLabel;
    private JTextField nameField, accountTypeField, amountField, durationField, interestRateField, monthlyDueField,
            totalPaymentField;
    private JTextArea loanInfoField;
    private JButton applyButton, calculateButton;

    public BankLoan() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(10, 10, 10, 10);

        nameLabel = new JLabel("Name:");
        accountTypeLabel = new JLabel("Account Type:");
        amountLabel = new JLabel("Loan Amount:");
        durationLabel = new JLabel("Loan Duration (in months):");
        interestRateLabel = new JLabel("Interest Rate (%):");
        monthlyDueLabel = new JLabel("Monthly Due:");
        totalPaymentLabel = new JLabel("Total Payment:");

        nameField = new JTextField(10);
        accountTypeField = new JTextField(10);
        amountField = new JTextField(10);
        durationField = new JTextField(10);
        interestRateField = new JTextField(10);
        monthlyDueField = new JTextField(10);
        monthlyDueField.setEditable(false);
        totalPaymentField = new JTextField(10);
        totalPaymentField.setEditable(false);
        loanInfoField = new JTextArea(5, 20);
        loanInfoField.setLineWrap(true);
        loanInfoField.setWrapStyleWord(true);

        // applyButton = new JButton("Apply");
        calculateButton = new JButton("Calculate");

        add(nameLabel, constraints);
        constraints.gridx++;
        add(nameField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(accountTypeLabel, constraints);
        constraints.gridx++;
        add(accountTypeField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(amountLabel, constraints);
        constraints.gridx++;
        add(amountField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(durationLabel, constraints);
        constraints.gridx++;
        add(durationField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(interestRateLabel, constraints);
        constraints.gridx++;
        add(interestRateField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(monthlyDueLabel, constraints);
        constraints.gridx++;
        add(monthlyDueField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(totalPaymentLabel, constraints);
        constraints.gridx++;
        add(totalPaymentField, constraints);
        // constraints.gridx = 0;
        // constraints.gridy++;
        // constraints.gridwidth = 2;
        // add(applyButton, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(calculateButton, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        add(loanInfoField, constraints);

        // applyButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // TODO: implement loan application logic
        // }
        // });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // calculate monthly due and total payment
                double loanAmount = Double.parseDouble(amountField.getText());
                int loanDuration = Integer.parseInt(durationField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText());

                // calculate effective monthly interest rate
                double effectiveInterestRate = Math.pow(1 + (interestRate / 100) / 12, 12) - 1;
                double monthlyInterestRate = effectiveInterestRate / 12;

                double numerator = monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanDuration);
                double denominator = Math.pow(1 + monthlyInterestRate, loanDuration) - 1;
                double monthlyDue = loanAmount * (numerator / denominator);
                double totalPayment = monthlyDue * loanDuration;

                // create string to display loan information
                String loanInfo = "Name: " + nameField.getText() + "\n";
                loanInfo += "Account Type: Savings Account\n" + "\n";
                loanInfo += "Loan Amount: $" + String.format("%.2f", loanAmount) + "\n";
                loanInfo += "Interest Rate: " + String.format("%.2f", interestRate) + "%\n";
                loanInfo += "Loan Duration: " + loanDuration + " months\n";
                loanInfo += "Monthly Due: $" + String.format("%.2f", monthlyDue) + "\n";
                loanInfo += "Total Loan Payment: $" + String.format("%.2f", totalPayment) + "\n";

                // set text to display loan information
                loanInfoField.setText(loanInfo);
            }
        });
    }
}
