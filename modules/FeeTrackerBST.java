import java.util.*;

/*
 FeeTrackerBST
 - Stores Payment objects in a Binary Search Tree keyed by timestamp for sorted reporting.
 - In-order traversal yields payments sorted by time.
 - Note: For production, consider self-balancing tree (AVL/Red-Black) to guarantee O(log n).
*/
public class FeeTrackerBST {
    public static class Payment {
        public String studentId;
        public long timestamp;
        public double amount;
        public Payment(String studentId, long timestamp, double amount) {
            this.studentId = studentId;
            this.timestamp = timestamp;
            this.amount = amount;
        }
        public String toString() {
            return studentId + " | " + amount + " | " + new Date(timestamp);
        }
    }

    private static class Node {
        Payment p;
        Node left, right;
        Node(Payment p) { this.p = p; }
    }

    private Node root;

    // Public add method
    public void add(Payment p) { root = add(root, p); }

    // Recursive helper inserts payment into BST by timestamp.
    private Node add(Node node, Payment p) {
        if (node == null) return new Node(p);
        if (p.timestamp < node.p.timestamp) node.left = add(node.left, p);
        else node.right = add(node.right, p);
        return node;
    }

    // In-order traversal returns payments sorted by timestamp.
    public List<Payment> inOrder() {
        List<Payment> out = new ArrayList<>();
        inOrder(root, out);
        return out;
    }

    private void inOrder(Node node, List<Payment> out) {
        if (node == null) return;
        inOrder(node.left, out);
        out.add(node.p);
        inOrder(node.right, out);
    }

    // Demo main
    public static void main(String[] args) {
        FeeTrackerBST ft = new FeeTrackerBST();
        ft.add(new Payment("S001", System.currentTimeMillis() - 3600 * 1000, 2000));
        ft.add(new Payment("S002", System.currentTimeMillis() - 7200 * 1000, 1500));
        ft.add(new Payment("S001", System.currentTimeMillis(), 3000));
        System.out.println("Payments sorted by time:");
        for (Payment p : ft.inOrder()) System.out.println(p);
    }
}

