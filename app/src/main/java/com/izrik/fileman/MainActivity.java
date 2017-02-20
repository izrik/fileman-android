package com.izrik.fileman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pwd = "";

        Intent intent = getIntent();
        if (intent != null) {
            pwd = intent.getStringExtra("pwd");
        }

        if (pwd == null || pwd == "") pwd = "/";

        String title = "FileMan";
        String newTitle = title + " - " + pwd;
        setTitle(newTitle);

        File f = new File(pwd);
        final File[] files = f.listFiles();

        ArrayAdapter<File> adapter = new ArrayAdapter<File>(this, R.layout.file_entry, files);
        ListView listView = (ListView)findViewById(R.id.object_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                File file = files[position];
                if (file.isDirectory()) {
                    intent1.putExtra("pwd", file.toString());
                    startActivity(intent1);
                }
            }
        });
    }
}
