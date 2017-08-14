package Calculations;

import java.io.Serializable;
import java.util.ArrayList;

import Models.Subject;

/**
 * Created by shafi on 8/14/2017.
 */

public class Store implements Serializable{

    private ArrayList<Subject> data;

    public Store(){
        data = new ArrayList<>();
    }

    public void addData(ArrayList<Subject> newData){
        this.data.addAll(newData);
    }

    public ArrayList<Subject> getData(){
        return Utilities.uniqueResults(data);
    }

    public int getDataCount(){
        return data.size();
    }

}
