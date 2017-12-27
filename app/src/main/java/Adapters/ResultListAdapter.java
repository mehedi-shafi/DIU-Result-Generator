package Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import shafi.mehedi.diuresultgenerator.R;

import java.util.ArrayList;

import Models.Subject;
import shafi.mehedi.diuresultgenerator.SubjectResult;

/**
 * Created by shafi on 7/21/2017.
 */

public class ResultListAdapter extends ArrayAdapter {

    private Activity activity;
    private int layoutResource;
    private ArrayList<Subject> results;

    public ResultListAdapter(Activity activity, int layoutResource, ArrayList<Subject> data){
        super (activity, layoutResource, data);
        this.activity = activity;
        this.layoutResource = layoutResource;
        this.results = data;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return results.get(position);
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
        ResultListViewHolder holder = null;

        if (row == null || row.getTag() == null){

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new ResultListViewHolder();

            holder.courseId = (TextView) row.findViewById(R.id.rowCourseId);
            holder.coursname = (TextView) row.findViewById(R.id.rowCourseName);
            holder.credit = (TextView) row.findViewById(R.id.rowCourseCredit);
            holder.result = (TextView) row.findViewById(R.id.rowCourseResult);

        }
        else{
            holder = (ResultListViewHolder) row.getTag();
        }

        holder.subject = (Subject) getItem(position);

        holder.courseId.setText(holder.subject.getCourseCode());
        holder.coursname.setText(holder.subject.getCourseName());
        holder.credit.setText(holder.subject.getCredit());
        holder.result.setText(holder.subject.getGradePoint());



        return row;
    }

    public class ResultListViewHolder{

        Subject subject;
        TextView courseId;
        TextView coursname;
        TextView credit;
        TextView result;


    }

}
