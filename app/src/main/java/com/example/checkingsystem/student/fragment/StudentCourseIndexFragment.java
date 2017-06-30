package com.example.checkingsystem.student.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.StudentCourseItemAdapter;
import com.example.checkingsystem.adapter.TeacherCourseItemAdapter;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.entity.VirtualCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class StudentCourseIndexFragment extends Fragment {
    ListView listView;
    StudentCourseItemAdapter courseItemAdapter;
    View view;
    List<CourseShow> courseList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_course_index,container,false);
        listView = (ListView) view.findViewById(R.id.fragment_student_course_index_list_view);
        initUI();
        return view;
    }
    private void initUI() {
        listView = (ListView) view.findViewById(R.id.fragment_student_course_index_list_view);
        courseList = new ArrayList<>();
        CourseShow courseShow = new CourseShow("123","软件测试","朱少民",null);
        courseList.add(courseShow);
        CourseShow courseShow1 = new CourseShow("1234","软件项目管理","朱少民",null);
        courseList.add(courseShow1);

    }
    public void addCourse(VirtualCourse virtualCourse, Bitmap bitmap)
    {
        Toast.makeText(getContext(),"恭喜你，添加成功",Toast.LENGTH_SHORT).show();
        Log.e("testVirCourse",virtualCourse.toString());
        CourseShow courseShow = new CourseShow();
        courseShow.setId(virtualCourse.getVirtualCourseCode());
        courseShow.setDbID(virtualCourse.getVirtualCourseId());
        courseShow.setName(virtualCourse.getVirtualCourseName());
        courseShow.setImgBitmap(bitmap);
        courseList.add(0,courseShow);
        updateUI();
    }

    public void updateUI()
    {
        courseItemAdapter = new StudentCourseItemAdapter(getContext(),courseList,R.layout.student_list_view_course_item);
        listView.setAdapter(courseItemAdapter);
    }
}
