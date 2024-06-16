package src;

import java.util.List;
import java.util.ArrayList; 

public class Student {
    private String name;
    private String id;
    private String email;
    private List<Module> modules;

    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.modules = new ArrayList<>();
    }

    public List<Module> getModules() {
        return this.modules;
    }

    // Getters and setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public int getTotalMarks() {
        int total = 0;
        for (Module module : modules) {
            for (int mark : module.getMarks()) {
                total += mark;
            }
        }
        return total;
    }

    public double getAverageMarks() {
        return getTotalMarks() / (double) 3;
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
