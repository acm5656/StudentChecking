package com.example.checkingsystem.student.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.beans.Item;

import java.util.ArrayList;
import java.util.List;

public class StudentInquireFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private List<Item> listItem;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_student_inquire, container, false);
        initItem();
        listView = (ListView)view.findViewById(R.id.fragment_student_inquire_list_view);
        ItemAdapter itemAdapter = new ItemAdapter(getContext(),R.layout.item_list_view,listItem);
        listView.setAdapter(itemAdapter);
        return view;
    }

    private void initItem() {
        listItem = new ArrayList<>();
        Item inquireScoreItem = new Item("查询成绩",R.drawable.search_icon);
        Item inquireCheckingItem = new Item("查询缺勤",R.drawable.search_icon);
        Item inquireAskForLeaveItem = new Item("查询请假",R.drawable.search_icon);
        listItem.add(inquireScoreItem);
        listItem.add(inquireCheckingItem);
        listItem.add(inquireAskForLeaveItem);

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
