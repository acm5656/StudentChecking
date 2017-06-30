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

public class StudentCourseItemAdapter extends BaseAdapter {
    Context context;
    List<CourseShow> courseList;
    int layout;
    public StudentCourseItemAdapter(Context context, List<CourseShow> courseList, int layout)
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
            viewHolder.name = (TextView) view.findViewById(R.id.student_list_view_course_item_course_name);
            viewHolder.img = (ImageView)view.findViewById(R.id.student_list_view_course_item_course_img);
            viewHolder.id = (TextView)view.findViewById(R.id.student_list_view_course_item_course_id);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (CourseViewHolder)view.getTag();
        }
        viewHolder.name.setText(courseShow.getName());
        viewHolder.id.setText(courseShow.getId());
        if(courseShow.getImgBitmap()!=null) {
            viewHolder.img.setImageBitmap(courseShow.getImgBitmap());
        }
        return view;
    }

}
