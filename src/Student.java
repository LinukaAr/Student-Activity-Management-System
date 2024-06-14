// package src;

// public class Student {
//     private String name;
//     private String id;
//     private String email;
//     private Module[] modules;


//     public Student(String name, String id, String email) {
//         this.name = name;
//         this.id = id;
//         this.email = email;
//         this.modules = new Module[3];
    
        
//     }

//     // Getters and setters for each field
//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public Module[] getModules() {
//         return modules;
//     }

//     public void addModule(int index, Module module) {
//         if (index >= 0 && index < 3) {
//             modules[index] = module;
//         }
//     }

//     public double calculateAverageMarks() {
//         int totalMarks = 0;
//         for (Module module : modules) {
//             if (module != null) {
//                 totalMarks += module.getMarks();
//             }
//         }
//         return totalMarks / 3.0;
//     }

//     public String calculateOverallGrade() {
//         double averageMarks = calculateAverageMarks();
//         if (averageMarks >= 80) {
//             return "Distinction";
//         } else if (averageMarks >= 70) {
//             return "Merit";
//         } else if (averageMarks >= 40) {
//             return "Pass";
//         } else {
//             return "Fail";
//         }
//     }



//     @Override
//     public String toString() {
//         return "Student{" +
//                 "name='" + name + '\'' +
//                 ", id='" + id + '\'' +
//                 ", email='" + email + '\'' 
//                 ;
//     }
// }


