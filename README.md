# Student Management System

A full-stack college Student Management System built with **Spring Boot 3
+ MongoDB** on the backend and **HTML5 / CSS3 / JavaScript / Bootstrap 5**
on the frontend. Built for a college final-year / internship project demo,
running entirely on **Windows 10/11 with Visual Studio Code**.

---

## 1. Project Overview

Manage students, staff, faculty, departments, courses, subjects,
attendance, fees, exams, results, library, hostel, transport, timetable,
leave requests, notices, events, placements and reports — all from one
role-based web dashboard.

## 2. Features

- Session-based login with BCrypt-hashed passwords (Spring Security)
- 5 roles: `SUPER_ADMIN`, `ADMIN`, `STAFF`, `FACULTY`, `STUDENT`, each with
  different access levels
- Full CRUD (Create/Read/Update/Delete) for every module, backed by real
  REST APIs and MongoDB — no hard-coded fake data
- Search, sort, filter and pagination on every list screen
- Dashboard with live totals and attendance/fees charts
- Attendance percentage calculation with a below-minimum warning
- Automatic fee pending-amount and payment-status calculation
- Automatic grade/GPA calculation and a marksheet view for results
- Library issue/return workflow with automatic stock tracking and fines
- Hostel room allocation with automatic bed-count tracking
- Leave request submission + approve/reject workflow
- Student photo upload (validated file type & size)
- Reports screen with Print and CSV (Excel-compatible) export
- Global JSON error handling (404 / 409 / 403 / 400 / 500)

## 3. Technology Stack

**Frontend:** HTML5, CSS3, JavaScript (vanilla, `fetch()`), Bootstrap 5,
Bootstrap Icons, Chart.js (dashboard charts only)

**Backend:** Java 21, Spring Boot 3.3, Spring MVC, Spring Security,
Spring Data MongoDB, Maven, REST API

**Database:** MongoDB (via MongoDB Compass for admin/import)

**IDE:** Visual Studio Code

> This project uses **MongoDB only**. There is no MySQL, no JDBC, no JPA/
> Hibernate anywhere in the codebase.

## 4. Required Software (Windows)

Install all of these before you start:

1. **Java Development Kit (JDK) 21**
   https://adoptium.net/ (choose Windows x64 .msi installer)
2. **Apache Maven**
   https://maven.apache.org/download.cgi (download the Binary zip, unzip
   to e.g. `C:\Program Files\Apache\maven`, then add `C:\Program
   Files\Apache\maven\bin` to your `PATH` environment variable)
3. **MongoDB Community Server** + **MongoDB Compass**
   See `mongodb-setup.md` in this project for full step-by-step
   instructions.
4. **Visual Studio Code**
   https://code.visualstudio.com/

### Verify installations

Open **Command Prompt** or **PowerShell** and run:

```
java -version
mvn -version
mongosh --version
```

If any command says `'java' is not recognized...` (or similar for `mvn`/
`mongosh`), that program's install folder was not added to your `PATH`
environment variable. Search "Edit environment variables for your
account" in the Windows Start menu, edit the `Path` variable under "User
variables", add the missing folder, then **close and reopen** your
terminal and try again.

## 5. Project Setup

1. Unzip this project anywhere, e.g. `C:\Projects\student-management-system`.
2. Open the folder in VS Code: `File > Open Folder...`
3. Install these VS Code extensions (Extensions panel, `Ctrl+Shift+X`):
   - **Extension Pack for Java**
   - **Spring Boot Extension Pack**
   - **Debugger for Java**
   - **Maven for Java**
4. Set up MongoDB following `mongodb-setup.md`.

## 6. Configuration

`src/main/resources/application.properties` is already configured for a
default local MongoDB install:

```properties
spring.application.name=student-management-system
server.port=8080
spring.data.mongodb.uri=mongodb://localhost:27017/student_management_system
```

You normally don't need to change anything here unless your MongoDB runs
on a different host/port.

## 7. How to Run the Application

Make sure MongoDB is running first (see `mongodb-setup.md`, step 3).

### Option A: Terminal (recommended for first run)

Open a terminal in the project's root folder (the one with `pom.xml`) and
run:

```
mvn clean install
mvn spring-boot:run
```

### Option B: VS Code

Open `StudentManagementSystemApplication.java`
(`src/main/java/com/example/studentmanagement/StudentManagementSystemApplication.java`)
and click the **Run** button that appears above the `main` method (this
needs the Java extensions from step 5 above).

### Other Maven commands you may need

```
mvn clean       # remove previous build output
mvn compile     # compile only
mvn test        # run tests
mvn package     # build a runnable JAR into target/
```

## 8. Application URL

Once running, open your browser at:

```
http://localhost:8080
```

You'll be redirected to the login page automatically.

## 9. Default Login Credentials

