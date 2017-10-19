package Models;

import java.util.ArrayList;

/**
 * Created by shafi on 10/14/2017.
 */

public class SemesterStorage extends Store {

    private int semesterId = 151;

    public SemesterStorage(int semesterId, ArrayList<Subject>data){
        super();
        addData(data);
        this.semesterId = semesterId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
}
