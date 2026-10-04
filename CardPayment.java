package assignment2;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
class CardPayment extends PaymentMethod {
    JFrame parentFrame;
    CardPayment(JFrame parentFrame, double amount){
        this.paymentChannel = "CARD";
        this.amount = amount;
        this.parentFrame = parentFrame;
    }
    @Override
    void processPayment(double amount){
        printReceipt(parentFrame);
    }
}