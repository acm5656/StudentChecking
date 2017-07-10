package com.example.checkingsystem.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Student;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 竞豪 on 2017/7/9.
 */

/**
 * 因为和请假时显示的item一样,因此布局直接使用的请假的item
 * 这个Adapter与AssistantAgreeForLeaveItemAdapter几乎完全一致
 */
public class AssistantAgreeForClassItemAdapter extends BaseAdapter {
    Context context;
    List<Student> studentList;
    int layout;

    public AssistantAgreeForClassItemAdapter(Context context, List<Student> studentList, int layout) {
        this.context = context;
        this.studentList = studentList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = View.inflate(context, layout, null);
            viewHolder = new ViewHolder();
            viewHolder.head = (CircleImageView) view.findViewById(R.id.clv_item_assistant_agree_for_leave);
            viewHolder.no = (TextView) view.findViewById(R.id.tv_item_assistant_agree_for_leave_mine_id);
            viewHolder.name = (TextView) view.findViewById(R.id.tv_item_assistant_agree_for_leave_mine_name);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        /**
         * 注意， 此处设置头像可能有错误
         */
        viewHolder.head.setImageURI(Uri.parse(studentList.get(i).getStudentHeadimageUrl()));
        viewHolder.no.setText(studentList.get(i).getStudentNo());
        viewHolder.name.setText(studentList.get(i).getStudentName());
        return view;
    }

    private class ViewHolder{
        CircleImageView head;
        TextView no;
        TextView name;
    }
}
