package PracticalTask;

import java.util.Scanner;

public class Contact {
    private String firstName;
    private String lastName;
    private String phone;
    private BankAccount bankAccount;

    public Contact(String firstName, String lastName, String phone, BankAccount bankAccount){
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setBankAccount();
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount() {
        this.bankAccount = new BankAccount("Example");
    }

    public static void AddContacts(MyAccount myAccount) {
        //adding contacts
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many contacts do you want to add? ");
        int contact_num = scanner.nextInt();

        try {
            for (int i = 0; i < contact_num; i++) {
                System.out.println("Enter first name: ");
                String c_fname = scanner.next();
                while (!(c_fname.matches("[a-zA-Z]+"))) {
                    System.out.println("First name can consist only of letters.");
                    System.out.println("Enter first name: ");
                    c_fname = scanner.next();
                }
                System.out.println("Enter last name: ");
                String c_lname = scanner.next();
                while (!(c_lname.matches("[a-zA-Z]+"))) {
                    System.out.println("Last name can consist only of letters.");
                    System.out.println("Enter last name: ");
                    c_lname = scanner.next();
                }
                System.out.println("Enter phone number: ");
                String c_pnumber = scanner.next();
                while (!(c_pnumber.matches("[\\d]+"))) {
                    System.out.println("Phone number can consist only of numbers.");
                    System.out.println("Enter phone number: ");
                    c_pnumber = scanner.next();
                }
                myAccount.getContacts().add(new Contact(c_fname, c_lname, c_pnumber, new BankAccount("Example")));
                System.out.println("Contact added.");
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }

        System.out.println("Contact list is complete.");

    }

}
