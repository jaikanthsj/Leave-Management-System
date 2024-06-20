Employee Leave Management System


Project Description:
   --> The Employee Leave Management System is a comprehensive solution designed for an IT company to manage employee leaves effectively. This system provides REST APIs to handle leave applications, approvals, and policy management. The project is built using Java, Spring Boot, JPA, and PostgreSQL, with JWT for secure admin/manager access and basic security for employees.


Features:

Policy Conditions:
1. Casual Leave (CL):
    --> Must be created at least 5 working days in advance.
    --> Maximum 1 CL per month.
   
2. Sick Leave (SL):
    --> Can be taken in case of emergencies.
    --> Maximum 1 SL per month.
   
3. Earned Leave (EL):
    --> Maximum 15 EL per year.

4. Optional Leave (OL) (additional feature):
    --> Must be informed at least 10 working days in advance.

   
Roles and Functionalities:

1. Admin/Manager
    --> Create or update policy rules.
    --> Approve or reject leave applications based on policy conditions.
    --> Update policy to include Optional Leave.

2. Employee
    --> Apply for leaves adhering to policy conditions.
    --> View total leaves, leave taken, and leave types (SL, CL, EL).
    --> Generate leave report with the file name format: EmployeeId_CurrentDate.csv.


Technologies Used:
    --> Backend: Java, Spring Boot
    --> Database: PostgreSQL
    --> Security: JWT (Admin/Manager), Basic Security (Employees)
    --> ORM: JPA (Java Persistence API)

    
Installation and Setup:
Prerequisites:
    --> Java 8 or higher
    --> Maven
    --> PostgreSQL


API Endpoints:

For ADMIN's:
1. SignUp --> POST /auth/signup - Add ADMIN/EMPLOYEE
2. Login --> POST /auth/login - If ADMIN Token will generate
3. Add Policy --> POST /admin/newPolicy - Add new policy
4. Update Policy --> PUT /admin/updatePolicy/{policyId} - Update the already existing policies
5. Leave Approve --> POST /leave/approve/{leaveId} - Approve the leave applied by the employee
6. Leave Rejection --> POST /leave/reject/{leaveId} - Reject the leave applied by the employee
7. Add Events --> POST /api/calendar/events - Add Events like Fixed Holiday, Optional Holiday, Business Day and so on
8. Delete Events --> DELETE /api/calendar/events/{id} - Delete the events if not required

For EMPLOYEE's:
1. Apply Leave --> POST /leave/apply - Employees apply for leave
2. Generate report --> GET /leave/generate-csv/{userId} - Generate the remaining available leaves for the employees with their Id
3. Get Events --> GET /api/calendar/events - View all the events in the current year
4. Get Calendar --> GET /api/calendar/month/{year}/{month} - View the events for the particular month and the year
