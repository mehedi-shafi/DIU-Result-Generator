package Code;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import Models.Student;
import Models.Subject;

/**
 * Created by shafi on 7/21/2017.
 */

public class Scrapper {

    private String htmlData;
    private int semesterId;


    public Scrapper(){

    }

    public Scrapper(String data) throws Exception{
        this.htmlData = data;
        if (data == ""){
            Log.v("data", "no data came");
        }
        else{
            System.out.println (data.length() + " sized data came. Whoa.. :P ");
        }
    }



    public Scrapper(String data, int semesterId) throws Exception{
        this.htmlData = data;
        this.semesterId = semesterId;

        if (data == ""){
            System.out.println("No data came.");
        }
        System.out.println ("data size: "  + data.length());
        System.out.println (semesterId);
    }

    public Student getStudentDataJson(String data) throws  Exception{

        Gson json = new GsonBuilder().create();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> dataMap = json.fromJson(data, type);

        return new Student(dataMap);
    }
//
//    public Student getStudentData () throws Exception{
//        Student student = new Student();
//
//        org.jsoup.nodes.Document doc = Jsoup.parse(htmlData);
//
//        Elements studentData = doc.getElementsByAttributeValueContaining("class", "col-md-9 col-sm-9");
//
//        student.setProgram(studentData.get(0).text());
//        student.setName(studentData.get(1).text());
//        student.setId(studentData.get(2).text());
//        student.setEnrollment(studentData.get(3).text());
//        student.setBatch(studentData.get(4).text());
//
//        return student;
//    }

    public ArrayList<Subject> getResult() throws Exception{
        ArrayList<Subject> result = new ArrayList<>();
        org.jsoup.nodes.Document doc = Jsoup.parse(htmlData);

        System.out.println();
        System.out.println("   +++++++++++++++++++++++++++      ");
        Element dataTable = doc.body().select("table").get(9);

        Elements allRows = dataTable.select("tr");

        Element firstRow = allRows.get(0);
        Elements columnNames = firstRow.select("td");

        ArrayList<String> columnTitle = new ArrayList();

        for (Element zero : columnNames){
            columnTitle.add(zero.text());
        }
//
//        for (int i = 0; i < columnTitle.size(); i++){
//            System.out.print(columnTitle.get(i) + "\t\t");
//        }

        for (int i = 1; i < allRows.size(); i++){
            Element tempRow = allRows.get(i);
            Elements tempData = tempRow.select("td");
            System.out.println(tempData.text());
            Subject sub = new Subject();
            sub.setCourseCode(tempData.get(0).text());
            sub.setCourseName(tempData.get(1).text());
            sub.setCredit(tempData.get(2).text());
            sub.setGrade(tempData.get(3).text());
            sub.setGradePoint(tempData.get(4).text());

            result.add(sub);
        }
        return result;
    }

}
