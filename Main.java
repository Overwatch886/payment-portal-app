package assignment2;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.event.*;
class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame("Payment Portal");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Creating Payment Amount input box
        JLabel amountLabel = new JLabel("Enter Price");
        JTextField amountBox = new JTextField(20);

        // Creating input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(amountLabel);
        inputPanel.add(amountBox);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Creating Payment method selection panel
        JRadioButton cashButton = new JRadioButton("💸 CASH");
        cashButton.setActionCommand("cash");

        cashButton.setPreferredSize(new Dimension(120, 50));
        cashButton.setBorder(BorderFactory.createEtchedBorder());
        cashButton.setBorderPainted(true);
        JRadioButton cardButton = new JRadioButton("💳 CARD");
        cardButton.setActionCommand("card");
        cardButton.setPreferredSize(new Dimension(120, 50));
        cardButton.setBorder(BorderFactory.createEtchedBorder());
        cardButton.setBorderPainted(true);

        JPanel paymentMethodPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 10));
        paymentMethodPanel.add(cashButton);
        paymentMethodPanel.add(cardButton);

        //adding to ButtonGroup
        ButtonGroup paymentMethods = new ButtonGroup();
        paymentMethods.add(cashButton);
        paymentMethods.add(cardButton);
        frame.add(paymentMethodPanel, BorderLayout.CENTER);

        // Creating Pay button
        JButton payButton = new JButton("Pay");

        // Creating Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(payButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Adding event listener to the pay button
        payButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountString = amountBox.getText().trim();

            if (amountString.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter the Amount You Want To Pay", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (paymentMethods.getSelection() == null) {
                JOptionPane.showMessageDialog(frame, "Select A Payment Method", "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // Error handling
            double amount;
            try {
                amount = Double.parseDouble(amountString);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Payment Value. Please only enter numbers e.g 54000, 560.99", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount<=0||Double.isNaN(amount)||Double.isInfinite(amount)){
                JOptionPane.showMessageDialog(frame, "Invalid Payment Value. Please only enter numbers greater than zero", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }


            String userPayMethodChoice = paymentMethods.getSelection().getActionCommand();
            PaymentMethod paymentSession =userPayMethodChoice.equals("cash")
            ? new CashPayment(frame, amount)
            : new CardPayment(frame, amount);
            String paymentDetails = paymentSession.generatePaymentDetails();
            int response = JOptionPane.showConfirmDialog(frame, paymentDetails, "Confirm Payment Details", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response!=JOptionPane.YES_OPTION) return;
            paymentSession.processPayment(amount);

            }
        });
    }
}
