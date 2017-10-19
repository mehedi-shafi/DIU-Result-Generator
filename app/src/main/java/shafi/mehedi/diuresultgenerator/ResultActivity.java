package shafi.mehedi.diuresultgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Adapters.ResultListAdapter;
import Adapters.SemesterListAdapter;
import Calculations.CGPA_calc;
import Calculations.Utilities;
import Models.SemesterPartedResult;
import Models.Student;
import Models.Subject;

public class ResultActivity extends AppCompatActivity {

    TextView name, id, batch, enroll, credit, courses, cgpa;
    ListView resultList;

    ResultListAdapter listAdapter;

    SemesterListAdapter semesterListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle mBundle = getIntent().getExtras();

        ArrayList<Subject> resultData = (ArrayList<Subject>) mBundle.getSerializable("dataList");
        Student student = (Student) mBundle.getSerializable("studentData");
        SemesterPartedResult semesterPartedData = (SemesterPartedResult) mBundle.getSerializable("semesterPartedData");

        name = (TextView) findViewById(R.id.resultStudentName);
        id = (TextView) findViewById(R.id.resultStudentId);
        enroll = (TextView) findViewById(R.id.resultStudentEnroll);
        batch = (TextView) findViewById(R.id.resultStudentBatch);

        resultList = (ListView) findViewById(R.id.ResultList);
        semesterListAdapter = new SemesterListAdapter(this, R.layout.semester_row, semesterPartedData.getSemesters());
        resultList.setAdapter(semesterListAdapter);

        credit = (TextView) findViewById(R.id.creditDone);
        courses = (TextView) findViewById(R.id.courseTaken);
        cgpa = (TextView) findViewById(R.id.cgText);

//        CGPA_calc cgCal = new CGPA_calc(Utilities.uniqueResults(resultData));
            CGPA_calc cgCal = new CGPA_calc(resultData);

        name.setText(student.getName());
        id.setText(student.getId());
        enroll.setText(student.getEnrollment());
        batch.setText(student.getBatch());

        credit.setText(String.valueOf(cgCal.getTotalCredit()));
        courses.setText(String.valueOf(cgCal.totalNumCourseCompleted()));

        DecimalFormat numberFormat = new DecimalFormat("00.00");
        String _cg_ = String.valueOf(numberFormat.format(cgCal.getCgpa()));
        cgpa.setText(_cg_);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
        ResultActivity.this.finish();
    }
}
