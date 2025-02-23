package permilogital;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

//user selects what action to perform
public class Userselection {

    public void userChoice(String user_input, Scanner scan){
        createNewFile();
        switch (user_input) {
            case "1":

                System.out.println("\nEnter Dormer Credentials\n");
                scan.nextLine();
                DormResidents student = new Student();

                
                student.inputData(scan);    //STORING NAME,ROOMNUM,PERMIT TYPE
                convertStringtoCSVFile(student);
                break;
                
            case "2":
                System.out.println("\nEntering manager selection options");

                DormResidents manager = new Manager();

                manager.inputData(scan);
                break;

            case "3":
                System.out.println("Terminating program...");
                System.exit(0);
                break;

            default:
                System.out.println("\nInvalid Input. Please try again.");
                break;
        }
    }

    private static void convertStringtoCSVFile(DormResidents student){
        //IF student variables are empty then they are not stored
                if (student.getCurrentDate() != null || student.getRoomNumber() != null || student.getStudentNumber() != null || student.getpermitType() != null) {
                   
                    //Convert student variables into String to store into csv file
                    String csvLine = student.getStudentNumber() + "," + student.getRoomNumber() + "," + student.getpermitType() + "," + student.getCurrentDate(); 
                    savePermitToFile(csvLine);   

                }else{
                    return;
                }
    }
    
    //SHOULD BE IN A DIFFERENT CLASS
    //Input into CSV

    private static void savePermitToFile(String csvLine) {
        String currentdayfilename = "permits-" + LocalDate.now().toString() + ".csv";
        try (FileWriter writer = new FileWriter(currentdayfilename, true)) {
            writer.write(csvLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CREATING NEW FILE 
    private static void createNewFile(){
        String currentdayfilename = "permits-" + LocalDate.now().toString() + ".csv";
        try {
            FileWriter writer = new FileWriter(currentdayfilename, true);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
