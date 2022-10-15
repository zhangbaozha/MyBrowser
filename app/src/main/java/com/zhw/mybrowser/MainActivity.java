package com.zhw.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new MyDatabaseHelper(this,"Record.db",null,4);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1;
        button1 = (Button) findViewById(R.id.Button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 =(EditText) findViewById (R.id.EditText1);
                String url = editText1.getText().toString();

                ContentValues values = new ContentValues();
                values.put("url",url);
                db.insert("Record",null,values);
                values.clear();




                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                Bundle bundle=new Bundle();

                bundle.putString("url", url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button button2;
        button2 = (Button) findViewById(R.id.Button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 =(EditText) findViewById (R.id.EditText2);
                String keyWord = editText1.getText().toString();
                String url = "http://www.baidu.com/s?wd=" + keyWord;
                ContentValues values = new ContentValues();
                values.put("url",url);
                db.insert("Record",null,values);
                values.clear();



                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url", url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // 建立数据源
        String[] mItems = {"历史记录", "收藏夹", "全屏", "Item 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                    startActivity(intent);
                }
                if(i == 1){
                    Intent intent = new Intent(MainActivity.this, StarActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}