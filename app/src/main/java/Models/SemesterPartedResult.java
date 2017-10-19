package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shafi on 10/14/2017.
 */

public class SemesterPartedResult implements Serializable {

    private ArrayList<SemesterStorage> semesters = new ArrayList<>();

    public SemesterPartedResult(){

    }

    public void addSemester(SemesterStorage data){
        this.semesters.add(data);
    }

    public ArrayList<SemesterStorage> getSemesters(){
        return semesters;
    }

}
