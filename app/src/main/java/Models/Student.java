package Models;

import java.io.Serializable;

/**
 * Created by shafi on 7/21/2017.
 */

public class Student implements Serializable {

    private String name;
    private String id;
    private String program;
    private String batch;
    private String enrollment;
    private boolean stored;

    public Student(){
        stored = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    public void setStudent(Student student){
        this.stored = true;
        this.name = student.getName();
        this.batch = student.getBatch();
        this.enrollment = student.getEnrollment();
        this.id = student.getId();
        this.program = student.getProgram();
    }
}
