//KATE CAPADOCIA
//GABRIEL DOMINIC DICAR
//JOHN ROMSON ERAZO
//EARL PABUA

package permilogital;

import java.util.Scanner;

public class Main {

    private static void startDisplay(){
        System.out.println("\n\n=====================================");
        System.out.println("=                                   =");
		System.out.println("=      P E R M I L O G I T A L      =");
		System.out.println("=                                   =");
        System.out.println("=====================================");
        System.out.println("1. Student\n2. Dorm Manager\n3. Exit\n");
        System.out.print("Enter here: ");
    }
    

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String user_input;
        Userselection select = new Userselection();

        do {
            startDisplay();
            user_input = scan.next().toLowerCase();
            select.userChoice(user_input, scan);  
        } while (user_input.equals("exit") != true);
    }
}