package com.example.sarveshagrawal.canadiandetails.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.sarveshagrawal.canadiandetails.Data.Canada_List_Model;
import com.example.sarveshagrawal.canadiandetails.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Canada_List_Model> mList = new ArrayList();
    ListView listView;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

//    private boolean checkConnect() {
//
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo() != null;
//    }
}
