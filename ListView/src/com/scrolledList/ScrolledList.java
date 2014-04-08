package com.scrolledList;

import android.content.res.Resources;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.graphics.Color;


public class ScrolledList extends Activity implements View.OnClickListener {
    Resources res;
    TextView infoArea;
    RadioGroup itemGrp;
    String[] inStrs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        res = getResources();
        itemGrp = (RadioGroup)findViewById(R.id.wordList);

        infoArea = (TextView)findViewById(R.id.infoArea);
        infoArea.setBackgroundColor(Color.parseColor("#ffffaa"));

        inStrs = res.getStringArray(R.array.lorem);

        RadioButton r;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        int i;
        for (i = 0; i < inStrs.length; i++) {
            r = new RadioButton(this);
            r.setText(inStrs[i]);
            r.setId(i);
            r.setOnClickListener(this);
            itemGrp.addView(r, lp);
        }
    }

    public void onClick(View v) {
        infoArea.setText(String.format("By ID:%s%nBy RadioGroup:%s",
                    inStrs[v.getId()],
                    inStrs[itemGrp.getCheckedRadioButtonId()]));
    }
}
