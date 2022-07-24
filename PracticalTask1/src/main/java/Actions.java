import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static java.lang.System.exit;


public class Actions {
    private static Scanner scanner = new Scanner(System.in);
    private static FileWriter transFile;
    private static myAccount usrMyAcc;
    private static int contact_num;
    private static List<Contact> contacts = new ArrayList<Contact>();
    private static bankAccount c_bAcc = new bankAccount("Example", Iban.random(CountryCode.BG), 0);
    private static List<String> transactions = new ArrayList<String>();


    public static void StartUp() {
        System.out.println("Enter first name: ");
        String fname = scanner.next();
        System.out.println("Enter second name: ");
        String lname = scanner.next();
        System.out.println("Enter phone number: ");
        int pnumber = scanner.nextInt();

        System.out.println("Enter the name of your bank: ");
        String bname = scanner.next();
        System.out.println("Generating an IBAN...");
        Iban iban = Iban.random(CountryCode.BG);
        System.out.println("Here is your IBAN: " + iban);
        double balance = 0;

        bankAccount bankAcc = new bankAccount(bname, iban, balance);
        usrMyAcc = new myAccount(fname, lname, pnumber, bankAcc);

        System.out.println("Add funds to your bank account: ");
        double add_funds = scanner.nextDouble();
        bankAcc.top_up(add_funds);
        transFile();
    }

    public static void transFile() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH;mm;ss");
        LocalDateTime now = LocalDateTime.now();
        String formatDate = now.format(dtf);
        try {
            transFile = new FileWriter(usrMyAcc.getLastName() + "_" + formatDate + ".txt");
        } catch (Exception e) {
            System.out.println("Cannot create file.");
            throw new RuntimeException(e);
        }
    }

    public static void AddContacts() {
        System.out.println("How many contacts do you want to add? ");
        contact_num = scanner.nextInt();
        try {
            for (int i = 0; i < contact_num; i++) {
                System.out.println("Enter first name: ");
                String c_fname = scanner.next();
                System.out.println("Enter second name: ");
                String c_lname = scanner.next();
                System.out.println("Enter phone number: ");
                int c_pnumber = scanner.nextInt();
                Contact temp_contact = new Contact(c_fname, c_lname, c_pnumber, c_bAcc);
                contacts.add(temp_contact);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error");
        }


        System.out.println("Contact list is complete.");
    }


    public static boolean PayToContact() throws IOException {
        System.out.println("Enter the number of the contact: ");
        int des_num = scanner.nextInt();
        System.out.println("Enter the amount of money you wish to pay: ");
        double des_balance = scanner.nextDouble();

        for (Contact contact : contacts) {
            System.out.println("Checking if phone number exists...");
            if (contact.getPhone() == des_num) {
                System.out.println("Checking for sufficient availability...");
                if (usrMyAcc.getbAcc().getBalance() >= des_balance) {

                    usrMyAcc.getbAcc().setBalance(usrMyAcc.getbAcc().getBalance() - des_balance);
                    contact.getbAcc().setBalance(contact.getbAcc().getBalance() + des_balance);

                    System.out.println("Transaction is complete.");
                    String writeTrans = ("Pay transaction to " + contact.getPhone() + ", amount is equal to " + des_balance + ".\n");
                    transactions.add(writeTrans);
                    transFile.write(writeTrans);
                    return true;
                } else {
                    System.out.println("Insufficient balance.");
                    return false;
                }
            }
        }
        System.out.println("There is no such phone number.");
        return false;

    }

    public static void closeFile() {

    }

    public static boolean RefundFromContact() throws IOException {
        System.out.println("Enter the number of the contact: ");
        int des_num = scanner.nextInt();
        System.out.println("Enter the amount of money you wish to refund: ");
        double des_balance = scanner.nextDouble();

        for (Contact contact : contacts) {
            System.out.println("Checking if phone number exists...");
            if (contact.getPhone() == des_num) {
                System.out.println("Checking for sufficient availability...");
                if (contact.getbAcc().getBalance() >= des_balance) {

                    contact.getbAcc().setBalance(contact.getbAcc().getBalance() - des_balance);
                    usrMyAcc.getbAcc().setBalance(usrMyAcc.getbAcc().getBalance() + des_balance);

                    System.out.println("Transaction is complete.");
                    String writeTrans = ("Refund transaction to " + contact.getPhone() + ", amount is equal to " + des_balance + ".\n");
                    transactions.add(writeTrans);
                    transFile.write(writeTrans);
                    return true;
                } else {
                    System.out.println("Insufficient balance.");
                    return false;
                }
            }
        }
        System.out.println("There is no such phone number.");
        return false;
    }

    public static void ExitAPP() throws IOException {
        transFile.close();
        exit(0);
    }

    public static void ViewTransHistory() throws IOException {
            transactions.forEach(System.out::print);
    }

}
