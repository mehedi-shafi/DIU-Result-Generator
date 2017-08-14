package Code;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import Calculations.Store;
import Calculations.Utilities;
import Models.Student;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shafi on 7/21/2017.
 */

public class OlineHandler extends AsyncTask  {



    private OkHttpClient client = new OkHttpClient();
    private String studentID, semesterID;
    private String htmlDATA;
    private Student student;
    private Store storage;
    private int current;
    private int last;
    private Button show;
    private ProgressBar progressBar;
    private TextView currentProcess;

    public  OlineHandler(String studentID, String semesterID, Student student, Store toStore, Integer current, Integer last, Button show, ProgressBar progressBar, TextView currentProcess){
        this.semesterID = semesterID;
        this.studentID = studentID;
        this.student = student;
        this.storage = toStore;
        this.current = current;
        this.last = last;
        this.show = show;
        this.progressBar = progressBar;
        this.currentProcess = currentProcess;
        currentProcess.setVisibility(View.VISIBLE);
        currentProcess.setEnabled(true);
    }


    @Override
    protected void onPostExecute(Object o) {
        currentProcess.setText("Currently fetching data for semester: " + current);
        try {
            Scrapper scrapper = new Scrapper(htmlDATA);
            if (!student.isStored()){
                Student temp = scrapper.getStudentData();
                student.setStudent(temp);
            }

            this.storage.addData(scrapper.getResult());

            if (current == last){
                show.setVisibility(View.VISIBLE);
                show.setEnabled(true);
                progressBar.setEnabled(true);
                progressBar.setVisibility(View.VISIBLE);
                currentProcess.setText("All done. See the result.");
            }

        }catch (Exception E){
            E.printStackTrace();
        }


        super.onPostExecute(o);
    }

    private String post (String stud_id, String sem_id) throws  Exception{
        RequestBody body = new FormBody.Builder().add("student_id", stud_id).add("semester_id", sem_id).build();
        Request request = new Request.Builder().url(Utilities.RESULT_URL).post(body).build();
        Response response = client.newCall(request).execute();
        this.htmlDATA = response.body().string();
        System.out.println (htmlDATA.length());
        Utilities.htmlData = htmlDATA;
        return htmlDATA;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try{
            String getResponse = post(studentID, semesterID);
            return  getResponse;
        }catch (Exception e){
            System.out.println ("Caught in action doInBackground.");
            e.printStackTrace();
        }
        return null;
    }

    public String getHtmlData(){
        return htmlDATA;
    }
}
