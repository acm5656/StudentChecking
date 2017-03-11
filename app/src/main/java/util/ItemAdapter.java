package util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkingsystem.R;
import com.example.checkingsystem.beans.Item;

import java.util.List;

/**
 * Created by 那年.盛夏 on 2017/3/10.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    private int resourseID;

    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourseID = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourseID,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.item_image);
        TextView textView = (TextView)view.findViewById(R.id.item_textView);
        imageView.setImageResource(item.getPictureID());
        textView.setText(item.getName());
        return view;
    }
}
