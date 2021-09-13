import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import java.util.*;
import javafx.scene.control.Alert.*;
import javax.swing.JOptionPane;

public class StudentManagementSys extends Application {

    Button addStudentRecordBtn;
    Button removeStudentRecordBtn;
    Button listAllStudentRecordsBtn;
    Button updateStudentRecordBtn;
    Button findStudentRecordBtn;
    Button clearTextBoardBtn;
    Button clearStudentDatabaseBtn;
    Button exitButton;

    protected Map<Integer, StudentInformations> studentDatabase = new HashMap<Integer, StudentInformations>();
    protected StudentInformations latestStudent;
    protected TextArea outputArea;
    protected Alert confirmationMsg, errorMsg, successMsg, informationMsg, warningMsg;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GetAction eventSource = new GetAction();

        addStudentRecordBtn = new Button("Add Student");
        addStudentRecordBtn.setOnAction(e -> addStudentRecord());

        removeStudentRecordBtn = new Button("Remove Student");
        removeStudentRecordBtn.setOnAction(e -> removeStudentRecord());

        listAllStudentRecordsBtn = new Button("List Students");
        listAllStudentRecordsBtn.setOnAction(e -> listStudentRecords());

        updateStudentRecordBtn = new Button("Update Student Record");
        updateStudentRecordBtn.setOnAction(e -> updateStudentRecord());

        findStudentRecordBtn = new Button("Find Student Record");
        findStudentRecordBtn.setOnAction(e -> findStudentRecord());

        clearTextBoardBtn = new Button("Clear Text Area");
        clearTextBoardBtn.setOnAction(eventSource);

        clearStudentDatabaseBtn = new Button("Clear Student Database");
        clearStudentDatabaseBtn.setOnAction(eventSource);

        exitButton = new Button("Exit Program");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                confirmationMsg = new Alert(AlertType.CONFIRMATION, "Do you want to quit the application?");

