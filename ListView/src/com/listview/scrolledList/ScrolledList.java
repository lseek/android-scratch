package com.listview.scrolledList;

import android.content.res.Resources;
import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.util.Log;


class CustomAdapter extends ArrayAdapter<String> {
    private SparseBooleanArray enabledItems = new SparseBooleanArray();
    private int currSelection;

    public CustomAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("ListViewScrolledList", String.format("getView(%d)", position));
        TextView item = (TextView) super.getView(position, convertView, parent);
        if (!isEnabled(position)) {
            Log.d("ListViewScrolledList", String.format("Position:%d is not enabled", position));
            item.setTextColor(Color.parseColor("#00ff00"));
        } else if (position == currSelection) {
            item.setBackgroundColor(Color.parseColor("#555555"));
        } else {
            item.setTextColor(Color.WHITE);
            item.setBackgroundColor(Color.BLACK);
        }
        return item;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return enabledItems.get(position, true);
    }

    public void disableItem(int position) {
        boolean state = enabledItems.get(position, true);
        enabledItems.put(position, false);
        Log.d("ListViewScrolledList", String.format("Disabled:%d", position));
    }

    public void setSelection(int position) {
        currSelection = position;
    }
}

public class ScrolledList extends Activity {
    Resources res;
    TextView infoArea;
    ListView items;
    CustomAdapter entries;
    String[] inStrs;
    int currIdx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        res = getResources();
        inStrs = res.getStringArray(R.array.lorem);

        items = (ListView)findViewById(R.id.wordList);
        entries = new CustomAdapter(this, android.R.layout.simple_list_item_1, inStrs);
        items.setAdapter(entries);
        items.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView currItem = (TextView)view;
                infoArea.setText(String.format("Selection:%s", currItem.getText().toString()));
                currIdx = position;
                entries.setSelection(position);
            }
        });

        infoArea = (TextView)findViewById(R.id.infoArea);
        infoArea.setBackgroundColor(Color.parseColor("#666600"));

    }
}
