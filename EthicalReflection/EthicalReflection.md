# Ethical Reflection

This project considers the following ethical aspects:

- Privacy: Store only the minimum required student information and avoid exposing sensitive fields (e.g., national ID). In production use encryption and access controls.
- Fairness: Course allocation should be transparent. A pure FCFS approach can be unfair to students who register later; consider reservation policies or priority rules if fairness is required.
- Transparency: Logs and clear documentation help stakeholders understand how decisions are made. Avoid hidden heuristics.
- Data retention: Keep payment and personal data only as long as necessary and follow institutional policies.