                if (confirmationMsg.showAndWait().get() == ButtonType.OK) {
                    System.exit(0);
                }
            }
        });

        VBox pane = new VBox(15);

        BorderPane pane1 = new BorderPane();

        Scene scene;

        outputArea = new TextArea();
        outputArea.setMaxSize(500, 500);
        outputArea.setEditable(false);

        pane1.setCenter(outputArea);

        pane.getChildren().addAll(pane1, addStudentRecordBtn, removeStudentRecordBtn, listAllStudentRecordsBtn,
                updateStudentRecordBtn, findStudentRecordBtn, clearTextBoardBtn, clearStudentDatabaseBtn, exitButton);

        scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Management Application");
        primaryStage.show();

    }

    private class GetAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == clearTextBoardBtn) {
                confirmationMsg = new Alert(AlertType.CONFIRMATION, "Clear the text area?");

                if (confirmationMsg.showAndWait().get() == ButtonType.OK) {
                    outputArea.clear();

                    successMsg = new Alert(AlertType.INFORMATION, "Successfully cleared the text area");
                }
            }

            else if (e.getSource() == clearStudentDatabaseBtn) {

                if (studentDatabase.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Database is already empty!");
                }

                else {
                    confirmationMsg = new Alert(AlertType.CONFIRMATION, "Do you want to clear the database?");

                    if (confirmationMsg.showAndWait().get() == ButtonType.OK) {
                        outputArea.clear();
                        studentDatabase.clear();

                        warningMsg = new Alert(AlertType.WARNING, "Cleared the database!");

                        warningMsg.show();
                    }
                }
            }

        }
    }

    public void addStudentRecord() {
        String sFirstName;
        String sLastName;
        String sGender;
        String sRace;

        int sGrade;
        int sAge;
        int studentID;

        do {
            try {
                studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter studentID"));

                if (studentDatabase.containsKey(studentID)) {
                    throw new Exception("StudentID already exists!");
                }

                if (studentID <= 0) {
                    throw new Exception("Invalid StudentID");

                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        do {
            try {
                sFirstName = JOptionPane.showInputDialog("Enter first name");

                if (sFirstName.length() == 0) {
                    throw new Exception("First name cannot be blank");
                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        do {
            try {
                sLastName = JOptionPane.showInputDialog("Enter last name");

                if (sLastName.length() == 0) {
                    throw new Exception("Last name cannot be blank");
                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        do {
            try {
                sGender = JOptionPane.showInputDialog("Enter gender");

                if (sGender.length() == 0) {
                    throw new Exception("Gender cannot be blank");
                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        do {
            try {
                sRace = JOptionPane.showInputDialog("Enter race");

                if (sRace.length() == 0) {
                    throw new Exception("Race cannot be blank");
                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        do {
            try {
                sGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter grade"));

                if (sGrade <= 0) {
                    throw new Exception("Invalid grade");
                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        do {
            try {
                sAge = Integer.parseInt(JOptionPane.showInputDialog("Enter age"));

                if (sAge <= 0) {
                    throw new Exception("Invalid age");
                }

                break;
            }

            catch (Exception excpt) {
                JOptionPane.showMessageDialog(null, excpt.getMessage());
            }

            continue;

        } while (true);

        confirmationMsg = new Alert(AlertType.CONFIRMATION, "Proceed with adding new student record?");

        if (confirmationMsg.showAndWait().get() == ButtonType.OK) {
            latestStudent = new StudentInformations(studentID, sFirstName, sLastName, sGender, sRace, sAge, sGrade);

            studentDatabase.put(studentID, latestStudent);

            if (studentDatabase.containsKey(studentID)) {
                successMsg = new Alert(AlertType.INFORMATION, "Successfully added student records!");

                successMsg.showAndWait();
            }

            else {
                errorMsg = new Alert(AlertType.ERROR, "Failed to add student records");

                errorMsg.showAndWait();
            }

        }

    }

    public void removeStudentRecord() {

        if (studentDatabase.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database is empty! Nothing to remove.");
        }

        else {
            int studentID = Integer
                    .parseInt(JOptionPane.showInputDialog("Enter studentID to remove student" + " records"));

            if (studentDatabase.containsKey(studentID)) {
                latestStudent = studentDatabase.get(studentID);

                studentDatabase.remove(studentID, latestStudent);

                successMsg = new Alert(AlertType.INFORMATION, "Successfully removed studentID: " + studentID);

                successMsg.show();
            }

            else {
                errorMsg = new Alert(AlertType.INFORMATION, "Could not find studentID. Student record not removed");

                errorMsg.show();
            }
        }

    }

    public void listStudentRecords() {
        outputArea.setText("\n\t\t\t\t------------ STUDENT DATABASE -------------");

        StringBuilder builder = new StringBuilder();

        if (studentDatabase.isEmpty()) {
            errorMsg = new Alert(AlertType.ERROR, "Database is empty!");

            errorMsg.show();
        } else {
            for (Map.Entry<Integer, StudentInformations> entry : studentDatabase.entrySet()) {
                builder.append("\n");
                builder.append("\nStudent Information:");
                builder.append("\nStudentID: " + entry.getKey());
                builder.append("\nFirstname: " + entry.getValue().getStudentFirstN());
                builder.append("\nLastname: " + entry.getValue().getStudentLastN());
                builder.append("\nGender: " + entry.getValue().getStudentG());
                builder.append("\nRace: " + entry.getValue().getStudentR());
                builder.append("\nAge: " + entry.getValue().getStudentA());
                builder.append("\nGrade: " + entry.getValue().getStudentG());
                builder.append("\n");

            }
        }
        outputArea.appendText(builder.toString());
    }

    public void updateStudentRecord() {
        String sFirstName;
        String sLastName;
        String sGender;
        String sRace = "";

        int sGrade;
        int sAge;
        int studentID;

        if (studentDatabase.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database is empty! Nothing to update!");
        }

        else {
            studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter studentID to update student record"));

            if (studentDatabase.containsKey(studentID)) {
                latestStudent = studentDatabase.get(studentID);

                confirmationMsg = new Alert(AlertType.CONFIRMATION, "Proceed to update the record of "
                        + latestStudent.getStudentFirstN() + " " + latestStudent.getStudentLastN() + "?");

                if (confirmationMsg.showAndWait().get() == ButtonType.OK) {
                    studentDatabase.remove(studentID, latestStudent);

                    do {
                        try {
                            studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter studentID"));

                            if (studentDatabase.containsKey(studentID)) {
                                throw new Exception("StudentID already exits!");
                            }

                            if (studentID <= 0) {
                                throw new Exception("Invalid StudentID");

                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        continue;

                    } while (true);

                    do {
                        try {
                            sFirstName = JOptionPane.showInputDialog("Enter first name");

                            if (sFirstName.length() == 0) {
                                throw new Exception("First name cannot be blank");
                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        continue;

                    } while (true);

                    do {
                        try {
                            sLastName = JOptionPane.showInputDialog("Enter last name");

                            if (sLastName.length() == 0) {
                                throw new Exception("Last name cannot be blank");
                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        continue;

                    } while (true);

                    do {
                        try {
                            sGender = JOptionPane.showInputDialog("Enter gender");

                            if (sGender.length() == 0) {
                                throw new Exception("Gender cannot be blank");
                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        continue;

                    } while (true);

                    do {
                        try {
                            sRace = JOptionPane.showInputDialog("Enter race");

                            if (sRace.length() == 0) {
                                throw new Exception("Race cannot be blank");
                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        break;

                    } while (true);

                    do {
                        try {
                            sGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter grade"));

                            if (sGrade <= 0) {
                                throw new Exception("Invalid grade");
                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        continue;

                    } while (true);

                    do {
                        try {
                            sAge = Integer.parseInt(JOptionPane.showInputDialog("Enter age"));

                            if (sAge <= 0) {
                                throw new Exception("Invalid age");
                            }

                            break;
                        }

                        catch (Exception excpt) {
                            JOptionPane.showMessageDialog(null, excpt.getMessage());
                        }

                        continue;

                    } while (true);

                    latestStudent = new StudentInformations(studentID, sFirstName, sLastName, sGender, sRace, sAge,
                            sGrade);

                    studentDatabase.put(studentID, latestStudent);

                    successMsg = new Alert(AlertType.INFORMATION, "Successfully updated " + "the student record!");

                    successMsg.show();
                } else {
                    errorMsg = new Alert(AlertType.ERROR, "Could not find studentID");

                    errorMsg.show();
                }
            }
        }

    }

    public void findStudentRecord() {

        if (studentDatabase.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database is empty! No students to find.");
        }

        else {
            int studentID = Integer.parseInt(JOptionPane.showInputDialog("Enter studentID to find student record"));

            if (studentDatabase.containsKey(studentID)) {
                latestStudent = studentDatabase.get(studentID);

                informationMsg = new Alert(AlertType.INFORMATION, "Displaying the student record of "
                        + latestStudent.getStudentFirstN() + " " + latestStudent.getStudentLastN());

                outputArea.setText("\n\t\t\t\t---------- STUDENT RECORD -------------");

                outputArea.appendText("\n" + "\nStudent Information:" + "\nStudentID: " + latestStudent.getStudentID()
                        + "\nFirstname: " + latestStudent.getStudentFirstN() + "\nLastname: "
                        + latestStudent.getStudentLastN() + "\nGender: " + latestStudent.getStudentG() + "\nRace: "
                        + latestStudent.getStudentR() + "\nAge: " + latestStudent.getStudentA() + "\nGrade: "
                        + latestStudent.getStudentG() + "\n");
            }

            else if (!studentDatabase.containsKey(studentID)) {
                errorMsg = new Alert(AlertType.ERROR, "StudentID was not found. Student not in database");

                errorMsg.show();
            }
        }
    }

}
