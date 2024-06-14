package src;

public class Module {
    private String moduleName;
    private int marks;
    private String grade;

    public Module(String moduleName, int marks) {
        this.moduleName = moduleName;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    private String calculateGrade(int marks) {
        if (marks >= 80) {
            return "Distinction";
        } else if (marks >= 70) {
            return "Merit";
        } else if (marks >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}
