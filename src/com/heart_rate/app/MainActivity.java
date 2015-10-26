package com.heart_rate.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.*;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends TabActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost host = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = host.newTabSpec("tag1");
        tab1.setContent(new Intent(this, HeartBeatCalculator.class));
        tab1.setIndicator("Calculator");

        TabHost.TabSpec tab2 = host.newTabSpec("tag2");
        tab2.setContent(new Intent(this, History.class));
        tab2.setIndicator("History");

        TabHost.TabSpec tab3 = host.newTabSpec("tag3");
        tab3.setContent(new Intent(this, Reference.class));
        tab3.setIndicator("Reference");


        host.addTab(tab1);
        host.addTab(tab2);
        host.addTab(tab3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.dev:
                createInstructionsDialog(R.string.dev, R.string.dev_title);
                break;
            case R.id.contact:
                createInstructionsDialog(R.string.contno_, R.string.contact);
                break;

            case R.id.rate:
                break;

            case R.id.instr:
                createInstructionsDialog(R.string.instruct, R.string.Instructions);
                break;

            case R.id.ref:
                Intent intent = new Intent(MainActivity.this, HeartbeatReference.class);
                startActivity(intent);
                break;

            case R.id.his:
                History.clearHistory(this);
                break;
        }
        return true;
    }


    public void createInstructionsDialog(int messageId, int titleId){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(messageId);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle(titleId);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog =  builder.create();
        dialog.show();
    }
}
