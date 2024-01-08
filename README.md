# Cube Assignment: Task Manager Application

This is a simple web application that allows users to manage a list of tasks.

## Screenshots 


![Screenshot 2024-01-08 115446](https://github.com/anmolvns/cube-assignment/assets/94983418/2ecb8b6e-070b-467b-9a9d-bb580b962717)

![Screenshot 2024-01-08 115546](https://github.com/anmolvns/cube-assignment/assets/94983418/fe4dc1a3-c7e7-449c-b44c-d28f9675ac97)

![Screenshot 2024-01-08 115641](https://github.com/anmolvns/cube-assignment/assets/94983418/0eefbfc9-6f99-4048-801a-165e15a3ebd0)

![Screenshot 2024-01-08 115715](https://github.com/anmolvns/cube-assignment/assets/94983418/45701220-89dc-4706-84c8-6467f4018e91)

![Screenshot 2024-01-08 115825](https://github.com/anmolvns/cube-assignment/assets/94983418/3bdfdba9-3b10-4219-8794-117bae9e8b03)

![image](https://github.com/anmolvns/cube-assignment/assets/94983418/1b923983-aa5d-4aa5-8d25-272527bf804f)

![Screenshot 2024-01-08 115909](https://github.com/anmolvns/cube-assignment/assets/94983418/b9350761-d8ba-4625-9918-e8ae29a8a98d)

## Prerequisites

Before you begin, make sure you have the following software installed on your machine:

- [Node.js](https://nodejs.org/)
- [npm](https://www.npmjs.com/) (Node Package Manager)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [PostgreSQL](https://www.postgresql.org/)

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/anmolvns/cube-assignment.git

2. **Change into the project directory:**
    ```bash
    cd cube-assignment
    
3. **Configure Database:**
    - Open PostgreSQL and create a database named `cube`.
    - Update the database connection configuration in
    - `src/main/resources/application.properties` with your PostgreSQL credentials:
      ```application.properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/cube?currentSchema=cube
      spring.datasource.username=your_database_username
      spring.datasource.password=your_database_password
      spring.datasource.driver-class-name=org.postgresql.Driver
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
      spring.jpa.show-sql=true

4. **Install Backend Dependencies:**
    - Using Maven.

## Running the Application

1. **Start the Spring Boot Backend:**

   -The backend will be accessible at http://localhost:8080.

3. **Start the React Frontend:**
    ```bash
    cd frontend
    npm start

  -The frontend will be accessible at http://localhost:3000.
  

3. **Open your web browser and navigate to http://localhost:3000 to use the Task Manager application.**

## Additional Configuration

  - You can configure the application further by modifying
  - `src/main/resources/application.properties` for backend configurations.
  - Update frontend API endpoint in `frontend/src/api.js` if your backend is running
    on a different URL.

## Feedback

Feel free to reach out to the project owner Anmol Vishwakarma with any feedback or issues.

Happy coding!

```vbnet
Make sure to replace placeholders like `your_database_username` and `your_database_password` with your actual database credentials. Adjust any other details according to your project's specifics.
