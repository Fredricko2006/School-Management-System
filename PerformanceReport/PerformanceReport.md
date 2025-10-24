# Performance Report

## Time Complexity Summary
- StudentRegistry (HashMap): insert/find/delete — average O(1); worst-case O(n) if many collisions.
- CourseScheduler (Queue): enqueue/dequeue — O(1).
- FeeTracker (BST): insert/search/delete — average O(log n), worst O(n) (unbalanced).
- LibrarySystem (HashMap + Stack): lookup/borrow/return — O(1).
- PerformanceAnalytics (Heap): build O(n), top-K extraction O(k log n). Subject matrix operations O(m^2) for m subjects.

## Space Complexity Notes
Each module stores O(n) elements proportional to the number of records (students, payments, books, scores). Matrices require O(m^2) space where m is number of subjects.
