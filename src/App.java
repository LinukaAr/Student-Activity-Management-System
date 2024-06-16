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
                    
                    manageStudentResults(input);
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
    
        // Add the new student to the students list
        students.add(new Student(name, id, email));
    
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
    // function to load students from file
    private static void loadFromFile(Scanner scanner) {
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        studentCount = 0;

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            students.clear();  // Clear the list of students before loading
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();// Read a line from the file
                String[] studentData = line.split(",");// Split the line by comma
                if (studentData.length == 3) {
                    studentNames[studentCount] = studentData[0];
                    studentIds[studentCount] = studentData[1];
                    studentEmails[studentCount] = studentData[2];
                    studentCount++;
                    students.add(new Student(studentData[0], studentData[1], studentData[2])); // Add student to the list
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
        while (!exit){
        System.out.println("==================================================================");
        System.out.println("Manage Student Results");
        System.out.println("1. Add module marks ");
        System.out.println("2. Generate a summary");
        System.out.println("3. Generate complete report");
        System.out.println("4.Main Menu");
        System.out.println("==================================================================");
        System.out.print("Please enter your choice: ");
        

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                addModuleMarks(input);
                break;
            case 2:
                genarateSummery();
                break;
            case 3:
               generateCompleteReport();
                break;
            case 4:
                exit = true; 
                main(studentEmails);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
    }

    public static void addModuleMarks(Scanner input) {
        System.out.print("Enter student ID: ");
        String studentId = input.next();
    
        // Find the student with the given ID
        Student student = students.stream()//stream is used to iterate over the collection
        .filter(s -> s.getId().equals(studentId))  //filter is used to filter the elements based on the given condition
        .findFirst()//return the first element of the stream
        .orElse(null);// return the default value if no value is present

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
        int[] marks = {mark1, mark2, mark3};//array to store the marks
        Module module = new Module(marks);
        student.addModule(module);
    
        System.out.println("Marks added successfully for student " + studentId);
    }
    
    private static List<Student> students = new ArrayList<>();//
    
    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
        //function to generate summary
        private static void genarateSummery() {
             
            System.out.println("-------------------------------------------------");
            System.out.println("Total Student Registrations: " + studentCount);
        
            int studentsWithPassingMarks = 0;
            for (int i = 0; i < studentCount; i++) {
                Student student = findStudentById(studentIds[i]);
                if (student != null && !student.getModules().isEmpty()) {//check if the student is not null and the modules are not empty
                    boolean passingMarks = true;
                    for (Module module : student.getModules()) {
                        for (int mark : module.getMarks()) {
                            if (mark < 40) {
                                passingMarks = false;
                                break;
                            }
                        }
                        if (!passingMarks) break;
                    }
                    if (passingMarks) {
                        studentsWithPassingMarks++;
                    }
                }
            }
        
            System.out.println("Total Students with Passing Marks in all Modules: " + studentsWithPassingMarks);
            System.out.println("-------------------------------------------------");
        }


    public static void generateCompleteReport() {
        sortStudentsByAverageMarks(students);
        System.out.printf("%-10s | %-15s | %-15s | %-15s | %-15s | %-5s | %-7s | %-5s%n", 
            "Student ID", "Student Name", "Module 1 marks", "Module 2 marks", "Module 3 marks", "Total", "Average", "Grade");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-10s | %-15s | ", student.getId(), student.getName());
            int[] marks = student.getModules().isEmpty() ? new int[3] : student.getModules().get(0).getMarks();//check if the modules are empty
            for (int i = 0; i < 3; i++) {//loop through the array to display the marks
                if (i < marks.length) {
                    System.out.printf("%-15s | ", marks[i]);
                } else {
                    System.out.printf("%-15s | ", "N/A");
                }
            }
            System.out.printf("%-5s | %-7.2f | %-5s%n", student.getTotalMarks(), student.getAverageMarks(), student.getGrade());
        }
    }
        
        
    //function to sort students by average marks(Bubble sort algorithm)
    public static void sortStudentsByAverageMarks(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (students.get(j).getAverageMarks() < students.get(j+1).getAverageMarks()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j+1));
                    students.set(j+1, temp);
                }
            }
        }
}

}
