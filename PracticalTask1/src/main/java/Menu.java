import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    public static boolean goToMenu(){
        String answer;
        do{
            System.out.println("Do you want to go to the menu?(yes/no)");
            answer = scanner.next();
        }while(!(answer.equals("yes")));

        return true;
    }
    public static void main(String[] args) {
        Actions.StartUp();
        Actions.AddContacts();

        String[] options = {"1- Pay to contact",
                "2- Refund from contact",
                "3- View Transaction History",
                "4- Exit",
        };
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 4){
            printMenu(options);
            try{
                option = scanner.nextInt();
                switch (option){
                    case 1:
                        Actions.PayToContact();
                        break;
                    case 2:
                        Actions.RefundFromContact();
                        break;
                    case 3:
                        Actions.ViewTransHistory();
                        if(goToMenu()) {
                            break;
                        }
                    case 4:
                        scanner.close();
                        Actions.ExitAPP();
                }
            }catch(InputMismatchException ex){
                System.out.println("Please enter an integer value between " +
                        "1 and "+options.length);
            }catch(Exception ex){
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
    }
}
