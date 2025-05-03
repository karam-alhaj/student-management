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
}
