package shafi.mehedi.diuresultgenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import Models.SemesterPartedResult;
import Models.Store;
import Calculations.Utilities;
import Code.OnlineHandler;
import Models.Student;

public class MainActivity extends AppCompatActivity {

    public String htmlData;
    private EditText id;
    private Button generate, show;
    private ProgressBar progressBar;
    private TextView progressText;
    private String student_id;
    private Student student = new Student();
    private Store mainStorage = new Store();
    private SemesterPartedResult semesterPartedResult = new SemesterPartedResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalize();

        generate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                generateResult();
                generate.setVisibility(View.INVISIBLE);
            }
        });

        show.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ResultActivity.class);

                Bundle mBundle = new Bundle();
                mBundle.putSerializable("dataList", mainStorage.getData());
                mBundle.putSerializable("studentData", student);
                mBundle.putSerializable("semesterPartedData", semesterPartedResult);

                i.putExtras(mBundle);

                startActivity(i);
                MainActivity.this.finish();
            }
        });

    }

    public void initalize(){

        id = (EditText) findViewById(R.id.idInput);

        generate = (Button) findViewById(R.id.go_button);
        show = (Button) findViewById(R.id.show_result);
        show.setVisibility(View.INVISIBLE);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressText = (TextView) findViewById(R.id.progress_text);
        progressBar.setVisibility(View.INVISIBLE);
        progressText.setVisibility(View.INVISIBLE);

    }

    public void generateResult() {
        student_id = id.getText().toString();
        int semester_from = Utilities.admissionSemester(student_id);
        if (semester_from == -1){
            Toast.makeText(getApplicationContext(), "Invalid ID", Toast.LENGTH_SHORT).show();
        }
        else{
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int last_t_digits = year % 100;
           String year_t = String.valueOf(last_t_digits) + "3";
            int semester_to = Integer.parseInt(year_t);
            System.out.println("year " + year + " semester to: " + semester_to);
            for (int i = semester_from; i <= semester_to; i++){
                OnlineHandler handler = new OnlineHandler(student_id, String.valueOf(i), student, mainStorage, i, semester_to, show, progressBar, progressText, semesterPartedResult);
                handler.execute();
                if (i % 10 == 3) i += 7;
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
