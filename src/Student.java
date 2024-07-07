public class Student {
    private String name;
    private String id;
    private String email;
    private Module[] modules;

    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.modules = new Module[1]; // Initialize the array with a size of 1
    }

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

    public Module[] getModules() {
        return modules;
    }

    public void addModule(Module module) {
        // Resize the array if it's full
        if (modules[modules.length - 1]!= null) {
            Module[] newModules = new Module[modules.length * 2];
            System.arraycopy(modules, 0, newModules, 0, modules.length);
            modules = newModules;
        }

        // Add the module to the array
        for (int i = 0; i < modules.length; i++) {
            if (modules[i] == null) {
                modules[i] = module;
                break;
            }
        }
    }
}