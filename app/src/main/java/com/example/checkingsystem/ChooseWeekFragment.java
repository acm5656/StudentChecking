package com.example.checkingsystem;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.support.v7.app.AlertDialog;



public class ChooseWeekFragment extends DialogFragment {
    int yourChoice;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return showSingleChoiceDialog();
    }

    private Dialog showSingleChoiceDialog(){
        final String[] items = { "放假中",
                "第1周",
                "第2周",
                "第3周",
                "第4周",
                "第5周",
                "第6周",
                "第7周",
                "第8周",
                "第9周",
                "第10周",
                "第11周",
                "第12周",
                "第13周",
                "第14周",
                "第15周",
                "第16周",
                "第17周",
                "第18周",
                "第19周",
                "第20周"
        };
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(getActivity());
        singleChoiceDialog.setTitle("选择周数");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            CallBackValue callBackValue = (CallBackValue) getActivity();
                            callBackValue.SendMessageValue(new Integer(yourChoice).toString());
                        }
                    }
                });

        return singleChoiceDialog.create();
    }
    public interface CallBackValue{
        public void SendMessageValue(String strValue);
    }

}
