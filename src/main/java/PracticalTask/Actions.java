package PracticalTask;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.System.exit;


public class Actions {
    Scanner scanner = new Scanner(System.in);
    private FileWriter transFile;
    public MyAccount usrMyAcc;


    public void StartUp() {

        //account creation
        System.out.println("Enter first name: ");
        String fname = scanner.next();
        while (!(fname.matches("[a-zA-Z]+"))) {
            System.out.println("First name can consist only of letters.");
            System.out.println("Enter first name: ");
            fname = scanner.next();
        }
        System.out.println("Enter last name: ");
        String lname = scanner.next();
        while (!(lname.matches("[a-zA-Z]+"))) {
            System.out.println("Last name can consist only of letters.");
            System.out.println("Enter last name: ");
            lname = scanner.next();
        }
        System.out.println("Enter phone number: ");
        String pnumber = scanner.next();
        while (!(pnumber.matches("\\d+"))) {
            System.out.println("Phone number can consist only of numbers.");
            System.out.println("Enter phone number: ");
            pnumber = scanner.next();
        }

        //Bank account creation
        System.out.println("Enter the name of your bank: ");
        String bname = scanner.next();
        while (!(bname.matches("[a-zA-Z]+"))) {
            System.out.println("Bank name can consist only of letters.");
            System.out.println("Enter Bank name: ");
            bname = scanner.next();
        }

        //initializing objects
        usrMyAcc = new MyAccount(fname, lname, pnumber, new BankAccount(bname));

    }

    public void TransFile() {
        //create new file to write transaction history
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String formatDate = now.format(dtf);
        try {
            transFile = new FileWriter(usrMyAcc.getLastName() + "_" + formatDate + ".txt");
        } catch (Exception e) {
            System.out.println("Cannot create file.");
            throw new RuntimeException(e);
        }
    }


    public void ExitAPP() throws IOException {
        //Saves .txt file and exits.
        TransFile();
        for(String transaction: usrMyAcc.getTransactions()){
            transFile.write(transaction);
        }
        transFile.flush();
        transFile.close();
        scanner.close();
        exit(0);
    }



}
