package com.listview.scrolledList;

import android.content.res.Resources;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.graphics.Color;


public class ScrolledList extends Activity {
    Resources res;
    TextView infoArea;
    ListView items;
    ArrayAdapter<CharSequence> entries;
    TextView currItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        res = getResources();
        items = (ListView)findViewById(R.id.wordList);
        entries = ArrayAdapter.createFromResource(this, R.array.lorem, android.R.layout.simple_list_item_1);
        items.setAdapter(entries);
        items.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currItem = (TextView)view;
                infoArea.setText(String.format("Selection:%s", currItem.getText().toString()));
                currItem.setEnabled(false);
            }
        });

        infoArea = (TextView)findViewById(R.id.infoArea);
        infoArea.setBackgroundColor(Color.parseColor("#ffffaa"));

    }

    public void disableEntry(View disableBtn) {
        currItem.setEnabled(false);
    }
}
