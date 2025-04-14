# Payment Processing System for Family-Oriented Service

# Overview

This project is a secure payment processing system designed for a family-oriented service. The system facilitates the management of payments for both parents and students, with atomic transactions, dynamic rate application, and multi-table balance updates. The application ensures role-based access control, updates balances based on payment rules (including shared children), and applies dynamic fees/discounts.

# Table of Contents

Build Instructions

Running the Application

Testing the Application

Design Decisions

Security Design

Multi-Table Payment Processing

Arithmetic Logic and Balance Updates

# Build Instructions

Clone the Repository: Clone the repository to your local machine using the following command:

bash
Copy
Edit
git clone <repository-url>
Install Dependencies: Make sure you have the following installed:

Java 11 or higher

Maven

JDK (Java Development Kit)

Navigate to the project directory and run:

bash
Copy
Edit
mvn clean install

# Build the Application:

To build the application, execute the following command:

bash
Copy
Edit
mvn package

# Run the Application:

To run the application, execute the following command:

bash
Copy
Edit
mvn spring-boot:run
The application will start on the default port 8080.

# Running the Application

Once the application is running, it exposes RESTful APIs for various operations, such as adding parents, students, configuring dynamic rates, and retrieving balances. You can interact with the application using Postman or CURL.

# Available Endpoints:

POST /api/parents - Add a parent

POST /api/students - Add a student

GET /api/students/{id} - Retrieve a student by ID

GET /api/students/parent/{parentId} - Retrieve all students for a specific parent

GET /api/parents/student/{studentId} - Retrieve parent details by student ID

GET /api/rates - Retrieve all rate configurations

POST /api/rates - Configure dynamic rates

# Testing the Application

To test the application:

Unit Tests: The application includes unit tests to ensure the correctness of logic. Run the following command to execute the tests:

bash
Copy
Edit
mvn test

# Integration Tests:

The application integrates with a mock database for simulating payment processing and balance updates. The integration tests ensure that multi-table processing works as expected.

# Manual Testing:

Use Postman or CURL Or Swagger to manually test the REST endpoints by sending requests with different payloads.

# Design Decisions

Security Design
Security in this payment processing system is a top priority. Here are the key design decisions related to security:

Role-Based Access Control (RBAC):

Users have roles such as ADMIN, PARENT, and STUDENT.

The application uses Spring Security to enforce role-based access to specific endpoints.

Only ADMIN users can add new parents and students, configure dynamic rates, and view sensitive data.

PARENTS can view their children’s balances, make payments, and view transaction history.

STUDENTS can view their own balance and transaction history.

JWT Authentication:

JSON Web Tokens (JWT) are used for authentication. When a user logs in, they receive a JWT that must be passed in subsequent requests as a bearer token.

JWTs are signed using a secure key and stored in HTTP headers for secure communication.

HTTPS:

The application requires HTTPS for secure communication between clients and the server.

Sensitive Data Handling:

Payment data, user credentials, and other sensitive information are securely stored and encrypted in the database.

Passwords are hashed using a secure algorithm such as bcrypt.

# Multi-Table Payment Processing

The payment processing system handles multiple related tables, such as Parents, Students, and Transactions, with interdependencies based on the shared children. Here’s how the design accommodates multi-table processing:

Parent and Student Relationship:

A student can have one or more parents, and a parent can have multiple students.

The system ensures atomicity by treating parent-child relationships as a core part of the payment processing logic.

When a payment is made, the student’s balance and the parent’s balance (if they share the student) are updated simultaneously within a single transaction to maintain data integrity.

# Transaction Management:

Each payment is recorded in the Payment table, with references to both the parent and the student involved.

The application uses Spring’s transaction management to ensure that updates to the balance fields of both Parents and Students are atomic. If any step fails, the transaction is rolled back.

# Arithmetic Logic and Balance Updates

The balance update logic involves both fixed and dynamic payment processing, influenced by rates that can change based on certain conditions. Here’s a detailed explanation of the arithmetic logic:

# Rate Calculation:

Each transaction may have a base fee, dynamic rate, and discount depending on factors like family size, student age, and payment deadlines.

The rate applied is retrieved from the Rates table and can change dynamically based on a variety of parameters.

# Balance Update Logic:

When a payment is made, the system checks if the parent and student are eligible for the payment.

The balance of the Student is updated first. If the student’s balance is sufficient to meet the payment amount, the balance is decreased.

If the payment is shared by the parent, the Parent balance is also updated based on the same payment amount, proportionally (e.g., 50% for each if there are two parents).

The system ensures that the balances are only updated if the payment is valid, ensuring no over-drawing of balances.

# Atomic Transactions:

The application uses Spring’s @Transactional annotation to ensure that balance updates for parents and students occur as a single atomic operation.

If a failure occurs at any point in the payment process, the transaction is rolled back, and no changes are made to the balances.

# Conclusion

This payment processing system is designed with security, accuracy, and efficiency in mind. By following the above instructions, you can build, run, and test the application, while also understanding the core design decisions that ensure secure and accurate payment processing.

For any additional information or troubleshooting, please refer to the project documentation or reach out to the development team.
