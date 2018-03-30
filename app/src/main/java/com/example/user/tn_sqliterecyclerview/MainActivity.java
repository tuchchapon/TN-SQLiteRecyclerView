package com.example.user.tn_sqliterecyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                        /*
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                                */
                        startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                  }
            });

      }

      @Override
      public void onStart() {
            super.onStart();

            SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

            String sql =
                    "SELECT product.*, image.pro_image FROM product " +
                            "LEFT JOIN image " +
                            "ON product._id = image.pro_id";

            Cursor cursor = db.rawQuery(sql, null);

            ArrayList<RowItem> itemArray = new ArrayList<>();
            RowItem item;
            while(cursor.moveToNext()) {
                  item = new RowItem();
                  item._id = cursor.getInt(0);
                  item.name = cursor.getString(1);
                  item.image = cursor.getBlob(3);
                  itemArray.add(item);
            }

            RecyclerView rcv = (RecyclerView)findViewById(R.id.recycler_view);
            CustomAdapter adapter = new CustomAdapter(this, itemArray);
            rcv.setAdapter(adapter);

            //RecyclerView.ItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            //rcv.addItemDecoration(itemDecor);

            //LinearLayoutManager layout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            GridLayoutManager layout = new GridLayoutManager(this, 2);
            rcv.setLayoutManager(layout);

            //cursor.close();
      }

      @Override
      public void onBackPressed() {
            finishAffinity();
            System.exit(0);

            /*
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            super.onDestroy();
            */
            /*
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            */
      }

      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
      }

      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if(id == R.id.action_settings) {
                  return true;
            }

            return super.onOptionsItemSelected(item);
      }
}
