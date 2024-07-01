
import java.util.ArrayList;
import java.util.List;

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

    public void addModule(Module module) {
        this.modules.add(module);
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

    
}