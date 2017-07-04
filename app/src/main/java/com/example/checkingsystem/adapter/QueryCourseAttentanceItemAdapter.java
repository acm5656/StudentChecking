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

public class QueryCourseAttentanceItemAdapter extends BaseAdapter {
    Context context;
    List<CourseShow> courseList;
    int layout;
    public QueryCourseAttentanceItemAdapter(Context context, List<CourseShow> courseList, int layout)
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
            viewHolder.courseAttentanceCount = (TextView)view.findViewById(R.id.list_view_student_query_course_attentance_item_attentance);
            viewHolder.img = (ImageView)view.findViewById(R.id.list_view_query_course_attentance_item_img);
            viewHolder.courseAskForLeaveCount = (TextView)view.findViewById(R.id.list_view_student_query_course_attentance_item_attentance_ask_for_leave);
            viewHolder.courseLateCount = (TextView)view.findViewById(R.id.list_view_student_query_course_attentance_item_attentance_late);
            viewHolder.courseAbsentCount = (TextView)view.findViewById(R.id.list_view_student_query_course_attentance_item_absent_count);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (CourseViewHolder) view.getTag();
        }
        viewHolder.name.setText(courseShow.getName());
        if(courseShow.getCourseAttentanceCount()==null||courseShow.getCourseAttentanceCount().trim().equals(""))
        {
            courseShow.setCourseAttentanceCount("0");
        }
        if(courseShow.getCourseAbsentCount()==null||courseShow.getCourseAbsentCount().trim().equals(""))
        {
            courseShow.setCourseAbsentCount("0");
        }
        if(courseShow.getCourseAskForLeaveCount()==null||courseShow.getCourseAskForLeaveCount().trim().equals(""))
        {
            courseShow.setCourseAskForLeaveCount("0");
        }
        if(courseShow.getCourseLateCount()==null||courseShow.getCourseLateCount().trim().equals(""))
        {
            courseShow.setCourseLateCount("0");
        }
        viewHolder.courseAbsentCount.setText(courseShow.getCourseAbsentCount());
        viewHolder.courseLateCount.setText(courseShow.getCourseLateCount());
        viewHolder.courseAskForLeaveCount.setText(courseShow.getCourseAskForLeaveCount());
        viewHolder.courseAttentanceCount.setText(courseShow.getCourseAttentanceCount());
        if(courseShow.getImgBitmap()!=null) {
            viewHolder.img.setImageBitmap(courseShow.getImgBitmap());
        }
        return view;
    }


}
