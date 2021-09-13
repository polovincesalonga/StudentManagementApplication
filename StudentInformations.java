public class StudentInformations {

    private String studentFirstName;
    private String studentLastName;
    private String studentGender;
    private String studentRace;

    private int studentID;
    private int studentAge;
    private int studentGrade;

    public StudentInformations(int studentID, String studentFirstName, String studentLastName, String studentGender,
            String studentRace, int studentAge, int studentGrade) {
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentGender = studentGender;
        this.studentRace = studentRace;
        this.studentAge = studentAge;
        this.studentGrade = studentGrade;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentFirstN(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentFirstN() {
        return studentFirstName;
    }

    public void setStudentLastN(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentLastN() {
        return studentLastName;
    }

    public void setStudentG(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentG() {
        return studentGender;
    }

    public void setStudentR(String studentRace) {
        this.studentRace = studentRace;
    }

    public String getStudentR() {
        return studentRace;
    }

    public void setStudentA(int studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentA() {
        return studentAge;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

}
