import java.util.*;

/*
 Main.java
 ---------------------------------------
 This file ties together all modules of the School Management System:
  - StudentRegistry
  - CourseScheduler
  - FeeTrackerBST
  - LibrarySystem
  - PerformanceAnalytics

 It demonstrates how the different modules interact in a simplified workflow.
*/

public class Main {
    public static void main(String[] args) {

        // ===== 1. Initialize all system modules =====
        StudentRegistry registry = new StudentRegistry();
        CourseScheduler.Course course = new CourseScheduler.Course("CS101", 2);
        FeeTrackerBST feeTracker = new FeeTrackerBST();
        LibrarySystem library = new LibrarySystem();
        PerformanceAnalytics analytics = new PerformanceAnalytics();

        // ===== 2. Student registration =====
        System.out.println("=== Student Registration ===");
        StudentRegistry.Student s1 = new StudentRegistry.Student("S001", "Alice Mwangi", "alice@uni.ac.ke");
        StudentRegistry.Student s2 = new StudentRegistry.Student("S002", "Ben Otieno", "ben@uni.ac.ke");
        registry.addStudent(s1);
        registry.addStudent(s2);
        System.out.println("Students in registry: " + registry.listAll());

        // ===== 3. Course scheduling =====
        System.out.println("\n=== Course Scheduling ===");
        course.registerStudent(s1.id);
        course.registerStudent(s2.id);
        System.out.println("Registered students: " + course.waitingList());
        System.out.println("Allocated next: " + course.allocateNext());
        System.out.println("Remaining in queue: " + course.waitingList());

        // ===== 4. Fee tracking =====
        System.out.println("\n=== Fee Tracking ===");
        feeTracker.add(new FeeTrackerBST.Payment(s1.id, System.currentTimeMillis(), 2000));
        feeTracker.add(new FeeTrackerBST.Payment(s2.id, System.currentTimeMillis(), 2500));
        System.out.println("Fee records (sorted by timestamp):");
        for (FeeTrackerBST.Payment p : feeTracker.inOrder()) {
            System.out.println(p);
        }

        // ===== 5. Library system =====
        System.out.println("\n=== Library System ===");
        library.addBook(new LibrarySystem.Book("978-1", "Data Structures in Java", 2));
        library.borrow("978-1", s1.id);
        library.returnBook("978-1", s1.id);
        System.out.println("Recent actions: " + library.recentActions(5));

        // ===== 6. Performance analytics =====
        System.out.println("\n=== Performance Analytics ===");
        List<PerformanceAnalytics.StudentScore> scores = Arrays.asList(
                new PerformanceAnalytics.StudentScore(s1.id, 84.5),
                new PerformanceAnalytics.StudentScore(s2.id, 92.0)
        );
        System.out.println("Top performer(s): " + analytics.topK(scores, 1));

        Map<String, double[]> subjectScores = new HashMap<>();
        subjectScores.put(s1.id, new double[]{70, 80, 90});
        subjectScores.put(s2.id, new double[]{85, 88, 94});
        double[][] matrix = analytics.buildSubjectMatrix(subjectScores);
        System.out.println("Sample subject correlation matrix value [0][0]: " + matrix[0][0]);

        // ===== 7. System summary =====
        System.out.println("\nSystem simulation complete. All modules interacted successfully.");
    }
}
