package assignment2;
import javax.swing.JFrame;
class CashPayment extends PaymentMethod {
    JFrame parentFrame;
    CashPayment(JFrame parentFrame, double amount){
        this.paymentChannel = "CASH";
        this.amount = amount;
        this.parentFrame = parentFrame;
    }
    @Override
    void processPayment(double amount){
        printReceipt(parentFrame);
    }
}