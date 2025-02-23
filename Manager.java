package permilogital;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Manager extends DormResidents {
    // Manager login credentials
    private static final String MANAGER_PASSWORD = "password";

    // Manager selection options
    public void inputData(Scanner scan) {
        // Check login credentials

        scan.nextLine();
        login(scan);

        int choice;

        do{
        System.out.println("\nChoose an option:");
        System.out.println("1. List of Dormers");
        System.out.println("2. List of Those Who Filed a Permit");
        System.out.println("3. Give Gentle Reminder");
        System.out.println("4. Exit");

        System.out.println("\nEnter Here: ");
        choice = scan.nextInt();
        scan.nextLine();
        
            switch (choice) {
                case 1:
                    displayResidentList();
                    break;

                case 2:
                    displayPermitList();
                    break;

                case 3:
                try {
                    giveGentleReminder(scan);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Student Number not found.");
                    break;
                }

                case 4:
                    System.out.println("Exiting Manager Options...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }   
                
        }while(choice != 4);
    }

    private void login(Scanner scan) {
        System.out.print("Enter Password: ");
        String password = scan.nextLine();

        if (password.equals(MANAGER_PASSWORD) != true ) {
            System.out.println("\nLogin failed please try again...");
            login(scan);
        }else{
            System.out.println("Logging in...\n");
        }
    }

    private void giveGentleReminder(Scanner scan){
        System.out.print("\nEnter a Student Number: ");
        String studentNumber = scan.nextLine();

        for (String[] resident : residents) {
            if (studentNumber.equals(resident[0].toString()) ){
                System.out.print(String.format("Give Gentle Reminder to%s? (1 - Yes / 2 - No) ", resident[2]));
                int ans = scan.nextInt();
                switch (ans) {
                    case 1:
                        editRecord(studentNumber);
                        break;
                    case 2:
                    System.out.println("Okie");
                        break;
                    default:
                        System.out.println(("Invalid input. Please try again."));
                        break;
                }
            } else {
                System.out.print("\nStudent is not in the list of dormers\n");
                break;
            }
        }
    }    

        private void editRecord(String studentNumber){
        String tempFile = "temp.txt";
        File oldFile = new File("residents.csv");
        File newFile = new File(tempFile);
        String studentNum = ""; String age = ""; String name = ""; String gr = "";

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("residents.csv"));
            x.useDelimiter("[,\n]");

            while (x.hasNext()) {
                studentNum = x.next();
                age = x.next();
                name = x.next();
                gr = x.next();

                if(studentNum.equals(studentNumber)){
                    int newGR = Integer.parseInt(gr.trim()) + 1;
                    pw.print(studentNum + "," + age + "," + name + "," + String.valueOf(newGR) + "\n");
                    System.out.println("Gentle Reminder given.");
                }else{
                    pw.print(studentNum + "," + age + "," + name + "," + gr + "\n");
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("residents.csv");
            newFile.renameTo(dump);
        } catch (Exception e) {
            System.out.println("Student Number not found");
        }
    }
    
    //Display the RESIDENTS
    private void displayResidentList() {
        System.out.println("\nList of Dorm Residents:");
        for (String[] resident : residents) {
            System.out.println(String.format("Name: %-30s Room: %-10s Gentle Reminders: %-2s" , resident[2], resident[1], resident[3]));
        }
    }

    // DISPLAY PERMITS FILED
    private void displayPermitList() {
        String permitStudentNumber;
        String residentName = "";
        String residentRoomNumber = "";

        System.out.println("\nList of Those Who Filed a Permit:");
        System.out.println("\n Printing List... \n"); 
        System.out.println(String.format("%-9s\t  %-30s%-16s %-20s %-20s\t", "Student Number", "Name", "Room", "Permit Type", "Time"));

        for (String[] permit : permits) {
            
            permitStudentNumber = permit[0];
            for (String [] resident : residents) {
                if (permitStudentNumber.equals(resident[0])) {
                    residentRoomNumber = resident[1];
                    residentName = resident[2]; 
                    break;
                }                
            }

            System.out.println(String.format("%-9s\t %-30s %-16s %-20s %-20s\t", permit[0], residentName, residentRoomNumber, permit[2], permit[3]));
        }
    }
}
