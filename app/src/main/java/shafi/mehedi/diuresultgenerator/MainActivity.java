package shafi.mehedi.diuresultgenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import Calculations.Store;
import Calculations.Utilities;
import Code.OlineHandler;
import Code.Scrapper;
import Models.Student;
import Models.Subject;

public class MainActivity extends AppCompatActivity {

    public String htmlData;
    private EditText id;
    private Spinner fromYear, fromSemester, toYear, toSemester;
    private TextView currentProcess;
    private ProgressBar progressBar;
    private Button submit, show;
    private String student_id;
    private String semester_from, semester_to;
    private ArrayList<Subject> results;
    private Student student = new Student();
    private Store mainStorage = new Store();

    private final int WAIT_TIME = 20000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalize();

        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                generateResult();
                progressBar.setEnabled(true);
                submit.setVisibility(View.INVISIBLE);
            }
        });

        show.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ResultActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putSerializable("dataList", mainStorage.getData());
                mBundle.putSerializable("studentData", student);

                i.putExtras(mBundle);

                startActivity(i);
                MainActivity.this.finish();
            }
        });

    }

    public void initalize(){

        id = (EditText) findViewById(R.id.idInput);
        fromYear = (Spinner) findViewById(R.id.spinnerFromYear);
        fromSemester = (Spinner) findViewById(R.id.spinnerFromSemester);
        toYear = (Spinner) findViewById(R.id.spinnerToYear);
        toSemester = (Spinner) findViewById(R.id.spinnerToSemester);
        currentProcess = (TextView) findViewById(R.id.currentProcess);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        submit = (Button) findViewById(R.id.generateResultButton);
        show = (Button) findViewById(R.id.viewResult);

        progressBar.setVisibility(View.INVISIBLE);
        currentProcess.setVisibility(View.INVISIBLE);
        show.setVisibility(View.INVISIBLE);


        ArrayList<String> yearsFrom = new ArrayList<>();
        ArrayList<String> yearsTo = new ArrayList<>();
        for (int i = 2000; i < 2050; i++){
            yearsFrom.add(String.valueOf(i));
            yearsTo.add(String.valueOf(i));
        }
        ArrayList<String> semestersFrom = new ArrayList<>();
        ArrayList<String> semestersTo = new ArrayList<>();
        semestersFrom.add("Spring");
        semestersFrom.add("Summer");
        semestersFrom.add("Fall");
        semestersTo.add("Spring");
        semestersTo.add("Summer");
        semestersTo.add("Fall");

        ArrayAdapter<String> yearAdapterFrom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearsFrom);
        ArrayAdapter<String> yearAdapterTo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearsTo);
        ArrayAdapter<String> semesterAdapterFrom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestersFrom);
        ArrayAdapter<String> semesterAdapterTo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestersTo);

        yearAdapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterAdapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterAdapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromYear.setAdapter(yearAdapterFrom);
        toYear.setAdapter(yearAdapterTo);
        fromSemester.setAdapter(semesterAdapterFrom);
        toSemester.setAdapter(semesterAdapterTo);

    }

    public void generateResult() {
        student_id = id.getText().toString();
        semester_from = Utilities.semester_id_generation(fromYear.getSelectedItemPosition() + 2000, fromSemester.getSelectedItemPosition() + 1);
        semester_to = Utilities.semester_id_generation(toYear.getSelectedItemPosition() + 2000, toSemester.getSelectedItemPosition() + 1);
        Log.v("Semester from ", semester_from);
        Log.v("Semester to ", semester_to);
        progressBar.setVisibility(View.VISIBLE);

        int currentsem;


        for (int i = Integer.parseInt(semester_from); i <= Integer.parseInt(semester_to); i++) {

            OlineHandler handler = new OlineHandler(student_id, String.valueOf(i), student, mainStorage, i, Integer.parseInt(semester_to), show, progressBar, currentProcess);
            handler.execute();

            if (i % 10 == 3){
                i += 7;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {

            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mehedi-shafi/DIU-Result-Generator"));
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
