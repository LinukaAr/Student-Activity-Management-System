
public class Module {
    private int[] marks;
    

    public Module(int[] marks) {
        this.marks = marks;
       
    }
    public int[] getMarks() {
        return this.marks;
    }

    public int getTotalMarks() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public double getAverageMarks() {
        return getTotalMarks() / (double) marks.length;
    }

    public String getGrade() {
        double average = getAverageMarks();
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