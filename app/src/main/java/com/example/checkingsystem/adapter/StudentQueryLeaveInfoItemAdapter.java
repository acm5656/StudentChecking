package com.example.checkingsystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.entity.CourseLeave;
import com.example.checkingsystem.entity.Teacher;
import com.example.checkingsystem.entity.VirtualCourseLeave;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/7/11.
 */

public class StudentQueryLeaveInfoItemAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<VirtualCourseLeave> courseLeaveList;
    public StudentQueryLeaveInfoItemAdapter(Context context,int layout,List<VirtualCourseLeave> courseLeaveList)
    {
        this.context = context;
        this.layout = layout;
        this.courseLeaveList = courseLeaveList;
    }
    @Override
    public int getCount() {
        return courseLeaveList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseLeaveList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = View.inflate(context,layout,null);
            viewHolder = new ViewHolder();
            viewHolder.createTime = (TextView) convertView.findViewById(R.id.tv_student_leave_info_item_create_time);
            viewHolder.leaveTime = (TextView)convertView.findViewById(R.id.tv_student_leave_info_item_leave_time);
            viewHolder.status = (TextView)convertView.findViewById(R.id.tv_student_leave_info_item_status);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Date date = new Date(courseLeaveList.get(position).getVirtualCourseLeaveGmtCreated().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String dateStr = sdf.format(date);
        viewHolder.createTime.setText(dateStr);
        Date beginDate = new Date(courseLeaveList.get(position).getVirtualCourseLeaveBegin().getTime());
        Date endDate = new Date(courseLeaveList.get(position).getVirtualCourseLeaveEnd().getTime());
        viewHolder.leaveTime.setText(sdf.format(beginDate)+"到"+sdf.format(endDate));
        viewHolder.status.setText("状态："+courseLeaveList.get(position).statusToChinese());
        return convertView;
    }
    class ViewHolder{
        TextView createTime;
        TextView leaveTime;
        TextView status;
    }

}

