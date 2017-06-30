package com.example.checkingsystem.student.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.adapter.StudentQueryCourseAttentanceItemAdapter;
import com.example.checkingsystem.beans.Item;
import com.example.checkingsystem.entity.CourseShow;

import java.util.ArrayList;
import java.util.List;

import util.ItemAdapter;

public class StudentInquireFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private List<CourseShow> listItem;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_student_inquire, container, false);
        initItem();
        listView = (ListView)view.findViewById(R.id.fragment_student_inquire_list_view);
        StudentQueryCourseAttentanceItemAdapter studentQueryCourseAttentanceItemAdapter = new StudentQueryCourseAttentanceItemAdapter(getContext(),listItem,R.layout.list_view_student_query_course_attentance_item);
        listView.setAdapter(studentQueryCourseAttentanceItemAdapter);
        return view;
    }

    private void initItem() {
        listItem = new ArrayList<>();
        CourseShow courseShow = new CourseShow("123","软件测试","朱少民",null,"13","1","81");
        CourseShow courseShow1 = new CourseShow("124","软件项目管理","朱少民",null,"13","1","81");
        CourseShow courseShow2 = new CourseShow("125","软件构造","朱少民",null,"13","1","81");
        listItem.add(courseShow);
        listItem.add(courseShow1);
        listItem.add(courseShow2);

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
