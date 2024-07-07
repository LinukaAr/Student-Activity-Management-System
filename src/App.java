import java.util.*;
import java.io.*;

public class App {
    private static final int MAX_CAPACITY = 100;
    private static String[] studentNames = new String[MAX_CAPACITY];//arrays to store student names,id and emails with max capacity
    private static String[] studentIds = new String[MAX_CAPACITY];
    private static String[] studentEmails = new String[MAX_CAPACITY];
    private static int studentCount = 0;
    private static Student[] students = new Student[MAX_CAPACITY];
    


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
            try {
                int choice = input.nextInt();
                input.nextLine();
    
                //switch statement 
                switch (choice) {
                    case 1:
                        int availableSeats = getAvailableSeatsCount(); //call the function to get available seats
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
                        manageStudentResults(input);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        exit = true; // make the exit variable true to exit the loop
                        break;
                    default:
                        System.out.println("Invalid choice"); //if the user enters invalid choice
                        input.nextLine(); // consume the invalid input
                        break;
                }
            } catch (java.util.InputMismatchException e) { //catch the exception if the user enters invalid input 
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    
        input.close(); 
    }

    //function to initialise the arrays
    private static void initialise(String[] names, String[] ids, String[] emails) {
        for (int i = 0; i < MAX_CAPACITY; i++) { //loop through the array until the max capacity and set the values to empty
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
    
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
    
        Student student = new Student(name, id, email);
        students[studentCount] = student;
        studentNames[studentCount] = name;
        studentIds[studentCount] = id;
        studentEmails[studentCount] = email;
        studentCount++;
    
        System.out.println("Student registered successfully.");
    }


    //check whether the id is duplicate or not
    private static boolean isDuplicateId(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (id.equals(studentIds[i])) { //check if the id is equal to the id in the array
                return true;
            }
        }
        return false;
    }

    //function to delete student
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            // check if the student id is available in the array
            if (studentIds[i] != null && studentIds[i].equals(id)) {
                // Shift all elements to the left to fill the gap
                for (int j = i; j < studentCount - 1; j++) {
                    studentNames[j] = studentNames[j + 1];
                    studentIds[j] = studentIds[j + 1];
                    studentEmails[j] = studentEmails[j + 1];
                }
                // Set the last element to null
                studentNames[studentCount - 1] = null;
                studentIds[studentCount - 1] = null;
                studentEmails[studentCount - 1] = null;
                studentCount--; // decrement the student count
                System.out.println("Student with ID " + id + " removed successfully.");
                break;
            }else{
                System.out.println("Student with ID " + id + " not found.");
            }
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
        
