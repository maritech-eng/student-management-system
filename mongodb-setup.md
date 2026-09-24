# MongoDB Setup Guide (Windows 10/11)

This guide walks you through installing MongoDB, starting it, and loading the
sample data for the Student Management System.

## 1. Install MongoDB Community Server

1. Go to https://www.mongodb.com/try/download/community
2. Choose **Windows**, package **msi**, and download the installer.
3. Run the installer:
   - Choose **Complete** setup type.
   - When prompted, leave **"Install MongoDB as a Service"** checked (this
     means MongoDB starts automatically in the background — you won't need
     to start it manually every time).
   - You do **not** need to install MongoDB Compass from this installer if
     you plan to install it separately (see step 2).
4. Finish the installation.

## 2. Install MongoDB Compass

1. Go to https://www.mongodb.com/try/download/compass
2. Download and install the Windows version.
3. Open Compass after installation.

## 3. Start MongoDB

If you installed MongoDB as a Windows Service (default), it is **already
running** in the background. You can check with:

```
sc query MongoDB
```

If it's not running, start it with:

```
net start MongoDB
```

If `net start MongoDB` says the service doesn't exist, you can start the
server manually instead (open a terminal and run):

```
"C:\Program Files\MongoDB\Server\7.0\bin\mongod.exe" --dbpath="C:\data\db"
```

(Create the `C:\data\db` folder first if it doesn't exist, or point
`--dbpath` at any folder you like.)

## 4. Connect using Compass

1. Open MongoDB Compass.
2. In the connection string field, enter:
   ```
   mongodb://localhost:27017
   ```
3. Click **Connect**.

## 5. Create the database

1. In Compass, click **"Create Database"**.
2. Database name: `student_management_system`
3. Collection name: `students` (you can add the rest below - Compass
   requires at least one collection to create the database).
4. Click **Create Database**.

## 6. Create the remaining collections

You can let Spring Boot create empty collections automatically the first
time it saves a document to them (Spring Data MongoDB does this for you),
or create them manually in Compass now. Suggested collection names:

```
users, students, staff, faculty, departments, courses, subjects,
attendance, fees, exams, results, books, library_transactions,
hostels, rooms, hostel_allocations, buses, transport_allocations,
timetables, leaves, notices, events, placements
```

## 7. Import the sample JSON data

The `mongodb/` folder in this project has one JSON file per collection
(e.g. `students.json`, `departments.json`, etc).

For each file:

1. In Compass, open the `student_management_system` database.
2. Click on the collection with the matching name (e.g. click on
   `students` collection for `students.json`). If the collection doesn't
   exist yet, click **"Create Collection"** first and name it exactly as
   the file (without `.json`).
3. Click the **"ADD DATA"** button → **"Import File"**.
4. Select the matching `.json` file from the `mongodb/` folder.
5. File type: **JSON**.
6. Click **Import**.

Repeat for every `.json` file in the `mongodb/` folder.

> **Note:** You do NOT need to manually create/import `users` — the
> application automatically creates the 4 demo login accounts
> (admin, staff, faculty, student) the first time you run it. See
> README.md for those credentials.

## 8. Configure Spring Boot (already done for you)

`src/main/resources/application.properties` is already set to:

```
spring.data.mongodb.uri=mongodb://localhost:27017/student_management_system
```

If your MongoDB runs on a different port or requires authentication,
update this line accordingly.

## 9. Run the application

From the project root, in a terminal (see README.md for full details):

```
mvn spring-boot:run
```

Then open http://localhost:8080 in your browser.
