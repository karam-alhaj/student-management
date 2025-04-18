public class registrationSystem {
    private LinkedList studentsList;
    private LinkedList coursesList;

    private int lastStudentAdded;
    private int lastCourseAdded;

    public registrationSystem() {
        studentsList = new LinkedList();
        coursesList = new LinkedList();
        lastStudentAdded = 0;
        lastCourseAdded = 0;
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
