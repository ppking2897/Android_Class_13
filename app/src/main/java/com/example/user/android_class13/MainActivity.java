package com.example.user.android_class13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Boolean isStart;
    private MyReceiver receiver;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textview);

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ppking");
        registerReceiver(receiver, filter);

    }
    public void finish() {
        super.finish();
        unregisterReceiver(receiver);
    }

    public void startservice(View v){
        Intent it = new Intent(this, MyService.class);

        it.putExtra("isstart",isStart);
        it.putExtra("value", (int)(Math.random()*100)+1);

        startService(it);
        isStart = true ;

    }
    public void stopservice(View v){
        Intent it = new Intent(this, MyService.class);
        stopService(it);

    }
    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("ppking", "got it");
            String mesg = intent.getStringExtra("ppking");
            textView.setText(mesg);
        }
    }


}
