/*
 * Show files in a directory as a list and allow the user to choose one of
 * them.
 */
package com.scratch.fileio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import java.io.File;
import android.widget.*;


public class FileChooser extends Activity
{
    private String selection;
    ListView fileListView;
    TextView infoView;
    String[] fileList;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fileListView = (ListView)findViewById(R.id.fileList);
        infoView = (TextView)findViewById(R.id.info);

        File appDir = getExternalFilesDir(null);
        fileList = appDir.list();

        ArrayAdapter<String> fileNameAdapter;
        fileNameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fileList);
        fileListView.setAdapter(fileNameAdapter);

        infoView.setText(appDir.toString());

        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                infoView.setText(String.format("Selection:%s", fileList[position]));
            }
        });

    }
}
