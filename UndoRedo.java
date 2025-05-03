import java.util.Stack;

public class UndoRedo {
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    

    public void addAction(String action) {
        undoStack.push(action);
    }
    public void undo(){
        String action = undoStack.pop();

        switch (action) {
            case "add-student" -> // remove the last added student
                Main.system.removeStudent(Main.system.getLastStudentAdded());
            case "remove-student" -> // undo the remove action
                Main.system.addStudent(Main.system.getLastStudentRemoved());
            // add back the last removed student or course
            case "add-course" -> // remove the last added course
                Main.system.removeCourse(Main.system.getLastCourseAdded());
            case "remove-course" -> // undo the remove action
                Main.system.addCourse(Main.system.getLastCourseRemoved());
            case "enroll" -> // remove the last enrolled student
                Main.system.removeStudentFromCourse(Main.system.getLastStudentAdded(),Main.system.getLastCourseWithStudent());
            case "unenroll" -> // undo the unenroll action
                Main.system.enrollStudentToCourse(Main.system.getLastStudentRemoved(),Main.system.getLastCourseRemoveStudent());
            default -> {
            }
        }
        redoStack.push(action);
    }
    public void redo(){
     String action = redoStack.pop();
        switch (action) {
            case "add-studet" -> Main.system.addStudent(Main.system.getLastStudentRemoved());
            case "remove-student" -> Main.system.removeStudent(Main.system.getLastStudentAdded());
            case "add-course" -> Main.system.addCourse(Main.system.getLastCourseRemoved());
            case "remove-course" -> Main.system.removeCourse(Main.system.getLastCourseAdded());
            case "enroll" -> Main.system.enrollStudentToCourse(Main.system.getLastStudentRemoved(), Main.system.getLastCourseRemoveStudent());
            case "unenroll" -> Main.system.removeStudentFromCourse(Main.system.getLastStudentAdded(), Main.system.getLastCourseWithStudent());
            default -> {
            }
        }
    undoStack.push(action);
    }

}