package src;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        while (!exit) {
            // Display the menu
            System.out.println("==================================================================");
            System.out.println("University of Westminster - Student Activity Management System");
            System.out.println("==================================================================");
            System.out.println("1. Check available seats");
            System.out.println("2. Student Management");
            System.out.println("3. Manage student results");
            System.out.println("4. Generate reports");
            System.out.println("5. Exit");
            System.out.println("==================================================================");

            System.out.print("Please Enter Your Choice: ");
            int choice = input.nextInt();
            input.nextLine(); 
            
            switch (choice) {
                case 1:
                    int availableSeats = StudentRegistration.getAvailableSeatsCount();
                    System.out.println("Available Seats: " + availableSeats);
                    break;
                case 2:
                    StudentRegistration.main(args);
                    break;
                case 3:
                    System.out.println("Manage student results");
                    // StudentMangement.Marks.Marks(args);
                    break;
                case 4:
                    System.out.println("Generate reports");
                    // Add code to generate reports
                    break;
                case 5:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        input.close();
    }
}
