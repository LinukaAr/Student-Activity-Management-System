package src;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException; 
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException; 



public class StudentRegistration {

    private static final int MAX_CAPACITY = 100;
    private static int studentCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[MAX_CAPACITY];
       

        
        initialise(students);
        //Printing the main menu
        while (true) {
            System.out.println("\n==================================================================");
            System.out.println("Student Mangement");
            System.out.println("1. Register Student");
            // System.out.println("Store");
            System.out.println("2. Find student");
            System.out.println("3. Remove student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save student to file");
            System.out.println("6. Load details from file");
            System.out.println("7. Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    if (studentCount < MAX_CAPACITY) {
                        students[studentCount] = registerStudent(scanner);
                        studentCount++;
                    } else {
                        System.out.println("Maximum capacity reached. Cannot register more students.");
                    }
                    break;
                case 2:
                    System.out.println("Find student by ID");
                    findStudentById(students, studentCount, null);
                    break;
                case 3:
                    System.out.println("Remove Student by ID");
                    deleteStudent(students, studentCount);
                    break;
                case 4:
                    displayStudents(students);
                    break;
                case 5:
                    System.out.println("5. Save student to file");
                    saveToFile(students, studentCount);
                    break;
                case 6:
                    System.out.println("Load data from file");
                    studentCount = loadFromFile(students);
                    break;
                
                case 7:
                    App.main(args);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    } 

    //Find the student by UoW ID
    private static void findStudentById(Student[] students, int studentCount, String id) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        id = scanner.nextLine();
        
        // Search for student by ID
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                System.out.println("Student found:");
                System.out.println("Student found:");
                System.out.println("--------------------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s | %-15s |\n", "Name", "ID", "Email", "Phone Number");
                System.out.println("--------------------------------------------------------------");
                System.out.printf("| %-10s | %-10s | %-20s | %-15s |\n", students[i].getName(), students[i].getId(), students[i].getEmail(), students[i].getPhoneNumber());
                System.out.println("--------------------------------------------------------------");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    //delete Student
    private static void deleteStudent(Student[] students , int studentCount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        // Search for student by ID and remove
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null && students[i].getId().equals(id)) {
                students[i] = null;
                System.out.println("Student with ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");

        // Search for student by ID
        findStudentById(students, studentCount, id);
    }

    private static void initialise(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            students[i] = null;
        }
        System.out.println("Initialized student array.");
    }

    //save students
    private static Student registerStudent(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        return new Student(name, id, email, phoneNumber);
    }

    //display students
    private static void displayStudents(Student[] students) {
        System.out.println("\nRegistered Students:");
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-20s | %-15s |\n", "Name", "ID", "Email", "Phone Number");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.printf("| %-10s | %-10s | %-20s | %-15s |\n", students[i].getName(), students[i].getId(), students[i].getEmail(), students[i].getPhoneNumber());
            }
        }
        System.out.println("--------------------------------------------------------------------");
        }

        public static int getAvailableSeatsCount() {
        return MAX_CAPACITY - studentCount; // error: studentCount cannot be resolved to a variable
        }
    
    // save data to the txt file
    private static void saveToFile(Student[] students , int studentCount) {
        Scanner scanner = new Scanner(System.in); 
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    
            for (int i = 0; i < studentCount; i++) {
                Student student = students[i];
                String studentData = student.getName() + "," + student.getId() + "," + student.getEmail() + "," + student.getPhoneNumber();
                bufferedWriter.write(studentData);
                bufferedWriter.newLine();
            }
    
            bufferedWriter.close();
            System.out.println("Students saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    //load data from the txt file
    private static int loadFromFile(Student[] students) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
    
        int studentCount = 0;
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
    
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] studentData = line.split(",");
                String name = studentData[0];
                String id = studentData[1];
                String email = studentData[2];
                String phoneNumber = studentData[3];
                students[studentCount] = new Student(name, id, email, phoneNumber);
                studentCount++;
            }
    
            fileScanner.close();
            System.out.println("Students loaded from file successfully.");
    
        } catch (FileNotFoundException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
        
        return studentCount;
    }
    
}
