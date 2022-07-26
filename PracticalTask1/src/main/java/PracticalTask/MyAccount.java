package PracticalTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyAccount {

    private String firstName;
    private String lastName;
    private String phone;
    private static BankAccount bankAccount;
    private static List<String> transactions;
    private static List<Contact> contacts;

    public MyAccount(String firstName, String lastName, String phone, BankAccount bankAccount){
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setBankAccount(bankAccount);
        setContacts(new ArrayList<Contact>());
        setTransactions(new ArrayList<String>());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public static List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    public static List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public static void ViewTransHistory() throws IOException {
        MyAccount.getTransactions().forEach(System.out::println);
    }
}
