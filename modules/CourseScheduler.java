import java.util.*;

/*
 CourseScheduler
 - Each Course holds a queue of student IDs to represent registration order.
 - registerStudent enqueues if capacity allows, allocateNext dequeues the next student.
*/
public class CourseScheduler {
    public static class Course {
        public String code;
        public int capacity;
        public Queue<String> queue;
        public Course(String code, int capacity) {
            this.code = code;
            this.capacity = capacity;
            this.queue = new LinkedList<>();
        }

        // Register a student; returns false if course is full.
        public boolean registerStudent(String studentId) {
            if (queue.size() >= capacity) return false;
            queue.offer(studentId);
            return true;
        }

        // Allocate the next student (remove from queue).
        public String allocateNext() {
            return queue.poll();
        }

        // Return a snapshot of the waiting list.
        public List<String> waitingList() {
            return new ArrayList<>(queue);
        }

        public String toString() {
            return code + " | capacity: " + capacity + " | waiting: " + queue.size();
        }
    }

    // Demonstration main
    public static void main(String[] args) {
        Course c = new Course("CS101", 2);
        System.out.println(c.registerStudent("S001")); // true
        System.out.println(c.registerStudent("S002")); // true
        System.out.println(c.registerStudent("S003")); // false - course full
        System.out.println("Allocated: " + c.allocateNext());
        System.out.println("Waiting: " + c.waitingList());
    }
}
