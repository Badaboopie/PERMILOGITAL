package permilogital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Blueprint
public abstract class DormResidents {
    private String studentNumber;
    private String permitType;
    private String formattedDate;
    private String roomNumber;
    // private String permitStudentNumber;

    //FOR FILE NAME
    private String currentdayfilename = "permits-" + LocalDate.now().toString() + ".csv";

    // Load permit data from CSV file and display
    List<String[]> permits = readPermitDataFromCSV(currentdayfilename);


    // Load resident data from CSV file and display
    List<String[]> residents = readResidentDataFromCSV("residents.csv"); //CHANGE FILEPATH TO RESIDENTS.CSV


    //InputDATA
    public abstract void inputData(Scanner scan);


    //GETTERS
    public String getCurrentDate(){
        return formattedDate;
    }

    public String getStudentNumber(){
        return studentNumber;
    }

    public String getpermitType(){
        return permitType;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    //SETTERS
    public void setCurrentDate(String value){
        formattedDate = value;
    }

    public void setStudentNumber(String value){
        studentNumber = value;
    }

    public void setpermitType(String value){
        permitType = value;
    }

    public void setRoomNumber(String value){
        roomNumber = value;
    }

    //READ DATA FROM CSV
    public List<String[]> readResidentDataFromCSV(String filePath) {
        List<String[]> residents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                residents.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return residents;
    }

    //GETS data from CSV
    public List<String[]> readPermitDataFromCSV(String filePath) {
        List<String[]> permits = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                permits.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return permits;
    }
}
