package com.izrik.fileman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pwd = "/";
        File f = new File(pwd);
        File[] files = f.listFiles();

        ArrayAdapter<File> adapter = new ArrayAdapter<File>(this, R.layout.file_entry, files);
        ListView listView = (ListView)findViewById(R.id.object_list);
        listView.setAdapter(adapter);
    }
}
