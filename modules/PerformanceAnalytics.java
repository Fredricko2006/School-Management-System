import java.util.*;

/*
 PerformanceAnalytics
 - Uses a min-heap (priority queue) of size k to find top-K student scores.
 - Also includes a method to build a subject correlation matrix from student subject vectors.
*/
public class PerformanceAnalytics {
    public static class StudentScore {
        public String studentId;
        public double score;
        public StudentScore(String studentId, double score) {
            this.studentId = studentId;
            this.score = score;
        }
        public String toString() { return studentId + " | " + score; }
    }

    // Returns the top k scores in descending order.
    public List<StudentScore> topK(List<StudentScore> scores, int k) {
        PriorityQueue<StudentScore> pq = new PriorityQueue<>(Comparator.comparingDouble(s -> s.score));
        for (StudentScore s : scores) {
            if (pq.size() < k) pq.offer(s);
            else if (s.score > pq.peek().score) { pq.poll(); pq.offer(s); }
        }
        List<StudentScore> out = new ArrayList<>();
        while (!pq.isEmpty()) out.add(pq.poll());
        Collections.reverse(out);
        return out;
    }

    // Build a simple subject correlation matrix by summing outer products of student score vectors.
    public double[][] buildSubjectMatrix(Map<String, double[]> studentSubjectScores) {
        int m = studentSubjectScores.values().iterator().next().length;
        double[][] mat = new double[m][m];
        for (double[] scores : studentSubjectScores.values()) {
            for (int i = 0; i < m; i++) for (int j = 0; j < m; j++) mat[i][j] += scores[i] * scores[j];
        }
        return mat;
    }

    // Demo main
    public static void main(String[] args) {
        PerformanceAnalytics pa = new PerformanceAnalytics();
        List<StudentScore> scores = Arrays.asList(new StudentScore("S001", 78), new StudentScore("S002", 92), new StudentScore("S003", 85));
        System.out.println("Top2: " + pa.topK(scores, 2));
        Map<String, double[]> ss = new HashMap<>();
        ss.put("S001", new double[]{70,80,90});
        ss.put("S002", new double[]{60,75,85});
        double[][] mat = pa.buildSubjectMatrix(ss);
        System.out.println("Matrix[0][0]=" + mat[0][0]);
    }
}
