Brief description of the projects:


**1. First project - Queue Management System**
_I.Overview_
This project is a Queue Management System designed to streamline and optimize customer service operations. 
The system efficiently manages queues, reducing wait times and enhancing user experience.

_II.Features_
User Registration: Customers can register for a queue via a web or mobile interface.
Real-time Queue Monitoring: Users can view their position in the queue in real time.
Notifications: Alerts users when their turn is approaching.
Admin Dashboard: Enables queue management, user monitoring, and analytics.
Multi-Queue Support: Handles multiple queues simultaneously.
Priority Queuing: Supports priority-based queueing for special cases.

_III.Usage_
1.Open the application in your browser.
2.Register for a queue and receive a queue number.
3.Monitor your position in real-time.
4.Receive notifications when your turn is near.

_IV.Technologies Used_
Frontend: React.js, HTML, CSS
Backend: Node.js
Notifications: WebSockets / Firebase


**2. Second project - Study Platform Management System**
_I.Overview_
This project is a Study Platform Management System, designed to facilitate the administration of student activities, courses, and interactions between students and professors.
The system allows users (students, professors, and administrators) to manage academic activities efficiently through a structured database.

_II.Features_
User Authentication: Students, professors, and administrators can log in securely.
1.User Role Management:
Students can register for courses, view grades, join study groups, and interact with professors.
Professors can schedule activities, assign grades, and manage their assigned courses.
Administrators can manage users, assign professors to courses, and modify course data.
2.Course Management:
Courses have multiple activity types (lectures, seminars, labs) with a maximum number of students.
Professors are assigned based on the lowest student load.
3.Study Groups:
Students can form study groups for specific subjects.
Groups allow message exchange and activity planning.
4.Activity Scheduling:
Professors can schedule lectures, exams, and assignments.
Students can join available activities based on availability and scheduling constraints.
5.Grade Management:
Professors can grade students based on weighted averages.
Students can view and download their grades.

_III.Technologies Used_
Backend: MySQL for database management.
Frontend: Java-based GUI for user interaction.
Development Tools: IntelliJ IDEA for development and debugging.

_IV.Future Enhancements_
Implementing a payment system for course fees.
Adding a notification system for students and professors.
Enhancing user experience with an improved graphical interface.
Optimizing the system for handling large concurrent user requests.



**4. Fourth project - Order Management System**
_I.Overview_
This project is an Order Management System designed for handling customer orders in a warehouse. 
It provides functionalities for managing products, customers, and orders while ensuring efficient data processing through a structured database system.

_II.Features_
1.Product Management:
Add, update, delete, and list products in the database.
2.Customer Management:
Add, update, delete, and list customer records.
3.Order Processing:
Place customer orders and update stock levels accordingly.
Generate invoices for completed orders.
4.Database Management:
Uses a relational database to store data.
Implements business logic for data validation and transaction integrity.
5.Graphical User Interface:
Built using Java Swing for an interactive experience.

_III.Technologies Used_
Backend: Java (Swing for GUI, JDBC for database interaction)
Database: MySQL
Development Tools: IntelliJ IDEA

_IV.Future Enhancements_
Adding search and filtering functionalities for easier data retrieval.
Implementing user authentication to restrict access based on roles.
Enhancing the user interface for a better experience.
Extending the system to manage suppliers and employees.


**5. Fifth project - Waste Collection Management System**
_I.Overview_
This project is a Waste Collection Management System designed for a waste collection company. 
It implements a client-server architecture using service-oriented architecture (SOA) and design patterns to enhance functionality and modularity.

_II.Features_
User Roles:
1.Employee:
View the list of assigned waste collection addresses, sorted by location.
Display the optimal route for waste collection (graphical representation).
Search for a specific waste collection address and mark it as "collected."
2.Activity Coordinator:
View all waste collection addresses, sorted by location.
Filter waste based on collection status: Unassigned, Assigned but not collected, Collected
Perform CRUD operations on waste collection addresses.
Assign waste collection addresses to employees.
Export waste collection address lists in CSV, JSON, XML, and DOC formats.
View statistical reports (radial, circular, bar charts).
3.Administrator:
Manage users (add, update, delete, and filter by role).
View system users and their assigned roles.
Notify users about authentication changes via Email, SMS, WhatsApp, Skype.
The client interface is available in three international languages.

_III.Technologies Used_
Backend: Java (Spring Boot for server-side services)
Frontend: Java Swing (GUI-based client application)
Database: MySQL
Networking: Client-server communication using SOA
Build & Dependency Management: Maven
Data Export Formats: CSV, JSON, XML, DOC

_IV.System Architecture_
Client-Server Model:
Server: Runs continuously to manage user requests and process operations.
Client: A GUI-based application that sends requests and processes responses from the server.
Service-Oriented Architecture (SOA):
Communication between client and server is done via service requests.
At least five design patterns are used to ensure modularity and scalability.

_V.Future Enhancements_
Implement real-time tracking of employees for waste collection.
Introduce AI-based route optimization.
Improve mobile accessibility with a dedicated Android/iOS application.
Implement cloud-based storage for better data management.
