package com.example.checkingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Course;
import com.example.checkingsystem.entity.CourseShow;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class TeacherQueryCourseAttentanceItemAdapter extends BaseAdapter {
    Context context;
    List<CourseShow> courseList;
    int layout;
    public TeacherQueryCourseAttentanceItemAdapter(Context context, List<CourseShow> courseList, int layout)
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
        CourseShow courseShow = courseList.get(position);
        View view = null;
        CourseViewHolder viewHolder;

        if(convertView==null)
        {
            view = LayoutInflater.from(context).inflate(layout,null);
            viewHolder = new CourseViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.list_view_teacher_query_course_attentance_item_name);
            viewHolder.img = (ImageView)view.findViewById(R.id.list_view_teacher_query_course_attentance_item_img);
            viewHolder.studentNumber = (TextView)view.findViewById(R.id.list_view_teacher_query_course_attentance_item_student_number);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (CourseViewHolder)view.getTag();
        }
        viewHolder.name.setText(courseShow.getName());
        viewHolder.studentNumber.setText(courseShow.getStudentNumber());



        return view;
    }


}
