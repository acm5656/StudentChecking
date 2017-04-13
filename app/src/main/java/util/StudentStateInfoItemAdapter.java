package util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.beans.Item;
import com.example.checkingsystem.beans.TeacherCheckingStudentItem;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/4/9.
 */

public class StudentStateInfoItemAdapter extends ArrayAdapter<TeacherCheckingStudentItem> {
    private int resourseID;

    public StudentStateInfoItemAdapter(Context context, int textViewResourceId, List<TeacherCheckingStudentItem> objects) {
        super(context, textViewResourceId, objects);
        resourseID = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeacherCheckingStudentItem item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourseID,null);
        TextView studentNo = (TextView) view.findViewById(R.id.tacher_checking_student_list_student_no);
        TextView studentName = (TextView)view.findViewById(R.id.teacher_checking_student_list_student_name);
        TextView studentState = (TextView)view.findViewById(R.id.teacher_checking_student_list_student_state);
        studentNo.setText(item.getStudentNo());
        studentName.setText(item.getStudentName());
        studentState.setText(item.getStudentState());
        return view;
    }
}
