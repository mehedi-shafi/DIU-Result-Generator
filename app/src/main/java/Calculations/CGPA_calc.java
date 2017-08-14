package Calculations;

import java.util.ArrayList;

import Models.Subject;

/**
 * Created by shafi on 7/21/2017.
 */

public class CGPA_calc {

    private ArrayList<Subject> subjectWiseResults;
    private double[] gradePoints = new double[] {0.0, 2.0, 2.25, 2.50, 2.75, 3.00, 3.25, 3.50, 3.75, 4.00};
    private int[] numRanges = new int[] {0, 40, 45, 50, 55, 60, 65, 70, 75, 80, 100};
    private String[] gradeScale = new String[] {"F", "D", "C", "C+", "B-", "B", "B+", "A-", "A", "A+"};
    private double totalCredit;
    private double cgpa;
    private String grade;

    public CGPA_calc(ArrayList<Subject> data){
        subjectWiseResults = data;
        totalCredit = getTotalCredit();
        cgpa = getCgpa();
        grade = getGrade();
    }

    public double getTotalCredit (){
        double credit = 0;
        int tempSize = totalNumCourseCompleted();

        for (int i = 0; i < tempSize; i++){
            credit += Double.parseDouble(subjectWiseResults.get(i).getCredit());
        }

        return credit;
    }

    public double getCgpa(){
        double cg = 0;
        double cgxcred = 0;
        int tempSize = totalNumCourseCompleted();

        for (int i = 0; i < tempSize; i++){
            cgxcred += (double) (Double.parseDouble(subjectWiseResults.get(i).getGradePoint()) *Double.parseDouble(subjectWiseResults.get(i).getCredit()));
        }
        cg = cgxcred / getTotalCredit();

        return cg;
    }

    public int totalNumCourseCompleted(){
        return subjectWiseResults.size();
    }

    public String getGrade(){
        for (int i = 0; i < (gradePoints.length-1); i ++){
            if (gradePoints[i] <= cgpa && cgpa <= gradePoints[i+1]){
                return gradeScale[i];
            }
        }
        return  null;
    }

    public void getStudentResult(){

        System.out.println ("Total credit: " + totalCredit + "\t Total courses: "  + totalNumCourseCompleted() + "\tCGPA: " + cgpa + "\tGrade: " + grade);

    }


}
