# SmartSched CRM

SmartSched CRM is a console-based Java CRM application built using MVC architecture, designed to efficiently manage clients, appointments, dashboards, and automated reminders.

---

## 🔹 Key Features

* Client Management – Add, edit, view and delete client details
* Appointment Management – Schedule, view and cancel appointments
* Dashboard – View total clients, today’s appointments, and pending reminders
* Automated Reminders – Background reminder system using Java Scheduler
* Clean Architecture – Proper separation of UI, Service, DAO, and Model layers

---

## 🔹 Tech Stack

* Java 21
* MySQL
* JDBC
* MVC Architecture
* ScheduledExecutorService

---

## 🔹 Highlights

* No database logic inside the UI layer
* Background tasks run without blocking the main application flow
* Production-ready database schema
* Clean, readable, interview-ready code structure

---

## 🔹 How to Run

1. Configure MySQL credentials in `Database.java`
2. Add MySQL Connector JAR to the `lib/` folder
3. Run `App.java`

---

## 🚀 Future Enhancements

* 📧 Email Alerts
  Automated email reminders sent to clients before appointments using JavaMail API.

* 👤 User Roles (Admin / Staff)
  Role-based access control to restrict features based on user permissions.

* 📁 Export & Reports
  Generate appointment and client reports in CSV or PDF format for business analysis.

---

## 👤 Author

IBRAHIM SHAIFULLA
B.E. | ECE
