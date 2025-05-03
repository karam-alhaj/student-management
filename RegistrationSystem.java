public class RegistrationSystem {

    private LinkedList studentsList;
    private LinkedList coursesList;

    private int lastStudentAdded;
    private int lastCourseAdded;
    private int lastStudentRemoved;
    private int lastCourseRemoved;

    private int lastStudentAddedToCourse;
    private int lastStudentRemovedFromCourse;

    private int lastCourseWithStudent; // New field to track the last course with a student
    private int lastCourseRemoveStudent; // New field to track the last course with a student

    public RegistrationSystem() {
        studentsList = new LinkedList();
        coursesList = new LinkedList();
        lastStudentAdded = -1;
        lastCourseAdded = -1;
        lastStudentRemoved = -1;
        lastCourseRemoved = -1;
        lastStudentAddedToCourse = -1;
        lastStudentRemovedFromCourse = -1;
        lastCourseWithStudent = -1; // Initialize to -1
        lastCourseRemoveStudent = -1; // Initialize to -1
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

    public int getLastCourseWithStudent() { // New getter for lastCourseWithStudent
        return lastCourseWithStudent;
    }
    public int getLastCourseRemoveStudent() { // New getter for lastCourseRemoveStudent
        return lastCourseRemoveStudent;
    }
    public void addStudent(int studentID) {
        studentsList.add(new LinkedList.Node(studentID));
        lastStudentAdded = studentID;
        Main.undoRedo.addAction("add-student");
    }

    public void addCourse(int courseID) {
        coursesList.add(new LinkedList.Node(courseID));
        lastCourseAdded = courseID;
            Main.undoRedo.addAction("add-course");

    }

    public void removeStudent(int studentID) {
        studentsList.remove(studentID);
        lastStudentRemoved = studentID;
        LinkedList.Node courseHead = coursesList.getHead();
        while (courseHead != null) {
            courseHead.list.remove(studentID);
            courseHead = courseHead.next;
            Main.undoRedo.addAction("remove-student");

        }
    }

    public void removeCourse(int courseID) {
        coursesList.remove(courseID);
        lastCourseRemoved = courseID; 
        LinkedList.Node studentHead = studentsList.getHead();
        while (studentHead != null) {
            studentHead.list.remove(courseID);
            studentHead = studentHead.next;
            Main.undoRedo.addAction("remove-course");

        }
    }

    public void enrollStudentToCourse(int studentID, int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (courseNode != null) {
            courseNode.list.add(studentID);
            lastStudentAddedToCourse = studentID;
            lastCourseWithStudent = courseID; // Update lastCourseWithStudent
            Main.undoRedo.addAction("enroll");
        }
    }

    public void removeStudentFromCourse(int studentID, int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (courseNode != null) {
            courseNode.list.remove(studentID);
            lastStudentRemovedFromCourse = studentID;
            lastCourseRemoveStudent = courseID; // Update lastCourseRemoveStudent
            Main.undoRedo.addAction("unenroll");

        }
    }

    public void Print(){
        System.out.println("Students:");
        studentsList.printList();
        
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
    private void SortLinkedList(LinkedList list) {
        if (list.getHead() == null) return;
        boolean swapped;
        do {
            swapped = false;
            LinkedList.Node curr = list.getHead();
            while (curr.next != null) {
                if (curr.ID > curr.next.ID) {
                    int temp = curr.ID;
                    curr.ID = curr.next.ID;
                    curr.next.ID = temp;
                    swapped = true;
                }
                curr = curr.next;
            }
        } while (swapped);
    }
    public void SortCoursesByID(int studentID) {
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        if (studentNode != null) {
            SortLinkedList(studentNode.list);
            System.out.println("Courses sorted by ID for student " + studentID + ":");
            studentNode.list.printList();
        } else {
            System.out.println("Student not found!");
        }
    }
    public void SortStudentsByID(int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (courseNode != null) {
            SortLinkedList(courseNode.list);
            System.out.println("Students sorted by ID for course " + courseID + ":");
            courseNode.list.printList();
        } else {
            System.out.println("Course not found!");
        }
    }
    public boolean isFullCourse(int courseID) {
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (courseNode != null) {
            return courseNode.list.size() >= 30;
        } else {
            return false;
        }
    }
    public boolean isNormalStudent(int studentID){
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        if(studentNode!=null){
            return (studentNode.list.size() >=2 && studentNode.list.size() <= 7);
        }else{
            return false;
        }
    }
}
