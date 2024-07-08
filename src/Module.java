public class Module {
    private int[] marks;

    // Constructor
    public Module(int[] marks) {
        this.marks = marks;
    }

    public int[] getMarks() {
        return marks;
    }

    // Method to get the total marks
    public int getTotalMarks() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to get the average of the marks
    public static double getAverage(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total / (double) marks.length;
    }

    // Method to get the grade based on the average
    public static String getGrade(double average) {
        if (average >= 70) {
            return "A";
        } else if (average >= 60) {
            return "B";
        } else if (average >= 50) {
            return "C";
        } else if (average >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}