package com.grid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.graphics.Color;
import java.util.Random;


public class Grid extends Activity {
    static int PARENT = LayoutParams.MATCH_PARENT;
    static int WRAP = LayoutParams.WRAP_CONTENT;

    int gridSize = 10;
    LinearLayout UI;
    LinearLayout clueFrame;
    TableLayout wordGrid;

    private LinearLayout createClueArea(LinearLayout parent) {
        TextView clueArea;
        LinearLayout frame;

        frame = new LinearLayout(this);
        frame.setLayoutParams(new LinearLayout.LayoutParams(PARENT, 0, 30));
        frame.setOrientation(LinearLayout.VERTICAL);
        frame.setBackgroundColor(Color.parseColor("#ffffaa"));
        parent.addView(frame);

        clueArea = new TextView(this);
        clueArea.setText("Clue Area");
        frame.addView(clueArea);
        return frame;
    }

    private TextView mkCell() {
        TextView t;
        char c;
        Random rnd = new Random();

        c = (char)('A' + rnd.nextInt(25));
        t = new TextView(this);
        t.setText(Character.toString(c));
        t.setTextColor(Color.parseColor("#ffffff"));
        t.setGravity(Gravity.CENTER);
        t.setPadding(5, 2, 5, 2);
        return t;
    }

    private TableLayout createWordGrid(LinearLayout parent) {
        TableLayout g;
        TableRow r;
        TableRow.LayoutParams rp;
        TableLayout.LayoutParams tp;
        int i, j;

        g = new TableLayout(this);
        rp = new TableRow.LayoutParams(PARENT, WRAP);
        rp.weight = 1;
        tp = new TableLayout.LayoutParams(PARENT, WRAP);
        tp.weight = 1;
        for (i = 0; i < gridSize; i++) {
            r = new TableRow(this);
            r.setGravity(Gravity.CENTER);
            for (j = 0; j < gridSize; j++) {
                r.addView(mkCell(), rp);
            }
            g.addView(r, tp);
        }
        parent.addView(g, new LinearLayout.LayoutParams(PARENT, 0, 70));
        return g;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI = new LinearLayout(this);
        UI.setOrientation(LinearLayout.VERTICAL);

        LayoutParams layoutParam;
        layoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT); 
        // set LinearLayout as a root element of the screen 
        setContentView(UI, layoutParam);

        wordGrid = createWordGrid(UI);
        clueFrame = createClueArea(UI);
    }
}
