
public class Main {
    public static UndoRedo undoRedo = new UndoRedo();
    public static RegistrationSystem system = new RegistrationSystem();

    public static void main(String[] args) {
        system.addStudent(1);
        
        // undoRedo.undo();
        system.addCourse(1);
        system.addCourse(2);
        system.enrollStudentToCourse(1, 1);
        system.enrollStudentToCourse(1, 2);
        system.listCoursesByStudent(1);
    }
}