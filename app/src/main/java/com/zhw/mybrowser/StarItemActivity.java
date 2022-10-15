package com.zhw.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StarItemActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    List<String> Items = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_item);
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String category = bundle.getString("category");


        dbHelper = new MyDatabaseHelper(this,"StarCategoryItem.db",null,4);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("StarCategoryItem", null, "category=?",new String[]{category},null,null,null);
        if(cursor.moveToFirst()){
            do{

                @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("name"));

                Items.add(url);
            }while(cursor.moveToNext());

        }

        setContentView(R.layout.activity_star_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(StarItemActivity.this, android.R.layout.simple_list_item_1,Items);
        ListView listView = (ListView) findViewById(R.id.ListView1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = Items.get(i);
                setContentView(R.layout.activity_star_item);
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) WebView webView = findViewById(R.id.WebView1);

                webView.loadUrl(url);
                webView.setWebViewClient(new WebViewClient());

            }
        });
    }
}