package com.example.checkingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.entity.StudentAttendanceCountShow;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class QueryCourseStudentAttentanceInfoItemAdapter extends BaseAdapter {
    Context context;
    List<StudentAttendanceCountShow> courseList;
    int layout;
    public QueryCourseStudentAttentanceInfoItemAdapter(Context context, List<StudentAttendanceCountShow> courseList, int layout)
    {
        this.context = context;
        this.courseList = courseList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentAttendanceCountShow courseShow = courseList.get(position);
        View view = null;
        CourseViewHolder viewHolder;

        if(convertView==null)
        {
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new CourseViewHolder();
            viewHolder.studentID = (TextView)view.findViewById(R.id.list_view_teacher_query_course_student_attentance_item_student_id);
            viewHolder.studentName = (TextView) view.findViewById(R.id.list_view_teacher_query_course_student_attentance_item_student_name);
            viewHolder.courseAttentanceCount = (TextView)view.findViewById(R.id.list_view_teacher_query_course_student_attentance_item_attentance);
            viewHolder.img = (ImageView)view.findViewById(R.id.list_view_teacher_query_student_course_attentance_item_img);
            viewHolder.courseAskForLeaveCount = (TextView)view.findViewById(R.id.list_view_teacher_query_course_student_attentance_item_attentance_ask_for_leave);
            viewHolder.courseLateCount = (TextView)view.findViewById(R.id.list_view_teacher_query_course_student_attentance_item_attentance_late);
            viewHolder.courseAbsentCount = (TextView)view.findViewById(R.id.list_view_teacher_query_course_student_attentance_item_absent_count);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (CourseViewHolder) view.getTag();
        }

        if(courseShow.getAttendanceCount()==null||courseShow.getAttendanceCount().trim().equals(""))
        {
            courseShow.setAttendanceCount("0");
        }
        if(courseShow.getAbsentCount()==null||courseShow.getAbsentCount().trim().equals(""))
        {
            courseShow.setAbsentCount("0");
        }
        if(courseShow.getLeaveCount()==null||courseShow.getLeaveCount().trim().equals(""))
        {
            courseShow.setLeaveCount("0");
        }
        if(courseShow.getLateCount()==null||courseShow.getLateCount().trim().equals(""))
        {
            courseShow.setLateCount("0");
        }
        viewHolder.studentID.setText(courseShow.getStduentSchoolID());
        viewHolder.studentName.setText(courseShow.getStudentName());
        viewHolder.courseAbsentCount.setText(courseShow.getAbsentCount());
        viewHolder.courseLateCount.setText(courseShow.getLateCount());
        viewHolder.courseAskForLeaveCount.setText(courseShow.getLeaveCount());
        viewHolder.courseAttentanceCount.setText(courseShow.getAttendanceCount());
        if(courseShow.getBitmap()!=null) {
            viewHolder.img.setImageBitmap(courseShow.getBitmap());
        }
        return view;
    }


}
