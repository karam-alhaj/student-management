# University Course Registration System

A Java-based system that manages students, courses, and enrollments using a sparse, linked-list approach. This implementation stores only active student–course relationships, enabling efficient dynamic operations without static arrays.

---

## 🌟 Key Features

- **Dynamic Student & Course Management**

  - Add or remove students and courses at runtime
  - Track the most recently added student or course

- **Bidirectional Enrollment**

  - Enroll a student in a course (updates both student and course records)
  - Remove an enrollment cleanly from both sides

- **Data Retrieval & Sorting**

  - List all courses for a given student
  - List all students in a given course
  - Sort a student’s courses or a course’s students in ascending order by ID

- **Constraint Validation**

  - Enforce 2–7 courses per student
  - Enforce up to 30 students per course

---

## 📐 Architecture & Design

### 1. LinkedList

A generic singly-linked list where each node stores:

- `int ID` – unique identifier (student or course)
- `Node next` – reference to the next node
- `LinkedList list` – a sub-list for cross-references:
  - If the node represents a **student**, `list` holds that student’s enrolled courses.
  - If the node represents a **course**, `list` holds that course’s enrolled students.

Core methods:

- `add(int id)` / `add(Node newNode)` – append a node
- `remove(int id)` – remove the first node matching `id`
- `contains(int id)` – check existence
- `findNode(int id)` – locate and return a node by ID
- `size()` – count nodes in the list
- `printList()` – output all IDs in order

### 2. UniversitySystem

Coordinates two master `LinkedList` instances:

- `studentsList` – all student nodes
- `coursesList` – all course nodes

Supports:

- **Student & Course Operations**

  - `addStudent(int)` / `removeStudent(int)`
  - `addCourse(int)` / `removeCourse(int)`
  - `getLastStudentAdded()` / `getLastCourseAdded()`

- **Enrollment Operations**

  - `enrollStudent(int studentID, int courseID)`
  - `removeEnrollment(int studentID, int courseID)`

- **Retrieval & Sorting**

  - `listCoursesByStudent(int studentID)`
  - `listStudentsByCourse(int courseID)`
  - `sortCoursesByID(int studentID)`
  - `sortStudentsByID(int courseID)`

- **Constraint Checks**

  - `isNormalStudent(int studentID)` – returns true if 2 ≤ courses ≤ 7
  - `isFullCourse(int courseID)` – returns true if enrolled students ≥ 30

Internally, removal of a student or course cascades through all cross-lists to maintain consistency.

---

## 🗂️ Project Structure

```
/
├── src/
│   ├── LinkedList.java        # Core linked-list implementation
│   ├── UniversitySystem.java  # Manager class with all operations
│   └── Main.java              # Demo entry point showing typical usage
├── doc/
│   └── documentation.pdf      # System design, screenshots, usage notes
└── README.md                  # Project overview and setup instructions
```

---

## ⚙️ Requirements & Setup

- **Java:** JDK 8 or later
- **Build:** Plain `javac` / `java`

1. Clone or download the repository
2. Compile all source files:
   ```bash
   javac src/*.java
   ```
3. Run the demo in `Main`:
   ```bash
   java -cp src Main
   ```

---

## 🚀 Usage Example

```java
UniversitySystem system = new UniversitySystem();

// Add students and courses
system.addStudent(1001);
system.addStudent(1002);
system.addCourse(2001);
system.addCourse(2002);

// Perform enrollments
system.enrollStudent(1001, 2001);
system.enrollStudent(1002, 2001);
system.enrollStudent(1001, 2002);

// List data
system.listCoursesByStudent(1001);   // → 2001 2002
system.listStudentsByCourse(2001);   // → 1001 1002

// Sort and reprint
system.sortStudentsByID(2001);
system.listStudentsByCourse(2001);   // → 1001 1002 ascending

// Check constraints
boolean ok1 = system.isNormalStudent(1001); // true (2 courses)
boolean ok2 = system.isFullCourse(2001);    // false (<30 students)
```

---