        //try with resources to write to the file
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < studentCount; i++) {
                if (studentNames[i] != null && studentIds[i] != null && studentEmails[i] != null) {
                    String studentData = studentNames[i] + "," + studentIds[i] + "," + studentEmails[i]; //write the student data to the file separated by comma
                    bufferedWriter.write(studentData);
                    bufferedWriter.newLine();
                }
            }
            System.out.println("Students saved to file successfully.");
        } catch (IOException e) { //catch the exception if any error occurs
            System.out.println("Error saving students to file: " + e.getMessage());
        }
    }
    
    // function to load students from file
    private static void loadFromFile(Scanner scanner) {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        studentCount = 0;

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine(); 
                String[] studentData = line.split(","); 
                if (studentData.length == 3) {
                    studentNames[studentCount] = studentData[0]; 
                    studentIds[studentCount] = studentData[1];
                    studentEmails[studentCount] = studentData[2];
                    students[studentCount] = new Student(studentData[0], studentData[1], studentData[2]); 
                    studentCount++;
                }
            }
            System.out.println("Students loaded from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading students from file: " + e.getMessage());
        }
    }

    //print the mange Student menu
    private static void manageStudentResults(Scanner input) {
        boolean exit = false;
        while (!exit) {
            System.out.println("==================================================================");
            System.out.println("Manage Student Results");
            System.out.println("1. Add module marks ");
            System.out.println("2. Generate a summary");
            System.out.println("3. Generate complete report");
            System.out.println("4.Main Menu");
            System.out.println("===============================================================");
    
            System.out.print("Please enter your choice: ");
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        addModuleMarks(input);
                        break;
                    case 2:
                        generateSummary();
                        break;
                    case 3:
                        generateCompleteReport();
                        break;
                    case 4:
                        exit = true; 
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
    
            } catch (java.util.InputMismatchException e) { //catch the exception if the user enters invalid input
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    public static void addModuleMarks(Scanner input) {
        System.out.print("Enter student ID: ");
        String studentId = input.next();

        Student student = null;
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(studentId)) {
                student = students[i];
                break;
            }
        }

        if (student == null) {
            System.out.println("No student found with the given ID.");
            return;
        }

        System.out.print("Enter marks for module 1: ");
        int mark1 = input.nextInt();
        System.out.print("Enter marks for module 2: ");
        int mark2 = input.nextInt();
        System.out.print("Enter marks for module 3: ");
        int mark3 = input.nextInt();
        int[] marks = {mark1, mark2, mark3};
        Module module = new Module(marks);
        student.addModule(module);

        System.out.println("Marks added successfully for student " + studentId);
    }
        
    // function to generate summary
    private static void generateSummary() {
        int totalStudentRegistrations = studentCount;
        int totalStudentsPassedAllModules = 0;

        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student!= null) {
                Module[] modules = student.getModules();
                if (modules!= null && modules.length > 0) {
                    Module module = modules[0];
                    if (module!= null) {
                        int[] marks = module.getMarks();
                        if (marks!= null && marks.length > 0) {
                            boolean passedAllModules = true;
                            for (int mark : marks) {
                                if (mark < 40) {
                                    passedAllModules = false;
                                    break;
                                }
                            }
                            if (passedAllModules) {
                                totalStudentsPassedAllModules++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Summary:");
        System.out.println("Total student registrations: " + totalStudentRegistrations);
        System.out.println("Total students who havepassed all three modules: " + totalStudentsPassedAllModules);
    }

    
    private static void generateCompleteReport() {
        bubbleSortStudentsByAverage(); // Sort students by average marks in ascending order
    
        System.out.println("Complete Report:");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Student ID | Student Name | Module 1 Marks | Module 2 Marks | Module 3 Marks | Total | Average | Grade");
        System.out.println("-------------------------------------------------------------------------------------------------------");
    
        // First pass: Print students with marks
        for (int i = 0; i < studentCount; i++) {
            printStudentReport(students[i], true); // true indicates we're printing students with marks
        }
    
        // Second pass: Print students without marks
        for (int i = 0; i < studentCount; i++) {
            printStudentReport(students[i], false); // false indicates we're printing students without marks
        }
    }
    
    private static void printStudentReport(Student student, boolean withMarks) {
        if (student != null) {
            Module[] modules = student.getModules();
            if (modules != null && modules.length > 0) {
                Module module = modules[0];
                if (module != null) {
                    int[] marks = module.getMarks();
                    if (marks != null && marks.length > 0) {
                        if (withMarks) { // Print only if student has marks
                            int total = 0;
                            for (int mark : marks) {
                                total += mark;
                            }
                            double average = total / (double) marks.length;
                            String grade = Module.getGrade(average);
    
                            System.out.println(String.format("%-10s | %-12s | %-14d | %-14d | %-14d | %-5d | %-7.2f | %s", student.getId(), student.getName(), marks[0], marks[1], marks[2], total, average, grade));
                        }
                    } else if (!withMarks) { // Print only if student does not have marks
                        System.out.println(String.format("%-10s | %-12s | %-14s | %-14s | %-14s | %-5s | %-7s | %s", student.getId(), student.getName(), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"));
                    }
                } else if (!withMarks) {
                    System.out.println(String.format("%-10s | %-12s | %-14s | %-14s | %-14s | %-5s | %-7s | %s", student.getId(), student.getName(), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"));
                }
            } else if (!withMarks) {
                System.out.println(String.format("%-10s | %-12s | %-14s | %-14s | %-14s | %-5s | %-7s | %s", student.getId(), student.getName(), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"));
            }
        }
    }
       private static void bubbleSortStudentsByAverage() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                Student student1 = students[j];
                Student student2 = students[j + 1];

                if (student1!= null && student2!= null) {
                    Module[] modules1 = student1.getModules();
                    Module[] modules2 = student2.getModules();

                    if (modules1!= null && modules1.length > 0 && modules2!= null && modules2.length > 0) {
                        Module module1 = modules1[0];
                        Module module2 = modules2[0];

                        if (module1!= null && module2!= null) {
                            int[] marks1 = module1.getMarks();
                            int[] marks2 = module2.getMarks();

                            if (marks1!= null && marks1.length > 0 && marks2!= null && marks2.length > 0) {
                                double average1 = Module.getAverage(marks1);
                                double average2 = Module.getAverage(marks2);

                                if (average1 < average2) {
                                    // Swap students
                                    Student temp = students[j];
                                    students[j] = students[j + 1];
                                    students[j + 1] = temp;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
    
