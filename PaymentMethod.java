package assignment2;

import java.awt.Component;
import javax.swing.*;
import java.awt.Font;

abstract class PaymentMethod{
    String paymentChannel;
    double amount;

    private String generateReceipt(){
        StringBuilder receipt = new StringBuilder();
        receipt.append("=========================================\n");
        receipt.append("Overwatch Research Center\n");
        receipt.append("Room B48, Independence Hall\n");
        receipt.append("University of Ibadan, Ibadan\n");
        receipt.append("(234) 916-512-6149\n");
        receipt.append("=========================================\n");
        receipt.append(String.format("%-20s %-10s\n", "Item", "Price"));
        receipt.append(String.format("%-20s : ₦%,.2f\n", "1. Research Journals", amount));
        receipt.append("-----------------------------------------\n");
        receipt.append(String.format("%-20s : ₦%,.2f\n", "Subtotal", amount));
        receipt.append(String.format("%-20s : ₦%,.2f\n", "VAT (7.5)", 0.075*amount));
        receipt.append("=========================================\n");
        receipt.append(String.format("%-20s : ₦%,.2f\n", "Total", amount+(0.075*amount)));
        receipt.append("=========================================\n");
        receipt.append(String.format("%-20s : %-10s\n", "TRANSACTION TYPE", paymentChannel));
        receipt.append("-----------------------------------------\n");
        receipt.append(String.format("%-20s : %-10s\n", "TRANSACTION STATUS", "SUCCESSFUL"));
        receipt.append("=========================================\n");
        receipt.append("Thank you for Visiting\n");
        receipt.append("Overwatch Research Center\n");
        receipt.append("=========================================\n");

        return receipt.toString();
        
    }
    public String generatePaymentDetails(){
        StringBuilder paymentDetailsFormat = new StringBuilder();
        String item = "Research Journal";
        paymentDetailsFormat.append(String.format("%-20s : %10s\n", "You are About to Pay For", item));
        paymentDetailsFormat.append(String.format("%-10s : %,.2f\n", "The Cost of this item is", amount));
        paymentDetailsFormat.append(String.format("%-10s : %s\n", "The Selected Payment Channel is", paymentChannel));
        paymentDetailsFormat.append("Do you want to proceed with the payment?");

        return paymentDetailsFormat.toString();
    }
    public void printReceipt(JFrame parentFrame){
        JTextArea receiptContent = new JTextArea(generateReceipt());
        receiptContent.setEditable(false);

        JDialog receiptArea = new JDialog(parentFrame, "Payment Receipt", true);
        receiptArea.add(new JScrollPane(receiptContent));
        receiptContent.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        receiptArea.pack();
        receiptArea.setLocationRelativeTo(parentFrame);
        receiptArea.setVisible(true);
    }
    abstract void processPayment(double amount);
}
