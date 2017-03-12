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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentMineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class StudentMineFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private ListView listView;
    private List<Item> listItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_mine, container, false);

        return view;
    }



    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
