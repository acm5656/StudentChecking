package com.example.checkingsystem.teacher.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.checkingsystem.LoginActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.TeacherCourseItemAdapter;
import com.example.checkingsystem.entity.CourseShow;
import com.example.checkingsystem.entity.VirtualCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/6/28.
 */

public class TeacherCourseIndexFragment extends Fragment implements View.OnClickListener {
    final int TEACHER_ADD_COURSE = 1;
    View view;
    TeacherCourseItemAdapter teacherCourseItemAdapter;
    ListView listView;
    List<CourseShow> courseList;
    public TeacherCourseIndexFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_teacher_course_index,container,false);
        initUI();
        return view;
    }

    private void initUI() {
        listView = (ListView) view.findViewById(R.id.fragment_teacher_course_index_list_view);
        courseList = new ArrayList<>();
        CourseShow courseShow = new CourseShow("123","软件测试","朱少民",null);
        courseList.add(courseShow);
        CourseShow courseShow1 = new CourseShow("1234","软件项目管理","朱少民",null);
        courseList.add(courseShow1);
        updateListView();
    }
    private void setClickListener()
    {
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case TEACHER_ADD_COURSE:
                    VirtualCourse virtualCourse = (VirtualCourse) data.getSerializableExtra("course");
                    Bitmap bitmap = data.getParcelableExtra("bitmap");
                    Toast.makeText(getContext(),"恭喜你，添加成功,课程编号为："+virtualCourse.getVirtualCourseCode(),Toast.LENGTH_SHORT).show();

                    CourseShow courseShow = new CourseShow();
                    courseShow.setId(virtualCourse.getVirtualCourseCode());
                    courseShow.setDbID(virtualCourse.getVirtualCourseId());
                    courseShow.setName(virtualCourse.getVirtualCourseName());
                    courseShow.setTeahcerName(LoginActivity.teacherStatic.getTeacherName());
                    courseShow.setImgBitmap(bitmap);
                    courseList.add(0,courseShow);
                    updateListView();
                    break;
            }
        }
    }

    private void updateListView() {
        teacherCourseItemAdapter = new TeacherCourseItemAdapter(getContext(),courseList,R.layout.teacher_list_view_course_item);
        listView.setAdapter(teacherCourseItemAdapter);
    }

}
