package Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import Calculations.CGPA_calc;
import Calculations.Utilities;
import Models.SemesterPartedResult;
import Models.SemesterStorage;
import Models.Subject;
import shafi.mehedi.diuresultgenerator.R;

/**
 * Created by shafi on 10/19/2017.
 */

public class SemesterListAdapter extends ArrayAdapter {

    private Activity activity;
    private int layoutResource;
    private ArrayList<SemesterStorage> data;

    public SemesterListAdapter(Activity activity, int layoutResource, ArrayList<SemesterStorage> data){
        super(activity, layoutResource, data);
        this.activity = activity;
        this.layoutResource = layoutResource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        SemesterViewHolder holder = null;
        if (row == null || row.getTag() == null){
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new SemesterViewHolder();

            holder.cgpa = (TextView) row.findViewById(R.id.cgpa_semester);
            holder.sgpa = (TextView) row.findViewById(R.id.sgpa_semester);
            holder.coursesNumber = (TextView) row.findViewById(R.id.total_courses_semester);
            holder.creditNumber = (TextView) row.findViewById(R.id.credit_done_semester);
            holder.resultList = (ListView) row.findViewById(R.id.subjectListSemester);
            holder.semesterTitle = (TextView) row.findViewById(R.id.semester_row_semester);

        }
        else{
            holder = (SemesterViewHolder) row.getTag();
        }

        holder.semesterStorage = (SemesterStorage) getItem(position);

        CGPA_calc temp_calc = new CGPA_calc(holder.semesterStorage.getData());

        holder.coursesNumber.setText(String.valueOf(holder.semesterStorage.getData().size()));
        holder.semesterTitle.setText(Utilities.semesterTitle(holder.semesterStorage.getSemesterId()));
        ArrayList<Subject> temp_one = new ArrayList<>();
        for (int i = 0; i <= position; i++){
            temp_one.addAll(((SemesterStorage) getItem(i)).getData());
        }
        CGPA_calc currentCGPA = new CGPA_calc(temp_one);
        NumberFormat formatter = new DecimalFormat("##.##");
        holder.cgpa.setText(String.valueOf(formatter.format(currentCGPA.getCgpa())));
        holder.sgpa.setText(String.valueOf(formatter.format(temp_calc.getCgpa())));

        holder.creditNumber.setText(String.valueOf(temp_calc.getTotalCredit()));

        ResultListAdapter listAdapter = new ResultListAdapter(activity, R.layout.result_row, holder.semesterStorage.getData());
        holder.resultList.setAdapter(listAdapter);

        return row;
    }

    public class SemesterViewHolder{
        SemesterStorage semesterStorage;
        TextView semesterTitle;
        TextView coursesNumber;
        TextView creditNumber;
        TextView sgpa;
        TextView cgpa;
        ListView resultList;
    }
}
