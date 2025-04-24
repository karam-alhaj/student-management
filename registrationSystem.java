public class registrationSystem {
    private LinkedList studentsList;
    private LinkedList coursesList;

    private int lastStudentAdded;
    private int lastCourseAdded;

    public registrationSystem() {
        studentsList = new LinkedList();
        coursesList = new LinkedList();
        lastStudentAdded = -1;
        lastCourseAdded = -1;
    }

    public int getLastStudentAdded() {
        return lastStudentAdded;
    }
    public int getLastCourseAdded() {
        return lastCourseAdded;
    }
      public void addStudent(int studentID) {
// Add to the students list
        studentsList.add(new LinkedList.Node(studentID));
        lastStudentAdded = studentID;
    }
    public void addCourse(int courseID) {
        // Add to the courses list
        coursesList.add(new LinkedList.Node(courseID));
        lastCourseAdded = courseID;
    }
    public void removeStudent(int studentID) {
        studentsList.remove(studentID);
        // remove 'studentID' for each course in coursesList
        LinkedList.Node courseHead = coursesList.getHead();
        while (courseHead != null) {
        	courseHead.list.remove(studentID);  
        	courseHead = courseHead.next;
        }
    }
    public void removeCourse(int courseID) {
        coursesList.remove(courseID);
        // remove 'courseID' For each student in studentsList
        LinkedList.Node studentHead = studentsList.getHead();
        while (studentHead != null) {
        	studentHead.list.remove(courseID);
            studentHead = studentHead.next;
        }
    }
    public void enrollStudent(int studentID, int courseID) {
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (studentNode != null && courseNode != null) {
            studentNode.list.enroll(courseID, new LinkedList.Node(studentID));
            courseNode.list.enroll(studentID, new LinkedList.Node(courseID));
        } else {
            System.out.println("Student or Course not found!");
        }
    }
    public void removeEnrollment(int studentID, int courseID) {
        LinkedList.Node studentNode = studentsList.findNode(studentID);
        LinkedList.Node courseNode = coursesList.findNode(courseID);
        if (studentNode != null && courseNode != null) {
            studentNode.list.remove(courseID);
            courseNode.list.remove(studentID);
        } else {
            System.out.println("Student or Course not found!");
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
