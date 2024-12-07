---

# Mini Loan App

A Full-Stack Application built with React, Spring Boot, and MongoDB atlas for managing loan applications, repayments, and user management like user registration etc. The app allows users to apply for loans, track repayments, and view loan statuses. Admins can approve loan requests.

## Features

- **User Authentication**:
  - Registration and Login using JWT-based authentication.
  - Role-based access control with **Admin** and **Customer** roles.
  
- **Loan Management**:
  - Apply for loans by filling out a loan application form.
  - View loan details, including repayment schedules.
  - Admin can approve loan requests.
  - Admin can view all pending loans.
  
- **Repayment Management**:
  - Customers can submit repayments for loans once admin approves it.
  - Customer can track all repayments made by him.
  - Once all repayments of a particular loan is PAID then status of that loan becomes PAID automatically.
  
- **Admin Dashboard**:
  - Admin can approve pending loans.
  - Admin can see a list of all loan requests and approve .
  
- **Frontend**:
  - React.js for the frontend UI.
  - Protected routes for user and admin-specific pages.

- **Backend**:
  - Spring Boot for the backend.
  - JWT-based authentication for secure API endpoints.
  - MongoDB integration for storing users, loans, and repayment data.

## Technologies Used

- **Frontend**:
  - React.js
  - React Router
  - Context API
  - Axios for API requests
  - JWT Authentication
  
- **Backend**:
  - Spring Boot
  - JWT Authentication
  - MongoDB
  
- **Development Tools**:
  - Maven for building the backend
  - Node.js for the frontend
  
## Getting Started

To get a local copy of the project up and running, follow these steps:

### Prerequisites

- Install [Node.js](https://nodejs.org/)
- Install [Java 11 or higher](https://adoptopenjdk.net/)
- Install [MongoDB](https://www.mongodb.com/try/download/community) (for local development)
  

---

### **Backend Setup**

1. **Clone the Repository**  
   Clone the repository using the following command:  
   ```bash
   git clone https://github.com/omkar193/mini-loan-app-fullStack.git
   ```

2. **Navigate to the Backend Directory**  
   Move into the backend directory:  
   ```bash
   cd mini-loan-app-fullStack/miniLoanApp
   ```

3. **Verify Prerequisites**  
   Ensure the following are installed on your system:  
   - **Java 17 or above**  
     Verify installation with:  
     ```bash
     java -version
     ```
   - **Maven**  
     Verify installation with:  
     ```bash
     mvn -v
     ```
   - **MongoDB**  
     Ensure MongoDB Atlas or a local MongoDB instance is running and properly configured.

4. **Run the Spring Boot Application**  
   - To run the application using the embedded Maven wrapper:  
     ```bash
     ./mvnw spring-boot:run
     ```
     *Note for Windows users:* Use the following instead:  
     ```bash
     mvnw.cmd spring-boot:run
     ```
   - Alternatively, package the application into a JAR and run it:  
     ```bash
     ./mvnw package
     java -jar target/miniLoanApp-0.0.1-SNAPSHOT.jar
     ```

5. **Default Configuration**  
   - By default, the backend will run on `http://localhost:8080`.  
   - To change configurations (e.g., port), edit the `application.properties` file located in:  
     ```
     src/main/resources/application.properties
     ```

6. **(Optional) Running Without Maven**  
   If Maven is not installed, you can use an IDE like IntelliJ IDEA or Eclipse:  
   - Import the project as a Maven project.  
   - Locate the `MiniLoanAppApplication` class in the `com.techdome.miniLoanApp` package.  
   - Run the class as a Spring Boot application.

---


### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd mini-loan-app-fullStack/mini-loan-app
   ```
   
2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the React development server:
   ```bash
   npm start
   ```

   The frontend will be available at `http://localhost:3000`.

### Database Configuration

- **MongoDB Atlas** is used as the database for this application.
- By default, MongoDB runs locally on port `27017`. Ensure MongoDB is running before starting the backend.
- To make the database accessible for testing and running from various environments, the MongoDB connection URI has been modified to allow connections from all IP addresses by setting it to `0.0.0.0/0`. This allows external connections to the database during development and testing.

    ```plaintext
    mongodb://<username>:<password>@cluster0.mongodb.net/your_database?retryWrites=true&w=majority
    ```

- **Important:** Make sure to change this back to a more secure setting for production deployments to restrict access to authorized IP addresses only.


## API Endpoints

### Authentication

- **POST `/api/auth/register`**: Register a new user.
- **POST `/api/auth/login`**: Login and get a JWT token.

### Loan Management

- **GET `/api/loans`**: Get all loans .
- **POST `/api/loans`**: Apply for a loan .
- **GET `/api/loans/{id}`**: Get loan details by ID.
- **PUT `/api/loans/{id}`**: Admin can approve a loan (Admin only).

### Repayment Management

- **GET `/api/repayments`**: Get all repayment .
- **POST `/api/repayments`**: Submit a repayment .

## Testing

The backend has been thoroughly tested using **JUnit** and **Mockito** to ensure reliable functionality and high code quality.

- **Backend Test Coverage**: The backend has been tested with **JUnit** and **Mockito**, and the code coverage is **over 87%**.
- Test cases include:
  - **Controller tests** to validate API responses.
  - **Service tests** to verify business logic.
  - **Mocking dependencies** using **Mockito** to simulate data and test individual components.

To run backend tests:

```bash
./mvnw test
```

To run frontend tests:

```bash
npm test
```
