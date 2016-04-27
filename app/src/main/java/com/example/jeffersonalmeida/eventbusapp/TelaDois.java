package com.example.jeffersonalmeida.eventbusapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TelaDois extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dois);

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

    // method that will be called when someone posts an event NetworkStateChanged
    @Subscribe
    public void onEventMainThread(NetworkStateChanged event) {

        Log.d("TelaDois", "onEventMainThread");

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
        getMenuInflater().inflate(R.menu.menu_tela_dois, menu);
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
