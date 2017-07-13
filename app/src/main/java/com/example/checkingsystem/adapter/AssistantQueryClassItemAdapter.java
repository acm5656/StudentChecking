package com.example.checkingsystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.entity.ClassShow;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/7/12.
 */

public class AssistantQueryClassItemAdapter extends BaseAdapter {
    private Context context;
    private List<ClassShow> classList;
    private int layout;
    public AssistantQueryClassItemAdapter(Context context,int layout,List<ClassShow> classList)
    {
        this.context = context;
        this.classList = classList;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return classList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ClassShow myClass = classList.get(position);
        if(convertView==null)
        {
            convertView = View.inflate(context,layout,null);
            viewHolder = new ViewHolder();
            viewHolder.classNameTextView = (TextView) convertView.findViewById(R.id.tv_item_assistant_query_class_major);
            viewHolder.classTextView = (TextView)convertView.findViewById(R.id.tv_item_assistant_query_class_no);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.iv_item_assistant_query_class);
            viewHolder.codeTextView = (TextView) convertView.findViewById(R.id.tv_item_assistant_query_class_code);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        if(myClass.getBitmap()!=null)
        {
            viewHolder.imageView.setImageBitmap(myClass.getBitmap());
        }
        viewHolder.classNameTextView.setText(myClass.getClassDepartment()+"  "+myClass.getClassMajor());
        viewHolder.classTextView.setText(myClass.getClassYear()+"-"+myClass.getClassNo()+"班");
        viewHolder.codeTextView.setText("编号："+myClass.getClassCode());

        return convertView;
    }
    class ViewHolder {
        TextView classTextView;
        ImageView imageView;
        TextView classNameTextView;
        TextView codeTextView;
    }
}
