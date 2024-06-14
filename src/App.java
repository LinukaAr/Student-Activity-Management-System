package src;
import java.util.*;
import java.io.*;

public class App {
    private static final int MAX_CAPACITY = 100;
    private static String[] studentNames = new String[MAX_CAPACITY];
    private static String[] studentIds = new String[MAX_CAPACITY];
    private static String[] studentEmails = new String[MAX_CAPACITY];
    private static int studentCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        initialise(studentNames, studentIds, studentEmails);
        boolean exit = false;
        
        //used while loop to keep the program running until the user chooses to exit
        while (!exit) {
            // Display the main menu
            System.out.println("==================================================================");
            System.out.println("University of Westminster - Student Activity Management System");
            System.out.println("==================================================================");
            System.out.println("1. Check available seats");
            System.out.println("2. Register student(with ID)");
            System.out.println("3. Delete student");
            System.out.println("4. Find student(with student ID)");
            System.out.println("5. Save student details to a file");
            System.out.println("6. Load details from file");
            System.out.println("7. Display All Students");
            System.out.println("8. Manage Student Results");
            System.out.println("9. Exit");
            System.out.println("==================================================================");

            System.out.print("Please Enter Your Choice: ");
            int choice = input.nextInt();
            input.nextLine();

            //used switch statement 
            switch (choice) {
                case 1:
                    int availableSeats = getAvailableSeatsCount();
                    System.out.println("Available Seats: " + availableSeats);
                    break;
                case 2:
                    System.out.println("Student Registration");
                    registerStudent(input);
                    break;
                case 3:
                    System.out.println("Remove student");
                    deleteStudent(input);
                    break;
                case 4:
                    System.out.println("Find student");
                    findStudentById(input);
                    break;
                case 5:
                    System.out.println("Save student to file");
                    saveToFile(input);
                    break;
                case 6:
                    System.out.println("Load data from file");
                    loadFromFile(input);
                    break;
                case 7:
                    System.out.println("Display All Students");
                    displayStudents();
                    break;
                case 8:
                    System.out.println("Manage Student Results");
                    //manageStudentResults(input);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    exit = true; // make the exit variable true to exit the loop
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        input.close();
    }

    private static void initialise(String[] names, String[] ids, String[] emails) {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            names[i] = "";
            ids[i] = "";
            emails[i] = "";
        }
    }

    //function to get available seats
    private static int getAvailableSeatsCount() {
        return MAX_CAPACITY - studentCount; //calculate the available seats
    }
    
    //function to register student
    private static void registerStudent(Scanner scanner) {
        if (studentCount >= MAX_CAPACITY) {
            System.out.println("No available seats.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        String id = "";
        boolean validId = false;
        //loop until user entered the correct version of ID
        while (!validId) {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();
            
            if (id.length() != 8) {
                System.out.println("Error: ID should be 8 characters long.");
            } else if (isDuplicateId(id)) {
                System.out.println("Error: Student is already registered with this ID.");
            } else {
                validId = true;
            }
        }
        
        System.out.println("Student ID is valid.");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        studentNames[studentCount] = name;
        studentIds[studentCount] = id;
        studentEmails[studentCount] = email;
        studentCount++;
        
        System.out.println("Student registered successfully.");
    }

    //check whether the id is duplicate or not
    private static boolean isDuplicateId(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (id.equals(studentIds[i])) {
                return true;
            }
        }
        return false;
    }

    //function to delete student
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            // check if the student id is available in the array
            if (studentIds[i] != null && studentIds[i].equals(id)) {
                // Shift all elements to the left to fill the gap
                for (int j = i; j < studentCount - 1; j++) {
                    studentNames[j] = studentNames[j + 1];
                    studentIds[j] = studentIds[j + 1];
                    studentEmails[j] = studentEmails[j + 1];
                }
                studentNames[studentCount - 1] = null;
                studentIds[studentCount - 1] = null;
                studentEmails[studentCount - 1] = null;
                studentCount--;
                System.out.println("Student with ID " + id + " removed successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    //function to find student by id
    private static void findStudentById(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        boolean found = false;
        //loop through the array to find the student and check if the student id is available in the array
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i] != null && studentIds[i].equals(id)) {
                System.out.println("Student found:");
                System.out.println("-------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s |\n", "Name", "ID", "Email");
                System.out.println("-------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s |\n", studentNames[i], studentIds[i], studentEmails[i]);
                System.out.println("-------------------------------------------------");
                found = true;
                break;
            }
        }
        //if student not found
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    //function to display all students
    private static void displayStudents() {
        System.out.println("\nRegistered Students:");
        System.out.println("-------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-20s |\n", "Name", "ID", "Email");
        System.out.println("-------------------------------------------------");
        //loop through the array to display all the students
        for (int i = 0; i < studentCount; i++) {
            if (studentNames[i] != null && studentIds[i] != null && studentEmails[i] != null) {
                System.out.printf("| %-10s | %-10s | %-20s |\n", studentNames[i], studentIds[i], studentEmails[i]);
            }
        }
        System.out.println("-------------------------------------------------");
    }

    //function to save students to file 
    private static void saveToFile(Scanner scanner) {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
    
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < studentCount; i++) {
                if (studentNames[i] != null && studentIds[i] != null && studentEmails[i] != null) {
                    String studentData = studentNames[i] + "," + studentIds[i] + "," + studentEmails[i];
                    bufferedWriter.write(studentData);
                    bufferedWriter.newLine();
                }
            }
            System.out.println("Students saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }
    
    //function to load students from file
    private static void loadFromFile(Scanner scanner) {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        studentCount = 0;
    
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            //loop through the file to read the student data
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] studentData = line.split(",");
                if (studentData.length == 3) {
                    studentNames[studentCount] = studentData[0];
                    studentIds[studentCount] = studentData[1];
                    studentEmails[studentCount] = studentData[2];
                    studentCount++;
                }
            }
            System.out.println("Students loaded from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
    }
}
}