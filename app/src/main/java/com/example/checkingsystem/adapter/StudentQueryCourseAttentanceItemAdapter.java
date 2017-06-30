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

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class StudentQueryCourseAttentanceItemAdapter extends BaseAdapter {
    Context context;
    List<CourseShow> courseList;
    int layout;
    public StudentQueryCourseAttentanceItemAdapter(Context context, List<CourseShow> courseList, int layout)
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
            viewHolder.name = (TextView) view.findViewById(R.id.list_view_student_query_course_attentance_item_name);
            viewHolder.courseAttentanceCount = (TextView)view.findViewById(R.id.list_view_student_query_course_attentance_item_attentance_count);
            viewHolder.courseAskForLeaveCount = (TextView)view.findViewById(R.id.list_view_student_query_course_attentance_item_ask_leave_count);
            viewHolder.img = (ImageView)view.findViewById(R.id.list_view_student_query_course_attentance_item_img);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (CourseViewHolder) view.getTag();
        }
        viewHolder.name.setText(courseShow.getName());
        viewHolder.courseAttentanceCount.setText(courseShow.getCourseAttentanceCount());
        viewHolder.courseAskForLeaveCount.setText(courseShow.getCourseAskForLeaveCount());



        return view;
    }


}
