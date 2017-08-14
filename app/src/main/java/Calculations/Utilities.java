package Calculations;

import java.util.ArrayList;

import Models.Subject;

/**
 * Created by shafi on 7/21/2017.
 */

public class Utilities {

    public static final String RESULT_URL= "http://studentportal.diu.edu.bd/result";
    public static String htmlData = "";

    public static String semester_id_generation (int year, int semester){

        String yearString = String.valueOf(year);
        String semesterString = String.valueOf(semester);
        String semester_id = String.valueOf(yearString.charAt(2));
        semester_id += yearString.charAt(3);
        semester_id  += semesterString;

        return semester_id;
    }

    public static int numberSemester (String from, String to){
        int frm = Integer.parseInt(from);
        int t = Integer.parseInt(to);
        int semCount = 0;
        for (int i = frm; i <= t; i++){
            semCount++;
            if (i % 10 == 3) i += 7;
        }
        return semCount;
    }

    public static ArrayList<Subject> uniqueResults (ArrayList<Subject> dump){
        int len = dump.size();

        for (int i = 0; i < dump.size(); i++){
            for (int j = i + 1; j < dump.size(); j++){
                if (dump.get(i).getCourseCode().equals(dump.get(j).getCourseCode())){
                    if (Double.parseDouble(dump.get(i).getGradePoint()) < Double.parseDouble(dump.get(j).getGradePoint())){
                        dump.remove(i);
                    }
                    else if (Double.parseDouble(dump.get(i).getGradePoint()) == Double.parseDouble(dump.get(j).getGradePoint())){
                        dump.remove(i);
                    }
                    else{
                        dump.remove(j);
                    }
                }
            }
        }
        return dump;
    }

}
