package com.zhw.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    List<String> urls = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new MyDatabaseHelper(this,"Record.db",null,4);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Cursor cursor = db.query("Record",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String Url = cursor.getString(cursor.getColumnIndex("url"));
                @SuppressLint("Range") String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                System.out.println(timestamp);
                urls.add(Url);
            }while(cursor.moveToNext());

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RecordActivity.this, android.R.layout.simple_list_item_1,urls);
        ListView listView = (ListView) findViewById(R.id.ListView1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = urls.get(i);
                setContentView(R.layout.activity_web);
                WebView webView = findViewById(R.id.WebView1);

                webView.loadUrl(url);
                webView.setWebViewClient(new WebViewClient());

            }
        });
        Button button1;
        button1 = (Button) findViewById(R.id.Button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecordActivity.this.finish();
            }
        });

    }
}