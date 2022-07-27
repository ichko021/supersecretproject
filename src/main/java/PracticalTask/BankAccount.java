package PracticalTask;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class BankAccount {

    private String bankName;
    private Iban iban;
    private double balance;

    public BankAccount(String bankName){
        setBankName(bankName);
        setIBAN(Iban.random(CountryCode.BG));
        setBalance(0);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Iban getIBAN() {
        return iban;
    }

    public void setIBAN(Iban iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance<0){
            System.out.println("Insufficient balance.");
        }else{
            this.balance = balance;
        }

    }

    public void top_up(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add funds to your account:");
        double add_money = scanner.nextDouble();
        while(add_money < 0){
            System.out.println("Insufficient balance.");
            System.out.println("Add funds to your account:");
            add_money = scanner.nextDouble();
        }
        balance = balance + add_money;
        System.out.println("You have successfully added "+add_money+" BGN. Your balance is "+balance+" BGN.");
    }

    public void bankOperations(int method, MyAccount myAccount) throws IOException {
        //pay and refund transaction.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter phone number of the contact: ");
        String des_num = scanner.next();
        while (!(des_num.matches("[\\d]+"))) {
            System.out.println("Phone number can consist only of numbers.");
            System.out.println("Enter phone number of the contact: ");
            des_num = scanner.next();
        }
        System.out.println("Enter sum: ");
        double des_balance = scanner.nextDouble();
        while (des_balance <= 0) {
            System.out.println("Insufficient sum");
            System.out.println("Enter sum: ");
            des_balance = scanner.nextDouble();
        }

        for (Contact c : myAccount.getContacts()) {
            double get_user_balance = myAccount.getBankAccount().getBalance();
            double get_contact_balance = c.getBankAccount().getBalance();

            if (c.getPhone().equals(des_num)) {
                if (method == 1) {
                    try {
                        if(des_balance>get_user_balance){
                            System.out.println("Cannot perform transaction. The funds in your bank account are not enough.");
                        }else {
                            myAccount.getBankAccount().setBalance(get_user_balance - des_balance);
                            c.getBankAccount().setBalance(get_contact_balance + des_balance);
                            System.out.println("The transaction was successful.");
                            String payment_statement = myAccount.getFirstName() + " paid " + des_balance + " BGN to " + c.getPhone() + "\n";
                            myAccount.getTransactions().add(payment_statement);
                        }
                    }catch(Exception e){
                        System.out.println("There is a problem. Try later.");
                    }
                } else {
                    try {
                        if(des_balance>get_contact_balance){
                            System.out.println("Cannot perform transaction. The funds in the contact's bank account are not enough.");
                        }else {
                            myAccount.getBankAccount().setBalance(get_user_balance + des_balance);
                            c.getBankAccount().setBalance(get_contact_balance - des_balance);
                            System.out.println("The transaction was successful.");
                            String refund_statement = myAccount.getFirstName() + " refunded " + des_balance + " BGN from " + c.getPhone() + "\n";
                            myAccount.getTransactions().add(refund_statement);
                        }
                    }catch(Exception e){
                        System.out.println("There is a problem. Try later.");
                    }
                }
            }
        }
    }
}
