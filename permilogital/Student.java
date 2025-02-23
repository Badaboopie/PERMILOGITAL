package permilogital;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;


public class Student extends DormResidents{
    private Date currentDate;
    private SimpleDateFormat dateFormat;
    private String permitname;

    //Storing input data 
    public void inputData(Scanner scan){
        
        Calendar calendar = GregorianCalendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        currentDate = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //STORING THE TIME THE PERMIT WAS FILED//
        calendar.setTime(currentDate);

        try{
            if (5 < time && time < 18){
                //Name
                residentNameChecker(scan);      
                
                //ERROR CATCHING/tRY CATCH for invalid permit names
                permitNameChecker(scan);                 //Permit Type

                //current time
                setCurrentDate(dateFormat.format(currentDate));
            }
            else { 
                throw new Exception();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Student Number not found. Please try again");
        }
        catch(InputMismatchException e){
            System.out.println("This student has already filed a permit... ");
        }
        catch(Exception e) {
            System.out.println("Filing of permits is not allowed at this time.\n");
        }
    }


    //CHECK NAMING NAME ASSOCIATED WITH THE STUDENT NUMBER
    private void residentNameChecker(Scanner scan){
        System.out.println("Enter your student number: ");
        String studentNumber = scan.nextLine();

        boolean counter = false;

        for (String[] permit : permits) {
            if (permit[0].equals(studentNumber)) {
                throw new InputMismatchException();
            }
        }

        //CHECKING FOR VALID STUDENT NUMBERS OR IF THEY ARE ALREADY IN THE LIST
        for (String[] resident : residents) {
            if (studentNumber.equals(resident[0].toString()) ){
                System.out.println(String.format("\nName: %-30s", resident[2]));
                System.out.println(String.format("Room Number: %-3s\n", resident[1]));

                setRoomNumber(resident[1]);//SET ROOMNUMBER

                counter = true;
            }
        }

        if (counter){
            setStudentNumber(studentNumber); 
        }else{
            throw new IllegalArgumentException();
        }
    
    }

    //TRY CATCH for name
    private void permitNameChecker(Scanner scan){
        System.out.println("Enter the permit type: ");
        try {
            permitname = scan.nextLine().toLowerCase();

            if (permitname.equals("overnight permit") || permitname.equals("late permit")||permitname.equals("weekend permit")) {
                System.out.println("\nValid ... Permit Stored...\n");
                setpermitType(permitname);
            }
            else if (permitname.equals("op") || permitname.equals("lp")||permitname.equals("wp")) {
                System.out.println("\nValid .. Permit Stored...\n");

                //Will transform op/lp/wp into overnight permit, late permit, etc. ...
                if(permitname.equals("op")){
                    setpermitType("overnight permit");
                }

                if (permitname.equals("lp")){
                    setpermitType("late permit");
                }
                
                if (permitname.equals("wp")){
                    setpermitType("weekend permit");
                }

            }
            else if (permitname.equals("overnight") || permitname.equals("late")||permitname.equals("weekend")) {
                System.out.println("\nValid .. Permit Stored...\n");

                //Will transform op/lp/wp into overnight permit, late permit, etc. ...
                if(permitname.equals("overnight")){
                    setpermitType("overnight permit");
                }

                if (permitname.equals("late")){
                    setpermitType("late permit");
                }
                
                if (permitname.equals("weekend")){
                    setpermitType("weekend permit");
                }

            }else{
                throw new Exception();
            }  
            
        } catch (Exception e) {
            System.out.println("Please enter a valid keyword: overnight permit / late permit / weekend permit");

            //Loop again if invalid input
            permitNameChecker(scan);
        }
    }
}
