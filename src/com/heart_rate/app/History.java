package com.heart_rate.app;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jaat on 21-10-2015.
 */
public class History extends ListActivity {

    String heartbeat = null;
    static ArrayList<String> heartbeat_data = new ArrayList<String>();
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        //CREATING DATABASE AND TABLE
        SQLiteDatabase db = openOrCreateDatabase("heartrate_history", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS history(heart_rate INT(3));");
        db.close();

        try {
            insertIntoDatabase(getIntent().getExtras().getString("heartbeat"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (heartbeat_data.isEmpty()){
            adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, heartbeat_data);

            //LOADING PREVIOUS DATA
            loadHeartbeatData();
            setupListview();
        }
        else setupListview();

    }



    private void setupListview(){
        setListAdapter(adapter);
    }
    private void insertIntoDatabase(String heartbeat){
        if(heartbeat == null || heartbeat.equals("")) return;
        SQLiteDatabase db = openOrCreateDatabase("heartrate_history", MODE_PRIVATE, null);
        db.execSQL("INSERT INTO history VALUES(" + heartbeat + ");");
        db.close();

        //UPDATING LISTVIEW
        heartbeat_data.add(heartbeat);
    }
    private void loadHeartbeatData(){
        SQLiteDatabase db = openOrCreateDatabase("heartrate_history", MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM history;", null);
        while (cursor.moveToNext()){
            int heartbeat = cursor.getInt(0);
            heartbeat_data.add(String.valueOf(heartbeat));
        }
        cursor.close();

        db.close();
    }
    public static void clearHistory(Context context){
        //CLEARING HISTORY
        SQLiteDatabase db = context.openOrCreateDatabase("heartrate_history", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE history;");
        db.execSQL("CREATE TABLE IF NOT EXISTS history(heart_rate INT(3));");
        db.close();
        HeartBeatCalculator.createToast("History Cleared");

        //UPDATING LISTVIEW
        heartbeat_data.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
