package com.zhw.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
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

public class StarActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    List<String> categories = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        dbHelper = new MyDatabaseHelper(this,"StarCategory.db",null,4);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put("name","收藏夹1");
//        db.insert("StarCategory",null,values);
//        values.clear();





        Cursor cursor = db.query("StarCategory",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String item = cursor.getString(cursor.getColumnIndex("name"));


                categories.add(item);
            }while(cursor.moveToNext());

        }

        setContentView(R.layout.activity_star);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(StarActivity.this, android.R.layout.simple_list_item_1,categories);
        ListView listView = (ListView) findViewById(R.id.ListView1);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StarActivity.this, StarItemActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("category", categories.get(i));
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


//        dbHelper = new MyDatabaseHelper(this,"StarCategoryItem.db",null,4);
//        db = dbHelper.getWritableDatabase();
//        values.put("category","收藏夹1");
//        values.put("name","歪比巴卜");
//        db.insert("StarCategoryItem",null,values);
//        values.clear();
        Button button1;
        button1 = (Button) findViewById(R.id.Button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StarActivity.this.finish();
            }
        });

    }
}