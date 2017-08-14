package Models;

import java.io.Serializable;

/**
 * Created by shafi on 7/21/2017.
 */

public class Subject implements Serializable {

    private String courseName;
    private String courseCode;
    private String credit;
    private String gradePoint;
    private String grade;
    private int semesterTaken;

    public int getSemesterTaken() {
        return semesterTaken;
    }

    public void setSemesterTaken(int semesterTaken) {
        this.semesterTaken = semesterTaken;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(String gradePoint) {
        this.gradePoint = gradePoint;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