These 4 demo accounts are **created automatically** the first time the
application starts (see `DataInitializer.java`) — you don't need to
import anything for these to work:

| Role         | Email                  | Password     |
|--------------|-------------------------|---------------|
| Super Admin  | admin@example.com       | Admin@123     |
| Staff        | staff@example.com       | Staff@123     |
| Faculty      | faculty@example.com     | Faculty@123   |
| Student      | student@example.com     | Student@123   |

New students can also self-register from the login page ("Create an
account" link) — self-registration always creates a `STUDENT` account.
Staff/Faculty/Admin accounts are meant to be created by an administrator
(a future enhancement would be a dedicated "Manage Users" screen).

## 10. MongoDB Collections

```
users, students, staff, faculty, departments, courses, subjects,
attendance, fees, exams, results, books, library_transactions,
hostels, rooms, hostel_allocations, buses, transport_allocations,
timetables, leaves, notices, events, placements
```

Sample data for all of these (except `users`, which is auto-seeded) is
provided in the `mongodb/` folder as one JSON file per collection. See
`mongodb-setup.md` for exact import steps using Compass.

## 11. Project Structure

```
student-management-system/
├── pom.xml
├── README.md
├── mongodb-setup.md
├── mongodb/                     <- sample data, one JSON file per collection
│
└── src/main/
    ├── java/com/example/studentmanagement/
    │   ├── controller/          <- REST controllers (one per module)
    │   ├── service/             <- business logic
    │   ├── repository/          <- Spring Data MongoDB repositories
    │   ├── model/                <- @Document entities
    │   ├── dto/                  <- request/response objects
    │   ├── security/             <- Spring Security config & user details
    │   ├── config/               <- app-level config, demo user seeding
    │   └── exception/            <- global error handling
    │
    └── resources/
        ├── application.properties
        └── static/               <- frontend (HTML/CSS/JS), served directly
            ├── *.html            <- one page per module
            ├── css/style.css
            └── js/               <- one script per module + shared helpers
```

## 12. REST API Overview

Every module follows the same REST pattern, e.g. for Students:

```
GET    /api/students
GET    /api/students/{id}
POST   /api/students
PUT    /api/students/{id}
DELETE /api/students/{id}
```

The same 5 endpoints exist for: `staff`, `faculty`, `departments`,
`courses`, `subjects`, `attendance`, `fees`, `exams`, `results`,
`notices`, `events`, `placements`, `leaves`, `timetable`,
`library/books`, `library/transactions`, `hostel/hostels`,
`hostel/rooms`, `hostel/allocations`, `transport/buses`,
`transport/allocations`.

Plus a few extra convenience endpoints:

```
GET  /api/dashboard/summary                       - dashboard stats
GET  /api/attendance/student/{id}/percentage       - attendance %
GET  /api/results/student/{id}/marksheet           - marksheet + CGPA
POST /api/library/transactions/issue?bookId=&studentId=
PUT  /api/library/transactions/{id}/return
PUT  /api/leaves/{id}/approve?approvedBy=
PUT  /api/leaves/{id}/reject?approvedBy=
POST /api/upload/student-photo   (multipart/form-data, field name "file")
POST /api/auth/login | /logout | /register | /change-password
GET  /api/auth/me
```

## 13. Authorization Summary

- **SUPER_ADMIN / ADMIN**: full access to everything
- **STAFF**: manage students (read), attendance, fees, notices
- **FACULTY**: attendance, subjects, exams, results, timetable
- **STUDENT**: read access to most modules, can submit their own leave
  requests

Authorization rules live in `security/SecurityConfig.java`. As a
simplification for this project, most `GET` endpoints are open to any
authenticated user (a real production system would additionally filter
each student's own records server-side).

## 14. Troubleshooting

**`'mvn' is not recognized as an internal or external command`**
Maven's `bin` folder isn't in your `PATH`. See step 4 above.

**App fails to start with a Mongo connection error**
MongoDB isn't running. Open Compass and try connecting to
`mongodb://localhost:27017` — if that fails, follow `mongodb-setup.md`
step 3 to start the MongoDB service.

**Port 8080 already in use**
Another program is using port 8080. Either stop that program, or change
`server.port` in `application.properties` to something else (e.g. `8081`)
and restart.

**Login page keeps redirecting back to itself**
Clear your browser cookies for `localhost:8080`, or try in a private/
incognito window — an old session cookie may be stuck.

**Uploaded student photos don't show up**
Photos are saved to an `uploads/students/` folder created next to where
you run `mvn spring-boot:run` from. Make sure you're running the command
from the project root every time, so the folder stays in the same place.

**"This is a college demo project" - security note**
CSRF protection is disabled for the `/api/**` endpoints and CORS is
scoped to `localhost` to keep the beginner-friendly `fetch()`-based
frontend simple. This is an accepted simplification for a local/demo
project — a production deployment would need a tighter security review.

---

Happy building! 🎓
