package src;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    private static final int MAX_CAPACITY = 100; // used private access modifier to make it accessible only within the class
    private static Student[] students = new Student[MAX_CAPACITY];
    private static int studentCount = 0;

    public static void main(String[] args) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
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
            System.out.println("8. Exit");
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
                    students[studentCount++] = registerStudent(input);
                    break;
                case 3:
                    System.out.println("Remove student");
                    deleteStudent(students, studentCount);
                    break;
                case 4:
                    System.out.println("Find student");
                    findStudentById(students, studentCount);
                    break;
                case 5:
                    System.out.println("Save student to file");
                    saveToFile(students, studentCount);
                    break;
                case 6:
                    System.out.println("Load data from file");
                    studentCount = loadFromFile(students);
                    break;
                case 7:
                    System.out.println("Display All Students");
                    displayStudents(students);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    // make the exit variable true to exit the loop
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        input.close();
    }

    //function to get available seats
    private static int getAvailableSeatsCount() {
        return MAX_CAPACITY - studentCount;//calculate the available seats
    }

    //function to register student
    private static Student registerStudent(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        // check for duplicate ID
        if (isDuplicateId(id)) {
            System.out.println("Error: Student is already registered or Please enter a unique ID.");
            return null;
        }
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Student registered successfully.");
        return new Student(name, id, email); 
    }
    
    //check weather the id is duplicate or not
    private static boolean isDuplicateId(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    //function to delete student
    private static void deleteStudent(Student[] students, int studentCount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            // check if the student id is available in the array
            if (students[i] != null && students[i].getId().equals(id)) {
                students[i] = null;
                System.out.println("Student with ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    //function to find student by id
    private static void findStudentById(Student[] students, int studentCount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        boolean found = false;
        //loop through the array to find the student and check if the student id is available in the array
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                System.out.println("Student found:");
                System.out.println("-------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s |\n", "Name", "ID", "Email");
                System.out.println("-------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s |\n", students[i].getName(), students[i].getId(), students[i].getEmail());
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
    private static void displayStudents(Student[] students) {

        System.out.println("\nRegistered Students:");
        System.out.println("-------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-20s |\n", "Name", "ID", "Email");
        System.out.println("-------------------------------------------------");
        //loop through the array to display all the students
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.printf("| %-10s | %-10s | %-20s |\n", students[i].getName(), students[i].getId(), students[i].getEmail());
            }
        }
        System.out.println("-------------------------------------------------");
    }

    //function to save students to file 
    private static void saveToFile(Student[] students, int studentCount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);//create a buffered writer object

            for (int i = 0; i < studentCount; i++) {
                if (students[i] != null) {
                    String studentData = students[i].getName() + "," + students[i].getId() + "," + students[i].getEmail() + ",";//store the student data in a string
                    bufferedWriter.write(studentData);//write the student data to the file
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
            System.out.println("Students saved to file successfully.");
        } catch (IOException e) {//catch the exception if there is an error saving the students to file
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    //function to load students from file
    private static int loadFromFile(Student[] students) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();

        int studentCount = 0;
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            //loop through the file to read the student data
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] studentData = line.split(",");
                String name = studentData[0];
                String id = studentData[1];
                String email = studentData[2];
                students[studentCount] = new Student(name, id, email);//create a new student object
                studentCount++;//increment the student count
            }

            fileScanner.close();
            System.out.println("Students loaded from file successfully.");

        } catch (FileNotFoundException e) {//catch the exception if the file is not found
            System.out.println("Error loading students from file: " + e.getMessage());
        }

        return studentCount;
    }
}

