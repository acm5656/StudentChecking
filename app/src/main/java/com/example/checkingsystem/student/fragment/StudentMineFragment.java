package com.example.checkingsystem.student.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkingsystem.MainActivity;
import com.example.checkingsystem.R;
import com.example.checkingsystem.RegistActivity;
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
    private Button quitButton;
    private TextView registTextView;
    public static final int FACE_REGIST_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_mine, container, false);
        quitButton = (Button) view.findViewById(R.id.fragment_student_mine_quit);

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.studentDao.deleteStudent(MainActivity.studentStatic);
                MainActivity.studentStatic = null;
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        registTextView = (TextView) view.findViewById(R.id.fragment_student_mine_regist_face);
        registTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistActivity.class);
                startActivityForResult(intent,FACE_REGIST_REQUEST_CODE);
            }
        });

        return view;
    }



    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case FACE_REGIST_REQUEST_CODE:
                    String dataStr = data.getStringExtra("data_return");
                    Log.e("toast","---------------2");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
