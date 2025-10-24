# System Design Document

## Architecture Overview
A simple modular design is used. Each module is implemented as an independent
Java class placed under /src. Modules:
- StudentRegistry: manages students using a HashMap keyed by student ID.
- CourseScheduler: models course registration using a Queue for FCFS behavior.
- FeeTrackerBST: stores payments in a Binary Search Tree sorted by timestamp.
- LibrarySystem: maintains a catalog HashMap keyed by ISBN and a Stack for recent actions.
- PerformanceAnalytics: computes top-K performers using a heap and builds a subject correlation matrix.

A central controller (not implemented here) could instantiate and coordinate these modules.

## Data Structure Justification
- HashMap for StudentRegistry: constant-time average lookup for frequent queries.
- Queue for CourseScheduler: preserves registration order (FCFS) and supports O(1) operations.
- BST for FeeTracker: natural in-order traversal gives sorted payments; AVL could be used if strict balancing is needed.
- HashMap + Stack for LibrarySystem: fast ISBN lookup and LIFO recent-action tracking.
- Heap + Matrix for Analytics: heap efficiently extracts top-K; matrix supports correlation analytics.

## Sample Flow (Student Registration -> Course Allocation)
1. Add a student to StudentRegistry with addStudent(student).
2. Student registers for course via CourseScheduler.registerStudent(courseCode, studentId).
3. CourseScheduler enqueues the student into the course's queue.
4. When allocation happens, CourseScheduler.allocateNext() dequeues and returns the next student.

