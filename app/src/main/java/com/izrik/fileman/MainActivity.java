package com.izrik.fileman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public void RequestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pwd = "";

        Intent intent = getIntent();
        if (intent != null) {
            pwd = intent.getStringExtra("pwd");
        }

        this.RequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (pwd == null || pwd == "") {
            pwd = Environment.getExternalStorageDirectory().toString(); // /storage/emulated/0
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).toString(); // /storage/emulated/0/Alarms
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString(); // /storage/emulated/0/DCIM
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString(); // /storage/emulated/0/Documents
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(); // /storage/emulated/0/Download
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString(); // /storage/emulated/0/Movies
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString(); // /storage/emulated/0/Music
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).toString(); // /storage/emulated/0/Notifications
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString(); // /storage/emulated/0/Pictures
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).toString(); // /storage/emulated/0/Podcasts
//            pwd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).toString(); // /storage/emulated/0/Ringtones
//            pwd = getExternalFilesDir(Environment.DIRECTORY_ALARMS).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Alarms
//            pwd = getExternalFilesDir(Environment.DIRECTORY_DCIM).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/DCIM
//            pwd = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Documents
//            pwd = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Download
//            pwd = getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Movies
//            pwd = getExternalFilesDir(Environment.DIRECTORY_MUSIC).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Music
//            pwd = getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Notifications
//            pwd = getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Pictures
//            pwd = getExternalFilesDir(Environment.DIRECTORY_PODCASTS).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Podcasts
//            pwd = getExternalFilesDir(Environment.DIRECTORY_RINGTONES).toString(); // /storage/emulated/0/Android/data/com.izrik.fileman/files/Ringtones
//            pwd = "/storage/self";
//            pwd = this.getFilesDir().toString();
            pwd = "/";
        }

        String title = "FileMan";
        String newTitle = title + " - " + pwd;
        setTitle(newTitle);

        File f = new File(pwd);
        boolean isAbsolute = f.isAbsolute();
        boolean isDirectory = f.isDirectory();
        boolean isFile = f.isFile();
        boolean isHidden = f.isHidden();
        boolean exists = f.exists();
        final File[] files = f.listFiles();

        ArrayAdapter<File> adapter = new ArrayAdapter<File>(this, R.layout.file_entry, files);
        ListView listView = (ListView) findViewById(R.id.object_list);
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
