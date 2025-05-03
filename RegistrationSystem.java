public class RegistrationSystem {

    private LinkedList studentsList;
    private LinkedList coursesList;

    private int lastStudentAdded;
    private int lastCourseAdded;
    private int lastStudentRemoved;
    private int lastCourseRemoved;

    private int lastStudentAddedToCourse;
    private int lastStudentRemovedFromCourse;

    private int lastCourseWithStudent;
    private int lastCourseRemoveStudent;

    public RegistrationSystem() {
        studentsList = new LinkedList();
        coursesList = new LinkedList();
        lastStudentAdded = -1;
        lastCourseAdded = -1;
        lastStudentRemoved = -1;
        lastCourseRemoved = -1;
        lastStudentAddedToCourse = -1;
        lastStudentRemovedFromCourse = -1;
        lastCourseWithStudent = -1;
        lastCourseRemoveStudent = -1;
    }

    // Getters remain unchanged

    public void addStudent(int studentID) {
        studentsList.add(new LinkedList.Node(studentID));
        lastStudentAdded = studentID;
        System.out.println("Student added");
        Main.undoRedo.addAction("add-student");
    }

    public void addCourse(int courseID) {
        coursesList.add(new LinkedList.Node(courseID));
        lastCourseAdded = courseID;
        System.out.println("Course added");
        Main.undoRedo.addAction("add-course");
    }

    public void removeStudent(int studentID) {
        studentsList.remove(studentID);
        lastStudentRemoved = studentID;
        LinkedList.Node courseHead = coursesList.getHead();
        while (courseHead != null) {
            courseHead.list.remove(studentID);
            courseHead = courseHead.next;
        }
        Main.undoRedo.addAction("remove-student");
    }

    public void removeCourse(int courseID) {
        coursesList.remove(courseID);
        lastCourseRemoved = courseID;
        LinkedList.Node studentHead = studentsList.getHead();
        while (studentHead != null) {
            studentHead.list.remove(courseID);
            studentHead = studentHead.next;
        }
        System.out.println("Course removed");
        Main.undoRedo.addAction("remove-course");
    }

    public void enrollStudentToCourse(int studentID, int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        if (courseNode != null && studentNode != null) {
            courseNode.list.add(new LinkedList.Node(studentID));
            studentNode.list.add(new LinkedList.Node(courseID));
            lastStudentAddedToCourse = studentID;
            lastCourseWithStudent = courseID;
            Main.undoRedo.addAction("enroll");
            System.out.println("Student Enrolled");
        } else {
            System.out.println("Student or course not found in the system");
        }
    }

    public void removeStudentFromCourse(int studentID, int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        if (courseNode != null && studentNode != null) {
            courseNode.list.remove(studentID);
            studentNode.list.remove(courseID);
            lastStudentRemovedFromCourse = studentID;
            lastCourseRemoveStudent = courseID;
            System.out.println("Removed Student From Course");
            Main.undoRedo.addAction("unenroll");
        } else {
            System.out.println("Course or student not found.");
        }
    }

    public void listCoursesByStudent(int studentID) {
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        if (studentNode != null) {
            System.out.println("Courses for student " + studentID + ":");
            studentNode.list.printList();
        } else {
            System.out.println("Student not found.");
        }
    }

    public void listStudentsByCourse(int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (courseNode != null) {
            System.out.println("Students enrolled in course " + courseID + ":");
            courseNode.list.printList();
        } else {
            System.out.println("Course not found!");
        }
    }

    private void sortLinkedList(LinkedList list) {
        if (list.getHead() == null || list.getHead().next == null) return;

        boolean swapped;
        do {
            swapped = false;
            LinkedList.Node prev = null;
            LinkedList.Node curr = list.getHead();
            LinkedList.Node next = curr.next;

            while (next != null) {
                if (curr.ID > next.ID) {
                    // Swap curr and next nodes
                    swapped = true;
                    if (prev != null) {
                        prev.next = next;
                    } else {
                        list.setHead(next);
                    }
                    curr.next = next.next;
                    next.next = curr;

                    // Update prev for next iteration
                    prev = next;
                    next = curr.next;
                } else {
                    prev = curr;
                    curr = next;
                    next = next.next;
                }
            }
        } while (swapped);
    }

    public void sortCoursesByID(int studentID) {
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        if (studentNode != null) {
            sortLinkedList(studentNode.list);
            System.out.println("Courses sorted by ID for student " + studentID + ":");
            studentNode.list.printList();
        } else {
            System.out.println("Student not found!");
        }
    }

    public void sortStudentsByID(int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (courseNode != null) {
            sortLinkedList(courseNode.list);
            System.out.println("Students sorted by ID for course " + courseID + ":");
            courseNode.list.printList();
        } else {
            System.out.println("Course not found!");
        }
    }

    public boolean isFullCourse(int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        return courseNode != null && courseNode.list.size() >= 30;
    }

    public boolean isNormalStudent(int studentID) {
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        return studentNode != null && studentNode.list.size() >= 2 && studentNode.list.size() <= 7;
    }


    public int getLastStudentAdded() {
        return lastStudentAdded;
    }

    public int getLastCourseAdded() {
        return lastCourseAdded;
    }

    public int getLastStudentRemoved() {
        return lastStudentRemoved;
    }

    public int getLastCourseRemoved() {
        return lastCourseRemoved;
    }

    public int getLastStudentAddedToCourse() {
        return lastStudentAddedToCourse;
    }

    public int getLastStudentRemovedFromCourse() {
        return lastStudentRemovedFromCourse;
    }

    // Getters for the new fields tracking course context
    public int getLastCourseWithStudent() {
        return lastCourseWithStudent;
    }

    public int getLastCourseRemoveStudent() {
        return lastCourseRemoveStudent;
    }
}