package com.example.checkingsystem.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.entity.Class;
import com.example.checkingsystem.entity.ClassLeaveShow;
import com.example.checkingsystem.entity.CourseLeave;
import com.example.checkingsystem.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 竞豪 on 2017/7/8.
 */
public class AssistantAgreeForLeaveItemAdapter extends BaseAdapter{
    Context context;
    List<ClassLeaveShow> classLeaveShowList;
    int layout;

    public AssistantAgreeForLeaveItemAdapter(Context context, List<ClassLeaveShow> classLeaveShowList, int layout) {
        this.context = context;
        this.classLeaveShowList = classLeaveShowList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return classLeaveShowList.size();
    }

    @Override
    public Object getItem(int i) {
        return classLeaveShowList.get(i);
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
            viewHolder.status = (TextView)view.findViewById(R.id.tv_item_assistant_agree_for_leave_mine_status);
            viewHolder.createTime = (TextView)view.findViewById(R.id.tv_item_assistant_agree_for_leave_mine_time);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();

        if(classLeaveShowList.get(i).getStudentBitmap()!=null)
        {
            viewHolder.head.setImageBitmap(classLeaveShowList.get(i).getStudentBitmap());
        }
        viewHolder.no.setText(classLeaveShowList.get(i).getStudentSchoolName());
        viewHolder.name.setText(classLeaveShowList.get(i).getStudentName());
        viewHolder.status.setText("状态："+classLeaveShowList.get(i).getChinese());
        Date date = new Date(classLeaveShowList.get(i).getVirtualCourseLeaveGmtCreated().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String dateStr = sdf.format(date);
        viewHolder.createTime.setText("申请时间："+"\n"+dateStr);
        return view;
    }

    private class ViewHolder{
        CircleImageView head;
        TextView no;
        TextView name;
        TextView status;
        TextView createTime;
    }
}
