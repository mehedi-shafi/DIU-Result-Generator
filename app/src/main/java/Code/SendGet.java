package Code;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Calculations.Utilities;
import Models.Student;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shafi on 12/27/2017.
 */

public class SendGet extends AsyncTask {

    private String responseData = "";
    private String link;
    Student student;

    private OkHttpClient client = new OkHttpClient();

    public SendGet(String link, Student student){
        this.link = link;
        this.student = student;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        try {
            sendDataBack();
        }catch (Exception E){
            System.out.println("Problem trying to retrieve data of the student.");
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        StringBuffer result = new StringBuffer();
        try{
            Request request = new Request.Builder().url(link).build();
            Response response = client.newCall(request).execute();
            this.responseData = response.body().string();
        }catch (Exception E){
            E.printStackTrace();
            System.out.println("Send request crashed");
        }
        return result.toString();
    }

    public void sendDataBack() throws  Exception{
        Scrapper scp = new Scrapper();
        this.student.setStudent(scp.getStudentDataJson(responseData));
    }
}
