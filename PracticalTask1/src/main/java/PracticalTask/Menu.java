package PracticalTask;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static Scanner scanner = new Scanner(System.in);
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }

    public static boolean GoToMenu(){
        System.out.println("Do you want to go back to the menu?(yes/no)");
        String answer = scanner.next();
        while(!(answer.equals("yes"))){
            System.out.println("Do you want to go back to the menu?(yes/no)");
            answer = scanner.next();
        }
        return true;
    }

    public static void main(String[] args) {
        Actions.StartUp();
        Contact.AddContacts();

        String[] options = {"1- Top up account",
                "2- Pay to contact",
                "3- Refund from contact",
                "4- View Transaction History",
                "5- Exit",
        };
        int option = 1;
        while (option!=5){
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch(option){
                    case 1:
                        BankAccount.top_up(MyAccount.getBankAccount().getBalance());
                        break;
                    case 2:
                        BankAccount.bankOperations(1);
                        break;
                    case 3:
                        BankAccount.bankOperations(2);
                        break;
                    case 4:
                        MyAccount.ViewTransHistory();
                        if(GoToMenu()){
                            break;
                        }
                    case 5:
                        Actions.ExitAPP();
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
            catch (Exception ex){
                System.out.println("An unexpected error happened. Please try again");
                scanner.next();
            }
        }
    }
}
