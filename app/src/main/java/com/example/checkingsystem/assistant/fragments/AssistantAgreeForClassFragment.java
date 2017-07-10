package com.example.checkingsystem.assistant.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.checkingsystem.R;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantAgreeForClassFragment extends Fragment{
    ProgressDialog progressDialog;

    private View view;
    private ListView listView;

    /**
     * listView的适配器
     */
    private AssistantAgreeForClassFragment assistantAgreeForClassFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_assistant_agree_for_course, container, false);
//        progressDialog = ProgressDialog.show(getActivity(),"查询申请入班信息", "请稍等", true, false);
        initItem();
        return view;
    }

    private void initItem(){
        listView = (ListView) view.findViewById(R.id.lv_fragment_assistant_agree_for_course);
    }
}
