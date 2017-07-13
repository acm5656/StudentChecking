package com.example.checkingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.StudentAttendanceCountShow;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class QueryClassStudentAttentanceInfoItemAdapter extends BaseAdapter {
    Context context;
    List<StudentAttendanceCountShow> classList;
    int layout;
    public QueryClassStudentAttentanceInfoItemAdapter(Context context, List<StudentAttendanceCountShow> classList, int layout)
    {
        this.context = context;
        this.classList = classList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return classList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentAttendanceCountShow classShow = classList.get(position);
        View view = null;
        ClassViewHolder viewHolder;

        if(convertView==null)
        {
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new ClassViewHolder();
            viewHolder.studentID = (TextView)view.findViewById(R.id.list_view_assistant_query_class_student_attentance_item_student_id);
            viewHolder.studentName = (TextView) view.findViewById(R.id.list_view_assistant_query_class_student_attentance_item_student_name);
            viewHolder.classAttentanceCount = (TextView)view.findViewById(R.id.list_view_assistant_query_class_student_attentance_item_attentance);
            viewHolder.img = (ImageView)view.findViewById(R.id.list_view_assistant_query_student_class_attentance_item_img);
            viewHolder.classAskForLeaveCount = (TextView)view.findViewById(R.id.list_view_assistant_query_class_student_attentance_item_attentance_ask_for_leave);
            viewHolder.classLateCount = (TextView)view.findViewById(R.id.list_view_assistant_query_class_student_attentance_item_attentance_late);
            viewHolder.classAbsentCount = (TextView)view.findViewById(R.id.list_view_assistant_query_class_student_attentance_item_absent_count);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ClassViewHolder) view.getTag();
        }

        if(classShow.getAttendanceCount()==null||classShow.getAttendanceCount().trim().equals(""))
        {
            classShow.setAttendanceCount("0");
        }
        if(classShow.getAbsentCount()==null||classShow.getAbsentCount().trim().equals(""))
        {
            classShow.setAbsentCount("0");
        }
        if(classShow.getLeaveCount()==null||classShow.getLeaveCount().trim().equals(""))
        {
            classShow.setLeaveCount("0");
        }
        if(classShow.getLateCount()==null||classShow.getLateCount().trim().equals(""))
        {
            classShow.setLateCount("0");
        }
        viewHolder.studentID.setText(classShow.getStduentSchoolID());
        viewHolder.studentName.setText(classShow.getStudentName());
        viewHolder.classAbsentCount.setText(classShow.getAbsentCount());
        viewHolder.classLateCount.setText(classShow.getLateCount());
        viewHolder.classAskForLeaveCount.setText(classShow.getLeaveCount());
        viewHolder.classAttentanceCount.setText(classShow.getAttendanceCount());
        if(classShow.getBitmap()!=null) {
            viewHolder.img.setImageBitmap(classShow.getBitmap());
        }
        return view;
    }


}
