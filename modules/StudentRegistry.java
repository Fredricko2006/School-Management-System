import java.util.*;

/*
 StudentRegistry
 - Uses a HashMap keyed by student ID for fast average-case lookup, insertion, and deletion.
 - Methods: addStudent, findStudent, removeStudent, listAll
*/
public class StudentRegistry {
    public static class Student {
        public String id;
        public String name;
        public String email;
        public Student(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        public String toString() {
            return id + " | " + name + " | " + email;
        }
    }

    // HashMap provides average O(1) operations for lookup and insertion.
    private Map<String, Student> table = new HashMap<>();

    // Add a student to the registry. If the ID exists, this replaces the record.
    public void addStudent(Student s) {
        table.put(s.id, s);
    }

    // Find and return a student by ID, or null if not found.
    public Student findStudent(String id) {
        return table.get(id);
    }

    // Remove a student by ID and return the removed record (or null).
    public Student removeStudent(String id) {
        return table.remove(id);
    }

    // List all students currently in the registry.
    public List<Student> listAll() {
        return new ArrayList<>(table.values());
    }

    // Sample main for quick testing and demonstration.
    public static void main(String[] args) {
        StudentRegistry reg = new StudentRegistry();
        reg.addStudent(new Student("S001", "Alice Mwangi", "alice@uni.ac.ke"));
        reg.addStudent(new Student("S002", "Ben Otieno", "ben@uni.ac.ke"));
        System.out.println("Find S001: " + reg.findStudent("S001"));
        reg.removeStudent("S002");
        System.out.println("All students: " + reg.listAll());
    }
}
