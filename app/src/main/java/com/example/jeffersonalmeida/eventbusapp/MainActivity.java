package com.example.jeffersonalmeida.eventbusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);

        EventBus.getDefault().register(this);

        boolean isConnected = new NetworkChecker(this).connected();
        setTexView(isConnected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    public void click(View view){
        startActivity(new Intent(this, TelaDois.class));
    }

    // method that will be called when someone posts an event NetworkStateChanged
    @Subscribe
    public void onEventMainThread(NetworkStateChanged event) {

        Log.d("MainActivity", "onEventMainThread");

        setTexView(event.isInternetConnected());
    }

    private void setTexView(Boolean isInternetConnected) {
        if (isInternetConnected) {
            textView.setText("We Have Internet Connection!");
            int color = getColor(R.color.accent_material_light);
            textView.setTextColor(color);
        }else {
            textView.setText("No Internet connection!");
            int color = getColor(R.color.abc_input_method_navigation_guard);
            textView.setTextColor(color);
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
