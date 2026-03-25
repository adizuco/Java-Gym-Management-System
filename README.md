# Java Gym Management System

A console-based management system for gym operations, built as a team project.

## 🚀 Current State (Presentation Ready)
The project currently has a **minimal runnable version** suitable for demonstration. It includes basic authentication, a menu system, and mock data.

### Features
*   **Authentication**: Secure login loop (Credentials: `admin`/`admin123`).
*   **Member Directory**: View a list of registered gym members (Mock data included).
*   **Attendance Tracking**: Simulated check-in functionality.
*   **Team Workload Division**: All source files are clearly commented with assigned member responsibilities.

## 👥 Team & Responsibilities
*   **Abdulaziz (Leader)**: Core application logic, Authentication, and File Management.
*   **Boburjon**: Member profiles, Memberships, and CRUD operations.
*   **Rahmatulloh**: Trainer management and Class scheduling.
*   **Asomiddin**: Payments, Billing, and Attendance tracking.

## 🛠️ Project Structure
```text
src/
├── Main.java           # Entry point & Menu system
├── models/             # Entity classes (User, Member, Trainer, etc.)
├── services/           # Business logic (AuthService, MemberService, etc.)
└── util/               # Utility classes (FileManager)
```

## 🏃 How to Run
1.  **Compile**:
    ```bash
    javac -d . src/models/*.java src/services/*.java src/Main.java
    ```
2.  **Execute**:
    ```bash
    java Main
    ```

## 📅 Roadmap
- **Phase 1**: Initial skeleton & Presentation version (✅ Completed).
- **Phase 2**: Core business logic implementation.
- **Phase 3**: Data persistence (File I/O).
- **Phase 4**: Integration & Final testing.
