package com.example.checkingsystem.student.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.checkingsystem.R;
import com.example.checkingsystem.VerifyFaceActivity;
public class StudentCheckingFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;
    private Button verfiButton;
    public final static int FACE_VERIFY_RESULT = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_checking, container, false);
        verfiButton = (Button)view.findViewById(R.id.fragment_student_checking_button);
        verfiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VerifyFaceActivity.class);
                startActivityForResult(intent,FACE_VERIFY_RESULT);
            }
        });
        return view;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case FACE_VERIFY_RESULT:
                if(resultCode == getActivity().RESULT_OK) {
                    String dataStr = data.getStringExtra("data_return");
                    Log.e("toast","---------------2");
                    Toast.makeText(getContext(),dataStr,Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
}
